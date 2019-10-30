/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmsl.model;

import base.Model;
import com.tmsl.pojo.Faculty;
import com.tmsl.pojo.Student;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Shankha
 *
 */
public class AuthModel extends Model {

    /**
     * Validate Student by Semi-Complete Student Object
     * @param student
     * @return
     * @throws SQLException
     */
    public Student validateStudent(Student student) throws SQLException {
        Student output_student = new Student();
        db.joinTables(new String[]{"student_master","student_password_map"});
        db.select(new String[]{"A.mca_university_roll_no"});
        db.where("A.email", "B.email");
        
        db.where("A.email", student.getEmail());
        db.where("B.password", student.getPassword());
        
        //System.out.println(db.getQueryString());
        
        //return null;
        ResultSet rs = db.access();
        
        if(rs.next()){
            output_student.setMca_university_roll_no(rs.getString("mca_university_roll_no"));
        }else{
            return null;
        }
        
        return output_student;
    }

    /**
     * Validate faculty by Semi-Complete faculty Object
     * @param faculty
     * @return
     * @throws SQLException
     */
    public Faculty validateFaculty(Faculty faculty) throws SQLException {
        Faculty output_faculty = new Faculty();

        db.selectTable("faculty_master");
        db.select(new String[]{"*"});

        if (faculty.getPassword().trim() != "") {
            db.where("password", faculty.getPassword().trim());
            if (faculty != null && faculty.getEmail().trim() != "") {
                db.where("email", faculty.getEmail().trim());
            } else if (faculty != null && faculty.getUsername().trim() != "") {
                db.where("username", faculty.getUsername().trim());
            }
        } else {
            return null;
        }

        ResultSet rs = db.access();
        if (rs.next()) {
            output_faculty.setUsername(rs.getString("username").trim());
            output_faculty.setFirst_name(rs.getString("first_name").trim());
            if (rs.getString("middle_name") != null) {
                output_faculty.setMiddle_name(rs.getString("middle_name").trim());
            }
            if (rs.getString("last_name") != null) {
                output_faculty.setLast_name(rs.getString("last_name").trim());
            }
            output_faculty.setEmail(rs.getString("email").trim());
            output_faculty.setPassword(rs.getString("password").trim());
            if (rs.getString("mobile_number") != null) {
                output_faculty.setMobile_number(rs.getString("mobile_number").trim());
            }
            output_faculty.setFaculty_type(rs.getString("faculty_type").trim());
        } else {
            return null;
        }

        return output_faculty;
    }
}
