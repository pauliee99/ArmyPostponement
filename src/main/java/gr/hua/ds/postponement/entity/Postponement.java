package gr.hua.ds.postponement.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "postponement")
public class Postponement {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "date", nullable = true)
    //@Temporal(TemporalType.TIMESTAMP)
    private Timestamp date;

    @Basic
    @Column(name = "time", nullable = true)
    //@Temporal(TemporalType.TIMESTAMP)
    private Timestamp time;

//    @Basic
//    @Column(name = "office", nullable = false)
//    private int office;
//    @ManyToOne()  // Καμία λειτουργία
//    @ManyToOne(cascade = {CascadeType.ALL} )  // Όλες οι λειτουργίες
//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH},
//            fetch = FetchType.EAGER )
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH},
            fetch = FetchType.EAGER )
    @JoinColumn(name = "office", nullable = false)
    private Office office;

    @Basic
    @Column(name = "office_descr", nullable = true, length = 50)
    private String officeDescr;

    @Basic
    @Column(name = "reason", nullable = false)
    private int reason;

    @Basic
    @Column(name = "reason_descr", nullable = true, length = 50)
    private String reasonDescr;

    @Basic
    @Column(name = "file", nullable = true, length = 255)
    private String file;

    @Basic
    @Column(name = "status", nullable = false)
    private int status;

//    @Basic
//    @Column(name = "user_in", nullable = false, length = 50)
//    private String userIn;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH},
            fetch = FetchType.EAGER )
    @JoinColumn(name = "user_in", nullable = false)
    private User userIn;



    @Basic
    @Column(name = "comment_in", nullable = true, length = 255)
    private String commentIn;

//    @Basic
//    @Column(name = "user_valid", nullable = true, length = 50)
//    private String userValid;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH},
            fetch = FetchType.EAGER )
    @JoinColumn(name = "user_valid", nullable = true)
    private User userValid;



    @Basic
    @Column(name = "comment_valid", nullable = true, length = 255)
    private String commentValid;

//    @Basic
//    @Column(name = "user_approved", nullable = true, length = 50)
//    private String userApproved;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH},
            fetch = FetchType.EAGER )
    @JoinColumn(name = "user_approved", nullable = true)
    private User userApproved;

    @Basic
    @Column(name = "comment_approved", nullable = true, length = 255)
    private String commentApproved;


//    @ManyToOne
//    @JoinColumn(name = "office", referencedColumnName = "id", nullable = false)
//    private Office officeByOffice;
//    @ManyToOne
//    @JoinColumn(name = "reason", referencedColumnName = "id", nullable = false)
//    private Reason reasonByReason;
//    @ManyToOne
//    @JoinColumn(name = "user_in", referencedColumnName = "username", nullable = false)
//    private User userByUserIn;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public String getOfficeDescr() {
        return officeDescr;
    }

    public void setOfficeDescr(String officeDescr) {
        this.officeDescr = officeDescr;
    }

    public int getReason() {
        return reason;
    }

    public void setReason(int reason) {
        this.reason = reason;
    }

    public String getReasonDescr() {
        return reasonDescr;
    }

    public void setReasonDescr(String reasonDescr) {
        this.reasonDescr = reasonDescr;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

//    public String getUserIn() {
//        return userIn;
//    }
    public User getUserIn() {
        return userIn;
    }

//    public void setUserIn(String userIn) {
//        this.userIn = userIn;
//    }
    public void setUserIn(User userIn) {
        this.userIn = userIn;
    }

    public String getCommentIn() {
        return commentIn;
    }

    public void setCommentIn(String commentIn) {
        this.commentIn = commentIn;
    }

    public User getUserValid() {
        return userValid;
    }

    public void setUserValid(User userValid) {
        this.userValid = userValid;
    }

    public String getCommentValid() {
        return commentValid;
    }

    public void setCommentValid(String commentValid) {
        this.commentValid = commentValid;
    }

    public User getUserApproved() {
        return userApproved;
    }

    public void setUserApproved(User userApproved) {
        this.userApproved = userApproved;
    }

    public String getCommentApproved() {
        return commentApproved;
    }

    public void setCommentApproved(String commentApproved) {
        this.commentApproved = commentApproved;
    }


//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Postponement that = (Postponement) o;
//
//        if (id != that.id) return false;
//        if (office != that.office) return false;
//        if (reason != that.reason) return false;
//        if (status != that.status) return false;
//        if (date != null ? !date.equals(that.date) : that.date != null) return false;
//        if (time != null ? !time.equals(that.time) : that.time != null) return false;
//        if (officeDescr != null ? !officeDescr.equals(that.officeDescr) : that.officeDescr != null) return false;
//        if (reasonDescr != null ? !reasonDescr.equals(that.reasonDescr) : that.reasonDescr != null) return false;
//        if (file != null ? !file.equals(that.file) : that.file != null) return false;
//        if (userIn != null ? !userIn.equals(that.userIn) : that.userIn != null) return false;
//        if (commentIn != null ? !commentIn.equals(that.commentIn) : that.commentIn != null) return false;
//        if (userValid != null ? !userValid.equals(that.userValid) : that.userValid != null) return false;
//        if (commentValid != null ? !commentValid.equals(that.commentValid) : that.commentValid != null) return false;
//        if (userApproved != null ? !userApproved.equals(that.userApproved) : that.userApproved != null) return false;
//        if (commentApproved != null ? !commentApproved.equals(that.commentApproved) : that.commentApproved != null)
//            return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = id;
//        result = 31 * result + (date != null ? date.hashCode() : 0);
//        result = 31 * result + (time != null ? time.hashCode() : 0);
//        result = 31 * result + office;
//        result = 31 * result + (officeDescr != null ? officeDescr.hashCode() : 0);
//        result = 31 * result + reason;
//        result = 31 * result + (reasonDescr != null ? reasonDescr.hashCode() : 0);
//        result = 31 * result + (file != null ? file.hashCode() : 0);
//        result = 31 * result + status;
//        result = 31 * result + (userIn != null ? userIn.hashCode() : 0);
//        result = 31 * result + (commentIn != null ? commentIn.hashCode() : 0);
//        result = 31 * result + (userValid != null ? userValid.hashCode() : 0);
//        result = 31 * result + (commentValid != null ? commentValid.hashCode() : 0);
//        result = 31 * result + (userApproved != null ? userApproved.hashCode() : 0);
//        result = 31 * result + (commentApproved != null ? commentApproved.hashCode() : 0);
//        return result;
//    }



}
