package idyjy;

import idyjy.dao.PageUrlDao;
import idyjy.entity.PageUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: zhangbaoning
 * @date: 2019/2/15
 * @since: JDK 1.8
 * @description: TODO
 */
//@Component
public class HandleAgainData implements ApplicationRunner {
    @Autowired private PageUrlDao pageUrlDao;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Set<String> set = new HashSet();
       List<PageUrl> pageUrlList= pageUrlDao.findAll();
        for (PageUrl pageUrl : pageUrlList) {
            set.add(pageUrl.getUrl());
        }
        pageUrlList.clear();
        int id = 0;
        for (String s : set) {
            id ++;
            PageUrl pageUrl = new PageUrl();
            pageUrl.setId(id);
            pageUrl.setUrl(s);
            pageUrl.setStatus(0);
            pageUrlList.add(pageUrl);
        }
        pageUrlDao.deleteAll();
        pageUrlDao.saveAll(pageUrlList);
    }
}
