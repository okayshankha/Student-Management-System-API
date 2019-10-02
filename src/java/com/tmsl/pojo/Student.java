/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmsl.pojo;

import java.util.Objects;

/**
 *
 * @author Shankha
 */
public class Student {

    private String first_name;
    private String middle_name;
    private String last_name;
    private String email;

    private String mobile_number;
    private String sex;
    private String dob;

    /**
     * 10th information
     */
    private String _10_th_exam_name;
    private String _10_th_passing_year;
    private String _10_th_board;
    private String _10_th_school_name;
    private String _10_th_language_medium;
    private String _10_th_standard_marks;
    private String _10_th_actual_marks;
    private String _10_th_math_percentage;

    /**
     * 12th information
     */
    private String _12_th_exam_name;
    private String _12_th_passing_year;
    private String _12_th_board;
    private String _12_th_school_name;
    private String _12_th_language_medium;
    private String _12_th_standard_marks;
    private String _12_th_actual_marks;
    private String _12_th_math_percentage;

    /**
     * Diploma information
     */
    private String diploma_university;
    private String diploma_stream;
    private String diploma_passing_year;
    private String diploma_marks;

    /**
     * Graduation information
     */
    private String graduation_university;
    private String graduation_stream;
    private String graduation_passing_year;
    private String graduation_marks;

    /**
     * MCA information
     */
    private String mca_entrance_exam_name;
    private String mca_entrance_rank;
    private String mca_college_name;
    private String mca_university;
    private String mca_stream;
    private String mca_course_duration;
    private String mca_university_registration_no;
    private String mca_university_roll_no;
    private String mca_academic_session;

    /**
     * Parent information
     */
    private String fathers_name;
    private String fathers_occupation;
    private String mothers_name;
    private String mothers_occupation;
    private String gurdains_name;
    private String gurdains_occupation;
    private String gurdains_relation;

    /**
     * Residential information
     */
    private String present_address;
    private String permanent_address;
    private String present_state;
    private String permanent_state;
    private String present_city;
    private String permanent_city;
    private String present_district;
    private String permanent_district;
    private String present_pin_number;
    private String permanent_pin_number;
    private String present_post_office;
    private String permanent_post_office;
    private String physical_disability;
    private String academic_year_gap;
    private String session_of_year_gap;
    private String reason_of_year_gap;

    /**
     * Personal Information
     */
    private String blood_group;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String get10_th_exam_name() {
        return _10_th_exam_name;
    }

    public void set10_th_exam_name(String _10_th_exam_name) {
        this._10_th_exam_name = _10_th_exam_name;
    }

    public String get10_th_passing_year() {
        return _10_th_passing_year;
    }

    public void set10_th_passing_year(String _10_th_passing_year) {
        this._10_th_passing_year = _10_th_passing_year;
    }

    public String get10_th_board() {
        return _10_th_board;
    }

    public void set10_th_board(String _10_th_board) {
        this._10_th_board = _10_th_board;
    }

    public String get10_th_school_name() {
        return _10_th_school_name;
    }

    public void set10_th_school_name(String _10_th_school_name) {
        this._10_th_school_name = _10_th_school_name;
    }

    public String get10_th_language_medium() {
        return _10_th_language_medium;
    }

    public void set10_th_language_medium(String _10_th_language_medium) {
        this._10_th_language_medium = _10_th_language_medium;
    }

    public String get10_th_standard_marks() {
        return _10_th_standard_marks;
    }

    public void set10_th_standard_marks(String _10_th_standard_marks) {
        this._10_th_standard_marks = _10_th_standard_marks;
    }

    public String get10_th_actual_marks() {
        return _10_th_actual_marks;
    }

    public void set10_th_actual_marks(String _10_th_actual_marks) {
        this._10_th_actual_marks = _10_th_actual_marks;
    }

    public String get10_th_math_percentage() {
        return _10_th_math_percentage;
    }

    public void set10_th_math_percentage(String _10_th_math_percentage) {
        this._10_th_math_percentage = _10_th_math_percentage;
    }

    public String get12_th_exam_name() {
        return _12_th_exam_name;
    }

    public void set12_th_exam_name(String _12_th_exam_name) {
        this._12_th_exam_name = _12_th_exam_name;
    }

    public String get12_th_passing_year() {
        return _12_th_passing_year;
    }

    public void set12_th_passing_year(String _12_th_passing_year) {
        this._12_th_passing_year = _12_th_passing_year;
    }

    public String get12_th_board() {
        return _12_th_board;
    }

    public void set12_th_board(String _12_th_board) {
        this._12_th_board = _12_th_board;
    }

    public String get12_th_school_name() {
        return _12_th_school_name;
    }

    public void set12_th_school_name(String _12_th_school_name) {
        this._12_th_school_name = _12_th_school_name;
    }

    public String get12_th_language_medium() {
        return _12_th_language_medium;
    }

    public void set12_th_language_medium(String _12_th_language_medium) {
        this._12_th_language_medium = _12_th_language_medium;
    }

    public String get12_th_standard_marks() {
        return _12_th_standard_marks;
    }

    public void set12_th_standard_marks(String _12_th_standard_marks) {
        this._12_th_standard_marks = _12_th_standard_marks;
    }

    public String get12_th_actual_marks() {
        return _12_th_actual_marks;
    }

    public void set12_th_actual_marks(String _12_th_actual_marks) {
        this._12_th_actual_marks = _12_th_actual_marks;
    }

    public String get12_th_math_percentage() {
        return _12_th_math_percentage;
    }

    public void set12_th_math_percentage(String _12_th_math_percentage) {
        this._12_th_math_percentage = _12_th_math_percentage;
    }

    public String getDiploma_university() {
        return diploma_university;
    }

    public void setDiploma_university(String diploma_university) {
        this.diploma_university = diploma_university;
    }

    public String getDiploma_stream() {
        return diploma_stream;
    }

    public void setDiploma_stream(String diploma_stream) {
        this.diploma_stream = diploma_stream;
    }

    public String getDiploma_passing_year() {
        return diploma_passing_year;
    }

    public void setDiploma_passing_year(String diploma_passing_year) {
        this.diploma_passing_year = diploma_passing_year;
    }

    public String getDiploma_marks() {
        return diploma_marks;
    }

    public void setDiploma_marks(String diploma_marks) {
        this.diploma_marks = diploma_marks;
    }

    public String getGraduation_university() {
        return graduation_university;
    }

    public void setGraduation_university(String graduation_university) {
        this.graduation_university = graduation_university;
    }

    public String getGraduation_stream() {
        return graduation_stream;
    }

    public void setGraduation_stream(String graduation_stream) {
        this.graduation_stream = graduation_stream;
    }

    public String getGraduation_passing_year() {
        return graduation_passing_year;
    }

    public void setGraduation_passing_year(String graduation_passing_year) {
        this.graduation_passing_year = graduation_passing_year;
    }

    public String getGraduation_marks() {
        return graduation_marks;
    }

    public void setGraduation_marks(String graduation_marks) {
        this.graduation_marks = graduation_marks;
    }

    public String getMca_entrance_exam_name() {
        return mca_entrance_exam_name;
    }

    public void setMca_entrance_exam_name(String mca_entrance_exam_name) {
        this.mca_entrance_exam_name = mca_entrance_exam_name;
    }

    public String getMca_entrance_rank() {
        return mca_entrance_rank;
    }

    public void setMca_entrance_rank(String mca_entrance_rank) {
        this.mca_entrance_rank = mca_entrance_rank;
    }

    public String getMca_college_name() {
        return mca_college_name;
    }

    public void setMca_college_name(String mca_college_name) {
        this.mca_college_name = mca_college_name;
    }

    public String getMca_university() {
        return mca_university;
    }

    public void setMca_university(String mca_university) {
        this.mca_university = mca_university;
    }

    public String getMca_stream() {
        return mca_stream;
    }

    public void setMca_stream(String mca_stream) {
        this.mca_stream = mca_stream;
    }

    public String getMca_course_duration() {
        return mca_course_duration;
    }

    public void setMca_course_duration(String mca_course_duration) {
        this.mca_course_duration = mca_course_duration;
    }

    public String getMca_university_registration_no() {
        return mca_university_registration_no;
    }

    public void setMca_university_registration_no(String mca_university_registration_no) {
        this.mca_university_registration_no = mca_university_registration_no;
    }

    public String getMca_university_roll_no() {
        return mca_university_roll_no;
    }

    public void setMca_university_roll_no(String mca_university_roll_no) {
        this.mca_university_roll_no = mca_university_roll_no;
    }

    public String getMca_academic_session() {
        return mca_academic_session;
    }

    public void setMca_academic_session(String mca_academic_session) {
        this.mca_academic_session = mca_academic_session;
    }

    public String getFathers_name() {
        return fathers_name;
    }

    public void setFathers_name(String fathers_name) {
        this.fathers_name = fathers_name;
    }

    public String getFathers_occupation() {
        return fathers_occupation;
    }

    public void setFathers_occupation(String fathers_occupation) {
        this.fathers_occupation = fathers_occupation;
    }

    public String getMothers_name() {
        return mothers_name;
    }

    public void setMothers_name(String mothers_name) {
        this.mothers_name = mothers_name;
    }

    public String getMothers_occupation() {
        return mothers_occupation;
    }

    public void setMothers_occupation(String mothers_occupation) {
        this.mothers_occupation = mothers_occupation;
    }

    public String getGurdains_name() {
        return gurdains_name;
    }

    public void setGurdains_name(String gurdains_name) {
        this.gurdains_name = gurdains_name;
    }

    public String getGurdains_occupation() {
        return gurdains_occupation;
    }

    public void setGurdains_occupation(String gurdains_occupation) {
        this.gurdains_occupation = gurdains_occupation;
    }

    public String getGurdains_relation() {
        return gurdains_relation;
    }

    public void setGurdains_relation(String gurdains_relation) {
        this.gurdains_relation = gurdains_relation;
    }

    public String getPresent_address() {
        return present_address;
    }

    public void setPresent_address(String present_address) {
        this.present_address = present_address;
    }

    public String getPermanent_address() {
        return permanent_address;
    }

    public void setPermanent_address(String permanent_address) {
        this.permanent_address = permanent_address;
    }

    public String getPresent_state() {
        return present_state;
    }

    public void setPresent_state(String present_state) {
        this.present_state = present_state;
    }

    public String getPermanent_state() {
        return permanent_state;
    }

    public void setPermanent_state(String permanent_state) {
        this.permanent_state = permanent_state;
    }

    public String getPresent_city() {
        return present_city;
    }

    public void setPresent_city(String present_city) {
        this.present_city = present_city;
    }

    public String getPermanent_city() {
        return permanent_city;
    }

    public void setPermanent_city(String permanent_city) {
        this.permanent_city = permanent_city;
    }

    public String getPresent_district() {
        return present_district;
    }

    public void setPresent_district(String present_district) {
        this.present_district = present_district;
    }

    public String getPermanent_district() {
        return permanent_district;
    }

    public void setPermanent_district(String permanent_district) {
        this.permanent_district = permanent_district;
    }

    public String getPresent_pin_number() {
        return present_pin_number;
    }

    public void setPresent_pin_number(String present_pin_number) {
        this.present_pin_number = present_pin_number;
    }

    public String getPermanent_pin_number() {
        return permanent_pin_number;
    }

    public void setPermanent_pin_number(String permanent_pin_number) {
        this.permanent_pin_number = permanent_pin_number;
    }

    public String getPresent_post_office() {
        return present_post_office;
    }

    public void setPresent_post_office(String present_post_office) {
        this.present_post_office = present_post_office;
    }

    public String getPermanent_post_office() {
        return permanent_post_office;
    }

    public void setPermanent_post_office(String permanent_post_office) {
        this.permanent_post_office = permanent_post_office;
    }

    public String getPhysical_disability() {
        return physical_disability;
    }

    public void setPhysical_disability(String physical_disability) {
        this.physical_disability = physical_disability;
    }

    public String getAcademic_year_gap() {
        return academic_year_gap;
    }

    public void setAcademic_year_gap(String academic_year_gap) {
        this.academic_year_gap = academic_year_gap;
    }

    public String getSession_of_year_gap() {
        return session_of_year_gap;
    }

    public void setSession_of_year_gap(String session_of_year_gap) {
        this.session_of_year_gap = session_of_year_gap;
    }

    public String getReason_of_year_gap() {
        return reason_of_year_gap;
    }

    public void setReason_of_year_gap(String reason_of_year_gap) {
        this.reason_of_year_gap = reason_of_year_gap;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

}
