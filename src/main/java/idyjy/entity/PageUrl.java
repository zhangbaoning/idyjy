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
@Table(name = "page_url", schema = "movie", catalog = "")
public class PageUrl {
    private int id;
    private String url;
    private Integer status;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageUrl pageUrl = (PageUrl) o;
        return id == pageUrl.id &&
                Objects.equals(url, pageUrl.url) &&
                Objects.equals(status, pageUrl.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, status);
    }
}
