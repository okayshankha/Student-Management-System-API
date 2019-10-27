/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tmsl.capability;

import base.Controller;
import base.Model;
import com.google.gson.Gson;
import com.tmsl.model.AdminModel;
import com.tmsl.pojo.Faculty;
import com.tmsl.pojo.Student;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Shankha
 */
public class CommonCapability extends Controller {

    public Model model;

    protected Student compileStudentPostData() {

        Student student = new Student();
        student.setFirst_name(gPost("first_name"));
        student.setMiddle_name(gPost("middle_name"));
        student.setLast_name(gPost("last_name"));
        student.setMobile_number(gPost("mobile_number"));
        student.setEmail(gPost("email"));
        student.setSex(gPost("sex"));
        student.setDob(gPost("dob"));

        student.set10_th_exam_name(gPost("_10_th_exam_name"));
        student.set10_th_passing_year(gPost("_10_th_passing_year"));
        student.set10_th_board(gPost("_10_th_board"));
        student.set10_th_school_name(gPost("_10_th_school_name"));
        student.set10_th_language_medium(gPost("_10_th_language_medium"));
        student.set10_th_standard_marks(gPost("_10_th_standard_marks"));
        student.set10_th_actual_marks(gPost("_10_th_actual_marks"));
        student.set10_th_math_percentage(gPost("_10_th_math_percentage"));

        student.set12_th_exam_name(gPost("_12_th_exam_name"));
        student.set12_th_passing_year(gPost("_12_th_passing_year"));
        student.set12_th_board(gPost("_12_th_board"));
        student.set12_th_school_name(gPost("_12_th_school_name"));
        student.set12_th_language_medium(gPost("_12_th_language_medium"));
        student.set12_th_standard_marks(gPost("_12_th_standard_marks"));
        student.set12_th_actual_marks(gPost("_12_th_actual_marks"));
        student.set12_th_math_percentage(gPost("_12_th_math_percentage"));

        student.setDiploma_university(gPost("diploma_university"));
        student.setDiploma_stream(gPost("diploma_stream"));
        student.setDiploma_passing_year(gPost("diploma_passing_year"));
        student.setDiploma_marks(gPost("diploma_marks"));

        student.setGraduation_university(gPost("graduation_university"));
        student.setGraduation_stream(gPost("graduation_stream"));
        student.setGraduation_passing_year(gPost("graduation_passing_year"));
        student.setGraduation_marks(gPost("graduation_marks"));

        student.setMca_entrance_exam_name(gPost("mca_entrance_exam_name"));
        student.setMca_entrance_rank(gPost("mca_entrance_rank"));
        student.setMca_college_name(gPost("mca_college_name"));
        student.setMca_university(gPost("mca_university"));
        student.setMca_stream(gPost("mca_stream"));
        student.setMca_course_duration(gPost("mca_course_duration"));
        student.setMca_university_registration_no(gPost("mca_university_registration_no"));
        student.setMca_university_roll_no(gPost("mca_university_roll_no"));
        student.setMca_academic_session(gPost("mca_academic_session"));

        student.setFathers_name(gPost("fathers_name"));
        student.setFathers_occupation(gPost("fathers_occupation"));
        student.setMothers_name(gPost("mothers_name"));

        student.setMothers_occupation(gPost("mothers_occupation"));
        student.setGurdains_name(gPost("gurdains_name"));
        student.setGurdains_occupation(gPost("gurdains_occupation"));
        student.setGurdains_relation(gPost("gurdains_relation"));
        student.setMca_university_roll_no(gPost("mca_university_roll_no"));
        student.setMca_academic_session(gPost("mca_academic_session"));

        student.setPresent_address(gPost("present_address"));
        student.setPermanent_address(gPost("permanent_address"));
        student.setPresent_state(gPost("present_state"));
        student.setPresent_city(gPost("present_city"));
        student.setPermanent_city(gPost("permanent_city"));
        student.setPresent_district(gPost("present_district"));
        student.setPermanent_district(gPost("permanent_district"));
        student.setPresent_pin_number(gPost("present_pin_number"));
        student.setPermanent_pin_number(gPost("permanent_pin_number"));
        student.setPresent_post_office(gPost("present_post_office"));
        student.setPermanent_post_office(gPost("permanent_post_office"));

        student.setPhysical_disability(gPost("physical_disability"));
        student.setAcademic_year_gap(gPost("academic_year_gap"));
        student.setSession_of_year_gap(gPost("session_of_year_gap"));
        student.setReason_of_year_gap(gPost("reason_of_year_gap"));
        student.setBlood_group(gPost("blood_group"));

        return student;
    }

    protected Student compileStudentExcelData(ArrayList<String> sData, Map<String, Integer> positionMap) {

        Student student = new Student();
        student.setFirst_name(sData.get(positionMap.get("First Name")));
        student.setMiddle_name(sData.get(positionMap.get("Middle Name")));
        student.setLast_name(sData.get(positionMap.get("Last Name")));
        student.setMobile_number(sData.get(positionMap.get("Mobile")));
        student.setEmail(sData.get(positionMap.get("Email")));
        student.setSex(sData.get(positionMap.get("Sex")));
        student.setDob(sData.get(positionMap.get("DOB(DD/MM/YYYY)")));

        student.set10_th_exam_name(sData.get(positionMap.get("10th Exam Name")));
        student.set10_th_passing_year(sData.get(positionMap.get("10th passing year")));
        student.set10_th_board(sData.get(positionMap.get("10th board name")));
        student.set10_th_school_name(sData.get(positionMap.get("10th school name")));
        student.set10_th_language_medium(sData.get(positionMap.get("10th language medium")));
        student.set10_th_standard_marks(sData.get(positionMap.get("10th Standard marks")));
        student.set10_th_actual_marks(sData.get(positionMap.get("10th Obtained marks")));
        student.set10_th_math_percentage(sData.get(positionMap.get("10th math percentage")));

        student.set12_th_exam_name(sData.get(positionMap.get("12th Exam Name")));
        student.set12_th_passing_year(sData.get(positionMap.get("12th passing year")));
        student.set12_th_board(sData.get(positionMap.get("12th board name")));
        student.set12_th_school_name(sData.get(positionMap.get("12th school name")));
        student.set12_th_language_medium(sData.get(positionMap.get("12th language medium")));
        student.set12_th_standard_marks(sData.get(positionMap.get("12th standard marks")));
        student.set12_th_actual_marks(sData.get(positionMap.get("12th obtained marks")));
        student.set12_th_math_percentage(sData.get(positionMap.get("12th math percentage")));

        student.setDiploma_university(sData.get(positionMap.get("Diploma university")));
        student.setDiploma_stream(sData.get(positionMap.get("Diploma stream")));
        student.setDiploma_passing_year(sData.get(positionMap.get("Diploma passing year")));
        student.setDiploma_marks(sData.get(positionMap.get("Diploma obtained marks")));

        student.setGraduation_university(sData.get(positionMap.get("Graduation university")));
        student.setGraduation_stream(sData.get(positionMap.get("Graduation stream")));
        student.setGraduation_passing_year(sData.get(positionMap.get("Graduation passing year")));
        student.setGraduation_marks(sData.get(positionMap.get("Graduation obtained marks")));

        student.setMca_entrance_exam_name(sData.get(positionMap.get("MCA entrance test name")));
        student.setMca_entrance_rank(sData.get(positionMap.get("MCA entrance test rank")));
        student.setMca_college_name(sData.get(positionMap.get("MCA college name")));
        student.setMca_university(sData.get(positionMap.get("MCA university")));
        student.setMca_stream(sData.get(positionMap.get("MCA stream")));
        student.setMca_course_duration(sData.get(positionMap.get("MCA course duration")));
        student.setMca_university_registration_no(sData.get(positionMap.get("MCA university registration no")));
        student.setMca_university_roll_no(sData.get(positionMap.get("MCA university roll no")));
        student.setMca_academic_session(sData.get(positionMap.get("MCA course duration")));

        student.setFathers_name(sData.get(positionMap.get("Fathers Name")));
        student.setFathers_occupation(sData.get(positionMap.get("Fathers occupation")));
        student.setMothers_name(sData.get(positionMap.get("Mothers name")));
        student.setMothers_occupation(sData.get(positionMap.get("Mothers occupation")));
        student.setGurdains_name(sData.get(positionMap.get("Gurdains name")));
        student.setGurdains_occupation(sData.get(positionMap.get("Gurdains occupation")));
        student.setGurdains_relation(sData.get(positionMap.get("Gurdains relation")));

        student.setPresent_address(sData.get(positionMap.get("Present address")));
        student.setPermanent_address(sData.get(positionMap.get("Permanent address")));
        student.setPresent_state(sData.get(positionMap.get("Present state")));
        student.setPermanent_state(sData.get(positionMap.get("Permanent state")));
        student.setPresent_city(sData.get(positionMap.get("Present city")));
        student.setPermanent_city(sData.get(positionMap.get("Permanent city")));
        student.setPresent_district(sData.get(positionMap.get("Present district")));
        student.setPermanent_district(sData.get(positionMap.get("Permanent district")));
        student.setPresent_pin_number(sData.get(positionMap.get("Present pin number")));
        student.setPermanent_pin_number(sData.get(positionMap.get("Permanent pin number")));
        student.setPresent_post_office(sData.get(positionMap.get("Present post office")));
        student.setPermanent_post_office(sData.get(positionMap.get("Permanent post office")));

        student.setPhysical_disability(sData.get(positionMap.get("Physical disability")));
        student.setAcademic_year_gap(sData.get(positionMap.get("Number of academic year gap")));
        student.setSession_of_year_gap(sData.get(positionMap.get("Session of year gap")));
        student.setReason_of_year_gap(sData.get(positionMap.get("Reason of year gap")));
        student.setBlood_group(sData.get(positionMap.get("Blood group")));
/*
        Gson gson = new Gson();
        String json = gson.toJson(student);
        System.out.println("Att ------->" + json);
*/
        return student;
    }

    protected Faculty compileFacultyPostData() {
        String username = gPost("username");
        String email = gPost("email");
        String first_name = gPost("first_name");
        String middle_name = gPost("middle_name");
        String last_name = gPost("last_name");
        String pass = "Default";
        String mobile_number = gPost("mobile_number");
        String faculty_type = "-1";

        Faculty faculty = new Faculty();
        faculty.setUsername(username);
        faculty.setEmail(email);
        faculty.setFirst_name(first_name);
        faculty.setMiddle_name(middle_name);
        faculty.setLast_name(last_name);
        faculty.setPassword(pass);
        faculty.setMobile_number(mobile_number);
        faculty.setFaculty_type(faculty_type);

        return faculty;
    }
    


}
