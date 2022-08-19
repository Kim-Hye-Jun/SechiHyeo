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
-- Table structure for table `debate_board`
--

DROP TABLE IF EXISTS `debate_board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `debate_board` (
  `board_no` int unsigned NOT NULL AUTO_INCREMENT,
  `a_opinion` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `b_opinion` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `board_content` varchar(300) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `board_finished` tinyint(1) NOT NULL DEFAULT '0',
  `board_time` datetime(6) DEFAULT NULL,
  `board_title` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `debate_time` datetime(6) DEFAULT NULL,
  `debate_topic` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `max_applicant` int NOT NULL DEFAULT '0',
  `member_no` int unsigned DEFAULT NULL,
  PRIMARY KEY (`board_no`),
  KEY `FK97r2qxdfv3viph6fhfvne78dk` (`member_no`),
  CONSTRAINT `FK97r2qxdfv3viph6fhfvne78dk` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `debate_board`
--

LOCK TABLES `debate_board` WRITE;
/*!40000 ALTER TABLE `debate_board` DISABLE KEYS */;
INSERT INTO `debate_board` VALUES (1,'해결 가능하다','해결 불가능하다','지구온난화가 요즘 심각해보입니다.. 해결을 할 수 있는 방법이 있을까요? ',0,'2022-08-19 10:20:50.268000','지구온난화에 대하여...','2022-08-21 02:00:00.000000','지구온난화 해결할 수 있을까?',6,1),(2,'찬성','반대','주 4일제에 관한 주제로 연습 진행할 예정입니다. 진영 맞춰서 신청해주세요!',0,'2022-08-19 10:20:55.844000','싸피대학교 토론동아리 토론토 정기 연습','2022-08-02 19:20:00.000000','주 4일제가 좋은 영향을 미칠 것인가',4,2),(3,'찬성','반대','이번 청소년 토론 대회 연습 같이 하실 분 모집합니다! 주제는 9시 등교제 찬성/반대입니다.',0,'2022-08-19 10:28:21.315000','청소년 토론 대회 같이 준비하실 분 모집합니다.','2022-08-30 20:30:00.000000','9시 등교제',6,8),(4,'찬성','반대','많은 관심 부탁드립니다!',0,'2022-08-19 10:31:19.102000','청소년 음주는 금지되어야 하는가','2022-08-08 21:30:00.000000','청소년 음주는 금지되어야 하는가',6,2),(5,'비트코인 찬성','비트코인 반대','14회차 온라인 토론 대회의 주제는 비트코인 열풍에 대해서 입니다. 참가해서 다양한 의견을 제시해주세요!!!!',0,'2022-08-19 10:32:38.911000','제 14회차 온라인 토론 대회 참가 모집','2022-09-02 22:35:00.000000','비트코인 투자 열풍 과연 옳은가?',6,3),(6,'찬성','반대','같이 준비해서 좋은 결과 만들어요!',0,'2022-08-19 10:34:25.843000','전국 대학생 토론대회','2022-08-14 00:30:00.000000','전국 대학생 토론대회 함께 준비하실 대학생 구합니다!',2,2),(7,'MBTI의 몰입 괜찮다','MBTI의 몰입 반대다','MBTI를 너무 과신용하는 것 같은 사회에 분위기가 있는데 다양한  의견을 듣고 싶습니다',0,'2022-08-19 10:34:44.259000','MBTI 어디까지 맹신해야하는가?','2022-09-02 22:35:00.000000','MBTI 과몰입 허용하는가?',6,3),(8,'짜장면','짬뽕','중국집에서 평생 먹을수 있는 면류를 하나만 고른다면?',0,'2022-08-19 10:36:54.482000','1대 1 토론 서로 피드백 해주실분!','2022-08-24 20:30:00.000000','짜장면 vs 짬뽕',2,4),(9,'유튜브','TV','유튜브의 영향력이 커진 오늘날 그래도 둘 중 어느 매체의 영향력이 큰 것 같은지 다양한 의견 나눠봐요',0,'2022-08-19 10:36:58.607000','유튜브 VS TV','2022-09-17 00:38:00.000000','유튜브와 TV 중 어느 매체가 영향력이 더 큰 것 같나요',2,3),(10,'찬성','반대','토론 주제는 아직 미정이지만 토론 규칙 연습 목표로 토론해 보실 분 구합니다!',0,'2022-08-19 10:37:25.340000','1:1 토론 규칙 연습하실 분 구합니다..','2022-08-23 19:40:00.000000','아직 미정',2,8),(11,'나는 가솔린 자동차가 좋다.','나는 전기 자동차가 좋다.','주제와 같다. 같이 얘기합시다.',0,'2022-08-19 10:38:25.988000','가솔린차? 전기차?','2022-08-28 22:00:00.000000','가솔린 자동차와 전기 자동차와 각각의 자동차를 좋아하는 사람의 견해',4,9),(12,'JAVA','PYTHON','친한 지인이 개발에 입문하고 싶다고 한다면? 다양한 관점에서 접근하셔도 됩니다!',0,'2022-08-19 10:40:30.854000','IT관련 토론 해보고 싶으신 분을 모집합니다','2022-08-19 10:40:00.000000','프로그래밍을 처음 배우는 사람이라면 어떤 언어가 좋은가?',6,4),(13,'스타벅스가 최고다','벤티가 최고다','스타벅스 VS 벤티',0,'2022-08-19 10:40:55.282000','커피 가격에 대해서','2022-09-03 19:40:00.000000','비싼 브랜드 커피와 염가 커피에 대해서',6,9);
/*!40000 ALTER TABLE `debate_board` ENABLE KEYS */;
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
