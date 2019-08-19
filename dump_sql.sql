/*
Navicat MySQL Data Transfer

Source Server         : java
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : java

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-08-19 20:46:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of grade
-- ----------------------------
INSERT INTO `grade` VALUES ('10', '18HCB');

-- ----------------------------
-- Table structure for point
-- ----------------------------
DROP TABLE IF EXISTS `point`;
CREATE TABLE `point` (
  `MSSV` varchar(10) DEFAULT NULL,
  `class_name` varchar(10) DEFAULT NULL,
  `middle` float DEFAULT NULL,
  `final` float DEFAULT NULL,
  `other` float DEFAULT NULL,
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `sum` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of point
-- ----------------------------
INSERT INTO `point` VALUES ('1742005', '18HCB_CNTT', '8', '8', '8', '6', '8');
INSERT INTO `point` VALUES ('1842002', '18HCB_CNTT', '4', '5', '6', '7', '5');
INSERT INTO `point` VALUES ('1842003', '18HCB_CNTT', '7', '8', '9', '8', '8.5');
INSERT INTO `point` VALUES ('1842004', '18HCB_CNTT', '2', '4', '6', '9', '4.5');
INSERT INTO `point` VALUES ('1842005', '18HCB_CNTT', '8', '10', '2', '10', '9.5');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `MSSV` varchar(10) DEFAULT NULL,
  `gender` varchar(5) DEFAULT NULL,
  `CMND` varchar(15) DEFAULT NULL,
  `grade_name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=58 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('51', 'Lý V?n F', '1842001', 'Nam', '678912345', '18HCB');
INSERT INTO `student` VALUES ('52', 'Chiêu V?n G', '1842002', 'Nam', '789123456', '18HCB');
INSERT INTO `student` VALUES ('53', 'Tr?n Th? H', '1842003', 'N?', '891234567', '18HCB');
INSERT INTO `student` VALUES ('54', 'M?c V?n I', '1842004', 'Nam', '912345678', '18HCB');
INSERT INTO `student` VALUES ('55', 'V?n Th? J', '1842005', 'N?', '987654321', '18HCB');
INSERT INTO `student` VALUES ('56', 'ABC', '1742005', 'Nu', '123456', '18HCB');
INSERT INTO `student` VALUES ('57', 'Quang', '1700000', 'Nam', '123412', '17HCB');

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject` (
  `id` varchar(10) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `room` varchar(10) DEFAULT NULL,
  `grade_name` varchar(10) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of subject
-- ----------------------------
INSERT INTO `subject` VALUES ('CTT012', 'Ki?m ch?ng ph?n m?m', 'C32', '17HCB');
INSERT INTO `subject` VALUES ('CTT011', 'Thi?t k? giao di?n nâng cao', 'C33', '18HCB');
INSERT INTO `subject` VALUES ('CTT011', 'Thi?t k? giao di?n', 'C32', '17HCB');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=129 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('giaovu', 'giaovu', '1', '83');
INSERT INTO `user` VALUES ('1742005', '1742005', '0', '84');
INSERT INTO `user` VALUES ('giaovu', 'giaovu', '1', '85');
INSERT INTO `user` VALUES ('1742005', '1742005', '0', '86');
INSERT INTO `user` VALUES ('giaovu', 'giaovu', '1', '87');
INSERT INTO `user` VALUES ('1742005', '1742005', '0', '88');
INSERT INTO `user` VALUES ('giaovu', 'giaovu', '1', '89');
INSERT INTO `user` VALUES ('1742005', '1742005', '0', '90');
INSERT INTO `user` VALUES ('giaovu', 'giaovu', '1', '91');
INSERT INTO `user` VALUES ('1742005', '1742005', '0', '92');
INSERT INTO `user` VALUES ('giaovu', 'giaovu', '1', '93');
INSERT INTO `user` VALUES ('1742005', '1742005', '0', '94');
INSERT INTO `user` VALUES ('giaovu', 'giaovu', '1', '95');
INSERT INTO `user` VALUES ('1742005', '1742005', '0', '96');
INSERT INTO `user` VALUES ('giaovu', 'giaovu', '1', '97');
INSERT INTO `user` VALUES ('1742005', '1742005', '0', '98');
INSERT INTO `user` VALUES ('giaovu', 'giaovu', '1', '99');
INSERT INTO `user` VALUES ('1742005', '1742005', '0', '100');
INSERT INTO `user` VALUES ('giaovu', 'giaovu', '1', '101');
INSERT INTO `user` VALUES ('1742005', '1742005', '0', '102');
INSERT INTO `user` VALUES ('giaovu', 'giaovu', '1', '103');
INSERT INTO `user` VALUES ('1742005', '1742005', '0', '104');
INSERT INTO `user` VALUES ('giaovu', 'giaovu', '1', '105');
INSERT INTO `user` VALUES ('1742005', '1742005', '0', '106');
INSERT INTO `user` VALUES ('giaovu', 'giaovu', '1', '107');
INSERT INTO `user` VALUES ('1742005', '1742005', '0', '108');
INSERT INTO `user` VALUES ('giaovu', 'giaovu', '1', '109');
INSERT INTO `user` VALUES ('1742005', '1742005', '0', '110');
INSERT INTO `user` VALUES ('giaovu', 'giaovu', '1', '111');
INSERT INTO `user` VALUES ('1742005', '1742005', '0', '112');
INSERT INTO `user` VALUES ('giaovu', 'giaovu', '1', '113');
INSERT INTO `user` VALUES ('1742005', '1742005', '0', '114');
INSERT INTO `user` VALUES ('giaovu', 'giaovu', '1', '115');
INSERT INTO `user` VALUES ('1742005', '1742005', '0', '116');
INSERT INTO `user` VALUES ('giaovu', 'giaovu', '1', '117');
INSERT INTO `user` VALUES ('1742005', '1742005', '0', '118');
INSERT INTO `user` VALUES ('giaovu', 'giaovu', '1', '119');
INSERT INTO `user` VALUES ('1742005', '1742005', '0', '120');
INSERT INTO `user` VALUES ('giaovu', 'giaovu', '1', '121');
INSERT INTO `user` VALUES ('1742005', '1742005', '0', '122');
INSERT INTO `user` VALUES ('giaovu', 'giaovu', '1', '123');
INSERT INTO `user` VALUES ('1742005', '1742005', '0', '124');
INSERT INTO `user` VALUES ('giaovu', 'giaovu', '1', '125');
INSERT INTO `user` VALUES ('1742005', '1742005', '0', '126');
INSERT INTO `user` VALUES ('giaovu', 'giaovu', '1', '127');
INSERT INTO `user` VALUES ('1742005', '1742005', '0', '128');
