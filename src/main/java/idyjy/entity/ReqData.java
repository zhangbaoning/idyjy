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
@Table(name = "req_data", schema = "movie", catalog = "")
public class ReqData {
    private int id;
    private String title;
    private String ed2K;

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
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "ed2k")
    public String getEd2K() {
        return ed2K;
    }

    public void setEd2K(String ed2K) {
        this.ed2K = ed2K;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReqData reqData = (ReqData) o;
        return id == reqData.id &&
                Objects.equals(title, reqData.title) &&
                Objects.equals(ed2K, reqData.ed2K);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, ed2K);
    }
}
