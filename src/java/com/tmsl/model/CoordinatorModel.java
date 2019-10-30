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
import java.util.List;
import java.util.Map;

/**
 *
 * @author Shankha
 *
 */
public class CoordinatorModel extends Model {

    /**
     * Returns All Student Data
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
     * Get Student Semester
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
     * Set Student Marks
     * @param roll
     * @param subjectCode
     * @param marks
     * @param year
     * @return
     * @throws SQLException
     */
    public boolean setStudentMarks(String roll, String subjectCode, String marks, String year) throws SQLException {
        subjectCode = subjectCode.toUpperCase();
        
                
        db.joinTables(new String[]{"faculty_subject_map", "faculty_master"});
        db.select(new String[]{"A.subject_id"});
        db.where("A.faculty_id", "B.id");
        ResultSet _rs = db.access();
        List<String> _subjectList = new ArrayList<>();
        while (_rs.next()) {
            _subjectList.add(_rs.getString("subject_id"));
        }
        
        if(!_subjectList.contains(getSubjectID(subjectCode))){
            System.out.println();
            System.out.println("-------------------------------------------------------------------");
            System.out.println("|             Faculty is not assigned to this subject             |");
            System.out.println("-------------------------------------------------------------------");
            return false;
        }
        
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
     * Get Student Marks
     * @param filters
     * @param values
     * @param orderBy
     * @param facultyEmail
     * @return
     * @throws SQLException Inserts new Student Marks and also update an
     * existing marks
     */
    public Map<String, Object> getStudentMarks(String[] filters, String[] values, String orderBy, String facultyEmail) throws SQLException {
        Map<String, Object> data = new HashMap<>();
        int cnt = 0;

        db.joinTables(new String[]{"faculty_subject_map", "faculty_master"});
        db.select(new String[]{"A.subject_id"});
        db.where("A.faculty_id", "B.id");
        ResultSet _rs = db.access();
        List<String> _subjectList = new ArrayList<>();
        while (_rs.next()) {
            _subjectList.add(_rs.getString("subject_id"));
        }

        String[] subjectList = new String[_subjectList.size()];
        subjectList = _subjectList.toArray(subjectList);

        db.joinTables(new String[]{"student_master", "student_marks_map", "subject_semester_map"});
        db.select(new String[]{"A.first_name", "A.middle_name", "A.last_name", "B.mca_university_roll_no", "B.marks", "B.year", "C.subject_name", "C.subject_code"});
        db.where("A.mca_university_roll_no", "B.mca_university_roll_no");
        db.where("B.subject_id", "C.id");
        db.orderBy(new String[]{"B.year"});
        db.where_in("C.id", subjectList);

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

                ArrayList<Map<String, String>> list = new ArrayList<>();
                list.add(_dataMarks);

                _data.put("result", list);

                data.put(sRoll, _data);

            }
        }
        Map<String, Object> d2 = new HashMap<>();
        d2.put("student_marks", data);
        d2.put("found", cnt);
        return (!d2.isEmpty()) ? d2 : null;
    }

    /**
     * Get Subjects
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

}
