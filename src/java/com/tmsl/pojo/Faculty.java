/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmsl.pojo;

/**
 *
 * @author Shankha
 */
public class Faculty {

    private String username;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String email;
    private String password;
    private String mobile_number;
    private String faculty_type;

    /**
     *
     */
    public Faculty() {
        username = "";
        first_name = "";
        middle_name = "";
        last_name = "";
        email = "";
        password = "";
        mobile_number = "";
        faculty_type = "";
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     *
     * @param first_name
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     *
     * @return
     */
    public String getMiddle_name() {
        return middle_name;
    }

    /**
     *
     * @param middle_name
     */
    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    /**
     *
     * @return
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     *
     * @param last_name
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public String getMobile_number() {
        return mobile_number;
    }

    /**
     *
     * @param mobile_number
     */
    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    /**
     *
     * @return
     */
    public String getFaculty_type() {
        return faculty_type;
    }

    /**
     *
     * @param faculty_type
     */
    public void setFaculty_type(String faculty_type) {
        this.faculty_type = faculty_type;
    }

}
