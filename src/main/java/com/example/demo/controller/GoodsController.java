package com.example.demo.controller;

import com.example.demo.domain.BuyDO;
import com.example.demo.domain.CartDO;
import com.example.demo.domain.GoodsDO;
import com.example.demo.service.BuyService;
import com.example.demo.service.CartService;
import com.example.demo.service.GoodsService;
import com.example.demo.utils.R;
import com.example.demo.utils.TimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    GoodsService goodsService;
    @Autowired
    CartService cartService;
    @Autowired
    BuyService buyService;

    /**
     * 商品详情页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("item/{id}")
    String item(@PathVariable("id") String id, Model model){
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        GoodsDO goodsDO = goodsService.list(map).get(0);
        model.addAttribute("item", goodsDO);
        return "goods/item";
    }

    /**
     * 添加到购物车
     * @param goodsid
     * @param goodsnumber
     * @param httpSession
     * @return
     */
    @PostMapping("addItem")
    @ResponseBody
    R addItem(String goodsid, int goodsnumber, HttpSession httpSession){
        R r = new R();
        Map<String, Object> map = new HashMap<>();
        map.put("id", goodsid);
        GoodsDO goodsDO = goodsService.list(map).get(0);
        if (httpSession.getAttribute("username") == null){      //判断用户是否登录
            r.code = 1;
            return r;
        }else if(goodsnumber > goodsDO.getNumber()){                //判断用户选择的数量是否大于库存
            r.code = -1;
            r.msg = "库存不足";
            return r;
        } else {
            //=============添加商品到购物车，
            String username = httpSession.getAttribute("username").toString();
            Map<String, Object> map1 = new HashMap<>();
            map1.put("username", username);
            map1.put("goodsid", goodsid);
            List<CartDO> list = cartService.list(map1);
            //--------------如果商品已经在用户的购物车，则增加购物车中该商品的数量。否则将商品信息添加到购物车。
            if(list != null && list.size() != 0){
                CartDO param = list.get(0);
                param.setGoodsnumber(goodsnumber);
                cartService.addNumber(list.get(0));
            }
            else{
                CartDO cartDO = new CartDO();
                cartDO.setId(UUID.randomUUID().toString());
                cartDO.setUsername(httpSession.getAttribute("username").toString());
                cartDO.setGoodsid(goodsid);
                cartDO.setGoodsnumber(goodsnumber);
                cartService.add(cartDO);
            }
            goodsService.addNumber(goodsid, -1 * goodsnumber);  //减少库存
            r.code = 0;
            return r;
        }
    }

    /**
     * 购物车页面
     * @param model
     * @param httpSession
     * @return
     */
    @GetMapping("shoppingcart")
    String shoppingCart(Model model, HttpSession httpSession){
        if(httpSession.getAttribute("username") == null){   //如果没有登录，则返回登录页面
            return "login";
        }
        //根据username查询购物车信息
        else {
            Map<String, Object> map = new HashMap<>();
            map.put("username", httpSession.getAttribute("username"));
            List<CartDO> list = cartService.list(map);
            double totalPrice = 0;
            int size = list.size();
            for(CartDO cartDO: list){   //计算购物车的总价格
                totalPrice += cartDO.getPrice();
            }
            model.addAttribute("list", list);   //用户购物车中的全部商品
            model.addAttribute("totalPrice", totalPrice);   //总价格
            model.addAttribute("size", size);   //商品数量
            return "goods/shoppingcart";
        }
    }

    /**
     * 根据id删除购物车的商品
     * @param id
     * @return
     */
    @PostMapping("delete")
    @ResponseBody
    String delete(String id){
        CartDO cartDO = cartService.getById(id);
        goodsService.addNumber(cartDO.getGoodsid(), cartDO.getGoodsnumber());
        cartService.delete(id);
        return "ok";
    }

    /**
     * 根据id查询购物车中商品信息
     * @param id
     * @return
     */
    @PostMapping("getItem")
    @ResponseBody
    GoodsDO getItem(String id){
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        return goodsService.list(map).get(0);
    }

    /**
     * 根据编号购买购物车中的商品
     * @param id
     * @return
     */
    @PostMapping("buy")
    @ResponseBody
    String buy(String id){
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        CartDO cartDO = cartService.list(map).get(0);
        BuyDO buyDO = new BuyDO();
        buyDO.setId(UUID.randomUUID().toString());
        buyDO.setUsername(cartDO.getUsername());
        buyDO.setGoodsid(cartDO.getGoodsid());
        buyDO.setMoney(cartDO.getPrice());
        buyDO.setGoodsnumber(cartDO.getGoodsnumber());
        buyService.add(buyDO);  //添加购物记录
        GoodsDO goodsDO = new GoodsDO();
        goodsDO.setId(cartDO.getGoodsid());
        goodsDO.setSold(cartDO.getGoodsnumber());
        goodsService.sold(goodsDO); //增加商品销量
        cartService.delete(id); //从购物车删除该商品
        return "ok";
    }

    /**
     * 购买用户购物车中的全部商品
     * @param httpSession
     * @return
     */
    @PostMapping("buyAll")
    @ResponseBody
    String buyAll(HttpSession httpSession){
        Map<String, Object> map = new HashMap<>();
        map.put("username", httpSession.getAttribute("username"));
        List<CartDO> list = cartService.list(map);
        BuyDO buyDO = new BuyDO();
        for (CartDO cartDO : list){
            buyDO.setId(UUID.randomUUID().toString());
            buyDO.setUsername(cartDO.getUsername());
            buyDO.setGoodsid(cartDO.getGoodsid());
            buyDO.setMoney(cartDO.getPrice());
            buyDO.setGoodsnumber(cartDO.getGoodsnumber());
            buyService.add(buyDO);  //添加购物记录
            GoodsDO goodsDO = new GoodsDO();
            goodsDO.setId(cartDO.getGoodsid());
            goodsDO.setSold(cartDO.getGoodsnumber());
            goodsService.sold(goodsDO); //修改商品销量
            cartService.delete(cartDO.getId()); //从购物车删除商品
        }
        return "ok";
    }

    /**
     * 购物记录页面
     * @param model
     * @param httpSession
     * @return
     */
    @GetMapping("shoppinghistory")
    String shoppinghistory(Model model, HttpSession httpSession){
        Map<String, Object> map = new HashMap<>();
        map.put("username", httpSession.getAttribute("username"));
        List<BuyDO> list = buyService.list(map);
        for (BuyDO buyDO : list){
            buyDO.setTime(TimeFormat.timeFormat(buyDO.getTime()));
        }
        model.addAttribute("list", list);   //用户的全部购物记录
        model.addAttribute("size", list.size());    //购物记录的数量
        return "/goods/shoppinghistory";
    }
}
