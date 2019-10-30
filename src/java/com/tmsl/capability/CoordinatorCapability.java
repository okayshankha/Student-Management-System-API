/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmsl.capability;

import com.tmsl.model.CoordinatorModel;
import com.tmsl.model.TeacherModel;
import com.tmsl.pojo.Student;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Shankha
 */
public class CoordinatorCapability extends CommonCapability {

    CoordinatorModel coordinatorModel = new CoordinatorModel();

    /**
     * Ends MySQL connection
     * @throws SQLException
     */
    public void closeConnection() throws SQLException {
        coordinatorModel.closeConnection();
    }

    /**
     * Submits New Notice
     * @return
     * @throws SQLException
     */
    public boolean submitNotice() throws SQLException {
        String content = gPost("content");
        HttpSession session = request.getSession();
        String faculty_type = session.getAttribute("faculty_type").toString();

        if (faculty_type.equals("1") || faculty_type.equals("2") || faculty_type.equals("3")) {
            String faculty_id = coordinatorModel.getFacultyID(session.getAttribute("email").toString());

            coordinatorModel.postNotice(faculty_id, content);

            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns All Notices (Optional filter by faculty email)
     * @return
     * @throws SQLException
     */
    public Map<String, Object> getNotice() throws SQLException {
        String faculty_id = coordinatorModel.getFacultyID(gPost("email"));
        if (faculty_id == null) {
            faculty_id = "";
        }
        return coordinatorModel.getNotice(faculty_id);
    }

    /**
     * Get All Students
     * @return
     * @throws SQLException
     */
    public ArrayList<Student> getStudents() throws SQLException {
        ArrayList<Student> list = coordinatorModel.getAllStudent();
        return ((list != null) && (!list.isEmpty())) ? list : null;
    }

    /**
     * Get All Students (With filters)
     * @param filters
     * @param values
     * @return
     * @throws SQLException
     */
    public ArrayList<Student> getStudents(String[] filters, String[] values) throws SQLException {
        ArrayList<Student> list = null;

        list = coordinatorModel.getAllStudent(filters, values);

        return ((list != null) && (list.size() != 0)) ? list : null;
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
    public boolean setStudnetMarks(String roll, String subjectCode, String marks, String year) throws SQLException {
        return coordinatorModel.setStudentMarks(roll, subjectCode, marks, year);
    }


    /**
     * Returns Student Marks
     * @param filters_post_data
     * @param values_post_data
     * @param orderBy
     * @return
     * @throws SQLException
     */
    public Map<String, Object> getStudnetMarks(String filters_post_data, String values_post_data, String orderBy) throws SQLException {

        Map<String, Object> data = new HashMap<>();
        HttpSession session = request.getSession();
        String facultyEmail = session.getAttribute("email").toString();
        if (!filters_post_data.equals("")) {
            ArrayList<String> validFilters = new ArrayList<>();
            validFilters.add("marks_above");
            validFilters.add("subject_code");
            validFilters.add("roll");
            validFilters.add("year");
            validFilters.add("passing_year");
            validFilters.add("sem");

            String[] filters = filters_post_data.split("\\|", -1);
            String[] values = values_post_data.split("\\|", -1);
            for (String s : filters) {
                if (!validFilters.contains(s)) {
                    data.put("error", "true");
                    data.put("filter", "invalid filter value is provided");
                    return data;
                }
            }

            if (filters.length != values.length) {
                data.put("error", "true");
                data.put("filter", "filter count and value count are not same");
                return data;
            }
            data = coordinatorModel.getStudentMarks(filters, values, orderBy, facultyEmail);
            if (data == null) {
                data = new HashMap<String, Object>();
                data.put("error", "true");
                data.put("database", "error accessing database");
            }

            return data;
        } else {
            return coordinatorModel.getStudentMarks(new String[]{}, new String[]{}, "", facultyEmail);
        }
    }

    /*
    public Map<String, Object> getRunnerups(String year) throws SQLException {
        return coordinatorModel.getRunnerups(year);
    }*/

    /**
     * Returns All Subjects
     * @return
     * @throws SQLException
     */

    public Map<String, Object> getSubjects() throws SQLException {
        return coordinatorModel.getSubjects();
    }

}
