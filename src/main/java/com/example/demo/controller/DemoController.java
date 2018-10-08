package com.example.demo.controller;

import com.example.demo.domain.CartDO;
import com.example.demo.domain.GoodsDO;
import com.example.demo.domain.UserDO;
import com.example.demo.service.CartService;
import com.example.demo.service.GoodsService;
import com.example.demo.service.UserService;
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
@RequestMapping("/")
public class DemoController {
    @Autowired
    UserService userService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    CartService cartService;

    @GetMapping()
    String test(Model model, HttpSession httpSession){
        Map<String, Object> map = new HashMap<>();
        model.addAttribute("goods", goodsService.getGoods(map));
        model.addAttribute("username", httpSession.getAttribute("username"));
        return "index";
    }

    @GetMapping("login")
    String login(){
        return "login";
    }

    @PostMapping("identification")
    @ResponseBody
    int index(HttpSession session, UserDO userDO){
        String username = userDO.getUsername();
        String password = userDO.getPassword();
        String dbPassword = userService.getPassWord(username);
        if (dbPassword != null && dbPassword.equals(password)){
            session.setAttribute("username", userDO.getUsername());
            return 1;
        }
        else
            return 0;
    }

    @GetMapping("item/{id}")
    String item(@PathVariable("id") String id, Model model){
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        GoodsDO goodsDO = goodsService.getGoods(map).get(0);
        model.addAttribute("item", goodsDO);
        return "item";
    }

    @PostMapping("addItem")
    @ResponseBody
    Map<String, Object> addItem(String goodsid, int goodsnumber, HttpSession httpSession){

        if (httpSession.getAttribute("username") == null){
            Map<String, Object> map1 = new HashMap<>();
            map1.put("code", 1);
            return map1;
        }else {
            String username = httpSession.getAttribute("username").toString();
            Map<String, Object> map = new HashMap<>();
            map.put("username", username);
            map.put("goodsid", goodsid);
            List<CartDO> list = cartService.list(map);
            if(list == null && list.size() != 0){
                //
            } else{
                CartDO cartDO = new CartDO();
                cartDO.setId(UUID.randomUUID().toString());
                cartDO.setUsername(httpSession.getAttribute("username").toString());
                cartDO.setGoodsid(goodsid);
                cartDO.setGoodsnumber(goodsnumber);
                cartService.add(cartDO);
            }
            Map<String, Object> map1 = new HashMap<>();
            map1.put("code", 0);
            return map1;
        }
    }

    @GetMapping("shoppingcart")
    String shoppingCart(Model model, HttpSession httpSession){
        if(httpSession.getAttribute("username") == null){
            return "login";
        }else {
            Map<String, Object> map = new HashMap<>();
            map.put("username", httpSession.getAttribute("username"));
            List<CartDO> list = cartService.list(map);
            model.addAttribute("list", list);
            return "shoppingcart";
        }
    }

    @PostMapping("delete")
    @ResponseBody
    String delete(String id){
        cartService.delete(id);
        return "ok";
    }

    @GetMapping("register")
    String register(){
        return "register";
    }

    @PostMapping("register")
    @ResponseBody
    int register(String username, String password, HttpSession httpSession){
        if (userService.getPassWord(username) == null){
            UserDO userDO = new UserDO();
            userDO.setUsername(username);
            userDO.setPassword(password);
            userService.add(userDO);
            httpSession.setAttribute("username", userDO.getUsername());
            return 0;
        }else {
            return 1;
        }
    }
    @PostMapping("exit")
    @ResponseBody
    void exit(HttpSession httpSession){
        httpSession.removeAttribute("username");
    }
}
