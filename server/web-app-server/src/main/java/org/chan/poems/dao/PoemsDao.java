package org.chan.poems.dao;

import org.chan.cons.Constant;
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
    public List<Map<String, Object>> queryTang(Integer counter) {
        String sql = "SELECT id,title,yunlv_rule AS yunlvRule,author_id AS authorId, " +
                "content,dynasty,author " +
                "FROM poetry LIMIT ?,?";
        return this.jdbcTemplate.queryForList(sql,
                new Object[] { counter * Constant.OFFSET, Constant.OFFSET });
    }

    /**
     * 根据唐诗ID和作者ID查询唐诗
     *
     * @param poetryId 唐诗ID
     * @param authorId 作者ID
     * @return 唐诗详情
     */
    public Map<String, Object> queryTangDetails(String poetryId, String authorId) {
        String sql = "SELECT id,title,yunlv_rule AS yunlvRule,author_id AS authorId, " +
                "content,dynasty,author " +
                "FROM poetry WHERE id = ?";
        List<Map<String, Object>> listMap = this.jdbcTemplate.queryForList(sql, new Object[] { poetryId });

        return listMap.size() == 0 ? Collections.emptyMap() : listMap.get(0);
    }


    /**
     * 根据唐诗名称搜索唐诗
     *
     * @param searchText 唐诗名称
     * @param counter 加载次数
     * @return
     */
    public List<Map<String, Object>> queryTangByTitle(String searchText, Integer counter) {
        String sql = "SELECT id,title,yunlv_rule AS yunlvRule,author_id AS authorId, " +
                "content,dynasty,author " +
                "FROM poetry WHERE title=?  LIMIT ?,?";
        return this.jdbcTemplate.queryForList(sql,
                new Object[] {searchText, counter * Constant.OFFSET, Constant.OFFSET});
    }

    /**
     * 根据唐诗作者搜索唐诗
     *
     * @param searchText 唐诗作者
     * @param counter 加载次数
     * @return
     */
    public List<Map<String, Object>> queryTangByAuthor(String searchText, Integer counter) {
        String sql = "SELECT id,title,yunlv_rule AS yunlvRule,author_id AS authorId, " +
                "content,dynasty,author " +
                "FROM poetry WHERE author=?  LIMIT ?,?";
        return this.jdbcTemplate.queryForList(sql,
                new Object[] {searchText, counter * Constant.OFFSET, Constant.OFFSET});
    }


    /**
     * 查询宋词
     *
     * @return 宋词列表
     */
    public List<Map<String, Object>> querySong(Integer counter) {
        String sql = "SELECT id,title,content,author_id AS authorId,dynasty,author " +
                "FROM poems LIMIT ?,?";
        return this.jdbcTemplate.queryForList(sql,
                new Object[] { counter * Constant.OFFSET, Constant.OFFSET });
    }

    /**
     * 根据宋词ID和作者ID查询宋词
     *
     * @param poemId 宋词ID
     * @param authorId 作者ID
     * @return 宋词详情
     */
    public Map<String, Object> querySongDetails(String poemId, String authorId) {
        String sql = "SELECT id,title,content,author_id AS authorId,dynasty,author " +
                "FROM poems WHERE id = ?";
        List<Map<String, Object>> listMap = this.jdbcTemplate.queryForList(sql,
                new Object[] { poemId });

        return listMap.size() == 0 ? Collections.emptyMap() : listMap.get(0);
    }

    /**
     * 根据宋词名称查询宋词
     *
     * @param searchText 宋词名称
     * @param counter 加载次数
     * @return
     */
    public List<Map<String, Object>> querySongByTitle(String searchText, Integer counter) {
        String sql = "SELECT id,title,content,author_id AS authorId,dynasty,author " +
                "FROM poems WHERE title=? LIMIT ?,?";
        return this.jdbcTemplate.queryForList(sql,
                new Object[] { searchText, counter * Constant.OFFSET, Constant.OFFSET });
    }

    /**
     * 根据宋词作者查询宋词
     *
     * @param searchText 宋词名称
     * @param counter 加载次数
     * @return
     */
    public List<Map<String, Object>> querySongByAuthor(String searchText, Integer counter) {
        String sql = "SELECT id,title,content,author_id AS authorId,dynasty,author " +
                "FROM poems WHERE author=? LIMIT ?,?";
        return this.jdbcTemplate.queryForList(sql,
                new Object[] { searchText, counter * Constant.OFFSET, Constant.OFFSET });
    }

}
