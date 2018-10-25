package com.example.demo.controller;

import com.example.demo.domain.GoodsDO;
import com.example.demo.service.GoodsService;
import com.example.demo.utils.PageUtil;
import com.example.demo.utils.TableQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class DemoController {
    @Autowired
    GoodsService goodsService;

    /**
     *返回首页
     * @param model
     * @param httpSession
     * @return
     */
    @GetMapping()
    String index(Model model, HttpSession httpSession, @RequestParam Map<String, Object> map){
        Map<String, Object> tempMap = new HashMap<>();
        tempMap.put("limit", "10");
        tempMap.put("page", "1");
        tempMap.putAll(map);        //如果map中有limit或page, 会覆盖tempMap中的默认值
        TableQuery tableQuery = new TableQuery(tempMap);
        List<GoodsDO> list = goodsService.list(tableQuery);
        int count = goodsService.count(tableQuery);
        PageUtil pageUtil = new PageUtil(list, count, Integer.parseInt(tempMap.get("page").toString()), Integer.parseInt(tempMap.get("limit").toString()));
        model.addAttribute("pageUtil", pageUtil);
        model.addAttribute("search", map.get("search"));
        for(String key : tempMap.keySet()){
            System.out.println("key " + key + " value " + tempMap.get(key));
        }
        return "index";
    }
}
