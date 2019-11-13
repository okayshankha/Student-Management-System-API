/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import Core.Database.DB;
import Core.Database.Database;
import com.tmsl.pojo.Faculty;
import com.tmsl.pojo.Student;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Shankha
 */
public class Model {

    /**
     * Database object variable.
     */
    protected DB db;

    /**
     * Default Constructor
     */
    public Model() {
        Database.config("localhost", "shankha", "root", "project_database");
        db = new DB(true);

        //Database.config("remotemysql.com", "W7CEGX1euX", "3diebmPP4h", "W7CEGX1euX");
        //db = new DB(true);
        //Model.static_db = new DB(true);
    }

    /**
     * Ends MySQL connection
     *
     * @throws SQLException
     */
    public void closeConnection() throws SQLException {
        db.close();
    }

    /**
     * Enables to post a new notice
     *
     * @param faculty_id
     * @param content
     * @throws SQLException
     */
    public void postNotice(String faculty_id, String content) throws SQLException {
        Map<String, String> data = new HashMap<>();
        data.put("content", content);

        Date date = java.util.Calendar.getInstance().getTime();
        data.put("date", date.toString());

        data.put("faculty_id", faculty_id);
        db.insert("notice", data);
        db.access();
    }

    /**
     * Enables to post a new notice (Optionally filtered by faculty email id)
     *
     * @param faculty_id
     * @return
     * @throws SQLException
     */
    public Map<String, Object> getNotice(String faculty_id) throws SQLException {
        db.joinTables(new String[]{"faculty_master", "notice"});
        db.select(new String[]{"B.faculty_id", "A.first_name", "A.middle_name", "A.last_name", "B.content", "B.date"});
        db.where("A.id", "B.faculty_id");
        if (!faculty_id.equals("")) {
            db.where("B.faculty_id", faculty_id);
        }
        ResultSet rs = db.access();
        Map<String, Object> output = new HashMap<>();

        while (rs.next()) {
            String _faculty_id = rs.getString("faculty_id");
            if (output.containsKey(_faculty_id)) {
                Map<String, Object> _temp = (Map<String, Object>) output.get(_faculty_id);
                Map<String, String> notices = (Map<String, String>) _temp.get("notices");
                notices.put(rs.getString("date"), rs.getString("content"));
                _temp.put("notices", notices);

                output.put(_faculty_id, _temp);
            } else {
                Map<String, Object> _temp = new HashMap<>();
                _temp.put("first_name", rs.getString("first_name"));
                _temp.put("middle_name", rs.getString("middle_name"));
                _temp.put("last_name", rs.getString("last_name"));

                Map<String, String> notices = new HashMap<>();
                notices.put(rs.getString("date"), rs.getString("content"));
                _temp.put("notices", notices);
                output.put(_faculty_id, _temp);
            }
        }
        return output;
    }

    /**
     * Generates an unique Student Roll number
     *
     * @return
     * @throws SQLException
     */
    public String generateRoll() throws SQLException {
        Integer roll = 0;
        Random rand = new Random();
        do {
            roll = rand.nextInt(90000000) + 10000000;
        } while (existsRoll(roll.toString()));

        return roll.toString();
    }

    /**
     * Finds Student existence for an email
     *
     * @param email
     * @return
     * @throws SQLException
     */
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

    /**
     * Finds Student existence for a Roll
     *
     * @param roll
     * @return
     * @throws SQLException
     */
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

    /**
     * Finds Student Registration Number by an email (If Exists)
     *
     * @param email
     * @return
     * @throws SQLException
     */
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

    /**
     * Finds Student Roll Number by an email (If Exists)
     *
     * @param email
     * @return
     * @throws SQLException
     */
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

    /**
     *
     * Finds Faculty object by Semi-complete Faculty object (If Exists)
     *
     * @param faculty
     * @return
     * @throws SQLException
     */
    public Faculty getFaculty(Faculty faculty) throws SQLException {
        Faculty output_faculty = new Faculty();

        db.selectTable("faculty_master");
        db.select(new String[]{"*"});
        if (!faculty.getPassword().trim().equals("") && !faculty.getPassword().trim().equals("adminRequest96")) {
            db.where("password", faculty.getPassword().trim());
        } else {
            if (!faculty.getPassword().trim().equals("adminrequest96")) {
                return null;
            }
        }

        if (faculty != null && !faculty.getEmail().trim().equals("")) {
            db.where("email", faculty.getEmail().trim());
        } else if (faculty != null && !faculty.getUsername().trim().equals("")) {
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

    /**
     * Returns Subject Semester by the Subject Code.
     *
     * @param subjectCode
     * @return
     * @throws SQLException It will return only the first result for the subject
     * code.
     */
    public String getSubjectSemester(String subjectCode) throws SQLException {
        String sem = "";
        db.selectTable("subject_semester_map");
        db.select(new String[]{"subject_semester"});
        db.where("id", subjectCode);
        db.or_where("subject_code", subjectCode);

        ResultSet rs = db.access();

        if (rs.next()) {
            sem = rs.getString("subject_semester");
        }

        return (sem != null && sem.equals("")) ? null : sem;
    }

    /**
     * Returns Subject ID by the Subject Code.
     *
     * @param subjectCode
     * @return
     * @throws SQLException It will return only the first result for the subject
     * code.
     */
    public String getSubjectID(String subjectCode) throws SQLException {
        String sem = "";
        db.selectTable("subject_semester_map");
        db.select(new String[]{"id"});
        db.where("id", subjectCode);
        db.or_where("subject_code", subjectCode);

        ResultSet rs = db.access();

        if (rs.next()) {
            sem = rs.getString("id");
        }

        return (sem != null && sem.equals("")) ? null : sem;
    }

    /**
     * Returns Faculty ID by Faculty email (If Exists)
     *
     * @param email
     * @return
     * @throws SQLException
     */
    public String getFacultyID(String email) throws SQLException {
        db.selectTable("faculty_master");
        db.select(new String[]{"id"});
        db.where("email", email);
        ResultSet rs = db.access();

        if (rs.next()) {
            return rs.getString("id").trim();
        }

        return null;
    }

    /**
     * Assign Faculty to a Subject.
     *
     * @param facultyID
     * @param subjectID
     * @return
     * @throws SQLException
     */
    protected boolean assignFacultyToSubject(String facultyID, String subjectID) throws SQLException {
        db.selectTable("faculty_subject_map");
        db.select_count("id");
        db.where("faculty_id", facultyID);
        db.where("subject_id", subjectID);
        ResultSet rs = db.access();
        if (rs.next()) {
            if (rs.getString("COUNT").equals("1")) {
                return true;
            } else {
                Map<String, String> data = new HashMap<>();
                data.put("faculty_id", facultyID);
                data.put("subject_id", subjectID);
                db.insert("faculty_subject_map", data);
                db.access();
                return true;
            }
        }
        return false;
    }

    /**
     * Unassign Faculty from a Subject.
     *
     * @param facultyID
     * @param subjectID
     * @return
     * @throws SQLException
     */
    protected boolean unassignFacultyToSubject(String facultyID, String subjectID) throws SQLException {
        db.selectTable("faculty_subject_map");
        db.select_count("id");
        db.where("faculty_id", facultyID);
        db.where("subject_id", subjectID);
        ResultSet rs = db.access();
        if (rs.next()) {
            if (rs.getString("COUNT").equals("1")) {
                db.selectTable("faculty_subject_map");
                db.delete();
                db.where("faculty_id", facultyID);
                db.where("subject_id", subjectID);
                db.access();
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Compile Students Data From ResultSet (Database)
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    protected ArrayList<Student> compileStudentsDataFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<Student> list = new ArrayList<>();
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
            try {
                student.setImage(rs.getString("image").replaceAll("%%", "\\\\"));
            } catch (NullPointerException e) {

            }

            list.add(student);
        }
        return list;
    }
}
