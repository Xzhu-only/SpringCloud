/*
 Navicat MySQL Data Transfer

 Source Server         : docker_mysql5.7
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : localhost:8000
 Source Schema         : student-affairs

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 21/12/2024 15:39:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for campus_card
-- ----------------------------
DROP TABLE IF EXISTS `campus_card`;
CREATE TABLE `campus_card`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `card_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '校园卡编号',
  `balance` double(10, 2) NOT NULL DEFAULT 0.00 COMMENT '校园卡余额',
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '校园卡状态',
  `issued_date` datetime NOT NULL COMMENT '发放日期',
  `last_recharge_date` datetime NOT NULL COMMENT '最后重置时间',
  `student_id` int(11) NOT NULL COMMENT '所属学生ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '校园卡表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of campus_card
-- ----------------------------
INSERT INTO `campus_card` VALUES (2, '0688826381', 2200.00, '使用中', '2024-12-20 18:00:25', '2024-12-21 07:23:23', 13);

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `course_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程名称',
  `credit` int(11) NOT NULL COMMENT '课程学分',
  `course_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程编号',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程描述',
  `delete_flag` tinyint(4) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  `teacher_id` int(11) NOT NULL COMMENT '授课老师ID',
  `create_time` datetime NOT NULL COMMENT '创建学生',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '课程表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (2, '数据库原理', 4, 'cs101', '学习数据库的基本概念与应用', 0, 15, '2024-12-20 08:47:01');
INSERT INTO `course` VALUES (3, '操作系统', 4, 'cs102', '深入学习操作系统原理和应用', 0, 14, '2024-12-20 09:59:14');
INSERT INTO `course` VALUES (4, '计算机网络', 3, 'cs103', '讲解计算机网络的基本原理与技术', 0, 15, '2024-12-20 09:59:35');
INSERT INTO `course` VALUES (5, '高等数学', 4, 'cs104', '高等数学', 0, 15, '2024-12-20 18:18:54');
INSERT INTO `course` VALUES (6, '大学物理', 3, 'cs105', '大学物理', 0, 14, '2024-12-20 18:19:09');
INSERT INTO `course` VALUES (7, '大数据', 3, 'cs106', '大数据', 0, 14, '2024-12-20 18:19:31');
INSERT INTO `course` VALUES (8, '马克思主义', 2, 'cs107', '教授马克思主意', 1, 14, '2024-12-20 23:10:59');

-- ----------------------------
-- Table structure for course_grades
-- ----------------------------
DROP TABLE IF EXISTS `course_grades`;
CREATE TABLE `course_grades`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `selection_id` int(11) NOT NULL COMMENT '选课ID',
  `grade` double(10, 2) NOT NULL DEFAULT 0.00 COMMENT '成绩',
  `grade_date` datetime NOT NULL COMMENT '成绩录入日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '成绩表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_grades
-- ----------------------------
INSERT INTO `course_grades` VALUES (1, 2, 88.00, '2024-12-20 20:36:53');
INSERT INTO `course_grades` VALUES (2, 4, 90.00, '2024-12-20 20:49:39');
INSERT INTO `course_grades` VALUES (3, 3, 88.00, '2024-12-20 20:50:19');
INSERT INTO `course_grades` VALUES (8, 51, 44.00, '2024-12-21 12:44:06');
INSERT INTO `course_grades` VALUES (9, 50, 67.00, '2024-12-21 12:44:10');
INSERT INTO `course_grades` VALUES (10, 49, 89.00, '2024-12-21 12:44:13');
INSERT INTO `course_grades` VALUES (11, 48, 33.00, '2024-12-21 12:44:16');
INSERT INTO `course_grades` VALUES (12, 47, 45.00, '2024-12-21 12:44:19');
INSERT INTO `course_grades` VALUES (13, 46, 67.00, '2024-12-21 12:44:24');
INSERT INTO `course_grades` VALUES (14, 45, 78.00, '2024-12-21 12:44:29');
INSERT INTO `course_grades` VALUES (15, 44, 100.00, '2024-12-21 12:45:36');
INSERT INTO `course_grades` VALUES (16, 43, 90.00, '2024-12-21 12:45:40');
INSERT INTO `course_grades` VALUES (17, 42, 66.00, '2024-12-21 12:45:44');
INSERT INTO `course_grades` VALUES (18, 41, 45.00, '2024-12-21 12:45:51');
INSERT INTO `course_grades` VALUES (19, 40, 88.00, '2024-12-21 12:45:54');
INSERT INTO `course_grades` VALUES (20, 39, 99.00, '2024-12-21 12:45:56');
INSERT INTO `course_grades` VALUES (21, 38, 68.00, '2024-12-21 12:46:00');
INSERT INTO `course_grades` VALUES (22, 37, 88.00, '2024-12-21 12:46:03');
INSERT INTO `course_grades` VALUES (23, 36, 55.00, '2024-12-21 12:46:07');
INSERT INTO `course_grades` VALUES (24, 35, 45.00, '2024-12-21 12:46:09');
INSERT INTO `course_grades` VALUES (25, 34, 78.00, '2024-12-21 12:46:12');
INSERT INTO `course_grades` VALUES (26, 33, 89.00, '2024-12-21 12:46:16');
INSERT INTO `course_grades` VALUES (27, 32, 100.00, '2024-12-21 12:46:20');
INSERT INTO `course_grades` VALUES (28, 31, 45.00, '2024-12-21 12:46:25');
INSERT INTO `course_grades` VALUES (29, 30, 35.00, '2024-12-21 12:46:27');
INSERT INTO `course_grades` VALUES (30, 29, 33.00, '2024-12-21 12:46:30');
INSERT INTO `course_grades` VALUES (31, 28, 88.00, '2024-12-21 12:46:33');
INSERT INTO `course_grades` VALUES (32, 27, 99.00, '2024-12-21 12:46:35');
INSERT INTO `course_grades` VALUES (33, 26, 78.00, '2024-12-21 12:46:39');
INSERT INTO `course_grades` VALUES (34, 25, 67.00, '2024-12-21 12:46:43');
INSERT INTO `course_grades` VALUES (35, 24, 88.00, '2024-12-21 12:46:46');
INSERT INTO `course_grades` VALUES (36, 23, 79.00, '2024-12-21 12:46:50');
INSERT INTO `course_grades` VALUES (37, 22, 99.00, '2024-12-21 12:46:53');
INSERT INTO `course_grades` VALUES (38, 21, 67.00, '2024-12-21 12:46:57');
INSERT INTO `course_grades` VALUES (39, 20, 56.00, '2024-12-21 12:47:00');
INSERT INTO `course_grades` VALUES (40, 19, 45.00, '2024-12-21 12:47:02');
INSERT INTO `course_grades` VALUES (41, 18, 78.00, '2024-12-21 12:47:05');
INSERT INTO `course_grades` VALUES (42, 17, 89.00, '2024-12-21 12:47:07');
INSERT INTO `course_grades` VALUES (43, 16, 68.00, '2024-12-21 12:47:11');
INSERT INTO `course_grades` VALUES (44, 15, 78.00, '2024-12-21 12:47:13');
INSERT INTO `course_grades` VALUES (45, 14, 66.00, '2024-12-21 12:47:16');
INSERT INTO `course_grades` VALUES (46, 13, 78.00, '2024-12-21 12:47:18');
INSERT INTO `course_grades` VALUES (47, 12, 99.00, '2024-12-21 12:47:20');
INSERT INTO `course_grades` VALUES (48, 11, 66.00, '2024-12-21 12:47:24');
INSERT INTO `course_grades` VALUES (49, 10, 44.00, '2024-12-21 12:47:27');
INSERT INTO `course_grades` VALUES (50, 9, 78.00, '2024-12-21 12:47:30');

-- ----------------------------
-- Table structure for course_selection
-- ----------------------------
DROP TABLE IF EXISTS `course_selection`;
CREATE TABLE `course_selection`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `student_id` int(11) NOT NULL COMMENT '学生ID',
  `course_id` int(11) NOT NULL COMMENT '课程ID',
  `selection_date` datetime NOT NULL COMMENT '选课日期',
  `semester` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '选课表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_selection
-- ----------------------------
INSERT INTO `course_selection` VALUES (2, 13, 4, '2024-12-20 17:44:08', '2024-2025第一学期');
INSERT INTO `course_selection` VALUES (3, 16, 7, '2024-12-20 20:38:50', '2024-2025第一学期');
INSERT INTO `course_selection` VALUES (4, 17, 6, '2024-12-20 20:39:02', '2024-2025第一学期');
INSERT INTO `course_selection` VALUES (9, 13, 7, '2024-12-21 12:40:39', '2024-2025第一学期');
INSERT INTO `course_selection` VALUES (10, 13, 6, '2024-12-21 12:40:42', '2024-2025第一学期');
INSERT INTO `course_selection` VALUES (11, 13, 5, '2024-12-21 12:40:45', '2024-2025第一学期');
INSERT INTO `course_selection` VALUES (12, 13, 3, '2024-12-21 12:40:51', '2024-2025第一学期');
INSERT INTO `course_selection` VALUES (13, 13, 7, '2024-12-21 12:40:54', '2024-2025第二学期');
INSERT INTO `course_selection` VALUES (14, 13, 6, '2024-12-21 12:40:56', '2024-2025第二学期');
INSERT INTO `course_selection` VALUES (15, 13, 5, '2024-12-21 12:40:59', '2024-2025第二学期');
INSERT INTO `course_selection` VALUES (16, 13, 4, '2024-12-21 12:41:06', '2024-2025第二学期');
INSERT INTO `course_selection` VALUES (17, 13, 3, '2024-12-21 12:41:08', '2024-2025第二学期');
INSERT INTO `course_selection` VALUES (18, 13, 7, '2024-12-21 12:41:13', '2025-2026第一学期');
INSERT INTO `course_selection` VALUES (19, 13, 5, '2024-12-21 12:41:16', '2025-2026第一学期');
INSERT INTO `course_selection` VALUES (20, 13, 6, '2024-12-21 12:41:20', '2025-2026第二学期');
INSERT INTO `course_selection` VALUES (21, 13, 3, '2024-12-21 12:41:22', '2025-2026第二学期');
INSERT INTO `course_selection` VALUES (22, 17, 7, '2024-12-21 12:41:34', '2024-2025第一学期');
INSERT INTO `course_selection` VALUES (23, 17, 5, '2024-12-21 12:41:40', '2024-2025第一学期');
INSERT INTO `course_selection` VALUES (24, 17, 4, '2024-12-21 12:41:43', '2024-2025第一学期');
INSERT INTO `course_selection` VALUES (25, 17, 3, '2024-12-21 12:41:47', '2024-2025第一学期');
INSERT INTO `course_selection` VALUES (26, 17, 7, '2024-12-21 12:41:49', '2024-2025第二学期');
INSERT INTO `course_selection` VALUES (27, 17, 5, '2024-12-21 12:41:52', '2024-2025第二学期');
INSERT INTO `course_selection` VALUES (28, 17, 3, '2024-12-21 12:41:55', '2024-2025第二学期');
INSERT INTO `course_selection` VALUES (29, 17, 7, '2024-12-21 12:41:58', '2025-2026第一学期');
INSERT INTO `course_selection` VALUES (30, 17, 4, '2024-12-21 12:42:00', '2025-2026第一学期');
INSERT INTO `course_selection` VALUES (31, 17, 5, '2024-12-21 12:42:03', '2025-2026第二学期');
INSERT INTO `course_selection` VALUES (32, 17, 7, '2024-12-21 12:42:06', '2025-2026第二学期');
INSERT INTO `course_selection` VALUES (33, 17, 3, '2024-12-21 12:42:10', '2025-2026第二学期');
INSERT INTO `course_selection` VALUES (34, 16, 6, '2024-12-21 12:42:23', '2024-2025第一学期');
INSERT INTO `course_selection` VALUES (35, 16, 5, '2024-12-21 12:42:26', '2024-2025第一学期');
INSERT INTO `course_selection` VALUES (36, 16, 4, '2024-12-21 12:42:28', '2024-2025第一学期');
INSERT INTO `course_selection` VALUES (37, 16, 3, '2024-12-21 12:42:30', '2024-2025第一学期');
INSERT INTO `course_selection` VALUES (38, 16, 2, '2024-12-21 12:42:36', '2024-2025第二学期');
INSERT INTO `course_selection` VALUES (39, 16, 2, '2024-12-21 12:42:39', '2025-2026第一学期');
INSERT INTO `course_selection` VALUES (40, 16, 2, '2024-12-21 12:42:43', '2025-2026第二学期');
INSERT INTO `course_selection` VALUES (41, 16, 4, '2024-12-21 12:42:47', '2025-2026第一学期');
INSERT INTO `course_selection` VALUES (42, 16, 5, '2024-12-21 12:42:50', '2025-2026第一学期');
INSERT INTO `course_selection` VALUES (43, 16, 7, '2024-12-21 12:42:53', '2025-2026第二学期');
INSERT INTO `course_selection` VALUES (44, 16, 6, '2024-12-21 12:42:56', '2025-2026第二学期');
INSERT INTO `course_selection` VALUES (45, 16, 4, '2024-12-21 12:42:59', '2025-2026第二学期');
INSERT INTO `course_selection` VALUES (46, 16, 3, '2024-12-21 12:43:01', '2025-2026第二学期');
INSERT INTO `course_selection` VALUES (47, 13, 2, '2024-12-21 12:43:14', '2024-2025第一学期');
INSERT INTO `course_selection` VALUES (48, 13, 2, '2024-12-21 12:43:17', '2025-2026第一学期');
INSERT INTO `course_selection` VALUES (49, 16, 7, '2024-12-21 12:43:26', '2024-2025第二学期');
INSERT INTO `course_selection` VALUES (50, 16, 6, '2024-12-21 12:43:30', '2025-2026第一学期');
INSERT INTO `course_selection` VALUES (51, 16, 2, '2024-12-21 12:43:46', '2024-2025第一学期');

-- ----------------------------
-- Table structure for dormitory
-- ----------------------------
DROP TABLE IF EXISTS `dormitory`;
CREATE TABLE `dormitory`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `dormitory_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '宿舍名称',
  `building_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '宿舍楼名称',
  `capacity` int(11) NOT NULL COMMENT '宿舍最大容纳人数',
  `current_occupancy` int(11) NOT NULL DEFAULT 0 COMMENT '当前已入住人数',
  `delete_flag` tinyint(4) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  `teacher_id` int(11) NOT NULL COMMENT '负责老师ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '宿舍表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dormitory
-- ----------------------------
INSERT INTO `dormitory` VALUES (1, '301', '6A栋', 5, 1, 0, 14, '2024-12-20 14:38:13');
INSERT INTO `dormitory` VALUES (2, '302', '6A栋', 4, 2, 0, 15, '2024-12-20 18:26:20');
INSERT INTO `dormitory` VALUES (3, '303', '6A栋', 8, 0, 0, 15, '2024-12-20 18:26:34');
INSERT INTO `dormitory` VALUES (4, '304', '6A栋', 4, 0, 0, 14, '2024-12-20 18:26:45');
INSERT INTO `dormitory` VALUES (5, '201', '6B栋', 5, 0, 0, 15, '2024-12-20 23:16:40');

-- ----------------------------
-- Table structure for dormitory_cleanliness_check
-- ----------------------------
DROP TABLE IF EXISTS `dormitory_cleanliness_check`;
CREATE TABLE `dormitory_cleanliness_check`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `dormitory_id` int(11) NOT NULL COMMENT '宿舍ID',
  `check_date` datetime NOT NULL COMMENT '检查日期',
  `cleanliness_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '卫生状况（合格或不合格）',
  `check_result` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '检查结果描述',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '宿舍卫生检查表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dormitory_cleanliness_check
-- ----------------------------
INSERT INTO `dormitory_cleanliness_check` VALUES (1, 1, '2024-12-20 15:43:18', '合格', '检查卫生合格', '');
INSERT INTO `dormitory_cleanliness_check` VALUES (2, 2, '2024-12-20 23:17:22', '不合格', '垃圾太多', '垃圾太多，无处下脚');
INSERT INTO `dormitory_cleanliness_check` VALUES (3, 1, '2024-12-20 23:26:32', '合格', '宿舍环境很好', '');

-- ----------------------------
-- Table structure for dormitory_discipline_record
-- ----------------------------
DROP TABLE IF EXISTS `dormitory_discipline_record`;
CREATE TABLE `dormitory_discipline_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `dormitory_id` int(11) NOT NULL COMMENT '宿舍ID',
  `incident_date` datetime NOT NULL COMMENT '违纪日期',
  `incident_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '违纪类型',
  `incident_details` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '违纪详情',
  `punishment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '处罚措施',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '宿舍违纪记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dormitory_discipline_record
-- ----------------------------
INSERT INTO `dormitory_discipline_record` VALUES (1, 1, '2024-12-20 15:58:30', '吵闹', '晚上熄灯之后依然吵闹', '一人一份检讨');
INSERT INTO `dormitory_discipline_record` VALUES (3, 1, '2024-12-21 14:32:24', '晚归', '学生晚归', '一人一份检讨');
INSERT INTO `dormitory_discipline_record` VALUES (4, 1, '2024-12-21 14:32:40', '打架', '学生打架', '检讨');
INSERT INTO `dormitory_discipline_record` VALUES (5, 1, '2024-12-21 14:32:55', '串寝', '学生串寝', '检讨');
INSERT INTO `dormitory_discipline_record` VALUES (6, 1, '2024-12-21 14:33:16', '吵闹', '熄灯后吵闹', '口头教育');
INSERT INTO `dormitory_discipline_record` VALUES (7, 2, '2024-12-20 14:34:00', '晚归', '111', '111');
INSERT INTO `dormitory_discipline_record` VALUES (8, 2, '2024-12-19 14:34:14', '吵闹', '11', '111');
INSERT INTO `dormitory_discipline_record` VALUES (9, 2, '2024-12-19 14:34:21', '晚归', '11', '111');
INSERT INTO `dormitory_discipline_record` VALUES (10, 2, '2024-12-18 14:34:30', '串寝', '111', '111');
INSERT INTO `dormitory_discipline_record` VALUES (11, 2, '2024-12-18 14:34:45', '打架', '111', '111');
INSERT INTO `dormitory_discipline_record` VALUES (12, 2, '2024-12-17 14:35:22', '吵闹', '111', '111');
INSERT INTO `dormitory_discipline_record` VALUES (13, 3, '2024-12-21 14:35:34', '晚归', '11', '111');
INSERT INTO `dormitory_discipline_record` VALUES (14, 3, '2024-12-20 14:35:42', '晚归', '111', '111');
INSERT INTO `dormitory_discipline_record` VALUES (15, 3, '2024-12-20 14:35:49', '串寝', '111', '111');
INSERT INTO `dormitory_discipline_record` VALUES (16, 3, '2024-12-20 14:35:57', '晚归', '111', '111');
INSERT INTO `dormitory_discipline_record` VALUES (17, 3, '2024-12-18 14:36:08', '吵闹', '111', '111');
INSERT INTO `dormitory_discipline_record` VALUES (18, 3, '2024-12-17 14:36:16', '串寝', '111', '111');
INSERT INTO `dormitory_discipline_record` VALUES (19, 4, '2024-12-21 14:36:30', '串寝', '111', '11');
INSERT INTO `dormitory_discipline_record` VALUES (20, 4, '2024-12-21 14:36:43', '打架', '111', '111');
INSERT INTO `dormitory_discipline_record` VALUES (21, 4, '2024-12-20 14:36:52', '晚归', '111', '111');
INSERT INTO `dormitory_discipline_record` VALUES (22, 4, '2024-12-18 14:37:00', '吵闹', '111', '111');
INSERT INTO `dormitory_discipline_record` VALUES (23, 4, '2024-12-17 14:37:10', '打架', '111', '111');
INSERT INTO `dormitory_discipline_record` VALUES (24, 4, '2024-12-17 14:37:20', '串寝', '111', '1111');
INSERT INTO `dormitory_discipline_record` VALUES (25, 5, '2024-12-21 14:37:32', '打架', '111', '111');
INSERT INTO `dormitory_discipline_record` VALUES (26, 5, '2024-12-19 14:37:40', '晚归', '111', '111');
INSERT INTO `dormitory_discipline_record` VALUES (27, 5, '2024-12-17 14:37:48', '吵闹', '111', '1111');
INSERT INTO `dormitory_discipline_record` VALUES (28, 1, '2024-12-15 14:39:33', '串寝', '111', '111');
INSERT INTO `dormitory_discipline_record` VALUES (29, 1, '2024-12-15 14:39:45', '晚归', '11', '1111');
INSERT INTO `dormitory_discipline_record` VALUES (30, 2, '2024-12-15 14:39:53', '晚归', '111', '111');
INSERT INTO `dormitory_discipline_record` VALUES (31, 2, '2024-12-15 14:40:01', '串寝', '111', '111');
INSERT INTO `dormitory_discipline_record` VALUES (32, 2, '2024-12-15 14:40:08', '晚归', '111', '111');
INSERT INTO `dormitory_discipline_record` VALUES (33, 4, '2024-12-15 14:40:16', '打架', '111', '111');
INSERT INTO `dormitory_discipline_record` VALUES (34, 5, '2024-12-15 14:40:24', '吵闹', '111', '111');
INSERT INTO `dormitory_discipline_record` VALUES (35, 1, '2024-12-16 14:41:17', '晚归', '111', '111');
INSERT INTO `dormitory_discipline_record` VALUES (36, 1, '2024-12-16 14:41:17', '打架', '111', '111');
INSERT INTO `dormitory_discipline_record` VALUES (37, 2, '2024-12-16 14:41:28', '吵闹', '111', '111');
INSERT INTO `dormitory_discipline_record` VALUES (38, 2, '2024-12-16 14:41:28', '晚归', '111', '111');
INSERT INTO `dormitory_discipline_record` VALUES (39, 3, '2024-12-16 14:41:38', '串寝', '111', '1111');
INSERT INTO `dormitory_discipline_record` VALUES (40, 4, '2024-12-16 14:41:48', '晚归', '111', '1111');
INSERT INTO `dormitory_discipline_record` VALUES (41, 5, '2024-12-16 14:42:00', '晚归', '111', '111');
INSERT INTO `dormitory_discipline_record` VALUES (42, 3, '2024-12-16 14:42:08', '晚归', '111', '111');
INSERT INTO `dormitory_discipline_record` VALUES (43, 1, '2024-12-21 14:43:18', '吵闹', '111', '111');
INSERT INTO `dormitory_discipline_record` VALUES (44, 3, '2024-12-21 14:43:23', '打架', '111', '111');
INSERT INTO `dormitory_discipline_record` VALUES (45, 4, '2024-12-21 14:43:29', '晚归', '111', '111');

-- ----------------------------
-- Table structure for dormitory_repair_request
-- ----------------------------
DROP TABLE IF EXISTS `dormitory_repair_request`;
CREATE TABLE `dormitory_repair_request`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `dormitory_id` int(11) NOT NULL COMMENT '宿舍ID',
  `request_date` datetime NOT NULL COMMENT '申请日期',
  `issue_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '问题类型',
  `issue_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '问题描述',
  `repair_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '申请状态（待处理、已完成）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '宿舍保修申请表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dormitory_repair_request
-- ----------------------------
INSERT INTO `dormitory_repair_request` VALUES (1, 1, '2024-12-20 18:48:04', '水管漏水', '卫生间水管漏水', '待处理');
INSERT INTO `dormitory_repair_request` VALUES (2, 2, '2024-12-20 23:23:54', '电器故障', '大厅灯泡坏了', '待处理');

-- ----------------------------
-- Table structure for dormitory_stay_request
-- ----------------------------
DROP TABLE IF EXISTS `dormitory_stay_request`;
CREATE TABLE `dormitory_stay_request`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `student_id` int(11) NOT NULL COMMENT '学生ID',
  `dormitory_id` int(11) NOT NULL COMMENT '宿舍ID',
  `stay_period_start` datetime NOT NULL COMMENT '留校起始日期',
  `stay_period_end` datetime NOT NULL COMMENT '留校结束日期',
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '留校原因',
  `approval_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '审批状态（待审批, 批准, 拒绝）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '宿舍留校申请表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dormitory_stay_request
-- ----------------------------
INSERT INTO `dormitory_stay_request` VALUES (2, 13, 2, '2024-12-21 00:00:00', '2024-12-31 00:00:00', '留校学习', '批准');
INSERT INTO `dormitory_stay_request` VALUES (4, 13, 1, '2024-12-22 00:00:00', '2024-12-31 00:00:00', '在校备考学习', '拒绝');

-- ----------------------------
-- Table structure for student_award_punishment
-- ----------------------------
DROP TABLE IF EXISTS `student_award_punishment`;
CREATE TABLE `student_award_punishment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `student_id` int(11) NOT NULL COMMENT '学生ID',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类型（奖励或惩罚）',
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '奖励或惩罚的原因',
  `details` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '详细描述',
  `create_time` datetime NOT NULL COMMENT '发生日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '奖惩表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_award_punishment
-- ----------------------------
INSERT INTO `student_award_punishment` VALUES (2, 13, '奖励', '学习优秀', '学习优秀，多科满分', '2024-12-20 18:20:23');
INSERT INTO `student_award_punishment` VALUES (3, 17, '惩罚', '考试作弊', '计算机考试时作弊，进行处分', '2024-12-20 18:21:03');
INSERT INTO `student_award_punishment` VALUES (4, 16, '奖励', '学期优秀', '学期优秀，奖励学期奖学金', '2024-12-20 18:21:53');

-- ----------------------------
-- Table structure for student_card
-- ----------------------------
DROP TABLE IF EXISTS `student_card`;
CREATE TABLE `student_card`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `card_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学生证编号',
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学生证状态（学生证状态（申请中、已发放、已失效）',
  `application_date` datetime NOT NULL COMMENT '申请日期',
  `issue_date` datetime NULL DEFAULT NULL COMMENT '发放日期',
  `expiration_date` datetime NULL DEFAULT NULL COMMENT '过期日期',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `student_id` int(11) NOT NULL COMMENT '所属学生ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '学生证表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_card
-- ----------------------------
INSERT INTO `student_card` VALUES (2, '0688507184', '已发放', '2024-12-20 17:55:06', '2024-12-20 23:12:46', '2028-12-20 23:12:46', '无', 13);
INSERT INTO `student_card` VALUES (4, '0707555475', '已发放', '2024-12-20 23:12:34', '2024-12-20 23:12:41', '2028-12-20 23:12:41', '', 17);

-- ----------------------------
-- Table structure for student_dormitory
-- ----------------------------
DROP TABLE IF EXISTS `student_dormitory`;
CREATE TABLE `student_dormitory`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `student_id` int(11) NOT NULL COMMENT '学生ID',
  `dormitory_id` int(11) NOT NULL COMMENT '宿舍ID',
  `move_in_date` datetime NOT NULL COMMENT '入住日期',
  `move_out_date` datetime NOT NULL COMMENT '退宿日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = ' 学生宿舍表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_dormitory
-- ----------------------------
INSERT INTO `student_dormitory` VALUES (5, 13, 2, '2024-12-21 07:36:56', '2028-12-21 07:36:56');

-- ----------------------------
-- Table structure for student_financial_aid
-- ----------------------------
DROP TABLE IF EXISTS `student_financial_aid`;
CREATE TABLE `student_financial_aid`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `student_id` int(11) NOT NULL COMMENT '学生ID',
  `aid_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '资助类型（如奖学金、助学金等）',
  `aid_amount` double(10, 2) NOT NULL COMMENT '资助金额',
  `semester` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '资助所属学期',
  `aid_source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '资助来源（如“学校奖学金”）',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '资助状态（待领取，已领取）',
  `create_time` datetime NOT NULL COMMENT '资助日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = ' 学生资助表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_financial_aid
-- ----------------------------
INSERT INTO `student_financial_aid` VALUES (1, 13, '奖学金', 2000.00, '2024-2025第一学期', '国家奖学金', '国家奖学金', '已领取', '2024-12-20 12:36:37');
INSERT INTO `student_financial_aid` VALUES (3, 16, '助学金', 3000.00, '2024-2025第一学期', '国家助学金', '', '待领取', '2024-12-20 18:24:14');
INSERT INTO `student_financial_aid` VALUES (4, 17, '奖学金', 2000.00, '2024-2025第一学期', '学期奖学金', '', '待领取', '2024-12-20 18:24:27');

-- ----------------------------
-- Table structure for student_health_record
-- ----------------------------
DROP TABLE IF EXISTS `student_health_record`;
CREATE TABLE `student_health_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `student_id` int(11) NOT NULL COMMENT '学生ID',
  `health_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '健康状况',
  `weight` double(10, 2) NOT NULL COMMENT '体重',
  `height` double(10, 2) NOT NULL COMMENT '身高',
  `blood_pressure` double(10, 2) NOT NULL COMMENT '血压',
  `vaccination_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '疫苗接种情况（如“已接种”）',
  `allergies` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '过敏史',
  `chronic_conditions` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '慢性病史',
  `create_time` datetime NOT NULL COMMENT '登记日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '学生健康记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_health_record
-- ----------------------------
INSERT INTO `student_health_record` VALUES (2, 13, '良好', 70.00, 1.80, 100.00, '已接种', '无', '无', '2024-12-20 18:13:44');
INSERT INTO `student_health_record` VALUES (3, 16, '良好', 50.00, 1.70, 90.00, '未接种', '无', '无', '2024-12-20 18:25:12');

-- ----------------------------
-- Table structure for student_registration
-- ----------------------------
DROP TABLE IF EXISTS `student_registration`;
CREATE TABLE `student_registration`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `student_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '新生姓名',
  `student_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '新生学号',
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '性别',
  `phone_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮箱',
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态，未签到、已签到、已取消',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '登记时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '新生预报登记表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_registration
-- ----------------------------

-- ----------------------------
-- Table structure for student_status
-- ----------------------------
DROP TABLE IF EXISTS `student_status`;
CREATE TABLE `student_status`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `student_id` int(11) NOT NULL COMMENT '学生ID',
  `full_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学生姓名',
  `ethnicity` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '民族',
  `native_place` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '籍贯',
  `id_card` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '身份证',
  `enrollment_year` datetime NOT NULL COMMENT '入学年份',
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学籍状态（在读、休学、退学等）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '学籍表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_status
-- ----------------------------
INSERT INTO `student_status` VALUES (1, 17, '李三', '汉族', '湖北', '435999200100012022', '2024-09-01 00:00:00', '在读', '2024-12-21 10:35:01');
INSERT INTO `student_status` VALUES (2, 16, '赵六', '汉族', '湖南', '3456666899091', '2024-09-01 00:00:00', '在读', '2024-12-21 13:00:04');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮箱',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '头像',
  `state` int(11) NOT NULL DEFAULT 0 COMMENT '用户状态，0是正常，1是封禁中',
  `role` int(11) NOT NULL COMMENT '用户角色，0是管理员，1是学生，2是教师',
  `delete_flag` tinyint(4) NOT NULL DEFAULT 0 COMMENT '逻辑删除标志',
  `create_time` datetime NOT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'admin@qq.com', 'http://localhost:8082/upload/image/1734597989359.png', 0, 0, 0, '2024-11-15 16:18:32');
INSERT INTO `user` VALUES (13, '张三', 'e10adc3949ba59abbe56e057f20f883e', 'zhangsan@qq.com', 'http://localhost:8082/upload/image/1734597856542.png', 0, 1, 0, '2024-12-19 16:44:46');
INSERT INTO `user` VALUES (14, '李四', 'e10adc3949ba59abbe56e057f20f883e', 'lis@qq.com', 'http://localhost:8082/upload/image/1734659874517.jpg', 0, 2, 0, '2024-12-20 09:57:55');
INSERT INTO `user` VALUES (15, '王五', 'e10adc3949ba59abbe56e057f20f883e', 'wangwu@qq.com', 'http://localhost:8082/upload/image/1734659890020.jpg', 0, 2, 0, '2024-12-20 09:58:11');
INSERT INTO `user` VALUES (16, '赵六', 'e10adc3949ba59abbe56e057f20f883e', 'zhaoliu@qq.com', 'http://localhost:8082/upload/image/1734684031390.jpg', 0, 1, 0, '2024-12-20 16:40:34');
INSERT INTO `user` VALUES (17, '李三', 'e10adc3949ba59abbe56e057f20f883e', 'lisan@qq.com', 'http://localhost:8082/upload/image/1734684057644.jpg', 0, 1, 0, '2024-12-20 16:40:58');
INSERT INTO `user` VALUES (18, 'jack', 'e10adc3949ba59abbe56e057f20f883e', 'jack@qq.com', 'http://localhost:8082/upload/image/1734699254620.jpg', 0, 2, 0, '2024-12-20 20:54:17');

SET FOREIGN_KEY_CHECKS = 1;
