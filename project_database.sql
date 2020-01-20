-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 20, 2020 at 01:00 PM
-- Server version: 10.3.15-MariaDB
-- PHP Version: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `project_database`
--

-- --------------------------------------------------------

--
-- Table structure for table `faculty_master`
--

CREATE TABLE `faculty_master` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `middle_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `mobile_number` varchar(100) NOT NULL,
  `faculty_type` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `faculty_master`
--

INSERT INTO `faculty_master` (`id`, `username`, `first_name`, `middle_name`, `last_name`, `email`, `password`, `mobile_number`, `faculty_type`) VALUES
(1, 'admin', 'admin', 'admin', 'admin', 'admin@admin.com', 'admin', '123456789', '1'),
(3, 'shankhaNew', 'Shankhadeep99', '', 'Das99999', 'a@a.com', 'Default', '23457', '2');

-- --------------------------------------------------------

--
-- Table structure for table `faculty_subject_map`
--

CREATE TABLE `faculty_subject_map` (
  `id` int(11) NOT NULL,
  `faculty_id` varchar(100) NOT NULL,
  `subject_id` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `notice`
--

CREATE TABLE `notice` (
  `id` int(11) NOT NULL,
  `faculty_id` varchar(100) NOT NULL,
  `content` varchar(100) NOT NULL,
  `data` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `student_marks_map`
--

CREATE TABLE `student_marks_map` (
  `id` int(11) NOT NULL,
  `mca_university_roll_no` varchar(100) NOT NULL,
  `subject_id` varchar(100) NOT NULL,
  `marks` varchar(100) NOT NULL,
  `year` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `student_master`
--

CREATE TABLE `student_master` (
  `id` int(11) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `middle_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `mobile_number` varchar(100) NOT NULL,
  `sex` varchar(100) NOT NULL,
  `dob` varchar(100) NOT NULL,
  `10_th_exam_name` varchar(100) NOT NULL,
  `10_th_passing_year` varchar(100) NOT NULL,
  `10_th_board` varchar(100) NOT NULL,
  `10_th_school_name` varchar(100) NOT NULL,
  `10_th_language_medium` varchar(100) NOT NULL,
  `10_th_standard_marks` varchar(100) NOT NULL,
  `10_th_actual_marks` varchar(100) NOT NULL,
  `10_th_math_percentage` varchar(100) NOT NULL,
  `12_th_exam_name` varchar(100) NOT NULL,
  `12_th_passing_year` varchar(100) NOT NULL,
  `12_th_board` varchar(100) NOT NULL,
  `12_th_school_name` varchar(100) NOT NULL,
  `12_th_language_medium` varchar(100) NOT NULL,
  `12_th_standard_marks` varchar(100) NOT NULL,
  `12_th_actual_marks` varchar(100) NOT NULL,
  `12_th_math_percentage` varchar(100) NOT NULL,
  `diploma_university` varchar(100) NOT NULL,
  `diploma_stream` varchar(100) NOT NULL,
  `diploma_passing_year` varchar(100) NOT NULL,
  `diploma_marks` varchar(100) NOT NULL,
  `graduation_university` varchar(100) NOT NULL,
  `graduation_stream` varchar(100) NOT NULL,
  `graduation_passing_year` varchar(100) NOT NULL,
  `graduation_marks` varchar(100) NOT NULL,
  `mca_entrance_exam_name` varchar(100) NOT NULL,
  `mca_entrance_rank` varchar(100) NOT NULL,
  `mca_college_name` varchar(100) NOT NULL,
  `mca_university` varchar(100) NOT NULL,
  `mca_stream` varchar(100) NOT NULL,
  `mca_course_duration` varchar(100) NOT NULL,
  `mca_university_registration_no` varchar(100) NOT NULL,
  `mca_university_roll_no` varchar(100) NOT NULL,
  `mca_academic_session` varchar(100) NOT NULL,
  `fathers_name` varchar(100) NOT NULL,
  `fathers_occupation` varchar(100) NOT NULL,
  `mothers_name` varchar(100) NOT NULL,
  `mothers_occupation` varchar(100) NOT NULL,
  `gurdains_name` varchar(100) NOT NULL,
  `gurdains_occupation` varchar(100) NOT NULL,
  `gurdains_relation` varchar(100) NOT NULL,
  `present_address` varchar(100) NOT NULL,
  `permanent_address` varchar(100) NOT NULL,
  `present_state` varchar(100) NOT NULL,
  `permanent_state` varchar(100) NOT NULL,
  `present_city` varchar(100) NOT NULL,
  `permanent_city` varchar(100) NOT NULL,
  `present_district` varchar(100) NOT NULL,
  `permanent_district` varchar(100) NOT NULL,
  `present_pin_number` varchar(100) NOT NULL,
  `permanent_pin_number` varchar(100) NOT NULL,
  `present_post_office` varchar(100) NOT NULL,
  `permanent_post_office` varchar(100) NOT NULL,
  `physical_disability` varchar(100) NOT NULL,
  `academic_year_gap` varchar(100) NOT NULL,
  `session_of_year_gap` varchar(100) NOT NULL,
  `reason_of_year_gap` varchar(100) NOT NULL,
  `image` varchar(100) NOT NULL,
  `BLOOD_GROUP` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_master`
--

INSERT INTO `student_master` (`id`, `first_name`, `middle_name`, `last_name`, `email`, `password`, `mobile_number`, `sex`, `dob`, `10_th_exam_name`, `10_th_passing_year`, `10_th_board`, `10_th_school_name`, `10_th_language_medium`, `10_th_standard_marks`, `10_th_actual_marks`, `10_th_math_percentage`, `12_th_exam_name`, `12_th_passing_year`, `12_th_board`, `12_th_school_name`, `12_th_language_medium`, `12_th_standard_marks`, `12_th_actual_marks`, `12_th_math_percentage`, `diploma_university`, `diploma_stream`, `diploma_passing_year`, `diploma_marks`, `graduation_university`, `graduation_stream`, `graduation_passing_year`, `graduation_marks`, `mca_entrance_exam_name`, `mca_entrance_rank`, `mca_college_name`, `mca_university`, `mca_stream`, `mca_course_duration`, `mca_university_registration_no`, `mca_university_roll_no`, `mca_academic_session`, `fathers_name`, `fathers_occupation`, `mothers_name`, `mothers_occupation`, `gurdains_name`, `gurdains_occupation`, `gurdains_relation`, `present_address`, `permanent_address`, `present_state`, `permanent_state`, `present_city`, `permanent_city`, `present_district`, `permanent_district`, `present_pin_number`, `permanent_pin_number`, `present_post_office`, `permanent_post_office`, `physical_disability`, `academic_year_gap`, `session_of_year_gap`, `reason_of_year_gap`, `image`, `BLOOD_GROUP`) VALUES
(1, 'Shankhadeep-11', '', 'Das', 'shankhascm96@gmail.com', '', '8768231887', 'M', '21/1/96', 'Madhaymic Parikhsha', '2012', 'W.B.S.E', 'Midnapore Collegiate School', 'Bengali', '535', '535', '90', 'Higher Secondary Exam', '2012', 'W.B.C.S.E', 'Midnapore Collegiate School', 'Bengali', '375', '354', '60', 'N/A', 'N/A', 'N/A', 'N/A', 'Mindnapore College', 'Computer Science', '2017', '73.33%', 'JECA', '303', 'Techno India Salt Lake', 'MAKAUT', 'MCA', '2017-2020', '88563961', '95453724', '2017-2020', 'Supriya Das', 'Music Professor', 'Mita Das', 'Home maker', 'N/A', 'N/A', 'N/A', 'Kolkata', 'Midnapore', 'West Bengal', 'West Bengal', 'Kolkata', 'Midnapore', 'Kolkata', 'Paschim Medinipur', '700102', '721101', 'Kestopur', 'Midnapore', 'No', '0', 'N/A', 'N/A', '', 'O+');

-- --------------------------------------------------------

--
-- Table structure for table `student_password_map`
--

CREATE TABLE `student_password_map` (
  `id` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `student_semester_map`
--

CREATE TABLE `student_semester_map` (
  `id` int(11) NOT NULL,
  `mca_university_roll_no` varchar(100) NOT NULL,
  `semester` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `subject_semester_map`
--

CREATE TABLE `subject_semester_map` (
  `id` int(11) NOT NULL,
  `subject_name` varchar(100) NOT NULL,
  `subject_code` varchar(100) NOT NULL,
  `subject_semester` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `faculty_master`
--
ALTER TABLE `faculty_master`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `faculty_subject_map`
--
ALTER TABLE `faculty_subject_map`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `notice`
--
ALTER TABLE `notice`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student_marks_map`
--
ALTER TABLE `student_marks_map`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student_master`
--
ALTER TABLE `student_master`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student_password_map`
--
ALTER TABLE `student_password_map`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student_semester_map`
--
ALTER TABLE `student_semester_map`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `subject_semester_map`
--
ALTER TABLE `subject_semester_map`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `faculty_master`
--
ALTER TABLE `faculty_master`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `faculty_subject_map`
--
ALTER TABLE `faculty_subject_map`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `notice`
--
ALTER TABLE `notice`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `student_marks_map`
--
ALTER TABLE `student_marks_map`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `student_master`
--
ALTER TABLE `student_master`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `student_password_map`
--
ALTER TABLE `student_password_map`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `student_semester_map`
--
ALTER TABLE `student_semester_map`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `subject_semester_map`
--
ALTER TABLE `subject_semester_map`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
