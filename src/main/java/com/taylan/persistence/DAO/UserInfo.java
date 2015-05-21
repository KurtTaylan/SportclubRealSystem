package com.taylan.persistence.DAO;

import com.taylan.persistence.absract.PersonAbstract;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * @author Taylan Kurt   <taylankurt34@gmail.com>
 *          User Info Data Annotation  - POJO class
 */
@NamedQueries({
	@NamedQuery(
	name = "getUserInformation",
	query = "from UserInfo u where u.id =" + " :id"
        ),
    @NamedQuery(
	name = "securityCheck",
	query = "select usernamee,passwordd from UserInfo u where u.id = :id"
        )
})
@Entity
@Table(name = "user_info", catalog = "sportclubsystem")
public class UserInfo extends PersonAbstract implements java.io.Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String name;
    private String surName;
    private String address;
    private String email;
    private String contact;
    private String age;
    private String gender;
    private String usernamee;
    private String passwordd;
    private UserSchedule schedule;
    
    public UserInfo(String name, String surName, String address, String email,
                    String age,String gender,String contact){
        this.name           = name;
        this.surName        = surName;
        this.address        = address;
        this.age            = age;
        this.gender         = gender;
        this.contact        = contact;
    }
    
    public UserInfo(){ }
    

    /**
     * @return the name
     */ 
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the surName
     */
    @Column(name = "surname", nullable = false, length = 45)
    public String getSurName() {
        return surName;
    }

    /**
     * @param surName the surName to set
     */
    public void setSurName(String surName) {
        this.surName = surName;
    }

    /**
     * @return the address
     */
    @Column(name = "address", nullable = false, length = 45)
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the email
     */
    @Column(name = "email", nullable = false, length = 45)
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the age
     */
    @Column(name = "age", nullable = false, length = 2)
    public String getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * @return the gender
     */
    @Column(name = "gender", nullable = false, length = 45)
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    @Override                   // Overriding from PersonAbstract class ...
    public String takeRecord(){
    
    
    return "Name:"+getName()+" Surname:"+getSurName()+" Age:"+getAge()+
            " Gender:"+getGender()+" Email:"+getEmail()+" Contact:"+getContact();
    }

    /**
     * @return the contact
     */
    @Column(name = "contact", nullable = false, length = 45)
    public String getContact() {
        return contact;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_user", unique = true, nullable = false)
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
     * @return the username
     */
    @Column(name = "username", nullable = false, length = 45)
    public String getUsernamee() {
        return usernamee;
    }

    /**
     * @param usernamee the usernamee to set
     */
    public void setUsernamee(String usernamee) {
        this.usernamee = usernamee;
    }

    /**
     * @return the password
     */
    @Column(name = "password", nullable = false, length = 45)
    public String getPasswordd() {
        return passwordd;
    }

    /**
     * @param passwordd the passwordd to set
     */
    public void setPasswordd(String passwordd) {
        this.passwordd = passwordd;
    }

    /**
     * @return the schedule
     */
    @OneToOne(fetch = FetchType.LAZY,mappedBy = "userInfo")
    public UserSchedule getSchedule() {
        return schedule;
    }

    /**
     * @param schedule the schedule to set
     */
    public void setSchedule(UserSchedule schedule) {
        this.schedule = schedule;
    }
}
