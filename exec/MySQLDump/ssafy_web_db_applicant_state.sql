-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: i7a508.p.ssafy.io    Database: ssafy_web_db
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `applicant_state`
--

DROP TABLE IF EXISTS `applicant_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `applicant_state` (
  `applicant_no` int unsigned NOT NULL AUTO_INCREMENT,
  `accept` int unsigned NOT NULL DEFAULT '2',
  `side` int unsigned NOT NULL DEFAULT '0',
  `turn` int unsigned NOT NULL DEFAULT '0',
  `board_no` int unsigned DEFAULT NULL,
  `member_no` int unsigned DEFAULT NULL,
  PRIMARY KEY (`applicant_no`),
  KEY `FKspwkw4bmekhgv9qk9u97sawut` (`board_no`),
  KEY `FKbq0k6uvat9f951ehcu7k3ss7a` (`member_no`),
  CONSTRAINT `FKbq0k6uvat9f951ehcu7k3ss7a` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`),
  CONSTRAINT `FKspwkw4bmekhgv9qk9u97sawut` FOREIGN KEY (`board_no`) REFERENCES `debate_board` (`board_no`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applicant_state`
--

LOCK TABLES `applicant_state` WRITE;
/*!40000 ALTER TABLE `applicant_state` DISABLE KEYS */;
INSERT INTO `applicant_state` VALUES (1,2,1,2,2,1),(2,2,0,0,1,2),(3,2,1,1,8,3),(4,2,0,3,4,3),(5,2,0,2,4,3),(6,2,1,1,10,3),(7,2,1,2,7,8),(8,2,1,1,2,8);
/*!40000 ALTER TABLE `applicant_state` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-19 10:44:05
