package org.chan.poems.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 *
 * 小程序服务数据库访问层
 *
 * @author chenrunhui
 * @date 2018/11/28 20:57
 */
@Repository
public class PoemsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询唐诗
     *
     * @return 唐诗列表
     */
    public List<Map<String, Object>> queryTang() {
        String sql = "SELECT * FROM poetry WHERE author_id = ?";
        return this.jdbcTemplate.queryForList(sql, new Object[] { "105" });
    }

    /**
     * 根据唐诗ID和作者ID查询唐诗
     *
     * @param poetryId 唐诗ID
     * @param authorId 作者ID
     * @return 唐诗详情
     */
    public Map<String, Object> queryTangDetails(String poetryId, String authorId) {
        String sql = "SELECT * FROM poetry WHERE id = ?";
        List<Map<String, Object>> listMap = this.jdbcTemplate.queryForList(sql, new Object[] { poetryId });

        return listMap.size() == 0 ? Collections.emptyMap() : listMap.get(0);
    }


    /**
     * 查询宋词
     *
     * @return 宋词列表
     */
    public List<Map<String, Object>> querySong() {
        String sql = "SELECT * FROM poems WHERE author_id = ?";
        return this.jdbcTemplate.queryForList(sql, new Object[] { "1" });
    }

    /**
     * 根据宋词ID和作者ID查询宋词
     *
     * @param poemId 宋词ID
     * @param authorId 作者ID
     * @return 宋词详情
     */
    public Map<String, Object> querySongDetails(String poemId, String authorId) {
        String sql = "SELECT * FROM poems WHERE id = ?";
        List<Map<String, Object>> listMap = this.jdbcTemplate.queryForList(sql, new Object[] { poemId });

        return listMap.size() == 0 ? Collections.emptyMap() : listMap.get(0);
    }

}
