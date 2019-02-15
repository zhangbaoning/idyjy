package idyjy;

import idyjy.dao.ReqDataDao;
import idyjy.entity.ReqData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Map;

/**
 * @author: zhangbaoning
 * @date: 2019/2/15
 * @since: JDK 1.8
 * @description: TODO
 */
@Component
public class ReqDataModelPipeline implements Pipeline {
    @Autowired
    private ReqDataDao reqDataDao;

    @Override
    public void process(ResultItems resultItems, Task task) {
        Map<String, Object> map = resultItems.getAll();
        ReqData reqData = new ReqData();
        reqData.setTitle( map.get("title").toString());
        reqData.setEd2K( map.get("url").toString());
        reqDataDao.save(reqData);
        System.out.println(map);
    }
}
