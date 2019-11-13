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

/**
 *
 * @author Shankha
 */
public class AdminModel extends Model {

    /**
     *
     * Return Faculty Filtered by Mobile Number
     *
     * @param mob
     * @return
     * @throws java.sql.SQLException
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

    /**
     * Return Faculty Filtered by Mobile email
     *
     * @param email
     * @return
     * @throws SQLException
     */
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

    /**
     * Return Faculty Filtered by Mobile type
     *
     * @param type
     * @param filterType
     * @param filterValue
     * @return
     * @throws SQLException
     */
    public ArrayList<Faculty> getAllFacultyByType(String type, String filterType, String filterValue) throws SQLException {
        ArrayList<Faculty> all_faculties = new ArrayList<>();

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
            if (!rs.getString("email").equals("")) {
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
        }

        return all_faculties;
    }

    /**
     * Return Faculty Filtered by Mobile type
     *
     * @param type
     * @return
     * @throws SQLException
     */
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
            if (!rs.getString("email").equals("")) {
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

        }

        return all_faculties;
    }

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

    /**
     * Adds New Faculty
     *
     * @param faculty
     * @return
     * @throws SQLException
     */
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

    /**
     * Deletes New Faculty
     *
     * @param faculty
     * @return
     * @throws SQLException
     */
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

    /**
     * Checks if Faculty Exists
     *
     * @param faculty
     * @return
     * @throws SQLException
     */
    public String existsFaculty(Faculty faculty) throws SQLException {
        int cnt = 0;
        
        if (faculty.getEmail().trim().equals("")) {
            return "please provide an email";
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
     * Returns all Student data
     *
     * @return
     * @throws java.sql.SQLException
     */
    public ArrayList<Student> getAllStudent() throws SQLException {
        db.selectTable("student_master");
        db.select(new String[]{"*"});
        ResultSet rs = db.access();
        ArrayList<Student> list = compileStudentsDataFromResultSet(rs);
        return list;
    }

    /**
     * Returns all Student data (With Filter)
     *
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
     * Adds Single Student
     *
     * @param student
     * @return
     * @throws SQLException
     */
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

        if (!needToUpdateData) {
            setStudentSemester(data.get("mca_university_roll_no"), "1");
            System.out.println("Semester ");
        }

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

    /**
     * Get Student Semester by Roll
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

    /**
     * Set Student Semester by Roll
     *
     * @param roll
     * @param sem
     * @return
     * @throws SQLException Sets the student semester number
     */
    public boolean setStudentSemester(String roll, String sem) throws SQLException {

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

    /**
     * Promote Student Semester
     *
     * @param roll
     * @return
     * @throws SQLException
     */
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
     * Get Student Marks
     *
     * @param filters
     * @param values
     * @param orderBy
     * @return
     * @throws SQLException Inserts new Student Marks and also update an
     * existing marks
     */
    public Map<String, Object> getStudentMarks(String[] filters, String[] values, String orderBy) throws SQLException {
        Map<String, Object> data = new HashMap<>();
        int cnt = 0;

        db.joinTables(new String[]{"student_master", "student_marks_map", "subject_semester_map"});
        db.select(new String[]{"A.first_name", "A.middle_name", "A.last_name", "B.mca_university_roll_no", "B.marks", "B.year", "C.subject_name", "C.subject_code"});
        db.where("A.mca_university_roll_no", "B.mca_university_roll_no");
        db.where("B.subject_id", "C.id");
        db.orderBy(new String[]{"B.year"});

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
                case "sem":
                    db.where("C.subject_semester", values[i].trim());
                    break;

            }
        }

        //System.out.println(db.getQueryString());
        ResultSet rs = db.access();

        while (rs.next()) {
            ++cnt;
            //data.put("found", ++cnt);
            String sRoll = rs.getString("mca_university_roll_no");
            if (data.containsKey(sRoll)) {

                Map<String, Object> temp1 = (Map<String, Object>) data.get(sRoll);
                ArrayList<Map<String, String>> list = (ArrayList<Map<String, String>>) temp1.get("result");

                Map<String, String> _dataMarks = new HashMap<>();

                _dataMarks.put("subject_name", rs.getString("subject_name"));
                _dataMarks.put("subject_code", rs.getString("subject_code"));
                _dataMarks.put("marks", rs.getString("marks"));
                _dataMarks.put("year", rs.getString("year"));

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
                _dataMarks.put("year", rs.getString("year"));

                ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
                list.add(_dataMarks);

                _data.put("result", list);

                data.put(sRoll, _data);

            }
        }
        Map<String, Object> d2 = new HashMap<>();
        d2.put("student_marks", data);
        d2.put("found", cnt);
        return (d2.size() != 0) ? d2 : null;
    }

    /**
     * Set Student Marks
     *
     * @param roll
     * @param subjectCode
     * @param marks
     * @param year
     * @return
     * @throws SQLException Inserts new Student Marks and also update an
     * existing marks
     */
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
     * Get Runner ups
     *
     * @param year
     * @return
     * @throws SQLException
     */
    public Map<String, Object> getRunnerups(String year) throws SQLException {
        Map<String, Object> result = new HashMap<>();

        String query1 = "SELECT C.`first_name`, C.`middle_name`, C.`last_name`, A.`semester`, B.* FROM `project_database`.`student_semester_map` AS A, (SELECT B.`mca_university_roll_no`, B.`year`, B.`total_marks` FROM `project_database`.`subject_semester_map` AS A, (SELECT `student_marks_map`.`mca_university_roll_no`, `student_marks_map`.`year`, `student_marks_map`.`subject_id`, Sum(`student_marks_map`.`marks`) AS 'total_marks' FROM `project_database`.`student_marks_map` GROUP BY `student_marks_map`.`mca_university_roll_no`) AS B WHERE B.`subject_id` = A.`id`) AS B, `project_database`.`student_master` AS C WHERE B.`mca_university_roll_no` = A.`mca_university_roll_no` AND A.`semester` = ? AND B.`year` = ? AND C.`mca_university_roll_no` = B.`mca_university_roll_no` ORDER BY B.`year`, B.`total_marks` DESC LIMIT 3;";
        //String query1 = "SELECT C.`first_name`, C.`middle_name`, C.`last_name`, A.`semester`, B.* FROM `project_database`.`student_semester_map` AS A, (SELECT B.`mca_university_roll_no`, B.`year`, B.`total_marks` FROM `project_database`.`subject_semester_map` AS A, (SELECT `student_marks_map`.`mca_university_roll_no`, `student_marks_map`.`year`, `student_marks_map`.`subject_id`, Sum(`student_marks_map`.`marks`) AS 'total_marks' FROM `W7CEGX1euX`.`student_marks_map` GROUP BY `student_marks_map`.`mca_university_roll_no`) AS B WHERE B.`subject_id` = A.`id`) AS B, `W7CEGX1euX`.`student_master` AS C WHERE B.`mca_university_roll_no` = A.`mca_university_roll_no` AND A.`semester` = ? AND B.`year` = ? AND C.`mca_university_roll_no` = B.`mca_university_roll_no` ORDER BY B.`year`, B.`total_marks` DESC LIMIT 3;";

        //String query2 = "SELECT C.`first_name`, C.`middle_name`, C.`last_name`, A.`semester`, B.* FROM `project_database`.`student_semester_map` AS A, (SELECT B.`mca_university_roll_no`, B.`year`, B.`total_marks` FROM `project_database`.`subject_semester_map` AS A, (SELECT `student_marks_map`.`mca_university_roll_no`, `student_marks_map`.`year`, `student_marks_map`.`subject_id`, Sum(`student_marks_map`.`marks`) AS 'total_marks' FROM `project_database`.`student_marks_map` GROUP BY `student_marks_map`.`mca_university_roll_no`) AS B WHERE B.`subject_id` = A.`id`) AS B, `project_database`.`student_master` AS C WHERE B.`mca_university_roll_no` = A.`mca_university_roll_no` AND A.`semester` = ? AND C.`mca_university_roll_no` = B.`mca_university_roll_no` ORDER BY B.`year`, B.`total_marks` DESC LIMIT 3;";
        String query = null;
        String[] params = null;

        if (!year.equals("")) {
            params = new String[]{"1", year};
            query = query1;
        } else {
            result.put("warning", "please specify the year");
            return result;
        }

        for (int i = 1; i <= 6; i++) {

            params[0] = i + "";
            ResultSet rs = db.access(query, params);
            ArrayList<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

            while (rs.next()) {
                Map<String, Object> _data = new HashMap<>();
                _data.put("first_name", rs.getString("first_name"));
                _data.put("middle_name", rs.getString("middle_name"));
                _data.put("last_name", rs.getString("last_name"));

                _data.put("roll", rs.getString("mca_university_roll_no"));
                _data.put("year", rs.getString("year"));
                _data.put("total_marks", rs.getString("total_marks"));

                resultList.add(_data);
            }
            result.put(params[0], resultList);
        }
        return result;
    }

    /**
     * Get Subjects
     *
     * @return
     * @throws SQLException
     */
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

    @Override
    public boolean assignFacultyToSubject(String facultyID, String subjectID) throws SQLException {
        return super.assignFacultyToSubject(facultyID, subjectID);
    }

    @Override
    public boolean unassignFacultyToSubject(String facultyID, String subjectID) throws SQLException {
        return super.unassignFacultyToSubject(facultyID, subjectID);
    }

}
