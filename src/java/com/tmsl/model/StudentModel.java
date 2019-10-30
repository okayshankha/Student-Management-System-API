/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmsl.model;

import base.Model;
import com.tmsl.pojo.Student;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Shankha
 */
public class StudentModel extends Model{

    /**
     * Get All Student
     * @return
     * @throws SQLException
     */
    public ArrayList<Student> getAllStudent() throws SQLException {
        db.selectTable("student_master");
        db.select(new String[]{"*"});
        ResultSet rs = db.access();
        ArrayList<Student> list = compileStudentsDataFromResultSet(rs);
        return list;
    }

    /**
     * Get All Student With Filters
     * @param filters
     * @param values
     * @return
     * @throws SQLException
     */
    public ArrayList<Student> getAllStudent(String[] filters, String[] values) throws SQLException {
        db.selectTable("student_master");
        db.select(new String[]{"*"});
        String filterName = null;
        String filterValue = null;

        for (int i = 0; i < filters.length; i++) {
            filterName = filters[i];
            filterValue = values[i];
            switch (filterName) {
                case "mobile":
                    filterName = "mobile_number";
                    break;
                case "fname":
                    filterName = "first_name";
                    break;
                case "mname":
                    filterName = "middle_name";
                    break;
                case "lname":
                    filterName = "last_name";
                    break;
                case "roll":
                    filterName = "mca_university_roll_no";
                    break;
                case "reg":
                    filterName = "mca_university_registration_no";
                    break;
                case "passing_year":
                    filterName = "mca_course_duration";
                    filterValue = (Integer.parseInt(filterValue) - 3) + "-" + filterValue;
                    break;
            }

            db.where(filterName, filterValue);
        }

        ResultSet rs = db.access();

        ArrayList<Student> list = compileStudentsDataFromResultSet(rs);
        return list;
    }

    /**
     * Update Student Date
     * @param updateFields
     * @param values
     * @param roll
     * @return
     * @throws SQLException
     */
    public int update(String[] updateFields, String[] values, String roll) throws SQLException {
        Map<String, String> data = new HashMap<>();
        int cnt = 0;
        for(int i = 0; i < updateFields.length; i++){
            if(!values[i].trim().equals("")){
                data.put(updateFields[i], values[i]);
                cnt++;
            }
        }
        db.update("student_master", data);
        db.where("mca_university_roll_no", roll);
        db.access();
        return cnt;
    }
    
    /**
     * Update/Upload Student Image
     * @param roll
     * @param imagePath
     * @throws SQLException
     */
    public void updateStudentImage(String roll, String imagePath) throws SQLException{
        Map<String, String> data = new HashMap<>();
        data.put("image", imagePath);
        db.update("student_master", data);
        db.where("mca_university_roll_no", roll);
        db.access();
    }
}
