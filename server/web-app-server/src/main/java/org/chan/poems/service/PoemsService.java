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

    public List<Map<String, String>> getIndex() {
        return this.poemsDao.queryIndex();
    }

}
