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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Shankha
 *
 */
public class HodModel extends Model {

    private ArrayList<Faculty> getAllFaculty() throws SQLException {
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
    
    public boolean assignFacultyToSubject(String facultyID, String subjectID) throws SQLException {
        return super.assignFacultyToSubject(facultyID, subjectID);
    }

    public boolean unassignFacultyToSubject(String facultyID, String subjectID) throws SQLException {
        return super.unassignFacultyToSubject(facultyID, subjectID);
    }

    /**
     Student Section 
     */
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

    /**
     *
     * @param roll
     * @return
     * @throws SQLException Returns Student semester number. Returns -1 if
     * student data not found. Returns 7 for passed out students.
     */
    public String getStudentSemester(String roll) throws SQLException {
        String semNum = "-1";

        db.selectTable("student_semester_map");
        db.select(new String[]{"semester"});
        db.where("mca_university_roll_no", roll);
        System.out.println(db.getQueryString());
        ResultSet rs = db.access();

        if (rs.next()) {
            semNum = rs.getString("semester");
        } else {
            System.out.println("Non-existing roll number!");
        }

        return semNum;
    }

    public boolean setStudentMarks(String roll, String subjectCode, String marks, String year) throws SQLException {
        subjectCode = subjectCode.toUpperCase();
        if (!getStudentSemester(roll).equals(getSubjectSemester(subjectCode))) {
            System.out.println();
            System.out.println("-------------------------------------------------------------------");
            System.out.println("| Subject doesn't belong to the semester the student enrolled in. |");
            System.out.println("-------------------------------------------------------------------");
            return false;
        }
        String subjectID = getSubjectID(subjectCode);
        ResultSet rs;
        db.selectTable("student_marks_map");
        db.select_count("id");
        db.where("mca_university_roll_no", roll);
        db.where("subject_id", subjectID);
        rs = db.access();

        Map<String, String> data = new HashMap<>();
        data.put("mca_university_roll_no", roll);
        data.put("subject_id", subjectID);
        data.put("marks", marks);
        data.put("year", year);
        if (rs.next()) {
            if (rs.getString("COUNT").equals("0")) {
                db.insert("student_marks_map", data);
            } else if (rs.getString("COUNT").equals("1")) {
                db.update("student_marks_map", data);
                db.where("mca_university_roll_no", roll);
                db.where("subject_id", subjectID);
            } else {
                return false;
            }
            db.access();
            return true;
        }

        return false;
    }

    /**
     *
     * @param roll
     * @param sem
     * @return
     * @throws SQLException Sets the student semester number
     */
    public boolean setStudentSemester(String roll, String sem) throws SQLException {

        if(Integer.parseInt(sem) < 1 || Integer.parseInt(sem) > 7){
            return false;
        }
        
        String _sem = getStudentSemester(roll);
        if (_sem.equals("-1")) {
            //db.selectTable("student_master");
            //db.select_count("id");
            //db.where("mca_university_roll_no", roll);
            //ResultSet rs = db.access();

            ArrayList<Student> list = getAllStudent(new String[]{"roll"}, new String[]{roll});
            int len = list.size();
            if (len == 1) {
                Map<String, String> data = new HashMap<>();
                data.put("mca_university_roll_no", roll);
                data.put("semester", sem);
                db.insert("student_semester_map", data);
                db.access();
                return true;
            }

            /*
            if (rs.next()) {
                if (len == 1) {
                    Map<String, String> data = new HashMap<>();
                    data.put("mca_university_roll_no", roll);
                    data.put("semester", sem);
                    db.insert("student_semester_map", data);
                    db.access();
                    return true;
                }
            }*/
            return false;

        } else {
            Map<String, String> data = new HashMap<>();
            data.put("semester", sem);
            db.update("student_semester_map", data);
            db.where("mca_university_roll_no", roll);
            db.access();
            if (getStudentSemester(roll).equals(sem)) {
                return true;
            } else {
                return false;
            }
        }

    }

    public boolean promoteStudentSemester(String roll) throws SQLException {
        String _sem = getStudentSemester(roll);
        Integer nextSem = Integer.parseInt(_sem) + 1;
        if (nextSem == 8) {
            return false;
        }
        if (!_sem.equals("-1")) {
            return setStudentSemester(roll, nextSem.toString());
        }
        return false;
    }

    /**
     *
     * @param roll
     * @param subjectCode
     * @param marks
     * @param year
     * @return
     * @throws SQLException Inserts new Student Marks and also update an
     * existing marks
     */
    public Map<String, Object> getStudentMarks(String roll, String[] filters, String[] values, String orderBy) throws SQLException {
        Map<String, Object> data = new HashMap<>();
        data.put("found", 0);
        int cnt = 0;

        db.joinTables(new String[]{"student_master", "student_marks_map", "subject_semester_map"});
        db.select(new String[]{"A.first_name", "A.middle_name", "A.last_name", "B.mca_university_roll_no", "B.marks", "C.subject_name", "C.subject_code"});
        db.where("A.mca_university_roll_no", "B.mca_university_roll_no");
        db.where("B.subject_id", "C.id");

        for (int i = 0; i < filters.length; i++) {
            switch (filters[i]) {
                case "marks_above":
                    db.where("B.marks", ">= " + values[i].trim());
                    break;
                case "subject_code":
                    db.where("C.subject_code", values[i].trim());
                    break;
                case "roll":
                    db.where("A.mca_university_roll_no", values[i].trim());
                    break;
                case "year":
                    db.where("B.year", values[i].trim());
                    break;
                case "passing_year":
                    db.where("A.mca_academic_session", (Integer.parseInt(values[i].trim()) - 3) + "-" + values[i].trim());
                    break;

            }
        }

        System.out.println(db.getQueryString());

        ResultSet rs = db.access();

        while (rs.next()) {
            data.put("found", ++cnt);
            String sRoll = rs.getString("mca_university_roll_no");
            if (data.containsKey(sRoll)) {

                Map<String, Object> temp1 = (Map<String, Object>) data.get(sRoll);
                ArrayList<Map<String, String>> list = (ArrayList<Map<String, String>>) temp1.get("result");

                Map<String, String> _dataMarks = new HashMap<>();

                _dataMarks.put("subject_name", rs.getString("subject_name"));
                _dataMarks.put("subject_code", rs.getString("subject_code"));
                _dataMarks.put("marks", rs.getString("marks"));

                list.add(_dataMarks);

                temp1.put("result", list);

                data.put(sRoll, temp1);

            } else {
                Map<String, Object> _data = new HashMap<>();
                _data.put("first_name", rs.getString("first_name"));
                _data.put("middle_name", rs.getString("middle_name"));
                _data.put("last_name", rs.getString("last_name"));

                Map<String, String> _dataMarks = new HashMap<>();

                _dataMarks.put("subject_name", rs.getString("subject_name"));
                _dataMarks.put("subject_code", rs.getString("subject_code"));
                _dataMarks.put("marks", rs.getString("marks"));

                ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
                list.add(_dataMarks);

                _data.put("result", list);

                data.put(sRoll, _data);

            }
        }
        return (data.size() != 0) ? data : null;
    }

    public Map<String, Object> getSubjects() throws SQLException {
        db.selectTable("subject_semester_map");
        db.select(new String[]{"*"});
        ResultSet rs = db.access();

        Map<String, Object> result = new HashMap<String, Object>();

        while (rs.next()) {
            if (result.containsKey(rs.getString("subject_semester"))) {
                List<Map<String, Object>> subList = (List<Map<String, Object>>) result.get(rs.getString("subject_semester"));
                Map<String, Object> _result = new HashMap<String, Object>();
                _result.put("id", rs.getString("id"));
                _result.put("subject_name", rs.getString("subject_name"));
                _result.put("subject_code", rs.getString("subject_code"));

                subList.add(_result);
                result.put(rs.getString("subject_semester"), subList);

            } else {
                List<Map<String, Object>> subList = new ArrayList<Map<String, Object>>();
                Map<String, Object> _result = new HashMap<String, Object>();
                _result.put("id", rs.getString("id"));
                _result.put("subject_name", rs.getString("subject_name"));
                _result.put("subject_code", rs.getString("subject_code"));

                subList.add(_result);
                result.put(rs.getString("subject_semester"), subList);
            }

        }

        return result;
    }

}
