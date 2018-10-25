package com.example.demo.controller;

import com.example.demo.domain.BuyDO;
import com.example.demo.domain.UserDO;
import com.example.demo.service.BuyService;
import com.example.demo.service.GoodsService;
import com.example.demo.service.UserService;
import com.example.demo.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    BuyService buyService;

    /**
     * 用户信息页面
     * @param model
     * @param httpSession
     * @return
     */
    @GetMapping()
    String user(Model model, HttpSession httpSession){
        Map<String, Object> map = new HashMap<>();
        map.put("username", httpSession.getAttribute("username"));
        UserDO userDO = userService.get(map);
        model.addAttribute("user", userDO);
        return "/user/user";
    }

    /**
     * 用户登录页面
     * @return
     */
    @GetMapping("login")
    String login(){
        return "/user/login";
    }

    /**
     * 用户登录验证
     * @param session
     * @param userDO
     * @return
     */
    @PostMapping("identification")
    @ResponseBody
    R index(HttpSession session, UserDO userDO){
        R r = new R();
        String username = userDO.getUsername();
        String password = userDO.getPassword();
        String dbPassword = userService.getPassWord(username);
        if (dbPassword != null && dbPassword.equals(password)){
            session.setAttribute("username", userDO.getUsername());
            r.setCode(0);
            return r;
        }
        else if(dbPassword == null){
            r.setCode(1);
            r.setMsg("用户名不存在");
            return r;
        }else {
            r.setCode(1);
            r.setMsg("密码错误");
            return r;
        }
    }

    /**
     * 用户信息编辑页面
     * @param model
     * @param httpSession
     * @return
     */
    @GetMapping("/edit")
    String edit(Model model, HttpSession httpSession){
        String username = httpSession.getAttribute("username").toString();
        if (username != null){
            Map<String, Object> map = new HashMap<>();
            map.put("username", username);
            UserDO userDO = userService.get(map);
            model.addAttribute("user", userDO);
            return "/user/edit";
        }else
            return "/user/login";
    }

    /**
     * 更新用户信息
     * @param userDO
     * @param httpSession
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    int update(UserDO userDO, HttpSession httpSession){
        userService.update(userDO);
        return 0;
    }

    /**
     * 用户注册页面
     * @return
     */
    @GetMapping("register")
    String register(){
        return "user/register";
    }

    /**
     * 用户注册
     * @param username
     * @param password
     * @param httpSession
     * @return
     */
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

    /**
     * 退出登录
     * @param httpSession
     */
    @PostMapping("exit")
    @ResponseBody
    void exit(HttpSession httpSession){
        httpSession.removeAttribute("username");
    }

    /**
     * 获取当前用户名
     * @param httpSession
     * @return
     */
    @PostMapping("getUser")
    @ResponseBody
    String getUser(HttpSession httpSession){
        if (httpSession.getAttribute("username") != null)
            return httpSession.getAttribute("username").toString();
        else
            return "";
    }

}
