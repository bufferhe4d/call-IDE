-- Generation Time: May 01, 2017 at 07:38 PM
-- Server version: 5.6.33
-- PHP Version: 7.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `callIDE`
--

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
-- Table structure for table `assignment`
--

CREATE TABLE `assignment` (
  `grade` smallint(3) UNSIGNED NOT NULL,
  `aid` int(11) NOT NULL,
  `author` int(11) NOT NULL,
  `cid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `cid` int(11) NOT NULL,
  `name` varchar(10) NOT NULL,
  `instructor` int(11) NOT NULL,
  `c_key` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`cid`, `name`, `instructor`, `c_key`) VALUES
(1, 'cs101', 20, 'frsrvm');

-- --------------------------------------------------------

--
-- Table structure for table `enrollment`
--

CREATE TABLE `enrollment` (
  `userid` int(11) NOT NULL,
  `cid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `instructor`
--

CREATE TABLE `instructor` (
  `name` varchar(40) NOT NULL,
  `userid` int(11) NOT NULL,
  `pwd` char(20) NOT NULL,
  `email` char(30) NOT NULL
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
  `email` char(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`stdID`, `name`, `userid`, `pwd`, `email`) VALUES
(0, 'asdfds', 1, 'fsd', 'fsdfsdg'),
(1, '2', 2, '3', '4'),
(1, '2', 3, '3', '4'),
(0, 'gddfg', 4, 'gdfggd', 'fg'),
(10, 'joe', 5, 'joepwd', 'joe@mail.com');

-- --------------------------------------------------------

--
-- Table structure for table `teaching`
--

CREATE TABLE `teaching` (
  `userid` int(11) NOT NULL,
  `cid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`userid`);

--
-- Indexes for table `assignment`
--
ALTER TABLE `assignment`
  ADD PRIMARY KEY (`aid`),
  ADD KEY `FK_AUTHOR` (`author`),
  ADD KEY `FK_COURSE` (`cid`);

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`cid`);

--
-- Indexes for table `enrollment`
--
ALTER TABLE `enrollment`
  ADD KEY `FK_USER` (`userid`),
  ADD KEY `FK_COURSE_ID` (`cid`);

--
-- Indexes for table `instructor`
--
ALTER TABLE `instructor`
  ADD PRIMARY KEY (`userid`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`userid`),
  ADD UNIQUE KEY `stdID` (`stdID`,`userid`),
  ADD UNIQUE KEY `userid` (`userid`);

--
-- Indexes for table `teaching`
--
ALTER TABLE `teaching`
  ADD KEY `FK_TEACHER` (`userid`),
  ADD KEY `FK_COURSE_T` (`cid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `assignment`
--
ALTER TABLE `assignment`
  MODIFY `aid` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `course`
--
ALTER TABLE `course`
  MODIFY `cid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `userid` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `assignment`
--
ALTER TABLE `assignment`
  ADD CONSTRAINT `FK_AUTHOR` FOREIGN KEY (`author`) REFERENCES `callidetest`.`STUDENT` (`userid`),
  ADD CONSTRAINT `FK_COURSE` FOREIGN KEY (`cid`) REFERENCES `callidetest`.`COURSE` (`cid`);

--
-- Constraints for table `enrollment`
--
ALTER TABLE `enrollment`
  ADD CONSTRAINT `FK_COURSE_ID` FOREIGN KEY (`cid`) REFERENCES `callidetest`.`COURSE` (`cid`),
  ADD CONSTRAINT `FK_USER` FOREIGN KEY (`userid`) REFERENCES `callidetest`.`STUDENT` (`userid`);

--
-- Constraints for table `teaching`
--
ALTER TABLE `teaching`
  ADD CONSTRAINT `FK_COURSE_T` FOREIGN KEY (`cid`) REFERENCES `callidetest`.`COURSE` (`cid`),
  ADD CONSTRAINT `FK_TEACHER` FOREIGN KEY (`userid`) REFERENCES `callidetest`.`INSTRUCTOR` (`userid`);
