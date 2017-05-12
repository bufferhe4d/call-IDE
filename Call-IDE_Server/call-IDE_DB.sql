-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: May 08, 2017 at 10:12 PM
-- Server version: 5.6.33
-- PHP Version: 7.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `apo_callide`
--
CREATE DATABASE IF NOT EXISTS `apo_callide` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `apo_callide`;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `name` varchar(40) NOT NULL,
  `userid` char(8) NOT NULL,
  `pwd` char(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `title` varchar(30) NOT NULL,
  `code` varchar(10) NOT NULL,
  `instructor` varchar(30) NOT NULL,
  `c_key` varchar(10) NOT NULL,
  ADD PRIMARY KEY (`code`);
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `course`
--


-- --------------------------------------------------------

--
-- Table structure for table `enrollment`
--

CREATE TABLE `enrollment` (
  `email` varchar(40) NOT NULL,
  `code` varchar(10) NOT NULL,

  FOREIGN KEY (code) REFERENCES apo_callide.course (code) ON DELETE SET NULL,
  FOREIGN KEY (email) REFERENCES apo_callide.student (email) ON DELETE SET NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- --------------------------------------------------------

--
-- Table structure for table `ins_assignment`
--

CREATE TABLE `ins_assignment` (
  `name` varchar(40) NOT NULL,
  `duedate` date NOT NULL,
  `subdate` date NOT NULL,
  `path` varchar(80) NOT NULL,
  PRIMARY KEY (`path`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ins_assignment`
--


-- --------------------------------------------------------

--
-- Table structure for table `ins_belongs`
--

CREATE TABLE `ins_belongs` (
  `email` varchar(40) NOT NULL,
  `path` varchar(80) NOT NULL,
  FOREIGN KEY (email) REFERENCES instructor (email),
  FOREIGN KEY (path) REFERENCES ins_assignment (path) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ins_belongs`


-- --------------------------------------------------------

--
-- Table structure for table `instructor`
--

CREATE TABLE `instructor` (
  `name` varchar(40) NOT NULL,
  `userid` int(11) NOT NULL,
  `pwd` char(20) NOT NULL,
  `email` varchar(40) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `instructor`
--


-- --------------------------------------------------------

--
-- Table structure for table `stu_assignment`
--

CREATE TABLE `stu_assignment` (
  `name` varchar(40) NOT NULL,
  `author` varchar(40) NOT NULL,
  `duedate` varchar(20) NOT NULL,
  `subdate` varchar(20) NOT NULL,
  `path` varchar(80) NOT NULL,
  `grade` int(3) DEFAULT NULL,
  PRIMARY KEY (`path`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `stu_belongs`
--

CREATE TABLE `stu_belongs` (
  `email` varchar(40) NOT NULL,
  `path` varchar(80) NOT NULL,
  FOREIGN KEY (email) REFERENCES student (email),
  FOREIGN KEY (path) REFERENCES stu_assignment (email) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `stdID` int(12) NOT NULL,
  `name` varchar(40) NOT NULL,
  `userid` int(8) NOT NULL,
  `pwd` char(20) NOT NULL,
  `email` varchar(40) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `student`
--

-- --------------------------------------------------------

--
-- Table structure for table `teaching`
--

CREATE TABLE `teaching` (
  `email` varchar(40) NOT NULL,
  `code` varchar(10) NOT NULL,
  FOREIGN KEY (code) REFERENCES course (code),
  FOREIGN KEY (email) REFERENCES instructor (email) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `teaching`
--



--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`userid`);

--
-- Indexes for table `course`
--


--
-- Indexes for table `enrollment`
--
ALTER TABLE `enrollment`
  ADD KEY `FK_USER` (`email`),
  ADD KEY `FK_COURSE_CODE` (`code`);

--
-- Indexes for table `ins_assignment`
--
ALTER TABLE `ins_assignment`
  ADD PRIMARY KEY (`path`);

--
-- Indexes for table `ins_belongs`
--
ALTER TABLE `ins_belongs`
  ADD KEY `FK_BELONG_INS` (`email`),
  ADD KEY `FK_ASPATH_INS` (`path`);

--
-- Indexes for table `instructor`
--


--
-- Indexes for table `stu_assignment`
--
ALTER TABLE `stu_assignment`
  ADD PRIMARY KEY (`path`);

--
-- Indexes for table `stu_belongs`
--
ALTER TABLE `stu_belongs`
  ADD KEY `FK_BELONG` (`email`),
  ADD KEY `FK_ASPATH` (`path`);

--
-- Indexes for table `student`


--
-- Indexes for table `teaching`
--
ALTER TABLE `teaching`
  ADD KEY `FK_TEACHER` (`email`),
  ADD KEY `FK_COURSE_T` (`code`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `enrollment`
--
ALTER TABLE `enrollment`
  ADD CONSTRAINT `FK_COURSE_CODE` FOREIGN KEY (`code`) REFERENCES `apo_callide`.`COURSE` (`code`) ON DELETE SET NULL,
  ADD CONSTRAINT `FK_USER` FOREIGN KEY (`email`) REFERENCES `apo_callide`.`STUDENT` (`email`) ON DELETE SET NULL;

--
-- Constraints for table `ins_belongs`
--
ALTER TABLE `ins_belongs`
  ADD CONSTRAINT `FK_ASPATH_INS` FOREIGN KEY (`path`) REFERENCES `apo_callide`.`INS_ASSIGNMENT` (`path`) ON DELETE SET NULL,
  ADD CONSTRAINT `FK_BELONG_INS` FOREIGN KEY (`email`) REFERENCES `apo_callide`.`INSTRUCTOR` (`email`) ON DELETE SET NULL;

--
-- Constraints for table `stu_belongs`
--
ALTER TABLE `stu_belongs`
  ADD CONSTRAINT `FK_ASPATH` FOREIGN KEY (`path`) REFERENCES `apo_callide`.`STU_ASSIGNMENT` (`path`) ON DELETE SET NULL,
  ADD CONSTRAINT `FK_BELONG` FOREIGN KEY (`email`) REFERENCES `apo_callide`.`STUDENT` (`email`) ON DELETE SET NULL;

--
-- Constraints for table `teaching`
--
ALTER TABLE `teaching`
  ADD CONSTRAINT `FK_COURSE_T` FOREIGN KEY (`code`) REFERENCES `apo_callide`.`COURSE` (`code`) ON DELETE SET NULL,
  ADD CONSTRAINT `FK_TEACHER` FOREIGN KEY (`email`) REFERENCES `apo_callide`.`INSTRUCTOR` (`email`) ON DELETE SET NULL;

