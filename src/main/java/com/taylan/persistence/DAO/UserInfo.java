package com.taylan.persistence.DAO;



/**
 * @author Taylan Kurt   <taylankurt34@gmail.com>
 *          User Info Data Annotation
 */

public class UserInfo {
    
    private String name;
    private String surName;
    private String address;
    private String email;
    private int age;
    private String gender;
    
    public UserInfo(String name, String surName, String address, String email,
                    int age,String gender){
        this.name = name;
        this.surName = surName;
        this.address = address;
        this.age = age;
        this.gender = gender;
    }
    
    public UserInfo(){
    
    }
    

    /**
     * @return the name
     */ 
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
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    
}
