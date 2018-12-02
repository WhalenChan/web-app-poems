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
        return JSON.toJSONString(this.service.getTang(Integer.valueOf(counter)));
    }

    /**
     * 导航到具体某首诗
     *
     * @param request http请求
     * @return 唐诗内容JSON
     */
    @GetMapping("tang/listDetails")
    public String fetchTangDetails(HttpServletRequest request) {
        String poetryId = request.getParameter("poetryId");
        String authorId = request.getParameter("authorId");

        return JSON.toJSONString(this.service.getTangDetails(poetryId, authorId));
    }

    /**
     * 唐诗搜索
     *
     * @param request
     * @return
     */
    @GetMapping("tang/search")
    public String doSearchTang(HttpServletRequest request) {
        String counter = request.getParameter("counter");
        //作品或作者
        String searchText = request.getParameter("searchText");

        return JSON.toJSONString(this.service.getTangSearch(searchText, Integer.valueOf(counter)));
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

        return JSON.toJSONString(this.service.getSong(Integer.valueOf(counter)));
    }

    /**
     * 导航到具体某首宋词
     *
     * @param request http请求
     * @return 宋词内容JSON
     */
    @GetMapping("song/listDetails")
    public String fetchSongDetails(HttpServletRequest request) {
        String poemId = request.getParameter("poemsId");
        String authorId = request.getParameter("authorId");

        return JSON.toJSONString(this.service.getSongDetails(poemId, authorId));
    }

    /**
     * 宋词搜索
     *
     * @param request
     * @return
     */
    @GetMapping("song/search")
    public String doSearchSong(HttpServletRequest request) {
        String counter = request.getParameter("counter");
        //作品或作者
        String searchText = request.getParameter("searchText");

        return JSON.toJSONString(this.service.getSongSearch(searchText, Integer.valueOf(counter)));
    }

}
