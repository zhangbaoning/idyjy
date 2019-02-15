package idyjy.service;

import idyjy.dao.ProxyDao;
import idyjy.entity.Proxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * @author: zhangbaoning
 * @date: 2019/2/15
 * @since: JDK 1.8
 * @description: TODO
 */
@Service
public class SaveProxy2DB {
    @Autowired
    private ProxyDao proxyDao;
    public void saveAll(){

        try {
            LineNumberReader reader = new LineNumberReader(new FileReader("C:/Users/lenovo/Desktop/同步/代理.txt"));
            String line= null;
            int id =0;
            while ((line=reader.readLine())!=null){
                System.out.println(line);
                id++;
               String[] str = line.split(":");
                Proxy proxy = new Proxy();
                proxy.setIp(str[0]);
                proxy.setPort(Integer.valueOf(str[1]));
                proxy.setId(id);
                proxyDao.save(proxy);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
