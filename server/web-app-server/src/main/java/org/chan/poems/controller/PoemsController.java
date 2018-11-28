package org.chan.poems.controller;

import com.alibaba.fastjson.JSON;
import org.chan.poems.service.PoemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 小程序服务控制器
 *
 * @author chenrunhui
 */
@Controller
public class PoemsController {

    @Autowired
    private PoemsService service;

    @RequestMapping("/index")
    @ResponseBody
    public String index() {
        return JSON.toJSONString(this.service.getIndex());
    }

}
