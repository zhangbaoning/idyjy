package idyjy.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author: zhangbaoning
 * @date: 2019/2/15
 * @since: JDK 1.8
 * @description: TODO
 */
@Entity
@Table(name = "proxy", schema = "movie", catalog = "")
public class Proxy {
    private int id;
    private String ip;
    private Integer port;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ip")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Basic
    @Column(name = "port")
    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proxy proxy = (Proxy) o;
        return id == proxy.id &&
                Objects.equals(ip, proxy.ip) &&
                Objects.equals(port, proxy.port);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ip, port);
    }
}
