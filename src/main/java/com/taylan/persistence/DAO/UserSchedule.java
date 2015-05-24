package com.taylan.persistence.DAO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Taylan Kurt <taylankurt34@gmail.com>
 */
@Entity
@Table(name = "user_schedule", catalog = "sportclubsystem")
public class UserSchedule implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String mo_schedule;
    private String tu_schedule;
    private String we_schedule;
    private String th_schedule;
    private String fr_schedule;
    private String sa_schedule;
    private String su_schedule;
    private UserInfo userInfo;

    public UserSchedule() {
    }

    public UserSchedule(String mo_schedule, String tu_schedule, String we_schedule, String th_schedule,
            String fr_schedule, String sa_schedule, String su_schedule) {

        this.mo_schedule = mo_schedule;
        this.tu_schedule = tu_schedule;
        this.we_schedule = we_schedule;
        this.th_schedule = th_schedule;
        this.fr_schedule = fr_schedule;
        this.sa_schedule = sa_schedule;
        this.su_schedule = su_schedule;

    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the mo_schedule
     */
    @Column(name = "m_schedule", nullable = true, length = 500)
    public String getMo_schedule() {
        return mo_schedule;
    }

    /**
     * @param mo_schedule the mo_schedule to set
     */
    public void setMo_schedule(String mo_schedule) {
        this.mo_schedule = mo_schedule;
    }

    /**
     * @return the tu_schedule
     */
    @Column(name = "tu_schedule", nullable = true, length = 500)
    public String getTu_schedule() {
        return tu_schedule;
    }

    /**
     * @param tu_schedule the tu_schedule to set
     */
    public void setTu_schedule(String tu_schedule) {
        this.tu_schedule = tu_schedule;
    }

    /**
     * @return the we_schedule
     */
    @Column(name = "w_schedule", nullable = true, length = 500)
    public String getWe_schedule() {
        return we_schedule;
    }

    /**
     * @param we_schedule the we_schedule to set
     */
    public void setWe_schedule(String we_schedule) {
        this.we_schedule = we_schedule;
    }

    /**
     * @return the th_schedule
     */
    @Column(name = "th_schedule", nullable = true, length = 500)
    public String getTh_schedule() {
        return th_schedule;
    }

    /**
     * @param th_schedule the th_schedule to set
     */
    public void setTh_schedule(String th_schedule) {
        this.th_schedule = th_schedule;
    }

    /**
     * @return the fr_schedule
     */
    @Column(name = "f_schedule", nullable = true, length = 500)
    public String getFr_schedule() {
        return fr_schedule;
    }

    /**
     * @param fr_schedule the fr_schedule to set
     */
    public void setFr_schedule(String fr_schedule) {
        this.fr_schedule = fr_schedule;
    }

    /**
     * @return the sa_schedule
     */
    @Column(name = "sa_schedule", nullable = true, length = 500)
    public String getSa_schedule() {
        return sa_schedule;
    }

    /**
     * @param sa_schedule the sa_schedule to set
     */
    public void setSa_schedule(String sa_schedule) {
        this.sa_schedule = sa_schedule;
    }

    /**
     * @return the su_schedule
     */
    @Column(name = "su_schedule", nullable = true, length = 500)
    public String getSu_schedule() {
        return su_schedule;
    }

    /**
     * @param su_schedule the su_schedule to set
     */
    public void setSu_schedule(String su_schedule) {
        this.su_schedule = su_schedule;
    }

    /**
     * @return the userInfo
     */
    @OneToOne()
    @JoinColumn(name = "user_info_id_user")
    public UserInfo getUserInfo() {
        return userInfo;
    }

    /**
     * @param userInfo the userInfo to set
     */
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

}
