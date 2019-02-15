package idyjy;

import idyjy.dao.PageUrlDao;
import idyjy.dao.ProxyDao;
import idyjy.entity.PageUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * @Description
 * @Author zhangbaoning
 * @Date 2019/2/14
 */
@Component
public class IdyjyPageProcessor implements PageProcessor, ApplicationRunner {
    @Autowired
    ProxyDao proxyDao;
    @Autowired
    ReqDataModelPipeline pipeline;
    @Autowired
    private PageUrlDao pageUrlDao;
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    @Override
    public void process(Page page) {

        try {
            System.out.println(page.getHtml());
            page.putField("title", page.getHtml().xpath("//div[@class='h1title']/h1/span[@id='name']/text()"));
            page.putField("url", page.getHtml().xpath("//input[@class='down-true-url']/@value").toString());

            List<String> downList = page.getHtml().regex("/down/[0-9]+\\-[0-9]+\\-[0-9]+\\.html").all();
           /* for (int i = 0; i <downList.size() ; i++) {
                String url = downList.get(i);
                downList.set(i,"http://www.idyjy.com"+url);
            }*/
            System.out.println(downList);
            page.addTargetRequests(downList);
            if (page.getResultItems().get("url") == null) {
               /* List<String> subList = page.getHtml().regex("http://www\\.idyjy\\.com/sub/[0-9]+\\.html").all();
                System.out.println(subList);
                page.addTargetRequests(subList);*/
                page.setSkip(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Site getSite() {
        return site;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {

            HttpClientDownloader httpClientDownloader = new HttpClientDownloader();

            if (true) {
                List<PageUrl> pageUrlList = pageUrlDao.findAll();
                for (PageUrl pageUrl : pageUrlList) {
                    int random = new Random().nextInt(10000);
                    Optional<idyjy.entity.Proxy> proxyOptional = proxyDao.findById(random);
                    Proxy proxy = new Proxy(proxyOptional.get().getIp(), proxyOptional.get().getPort());
                    httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(proxy));
                    if (pageUrl.getStatus() == 0) {
                        Spider.create(new IdyjyPageProcessor()).addUrl(pageUrl.getUrl())
                                .setDownloader(httpClientDownloader)
                                .addPipeline(pipeline)
                                .addPipeline(new ConsolePipeline())
                                .thread(1).run();
                    }
                    pageUrl.setStatus(1);
                    pageUrlDao.save(pageUrl);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
