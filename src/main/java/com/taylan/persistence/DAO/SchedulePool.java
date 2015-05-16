package com.taylan.persistence.DAO;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *@author Taylan Kurt   <taylankurt34@gmail.com>
 *              Schedule Pool Data Annotation  - POJO class
 */
@NamedQueries({
	@NamedQuery(
	name = "getScheduleInformation",
	query = "from SchedulePool s where s.idSchedulePool = :idSchedulePool"
        )
})
@Entity
@Table(name = "schedule_pool", catalog = "sportclubsystem")
public class SchedulePool implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    

    private Integer idSchedulePool;

    private String perpose;

    private String gender;

    private String levell;

    private String moSchedule;

    private String tuSchedule;

    private String weSchedule;

    private String thSchedule;

    private String fchedule;

    private String saSchedule;

    private String suSchedule;
    
    /* To making MANY TO MANY RELATION WITH UserInfo table */
    private Set<UserInfo> users = new HashSet<UserInfo>(0);
    
    /* To making MANY TO MANY RELATION WITH RecommendedExercises table */
    
    private Set<RecommendedExercises> recommendedExercises = new HashSet<RecommendedExercises>(0);
    
    
    public SchedulePool(){};
    
    public SchedulePool(String perpose,String gender
            ,String levell,String moSchedule,String tuSchedule,String weSchedule
            ,String thSchedule,String fchedule,String saSchedule,String suSchedule){
        this.perpose                = perpose;
        this.gender                 = gender;
        this.levell                 = levell;
        this.moSchedule              = moSchedule;
        this.tuSchedule             = tuSchedule;
        this.weSchedule              = weSchedule;
        this.thSchedule             = thSchedule;
        this.fchedule               = fchedule;
        this.saSchedule             = saSchedule;
        this.suSchedule             = suSchedule;
    }
    

    /**
     * @return the idSchedulePool
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_schedule_pool", unique = true, nullable = false)
    public Integer getIdSchedulePool() {
        return idSchedulePool;
    }

    /**
     * @param idSchedulePool the idSchedulePool to set
     */
    public void setIdSchedulePool(Integer idSchedulePool) {
        this.idSchedulePool = idSchedulePool;
    }

    /**
     * @return the perpose
     */
    @Column(name = "perpose", nullable = true, length = 45)
    public String getPerpose() {
        return perpose;
    }

    /**
     * @param perpose the perpose to set
     */
    public void setPerpose(String perpose) {
        this.perpose = perpose;
    }

    /**
     * @return the gender
     */
    @Column(name = "gender", nullable = true, length = 45)
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the level
     */
    @Column(name = "level", nullable = true, length = 45)
    public String getLevell() {
        return levell;
    }

    /**
     * @param levell the levell to set
     */
    public void setLevell(String levell) {
        this.levell = levell;
    }

    /**
     * @return the moSchedule
     */
    @Column(name = "m_schedule", nullable = true, length = 45)
    public String getMoSchedule() {
        return moSchedule;
    }

    /**
     * @param moSchedule the moSchedule to set
     */
    public void setMoSchedule(String moSchedule) {
        this.moSchedule = moSchedule;
    }

    /**
     * @return the tuSchedule
     */
    @Column(name = "tu_schedule", nullable = true, length = 45)
    public String getTuSchedule() {
        return tuSchedule;
    }

    /**
     * @param tuSchedule the tuSchedule to set
     */
    public void setTuSchedule(String tuSchedule) {
        this.tuSchedule = tuSchedule;
    }

    /**
     * @return the weSchedule
     */
    @Column(name = "w_schedule", nullable = true, length = 45)
    public String getWeSchedule() {
        return weSchedule;
    }

    /**
     * @param weSchedule the weSchedule to set
     */
    public void setWeSchedule(String weSchedule) {
        this.weSchedule = weSchedule;
    }

    /**
     * @return the thSchedule
     */
    @Column(name = "th_schedule", nullable = true, length = 45)
    public String getThSchedule() {
        return thSchedule;
    }

    /**
     * @param thSchedule the thSchedule to set
     */
    public void setThSchedule(String thSchedule) {
        this.thSchedule = thSchedule;
    }

    /**
     * @return the fchedule
     */
    @Column(name = "f_schedule", nullable = true, length = 45)
    public String getFchedule() {
        return fchedule;
    }

    /**
     * @param fchedule the fchedule to set
     */
    public void setFchedule(String fchedule) {
        this.fchedule = fchedule;
    }

    /**
     * @return the saSchedule
     */
    @Column(name = "sa_schedule", nullable = true, length = 45)
    public String getSaSchedule() {
        return saSchedule;
    }

    /**
     * @param saSchedule the saSchedule to set
     */
    public void setSaSchedule(String saSchedule) {
        this.saSchedule = saSchedule;
    }

    /**
     * @return the suSchedule
     */
    @Column(name = "su_schedule", nullable = true, length = 45)
    public String getSuSchedule() {
        return suSchedule;
    }

    /**
     * @param suSchedule the suSchedule to set
     */
    public void setSuSchedule(String suSchedule) {
        this.suSchedule = suSchedule;
    }

    /**
     * @return the users
     */
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "schedules")
    public Set<UserInfo> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(Set<UserInfo> users) {
        this.users = users;
    }

    /**
     * @return the recommendedExercises
     */
    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.ALL}, mappedBy = "schedule")
    public Set<RecommendedExercises> getRecommendedExercises() {
        return recommendedExercises;
    }

    /**
     * @param recommendedExercises the recommendedExercises to set
     */
    public void setRecommendedExercises(Set<RecommendedExercises> recommendedExercises) {
        this.recommendedExercises = recommendedExercises;
    }
    
    
    
}
