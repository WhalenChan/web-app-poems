package org.chan.poems.controller;

import com.alibaba.fastjson.JSON;
import org.chan.poems.service.PoemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
     * @param request http请求
     * @return 唐诗列表JSON
     */
    @GetMapping("tang/list")
    public String fetchTang(HttpServletRequest request) {
        String counter = request.getParameter("counter");
        String searchText = request.getParameter("searchText");
        return JSON.toJSONString(this.service.getTang());
    }

    /**
     * 导航到具体某首诗
     *
     * @param request http请求
     * @return 唐诗内容JSON
     */
    @GetMapping("tang/listDetails")
    public String fetchTangDetails(HttpServletRequest request) {
        String poetryId = request.getParameter("poetry_id");
        String authorId = request.getParameter("author_id");

        return JSON.toJSONString(this.service.getTangDetails(poetryId, authorId));
    }

    /**
     * 初始化宋词页面
     *
     * @param request http请求
     * @return 宋词列表JSON
     */
    @GetMapping("song/list")
    public String fetchSong(HttpServletRequest request) {
        String counter = request.getParameter("counter");
        String searchText = request.getParameter("searchText");

        return JSON.toJSONString(this.service.getSong());
    }

    /**
     * 导航到具体某首宋词
     *
     * @param request http请求
     * @return 宋词内容JSON
     */
    @GetMapping("song/listDetails")
    public String fetchSongDetails(HttpServletRequest request) {
        String poemId = request.getParameter("poems_id");
        String authorId = request.getParameter("author_id");

        return JSON.toJSONString(this.service.getSongDetails(poemId, authorId));
    }

}
