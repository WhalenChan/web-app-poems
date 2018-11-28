package org.chan.poems.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public List queryIndex() {
        String sql = "SELECT * FROM poems LIMIT 10";
        return this.jdbcTemplate.queryForList(sql, new Object[] {});
    }

}
