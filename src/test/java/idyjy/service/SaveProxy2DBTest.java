package idyjy.service;

import idyjy.dao.ProxyDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author: zhangbaoning
 * @date: 2019/2/15
 * @since: JDK 1.8
 * @description: TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SaveProxy2DBTest {
@Autowired
private SaveProxy2DB db;
    @Test
    public void saveAll() {
       db.saveAll();
    }
}