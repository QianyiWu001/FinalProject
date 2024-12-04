-- MySQL dump 10.13  Distrib 9.0.1, for macos15.1 (arm64)
--
-- Host: localhost    Database: NewStuManagement
-- ------------------------------------------------------
-- Server version	9.1.0

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

--
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendance` (
  `enrollment_id` int NOT NULL,
  `date` date NOT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`enrollment_id`,`date`),
  CONSTRAINT `attendance_ibfk_1` FOREIGN KEY (`enrollment_id`) REFERENCES `enrollments` (`enrollment_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance`
--

LOCK TABLES `attendance` WRITE;
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
INSERT INTO `attendance` VALUES (85,'2024-11-01','Late'),(85,'2024-11-02','Present'),(85,'2024-11-05','Late2'),(86,'2020-09-11','Present'),(86,'2024-11-01','Present'),(86,'2024-11-03','Absent'),(87,'2024-11-01','Late'),(87,'2024-11-04','Present'),(88,'2024-11-01','Present'),(88,'2024-11-05','Absent'),(89,'2024-11-02','Present'),(90,'2024-11-03','Late'),(91,'2024-11-01','Present'),(91,'2024-11-02','Absent'),(92,'2024-11-01','Present'),(92,'2024-11-03','Present'),(93,'2024-11-01','Absent'),(93,'2024-11-04','Late'),(94,'2024-11-01','Present'),(94,'2024-11-02','Present'),(95,'2024-11-01','Present'),(95,'2024-11-05','Absent'),(96,'2024-11-01','Late'),(96,'2024-11-03','Present'),(97,'2024-11-02','Absent'),(97,'2024-11-04','Present'),(98,'2024-11-01','Present'),(99,'2024-11-01','Absent'),(99,'2024-11-02','Present'),(100,'2024-11-01','Late'),(100,'2024-11-03','Absent'),(103,'2024-11-01','Absent'),(103,'2024-11-04','Late'),(104,'2024-11-01','Present'),(104,'2024-11-03','Present'),(105,'2024-11-02','Late'),(105,'2024-11-05','Present'),(106,'2024-11-01','Absent'),(106,'2024-11-04','Present');
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bills`
--

DROP TABLE IF EXISTS `bills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bills` (
  `bill_id` int NOT NULL AUTO_INCREMENT,
  `student_id` int NOT NULL,
  `bill_amount` float DEFAULT NULL,
  `paid_status` enum('Paid','Unpaid') NOT NULL,
  `due_date` date DEFAULT NULL,
  PRIMARY KEY (`bill_id`),
  KEY `FK3umig5omoetksaly7j6ebi929` (`student_id`),
  CONSTRAINT `FK_bills_student_id` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bills`
--

LOCK TABLES `bills` WRITE;
/*!40000 ALTER TABLE `bills` DISABLE KEYS */;
INSERT INTO `bills` VALUES (78,4,1500,'Unpaid','2024-11-01'),(80,6,1100,'Unpaid','2024-11-01'),(82,8,1300,'Unpaid','2024-11-01'),(83,9,800,'Paid','2024-10-01'),(84,10,1000,'Paid','2024-09-01'),(85,11,1500,'Unpaid','2024-11-01'),(86,12,1200,'Paid','2024-10-01'),(87,13,900,'Paid','2024-10-01'),(88,14,1100,'Unpaid','2024-10-01'),(89,15,1300,'Paid','2024-10-01'),(90,16,1000,'Unpaid','2024-10-01'),(91,17,900,'Paid','2024-10-01'),(92,18,1100,'Unpaid','2024-10-01'),(93,19,1500,'Paid','2024-10-01'),(94,20,1300,'Unpaid','2024-10-01'),(95,22,1200,'Paid','2024-10-01');
/*!40000 ALTER TABLE `bills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `course_id` int NOT NULL AUTO_INCREMENT,
  `course_name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `credits` int NOT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=203 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES (1,'Mathematics','Fundamentals of mathematics',3),(2,'Physics','Introduction to physics',2),(3,'Chemistry','Basics of chemistry',3),(4,'Biology','Foundations of biology',3),(5,'Computer Science','Intro to computer programming',3),(6,'English','Advanced English language',2),(7,'History','World history overview',3),(8,'Economics','Micro and macro economics',3),(9,'Statistics','Introductory statistics',3),(10,'Philosophy','Basics of philosophy',2),(11,'Art','Introduction to fine arts',2),(12,'Music','Music theory and practice',2),(13,'Psychology','Basics of psychology',3),(14,'Sociology','Introduction to sociology',3),(15,'Political Science','Government systems overview',3),(16,'Engineering','Basics of engineering',4),(17,'Astronomy','Introduction to astronomy',3),(18,'Environmental Science','Basics of environmental science',3),(19,'Geography','World geography',3),(101,'test','1',1);
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enrollments`
--

DROP TABLE IF EXISTS `enrollments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enrollments` (
  `enrollment_id` int NOT NULL AUTO_INCREMENT,
  `student_id` int NOT NULL,
  `course_id` int NOT NULL,
  PRIMARY KEY (`enrollment_id`),
  UNIQUE KEY `student_id` (`student_id`,`course_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `enrollments_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`) ON DELETE CASCADE,
  CONSTRAINT `enrollments_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enrollments`
--

LOCK TABLES `enrollments` WRITE;
/*!40000 ALTER TABLE `enrollments` DISABLE KEYS */;
INSERT INTO `enrollments` VALUES (85,4,3),(86,6,3),(103,6,7),(87,8,5),(104,8,9),(88,9,6),(89,10,7),(90,11,8),(105,11,13),(91,12,9),(92,13,10),(93,14,11),(94,15,12),(95,16,13),(96,17,14),(97,18,15),(98,19,16),(99,20,17),(100,21,18),(106,22,8);
/*!40000 ALTER TABLE `enrollments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grades`
--

DROP TABLE IF EXISTS `grades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grades` (
  `enrollment_id` int NOT NULL,
  `grade` int NOT NULL,
  PRIMARY KEY (`enrollment_id`),
  CONSTRAINT `grades_ibfk_1` FOREIGN KEY (`enrollment_id`) REFERENCES `enrollments` (`enrollment_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grades`
--

LOCK TABLES `grades` WRITE;
/*!40000 ALTER TABLE `grades` DISABLE KEYS */;
INSERT INTO `grades` VALUES (85,85),(86,90),(87,79),(88,92),(89,87),(90,75),(91,80),(92,88),(93,95),(94,82),(95,91),(96,84),(97,77),(98,89),(99,86),(100,83),(103,88),(104,94),(105,81),(106,79);
/*!40000 ALTER TABLE `grades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `student_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `UKe2rndfrsx22acpq2ty1caeuyw` (`email`),
  CONSTRAINT `FKsonpdm2xowqjw72dirngtse5e` FOREIGN KEY (`student_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (4,'Bob Smith update','bob@example.com','2345678901',NULL),(6,'Diana Prince','diana@example.com','4567890123','2'),(8,'Fiona White','fiona@example.com','6789012345',NULL),(9,'George Hall','george@example.com','7890123456',NULL),(10,'Hannah Green','hannah@example.com','8901234567',NULL),(11,'Ivy West','ivy@example.com','9012345678',NULL),(12,'Jack Brown','jack@example.com','0123456789',NULL),(13,'Kelly Black','kelly@example.com','2345678901',NULL),(14,'Liam Jones','liam@example.com','3456789012',NULL),(15,'Mia Gray','mia@example.com','4567890123',NULL),(16,'Noah Clark','noah@example.com','5678901234',NULL),(17,'Olivia Hill','olivia@example.com','6789012345',NULL),(18,'Paul Knight','paul@example.com','7890123456',NULL),(19,'Quinn Lewis','quinn@example.com','8901234567',NULL),(20,'Riley Martin','riley@example.com','9012345678',NULL),(21,'Sophia Wilson','sophia@example.com','0123456789',NULL),(22,'2','1@111','12354','fgsgsdfg');
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `unique_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'1','123','ROLE_ADMIN'),(2,'admin2','password123','ROLE_ADMIN'),(4,'Bob Smith update','123','ROLE_STUDENT'),(6,'Diana Prince','123','ROLE_STUDENT'),(8,'student6','password123','ROLE_STUDENT'),(9,'George Hall','123','ROLE_STUDENT'),(10,'Hannah Green','123','ROLE_STUDENT'),(11,'student9','password123','ROLE_STUDENT'),(12,'student10','password123','ROLE_STUDENT'),(13,'student11','password123','ROLE_STUDENT'),(14,'student12','password123','ROLE_STUDENT'),(15,'student13','password123','ROLE_STUDENT'),(16,'student14','password123','ROLE_STUDENT'),(17,'student15','password123','ROLE_STUDENT'),(18,'student16','password123','ROLE_STUDENT'),(19,'student17','password123','ROLE_STUDENT'),(20,'student18','password123','ROLE_STUDENT'),(21,'Sophia Wilson','123','ROLE_STUDENT'),(22,'2','123','ROLE_STUDENT');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'NewStuManagement'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-03 22:48:01
