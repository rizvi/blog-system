-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 15, 2023 at 02:43 PM
-- Server version: 8.0.31
-- PHP Version: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `blogmanagementsystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `admininfo`
--

DROP TABLE IF EXISTS `admininfo`;
CREATE TABLE IF NOT EXISTS `admininfo` (
  `NID` varchar(25) COLLATE utf8mb4_general_ci NOT NULL,
  `NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `PASSWORD` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`NID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admininfo`
--

INSERT INTO `admininfo` (`NID`, `NAME`, `PASSWORD`) VALUES
('123', 'admin', 'admin'),
('admin', 'admin', 'admin'),
('root', 'admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `checkinoutinfo`
--

DROP TABLE IF EXISTS `checkinoutinfo`;
CREATE TABLE IF NOT EXISTS `checkinoutinfo` (
  `SI_NO` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `EMAIL` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `PHONE` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `ADDRESS` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `NID` varchar(15) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `ROOMNO` varchar(15) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `ROOMTYPE` varchar(15) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `CAPACITY` varchar(15) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `CHECKEDIN` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `CHECKEDOUT` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `PRICEDAY` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `TOTALDAYS` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `TOTALPRICE` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`SI_NO`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `checkinoutinfo`
--

INSERT INTO `checkinoutinfo` (`SI_NO`, `NAME`, `EMAIL`, `PHONE`, `ADDRESS`, `NID`, `ROOMNO`, `ROOMTYPE`, `CAPACITY`, `CHECKEDIN`, `CHECKEDOUT`, `PRICEDAY`, `TOTALDAYS`, `TOTALPRICE`) VALUES
(1, 'mursalin', 'mursalin@gmail.com', '01222222', 'Dhaka, Bangladesh', 'mursalin', '1', 'AC', 'Single', '2023-06-14', '2023-06-15', '1500', '2', '3000'),
(2, 'Mazharul Islam', 'mazhar@gmail.com', '01750265482', 'Kapasia, Gazipur', '123', '11', 'Non-Ac', 'Double', '2023-06-15', '2023-06-16', '500', '2', '1000'),
(3, 'Mazharul Islam', 'mazhar@gmail.com', '01750265482', 'Kapasia, Gazipur', '123', '111', 'AC', 'Double', '2023-06-16', NULL, '1000', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `customerinfo`
--

DROP TABLE IF EXISTS `customerinfo`;
CREATE TABLE IF NOT EXISTS `customerinfo` (
  `NAME` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `NID` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `PASSWORD` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `EMAIL` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `PHONE` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `ADDRESS` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`NID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customerinfo`
--

INSERT INTO `customerinfo` (`NAME`, `NID`, `PASSWORD`, `EMAIL`, `PHONE`, `ADDRESS`) VALUES
('Abu Zakir Rizvi', 'rizvi', 'rizvi123', 'rizvi@gmail.com', '01719370567', 'Dhaka, Bangladesh'),
('Abdur Rahim', 'rahim', 'rahim123', 'rahim@gmail.com', '01719370567', 'Dhaka, Bangladesh'),
('Abdul Karim', 'karim', 'karim123', 'karim@gmail.com', '01719370567', 'Dhaka, Bangladesh');

-- --------------------------------------------------------

--
-- Table structure for table `employeeinfo`
--

DROP TABLE IF EXISTS `employeeinfo`;
CREATE TABLE IF NOT EXISTS `employeeinfo` (
  `NAME` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `NID` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `PASSWORD` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `EMAIL` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `ADDRESS` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `PHONE` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`NID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employeeinfo`
--

INSERT INTO `employeeinfo` (`NAME`, `NID`, `PASSWORD`, `EMAIL`, `ADDRESS`, `PHONE`) VALUES
('Md. Rahid Parvez', 'EMP001', '123456', 'rahid@gmail.com', 'Shahbag, Dhaka-1000', '01915953086'),
('Md. Mursalin', 'mursalin', 'mursalin', 'mur@gmail.com', 'dhaka, bangla', '01222222'),
('rakib', 'rakib', 'rakib', 'hasan@gmail.com', 'dhaka', '012323');

-- --------------------------------------------------------

--
-- Table structure for table `roominfo`
--

DROP TABLE IF EXISTS `roominfo`;
CREATE TABLE IF NOT EXISTS `roominfo` (
  `ROOM_NO` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `TYPE` varchar(10) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `CAPACITY` varchar(10) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `PRICE_DAY` float DEFAULT NULL,
  `STATUS` enum('Available','Unavailable','Booked') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'Available',
  PRIMARY KEY (`ROOM_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `roominfo`
--

INSERT INTO `roominfo` (`ROOM_NO`, `TYPE`, `CAPACITY`, `PRICE_DAY`, `STATUS`) VALUES
('1', 'AC', 'Single', 1500, 'Available'),
('11', 'Non-Ac', 'Double', 500, 'Available'),
('111', 'AC', 'Double', 1000, 'Booked'),
('12', 'Non-Ac', '12', 250, 'Available'),
('123', 'AC', '3', 750, 'Available'),
('13', 'Ac', '12', 12, 'Available'),
('2', 'AC-Room', 'Double', 2000, 'Available'),
('3', 'AC', 'Double', 600, 'Available'),
('9', 'Non-Ac', '4', 500, 'Available');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
