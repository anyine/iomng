/*
Navicat MySQL Data Transfer

Source Server         : vms
Source Server Version : 50613
Source Host           : localhost:3306
Source Database       : vms

Target Server Type    : MYSQL
Target Server Version : 50613
File Encoding         : 65001

Date: 2014-01-04 11:31:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `hibernate_sequences`
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequences`;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of hibernate_sequences
-- ----------------------------
INSERT INTO `hibernate_sequences` VALUES ('t_door', '1');
INSERT INTO `hibernate_sequences` VALUES ('t_controller', '1');
INSERT INTO `hibernate_sequences` VALUES ('t_reader', '3');
INSERT INTO `hibernate_sequences` VALUES ('sa_t_organization', '1');
INSERT INTO `hibernate_sequences` VALUES ('sa_t_user', '6');
INSERT INTO `hibernate_sequences` VALUES ('Record', '23');
INSERT INTO `hibernate_sequences` VALUES ('Person', '32');
INSERT INTO `hibernate_sequences` VALUES ('t_card', '3');
INSERT INTO `hibernate_sequences` VALUES ('Car', '1');
INSERT INTO `hibernate_sequences` VALUES ('t_leave', '20');

-- ----------------------------
-- Table structure for `sa_t_organization`
-- ----------------------------
DROP TABLE IF EXISTS `sa_t_organization`;
CREATE TABLE `sa_t_organization` (
  `id` bigint(20) NOT NULL,
  `index` int(11) DEFAULT NULL,
  `name` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_k8ujwspl3j59b9jf525q8wjsm` (`parent_id`),
  CONSTRAINT `FK_k8ujwspl3j59b9jf525q8wjsm` FOREIGN KEY (`parent_id`) REFERENCES `sa_t_organization` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of sa_t_organization
-- ----------------------------
INSERT INTO `sa_t_organization` VALUES ('0', '0', '根机构', null);
INSERT INTO `sa_t_organization` VALUES ('2', '0', '支队首长', '65536');
INSERT INTO `sa_t_organization` VALUES ('65536', '1', '济宁公安消防支队', '0');
INSERT INTO `sa_t_organization` VALUES ('65537', '1', '司令部', '65536');
INSERT INTO `sa_t_organization` VALUES ('65538', '2', '政治部', '65536');
INSERT INTO `sa_t_organization` VALUES ('65539', '3', '后勤部', '65536');
INSERT INTO `sa_t_organization` VALUES ('65540', '4', '防火部', '65536');
INSERT INTO `sa_t_organization` VALUES ('65541', '5', '特勤中队', '65536');
INSERT INTO `sa_t_organization` VALUES ('98304', '7', '警勤分队', '65536');

-- ----------------------------
-- Table structure for `sa_t_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sa_t_permission`;
CREATE TABLE `sa_t_permission` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sn` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of sa_t_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `sa_t_role`
-- ----------------------------
DROP TABLE IF EXISTS `sa_t_role`;
CREATE TABLE `sa_t_role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sn` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of sa_t_role
-- ----------------------------

-- ----------------------------
-- Table structure for `sa_t_role_sa_t_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sa_t_role_sa_t_permission`;
CREATE TABLE `sa_t_role_sa_t_permission` (
  `sa_t_role_id` bigint(20) NOT NULL,
  `permissions_id` bigint(20) NOT NULL,
  KEY `FK_p4gfp08s3phx9uh31t5emggex` (`permissions_id`),
  KEY `FK_e8ss406inge70m3q01q0kvjlv` (`sa_t_role_id`),
  CONSTRAINT `FK_e8ss406inge70m3q01q0kvjlv` FOREIGN KEY (`sa_t_role_id`) REFERENCES `sa_t_role` (`id`),
  CONSTRAINT `FK_p4gfp08s3phx9uh31t5emggex` FOREIGN KEY (`permissions_id`) REFERENCES `sa_t_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of sa_t_role_sa_t_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `sa_t_user`
-- ----------------------------
DROP TABLE IF EXISTS `sa_t_user`;
CREATE TABLE `sa_t_user` (
  `id` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `email` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` varchar(12) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `realname` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL,
  `username` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `organization_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_j0juytie7lblr8wdok1pkbap1` (`username`),
  KEY `FK_sy1r7u3vmw0vk9vc5luc3p0ks` (`organization_id`),
  CONSTRAINT `FK_sy1r7u3vmw0vk9vc5luc3p0ks` FOREIGN KEY (`organization_id`) REFERENCES `sa_t_organization` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of sa_t_user
-- ----------------------------
INSERT INTO `sa_t_user` VALUES ('1', '2013-11-20 17:29:33', '', '7c4a8d09ca3762af61e59520943dc26494f8941b', '15288843691', '超级管理员', 'enabled', 'admin', '65536');
INSERT INTO `sa_t_user` VALUES ('2', '2013-12-13 11:06:55', '', '7c4a8d09ca3762af61e59520943dc26494f8941b', '13805378543', '谢书华', 'enabled', 'xiesh', '65536');
INSERT INTO `sa_t_user` VALUES ('32768', '2013-11-23 15:59:47', '', '7c4a8d09ca3762af61e59520943dc26494f8941b', '13583729345', '赵书民', 'enabled', 'zhaosm', '65536');
INSERT INTO `sa_t_user` VALUES ('98304', '2013-12-13 17:16:02', '', '7c4a8d09ca3762af61e59520943dc26494f8941b', '18805379119', '孙作利', 'enabled', 'sunzl', '2');
INSERT INTO `sa_t_user` VALUES ('98305', '2013-12-13 17:17:18', '', '7c4a8d09ca3762af61e59520943dc26494f8941b', '15705375577', '郑田申', 'enabled', 'zhengts', '2');
INSERT INTO `sa_t_user` VALUES ('98306', '2013-12-13 17:18:02', '', '7c4a8d09ca3762af61e59520943dc26494f8941b', '15910019511', '王海洋', 'enabled', 'wanghy', '2');
INSERT INTO `sa_t_user` VALUES ('98307', '2013-12-13 17:19:50', '', '7c4a8d09ca3762af61e59520943dc26494f8941b', '13792369996', '时勇', 'enabled', 'shiy', '2');
INSERT INTO `sa_t_user` VALUES ('131072', '2013-12-13 17:55:14', '', '7c4a8d09ca3762af61e59520943dc26494f8941b', '15562375677', '李峰', 'enabled', 'lif', '2');
INSERT INTO `sa_t_user` VALUES ('131073', '2013-12-13 17:56:32', '', '7c4a8d09ca3762af61e59520943dc26494f8941b', '13853795606', '徐卫', 'enabled', 'xuw', '65539');
INSERT INTO `sa_t_user` VALUES ('131074', '2013-12-13 17:57:27', '', '7c4a8d09ca3762af61e59520943dc26494f8941b', '13853705988', '宋连旭', 'enabled', 'songlx', '65540');
INSERT INTO `sa_t_user` VALUES ('131075', '2013-12-13 17:58:13', '', '7c4a8d09ca3762af61e59520943dc26494f8941b', '13863767900', '张斌', 'enabled', 'zhangb', '65537');
INSERT INTO `sa_t_user` VALUES ('131076', '2013-12-13 17:59:04', '', '7c4a8d09ca3762af61e59520943dc26494f8941b', '15020799219', '薛启迪', 'enabled', 'xueqd', '65539');
INSERT INTO `sa_t_user` VALUES ('163840', '2013-12-15 17:10:03', '', '7c4a8d09ca3762af61e59520943dc26494f8941b', '18678797012', '井源', 'enabled', 'jingy', '65538');

-- ----------------------------
-- Table structure for `sa_t_user_sa_t_role`
-- ----------------------------
DROP TABLE IF EXISTS `sa_t_user_sa_t_role`;
CREATE TABLE `sa_t_user_sa_t_role` (
  `sa_t_user_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL,
  KEY `FK_d25962c8fwqmwea09r5cvs486` (`roles_id`),
  KEY `FK_li4mqqv6o717jed0hxq1c8lsh` (`sa_t_user_id`),
  CONSTRAINT `FK_d25962c8fwqmwea09r5cvs486` FOREIGN KEY (`roles_id`) REFERENCES `sa_t_role` (`id`),
  CONSTRAINT `FK_li4mqqv6o717jed0hxq1c8lsh` FOREIGN KEY (`sa_t_user_id`) REFERENCES `sa_t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of sa_t_user_sa_t_role
-- ----------------------------

-- ----------------------------
-- Table structure for `t_card`
-- ----------------------------
DROP TABLE IF EXISTS `t_card`;
CREATE TABLE `t_card` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cardType` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `number` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_fv3muenbn114v89v47h4562m4` (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=65871 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of t_card
-- ----------------------------
INSERT INTO `t_card` VALUES ('1', 'PERSON', '9935502');
INSERT INTO `t_card` VALUES ('2', 'PERSON', '5787953');
INSERT INTO `t_card` VALUES ('3', 'PERSON', '7088268');
INSERT INTO `t_card` VALUES ('4', 'PERSON', '6995707');
INSERT INTO `t_card` VALUES ('5', 'PERSON', '7607110');
INSERT INTO `t_card` VALUES ('6', 'PERSON', '7608151');
INSERT INTO `t_card` VALUES ('7', 'PERSON', '7602915');
INSERT INTO `t_card` VALUES ('8', 'PERSON', '7143485');
INSERT INTO `t_card` VALUES ('9', 'PERSON', '7143390');
INSERT INTO `t_card` VALUES ('10', 'PERSON', '7143385');
INSERT INTO `t_card` VALUES ('11', 'PERSON', '7143506');
INSERT INTO `t_card` VALUES ('12', 'PERSON', '9481536');
INSERT INTO `t_card` VALUES ('13', 'PERSON', '7605601');
INSERT INTO `t_card` VALUES ('14', 'PERSON', '7596593');
INSERT INTO `t_card` VALUES ('15', 'PERSON', '7601365');
INSERT INTO `t_card` VALUES ('16', 'PERSON', '7143537');
INSERT INTO `t_card` VALUES ('17', 'PERSON', '7143563');
INSERT INTO `t_card` VALUES ('18', 'PERSON', '7143532');
INSERT INTO `t_card` VALUES ('21', 'PERSON', '9481553');
INSERT INTO `t_card` VALUES ('22', 'PERSON', '7505006');
INSERT INTO `t_card` VALUES ('23', 'PERSON', '10740729');
INSERT INTO `t_card` VALUES ('24', 'PERSON', '7504986');
INSERT INTO `t_card` VALUES ('25', 'PERSON', '9696557');
INSERT INTO `t_card` VALUES ('27', 'PERSON', '8819506');
INSERT INTO `t_card` VALUES ('28', 'PERSON', '7449517');
INSERT INTO `t_card` VALUES ('29', 'PERSON', '7449524');
INSERT INTO `t_card` VALUES ('30', 'PERSON', '9696554');
INSERT INTO `t_card` VALUES ('31', 'PERSON', '9696580');
INSERT INTO `t_card` VALUES ('32', 'PERSON', '7449491');
INSERT INTO `t_card` VALUES ('33', 'PERSON', '7149958');
INSERT INTO `t_card` VALUES ('34', 'PERSON', '7149945');
INSERT INTO `t_card` VALUES ('35', 'PERSON', '7149971');
INSERT INTO `t_card` VALUES ('36', 'PERSON', '7149932');
INSERT INTO `t_card` VALUES ('37', 'PERSON', '7149984');
INSERT INTO `t_card` VALUES ('38', 'PERSON', '7149919');
INSERT INTO `t_card` VALUES ('39', 'PERSON', '7154645');
INSERT INTO `t_card` VALUES ('40', 'PERSON', '7149906');
INSERT INTO `t_card` VALUES ('41', 'PERSON', '7154658');
INSERT INTO `t_card` VALUES ('42', 'PERSON', '7149893');
INSERT INTO `t_card` VALUES ('43', 'PERSON', '9481527');
INSERT INTO `t_card` VALUES ('44', 'PERSON', '7598282');
INSERT INTO `t_card` VALUES ('45', 'PERSON', '9933181');
INSERT INTO `t_card` VALUES ('47', 'CAR', '2455251');
INSERT INTO `t_card` VALUES ('48', 'CAR', '2447105');
INSERT INTO `t_card` VALUES ('49', 'CAR', '2448242');
INSERT INTO `t_card` VALUES ('50', 'CAR', '2654909');
INSERT INTO `t_card` VALUES ('51', 'CAR', '2610447');
INSERT INTO `t_card` VALUES ('52', 'CAR', '2605244');
INSERT INTO `t_card` VALUES ('53', 'CAR', '2705547');
INSERT INTO `t_card` VALUES ('54', 'CAR', '2454713');
INSERT INTO `t_card` VALUES ('55', 'CAR', '2448224');
INSERT INTO `t_card` VALUES ('56', 'CAR', '2454697');
INSERT INTO `t_card` VALUES ('57', 'CAR', '2441157');
INSERT INTO `t_card` VALUES ('58', 'CAR', '2447570');
INSERT INTO `t_card` VALUES ('59', 'CAR', '2452571');
INSERT INTO `t_card` VALUES ('60', 'CAR', '2454946');
INSERT INTO `t_card` VALUES ('61', 'CAR', '2454459');
INSERT INTO `t_card` VALUES ('62', 'CAR', '2765223');
INSERT INTO `t_card` VALUES ('63', 'CAR', '2455449');
INSERT INTO `t_card` VALUES ('64', 'CAR', '2452450');
INSERT INTO `t_card` VALUES ('65', 'CAR', '2452439');
INSERT INTO `t_card` VALUES ('66', 'CAR', '2452927');
INSERT INTO `t_card` VALUES ('67', 'CAR', '2447486');
INSERT INTO `t_card` VALUES ('68', 'CAR', '2428411');
INSERT INTO `t_card` VALUES ('69', 'CAR', '2452726');
INSERT INTO `t_card` VALUES ('70', 'CAR', '2656750');
INSERT INTO `t_card` VALUES ('71', 'CAR', '2656933');
INSERT INTO `t_card` VALUES ('72', 'CAR', '2449355');
INSERT INTO `t_card` VALUES ('73', 'CAR', '2447855');
INSERT INTO `t_card` VALUES ('74', 'CAR', '2637269');
INSERT INTO `t_card` VALUES ('75', 'CAR', '2624348');
INSERT INTO `t_card` VALUES ('76', 'CAR', '2624073');
INSERT INTO `t_card` VALUES ('77', 'CAR', '2456338');
INSERT INTO `t_card` VALUES ('78', 'CAR', '2455242');
INSERT INTO `t_card` VALUES ('79', 'CAR', '2733241');
INSERT INTO `t_card` VALUES ('80', 'CAR', '2452171');
INSERT INTO `t_card` VALUES ('81', 'CAR', '2514903');
INSERT INTO `t_card` VALUES ('32771', 'PERSON', '5853543');
INSERT INTO `t_card` VALUES ('32772', 'PERSON', '7143364');
INSERT INTO `t_card` VALUES ('65870', 'PERSON', '9460499');

-- ----------------------------
-- Table structure for `t_car_type`
-- ----------------------------
DROP TABLE IF EXISTS `t_car_type`;
CREATE TABLE `t_car_type` (
  `id` bigint(20) NOT NULL,
  `name` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_dltbu6u5nix3wep1wpofwktwe` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of t_car_type
-- ----------------------------
INSERT INTO `t_car_type` VALUES ('2', '执勤车');
INSERT INTO `t_car_type` VALUES ('3', '消防车');
INSERT INTO `t_car_type` VALUES ('1', '默认类型');

-- ----------------------------
-- Table structure for `t_controller`
-- ----------------------------
DROP TABLE IF EXISTS `t_controller`;
CREATE TABLE `t_controller` (
  `id` bigint(20) NOT NULL,
  `ip` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `sn` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `door_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_k43u6e2th1jg5qn1h181jtk2m` (`ip`),
  UNIQUE KEY `UK_opfyxopyoptth2rd0qagwf4qg` (`sn`),
  KEY `FK_1irrnet40nfb8v9ab0eenou6c` (`door_id`),
  CONSTRAINT `FK_1irrnet40nfb8v9ab0eenou6c` FOREIGN KEY (`door_id`) REFERENCES `t_door` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_controller
-- ----------------------------
INSERT INTO `t_controller` VALUES ('1', '10.48.79.9', '122118323', '1');
INSERT INTO `t_controller` VALUES ('2', '10.48.79.8', '122118309', '1');
INSERT INTO `t_controller` VALUES ('3', '10.48.79.6', '122114997', '1');
INSERT INTO `t_controller` VALUES ('4', '10.48.79.7', '122118296', '1');

-- ----------------------------
-- Table structure for `t_dispatch_car_form`
-- ----------------------------
DROP TABLE IF EXISTS `t_dispatch_car_form`;
CREATE TABLE `t_dispatch_car_form` (
  `id` bigint(20) NOT NULL,
  `agree` tinyint(1) DEFAULT NULL,
  `approvePerson` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `approve_time` datetime DEFAULT NULL,
  `carManager` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `driver` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `line` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `reason` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `target` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `car_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_dispatch_car_form
-- ----------------------------

-- ----------------------------
-- Table structure for `t_door`
-- ----------------------------
DROP TABLE IF EXISTS `t_door`;
CREATE TABLE `t_door` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `organization_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_mptuyuunm1nl4bvv7yy7nadx4` (`organization_id`),
  CONSTRAINT `FK_mptuyuunm1nl4bvv7yy7nadx4` FOREIGN KEY (`organization_id`) REFERENCES `sa_t_organization` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_door
-- ----------------------------
INSERT INTO `t_door` VALUES ('1', '大门', '65536');

-- ----------------------------
-- Table structure for `t_inner_car`
-- ----------------------------
DROP TABLE IF EXISTS `t_inner_car`;
CREATE TABLE `t_inner_car` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `license` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `car_id` bigint(20) DEFAULT NULL,
  `level` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `notify_id` bigint(20) DEFAULT NULL,
  `organization_id` bigint(20) DEFAULT NULL,
  `car_type_id` bigint(20) NOT NULL DEFAULT '1',
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r4sdxtyvfsonf9g1owhsjho0q` (`license`),
  KEY `FK_2cbg0se1aiwf3raqv7kkxjcoj` (`notify_id`),
  KEY `FK_7x2t8uhtbj0ae6atxqgjyjkng` (`organization_id`),
  KEY `FK_pytwejsmdx5albd6ie21dm9jm` (`car_type_id`),
  KEY `FK_6ukb7qmicayhc07uo0q56lyw4` (`user_id`),
  KEY `FK_ktrss9ywxhbs6h0v9y7vqwx2h` (`car_id`),
  CONSTRAINT `FK_2cbg0se1aiwf3raqv7kkxjcoj` FOREIGN KEY (`notify_id`) REFERENCES `t_notify` (`id`),
  CONSTRAINT `FK_6ukb7qmicayhc07uo0q56lyw4` FOREIGN KEY (`user_id`) REFERENCES `sa_t_user` (`id`),
  CONSTRAINT `FK_7x2t8uhtbj0ae6atxqgjyjkng` FOREIGN KEY (`organization_id`) REFERENCES `sa_t_organization` (`id`),
  CONSTRAINT `FK_ktrss9ywxhbs6h0v9y7vqwx2h` FOREIGN KEY (`car_id`) REFERENCES `t_card` (`id`),
  CONSTRAINT `FK_pytwejsmdx5albd6ie21dm9jm` FOREIGN KEY (`car_type_id`) REFERENCES `t_car_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2514904 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of t_inner_car
-- ----------------------------
INSERT INTO `t_inner_car` VALUES ('2', '2013-12-11 11:19:31', 'WJ鲁6772X', 'IN', '48', 'direct', '1', '65536', '1', '1');
INSERT INTO `t_inner_car` VALUES ('3', '2013-12-11 11:19:31', 'WJ鲁0812X', 'IN', '49', 'direct', '1', '65536', '1', '131075');
INSERT INTO `t_inner_car` VALUES ('4', '2013-12-11 11:19:31', 'WJ鲁0855X', null, '50', null, '1', null, '1', null);
INSERT INTO `t_inner_car` VALUES ('5', '2013-12-11 11:19:31', 'WJ鲁6731X', null, '51', null, '1', null, '1', null);
INSERT INTO `t_inner_car` VALUES ('6', '2013-12-11 11:19:31', 'WJ鲁6735X', null, '52', null, '1', null, '1', null);
INSERT INTO `t_inner_car` VALUES ('7', '2013-12-11 11:19:31', 'WJ鲁6746X', null, '53', null, '1', null, '1', null);
INSERT INTO `t_inner_car` VALUES ('8', '2013-12-11 11:19:31', 'WJ鲁6711X', null, '54', null, '1', null, '1', null);
INSERT INTO `t_inner_car` VALUES ('9', '2013-12-11 11:19:31', '鲁HCB596', null, '55', null, '1', null, '1', null);
INSERT INTO `t_inner_car` VALUES ('10', '2013-12-11 11:19:31', 'WJ鲁0801X', 'OUT', '56', 'direct', '1', '65536', '1', '98304');
INSERT INTO `t_inner_car` VALUES ('11', '2013-12-11 11:19:31', 'WJ鲁0802X', 'IN', '57', 'direct', '1', '65536', '1', '98305');
INSERT INTO `t_inner_car` VALUES ('12', '2013-12-11 11:19:31', 'WJ鲁0803X', 'OUT', '58', 'direct', '1', '65536', '1', '98307');
INSERT INTO `t_inner_car` VALUES ('13', '2013-12-11 11:19:31', 'WJ鲁0805X', 'OUT', '59', 'direct', '1', '65536', '1', '98306');
INSERT INTO `t_inner_car` VALUES ('14', '2013-12-11 11:19:31', 'WJ鲁0806X', 'IN', '60', 'direct', '1', '2', '1', '131072');
INSERT INTO `t_inner_car` VALUES ('15', '2013-12-11 11:19:31', 'WJ鲁0808X', 'OUT', '61', 'direct', '1', '65536', '1', '163840');
INSERT INTO `t_inner_car` VALUES ('16', '2013-12-11 11:19:31', 'WJ鲁0809X', 'OUT', '62', 'direct', '1', '65536', '1', '131073');
INSERT INTO `t_inner_car` VALUES ('17', '2013-12-11 11:19:31', 'WJ鲁0810X', 'OUT', '63', 'direct', '1', '65536', '1', '131074');
INSERT INTO `t_inner_car` VALUES ('18', '2013-12-11 11:19:31', 'WJ鲁0831X', 'IN', '64', 'direct', '1', '65539', '1', '131073');
INSERT INTO `t_inner_car` VALUES ('19', '2013-12-11 11:19:31', 'WJ鲁6758X', null, '65', null, '1', null, '1', null);
INSERT INTO `t_inner_car` VALUES ('20', '2013-12-11 11:19:31', 'WJ鲁6759X', null, '66', null, '1', null, '1', null);
INSERT INTO `t_inner_car` VALUES ('21', '2013-12-11 11:19:31', 'WJ鲁6700X', null, '67', null, '1', null, '1', null);
INSERT INTO `t_inner_car` VALUES ('22', '2013-12-11 11:19:31', 'WJ鲁6714X', null, '68', null, '1', null, '1', null);
INSERT INTO `t_inner_car` VALUES ('23', '2013-12-11 11:19:31', 'WJ鲁6765X', null, '69', null, '1', null, '1', null);
INSERT INTO `t_inner_car` VALUES ('24', '2013-12-11 11:19:31', '鲁H3901警', 'IN', '70', 'direct', '1', '65540', '1', '131074');
INSERT INTO `t_inner_car` VALUES ('25', '2013-12-11 11:19:31', '鲁H3905警', 'IN', '71', 'direct', '1', '65540', '1', '131074');
INSERT INTO `t_inner_car` VALUES ('26', '2013-12-11 11:19:31', '鲁H3906警', 'IN', '72', 'direct', '1', '65540', '1', '131074');
INSERT INTO `t_inner_car` VALUES ('27', '2013-12-11 11:19:31', 'WJ鲁0816X', 'IN', '73', 'direct', '1', '65540', '1', '131074');
INSERT INTO `t_inner_car` VALUES ('28', '2013-12-11 11:19:31', 'WJ鲁0822X', 'IN', '74', 'direct', '1', '65538', '1', '163840');
INSERT INTO `t_inner_car` VALUES ('29', '2013-12-11 11:19:31', 'WJ鲁0813X', 'IN', '75', 'direct', '1', '65536', '1', '131073');
INSERT INTO `t_inner_car` VALUES ('30', '2013-12-11 11:19:31', '鲁HMC119', 'IN', '76', null, '1', null, '1', null);
INSERT INTO `t_inner_car` VALUES ('31', '2013-12-11 11:19:31', '鲁H3661C', null, '77', null, '1', null, '1', null);
INSERT INTO `t_inner_car` VALUES ('32', '2013-12-11 11:19:31', 'WJ鲁6777X', 'IN', '78', null, '1', null, '1', null);
INSERT INTO `t_inner_car` VALUES ('33', '2013-12-11 11:19:31', '鲁HXF377', 'OUT', '79', null, '1', null, '1', null);
INSERT INTO `t_inner_car` VALUES ('34', '2013-12-11 11:19:31', '鲁HU1855', 'IN', '80', null, '1', null, '1', null);
INSERT INTO `t_inner_car` VALUES ('2514903', '2013-12-11 11:19:31', 'WJ鲁0807X', 'OUT', '47', 'direct', '1', '2', '1', '2');

-- ----------------------------
-- Table structure for `t_inner_car_record`
-- ----------------------------
DROP TABLE IF EXISTS `t_inner_car_record`;
CREATE TABLE `t_inner_car_record` (
  `id` bigint(20) NOT NULL,
  `date` datetime DEFAULT NULL,
  `type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `door_id` bigint(20) NOT NULL,
  `status` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `car_id` bigint(20) DEFAULT NULL,
  `form_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_h8n0j2t00bl9aogu9s0o9vofj` (`form_id`),
  KEY `FK_73p60c5y1aq7bhuqy8x45dfjy` (`door_id`),
  CONSTRAINT `FK_73p60c5y1aq7bhuqy8x45dfjy` FOREIGN KEY (`door_id`) REFERENCES `t_door` (`id`),
  CONSTRAINT `FK_h8n0j2t00bl9aogu9s0o9vofj` FOREIGN KEY (`form_id`) REFERENCES `t_dispatch_car_form` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_inner_car_record
-- ----------------------------
INSERT INTO `t_inner_car_record` VALUES ('65536', '2013-12-13 15:38:21', 'IN', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('65537', '2013-12-13 15:38:22', 'IN', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('65538', '2013-12-13 15:38:28', 'IN', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('65539', '2013-12-13 15:38:32', 'IN', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('98305', '2013-12-13 16:25:51', 'IN', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('131079', '2013-12-13 17:44:59', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('131080', '2013-12-13 17:45:48', 'OUT', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('131081', '2013-12-13 17:46:49', 'IN', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('163840', '2013-12-13 17:56:09', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('163842', '2013-12-13 18:20:20', 'OUT', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('163843', '2013-12-13 19:24:44', 'IN', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('163845', '2013-12-13 20:04:48', 'IN', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('163847', '2013-12-14 10:05:10', 'IN', '1', 'normal', '33', null);
INSERT INTO `t_inner_car_record` VALUES ('163848', '2013-12-14 10:05:35', 'OUT', '1', 'normal', '33', null);
INSERT INTO `t_inner_car_record` VALUES ('163849', '2013-12-14 10:07:11', 'IN', '1', 'normal', '34', null);
INSERT INTO `t_inner_car_record` VALUES ('163850', '2013-12-14 10:07:17', 'IN', '1', 'normal', '33', null);
INSERT INTO `t_inner_car_record` VALUES ('163851', '2013-12-14 10:07:27', 'OUT', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('163852', '2013-12-14 10:07:29', 'IN', '1', 'normal', '33', null);
INSERT INTO `t_inner_car_record` VALUES ('163853', '2013-12-14 10:07:30', 'IN', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('163855', '2013-12-14 10:07:30', 'OUT', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('229376', '2013-12-14 12:30:47', 'IN', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('262144', '2013-12-14 12:43:02', 'IN', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('262145', '2013-12-14 12:43:49', 'OUT', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('262147', '2013-12-14 13:25:01', 'IN', '1', 'normal', '34', null);
INSERT INTO `t_inner_car_record` VALUES ('262150', '2013-12-14 15:37:30', 'IN', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('262151', '2013-12-14 15:38:48', 'OUT', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('262153', '2013-12-14 17:23:29', 'IN', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('294912', '2013-12-15 16:51:50', 'OUT', '1', 'normal', '17', null);
INSERT INTO `t_inner_car_record` VALUES ('294918', '2013-12-15 17:51:51', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('294923', '2013-12-15 18:41:33', 'OUT', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('294925', '2013-12-15 18:45:21', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('294926', '2013-12-15 18:58:01', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('294927', '2013-12-15 19:03:52', 'IN', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('294929', '2013-12-15 19:04:18', 'OUT', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('294930', '2013-12-15 20:21:56', 'OUT', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('294931', '2013-12-15 20:27:55', 'IN', '1', 'normal', '34', null);
INSERT INTO `t_inner_car_record` VALUES ('294932', '2013-12-15 21:06:52', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('294935', '2013-12-15 21:19:12', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('294936', '2013-12-15 22:25:16', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('294937', '2013-12-15 23:27:23', 'OUT', '1', 'normal', '34', null);
INSERT INTO `t_inner_car_record` VALUES ('294938', '2013-12-16 01:54:48', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('294939', '2013-12-16 02:39:40', 'IN', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('294940', '2013-12-16 05:36:13', 'IN', '1', 'normal', '34', null);
INSERT INTO `t_inner_car_record` VALUES ('294942', '2013-12-16 07:40:05', 'IN', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('294945', '2013-12-16 08:17:24', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('294946', '2013-12-16 08:23:30', 'IN', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('294947', '2013-12-16 08:23:59', 'IN', '1', 'normal', '30', null);
INSERT INTO `t_inner_car_record` VALUES ('294948', '2013-12-16 08:37:43', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('294949', '2013-12-16 09:12:24', 'IN', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('294950', '2013-12-16 09:37:40', 'IN', '1', 'normal', '34', null);
INSERT INTO `t_inner_car_record` VALUES ('294951', '2013-12-16 09:49:46', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('294952', '2013-12-16 09:49:48', 'OUT', '1', 'normal', '34', null);
INSERT INTO `t_inner_car_record` VALUES ('294953', '2013-12-16 10:28:47', 'OUT', '1', 'normal', '17', null);
INSERT INTO `t_inner_car_record` VALUES ('294956', '2013-12-16 12:43:34', 'IN', '1', 'normal', '34', null);
INSERT INTO `t_inner_car_record` VALUES ('294957', '2013-12-16 13:34:21', 'OUT', '1', 'normal', '34', null);
INSERT INTO `t_inner_car_record` VALUES ('294958', '2013-12-16 14:00:06', 'OUT', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('294959', '2013-12-16 14:13:03', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('294960', '2013-12-16 15:01:30', 'OUT', '1', 'normal', '15', null);
INSERT INTO `t_inner_car_record` VALUES ('327680', '2013-12-16 17:09:06', 'IN', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('327682', '2013-12-16 17:23:35', 'OUT', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('327683', '2013-12-16 17:39:51', 'OUT', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('327684', '2013-12-16 18:11:44', 'IN', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('393219', '2013-12-16 21:40:20', 'IN', '1', 'normal', '34', null);
INSERT INTO `t_inner_car_record` VALUES ('393220', '2013-12-17 07:06:56', 'OUT', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('393223', '2013-12-17 07:52:01', 'IN', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('393225', '2013-12-17 07:58:17', 'IN', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('393226', '2013-12-17 08:00:44', 'OUT', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('393227', '2013-12-17 09:10:38', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('393228', '2013-12-17 09:10:45', 'IN', '1', 'normal', '30', null);
INSERT INTO `t_inner_car_record` VALUES ('393229', '2013-12-17 09:10:51', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('393230', '2013-12-17 09:10:55', 'IN', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('393231', '2013-12-17 09:24:11', 'OUT', '1', 'normal', '29', null);
INSERT INTO `t_inner_car_record` VALUES ('393232', '2013-12-17 09:27:04', 'IN', '1', 'normal', '34', null);
INSERT INTO `t_inner_car_record` VALUES ('393233', '2013-12-17 09:43:36', 'OUT', '1', 'normal', '34', null);
INSERT INTO `t_inner_car_record` VALUES ('393234', '2013-12-17 10:05:40', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('393235', '2013-12-17 10:15:35', 'IN', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('393236', '2013-12-17 10:38:59', 'IN', '1', 'normal', '29', null);
INSERT INTO `t_inner_car_record` VALUES ('393237', '2013-12-17 10:41:33', 'OUT', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('393238', '2013-12-17 11:26:05', 'IN', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('393239', '2013-12-17 11:41:48', 'IN', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('393240', '2013-12-17 11:41:52', 'OUT', '1', 'normal', '30', null);
INSERT INTO `t_inner_car_record` VALUES ('393241', '2013-12-17 12:20:21', 'IN', '1', 'normal', '30', null);
INSERT INTO `t_inner_car_record` VALUES ('393242', '2013-12-17 12:31:30', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('393243', '2013-12-17 12:59:14', 'IN', '1', 'normal', '34', null);
INSERT INTO `t_inner_car_record` VALUES ('393244', '2013-12-17 13:08:19', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('393245', '2013-12-17 13:57:11', 'OUT', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('393246', '2013-12-17 14:16:27', 'OUT', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('393247', '2013-12-17 15:31:17', 'OUT', '1', 'normal', '34', null);
INSERT INTO `t_inner_car_record` VALUES ('393248', '2013-12-17 15:44:14', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('458760', '2013-12-19 07:08:37', 'OUT', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('458762', '2013-12-19 07:16:50', 'OUT', '1', 'normal', '17', null);
INSERT INTO `t_inner_car_record` VALUES ('458765', '2013-12-19 07:34:25', 'IN', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('458766', '2013-12-19 07:49:48', 'OUT', '1', 'normal', '29', null);
INSERT INTO `t_inner_car_record` VALUES ('458767', '2013-12-19 08:07:19', 'IN', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('458768', '2013-12-19 08:19:57', 'OUT', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('458769', '2013-12-19 08:22:45', 'OUT', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('458770', '2013-12-19 08:23:36', 'IN', '1', 'normal', '30', null);
INSERT INTO `t_inner_car_record` VALUES ('458771', '2013-12-19 08:28:49', 'IN', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('458772', '2013-12-19 08:40:15', 'IN', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('458773', '2013-12-19 09:06:00', 'OUT', '1', 'normal', '2', null);
INSERT INTO `t_inner_car_record` VALUES ('458774', '2013-12-19 09:10:34', 'IN', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('458775', '2013-12-19 10:07:50', 'OUT', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('458776', '2013-12-19 11:04:24', 'IN', '1', 'normal', '29', null);
INSERT INTO `t_inner_car_record` VALUES ('458778', '2013-12-19 11:49:43', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('458779', '2013-12-19 11:52:41', 'IN', '1', 'normal', '2', null);
INSERT INTO `t_inner_car_record` VALUES ('458780', '2013-12-19 12:03:28', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('458782', '2013-12-19 12:14:17', 'IN', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('458783', '2013-12-19 12:37:57', 'IN', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('458784', '2013-12-19 13:19:50', 'IN', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('458785', '2013-12-19 13:53:24', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('458786', '2013-12-19 13:59:03', 'OUT', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('458788', '2013-12-19 14:16:13', 'OUT', '1', 'normal', '17', null);
INSERT INTO `t_inner_car_record` VALUES ('458789', '2013-12-19 14:28:54', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('458790', '2013-12-19 14:40:44', 'OUT', '1', 'normal', '17', null);
INSERT INTO `t_inner_car_record` VALUES ('458791', '2013-12-19 15:26:56', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('458792', '2013-12-19 16:30:30', 'IN', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('491524', '2013-12-19 18:50:17', 'OUT', '1', 'normal', '17', null);
INSERT INTO `t_inner_car_record` VALUES ('491525', '2013-12-19 19:57:02', 'IN', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('491527', '2013-12-19 21:19:09', 'OUT', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('491528', '2013-12-19 21:48:17', 'OUT', '1', 'normal', '17', null);
INSERT INTO `t_inner_car_record` VALUES ('491529', '2013-12-19 23:00:20', 'IN', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('491530', '2013-12-19 23:03:45', 'IN', '1', 'normal', '2', null);
INSERT INTO `t_inner_car_record` VALUES ('491534', '2013-12-20 07:34:00', 'IN', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('491536', '2013-12-20 08:24:17', 'OUT', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('491537', '2013-12-20 08:31:02', 'IN', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('491538', '2013-12-20 09:47:52', 'IN', '1', 'normal', '34', null);
INSERT INTO `t_inner_car_record` VALUES ('491539', '2013-12-20 10:10:38', 'IN', '1', 'normal', '34', null);
INSERT INTO `t_inner_car_record` VALUES ('491541', '2013-12-20 10:56:21', 'IN', '1', 'normal', '34', null);
INSERT INTO `t_inner_car_record` VALUES ('491542', '2013-12-20 11:45:31', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('491543', '2013-12-20 12:18:09', 'IN', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('491544', '2013-12-20 14:29:23', 'OUT', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('491545', '2013-12-20 15:07:36', 'IN', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('491546', '2013-12-20 15:10:02', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('491548', '2013-12-20 15:51:04', 'IN', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('491549', '2013-12-20 17:24:56', 'IN', '1', 'normal', '29', null);
INSERT INTO `t_inner_car_record` VALUES ('491550', '2013-12-20 17:36:04', 'OUT', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('491552', '2013-12-20 17:49:40', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('557061', '2013-12-21 10:17:04', 'OUT', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('557062', '2013-12-21 10:17:57', 'IN', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('557063', '2013-12-21 10:20:58', 'OUT', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('557065', '2013-12-21 10:44:31', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('557066', '2013-12-21 10:47:47', 'IN', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('557067', '2013-12-21 10:59:27', 'OUT', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('557068', '2013-12-21 11:05:03', 'IN', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('557069', '2013-12-21 11:09:48', 'OUT', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('557070', '2013-12-21 11:14:19', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('557071', '2013-12-21 12:02:18', 'OUT', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('557072', '2013-12-21 12:22:36', 'IN', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('557074', '2013-12-21 14:08:47', 'IN', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('557076', '2013-12-21 15:17:26', 'OUT', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('557077', '2013-12-21 15:30:05', 'IN', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('557078', '2013-12-21 16:09:08', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('557080', '2013-12-21 17:27:09', 'OUT', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('557081', '2013-12-21 19:55:12', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('557082', '2013-12-22 09:33:50', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('557083', '2013-12-22 09:54:54', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('557084', '2013-12-22 11:05:36', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('557085', '2013-12-22 11:42:48', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('557088', '2013-12-22 14:37:32', 'IN', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('557091', '2013-12-22 17:32:40', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('557092', '2013-12-22 19:49:38', 'IN', '1', 'normal', '34', null);
INSERT INTO `t_inner_car_record` VALUES ('557096', '2013-12-23 06:56:16', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('557097', '2013-12-23 07:41:01', 'IN', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('557100', '2013-12-23 08:01:57', 'IN', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('557101', '2013-12-23 08:22:01', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('557103', '2013-12-23 08:53:33', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('557104', '2013-12-23 08:56:35', 'IN', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('557105', '2013-12-23 08:58:40', 'OUT', '1', 'normal', '15', null);
INSERT INTO `t_inner_car_record` VALUES ('557106', '2013-12-23 09:03:43', 'OUT', '1', 'normal', '17', null);
INSERT INTO `t_inner_car_record` VALUES ('557107', '2013-12-23 09:07:05', 'IN', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('557108', '2013-12-23 09:11:45', 'OUT', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('557109', '2013-12-23 09:13:24', 'OUT', '1', 'normal', '34', null);
INSERT INTO `t_inner_car_record` VALUES ('557110', '2013-12-23 09:21:11', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('557111', '2013-12-23 09:22:44', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('557112', '2013-12-23 09:27:21', 'IN', '1', 'normal', '34', null);
INSERT INTO `t_inner_car_record` VALUES ('557113', '2013-12-23 09:32:44', 'OUT', '1', 'normal', '29', null);
INSERT INTO `t_inner_car_record` VALUES ('557114', '2013-12-23 09:43:57', 'OUT', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('557115', '2013-12-23 09:50:57', 'OUT', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('557116', '2013-12-23 09:56:22', 'OUT', '1', 'normal', '2', null);
INSERT INTO `t_inner_car_record` VALUES ('557117', '2013-12-23 10:00:57', 'IN', '1', 'normal', '29', null);
INSERT INTO `t_inner_car_record` VALUES ('557118', '2013-12-23 10:03:25', 'OUT', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('557119', '2013-12-23 10:15:41', 'OUT', '1', 'normal', '34', null);
INSERT INTO `t_inner_car_record` VALUES ('557120', '2013-12-23 10:23:47', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('557122', '2013-12-23 10:58:04', 'IN', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('557124', '2013-12-23 11:27:02', 'IN', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('557125', '2013-12-23 11:29:23', 'IN', '1', 'normal', '2', null);
INSERT INTO `t_inner_car_record` VALUES ('557126', '2013-12-23 11:45:51', 'IN', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('557128', '2013-12-23 13:36:28', 'IN', '1', 'normal', '34', null);
INSERT INTO `t_inner_car_record` VALUES ('557129', '2013-12-23 14:44:37', 'IN', '1', 'normal', '2', null);
INSERT INTO `t_inner_car_record` VALUES ('557130', '2013-12-23 15:33:59', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('557131', '2013-12-23 15:42:01', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('557132', '2013-12-23 16:05:36', 'IN', '1', 'normal', '2', null);
INSERT INTO `t_inner_car_record` VALUES ('557133', '2013-12-23 16:37:12', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('557134', '2013-12-23 16:47:23', 'OUT', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('557135', '2013-12-23 16:53:53', 'OUT', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('557136', '2013-12-23 17:03:21', 'IN', '1', 'normal', '2', null);
INSERT INTO `t_inner_car_record` VALUES ('557137', '2013-12-23 17:04:05', 'IN', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('557138', '2013-12-23 17:22:02', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('557140', '2013-12-23 17:47:00', 'IN', '1', 'normal', '30', null);
INSERT INTO `t_inner_car_record` VALUES ('557142', '2013-12-23 18:05:00', 'OUT', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('557145', '2013-12-23 18:24:38', 'IN', '1', 'normal', '2', null);
INSERT INTO `t_inner_car_record` VALUES ('557149', '2013-12-23 20:50:12', 'IN', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('589824', '2013-12-24 08:51:36', 'IN', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('589825', '2013-12-24 09:08:28', 'OUT', '1', 'normal', '33', null);
INSERT INTO `t_inner_car_record` VALUES ('589826', '2013-12-24 09:20:51', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('589828', '2013-12-24 09:54:22', 'IN', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('589829', '2013-12-24 10:36:46', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('589830', '2013-12-24 10:37:00', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('589831', '2013-12-24 11:30:37', 'IN', '1', 'normal', '30', null);
INSERT INTO `t_inner_car_record` VALUES ('589832', '2013-12-24 12:42:47', 'OUT', '1', 'normal', '15', null);
INSERT INTO `t_inner_car_record` VALUES ('589833', '2013-12-24 14:14:41', 'IN', '1', 'normal', '32', null);
INSERT INTO `t_inner_car_record` VALUES ('589834', '2013-12-24 14:42:10', 'OUT', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('589835', '2013-12-24 14:59:04', 'OUT', '1', 'normal', '13', null);
INSERT INTO `t_inner_car_record` VALUES ('589836', '2013-12-24 15:02:58', 'OUT', '1', 'normal', '17', null);
INSERT INTO `t_inner_car_record` VALUES ('589837', '2013-12-24 15:18:38', 'OUT', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('589838', '2013-12-24 15:20:15', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('589839', '2013-12-24 15:25:53', 'OUT', '1', 'normal', '29', null);
INSERT INTO `t_inner_car_record` VALUES ('589840', '2013-12-24 15:58:37', 'IN', '1', 'normal', '29', null);
INSERT INTO `t_inner_car_record` VALUES ('589841', '2013-12-24 17:05:09', 'IN', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('589842', '2013-12-24 17:17:29', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('589843', '2013-12-24 17:26:41', 'IN', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('589844', '2013-12-24 17:49:26', 'IN', '1', 'normal', '30', null);
INSERT INTO `t_inner_car_record` VALUES ('589845', '2013-12-24 18:04:04', 'OUT', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('589847', '2013-12-24 18:14:46', 'IN', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('589848', '2013-12-24 18:53:13', 'IN', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('589850', '2013-12-24 19:05:59', 'OUT', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('589852', '2013-12-24 19:48:06', 'OUT', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('589853', '2013-12-24 20:09:22', 'IN', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('589854', '2013-12-24 20:14:40', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('589856', '2013-12-24 21:26:23', 'OUT', '1', 'normal', '17', null);
INSERT INTO `t_inner_car_record` VALUES ('589857', '2013-12-24 21:57:49', 'OUT', '1', 'normal', '17', null);
INSERT INTO `t_inner_car_record` VALUES ('589858', '2013-12-25 02:20:10', 'OUT', '1', 'normal', '34', null);
INSERT INTO `t_inner_car_record` VALUES ('589859', '2013-12-25 02:43:38', 'IN', '1', 'normal', '34', null);
INSERT INTO `t_inner_car_record` VALUES ('589863', '2013-12-25 08:05:35', 'IN', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('589864', '2013-12-25 08:12:18', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('589865', '2013-12-25 08:14:55', 'OUT', '1', 'normal', '34', null);
INSERT INTO `t_inner_car_record` VALUES ('589866', '2013-12-25 08:46:31', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('589867', '2013-12-25 08:57:26', 'IN', '1', 'normal', '34', null);
INSERT INTO `t_inner_car_record` VALUES ('589868', '2013-12-25 09:04:44', 'OUT', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('589869', '2013-12-25 09:29:03', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('589870', '2013-12-25 09:33:40', 'OUT', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('589871', '2013-12-25 09:43:04', 'IN', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('589872', '2013-12-25 11:16:17', 'OUT', '1', 'normal', '13', null);
INSERT INTO `t_inner_car_record` VALUES ('589873', '2013-12-25 11:27:41', 'OUT', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('589874', '2013-12-25 11:29:33', 'IN', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('589875', '2013-12-25 11:44:29', 'OUT', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('589876', '2013-12-25 13:17:11', 'IN', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('589877', '2013-12-25 13:49:17', 'IN', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('589878', '2013-12-25 13:49:48', 'IN', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('589879', '2013-12-25 14:39:28', 'OUT', '1', 'normal', '17', null);
INSERT INTO `t_inner_car_record` VALUES ('589880', '2013-12-25 14:47:48', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('589881', '2013-12-25 15:52:01', 'OUT', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('589882', '2013-12-25 16:00:52', 'IN', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('589884', '2013-12-25 17:11:50', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('589885', '2013-12-25 17:38:39', 'IN', '1', 'normal', '30', null);
INSERT INTO `t_inner_car_record` VALUES ('589886', '2013-12-25 17:40:36', 'OUT', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('589888', '2013-12-25 17:48:50', 'IN', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('589889', '2013-12-25 17:52:27', 'OUT', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('589890', '2013-12-25 17:53:52', 'OUT', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('589892', '2013-12-25 18:40:26', 'IN', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('589893', '2013-12-25 19:22:58', 'OUT', '1', 'normal', '17', null);
INSERT INTO `t_inner_car_record` VALUES ('589894', '2013-12-25 19:52:48', 'OUT', '1', 'normal', '15', null);
INSERT INTO `t_inner_car_record` VALUES ('589895', '2013-12-26 17:55:19', 'OUT', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('589896', '2013-12-26 17:55:21', 'IN', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('589898', '2013-12-26 17:55:23', 'OUT', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('589899', '2013-12-26 17:55:25', 'IN', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('589900', '2013-12-26 17:55:26', 'IN', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('622594', '2013-12-26 17:59:09', 'OUT', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('622596', '2013-12-26 17:59:13', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('622597', '2013-12-26 17:59:14', 'IN', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('622598', '2013-12-26 17:59:20', 'IN', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('622599', '2013-12-26 17:59:20', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('622600', '2013-12-26 17:59:20', 'IN', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('622601', '2013-12-26 17:59:21', 'OUT', '1', 'normal', '15', null);
INSERT INTO `t_inner_car_record` VALUES ('622602', '2013-12-26 17:59:22', 'IN', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('622604', '2013-12-26 17:59:24', 'OUT', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('622605', '2013-12-26 17:59:26', 'OUT', '1', 'normal', '29', null);
INSERT INTO `t_inner_car_record` VALUES ('622606', '2013-12-26 17:59:26', 'IN', '1', 'normal', '29', null);
INSERT INTO `t_inner_car_record` VALUES ('622607', '2013-12-26 17:59:27', 'OUT', '1', 'normal', '15', null);
INSERT INTO `t_inner_car_record` VALUES ('622608', '2013-12-26 17:59:29', 'IN', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('622609', '2013-12-26 17:59:29', 'OUT', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('622610', '2013-12-26 17:59:29', 'IN', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('622611', '2013-12-26 17:59:29', 'IN', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('622612', '2013-12-26 17:59:38', 'OUT', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('622613', '2013-12-26 17:59:41', 'OUT', '1', 'normal', '29', null);
INSERT INTO `t_inner_car_record` VALUES ('622614', '2013-12-26 17:59:50', 'IN', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('622615', '2013-12-26 18:00:04', 'IN', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('622616', '2013-12-26 18:00:04', 'OUT', '1', 'normal', '15', null);
INSERT INTO `t_inner_car_record` VALUES ('622617', '2013-12-26 18:00:06', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('622618', '2013-12-26 18:00:10', 'OUT', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('622619', '2013-12-26 18:00:17', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('622620', '2013-12-26 18:00:19', 'OUT', '1', 'normal', '15', null);
INSERT INTO `t_inner_car_record` VALUES ('622621', '2013-12-26 18:00:34', 'IN', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('622622', '2013-12-26 18:00:54', 'IN', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('622623', '2013-12-26 18:00:57', 'IN', '1', 'normal', '29', null);
INSERT INTO `t_inner_car_record` VALUES ('622624', '2013-12-26 18:01:11', 'IN', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('622626', '2013-12-26 18:01:17', 'OUT', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('622627', '2013-12-26 18:01:20', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('622628', '2013-12-26 18:01:42', 'OUT', '1', 'normal', '17', null);
INSERT INTO `t_inner_car_record` VALUES ('622629', '2013-12-26 18:01:45', 'OUT', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('622630', '2013-12-26 18:01:49', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('622632', '2013-12-26 18:19:55', 'IN', '1', 'normal', '30', null);
INSERT INTO `t_inner_car_record` VALUES ('622633', '2013-12-26 18:26:03', 'IN', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('622639', '2013-12-26 21:07:34', 'IN', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('622640', '2013-12-26 21:11:13', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('622641', '2013-12-26 21:15:04', 'IN', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('622642', '2013-12-26 21:20:30', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('622643', '2013-12-26 21:24:11', 'OUT', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('622645', '2013-12-26 21:57:20', 'IN', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('622646', '2013-12-26 21:58:18', 'OUT', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('622647', '2013-12-26 22:08:08', 'IN', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('622648', '2013-12-26 22:08:59', 'OUT', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('622649', '2013-12-26 22:20:47', 'IN', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('622650', '2013-12-26 22:28:48', 'OUT', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('622651', '2013-12-27 07:07:09', 'OUT', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('622653', '2013-12-27 07:32:56', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('622656', '2013-12-27 07:44:51', 'OUT', '1', 'normal', '17', null);
INSERT INTO `t_inner_car_record` VALUES ('622657', '2013-12-27 07:49:46', 'IN', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('622659', '2013-12-27 08:20:22', 'OUT', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('622660', '2013-12-27 08:28:00', 'IN', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('622661', '2013-12-27 08:45:30', 'IN', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('622662', '2013-12-27 08:48:11', 'IN', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('622663', '2013-12-27 09:30:29', 'IN', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('622664', '2013-12-27 09:33:58', 'IN', '1', 'normal', '30', null);
INSERT INTO `t_inner_car_record` VALUES ('622665', '2013-12-27 09:33:59', 'OUT', '1', 'normal', '29', null);
INSERT INTO `t_inner_car_record` VALUES ('622666', '2013-12-27 10:12:24', 'IN', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('622667', '2013-12-27 10:52:52', 'OUT', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('622668', '2013-12-27 11:14:38', 'OUT', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('622669', '2013-12-27 11:38:04', 'IN', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('622670', '2013-12-27 12:07:15', 'IN', '1', 'normal', '29', null);
INSERT INTO `t_inner_car_record` VALUES ('622671', '2013-12-27 12:09:26', 'IN', '1', 'normal', '30', null);
INSERT INTO `t_inner_car_record` VALUES ('622672', '2013-12-27 12:52:08', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('622673', '2013-12-27 13:30:49', 'OUT', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('622674', '2013-12-27 13:46:29', 'IN', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('622675', '2013-12-27 14:23:47', 'OUT', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('622676', '2013-12-27 14:38:35', 'OUT', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('622677', '2013-12-27 14:55:48', 'OUT', '1', 'normal', '17', null);
INSERT INTO `t_inner_car_record` VALUES ('622678', '2013-12-27 15:05:02', 'IN', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('622679', '2013-12-27 15:11:59', 'OUT', '1', 'normal', '17', null);
INSERT INTO `t_inner_car_record` VALUES ('622680', '2013-12-27 15:17:25', 'IN', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('622681', '2013-12-27 15:19:47', 'OUT', '1', 'normal', '29', null);
INSERT INTO `t_inner_car_record` VALUES ('622682', '2013-12-27 15:20:29', 'OUT', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('622683', '2013-12-27 15:40:43', 'OUT', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('622684', '2013-12-27 15:46:00', 'IN', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('622685', '2013-12-27 16:08:44', 'IN', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('622686', '2013-12-27 16:18:00', 'IN', '1', 'normal', '29', null);
INSERT INTO `t_inner_car_record` VALUES ('622687', '2013-12-27 16:33:44', 'IN', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('622688', '2013-12-27 16:45:01', 'IN', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('622689', '2013-12-27 18:16:02', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('622690', '2013-12-27 18:16:05', 'OUT', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('622691', '2013-12-27 18:16:07', 'IN', '1', 'normal', '33', null);
INSERT INTO `t_inner_car_record` VALUES ('622692', '2013-12-27 18:16:07', 'OUT', '1', 'normal', '33', null);
INSERT INTO `t_inner_car_record` VALUES ('622693', '2013-12-27 18:16:08', 'OUT', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('622699', '2013-12-27 18:57:57', 'IN', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('622702', '2013-12-27 19:39:08', 'OUT', '1', 'normal', '15', null);
INSERT INTO `t_inner_car_record` VALUES ('622706', '2013-12-28 07:27:59', 'IN', '1', 'normal', '33', null);
INSERT INTO `t_inner_car_record` VALUES ('622707', '2013-12-28 07:35:01', 'OUT', '1', 'normal', '33', null);
INSERT INTO `t_inner_car_record` VALUES ('622708', '2013-12-28 07:43:09', 'OUT', '1', 'normal', '15', null);
INSERT INTO `t_inner_car_record` VALUES ('622709', '2013-12-28 08:17:17', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('622710', '2013-12-28 10:05:05', 'OUT', '1', 'normal', '13', null);
INSERT INTO `t_inner_car_record` VALUES ('622718', '2013-12-28 14:35:04', 'IN', '1', 'normal', '15', null);
INSERT INTO `t_inner_car_record` VALUES ('622719', '2013-12-28 14:54:51', 'OUT', '1', 'normal', '13', null);
INSERT INTO `t_inner_car_record` VALUES ('622720', '2013-12-28 20:36:01', 'OUT', '1', 'normal', '34', null);
INSERT INTO `t_inner_car_record` VALUES ('622721', '2013-12-28 20:40:17', 'IN', '1', 'normal', '34', null);
INSERT INTO `t_inner_car_record` VALUES ('622722', '2013-12-29 08:34:42', 'IN', '1', 'normal', '30', null);
INSERT INTO `t_inner_car_record` VALUES ('622724', '2013-12-29 10:17:29', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('622726', '2013-12-29 12:30:49', 'OUT', '1', 'normal', '34', null);
INSERT INTO `t_inner_car_record` VALUES ('622728', '2013-12-29 15:27:26', 'OUT', '1', 'normal', '15', null);
INSERT INTO `t_inner_car_record` VALUES ('622729', '2013-12-29 16:20:00', 'OUT', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('655360', '2013-12-29 18:08:29', 'IN', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('655361', '2013-12-29 18:16:19', 'OUT', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('655362', '2013-12-29 18:27:18', 'IN', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('655363', '2013-12-29 18:31:39', 'OUT', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('655365', '2013-12-29 18:38:40', 'OUT', '1', 'normal', '15', null);
INSERT INTO `t_inner_car_record` VALUES ('655369', '2013-12-29 21:00:37', 'IN', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('655370', '2013-12-29 23:15:11', 'OUT', '1', 'normal', '34', null);
INSERT INTO `t_inner_car_record` VALUES ('655371', '2013-12-30 01:38:56', 'IN', '1', 'normal', '34', null);
INSERT INTO `t_inner_car_record` VALUES ('655374', '2013-12-30 07:39:31', 'IN', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('655376', '2013-12-30 07:49:08', 'OUT', '1', 'normal', '15', null);
INSERT INTO `t_inner_car_record` VALUES ('655378', '2013-12-30 08:03:01', 'OUT', '1', 'normal', '15', null);
INSERT INTO `t_inner_car_record` VALUES ('655379', '2013-12-30 08:06:05', 'IN', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('655380', '2013-12-30 08:20:17', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('655381', '2013-12-30 08:25:07', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('655382', '2013-12-30 08:51:53', 'IN', '1', 'normal', '34', null);
INSERT INTO `t_inner_car_record` VALUES ('688129', '2013-12-30 10:17:15', 'OUT', '1', 'normal', '29', null);
INSERT INTO `t_inner_car_record` VALUES ('688131', '2013-12-30 10:41:55', 'IN', '1', 'normal', '29', null);
INSERT INTO `t_inner_car_record` VALUES ('688132', '2013-12-30 12:12:06', 'OUT', '1', 'normal', '17', null);
INSERT INTO `t_inner_car_record` VALUES ('688133', '2013-12-30 13:31:03', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('688134', '2013-12-30 13:47:33', 'OUT', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('688136', '2013-12-30 14:53:18', 'OUT', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('688137', '2013-12-30 15:16:09', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('688139', '2013-12-30 15:40:56', 'OUT', '1', 'normal', '17', null);
INSERT INTO `t_inner_car_record` VALUES ('688140', '2013-12-30 15:41:54', 'OUT', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('688141', '2013-12-30 16:27:03', 'OUT', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('688142', '2013-12-30 16:51:45', 'OUT', '1', 'normal', '15', null);
INSERT INTO `t_inner_car_record` VALUES ('688144', '2013-12-31 08:22:21', 'OUT', '1', 'normal', '29', null);
INSERT INTO `t_inner_car_record` VALUES ('688145', '2013-12-31 08:22:23', 'IN', '1', 'normal', '29', null);
INSERT INTO `t_inner_car_record` VALUES ('688149', '2013-12-31 08:22:31', 'OUT', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('688150', '2013-12-31 08:22:33', 'IN', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('688151', '2013-12-31 08:22:41', 'IN', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('688152', '2013-12-31 08:22:41', 'IN', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('688153', '2013-12-31 08:22:41', 'IN', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('688154', '2013-12-31 08:22:43', 'IN', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('688155', '2013-12-31 08:22:47', 'IN', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('688156', '2013-12-31 08:26:30', 'OUT', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('688157', '2013-12-31 08:26:36', 'IN', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('688158', '2013-12-31 08:26:42', 'IN', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('688159', '2013-12-31 08:26:46', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('688160', '2013-12-31 08:26:47', 'OUT', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('688162', '2013-12-31 08:57:47', 'OUT', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('688163', '2013-12-31 09:40:04', 'OUT', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('688164', '2013-12-31 09:42:24', 'OUT', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('688165', '2013-12-31 10:29:54', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('688166', '2013-12-31 10:38:11', 'IN', '1', 'normal', '15', null);
INSERT INTO `t_inner_car_record` VALUES ('688167', '2013-12-31 10:40:23', 'OUT', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('688168', '2013-12-31 10:44:59', 'IN', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('688169', '2013-12-31 11:21:24', 'IN', '1', 'normal', '33', null);
INSERT INTO `t_inner_car_record` VALUES ('688170', '2013-12-31 11:25:43', 'OUT', '1', 'normal', '33', null);
INSERT INTO `t_inner_car_record` VALUES ('688171', '2013-12-31 12:17:36', 'OUT', '1', 'normal', '29', null);
INSERT INTO `t_inner_car_record` VALUES ('688172', '2013-12-31 12:47:59', 'IN', '1', 'normal', '29', null);
INSERT INTO `t_inner_car_record` VALUES ('688173', '2013-12-31 13:06:11', 'OUT', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('688174', '2013-12-31 13:13:31', 'IN', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('688175', '2013-12-31 13:28:08', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('688176', '2013-12-31 13:43:17', 'OUT', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('688177', '2013-12-31 14:06:13', 'OUT', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('688178', '2013-12-31 14:21:42', 'OUT', '1', 'normal', '15', null);
INSERT INTO `t_inner_car_record` VALUES ('688179', '2013-12-31 14:39:35', 'OUT', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('688180', '2013-12-31 14:41:52', 'OUT', '1', 'normal', '29', null);
INSERT INTO `t_inner_car_record` VALUES ('688181', '2013-12-31 14:44:25', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('688182', '2013-12-31 14:47:49', 'IN', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('688183', '2013-12-31 14:49:55', 'IN', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('688185', '2013-12-31 15:05:15', 'OUT', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('688187', '2013-12-31 15:56:47', 'IN', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('688189', '2013-12-31 16:41:34', 'IN', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('688190', '2013-12-31 16:58:09', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('688192', '2013-12-31 17:33:26', 'IN', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('688193', '2013-12-31 17:34:05', 'IN', '1', 'normal', '29', null);
INSERT INTO `t_inner_car_record` VALUES ('688194', '2013-12-31 17:53:25', 'OUT', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('688196', '2013-12-31 18:04:00', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('688197', '2013-12-31 18:12:48', 'OUT', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('688198', '2013-12-31 18:21:20', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('688199', '2013-12-31 19:01:07', 'IN', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('688200', '2013-12-31 19:03:43', 'IN', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('688201', '2013-12-31 19:04:28', 'OUT', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('688202', '2013-12-31 19:09:28', 'IN', '1', 'normal', '30', null);
INSERT INTO `t_inner_car_record` VALUES ('688203', '2013-12-31 19:10:35', 'IN', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('720896', '2013-12-31 21:57:30', 'IN', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('720897', '2013-12-31 21:57:32', 'IN', '1', 'normal', '33', null);
INSERT INTO `t_inner_car_record` VALUES ('720898', '2013-12-31 21:57:34', 'OUT', '1', 'normal', '33', null);
INSERT INTO `t_inner_car_record` VALUES ('720899', '2014-01-01 09:14:53', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('720900', '2014-01-01 09:15:04', 'OUT', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('720901', '2014-01-01 10:02:29', 'OUT', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('720902', '2014-01-01 10:32:17', 'IN', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('720903', '2014-01-01 14:42:14', 'IN', '1', 'normal', '14', null);
INSERT INTO `t_inner_car_record` VALUES ('720904', '2014-01-01 14:58:43', 'OUT', '1', 'normal', '15', null);
INSERT INTO `t_inner_car_record` VALUES ('720905', '2014-01-01 15:37:48', 'IN', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('720906', '2014-01-01 15:52:28', 'OUT', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('720907', '2014-01-01 16:36:41', 'IN', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('720908', '2014-01-01 17:18:25', 'OUT', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('720909', '2014-01-01 18:05:02', 'OUT', '1', 'normal', '34', null);
INSERT INTO `t_inner_car_record` VALUES ('720910', '2014-01-01 18:31:19', 'IN', '1', 'normal', '34', null);
INSERT INTO `t_inner_car_record` VALUES ('720911', '2014-01-01 19:09:55', 'IN', '1', 'normal', '30', null);
INSERT INTO `t_inner_car_record` VALUES ('720913', '2014-01-01 22:22:54', 'IN', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('720914', '2014-01-02 07:05:44', 'OUT', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('720916', '2014-01-02 11:28:46', 'IN', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('720917', '2014-01-02 11:28:49', 'OUT', '1', 'normal', '34', null);
INSERT INTO `t_inner_car_record` VALUES ('720918', '2014-01-02 11:28:50', 'IN', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('720919', '2014-01-02 11:28:50', 'OUT', '1', 'normal', '17', null);
INSERT INTO `t_inner_car_record` VALUES ('720920', '2014-01-02 11:28:54', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('720921', '2014-01-02 11:28:55', 'IN', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('720922', '2014-01-02 11:28:57', 'IN', '1', 'normal', '34', null);
INSERT INTO `t_inner_car_record` VALUES ('720923', '2014-01-02 11:28:57', 'OUT', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('720924', '2014-01-02 11:28:59', 'OUT', '1', 'normal', '17', null);
INSERT INTO `t_inner_car_record` VALUES ('720925', '2014-01-02 11:29:03', 'IN', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('720926', '2014-01-02 11:29:03', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('720927', '2014-01-02 12:19:59', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('720928', '2014-01-02 15:41:00', 'IN', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('720929', '2014-01-02 15:58:30', 'OUT', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('720930', '2014-01-02 16:50:10', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('720931', '2014-01-02 17:14:53', 'IN', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('720932', '2014-01-02 17:17:30', 'IN', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('720934', '2014-01-02 17:46:37', 'OUT', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('720937', '2014-01-02 18:18:59', 'IN', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('720938', '2014-01-02 18:42:38', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('720941', '2014-01-03 06:53:56', 'OUT', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('720942', '2014-01-03 07:05:48', 'OUT', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('720944', '2014-01-03 07:34:36', 'IN', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('720945', '2014-01-03 07:48:27', 'OUT', '1', 'normal', '15', null);
INSERT INTO `t_inner_car_record` VALUES ('720947', '2014-01-03 08:24:32', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('720948', '2014-01-03 08:31:05', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('720950', '2014-01-03 08:33:14', 'IN', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('720951', '2014-01-03 08:37:55', 'OUT', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('720952', '2014-01-03 09:06:56', 'IN', '1', 'normal', '2', null);
INSERT INTO `t_inner_car_record` VALUES ('720953', '2014-01-03 09:17:42', 'IN', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('720954', '2014-01-03 09:18:57', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('720955', '2014-01-03 10:36:54', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('720956', '2014-01-03 11:05:16', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('720957', '2014-01-03 11:16:24', 'IN', '1', 'normal', '29', null);
INSERT INTO `t_inner_car_record` VALUES ('720958', '2014-01-03 11:20:28', 'OUT', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('720959', '2014-01-03 11:30:00', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('720960', '2014-01-03 12:16:05', 'OUT', '1', 'normal', '15', null);
INSERT INTO `t_inner_car_record` VALUES ('720961', '2014-01-03 12:56:19', 'IN', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('720962', '2014-01-03 13:07:26', 'IN', '1', 'normal', '34', null);
INSERT INTO `t_inner_car_record` VALUES ('720963', '2014-01-03 13:12:38', 'OUT', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('720964', '2014-01-03 13:37:45', 'OUT', '1', 'normal', '10', null);
INSERT INTO `t_inner_car_record` VALUES ('720965', '2014-01-03 14:04:52', 'IN', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('720966', '2014-01-03 14:16:37', 'IN', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('720967', '2014-01-03 14:43:59', 'IN', '1', 'normal', '2', null);
INSERT INTO `t_inner_car_record` VALUES ('720968', '2014-01-03 14:51:29', 'OUT', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('720969', '2014-01-03 15:05:19', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('720970', '2014-01-03 15:26:07', 'IN', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('720971', '2014-01-03 15:32:43', 'IN', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('720972', '2014-01-03 16:08:15', 'IN', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('720973', '2014-01-03 16:08:22', 'OUT', '1', 'normal', '17', null);
INSERT INTO `t_inner_car_record` VALUES ('720974', '2014-01-03 16:20:27', 'OUT', '1', 'normal', '2', null);
INSERT INTO `t_inner_car_record` VALUES ('720975', '2014-01-03 16:20:43', 'IN', '1', 'normal', '2', null);
INSERT INTO `t_inner_car_record` VALUES ('720976', '2014-01-03 16:51:48', 'IN', '1', 'normal', '25', null);
INSERT INTO `t_inner_car_record` VALUES ('720977', '2014-01-03 16:54:46', 'OUT', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('720978', '2014-01-03 17:07:30', 'IN', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('720979', '2014-01-03 17:27:36', 'IN', '1', 'normal', '2', null);
INSERT INTO `t_inner_car_record` VALUES ('720980', '2014-01-03 17:31:00', 'OUT', '1', 'normal', '12', null);
INSERT INTO `t_inner_car_record` VALUES ('720981', '2014-01-03 17:43:17', 'OUT', '1', 'normal', '2514903', null);
INSERT INTO `t_inner_car_record` VALUES ('720985', '2014-01-04 08:21:29', 'IN', '1', 'normal', '3', null);
INSERT INTO `t_inner_car_record` VALUES ('720987', '2014-01-04 08:26:38', 'OUT', '1', 'normal', '16', null);
INSERT INTO `t_inner_car_record` VALUES ('720989', '2014-01-04 09:06:28', 'IN', '1', 'normal', '33', null);
INSERT INTO `t_inner_car_record` VALUES ('720990', '2014-01-04 09:12:37', 'IN', '1', 'normal', '30', null);
INSERT INTO `t_inner_car_record` VALUES ('720991', '2014-01-04 10:09:05', 'OUT', '1', 'normal', '33', null);

-- ----------------------------
-- Table structure for `t_inner_person`
-- ----------------------------
DROP TABLE IF EXISTS `t_inner_person`;
CREATE TABLE `t_inner_person` (
  `id` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `name` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `sex` varchar(5) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `card_id` bigint(20) DEFAULT NULL,
  `certificate` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `notify_id` bigint(20) DEFAULT NULL,
  `organization_id` bigint(20) DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sbxlbxcw14u1q4j7gp3uwg2eo` (`name`),
  KEY `FK_d2lihx1sh8e4huo15g9aof8fx` (`notify_id`),
  KEY `FK_ksrogfeueidd2ka96nhugnmw3` (`organization_id`),
  KEY `FK_7ia9p7s8q6y96nm6mf1t7bd7v` (`card_id`),
  CONSTRAINT `FK_7ia9p7s8q6y96nm6mf1t7bd7v` FOREIGN KEY (`card_id`) REFERENCES `t_card` (`id`),
  CONSTRAINT `FK_d2lihx1sh8e4huo15g9aof8fx` FOREIGN KEY (`notify_id`) REFERENCES `t_notify` (`id`),
  CONSTRAINT `FK_ksrogfeueidd2ka96nhugnmw3` FOREIGN KEY (`organization_id`) REFERENCES `sa_t_organization` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of t_inner_person
-- ----------------------------
INSERT INTO `t_inner_person` VALUES ('1', '2013-12-13 12:25:34', '测试', 'man', 'OUT', '1', '2240369336', '2', '65536', '15288843691');
INSERT INTO `t_inner_person` VALUES ('2', '2013-12-13 10:57:05', '游  毅', 'man', 'IN', '5', '14089702', '2', '98304', '13854732166');
INSERT INTO `t_inner_person` VALUES ('4', '2013-12-13 10:57:16', '王庆鲁', 'man', 'IN', '7', '14089912', '2', '98304', '13791726630');
INSERT INTO `t_inner_person` VALUES ('5', '2013-12-13 10:57:26', '刘  涛', 'man', 'IN', '8', '14080007', '2', '98304', '13963724321');
INSERT INTO `t_inner_person` VALUES ('6', '2013-12-13 17:07:09', '谭晓红', 'man', 'IN', '9', '14080002', '2', '98304', '13853712340');
INSERT INTO `t_inner_person` VALUES ('8', '2013-12-13 10:57:45', '师怀滨', 'man', 'OUT', '10', '14080323', '2', '98304', '13605379011');
INSERT INTO `t_inner_person` VALUES ('9', '2013-12-13 10:57:56', '许  路', 'man', 'IN', '11', '14080329', '2', '98304', '15269767779');
INSERT INTO `t_inner_person` VALUES ('10', '2013-12-13 10:58:08', '陈广超', 'man', 'IN', '12', '14080416', '2', '98304', '15265788708');
INSERT INTO `t_inner_person` VALUES ('11', '2013-12-13 10:58:19', '陈  波', 'man', 'IN', '13', '14080412', '2', '98304', '18369891555');
INSERT INTO `t_inner_person` VALUES ('12', '2013-12-13 10:58:30', '张珍洲', 'man', 'IN', '14', '14080703', '2', '98304', '18853759777');
INSERT INTO `t_inner_person` VALUES ('13', '2013-12-13 10:58:43', '巩秉鑫', 'man', 'IN', '15', '14080603', '2', '98304', '15965701777');
INSERT INTO `t_inner_person` VALUES ('14', '2013-12-13 10:58:53', '张  状', 'man', 'IN', '16', '14080686', '2', '98304', '15106475557');
INSERT INTO `t_inner_person` VALUES ('15', '2013-12-13 10:59:02', '赵  栋', 'man', 'IN', '17', '14080714', '2', '98304', '18753734899');
INSERT INTO `t_inner_person` VALUES ('16', '2013-12-13 10:59:10', '晁二东', 'man', 'IN', '18', '14080756', '2', '98304', '15092655601');
INSERT INTO `t_inner_person` VALUES ('19', '2013-12-13 10:59:51', '刘  阳', 'man', 'IN', '21', '14081008', '2', '98304', '15069793602');
INSERT INTO `t_inner_person` VALUES ('20', '2013-12-13 11:00:08', '吕  权', 'man', 'IN', '22', '14081142', '2', '98304', '15153740765');
INSERT INTO `t_inner_person` VALUES ('21', '2013-12-13 11:51:11', '高  峰', 'man', 'OUT', '23', '14081137', '2', '98304', '15964726373');
INSERT INTO `t_inner_person` VALUES ('22', '2013-11-23 16:20:11', '王繁圣', 'man', 'IN', '24', '14081111', '2', '98304', '');
INSERT INTO `t_inner_person` VALUES ('23', '2013-11-23 16:20:11', '王亮', 'man', 'IN', '25', '14081051', '2', '98304', '');
INSERT INTO `t_inner_person` VALUES ('25', '2013-12-13 11:00:25', '马思聪', 'man', 'IN', '27', '14080835', '2', '98304', '18754712920');
INSERT INTO `t_inner_person` VALUES ('26', '2013-12-13 11:00:38', '张飞', 'man', 'IN', '28', '370826198709246815', '2', '98304', '15054864752');
INSERT INTO `t_inner_person` VALUES ('27', '2013-12-13 11:00:50', '闫通', 'man', 'OUT', '29', '370883198903257216', '2', '98304', '15964755529');
INSERT INTO `t_inner_person` VALUES ('28', '2013-12-13 11:01:01', '付庆震', 'man', 'IN', '30', '370811198707212537', '2', '98304', '13792355975');
INSERT INTO `t_inner_person` VALUES ('29', '2013-12-13 11:01:09', '屈涛', 'man', 'OUT', '31', '370826198606205252', '2', '98304', '15969791751');
INSERT INTO `t_inner_person` VALUES ('30', '2013-12-13 11:01:22', '林祥德', 'man', 'IN', '32', '370832199107191337', '2', '98304', '18369713118');
INSERT INTO `t_inner_person` VALUES ('983043', '2013-12-17 16:05:37', '潘冠涛', 'man', 'IN', '32771', '14081112', '2', '98304', '15092703225');
INSERT INTO `t_inner_person` VALUES ('983044', '2013-12-14 11:12:49', '丛苏', 'man', 'IN', '32772', '14080751', '2', '98304', '18888888888');
INSERT INTO `t_inner_person` VALUES ('1016142', '2013-12-15 17:35:18', '于岳谭', 'man', 'IN', '65870', '370802199008221213', '2', '98304', '18053722710');

-- ----------------------------
-- Table structure for `t_inner_person_record`
-- ----------------------------
DROP TABLE IF EXISTS `t_inner_person_record`;
CREATE TABLE `t_inner_person_record` (
  `id` bigint(20) NOT NULL,
  `date` datetime DEFAULT NULL,
  `type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `door_id` bigint(20) NOT NULL,
  `status` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `leave_id` bigint(20) DEFAULT NULL,
  `person_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_11q8b7yi54hpn5wyvy61b4i2n` (`leave_id`),
  KEY `FK_r0e079u7lsicwjpgknujloiv9` (`door_id`),
  CONSTRAINT `FK_11q8b7yi54hpn5wyvy61b4i2n` FOREIGN KEY (`leave_id`) REFERENCES `t_leave` (`id`),
  CONSTRAINT `FK_r0e079u7lsicwjpgknujloiv9` FOREIGN KEY (`door_id`) REFERENCES `t_door` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_inner_person_record
-- ----------------------------
INSERT INTO `t_inner_person_record` VALUES ('98304', '2013-12-13 16:21:52', 'OUT', '1', 'normal', null, '30');
INSERT INTO `t_inner_person_record` VALUES ('131072', '2013-12-13 17:05:11', 'OUT', '1', 'normal', null, '27');
INSERT INTO `t_inner_person_record` VALUES ('131073', '2013-12-13 17:23:06', 'IN', '1', 'normal', null, '28');
INSERT INTO `t_inner_person_record` VALUES ('131074', '2013-12-13 17:24:59', 'OUT', '1', 'normal', null, '6');
INSERT INTO `t_inner_person_record` VALUES ('163844', '2013-12-13 19:59:26', 'OUT', '1', 'warning', null, '10');
INSERT INTO `t_inner_person_record` VALUES ('163846', '2013-12-13 20:20:20', 'IN', '1', 'warning', null, '9');
INSERT INTO `t_inner_person_record` VALUES ('163854', '2013-12-14 10:07:30', 'OUT', '1', 'normal', null, '19');
INSERT INTO `t_inner_person_record` VALUES ('196608', '2013-12-14 11:50:06', 'OUT', '1', 'normal', null, '983043');
INSERT INTO `t_inner_person_record` VALUES ('262146', '2013-12-14 13:17:20', 'IN', '1', 'normal', null, '983043');
INSERT INTO `t_inner_person_record` VALUES ('262148', '2013-12-14 14:17:50', 'IN', '1', 'normal', null, '26');
INSERT INTO `t_inner_person_record` VALUES ('262149', '2013-12-14 15:19:59', 'OUT', '1', 'normal', null, '26');
INSERT INTO `t_inner_person_record` VALUES ('262152', '2013-12-14 15:43:59', 'OUT', '1', 'normal', null, '26');
INSERT INTO `t_inner_person_record` VALUES ('294913', '2013-12-15 16:58:03', 'OUT', '1', 'normal', null, '27');
INSERT INTO `t_inner_person_record` VALUES ('294914', '2013-12-15 16:58:35', 'OUT', '1', 'normal', null, '27');
INSERT INTO `t_inner_person_record` VALUES ('294915', '2013-12-15 16:59:02', 'IN', '1', 'normal', null, '27');
INSERT INTO `t_inner_person_record` VALUES ('294916', '2013-12-15 17:38:20', 'OUT', '1', 'normal', null, '1016142');
INSERT INTO `t_inner_person_record` VALUES ('294917', '2013-12-15 17:38:32', 'IN', '1', 'normal', null, '1016142');
INSERT INTO `t_inner_person_record` VALUES ('294919', '2013-12-15 17:51:56', 'IN', '1', 'normal', null, '30');
INSERT INTO `t_inner_person_record` VALUES ('294920', '2013-12-15 17:55:40', 'IN', '1', 'normal', null, '26');
INSERT INTO `t_inner_person_record` VALUES ('294921', '2013-12-15 18:03:35', 'OUT', '1', 'normal', null, '1016142');
INSERT INTO `t_inner_person_record` VALUES ('294922', '2013-12-15 18:03:37', 'OUT', '1', 'normal', null, '27');
INSERT INTO `t_inner_person_record` VALUES ('294924', '2013-12-15 18:41:48', 'OUT', '1', 'normal', null, '19');
INSERT INTO `t_inner_person_record` VALUES ('294928', '2013-12-15 19:04:07', 'IN', '1', 'normal', null, '19');
INSERT INTO `t_inner_person_record` VALUES ('294933', '2013-12-15 21:12:58', 'IN', '1', 'normal', null, '27');
INSERT INTO `t_inner_person_record` VALUES ('294934', '2013-12-15 21:13:03', 'IN', '1', 'normal', null, '1016142');
INSERT INTO `t_inner_person_record` VALUES ('294941', '2013-12-16 07:20:14', 'OUT', '1', 'normal', null, '9');
INSERT INTO `t_inner_person_record` VALUES ('294943', '2013-12-16 07:58:01', 'IN', '1', 'normal', null, '6');
INSERT INTO `t_inner_person_record` VALUES ('294944', '2013-12-16 07:59:41', 'IN', '1', 'normal', null, '28');
INSERT INTO `t_inner_person_record` VALUES ('294954', '2013-12-16 11:57:07', 'OUT', '1', 'normal', null, '983043');
INSERT INTO `t_inner_person_record` VALUES ('294955', '2013-12-16 12:34:42', 'IN', '1', 'normal', null, '983043');
INSERT INTO `t_inner_person_record` VALUES ('294961', '2013-12-16 15:47:24', 'OUT', '1', 'normal', null, '29');
INSERT INTO `t_inner_person_record` VALUES ('327681', '2013-12-16 17:17:12', 'IN', '1', 'normal', null, '6');
INSERT INTO `t_inner_person_record` VALUES ('360448', '2013-12-16 18:32:52', 'IN', '1', 'normal', null, '8');
INSERT INTO `t_inner_person_record` VALUES ('393216', '2013-12-16 19:28:52', 'IN', '1', 'normal', null, '15');
INSERT INTO `t_inner_person_record` VALUES ('393217', '2013-12-16 20:32:50', 'IN', '1', 'normal', null, '16');
INSERT INTO `t_inner_person_record` VALUES ('393218', '2013-12-16 20:32:53', 'IN', '1', 'normal', null, '19');
INSERT INTO `t_inner_person_record` VALUES ('393221', '2013-12-17 07:32:01', 'IN', '1', 'normal', null, '8');
INSERT INTO `t_inner_person_record` VALUES ('393222', '2013-12-17 07:43:00', 'IN', '1', 'normal', null, '29');
INSERT INTO `t_inner_person_record` VALUES ('393224', '2013-12-17 07:52:13', 'IN', '1', 'normal', null, '6');
INSERT INTO `t_inner_person_record` VALUES ('425984', '2013-12-18 17:55:25', 'OUT', '1', 'warning', null, '21');
INSERT INTO `t_inner_person_record` VALUES ('458752', '2013-12-18 19:17:34', 'OUT', '1', 'warning', null, '983043');
INSERT INTO `t_inner_person_record` VALUES ('458753', '2013-12-18 19:17:49', 'IN', '1', 'warning', null, '983043');
INSERT INTO `t_inner_person_record` VALUES ('458754', '2013-12-18 19:27:26', 'OUT', '1', 'normal', null, '1016142');
INSERT INTO `t_inner_person_record` VALUES ('458755', '2013-12-18 19:27:48', 'IN', '1', 'normal', null, '1016142');
INSERT INTO `t_inner_person_record` VALUES ('458756', '2013-12-18 19:44:21', 'OUT', '1', 'normal', null, '9');
INSERT INTO `t_inner_person_record` VALUES ('458757', '2013-12-18 19:52:35', 'OUT', '1', 'normal', null, '29');
INSERT INTO `t_inner_person_record` VALUES ('458758', '2013-12-18 21:01:27', 'OUT', '1', 'normal', null, '8');
INSERT INTO `t_inner_person_record` VALUES ('458759', '2013-12-18 23:08:50', 'OUT', '1', 'normal', null, '10');
INSERT INTO `t_inner_person_record` VALUES ('458761', '2013-12-19 07:10:27', 'IN', '1', 'normal', null, '8');
INSERT INTO `t_inner_person_record` VALUES ('458763', '2013-12-19 07:20:43', 'IN', '1', 'normal', null, '9');
INSERT INTO `t_inner_person_record` VALUES ('458764', '2013-12-19 07:28:21', 'IN', '1', 'normal', null, '6');
INSERT INTO `t_inner_person_record` VALUES ('458777', '2013-12-19 11:07:31', 'IN', '1', 'normal', null, '29');
INSERT INTO `t_inner_person_record` VALUES ('458781', '2013-12-19 12:05:15', 'OUT', '1', 'normal', null, '15');
INSERT INTO `t_inner_person_record` VALUES ('458787', '2013-12-19 14:05:44', 'IN', '1', 'normal', null, '10');
INSERT INTO `t_inner_person_record` VALUES ('491520', '2013-12-19 18:08:54', 'IN', '1', 'normal', null, '9');
INSERT INTO `t_inner_person_record` VALUES ('491521', '2013-12-19 18:19:16', 'IN', '1', 'normal', null, '2');
INSERT INTO `t_inner_person_record` VALUES ('491522', '2013-12-19 18:26:40', 'OUT', '1', 'normal', null, '29');
INSERT INTO `t_inner_person_record` VALUES ('491523', '2013-12-19 18:30:55', 'OUT', '1', 'normal', null, '19');
INSERT INTO `t_inner_person_record` VALUES ('491526', '2013-12-19 20:24:56', 'IN', '1', 'normal', null, '19');
INSERT INTO `t_inner_person_record` VALUES ('491531', '2013-12-19 23:11:47', 'IN', '1', 'normal', null, '6');
INSERT INTO `t_inner_person_record` VALUES ('491532', '2013-12-20 06:45:38', 'IN', '1', 'normal', null, '9');
INSERT INTO `t_inner_person_record` VALUES ('491533', '2013-12-20 07:18:28', 'IN', '1', 'normal', null, '29');
INSERT INTO `t_inner_person_record` VALUES ('491535', '2013-12-20 08:00:44', 'IN', '1', 'normal', null, '6');
INSERT INTO `t_inner_person_record` VALUES ('491540', '2013-12-20 10:47:53', 'IN', '1', 'normal', null, '10');
INSERT INTO `t_inner_person_record` VALUES ('491547', '2013-12-20 15:50:39', 'IN', '1', 'normal', null, '2');
INSERT INTO `t_inner_person_record` VALUES ('491551', '2013-12-20 17:36:55', 'OUT', '1', 'warning', null, '6');
INSERT INTO `t_inner_person_record` VALUES ('524288', '2013-12-20 18:11:00', 'OUT', '1', 'warning', null, '9');
INSERT INTO `t_inner_person_record` VALUES ('557056', '2013-12-21 09:01:24', 'OUT', '1', 'normal', null, '1016142');
INSERT INTO `t_inner_person_record` VALUES ('557057', '2013-12-21 09:01:29', 'OUT', '1', 'normal', null, '29');
INSERT INTO `t_inner_person_record` VALUES ('557058', '2013-12-21 09:01:46', 'IN', '1', 'normal', null, '1016142');
INSERT INTO `t_inner_person_record` VALUES ('557059', '2013-12-21 09:01:58', 'IN', '1', 'normal', null, '29');
INSERT INTO `t_inner_person_record` VALUES ('557060', '2013-12-21 09:35:54', 'OUT', '1', 'normal', null, '983043');
INSERT INTO `t_inner_person_record` VALUES ('557064', '2013-12-21 10:39:11', 'IN', '1', 'normal', null, '983043');
INSERT INTO `t_inner_person_record` VALUES ('557073', '2013-12-21 13:44:10', 'OUT', '1', 'normal', null, '19');
INSERT INTO `t_inner_person_record` VALUES ('557075', '2013-12-21 14:20:38', 'IN', '1', 'normal', null, '19');
INSERT INTO `t_inner_person_record` VALUES ('557079', '2013-12-21 17:14:18', 'OUT', '1', 'normal', null, '26');
INSERT INTO `t_inner_person_record` VALUES ('557086', '2013-12-22 13:24:23', 'OUT', '1', 'normal', null, '19');
INSERT INTO `t_inner_person_record` VALUES ('557087', '2013-12-22 14:12:44', 'IN', '1', 'normal', null, '10');
INSERT INTO `t_inner_person_record` VALUES ('557089', '2013-12-22 14:51:30', 'IN', '1', 'normal', null, '19');
INSERT INTO `t_inner_person_record` VALUES ('557090', '2013-12-22 17:08:12', 'IN', '1', 'normal', null, '26');
INSERT INTO `t_inner_person_record` VALUES ('557093', '2013-12-22 21:53:27', 'OUT', '1', 'normal', null, '10');
INSERT INTO `t_inner_person_record` VALUES ('557094', '2013-12-23 00:30:07', 'IN', '1', 'normal', null, '11');
INSERT INTO `t_inner_person_record` VALUES ('557095', '2013-12-23 06:47:48', 'IN', '1', 'normal', null, '9');
INSERT INTO `t_inner_person_record` VALUES ('557098', '2013-12-23 07:43:45', 'IN', '1', 'normal', null, '29');
INSERT INTO `t_inner_person_record` VALUES ('557099', '2013-12-23 07:49:09', 'IN', '1', 'normal', null, '6');
INSERT INTO `t_inner_person_record` VALUES ('557102', '2013-12-23 08:51:31', 'IN', '1', 'normal', null, '10');
INSERT INTO `t_inner_person_record` VALUES ('557121', '2013-12-23 10:52:21', 'OUT', '1', 'normal', null, '19');
INSERT INTO `t_inner_person_record` VALUES ('557123', '2013-12-23 11:12:42', 'IN', '1', 'normal', null, '19');
INSERT INTO `t_inner_person_record` VALUES ('557127', '2013-12-23 12:32:44', 'IN', '1', 'normal', null, '13');
INSERT INTO `t_inner_person_record` VALUES ('557139', '2013-12-23 17:41:14', 'OUT', '1', 'normal', null, '9');
INSERT INTO `t_inner_person_record` VALUES ('557141', '2013-12-23 17:58:52', 'IN', '1', 'normal', null, '28');
INSERT INTO `t_inner_person_record` VALUES ('557143', '2013-12-23 18:14:24', 'OUT', '1', 'normal', null, '8');
INSERT INTO `t_inner_person_record` VALUES ('557144', '2013-12-23 18:17:22', 'OUT', '1', 'normal', null, '29');
INSERT INTO `t_inner_person_record` VALUES ('557146', '2013-12-23 19:07:40', 'OUT', '1', 'normal', null, '19');
INSERT INTO `t_inner_person_record` VALUES ('557147', '2013-12-23 19:59:54', 'IN', '1', 'normal', null, '26');
INSERT INTO `t_inner_person_record` VALUES ('557148', '2013-12-23 20:34:42', 'IN', '1', 'warning', null, '19');
INSERT INTO `t_inner_person_record` VALUES ('557150', '2013-12-23 21:06:44', 'OUT', '1', 'normal', null, '26');
INSERT INTO `t_inner_person_record` VALUES ('589827', '2013-12-24 09:42:48', 'IN', '1', 'normal', null, '29');
INSERT INTO `t_inner_person_record` VALUES ('589846', '2013-12-24 18:08:10', 'IN', '1', 'normal', null, '28');
INSERT INTO `t_inner_person_record` VALUES ('589849', '2013-12-24 18:59:36', 'OUT', '1', 'normal', null, '6');
INSERT INTO `t_inner_person_record` VALUES ('589851', '2013-12-24 19:20:45', 'IN', '1', 'normal', null, '28');
INSERT INTO `t_inner_person_record` VALUES ('589855', '2013-12-24 20:23:24', 'OUT', '1', 'normal', null, '9');
INSERT INTO `t_inner_person_record` VALUES ('589860', '2013-12-25 02:44:47', 'IN', '1', 'normal', null, '9');
INSERT INTO `t_inner_person_record` VALUES ('589861', '2013-12-25 07:52:18', 'IN', '1', 'normal', null, '6');
INSERT INTO `t_inner_person_record` VALUES ('589862', '2013-12-25 07:55:09', 'IN', '1', 'normal', null, '29');
INSERT INTO `t_inner_person_record` VALUES ('589883', '2013-12-25 16:22:43', 'IN', '1', 'normal', null, '2');
INSERT INTO `t_inner_person_record` VALUES ('589887', '2013-12-25 17:43:59', 'IN', '1', 'normal', null, '9');
INSERT INTO `t_inner_person_record` VALUES ('589891', '2013-12-25 17:57:03', 'OUT', '1', 'normal', null, '29');
INSERT INTO `t_inner_person_record` VALUES ('589897', '2013-12-26 17:55:22', 'OUT', '1', 'warning', null, '8');
INSERT INTO `t_inner_person_record` VALUES ('622592', '2013-12-26 17:59:01', 'IN', '1', 'warning', null, '8');
INSERT INTO `t_inner_person_record` VALUES ('622593', '2013-12-26 17:59:04', 'OUT', '1', 'warning', null, '9');
INSERT INTO `t_inner_person_record` VALUES ('622595', '2013-12-26 17:59:10', 'IN', '1', 'warning', null, '29');
INSERT INTO `t_inner_person_record` VALUES ('622603', '2013-12-26 17:59:23', 'IN', '1', 'warning', null, '15');
INSERT INTO `t_inner_person_record` VALUES ('622625', '2013-12-26 18:01:13', 'IN', '1', 'normal', null, '6');
INSERT INTO `t_inner_person_record` VALUES ('622631', '2013-12-26 18:05:20', 'IN', '1', 'normal', null, '28');
INSERT INTO `t_inner_person_record` VALUES ('622634', '2013-12-26 18:32:29', 'OUT', '1', 'normal', null, '19');
INSERT INTO `t_inner_person_record` VALUES ('622635', '2013-12-26 18:34:02', 'OUT', '1', 'normal', null, '8');
INSERT INTO `t_inner_person_record` VALUES ('622636', '2013-12-26 19:16:58', 'OUT', '1', 'normal', null, '1016142');
INSERT INTO `t_inner_person_record` VALUES ('622637', '2013-12-26 20:34:43', 'IN', '1', 'warning', null, '9');
INSERT INTO `t_inner_person_record` VALUES ('622638', '2013-12-26 20:50:01', 'IN', '1', 'normal', null, '1016142');
INSERT INTO `t_inner_person_record` VALUES ('622644', '2013-12-26 21:33:49', 'IN', '1', 'warning', null, '19');
INSERT INTO `t_inner_person_record` VALUES ('622652', '2013-12-27 07:27:11', 'IN', '1', 'normal', null, '9');
INSERT INTO `t_inner_person_record` VALUES ('622654', '2013-12-27 07:33:23', 'IN', '1', 'normal', null, '8');
INSERT INTO `t_inner_person_record` VALUES ('622655', '2013-12-27 07:40:59', 'IN', '1', 'normal', null, '28');
INSERT INTO `t_inner_person_record` VALUES ('622658', '2013-12-27 07:58:00', 'IN', '1', 'normal', null, '6');
INSERT INTO `t_inner_person_record` VALUES ('622694', '2013-12-27 18:16:08', 'OUT', '1', 'normal', null, '9');
INSERT INTO `t_inner_person_record` VALUES ('622695', '2013-12-27 18:22:17', 'OUT', '1', 'normal', null, '8');
INSERT INTO `t_inner_person_record` VALUES ('622696', '2013-12-27 18:35:07', 'OUT', '1', 'normal', null, '16');
INSERT INTO `t_inner_person_record` VALUES ('622697', '2013-12-27 18:35:09', 'OUT', '1', 'normal', null, '15');
INSERT INTO `t_inner_person_record` VALUES ('622698', '2013-12-27 18:45:59', 'OUT', '1', 'normal', null, '29');
INSERT INTO `t_inner_person_record` VALUES ('622700', '2013-12-27 19:00:03', 'OUT', '1', 'warning', null, '983043');
INSERT INTO `t_inner_person_record` VALUES ('622701', '2013-12-27 19:14:56', 'OUT', '1', 'warning', null, '6');
INSERT INTO `t_inner_person_record` VALUES ('622703', '2013-12-27 19:56:13', 'IN', '1', 'normal', null, '16');
INSERT INTO `t_inner_person_record` VALUES ('622704', '2013-12-27 20:04:12', 'IN', '1', 'normal', null, '15');
INSERT INTO `t_inner_person_record` VALUES ('622705', '2013-12-27 20:38:41', 'IN', '1', 'warning', null, '983043');
INSERT INTO `t_inner_person_record` VALUES ('622711', '2013-12-28 10:26:00', 'OUT', '1', 'normal', null, '11');
INSERT INTO `t_inner_person_record` VALUES ('622712', '2013-12-28 10:53:22', 'OUT', '1', 'normal', null, '15');
INSERT INTO `t_inner_person_record` VALUES ('622713', '2013-12-28 11:26:17', 'OUT', '1', 'normal', null, '1016142');
INSERT INTO `t_inner_person_record` VALUES ('622714', '2013-12-28 11:55:42', 'OUT', '1', 'normal', null, '19');
INSERT INTO `t_inner_person_record` VALUES ('622715', '2013-12-28 12:26:34', 'IN', '1', 'normal', null, '19');
INSERT INTO `t_inner_person_record` VALUES ('622716', '2013-12-28 13:47:36', 'IN', '1', 'normal', null, '15');
INSERT INTO `t_inner_person_record` VALUES ('622717', '2013-12-28 13:54:30', 'IN', '1', 'normal', null, '10');
INSERT INTO `t_inner_person_record` VALUES ('622723', '2013-12-29 09:31:34', 'IN', '1', 'normal', null, '1016142');
INSERT INTO `t_inner_person_record` VALUES ('622725', '2013-12-29 11:11:29', 'IN', '1', 'normal', null, '26');
INSERT INTO `t_inner_person_record` VALUES ('622727', '2013-12-29 14:57:05', 'IN', '1', 'normal', null, '28');
INSERT INTO `t_inner_person_record` VALUES ('655364', '2013-12-29 18:33:45', 'OUT', '1', 'normal', null, '1016142');
INSERT INTO `t_inner_person_record` VALUES ('655366', '2013-12-29 18:42:28', 'IN', '1', 'normal', null, '13');
INSERT INTO `t_inner_person_record` VALUES ('655367', '2013-12-29 20:34:45', 'IN', '1', 'normal', null, '1016142');
INSERT INTO `t_inner_person_record` VALUES ('655368', '2013-12-29 20:34:47', 'IN', '1', 'normal', null, '26');
INSERT INTO `t_inner_person_record` VALUES ('655372', '2013-12-30 07:25:17', 'IN', '1', 'normal', null, '9');
INSERT INTO `t_inner_person_record` VALUES ('655373', '2013-12-30 07:31:46', 'IN', '1', 'normal', null, '8');
INSERT INTO `t_inner_person_record` VALUES ('655375', '2013-12-30 07:39:58', 'IN', '1', 'normal', null, '29');
INSERT INTO `t_inner_person_record` VALUES ('655377', '2013-12-30 07:49:33', 'IN', '1', 'normal', null, '28');
INSERT INTO `t_inner_person_record` VALUES ('688128', '2013-12-30 09:53:29', 'OUT', '1', 'normal', null, '19');
INSERT INTO `t_inner_person_record` VALUES ('688130', '2013-12-30 10:20:19', 'IN', '1', 'normal', null, '19');
INSERT INTO `t_inner_person_record` VALUES ('688135', '2013-12-30 14:09:44', 'OUT', '1', 'normal', null, '19');
INSERT INTO `t_inner_person_record` VALUES ('688138', '2013-12-30 15:33:24', 'IN', '1', 'normal', null, '19');
INSERT INTO `t_inner_person_record` VALUES ('688143', '2013-12-30 17:36:25', 'OUT', '1', 'normal', null, '9');
INSERT INTO `t_inner_person_record` VALUES ('688146', '2013-12-31 08:22:25', 'OUT', '1', 'normal', null, '29');
INSERT INTO `t_inner_person_record` VALUES ('688147', '2013-12-31 08:22:27', 'OUT', '1', 'normal', null, '19');
INSERT INTO `t_inner_person_record` VALUES ('688148', '2013-12-31 08:22:28', 'OUT', '1', 'normal', null, '8');
INSERT INTO `t_inner_person_record` VALUES ('688161', '2013-12-31 08:34:24', 'IN', '1', 'normal', null, '28');
INSERT INTO `t_inner_person_record` VALUES ('688184', '2013-12-31 15:05:11', 'OUT', '1', 'normal', null, '19');
INSERT INTO `t_inner_person_record` VALUES ('688186', '2013-12-31 15:24:42', 'IN', '1', 'normal', null, '28');
INSERT INTO `t_inner_person_record` VALUES ('688188', '2013-12-31 16:11:47', 'IN', '1', 'normal', null, '19');
INSERT INTO `t_inner_person_record` VALUES ('688191', '2013-12-31 17:33:22', 'OUT', '1', 'normal', null, '30');
INSERT INTO `t_inner_person_record` VALUES ('688195', '2013-12-31 17:57:56', 'OUT', '1', 'normal', null, '9');
INSERT INTO `t_inner_person_record` VALUES ('720912', '2014-01-01 21:08:03', 'IN', '1', 'warning', null, '30');
INSERT INTO `t_inner_person_record` VALUES ('720915', '2014-01-02 07:28:04', 'IN', '1', 'normal', null, '9');
INSERT INTO `t_inner_person_record` VALUES ('720933', '2014-01-02 17:24:38', 'IN', '1', 'normal', null, '6');
INSERT INTO `t_inner_person_record` VALUES ('720935', '2014-01-02 17:58:22', 'OUT', '1', 'normal', null, '8');
INSERT INTO `t_inner_person_record` VALUES ('720936', '2014-01-02 18:11:18', 'OUT', '1', 'normal', null, '29');
INSERT INTO `t_inner_person_record` VALUES ('720939', '2014-01-02 19:01:32', 'OUT', '1', 'warning', null, '19');
INSERT INTO `t_inner_person_record` VALUES ('720940', '2014-01-02 20:25:53', 'IN', '1', 'warning', null, '19');
INSERT INTO `t_inner_person_record` VALUES ('720943', '2014-01-03 07:29:58', 'IN', '1', 'normal', null, '8');
INSERT INTO `t_inner_person_record` VALUES ('720946', '2014-01-03 08:01:12', 'IN', '1', 'normal', null, '6');
INSERT INTO `t_inner_person_record` VALUES ('720949', '2014-01-03 08:31:55', 'IN', '1', 'normal', null, '19');
INSERT INTO `t_inner_person_record` VALUES ('720982', '2014-01-03 17:47:37', 'OUT', '1', 'normal', null, '27');
INSERT INTO `t_inner_person_record` VALUES ('720983', '2014-01-03 18:18:48', 'IN', '1', 'normal', null, '28');
INSERT INTO `t_inner_person_record` VALUES ('720984', '2014-01-03 18:24:57', 'OUT', '1', 'normal', null, '8');
INSERT INTO `t_inner_person_record` VALUES ('720986', '2014-01-04 08:21:29', 'IN', '1', 'normal', null, '6');
INSERT INTO `t_inner_person_record` VALUES ('720988', '2014-01-04 08:40:52', 'IN', '1', 'normal', null, '11');

-- ----------------------------
-- Table structure for `t_inner_person_sa_t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_inner_person_sa_t_user`;
CREATE TABLE `t_inner_person_sa_t_user` (
  `t_inner_person_id` bigint(20) NOT NULL,
  `users_id` bigint(20) NOT NULL,
  KEY `FK_rho5nkus18o0c4898rmqqgdfl` (`users_id`),
  KEY `FK_chlv6m9wekv4jx3u6wkejvepg` (`t_inner_person_id`),
  CONSTRAINT `FK_chlv6m9wekv4jx3u6wkejvepg` FOREIGN KEY (`t_inner_person_id`) REFERENCES `t_inner_person` (`id`),
  CONSTRAINT `FK_rho5nkus18o0c4898rmqqgdfl` FOREIGN KEY (`users_id`) REFERENCES `sa_t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of t_inner_person_sa_t_user
-- ----------------------------
INSERT INTO `t_inner_person_sa_t_user` VALUES ('2', '32768');
INSERT INTO `t_inner_person_sa_t_user` VALUES ('4', '32768');
INSERT INTO `t_inner_person_sa_t_user` VALUES ('5', '32768');
INSERT INTO `t_inner_person_sa_t_user` VALUES ('8', '32768');
INSERT INTO `t_inner_person_sa_t_user` VALUES ('9', '32768');
INSERT INTO `t_inner_person_sa_t_user` VALUES ('10', '32768');
INSERT INTO `t_inner_person_sa_t_user` VALUES ('11', '32768');
INSERT INTO `t_inner_person_sa_t_user` VALUES ('12', '32768');
INSERT INTO `t_inner_person_sa_t_user` VALUES ('13', '32768');
INSERT INTO `t_inner_person_sa_t_user` VALUES ('14', '32768');
INSERT INTO `t_inner_person_sa_t_user` VALUES ('15', '32768');
INSERT INTO `t_inner_person_sa_t_user` VALUES ('16', '32768');
INSERT INTO `t_inner_person_sa_t_user` VALUES ('19', '32768');
INSERT INTO `t_inner_person_sa_t_user` VALUES ('20', '32768');
INSERT INTO `t_inner_person_sa_t_user` VALUES ('25', '32768');
INSERT INTO `t_inner_person_sa_t_user` VALUES ('26', '32768');
INSERT INTO `t_inner_person_sa_t_user` VALUES ('27', '32768');
INSERT INTO `t_inner_person_sa_t_user` VALUES ('28', '32768');
INSERT INTO `t_inner_person_sa_t_user` VALUES ('29', '32768');
INSERT INTO `t_inner_person_sa_t_user` VALUES ('30', '32768');
INSERT INTO `t_inner_person_sa_t_user` VALUES ('21', '1');
INSERT INTO `t_inner_person_sa_t_user` VALUES ('1', '1');
INSERT INTO `t_inner_person_sa_t_user` VALUES ('6', '32768');
INSERT INTO `t_inner_person_sa_t_user` VALUES ('983044', '32768');
INSERT INTO `t_inner_person_sa_t_user` VALUES ('1016142', '32768');
INSERT INTO `t_inner_person_sa_t_user` VALUES ('983043', '32768');

-- ----------------------------
-- Table structure for `t_leave`
-- ----------------------------
DROP TABLE IF EXISTS `t_leave`;
CREATE TABLE `t_leave` (
  `id` bigint(20) NOT NULL,
  `agree` tinyint(1) DEFAULT NULL,
  `approvePerson` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `approve_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `end_time` datetime NOT NULL,
  `reason` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `remark` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `person_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_4lqjos9mr7yopxnuhvqiamkf9` (`person_id`),
  CONSTRAINT `FK_4lqjos9mr7yopxnuhvqiamkf9` FOREIGN KEY (`person_id`) REFERENCES `t_inner_person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_leave
-- ----------------------------
INSERT INTO `t_leave` VALUES ('1', '1', '超级管理员', '2013-12-13 14:56:21', '2013-12-13 14:49:03', '2013-12-13 16:49:03', '32769', null, '2013-12-13 14:49:03', '1');
INSERT INTO `t_leave` VALUES ('2', '1', '超级管理员', '2013-12-13 15:31:35', '2013-12-13 14:56:21', '2013-12-13 16:56:21', '1', null, '2013-12-13 14:56:21', '1');
INSERT INTO `t_leave` VALUES ('32768', '1', '超级管理员', '2013-12-13 16:07:12', '2013-12-13 16:05:55', '2013-12-13 18:05:55', '这是一个短信申请测试', null, '2013-12-13 16:05:55', '1');
INSERT INTO `t_leave` VALUES ('32769', '1', '赵书民', '2013-12-13 16:12:34', '2013-12-13 16:10:35', '2013-12-15 18:10:35', '回家', null, '2013-12-13 16:10:35', '30');
INSERT INTO `t_leave` VALUES ('65536', '1', '赵书民', '2013-12-13 16:51:35', '2013-12-13 16:50:34', '2013-12-16 08:00:00', '回家', null, '2013-12-13 16:50:34', '28');
INSERT INTO `t_leave` VALUES ('65537', '1', '赵书民', '2013-12-13 16:51:54', '2013-12-13 16:51:04', '2013-12-15 21:30:00', '回家', null, '2013-12-13 16:51:04', '27');
INSERT INTO `t_leave` VALUES ('65538', '1', '赵书民', '2013-12-13 17:08:12', '2013-12-13 17:07:32', '2013-12-16 08:00:00', '回家', null, '2013-12-13 17:07:32', '6');
INSERT INTO `t_leave` VALUES ('65539', '1', '超级管理员', '2013-12-13 17:22:38', '2013-12-13 17:22:38', '2013-12-13 21:00:00', '家里来人，出去吃饭', '提交已批准的审批。', '2013-12-13 17:30:00', '25');
INSERT INTO `t_leave` VALUES ('98304', '1', '超级管理员', '2013-12-14 14:15:33', '2013-12-14 14:15:33', '2013-12-15 21:30:00', '回家', '提交已批准的审批。', '2013-12-14 14:30:00', '26');
INSERT INTO `t_leave` VALUES ('98305', '1', '超级管理员', '2013-12-14 18:05:27', '2013-12-14 18:05:27', '2013-12-14 21:00:00', '理发洗澡', '提交已批准的审批。', '2013-12-14 18:05:00', '20');
INSERT INTO `t_leave` VALUES ('98306', '1', '赵书民', '2013-12-15 12:45:32', '2013-12-15 12:44:37', '2013-12-15 13:00:00', '测试', null, '2013-12-15 12:44:38', '26');
INSERT INTO `t_leave` VALUES ('131072', '1', '超级管理员', '2013-12-15 13:23:17', '2013-12-15 13:19:21', '2013-12-15 13:30:00', '测试', null, '2013-12-15 13:19:21', '26');
INSERT INTO `t_leave` VALUES ('163840', '1', '赵书民', '2013-12-15 17:46:14', '2013-12-15 17:45:15', '2013-12-15 22:00:00', '事假', null, '2013-12-15 17:45:15', '1016142');
INSERT INTO `t_leave` VALUES ('163841', '1', '赵书民', '2013-12-15 17:57:42', '2013-12-15 17:56:26', '2013-12-15 22:00:00', '根领导出去。', null, '2013-12-15 17:56:26', '19');
INSERT INTO `t_leave` VALUES ('196608', '1', '赵书民', '2013-12-16 17:10:05', '2013-12-16 17:08:39', '2013-12-17 08:00:00', '回家', null, '2013-12-16 17:08:39', '6');
INSERT INTO `t_leave` VALUES ('196609', '1', '赵书民', '2013-12-16 18:07:36', '2013-12-16 18:06:34', '2013-12-16 08:00:00', '回家', null, '2013-12-16 18:06:34', '8');
INSERT INTO `t_leave` VALUES ('196610', '1', '超级管理员', '2013-12-16 18:16:48', '2013-12-16 18:16:47', '2013-12-16 21:00:00', '洗澡', '提交已批准的审批。', '2013-12-16 18:15:10', '16');
INSERT INTO `t_leave` VALUES ('196611', '1', '超级管理员', '2013-12-16 18:22:23', '2013-12-16 18:22:23', '2013-12-17 08:00:00', '回家', '提交已批准的审批。', '2013-12-16 18:19:49', '8');
INSERT INTO `t_leave` VALUES ('229376', '1', '超级管理员', '2013-12-16 18:34:37', '2013-12-16 18:34:37', '2013-12-16 21:00:00', '理发', '提交已批准的审批。', '2013-12-16 18:33:36', '15');
INSERT INTO `t_leave` VALUES ('262144', '0', null, null, '2013-12-16 18:49:06', '2013-12-16 21:00:00', '根领导出去。', null, '2013-12-16 18:49:06', '19');
INSERT INTO `t_leave` VALUES ('262145', '1', '赵书民', '2013-12-16 18:51:50', '2013-12-16 18:49:26', '2013-12-16 21:00:00', '根领导出去。', null, '2013-12-16 18:49:26', '19');
INSERT INTO `t_leave` VALUES ('294912', '1', '超级管理员', '2013-12-17 16:02:54', '2013-12-17 16:01:53', '2013-12-23 08:00:00', '事件', '提交已批准的审批。', '2013-12-17 16:00:00', '11');
INSERT INTO `t_leave` VALUES ('294913', '1', '赵书民', '2013-12-17 17:13:41', '2013-12-17 17:12:13', '2013-12-18 08:00:00', '回家', null, '2013-12-17 17:12:13', '6');
INSERT INTO `t_leave` VALUES ('327680', '0', null, null, '2013-12-17 19:11:19', '2013-12-17 21:00:00', '理发', null, '2013-12-17 19:11:19', '28');
INSERT INTO `t_leave` VALUES ('360448', '1', '超级管理员', '2013-12-18 09:47:14', '2013-12-18 09:44:08', '2013-12-18 10:00:00', '测试', null, '2013-12-18 09:44:08', '1016142');
INSERT INTO `t_leave` VALUES ('360449', '1', '赵书民', '2013-12-18 17:03:34', '2013-12-18 17:02:27', '2013-12-19 08:00:00', '回家', null, '2013-12-18 17:02:27', '6');
INSERT INTO `t_leave` VALUES ('393216', '1', '超级管理员', '2013-12-18 19:00:56', '2013-12-18 19:00:56', '2013-12-19 07:30:00', '回家。', '提交已批准的审批。', '2013-12-18 18:57:08', '9');
INSERT INTO `t_leave` VALUES ('393217', '1', '赵书民', '2013-12-18 19:22:51', '2013-12-18 19:21:26', '2013-12-18 19:30:00', '测试', null, '2013-12-18 19:21:26', '1016142');
INSERT INTO `t_leave` VALUES ('393218', '1', '超级管理员', '2013-12-18 19:29:22', '2013-12-18 19:29:22', '2013-12-19 11:28:51', '回家，带人员体检。', '提交已批准的审批。', '2013-12-18 19:27:41', '29');
INSERT INTO `t_leave` VALUES ('393219', '1', '超级管理员', '2013-12-19 11:33:58', '2013-12-19 11:33:58', '2013-12-22 22:00:00', '孩子有病，回家。', '提交已批准的审批。', '2013-12-19 11:33:11', '15');
INSERT INTO `t_leave` VALUES ('425984', '1', '超级管理员', '2013-12-19 18:04:38', '2013-12-19 18:04:38', '2013-12-20 07:30:00', '回家', '提交已批准的审批。', '2013-12-19 17:56:29', '9');
INSERT INTO `t_leave` VALUES ('425985', '1', '超级管理员', '2013-12-19 18:20:06', '2013-12-19 18:20:06', '2013-12-20 07:30:00', '回家', '提交已批准的审批。', '2013-12-19 17:56:29', '29');
INSERT INTO `t_leave` VALUES ('425986', '1', '赵书民', '2013-12-19 22:02:10', '2013-12-19 22:01:06', '2013-12-20 08:00:00', '回家', null, '2013-12-19 22:01:06', '8');
INSERT INTO `t_leave` VALUES ('425987', '1', '赵书民', '2013-12-19 23:00:12', '2013-12-19 22:58:18', '2013-12-20 08:00:00', '回家', null, '2013-12-19 22:58:18', '6');
INSERT INTO `t_leave` VALUES ('458752', '1', '赵书民', '2013-12-20 18:33:24', '2013-12-20 18:31:48', '2013-12-23 07:30:00', '回家', null, '2013-12-20 18:31:48', '9');
INSERT INTO `t_leave` VALUES ('458753', '1', '赵书民', '2013-12-20 18:33:39', '2013-12-20 18:31:48', '2013-12-23 08:30:00', '回家', null, '2013-12-20 18:31:48', '6');
INSERT INTO `t_leave` VALUES ('458754', '0', null, null, '2013-12-20 18:32:08', '2013-12-23 08:30:00', '回家', null, '2013-12-20 18:32:08', '6');
INSERT INTO `t_leave` VALUES ('458755', '1', '赵书民', '2013-12-20 18:34:02', '2013-12-20 18:33:02', '2013-12-20 22:00:00', '洗澡', null, '2013-12-20 18:33:02', '1016142');
INSERT INTO `t_leave` VALUES ('458756', '1', '超级管理员', '2013-12-20 18:46:26', '2013-12-20 18:46:26', '2013-12-23 08:00:00', '回家', '提交已批准的审批。', '2013-12-20 18:32:33', '29');
INSERT INTO `t_leave` VALUES ('458757', '1', '超级管理员', '2013-12-21 09:03:13', '2013-12-21 09:03:13', '2013-12-21 11:30:48', '洗澡', '提交已批准的审批。', '2013-12-21 09:30:52', '983043');
INSERT INTO `t_leave` VALUES ('458758', '1', '赵书民', '2013-12-21 13:34:55', '2013-12-21 13:33:03', '2013-12-21 14:30:00', '去干洗点', null, '2013-12-21 13:33:03', '19');
INSERT INTO `t_leave` VALUES ('458759', '1', '超级管理员', '2013-12-21 17:10:56', '2013-12-21 17:10:56', '2013-12-22 18:00:00', '回家', '提交已批准的审批。', '2013-12-21 17:09:57', '26');
INSERT INTO `t_leave` VALUES ('458760', '1', '超级管理员', '2013-12-21 17:38:51', '2013-12-21 17:38:51', '2013-12-21 21:00:00', '洗澡', '提交已批准的审批。', '2013-12-21 19:00:00', '20');
INSERT INTO `t_leave` VALUES ('458761', '1', '超级管理员', '2013-12-22 11:26:17', '2013-12-22 11:26:16', '2013-12-25 22:00:00', '续假', '提交已批准的审批。', '2013-12-22 22:00:00', '15');
INSERT INTO `t_leave` VALUES ('458762', '1', '赵书民', '2013-12-22 13:15:05', '2013-12-22 13:14:06', '2013-12-22 15:00:00', '买水果', null, '2013-12-22 13:14:06', '19');
INSERT INTO `t_leave` VALUES ('458763', '1', '超级管理员', '2013-12-23 12:26:19', '2013-12-23 12:26:19', '2013-12-29 22:00:00', '事假', '提交已批准的审批。', '2013-12-23 12:25:46', '13');
INSERT INTO `t_leave` VALUES ('458764', '1', '赵书民', '2013-12-23 17:16:30', '2013-12-23 17:15:26', '2013-12-23 19:15:26', '回家', null, '2013-12-23 17:15:26', '9');
INSERT INTO `t_leave` VALUES ('458765', '1', '赵书民', '2013-12-23 17:43:47', '2013-12-23 17:42:26', '2013-12-24 08:10:00', '回家', null, '2013-12-23 17:42:26', '28');
INSERT INTO `t_leave` VALUES ('458766', '1', '超级管理员', '2013-12-23 18:08:33', '2013-12-23 18:08:33', '2013-12-24 08:00:00', '回家', '提交已批准的审批。', '2013-12-23 18:07:53', '8');
INSERT INTO `t_leave` VALUES ('458767', '1', '赵书民', '2013-12-23 18:10:15', '2013-12-23 18:09:18', '2013-12-24 11:30:00', '回家明天去二院体检。', null, '2013-12-23 18:09:18', '29');
INSERT INTO `t_leave` VALUES ('458768', '1', '赵书民', '2013-12-23 18:52:51', '2013-12-23 18:51:57', '2013-12-23 20:30:00', '洗澡', null, '2013-12-23 18:51:57', '19');
INSERT INTO `t_leave` VALUES ('458769', '1', '超级管理员', '2013-12-23 19:43:05', '2013-12-23 19:43:05', '2013-12-23 21:30:40', '洗澡', '提交已批准的审批。', '2013-12-23 19:42:26', '26');
INSERT INTO `t_leave` VALUES ('458770', '0', '超级管理员', '2013-12-23 21:10:08', '2013-12-23 21:10:00', '2013-12-24 02:30:26', '公差', '提交已批准的审批。', '2013-12-23 21:09:18', '11');
INSERT INTO `t_leave` VALUES ('458771', '1', '赵书民', '2013-12-23 22:38:02', '2013-12-23 22:37:10', '2013-12-24 08:30:00', '回家', null, '2013-12-23 22:37:10', '6');
INSERT INTO `t_leave` VALUES ('491520', '1', '赵书民', '2013-12-24 18:03:19', '2013-12-24 17:56:12', '2013-12-24 20:00:00', '购物', null, '2013-12-24 17:56:12', '28');
INSERT INTO `t_leave` VALUES ('491521', '1', '赵书民', '2013-12-24 18:02:50', '2013-12-24 18:00:59', '2013-12-24 20:00:00', '买菜', null, '2013-12-24 18:00:59', '19');
INSERT INTO `t_leave` VALUES ('491522', '1', '赵书民', '2013-12-24 18:35:30', '2013-12-24 18:34:24', '2013-12-25 08:00:00', '回家请批示', null, '2013-12-24 18:34:24', '29');
INSERT INTO `t_leave` VALUES ('491523', '1', '赵书民', '2013-12-24 18:50:48', '2013-12-24 18:49:51', '2013-12-25 08:30:00', '回家', null, '2013-12-24 18:49:51', '6');
INSERT INTO `t_leave` VALUES ('491524', '1', '超级管理员', '2013-12-24 19:15:17', '2013-12-24 19:15:16', '2013-12-24 19:45:37', '买东西', '提交已批准的审批。', '2013-12-24 19:14:20', '20');
INSERT INTO `t_leave` VALUES ('491525', '1', '赵书民', '2013-12-24 19:58:05', '2013-12-24 19:57:01', '2013-12-24 21:57:01', '回家', null, '2013-12-24 19:57:01', '9');
INSERT INTO `t_leave` VALUES ('491526', '1', '超级管理员', '2013-12-25 17:04:34', '2013-12-25 17:04:34', '2013-12-26 07:30:00', '回家', '提交已批准的审批。', '2013-12-25 17:03:46', '9');
INSERT INTO `t_leave` VALUES ('491527', '1', '超级管理员', '2013-12-25 17:05:40', '2013-12-25 17:05:40', '2013-12-26 08:00:00', '回家', '提交已批准的审批。', '2013-12-25 17:03:46', '6');
INSERT INTO `t_leave` VALUES ('491528', '1', '超级管理员', '2013-12-25 17:06:09', '2013-12-25 17:06:09', '2013-12-26 08:00:00', '回家', '提交已批准的审批。', '2013-12-25 17:03:46', '8');
INSERT INTO `t_leave` VALUES ('491529', '1', '超级管理员', '2013-12-25 17:06:38', '2013-12-25 17:06:38', '2013-12-26 10:00:00', '家中有事', '提交已批准的审批。', '2013-12-25 17:03:46', '15');
INSERT INTO `t_leave` VALUES ('491530', '1', '赵书民', '2013-12-25 17:47:53', '2013-12-25 17:46:51', '2013-12-26 08:00:00', '回家请批示', null, '2013-12-25 17:46:51', '29');
INSERT INTO `t_leave` VALUES ('491531', '1', '赵书民', '2013-12-25 20:21:12', '2013-12-25 20:20:19', '2013-12-26 08:00:00', '回家', null, '2013-12-25 20:20:19', '8');
INSERT INTO `t_leave` VALUES ('491532', '1', '赵书民', '2013-12-26 16:57:14', '2013-12-26 16:55:54', '2013-12-27 08:30:00', '回家', null, '2013-12-26 16:55:54', '6');
INSERT INTO `t_leave` VALUES ('491533', '0', null, null, '2013-12-26 17:38:47', '0132-12-12 09:00:00', '回家', null, '2013-12-26 17:38:47', '28');
INSERT INTO `t_leave` VALUES ('491534', '1', '赵书民', '2013-12-26 17:40:04', '2013-12-26 17:38:48', '0132-12-12 09:00:00', '回家', null, '2013-12-26 17:38:48', '28');
INSERT INTO `t_leave` VALUES ('524288', '1', '超级管理员', '2013-12-26 17:59:48', '2013-12-26 17:59:48', '2013-12-27 08:10:00', '回家', '提交已批准的审批。', '2013-12-26 17:58:04', '28');
INSERT INTO `t_leave` VALUES ('524289', '1', '赵书民', '2013-12-26 18:16:47', '2013-12-26 18:15:22', '2013-12-26 21:30:00', '洗澡', null, '2013-12-26 18:15:22', '19');
INSERT INTO `t_leave` VALUES ('524290', '1', '赵书民', '2013-12-26 18:26:16', '2013-12-26 18:25:03', '2013-12-27 08:00:00', '回家', null, '2013-12-26 18:25:03', '8');
INSERT INTO `t_leave` VALUES ('524291', '1', '赵书民', '2013-12-26 19:01:45', '2013-12-26 19:00:10', '2013-12-26 22:00:00', '洗澡', null, '2013-12-26 19:00:10', '1016142');
INSERT INTO `t_leave` VALUES ('524292', '1', '超级管理员', '2013-12-27 16:32:05', '2013-12-27 16:32:05', '2013-12-27 22:00:00', '陪领导', '提交已批准的审批。', '2013-12-27 17:00:00', '10');
INSERT INTO `t_leave` VALUES ('524293', '1', '超级管理员', '2013-12-27 17:02:49', '2013-12-27 17:02:49', '2013-12-28 14:00:00', '陪领导', '提交已批准的审批。', '2013-11-27 17:00:00', '10');
INSERT INTO `t_leave` VALUES ('524294', '1', '赵书民', '2013-12-27 17:54:22', '2013-12-27 17:53:22', '2013-12-30 07:30:00', '回家', null, '2013-12-27 17:53:22', '9');
INSERT INTO `t_leave` VALUES ('524295', '1', '超级管理员', '2013-12-27 18:17:17', '2013-12-27 18:17:17', '2013-12-27 20:30:00', '洗澡', '提交已批准的审批。', '2013-12-27 18:16:52', '15');
INSERT INTO `t_leave` VALUES ('524296', '1', '超级管理员', '2013-12-27 18:17:50', '2013-12-27 18:17:50', '2013-12-27 20:30:00', '洗澡', '提交已批准的审批。', '2013-12-27 18:16:52', '16');
INSERT INTO `t_leave` VALUES ('524297', '1', '赵书民', '2013-12-27 18:20:23', '2013-12-27 18:19:26', '2013-12-30 08:00:00', '回家', null, '2013-12-27 18:19:26', '8');
INSERT INTO `t_leave` VALUES ('524298', '1', '赵书民', '2013-12-27 18:23:09', '2013-12-27 18:22:07', '2013-12-30 08:00:00', '回家请批示', null, '2013-12-27 18:22:07', '29');
INSERT INTO `t_leave` VALUES ('524299', '1', '超级管理员', '2013-12-27 18:48:13', '2013-12-27 18:48:13', '2013-12-27 09:00:00', '洗澡', '提交已批准的审批。', '2013-12-27 18:16:52', '983043');
INSERT INTO `t_leave` VALUES ('524300', '1', '赵书民', '2013-12-27 19:15:33', '2013-12-27 19:14:09', '2013-12-30 08:30:00', '回家', null, '2013-12-27 19:14:09', '6');
INSERT INTO `t_leave` VALUES ('524301', '1', '超级管理员', '2013-12-28 09:31:55', '2013-12-28 09:31:55', '2013-12-30 08:10:00', '回家', '提交已批准的审批。', '2013-12-28 09:30:19', '11');
INSERT INTO `t_leave` VALUES ('524302', '1', '超级管理员', '2013-12-28 10:49:31', '2013-12-28 10:49:31', '2013-12-28 15:00:00', '事假', '提交已批准的审批。', '2013-12-28 11:00:00', '15');
INSERT INTO `t_leave` VALUES ('524303', '1', '赵书民', '2013-12-28 10:55:02', '2013-12-28 10:54:01', '2013-12-29 12:00:00', '回家', null, '2013-12-28 10:54:01', '1016142');
INSERT INTO `t_leave` VALUES ('524304', '1', '赵书民', '2013-12-28 11:45:10', '2013-12-28 11:41:12', '2013-12-28 13:00:00', '市局送材料', null, '2013-12-28 11:41:12', '19');
INSERT INTO `t_leave` VALUES ('524305', '1', '超级管理员', '2013-12-28 12:33:15', '2013-12-28 12:33:15', '2013-12-28 16:00:00', '洗澡', '提交已批准的审批。', '2013-12-28 14:00:00', '20');
INSERT INTO `t_leave` VALUES ('524306', '1', '超级管理员', '2013-12-29 11:07:35', '2013-12-29 11:07:35', '2013-12-30 08:10:00', '回家', '提交已批准的审批。', '2013-12-29 11:10:00', '26');
INSERT INTO `t_leave` VALUES ('524307', '1', '赵书民', '2013-12-29 14:47:37', '2013-12-29 14:46:29', '2013-12-30 08:10:00', '回家', null, '2013-12-29 14:46:29', '28');
INSERT INTO `t_leave` VALUES ('557056', '1', '赵书民', '2013-12-29 18:24:08', '2013-12-29 18:23:02', '2013-12-29 22:00:00', '购买生活用品', null, '2013-12-29 18:23:02', '1016142');
INSERT INTO `t_leave` VALUES ('589824', '1', '赵书民', '2013-12-30 17:34:48', '2013-12-30 17:33:47', '2013-12-31 07:30:00', '回家', null, '2013-12-30 17:33:47', '9');
INSERT INTO `t_leave` VALUES ('589825', '1', '赵书民', '2013-12-30 17:56:48', '2013-12-30 17:55:56', '2013-12-31 08:00:00', '回家', null, '2013-12-30 17:55:56', '8');
INSERT INTO `t_leave` VALUES ('589826', '1', '赵书民', '2013-12-30 18:18:53', '2013-12-30 18:17:46', '2013-12-31 08:00:00', '回家请批示', null, '2013-12-30 18:17:46', '29');
INSERT INTO `t_leave` VALUES ('589827', '1', '赵书民', '2013-12-30 18:26:29', '2013-12-30 18:25:30', '2013-12-30 20:30:00', '鲁抗买药', null, '2013-12-30 18:25:30', '19');
INSERT INTO `t_leave` VALUES ('589828', '1', '赵书民', '2013-12-30 20:39:39', '2013-12-30 20:38:38', '2013-12-31 08:30:00', '回家', null, '2013-12-30 20:38:38', '6');
INSERT INTO `t_leave` VALUES ('589829', '1', '赵书民', '2013-12-31 07:32:05', '2013-12-31 07:30:51', '2013-12-31 14:00:00', '带孩子去医院看病', null, '2013-12-31 07:30:51', '29');
INSERT INTO `t_leave` VALUES ('589830', '1', '超级管理员', '2013-12-31 08:23:08', '2013-12-31 08:23:08', '2013-12-31 18:00:00', '回家', '提交已批准的审批。', '2013-12-31 08:22:26', '28');
INSERT INTO `t_leave` VALUES ('589831', '1', '赵书民', '2013-12-31 17:11:13', '2013-12-31 17:10:12', '2014-01-01 18:00:00', '回家', null, '2013-12-31 17:10:12', '30');
INSERT INTO `t_leave` VALUES ('589832', '1', '赵书民', '2013-12-31 17:15:39', '2013-12-31 17:14:53', '2014-01-02 07:30:00', '回家', null, '2013-12-31 17:14:53', '9');
INSERT INTO `t_leave` VALUES ('589833', '1', '超级管理员', '2013-12-31 17:19:06', '2013-12-31 17:18:32', '2013-12-31 22:00:00', '外出', '提交已批准的审批。', '2013-12-31 17:30:40', '25');
INSERT INTO `t_leave` VALUES ('589834', '1', '赵书民', '2013-12-31 19:59:50', '2013-12-31 19:58:57', '2014-01-02 08:30:00', '回家', null, '2013-12-31 19:58:57', '6');
INSERT INTO `t_leave` VALUES ('622592', '1', '超级管理员', '2013-12-31 21:06:47', '2013-12-31 21:06:47', '2014-01-02 08:10:07', '回家', '提交已批准的审批。', '2013-12-31 21:05:50', '11');
INSERT INTO `t_leave` VALUES ('622593', '1', '超级管理员', '2013-12-31 22:00:30', '2013-12-31 21:35:48', '2014-01-02 08:00:00', '回家', null, '2013-12-31 21:35:48', '8');
INSERT INTO `t_leave` VALUES ('622594', '1', '超级管理员', '2013-12-31 21:59:54', '2013-12-31 21:59:54', '2014-01-02 08:00:00', '回家', '提交已批准的审批。', '2013-12-31 22:00:00', '8');
INSERT INTO `t_leave` VALUES ('622595', '1', '超级管理员', '2014-01-01 09:13:02', '2014-01-01 09:13:02', '2014-01-01 11:00:00', '洗澡', '提交已批准的审批。', '2014-01-01 09:00:00', '983043');
INSERT INTO `t_leave` VALUES ('622596', '1', '赵书民', '2014-01-02 17:18:40', '2014-01-02 17:17:20', '2014-01-03 08:30:00', '回家', null, '2014-01-02 17:17:20', '6');
INSERT INTO `t_leave` VALUES ('622597', '1', '赵书民', '2014-01-02 17:52:25', '2014-01-02 17:51:39', '2014-01-03 08:00:00', '回家', null, '2014-01-02 17:51:39', '8');
INSERT INTO `t_leave` VALUES ('622598', '1', '赵书民', '2014-01-02 18:05:23', '2014-01-02 18:04:32', '2014-01-03 08:00:00', '孩子生病回家照顾', null, '2014-01-02 18:04:32', '29');
INSERT INTO `t_leave` VALUES ('622599', '1', '赵书民', '2014-01-02 18:51:06', '2014-01-02 18:50:06', '2013-01-02 20:30:00', '洗澡', null, '2014-01-02 18:50:06', '19');
INSERT INTO `t_leave` VALUES ('622600', '1', '超级管理员', '2014-01-03 17:45:23', '2014-01-03 17:45:23', '2014-01-06 22:00:00', '回家', '提交已批准的审批。', '2014-01-03 17:30:00', '27');
INSERT INTO `t_leave` VALUES ('622601', '1', '赵书民', '2014-01-03 17:57:06', '2014-01-03 17:45:58', '2014-01-06 08:10:00', '回家', null, '2014-01-03 17:45:58', '28');
INSERT INTO `t_leave` VALUES ('622602', '1', '赵书民', '2014-01-03 17:53:29', '2014-01-03 17:50:56', '2014-01-06 08:00:00', '回家', null, '2014-01-03 17:50:56', '8');
INSERT INTO `t_leave` VALUES ('622603', '1', '赵书民', '2014-01-03 22:04:33', '2014-01-03 22:03:36', '2014-01-06 08:30:00', '回家', null, '2014-01-03 22:03:36', '6');
INSERT INTO `t_leave` VALUES ('622604', '1', '超级管理员', '2014-01-04 08:22:44', '2014-01-04 08:22:44', '2014-01-06 08:00:00', '回家', '提交已批准的审批。', '2014-01-04 08:00:00', '11');
INSERT INTO `t_leave` VALUES ('622605', '1', '超级管理员', '2014-01-04 09:07:48', '2014-01-04 09:07:48', '2014-01-05 18:00:00', '回家', '提交已批准的审批。', '2014-01-04 09:00:33', '30');

-- ----------------------------
-- Table structure for `t_notify`
-- ----------------------------
DROP TABLE IF EXISTS `t_notify`;
CREATE TABLE `t_notify` (
  `id` bigint(20) NOT NULL,
  `endTime` datetime NOT NULL,
  `name` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `startTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ex15o63s1f48u6qodwrn39ofy` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of t_notify
-- ----------------------------
INSERT INTO `t_notify` VALUES ('1', '2013-12-13 08:15:00', '车辆短信通知', '2013-12-13 18:00:00');
INSERT INTO `t_notify` VALUES ('2', '2013-12-13 08:30:00', '人员短信通知', '2013-12-13 17:30:00');

-- ----------------------------
-- Table structure for `t_outer_car`
-- ----------------------------
DROP TABLE IF EXISTS `t_outer_car`;
CREATE TABLE `t_outer_car` (
  `id` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `license` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `status` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `car_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9dmq5oxvipoiof5d646xvy1b2` (`license`),
  KEY `FK_kwelwsfr6gd7b6vmwbw0x1ov2` (`car_id`),
  CONSTRAINT `FK_kwelwsfr6gd7b6vmwbw0x1ov2` FOREIGN KEY (`car_id`) REFERENCES `t_card` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_outer_car
-- ----------------------------

-- ----------------------------
-- Table structure for `t_outer_person`
-- ----------------------------
DROP TABLE IF EXISTS `t_outer_person`;
CREATE TABLE `t_outer_person` (
  `id` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `name` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sex` varchar(5) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `card_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_b04s2pvej5dfgr2i5b9yyaed4` (`name`),
  KEY `FK_pugojeiwngr3g5ga5sjlj6kd7` (`card_id`),
  CONSTRAINT `FK_pugojeiwngr3g5ga5sjlj6kd7` FOREIGN KEY (`card_id`) REFERENCES `t_card` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_outer_person
-- ----------------------------

-- ----------------------------
-- Table structure for `t_reader`
-- ----------------------------
DROP TABLE IF EXISTS `t_reader`;
CREATE TABLE `t_reader` (
  `id` bigint(20) NOT NULL,
  `gate_number` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `number` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `controle_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_bxq69cf23sfqqsrjlidwwuist` (`controle_id`),
  CONSTRAINT `FK_bxq69cf23sfqqsrjlidwwuist` FOREIGN KEY (`controle_id`) REFERENCES `t_controller` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_reader
-- ----------------------------
INSERT INTO `t_reader` VALUES ('65539', '1', '1', 'IN', '4');
INSERT INTO `t_reader` VALUES ('65540', '1', '2', 'OUT', '1');
INSERT INTO `t_reader` VALUES ('65541', '1', '1', 'OUT', '1');
INSERT INTO `t_reader` VALUES ('65542', '1', '1', 'IN', '2');
INSERT INTO `t_reader` VALUES ('65543', '1', '2', 'OUT', '3');
