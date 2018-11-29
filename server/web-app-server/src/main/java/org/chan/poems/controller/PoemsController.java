package org.chan.poems.controller;

import com.alibaba.fastjson.JSON;
import org.chan.poems.service.PoemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 小程序服务控制器
 *
 * @author chenrunhui
 */
@RestController
public class PoemsController {

    @Autowired
    private PoemsService service;

    /**
     * 初始化唐诗页面
     *
     * @param request
     * @return
     */
    @GetMapping("tang/list")
    public String fetchTang(HttpServletRequest request) {
        String counter = request.getParameter("counter");
        String searchText = request.getParameter("searchText");
        return JSON.toJSONString(this.service.getTang());
    }

    @GetMapping("tang/listDetails")
    public String fetchTangDetails(HttpServletRequest request) {
        String poetryId = request.getParameter("poetry_id");
        String authorId = request.getParameter("author_id");

        return JSON.toJSONString(this.service.getTangDetails(poetryId, authorId));
    }

}
