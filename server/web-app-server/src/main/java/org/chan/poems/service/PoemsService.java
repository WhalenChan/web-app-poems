package org.chan.poems.service;

import com.google.common.collect.Lists;
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
    public List<Map<String, Object>> getTang(Integer counter) {
        return this.poemsDao.queryTang(counter);
    }

    /**
     * 根据唐诗ID和作者ID获取唐诗
     *
     * @param poetryId 唐诗ID
     * @param authorId 作者ID
     * @return
     */
    public Map<String, Object> getTangDetails(String poetryId, String authorId) {
        Map<String, Object> resultMap = this.poemsDao.queryTangDetails(poetryId, authorId);
        return resultMap;
    }

    /**
     * 唐诗搜索
     *
     * @param searchText 查询条件
     * @param counter 加载次数
     * @return
     */
    public List<Map<String, Object>> getTangSearch(String searchText, Integer counter) {
        List<Map<String, Object>> resultMapList = Lists.newArrayList();
        resultMapList = this.poemsDao.queryTangByAuthor(searchText, counter);
        if (resultMapList != null && resultMapList.size() > 0) {
            return resultMapList;
        }
        resultMapList = this.poemsDao.queryTangByTitle(searchText, counter);
        if (resultMapList != null && resultMapList.size() > 0) {
            return resultMapList;
        }
        return resultMapList;
    }


    /**
     * 获取宋词
     *
     * @return 宋词列表
     */
    public List<Map<String, Object>> getSong(Integer counter) {
        return this.poemsDao.querySong(counter);
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

    /**
     * 宋词搜索
     *
     * @param searchText 查询条件
     * @param counter 加载次数
     * @return
     */
    public List<Map<String, Object>> getSongSearch(String searchText, Integer counter) {
        List<Map<String, Object>> resultMapList = Lists.newArrayList();
        resultMapList = this.poemsDao.querySongByAuthor(searchText, counter);
        if (resultMapList != null && resultMapList.size() > 0) {
            return resultMapList;
        }
        resultMapList = this.poemsDao.querySongByTitle(searchText, counter);
        if (resultMapList != null && resultMapList.size() > 0) {
            return resultMapList;
        }
        return resultMapList;
    }

}
