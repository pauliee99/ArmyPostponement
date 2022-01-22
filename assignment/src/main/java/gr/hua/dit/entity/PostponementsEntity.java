package gr.hua.dit.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Postponements", schema = "army", catalog = "")
public class PostponementsEntity {
    private int id;
    private Timestamp date;
    private Timestamp time;
    private String reason;
    private String file;
    private String comments;
    private int status;
    private String commentUpd;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "reason")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Basic
    @Column(name = "file")
    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Basic
    @Column(name = "comments")
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Basic
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "commentUpd")
    public String getCommentUpd() {
        return commentUpd;
    }

    public void setCommentUpd(String commentUpd) {
        this.commentUpd = commentUpd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostponementsEntity that = (PostponementsEntity) o;

        if (id != that.id) return false;
        if (status != that.status) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (reason != null ? !reason.equals(that.reason) : that.reason != null) return false;
        if (file != null ? !file.equals(that.file) : that.file != null) return false;
        if (comments != null ? !comments.equals(that.comments) : that.comments != null) return false;
        if (commentUpd != null ? !commentUpd.equals(that.commentUpd) : that.commentUpd != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (reason != null ? reason.hashCode() : 0);
        result = 31 * result + (file != null ? file.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + (commentUpd != null ? commentUpd.hashCode() : 0);
        return result;
    }
}
