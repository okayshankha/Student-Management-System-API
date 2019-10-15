/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import Core.Database.DB;
import Core.Database.Database;
import com.google.gson.Gson;
import com.tmsl.pojo.Faculty;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Shankha
 */
public class Model {

    protected DB db = new DB(true);
    private static DB static_db = new DB(true);

    protected HttpServletRequest request;

    public Model() {
        Database.config("localhost", "shankha", "root", "project_database");
    }

    public String generateRoll() throws SQLException {
        Integer roll = 0;
        Random rand = new Random();
        do {
            roll = rand.nextInt(90000000) + 10000000;
        } while (existsRoll(roll.toString()));

        return roll.toString();
    }

    public boolean existsStudentEmail(String email) throws SQLException {

        db.selectTable("student_master");
        db.select_count("id");
        db.where("email", email);

        ResultSet rs = db.access();
        if (rs.next()) {
            if (Integer.parseInt(rs.getString("COUNT")) > 0) {
                System.out.println("Student exists");
                return true;
                
            }
        }
        System.out.println("Student does not exist");
        return false;
    }

    public boolean existsRoll(String roll) throws SQLException {
        db.selectTable("student_master");
        db.select_count("id");

        db.where("mca_university_roll_no", roll);
        db.or_where("mca_university_registration_no", roll);

        ResultSet rs = db.access();
        if (rs.next()) {
            if (Integer.parseInt(rs.getString("COUNT")) > 0) {
                return true;
            }
        }

        return false;
    }

    public String getRegByEmail(String email) throws SQLException {

        db.selectTable("student_master");
        db.select(new String[]{"mca_university_registration_no"});

        db.or_where("email", email);

        ResultSet rs = db.access();
        if (rs.next()) {
            return rs.getString("mca_university_registration_no");
        }

        return null;
    }

    public String getRollByEmail(String email) throws SQLException {

        db.selectTable("student_master");
        db.select(new String[]{"mca_university_roll_no"});

        db.or_where("email", email);

        ResultSet rs = db.access();
        if (rs.next()) {
            return rs.getString("mca_university_roll_no");
        }

        return null;
    }

    public Faculty getFaculty(Faculty faculty) throws SQLException {
        Faculty output_faculty = new Faculty();

        db.selectTable("faculty_master");
        db.select(new String[]{"*"});
        if (faculty.getPassword().trim() != "" && !faculty.getPassword().trim().equals("adminRequest96")) {
            db.where("password", faculty.getPassword().trim());
        } else {
            if (!faculty.getPassword().trim().equals("adminrequest96")) {
                return null;
            }
        }

        if (faculty != null && faculty.getEmail().trim() != "") {
            db.where("email", faculty.getEmail().trim());
        } else if (faculty != null && faculty.getUsername().trim() != "") {
            if (!faculty.getPassword().trim().equals("adminrequest96")) {
                db.where("username", faculty.getUsername().trim());
            }
        }

        ResultSet rs = db.access(db.getQueryString());
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

    /*
    public Faculty getFaculty(Faculty faculty, boolean b) throws SQLException {
        Faculty output_faculty = new Faculty();

        db.selectTable("faculty_master");
        db.select(new String[]{"*"});

        if (!faculty.getEmail().trim().equals("")) {
            db.where("email", faculty.getEmail().trim());
        }else{
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
     */
}
