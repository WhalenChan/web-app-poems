package org.chan.poems.service;

import org.chan.poems.dao.PoemsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *
 * 小程序服务业务逻辑层
 *
 * @author chenrunhui
 * @date 2018/11/28 20:56
 */
@Service
public class PoemsService {

    @Autowired
    private PoemsDao poemsDao;

    /**
     * 获取唐诗
     *
     * @return 唐诗列表
     */
    public List<Map<String, Object>> getTang() {
        return this.poemsDao.queryTang();
    }

    /**
     * 根据唐诗ID和作者ID获取唐诗
     *
     * @param poetryId 唐诗ID
     * @param authorId 作者ID
     * @return
     */
    public Map<String, Object> getTangDetails(String poetryId, String authorId) {
        return this.poemsDao.queryTangDetails(poetryId, authorId);
    }


    /**
     * 获取宋词
     *
     * @return 宋词列表
     */
    public List<Map<String, Object>> getSong() {
        return this.poemsDao.querySong();
    }

    /**
     * 根据宋词ID和作者ID获取唐诗
     *
     * @param poemId 宋词ID
     * @param authorId 作者ID
     * @return
     */
    public Map<String, Object> getSongDetails(String poemId, String authorId) {
        return this.poemsDao.querySongDetails(poemId, authorId);
    }

}
