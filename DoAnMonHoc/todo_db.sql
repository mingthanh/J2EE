-- MySQL dump 10.13  Distrib 8.0.30, for Linux (x86_64)
-- ===================================================================
-- HỆ THỐNG QUẢN LÝ CÔNG VIỆC VÀ ISSUES - DATABASE CƠ SỞ
-- DATABASE: todo_db
-- Do An Mon Hoc - Issue Tracking System
-- Được tạo ra cho việc nộp bài tập môn học
-- ===================================================================

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- ===================================================================
-- TẠO CƠ SỞ DỮ LIỆU
-- ===================================================================
DROP DATABASE IF EXISTS `todo_db`;
CREATE DATABASE `todo_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;
USE `todo_db`;

-- ===================================================================
-- BẢNG: status_type - TRẠNG THÁI CỦA VẤN ĐỀ
-- MỤC ĐÍCH: Lưu trữ các trạng thái có thể của một vấn đề (TODO, IN_PROGRESS, DONE, REVIEW)
-- CỘT: id (khóa chính), name (tên trạng thái), description (mô tả)
-- ===================================================================
DROP TABLE IF EXISTS `status_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- DỮ LIỆU MẪU: Các trạng thái của vấn đề
-- 1: TODO - Vấn đề mới tạo, chưa bắt đầu
-- 2: IN_PROGRESS - Vấn đề đang được làm việc
-- 3: DONE - Vấn đề đã hoàn thành
-- 4: REVIEW - Vấn đề đang chờ review
INSERT INTO `status_type` VALUES (1,'TODO','Issue is newly created'),(2,'IN_PROGRESS','Issue is being worked on'),(3,'DONE','Issue is completed'),(4,'REVIEW','Issue is waiting for review');

-- ===================================================================
-- BẢNG: priority_type - MỨC ĐỘ ƯU TIÊN
-- MỤC ĐÍCH: Lưu trữ các mức độ ưu tiên (LOW, MEDIUM, HIGH)
-- CỘT: id (khóa chính), name (tên mức ưu tiên), level (cấp độ ưu tiên)
-- ===================================================================
DROP TABLE IF EXISTS `priority_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `priority_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `level` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- DỮ LIỆU MẪU: Các mức độ ưu tiên
-- 1: LOW - Độ ưu tiên thấp
-- 2: MEDIUM - Độ ưu tiên trung bình
-- 3: HIGH - Độ ưu tiên cao
INSERT INTO `priority_type` VALUES (1,'LOW',1),(2,'MEDIUM',2),(3,'HIGH',3);

-- ===================================================================
-- BẢNG: issue_type - LOẠI VẤN ĐỀ
-- MỤC ĐÍCH: Lưu trữ các loại vấn đề (TASK, BUG, STORY)
-- CỘT: id (khóa chính), name (tên loại vấn đề)
-- ===================================================================
DROP TABLE IF EXISTS `issue_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `issue_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- DỮ LIỆU MẪU: Các loại vấn đề/công việc
-- 1: TASK - Nhiệm vụ thông thường
-- 2: BUG - Lỗi cần sửa
-- 3: STORY - Tính năng/Story trong dự án
INSERT INTO `issue_type` VALUES (1,'TASK'),(2,'BUG'),(3,'STORY');

-- ===================================================================
-- BẢNG: users - THÔNG TIN NGƯỜI DÙNG
-- MỤC ĐÍCH: Lưu trữ thông tin cá nhân, tài khoản đăng nhập của người dùng
-- CỘT: id (UUID), username (tên đăng nhập), email, password (mã hóa BCrypt)
--      display_name (tên hiển thị), avatar_url (avatar), role (vai trò), 
--      active (hoạt động), created_at (ngày tạo)
-- ===================================================================
DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `username` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `display_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `avatar_url` longtext COLLATE utf8mb4_unicode_ci,
  `role` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- DỮ LIỆU MẪU: 3 người dùng mẫu
-- Mật khẩu: password123 (đã mã hóa bằng BCrypt)
-- 
-- admin-user-001: Tài khoản quản trị viên (SUPER_ADMIN) - có quyền quản lý toàn bộ hệ thống
-- user-001: Tài khoản người dùng bình thường (member)
-- user-002: Tài khoản người dùng bình thường (member)
INSERT INTO `users` VALUES 
('admin-user-001','admin','admin@example.com','$2a$10$slYQmyNdGzin7olVN3p5Be0DlH07IqqqdyG8QQrsparUR3Ep2HYAO','Administrator','','SUPER_ADMIN',1,'2026-04-06 10:00:00.000000'),
('user-001','user1','user1@example.com','$2a$10$slYQmyNdGzin7olVN3p5Be0DlH07IqqqdyG8QQrsparUR3Ep2HYAO','User One',NULL,NULL,1,'2026-04-06 10:00:00.000000'),
('user-002','user2','user2@example.com','$2a$10$slYQmyNdGzin7olVN3p5Be0DlH07IqqqdyG8QQrsparUR3Ep2HYAO','User Two',NULL,NULL,1,'2026-04-06 10:00:00.000000');

-- ===================================================================
-- BẢNG: projects - DỰ ÁN
-- MỤC ĐÍCH: Lưu trữ thông tin các dự án/projects
-- CỘT: id (UUID), name (tên dự án), key (mã dự án - dùng trong issue key)
--      description (mô tả dự án), active (dự án còn hoạt động), 
--      lead_id (người dẫn dắt dự án), created_at (ngày tạo)
-- ===================================================================
DROP TABLE IF EXISTS `projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projects` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `key` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` text COLLATE utf8mb4_unicode_ci,
  `active` bit(1) NOT NULL,
  `lead_id` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `key` (`key`),
  KEY `lead_id` (`lead_id`),
  CONSTRAINT `projects_ibfk_1` FOREIGN KEY (`lead_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- DỮ LIỆU MẪU: 2 dự án
-- 
-- proj-001: Hệ thống quản lý dự án (PM)
--   - Mã: PM
--   - Người dẫn dắt: admin
-- 
-- proj-002: Nền tảng thương mại điện tử (ECOM)
--   - Mã: ECOM
--   - Người dẫn dắt: admin
INSERT INTO `projects` VALUES 
('proj-001','Project Management System','PM','A system to manage projects and issues',1,'admin-user-001','2026-04-06 10:00:00.000000'),
('proj-002','E-Commerce Platform','ECOM','An e-commerce platform for online shopping',1,'admin-user-001','2026-04-06 10:30:00.000000');

-- ===================================================================
-- BẢNG: sprints - CÁC GIAI ĐOẠN DỰ ÁN (SPRINT)
-- MỤC ĐÍCH: Lưu trữ các sprint/giai đoạn phát triển của dự án
-- CỘT: id (UUID), name (tên sprint), description (mô tả)
--      start_date (ngày bắt đầu), end_date (ngày kết thúc), 
--      active (sprint còn hoạt động), project_id (dự án thuộc về)
-- ===================================================================
DROP TABLE IF EXISTS `sprints`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sprints` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` text COLLATE utf8mb4_unicode_ci,
  `start_date` datetime(6) DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `project_id` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `project_id` (`project_id`),
  CONSTRAINT `sprints_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- DỮ LIỆU MẪU: 2 sprint
-- 
-- sprint-001: Sprint 1 (Đang hoạt động)
--   - Từ: 01/04/2026 đến 15/04/2026
--   - Thuộc: Dự án PM
-- 
-- sprint-002: Sprint 2 (Không hoạt động)
--   - Từ: 16/04/2026 đến 30/04/2026
--   - Thuộc: Dự án PM
INSERT INTO `sprints` VALUES 
('sprint-001','Sprint 1','First sprint of the project','2026-04-01 00:00:00.000000','2026-04-15 00:00:00.000000',1,'proj-001'),
('sprint-002','Sprint 2','Second sprint of the project','2026-04-16 00:00:00.000000','2026-04-30 00:00:00.000000',0,'proj-001');

-- ===================================================================
-- BẢNG: issues - CÔNG VIỆC/VẤN ĐỀ
-- MỤC ĐÍCH: Lưu trữ các công việc/vấn đề cần giải quyết trong dự án
-- CỘT: id (UUID), issue_key (mã vấn đề - dạng PM-1, ECOM-1, v.v.)
--      summary (tiêu đề), description (mô tả chi tiết)
--      status_id (trạng thái), priority_id (độ ưu tiên), type_id (loại vấn đề)
--      project_id (dự án), reporter_id (người báo cáo), 
--      created_at (ngày tạo), due_at (hạn chót)
-- ===================================================================
DROP TABLE IF EXISTS `issues`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `issues` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `issue_key` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `summary` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` text COLLATE utf8mb4_unicode_ci,
  `status_id` int DEFAULT NULL,
  `priority_id` int DEFAULT NULL,
  `type_id` int DEFAULT NULL,
  `project_id` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `reporter_id` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `due_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `issue_key` (`issue_key`),
  KEY `status_id` (`status_id`),
  KEY `priority_id` (`priority_id`),
  KEY `type_id` (`type_id`),
  KEY `project_id` (`project_id`),
  KEY `reporter_id` (`reporter_id`),
  CONSTRAINT `issues_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `status_type` (`id`),
  CONSTRAINT `issues_ibfk_2` FOREIGN KEY (`priority_id`) REFERENCES `priority_type` (`id`),
  CONSTRAINT `issues_ibfk_3` FOREIGN KEY (`type_id`) REFERENCES `issue_type` (`id`),
  CONSTRAINT `issues_ibfk_4` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`),
  CONSTRAINT `issues_ibfk_5` FOREIGN KEY (`reporter_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- DỮ LIỆU MẪU: 4 vấn đề/công việc
-- 
-- PM-1: Tính năng đăng nhập (TODO, HIGH, STORY)
--   - Mô tả: Implement user login functionality
--   - Hạn chót: 10/04/2026
-- 
-- PM-2: Lỗi đăng xuất (IN_PROGRESS, HIGH, BUG)
--   - Mô tả: Fix bug where users cannot logout properly
--   - Hạn chót: 08/04/2026
-- 
-- PM-3: Cải tiến giao diện Dashboard (TODO, MEDIUM, STORY)
--   - Mô tả: Improve the dashboard user interface
--   - Hạn chót: 12/04/2026
-- 
-- ECOM-1: Tính năng giỏ hàng (IN_PROGRESS, HIGH, STORY)
--   - Mô tả: Implement shopping cart functionality
--   - Hạn chót: 14/04/2026
INSERT INTO `issues` VALUES 
('issue-001','PM-1','Login Feature','Implement user login functionality',1,3,3,'proj-001','admin-user-001','2026-04-06 10:00:00.000000','2026-04-10 00:00:00.000000'),
('issue-002','PM-2','Fix login bug','Fix bug where users cannot logout properly',2,3,2,'proj-001','user-001','2026-04-06 10:30:00.000000','2026-04-08 00:00:00.000000'),
('issue-003','PM-3','Dashboard UI Improvement','Improve the dashboard user interface',1,2,3,'proj-001','user-002','2026-04-06 11:00:00.000000','2026-04-12 00:00:00.000000'),
('issue-004','ECOM-1','Shopping Cart','Implement shopping cart functionality',2,3,3,'proj-002','admin-user-001','2026-04-06 10:15:00.000000','2026-04-14 00:00:00.000000');

-- ===================================================================
-- BẢNG: issue_assignees - GIAO CÔNG VIỆC CHO NGƯỜI DÙNG
-- MỤC ĐÍCH: Lưu trữ mapping giữa vấn đề và người được giao (quan hệ nhiều-nhiều)
-- CỘT: issue_id (vấn đề), user_id (người được giao)
-- GHI CHÚ: Một vấn đề có thể giao cho nhiều người, một người có thể được giao nhiều vấn đề
-- ===================================================================
DROP TABLE IF EXISTS `issue_assignees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `issue_assignees` (
  `issue_id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`issue_id`,`user_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `issue_assignees_ibfk_1` FOREIGN KEY (`issue_id`) REFERENCES `issues` (`id`),
  CONSTRAINT `issue_assignees_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- DỮ LIỆU MẪU: Giao công việc cho các thành viên nhóm
-- 
-- PM-1: Giao cho user1
-- PM-2: Giao cho user1
-- PM-3: Giao cho user2
-- ECOM-1: Giao cho user1 và user2 (cùng làm việc trên một vấn đề)
INSERT INTO `issue_assignees` VALUES 
('issue-001','user-001'),
('issue-002','user-001'),
('issue-003','user-002'),
('issue-004','user-001'),
('issue-004','user-002');

-- ===================================================================
-- BẢNG: comments - BÌNH LUẬN VẤN ĐỀ
-- MỤC ĐÍCH: Lưu trữ các bình luận/thảo luận trên mỗi vấn đề
-- CỘT: id (UUID), content (nội dung bình luận), created_at (ngày tạo)
--      issue_id (vấn đề bị bình luận), user_id (người bình luận)
-- ===================================================================
DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `content` text COLLATE utf8mb4_unicode_ci,
  `created_at` datetime(6) DEFAULT NULL,
  `issue_id` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_id` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `issue_id` (`issue_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`issue_id`) REFERENCES `issues` (`id`),
  CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- DỮ LIỆU MẪU: 3 bình luận
-- 
-- COM-001: Bình luận của user1 về vấn đề PM-1 (đăng nhập)
--   - "Great! I will start working on this task."
-- 
-- COM-002: Bình luận của user1 về vấn đề PM-2 (lỗi đăng xuất)
--   - "I found the issue and I am fixing it now."
-- 
-- COM-003: Bình luận của admin về vấn đề PM-2
--   - "Looks good to me!"
INSERT INTO `comments` VALUES 
('com-001','Great! I will start working on this task.','2026-04-06 10:30:00.000000','issue-001','user-001'),
('com-002','I found the issue and I am fixing it now.','2026-04-06 11:00:00.000000','issue-002','user-001'),
('com-003','Looks good to me!','2026-04-06 11:15:00.000000','issue-002','admin-user-001');

-- ===================================================================
-- BẢNG: boards - BẢNG KANBAN CÁ NHÂN
-- MỤC ĐÍCH: Lưu trữ các bảng kanban được tạo bởi người dùng
-- CỘT: id (UUID), name (tên bảng), description (mô tả)
--      user_id (chủ sở hữu/người tạo bảng), created_at (ngày tạo)
-- ===================================================================
DROP TABLE IF EXISTS `boards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `boards` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` text COLLATE utf8mb4_unicode_ci,
  `user_id` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `boards_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ===================================================================
-- BẢNG: project_member_role - VAI TRÒ THÀNH VIÊN DỰ ÁN
-- MỤC ĐÍCH: Định nghĩa các vai trò có thể của thành viên trong dự án
-- CỘT: id (khóa chính), name (tên vai trò), description (mô tả vai trò)
-- ===================================================================
DROP TABLE IF EXISTS `project_member_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project_member_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- DỮ LIỆU MẪU: 3 vai trò thành viên dự án
-- 
-- 1: LEAD - Người dẫn dắt/quản lý dự án
--   - Có quyền cao nhất trong dự án
--   - Có thể quản lý dự án, sprint, thành viên
-- 
-- 2: DEVELOPER - Lập trình viên
--   - Phát triển tính năng, sửa lỗi
--   - Có thể đảm nhận các công việc liên quan
-- 
-- 3: QA - Kiểm thử phần mềm
--   - Kiểm thử chất lượng, báo cáo lỗi
INSERT INTO `project_member_role` VALUES (1,'LEAD','Project Lead'),(2,'DEVELOPER','Developer'),(3,'QA','Quality Assurance');

-- ===================================================================
-- BẢNG: project_members - THÀNH VIÊN DỰ ÁN
-- MỤC ĐÍCH: Lưu trữ danh sách thành viên tham gia dự án
-- CỘT: id (UUID), project_id (dự án), user_id (người dùng)
--      role_id (vai trò trong dự án), joined_date (ngày tham gia)
-- GHI CHÚ: Một người dùng có thể là thành viên của nhiều dự án với vai trò khác nhau
-- ===================================================================
DROP TABLE IF EXISTS `project_members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project_members` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `project_id` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_id` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  `joined_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `project_id` (`project_id`),
  KEY `user_id` (`user_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `project_members_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`),
  CONSTRAINT `project_members_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `project_members_ibfk_3` FOREIGN KEY (`role_id`) REFERENCES `project_member_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- DỮ LIỆU MẪU: Các thành viên trong 2 dự án
-- 
-- ===== DỰ ÁN PM (Project Management System) =====
-- PM-M-001: admin - Vai trò LEAD (Người dẫn dắt)
-- PM-M-002: user1 - Vai trò DEVELOPER (Lập trình viên)
-- PM-M-003: user2 - Vai trò QA (Kiểm thử)
-- 
-- ===== DỰ ÁN ECOM (E-Commerce Platform) =====
-- ECOM-M-001: admin - Vai trò LEAD (Người dẫn dắt)
-- ECOM-M-002: user1 - Vai trò DEVELOPER (Lập trình viên)
INSERT INTO `project_members` VALUES 
('pm-001','proj-001','admin-user-001',1,'2026-04-06 10:00:00.000000'),
('pm-002','proj-001','user-001',2,'2026-04-06 10:00:00.000000'),
('pm-003','proj-001','user-002',3,'2026-04-06 10:00:00.000000'),
('pm-004','proj-002','admin-user-001',1,'2026-04-06 10:00:00.000000'),
('pm-005','proj-002','user-001',2,'2026-04-06 10:00:00.000000');

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- ===================================================================
-- KẾT THÚC: Dump hoàn tất vào ngày 06/04/2026
-- ===================================================================
