/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmsl.model;

import base.Model;
import com.google.gson.Gson;
import com.tmsl.pojo.Faculty;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Shankha
 */
public class AdminModel extends Model {

    public Faculty getFacultyByMobile(String mob) throws SQLException {
        Faculty faculty = null;

        if (mob.trim().equals("")) {
            return faculty;
        }

        db.selectTable("faculty_master");
        db.select(new String[]{"*"});
        db.where("mobile_number", mob);

        ResultSet rs = db.access();

        if (rs.next()) {
            Faculty tempFaculty = new Faculty();
            tempFaculty.setEmail(rs.getString("email"));
            tempFaculty.setFirst_name(rs.getString("first_name"));
            tempFaculty.setLast_name(rs.getString("last_name"));
            tempFaculty.setMiddle_name(rs.getString("middle_name"));
            tempFaculty.setMobile_number(rs.getString("mobile_number"));
            tempFaculty.setPassword("****");
            tempFaculty.setFaculty_type(rs.getString("faculty_type"));

            faculty = tempFaculty;
        }

        return faculty;
    }

    public Faculty getFacultyByEmail(String email) throws SQLException {
        Faculty faculty = null;
        
        if (email.trim().equals("")) {
            return faculty;
        }

        db.selectTable("faculty_master");
        db.select(new String[]{"*"});
        db.where("email", email);

        ResultSet rs = db.access();
        Faculty tempFaculty = new Faculty();
        if (rs.next()) {
            tempFaculty.setEmail(rs.getString("email"));
            tempFaculty.setFirst_name(rs.getString("first_name"));
            tempFaculty.setLast_name(rs.getString("last_name"));
            tempFaculty.setMiddle_name(rs.getString("middle_name"));
            tempFaculty.setMobile_number(rs.getString("mobile_number"));
            tempFaculty.setPassword("****");
            tempFaculty.setFaculty_type(rs.getString("faculty_type"));

            faculty = tempFaculty;
        }
        return faculty;
    }
    
        public ArrayList<Faculty> getAllFacultyByType(String type, String filterType, String filterValue) throws SQLException {
        ArrayList<Faculty> all_faculties = new ArrayList<Faculty>();

        if (type.equals("hod")) {
            type = "2";
        }
        if (type.equals("coordinator")) {
            type = "3";
        }
        if (type.equals("teacher")) {
            type = "4";
        }
        if (type.trim().equals("")) {
            return getAllFaculty();
        }

        db.selectTable("faculty_master");
        db.select(new String[]{"*"});
        db.where("faculty_type", type);
        switch(filterType){
            case "email":
                db.where("email", filterValue);
                break;
            case "pass":
                db.where("psssword", filterValue);
                break;    
            default:
                return getAllFacultyByType(type);
        }

        ResultSet rs = db.access();

        while (rs.next()) {
            Faculty tempFaculty = new Faculty();
            tempFaculty.setEmail(rs.getString("email"));
            //tempFaculty.setUsername(rs.getString("username"));
            tempFaculty.setUsername("hidden");
            tempFaculty.setFirst_name(rs.getString("first_name"));
            tempFaculty.setLast_name(rs.getString("last_name"));
            tempFaculty.setMiddle_name(rs.getString("middle_name"));
            tempFaculty.setMobile_number(rs.getString("mobile_number"));
            tempFaculty.setPassword("hidden");
            tempFaculty.setFaculty_type(rs.getString("faculty_type"));

            all_faculties.add(tempFaculty);
        }

        return all_faculties;
    }
    
    

    public ArrayList<Faculty> getAllFacultyByType(String type) throws SQLException {
        ArrayList<Faculty> all_faculties = new ArrayList<Faculty>();

        if (type.equals("hod")) {
            type = "2";
        }
        if (type.equals("coordinator")) {
            type = "3";
        }
        if (type.equals("teacher")) {
            type = "4";
        }
        if (type.trim().equals("")) {
            return getAllFaculty();
        }

        db.selectTable("faculty_master");
        db.select(new String[]{"*"});
        db.where("faculty_type", type);

        ResultSet rs = db.access();

        while (rs.next()) {
            Faculty tempFaculty = new Faculty();
            tempFaculty.setEmail(rs.getString("email"));
            //tempFaculty.setUsername(rs.getString("username"));
            tempFaculty.setUsername("hidden");
            tempFaculty.setFirst_name(rs.getString("first_name"));
            tempFaculty.setLast_name(rs.getString("last_name"));
            tempFaculty.setMiddle_name(rs.getString("middle_name"));
            tempFaculty.setMobile_number(rs.getString("mobile_number"));
            tempFaculty.setPassword("hidden");
            tempFaculty.setFaculty_type(rs.getString("faculty_type"));

            all_faculties.add(tempFaculty);
        }

        return all_faculties;
    }

    public ArrayList<Faculty> getAllFaculty() throws SQLException {
        ArrayList<Faculty> all_faculties = new ArrayList<Faculty>();

        db.selectTable("faculty_master");
        db.select(new String[]{"*"});
        db.where("faculty_type", "> 1");

        ResultSet rs = db.access();

        while (rs.next()) {
            Faculty tempFaculty = new Faculty();
            tempFaculty.setEmail(rs.getString("email"));
            tempFaculty.setFirst_name(rs.getString("first_name"));
            tempFaculty.setLast_name(rs.getString("last_name"));
            tempFaculty.setMiddle_name(rs.getString("middle_name"));
            tempFaculty.setMobile_number(rs.getString("mobile_number"));
            tempFaculty.setFaculty_type(rs.getString("faculty_type"));

            all_faculties.add(tempFaculty);
        }

        return all_faculties;
    }

    public boolean addFaculty(Faculty faculty) throws SQLException {
        Map<String, String> data = new HashMap<String, String>();

        data.put("username", (String) faculty.getUsername());
        data.put("email", (String) faculty.getEmail());
        data.put("first_name", (String) faculty.getFirst_name());
        data.put("middle_name", (String) faculty.getMiddle_name());
        data.put("last_name", (String) faculty.getLast_name());
        data.put("password", (String) faculty.getPassword());
        data.put("mobile_number", (String) faculty.getMobile_number());
        data.put("faculty_type", (String) faculty.getFaculty_type());

        //db.selectTable();
        db.insert("faculty_master", data);
        db.access();

        db.selectTable("faculty_master");
        db.select_count("id");
        db.where("email", (String) faculty.getEmail());
        ResultSet rs = db.access();

        if (rs.next()) {
            if (Integer.parseInt(rs.getString("COUNT(`id`)")) == 1) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean deleteFaculty(Faculty faculty) throws SQLException {
        db.selectTable("faculty_master");
        db.where("email", faculty.getEmail().trim());
        db.delete();
        db.access();

        db.selectTable("faculty_master");
        db.where("email", faculty.getEmail().trim());
        db.select_count("id");
        ResultSet rs = db.access();

        if (rs.next()) {
            if (Integer.parseInt(rs.getString("COUNT(`id`)")) == 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    public String existsFaculty(Faculty faculty) throws SQLException {
        int cnt = 0;
        if (faculty.getEmail().trim().equals("")) {
            //return "email";
        }

        /**
         * Check for same email
         */
        db.selectTable("faculty_master");
        db.select_count("id");
        db.where("email", faculty.getEmail().trim());
        ResultSet rs = db.access();
        if (rs.next()) {
            cnt = Integer.parseInt(rs.getString("COUNT(`id`)"));
            if (cnt > 0) {
                return "email";
            }
        }

        /**
         * Check for same mobile number
         */
        db.selectTable("faculty_master");
        db.select_count("id");
        db.where("mobile_number", faculty.getMobile_number().trim());
        rs = db.access();
        cnt = 0;
        if (rs.next()) {
            cnt = Integer.parseInt(rs.getString("COUNT(`id`)"));
            if (cnt > 0) {
                return "mobile_number";
            }
        }

        /**
         * Check for same username
         */
        db.selectTable("faculty_master");
        db.select_count("id");
        db.where("username", faculty.getUsername().trim());
        rs = db.access();
        cnt = 0;
        if (rs.next()) {
            cnt = Integer.parseInt(rs.getString("COUNT(`id`)"));
            if (cnt > 0) {
                return "username";
            }
        }

        return null;
    }

}
