/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmsl.model;

import base.Controller;
import base.Model;
import com.google.gson.Gson;
import com.tmsl.pojo.Faculty;
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
public class AdminModel extends Model {

    /**
     *
     * Faculty Section
     */
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
        switch (filterType) {
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
            if (Integer.parseInt(rs.getString("COUNT")) == 1) {
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
            if (Integer.parseInt(rs.getString("COUNT")) == 0) {
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
            cnt = Integer.parseInt(rs.getString("COUNT"));
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
            cnt = Integer.parseInt(rs.getString("COUNT"));
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
            cnt = Integer.parseInt(rs.getString("COUNT"));
            if (cnt > 0) {
                return "username";
            }
        }

        return null;
    }

    /**
     * Student Section
     */
    private ArrayList<Student> compileStudentsDataFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<Student> list = new ArrayList<Student>();
        while (rs.next()) {
            Student student = new Student();
            student.setFirst_name(rs.getString("first_name"));
            student.setMiddle_name(rs.getString("middle_name"));
            student.setLast_name(rs.getString("last_name"));
            student.setMobile_number(rs.getString("mobile_number"));
            student.setEmail(rs.getString("email"));
            student.setSex(rs.getString("sex"));
            student.setDob(rs.getString("dob"));

            student.set10_th_exam_name(rs.getString("10_th_exam_name"));
            student.set10_th_passing_year(rs.getString("10_th_passing_year"));
            student.set10_th_board(rs.getString("10_th_board"));
            student.set10_th_school_name(rs.getString("10_th_school_name"));
            student.set10_th_language_medium(rs.getString("10_th_language_medium"));
            student.set10_th_standard_marks(rs.getString("10_th_standard_marks"));
            student.set10_th_actual_marks(rs.getString("10_th_actual_marks"));
            student.set10_th_math_percentage(rs.getString("10_th_math_percentage"));

            student.set12_th_exam_name(rs.getString("12_th_exam_name"));
            student.set12_th_passing_year(rs.getString("12_th_passing_year"));
            student.set12_th_board(rs.getString("12_th_board"));
            student.set12_th_school_name(rs.getString("12_th_school_name"));
            student.set12_th_language_medium(rs.getString("12_th_language_medium"));
            student.set12_th_standard_marks(rs.getString("12_th_standard_marks"));
            student.set12_th_actual_marks(rs.getString("12_th_actual_marks"));
            student.set12_th_math_percentage(rs.getString("12_th_math_percentage"));

            student.setDiploma_university(rs.getString("diploma_university"));
            student.setDiploma_stream(rs.getString("diploma_stream"));
            student.setDiploma_passing_year(rs.getString("diploma_passing_year"));
            student.setDiploma_marks(rs.getString("diploma_marks"));

            student.setGraduation_university(rs.getString("graduation_university"));
            student.setGraduation_stream(rs.getString("graduation_stream"));
            student.setGraduation_passing_year(rs.getString("graduation_passing_year"));
            student.setGraduation_marks(rs.getString("graduation_marks"));

            student.setMca_entrance_exam_name(rs.getString("mca_entrance_exam_name"));
            student.setMca_entrance_rank(rs.getString("mca_entrance_rank"));
            student.setMca_college_name(rs.getString("mca_college_name"));
            student.setMca_university(rs.getString("mca_university"));
            student.setMca_stream(rs.getString("mca_stream"));
            student.setMca_course_duration(rs.getString("mca_course_duration"));

            student.setMca_university_registration_no(rs.getString("mca_university_registration_no"));
            student.setMca_university_roll_no(rs.getString("mca_university_roll_no"));

            student.setMca_academic_session(rs.getString("mca_academic_session"));

            student.setFathers_name(rs.getString("fathers_name"));
            student.setFathers_occupation(rs.getString("fathers_occupation"));
            student.setMothers_name(rs.getString("mothers_name"));
            student.setMothers_occupation(rs.getString("mothers_occupation"));
            student.setGurdains_name(rs.getString("gurdains_name"));
            student.setGurdains_occupation(rs.getString("gurdains_occupation"));
            student.setGurdains_relation(rs.getString("gurdains_relation"));

            student.setPresent_address(rs.getString("present_address"));
            student.setPermanent_address(rs.getString("permanent_address"));
            student.setPresent_state(rs.getString("present_state"));
            student.setPermanent_state(rs.getString("permanent_state"));

            student.setPresent_city(rs.getString("present_city"));
            student.setPermanent_city(rs.getString("permanent_city"));
            student.setPresent_district(rs.getString("present_district"));
            student.setPermanent_district(rs.getString("permanent_district"));
            student.setPresent_pin_number(rs.getString("present_pin_number"));
            student.setPermanent_pin_number(rs.getString("permanent_pin_number"));
            student.setPresent_post_office(rs.getString("present_post_office"));
            student.setPermanent_post_office(rs.getString("permanent_post_office"));

            student.setPhysical_disability(rs.getString("physical_disability"));
            student.setAcademic_year_gap(rs.getString("academic_year_gap"));
            student.setSession_of_year_gap(rs.getString("session_of_year_gap"));
            student.setReason_of_year_gap(rs.getString("reason_of_year_gap"));
            student.setBlood_group(rs.getString("blood_group"));

            list.add(student);
        }
        return list;
    }

    public ArrayList<Student> getAllStudent() throws SQLException {
        db.selectTable("student_master");
        db.select(new String[]{"*"});
        ResultSet rs = db.access();
        ArrayList<Student> list = compileStudentsDataFromResultSet(rs);
        return list;
    }

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

    public boolean addStudent(Student student) throws SQLException {
        Map<String, String> data = new HashMap<String, String>();
        boolean needToUpdateData = existsStudentEmail(student.getEmail());

        data.put("first_name", (String) student.getFirst_name());
        data.put("middle_name", (String) student.getMiddle_name());
        data.put("last_name", (String) student.getLast_name());
        data.put("mobile_number", (String) student.getMobile_number());
        data.put("email", (String) student.getEmail());
        data.put("sex", (String) student.getSex());
        data.put("dob", (String) student.getDob());

        data.put("10_th_exam_name", (String) student.get10_th_exam_name());
        data.put("10_th_passing_year", (String) student.get10_th_passing_year());
        data.put("10_th_board", (String) student.get10_th_board());
        data.put("10_th_school_name", (String) student.get10_th_school_name());
        data.put("10_th_language_medium", (String) student.get10_th_language_medium());
        data.put("10_th_standard_marks", (String) student.get10_th_standard_marks());
        data.put("10_th_actual_marks", (String) student.get10_th_actual_marks());
        data.put("10_th_math_percentage", (String) student.get10_th_math_percentage());

        data.put("12_th_exam_name", (String) student.get12_th_exam_name());
        data.put("12_th_passing_year", (String) student.get12_th_passing_year());
        data.put("12_th_board", (String) student.get12_th_board());
        data.put("12_th_school_name", (String) student.get12_th_school_name());
        data.put("12_th_language_medium", (String) student.get12_th_language_medium());
        data.put("12_th_standard_marks", (String) student.get12_th_standard_marks());
        data.put("12_th_actual_marks", (String) student.get12_th_actual_marks());
        data.put("12_th_math_percentage", (String) student.get12_th_math_percentage());

        data.put("diploma_university", (String) student.getDiploma_university());
        data.put("diploma_stream", (String) student.getDiploma_stream());
        data.put("diploma_passing_year", (String) student.getDiploma_passing_year());
        data.put("diploma_marks", (String) student.getDiploma_marks());

        data.put("graduation_university", (String) student.getGraduation_university());
        data.put("graduation_stream", (String) student.getGraduation_stream());
        data.put("graduation_passing_year", (String) student.getGraduation_passing_year());
        data.put("graduation_marks", (String) student.getGraduation_marks());

        data.put("mca_entrance_exam_name", (String) student.getMca_entrance_exam_name());
        data.put("mca_entrance_rank", (String) student.getMca_entrance_rank());
        data.put("mca_college_name", (String) student.getMca_college_name());
        data.put("mca_university", (String) student.getMca_university());
        data.put("mca_stream", (String) student.getMca_stream());
        data.put("mca_course_duration", (String) student.getMca_course_duration());

        if (!needToUpdateData) {
            data.put("mca_university_registration_no", generateRoll());
            data.put("mca_university_roll_no", generateRoll());
        } else {
            data.put("mca_university_registration_no", getRegByEmail(student.getEmail()));
            data.put("mca_university_roll_no", getRollByEmail(student.getEmail()));
        }

        data.put("mca_academic_session", (String) student.getMca_academic_session());

        data.put("fathers_name", (String) student.getFathers_name());
        data.put("fathers_occupation", (String) student.getFathers_occupation());
        data.put("mothers_name", (String) student.getMothers_name());
        data.put("mothers_occupation", (String) student.getMothers_occupation());
        data.put("gurdains_name", (String) student.getGurdains_name());
        data.put("gurdains_occupation", (String) student.getGurdains_occupation());
        data.put("gurdains_relation", (String) student.getGurdains_relation());

        data.put("present_address", (String) student.getPresent_address());
        data.put("permanent_address", (String) student.getPermanent_address());
        data.put("present_state", (String) student.getPresent_state());
        data.put("permanent_state", (String) student.getPermanent_state());

        data.put("present_city", (String) student.getPresent_city());
        data.put("permanent_city", (String) student.getPermanent_city());
        data.put("present_district", (String) student.getPresent_district());
        data.put("permanent_district", (String) student.getPermanent_district());
        data.put("present_pin_number", (String) student.getPresent_pin_number());
        data.put("permanent_pin_number", (String) student.getPermanent_pin_number());
        data.put("present_post_office", (String) student.getPresent_post_office());
        data.put("permanent_post_office", (String) student.getPermanent_post_office());

        data.put("physical_disability", (String) student.getPhysical_disability());
        data.put("academic_year_gap", (String) student.getAcademic_year_gap());
        data.put("session_of_year_gap", (String) student.getSession_of_year_gap());
        data.put("reason_of_year_gap", (String) student.getReason_of_year_gap());
        data.put("blood_group", (String) student.getBlood_group());

        System.out.println("AdminModel ready");
        

        //System.exit(0);
        if (needToUpdateData) {
            db.update("student_master", data);
            db.where("email", (String) student.getEmail());
            System.out.println("update setup");
        } else {
            
            db.insert("student_master", data);
            System.out.println("insert setup");
        }

        System.out.println(db.getQueryString());
        db.access();
        
        if (!needToUpdateData) setStudentSemester(data.get("mca_university_roll_no"), "1");

        db.selectTable("student_master");
        db.select_count("id");
        db.where("email", (String) student.getEmail());
        ResultSet rs = db.access();

        if (rs.next()) {
            if (Integer.parseInt(rs.getString("COUNT")) == 1) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public String getStudentSemester(String roll) throws SQLException {
        String semNum = "-1";

        db.selectTable("student_semester_map");
        db.select(new String[]{"mca_university_roll_no"});
        db.where("semester", roll);
        ResultSet rs = db.access();

        if (rs.next()) {
            semNum = rs.getString("mca_university_roll_no");
        }

        return semNum;
    }

    public boolean setStudentSemester(String roll, String sem) throws SQLException {

        String _sem = getStudentSemester(roll);
        if (_sem.equals("-1")) {
            db.selectTable("student_mester");
            db.select_count("id");
            db.where("mca_university_roll_no", roll);
            ResultSet rs = db.access();

            if (rs.next()) {
                if (Integer.parseInt(rs.getString("COUNT")) > 0) {
                    Map<String, String> data = new HashMap<>();
                    data.put("mca_university_roll_no", roll);
                    data.put("semester", sem);
                    db.insert("student_semester_map", data);
                    db.access();
                    return true;
                }
            }
            return false;

        } else {
            Map<String, String> data = new HashMap<>();
            data.put("semester", sem);
            db.update("student_semester_map", data);
            db.where("mca_university_roll_no", roll);
            db.access();
            return true;
        }

    }

}
