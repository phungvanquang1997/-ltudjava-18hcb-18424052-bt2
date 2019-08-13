/*
Navicat MySQL Data Transfer

Source Server         : admin
Source Server Version : 50724
Source Host           : localhost:3306
Source Database       : java

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-08-13 16:01:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` int(11) DEFAULT NULL,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `salary` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('0', 'quang', 'quang', '123');

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `id` varchar(10) NOT NULL,
  `name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of grade
-- ----------------------------

-- ----------------------------
-- Table structure for point
-- ----------------------------
DROP TABLE IF EXISTS `point`;
CREATE TABLE `point` (
  `MSSV` varchar(10) DEFAULT NULL,
  `subjectId` varchar(10) DEFAULT NULL,
  `middle` float DEFAULT NULL,
  `final` float DEFAULT NULL,
  `other` float DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of point
-- ----------------------------

-- ----------------------------
-- Table structure for sinhvien
-- ----------------------------
DROP TABLE IF EXISTS `sinhvien`;
CREATE TABLE `sinhvien` (
  `MSSV` varchar(10) NOT NULL,
  `gender` varchar(5) DEFAULT NULL,
  `CMND` varchar(15) DEFAULT NULL,
  `gradeId` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`MSSV`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sinhvien
-- ----------------------------

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject` (
  `id` varchar(10) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `room` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of subject
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
