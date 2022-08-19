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
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `member_no` int unsigned NOT NULL,
  `debate_number` int unsigned NOT NULL DEFAULT '0',
  `email` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `exp` int unsigned NOT NULL DEFAULT '0',
  `introduce` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `login_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `login_password` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `nickname` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `phone_number` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `profile_name` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `profile_url` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`member_no`),
  UNIQUE KEY `UK_mbmcqelty0fbrvxp1q58dn57t` (`email`),
  UNIQUE KEY `UK_enfm5patwjqulw8k4wwuo6f60` (`login_id`),
  UNIQUE KEY `UK_n2qryhkfoqeel6njfhrcq6k7u` (`phone_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,0,'kimhj0107@naver.com',0,NULL,'kimhj0107','18138372fad4b94533cd4881f03dc6c69296dd897234e0cee83f727e2e6b1f63','북극곰','01039933993','edf324ee-d878-4083-88b6-075fcc468204_북극곰.png','https://i7a508.p.ssafy.io/images/profile/edf324ee-d878-4083-88b6-075fcc468204_북극곰.png'),(2,0,'Kssafy@ssafy.com',0,NULL,'Kssafy7','7dc6cc4a94a3bddbabecfa799c47809f1fb54fd0f2359ddca0ab9182bf354cbc','김싸피','01012345678','363763fb-5504-4b29-a149-9c66b68a9920_배경화면_별이그림3.jpg','https://i7a508.p.ssafy.io/images/profile/363763fb-5504-4b29-a149-9c66b68a9920_배경화면_별이그림3.jpg'),(3,0,'debate@gmail.com',0,NULL,'test123','ecd71870d1963316a97e3ac3408c9835ad8cf0f3c1bc703527c30265534f75ae','토론최강자','01048675982','f6dea820-4f3c-46e4-9460-8d35fd1bdb93_프사.png','https://i7a508.p.ssafy.io/images/profile/f6dea820-4f3c-46e4-9460-8d35fd1bdb93_프사.png'),(4,0,'weseeplay@ssafy.com',0,NULL,'hong8978','03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4','라이언','01012341234','309e7546-31d2-42f3-872b-401b82205d43_alphaka.jpg','https://i7a508.p.ssafy.io/images/profile/309e7546-31d2-42f3-872b-401b82205d43_alphaka.jpg'),(5,0,'yangdid@ssafy.com',0,NULL,'yangdid','0eb89103272462c001e4ee64f080c1e0fc1fc04c55e36493afa9462018547561','토론초보자','01020202020',NULL,NULL),(6,0,'tester@gmail.com',0,NULL,'qwer1234','ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f','테스터','01046853694',NULL,NULL),(7,0,'trust@gmail.com',0,NULL,'truster','15933ed61d46e718e47132a5ad86cff7cbb459e56361960c95d15be1e697a068','토론은즐거워','01034696985',NULL,NULL),(8,0,'yangdid11@ssafy.com',0,NULL,'yangdid11','e5f3d62bdbf6f3ca44ec99b3b5cc69b80bd5a107b8b50852b2812af8e6ec5091','양싸피','01030303030','27823f47-1fba-4a50-9d8e-69d3ceb5b0f6_탄산.jpeg','https://i7a508.p.ssafy.io/images/profile/27823f47-1fba-4a50-9d8e-69d3ceb5b0f6_탄산.jpeg'),(9,0,'coffe@naver.com',0,NULL,'likecoffee','ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f','토론과함께','01027657326','0a1ca402-cba1-4b1b-98b1-eae3710057bb_coffeebean.png','https://i7a508.p.ssafy.io/images/profile/0a1ca402-cba1-4b1b-98b1-eae3710057bb_coffeebean.png');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
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
