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
     * @return
     */
    public List<Map<String, Object>> queryTang() {
        String sql = "SELECT * FROM poetry WHERE author_id = ?";
        return this.jdbcTemplate.queryForList(sql, new Object[] { "105" });
    }

    public Map<String, Object> queryTangDetails(String poetryId, String authorId) {
        String sql = "SELECT * FROM poetry WHERE id = ?";
        List<Map<String, Object>> listMap = this.jdbcTemplate.queryForList(sql, new Object[] { poetryId });

        return listMap.size() == 0 ? Collections.emptyMap() : listMap.get(0);
    }

}
