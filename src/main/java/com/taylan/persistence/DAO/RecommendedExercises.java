package com.taylan.persistence.DAO;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author Taylan Kurt   <taylankurt34@gmail.com>
 *          Recommended Exercises Data Annotation  - POJO class
 */

@NamedQueries({
        @NamedQuery(
                name = "getRecommenderExercises",
                query = "from RecommendedExercises rec where rec.id = :id"
        )
})
@Entity
@Table(name="recommended_exercises",catalog="sportclubsystem")
public class RecommendedExercises implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String chest;
    private String back;
    private String shoulder;
    private String arms;
    private String abs;
    private String leg;
    private String levell;
    private String purpose;
    private String gender;
    
    
    private SchedulePool schedule;
    
    

    public RecommendedExercises(){}
    
    public RecommendedExercises(String chest,String back
            ,String shoulder
            ,String arms,String abs,String leg,String levell,String purpose
            ,String gender){ 
        this.chest      = chest;
        this.back       = back;
        this.shoulder   = shoulder;
        this.arms       = arms;
        this.abs        = abs;
        this.leg        = leg;
        this.levell     = levell;
        this.purpose    = purpose;
        this.gender     = gender;
    }
    
    
    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idrecommended_exercises", unique = true, nullable = false)
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
     * @return the chest
     */
    @Column(name="chest", nullable=true, length=45)
    public String getChest() {
        return chest;
    }

    /**
     * @param chest the chest to set
     */
    public void setChest(String chest) {
        this.chest = chest;
    }

    /**
     * @return the back
     */
    @Column(name="back", nullable=true, length=45)
    public String getBack() {
        return back;
    }

    /**
     * @param back the back to set
     */
    public void setBack(String back) {
        this.back = back;
    }

    /**
     * @return the shoulder
     */
    @Column(name="shoulder", nullable=true, length=45)
    public String getShoulder() {
        return shoulder;
    }

    /**
     * @param shoulder the shoulder to set
     */
    public void setShoulder(String shoulder) {
        this.shoulder = shoulder;
    }

    /**
     * @return the arms
     */
    @Column(name="arms", nullable=true, length=45)
    public String getArms() {
        return arms;
    }

    /**
     * @param arms the arms to set
     */
    public void setArms(String arms) {
        this.arms = arms;
    }

    /**
     * @return the abs
     */
    @Column(name="abs",nullable=true,length=45)
    public String getAbs() {
        return abs;
    }

    /**
     * @param abs the abs to set
     */
    public void setAbs(String abs) {
        this.abs = abs;
    }

    /**
     * @return the leg
     */
    @Column(name="leg", nullable=true, length=45)
    public String getLeg() {
        return leg;
    }

    /**
     * @param leg the leg to set
     */
    public void setLeg(String leg) {
        this.leg = leg;
    }

    /**
     * @return the levell
     */
    @Column(name="levell", nullable=true, length=45)
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
     * @return the purpose
     */
    @Column(name="purpose", nullable=true, length=45)
    public String getPurpose() {
        return purpose;
    }

    /**
     * @param purpose the purpose to set
     */
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    /**
     * @return the gender
     */
    @Column(name="gender", nullable=true, length=45)
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
     * @return the schedule
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_schedule_pool", nullable = false)
    public SchedulePool getSchedule() {
        return schedule;
    }
    
    

    /**
     * @param schedule the schedule to set
     */
    public void setSchedule(SchedulePool schedule) {
        this.schedule = schedule;
    }
    
                
    
    /**
     * @return the schedulesRecommend
     */
    
    
}
