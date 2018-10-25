package com.example.demo.controller;

import com.example.demo.domain.GoodsDO;
import com.example.demo.domain.ManagerDO;
import com.example.demo.service.GoodsService;
import com.example.demo.service.ManagerService;
import com.example.demo.utils.LayuiTableUtil;
import com.example.demo.utils.R;
import com.example.demo.utils.SaveImage;
import com.example.demo.utils.TableQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/manager")
public class ManageController {
    @Autowired
    GoodsService goodsService;
    @Autowired
    ManagerService managerService;
    @Autowired
    SaveImage saveImage;

    /**
     * 管理员页面
     * @param httpSession
     * @return
     */
    @GetMapping()
    String manage(HttpSession httpSession){
        Object admin = httpSession.getAttribute("manager");
        if(httpSession.getAttribute("manager") != null){
            return "/manager/manager";
        }else
            return "/manager/managerLogin";
    }

    /**
     * 商品列表
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    LayuiTableUtil list(@RequestParam Map<String, Object> map){
        TableQuery tableQuery = new TableQuery(map);
        List<GoodsDO> list = goodsService.list(tableQuery);
        int count = goodsService.count(tableQuery);
        LayuiTableUtil tableUtil = new LayuiTableUtil(list, count);
        return tableUtil;
    }

    /**
     * 管理员登录页面
     * @return
     */
    @GetMapping("/managerLogin")
    String manageLogin(){
        return "/manager/managerLogin";
    }

    /**
     * 管理员登录验证
     * @param managerDO
     * @param httpSession
     * @return
     */
    @PostMapping("managerLogin")
    @ResponseBody
    R manageLogin(ManagerDO managerDO, HttpSession httpSession){
        R r = new R();
        ManagerDO db = managerService.getByName(managerDO.getUsername());
        if(db != null && db.getPassword().equals(managerDO.getPassword())){
            httpSession.setAttribute("manager", db.getUsername());
            r.setCode(0);
            r.setMsg("登录成功");
        }
        else if(db == null){
            r.setCode(-1);
            r.setMsg("账号不存在");
        }
        else {
            r.setCode(-1);
            r.setMsg("密码错误");
        }
        return r;
    }

    /**
     * 商品信息修改页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    String edit(@PathVariable("id") String id, Model model){
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        List<GoodsDO>  list = goodsService.list(map);
        model.addAttribute("item", list.get(0));
        return "/manager/edit";
    }

    /**
     * 更新商品信息
     * @param goodsDO
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    int save(GoodsDO goodsDO, MultipartFile image){
        System.out.println("id" + goodsDO.getId());
        goodsService.save(goodsDO);
        if(image != null)
            saveImage.save(goodsDO.getId(), image);
        return 0;
    }

    /**
     * 商品添加页面
     * @return
     */
    @GetMapping("/add")
    String add(){
        return "/manager/add";
    }

    /**
     * 添加商品
     * @param goodsDO
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    int add(GoodsDO goodsDO, MultipartFile image){
        goodsDO.setId(UUID.randomUUID().toString());
        goodsService.add(goodsDO);
        if(image != null)
            saveImage.save(goodsDO.getId(), image);
        return 0;
    }

    /**
     * 商品图片上传页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/uploadImg/{id}")
    String uploadImg(@PathVariable("id") String id, Model model){
        model.addAttribute("id", id);
        return "/manager/uploadImg";
    }

    /**
     * 保存商品图片
     * @param id
     * @param image
     * @return
     */
    @PostMapping("/uploadImg")
    @ResponseBody
    R uploadImg(String id, MultipartFile image){
        saveImage.save(id, image);
        R r = new R();
        r.setCode(0);
        return r;
    }

    /**
     * 管理员退出登录
     */
    @GetMapping("/exit")
    String exit(HttpSession httpSession){
        httpSession.removeAttribute("manager");
        return "/manager/managerLogin";
    }
}
