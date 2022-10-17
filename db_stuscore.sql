CREATE DATABASE  IF NOT EXISTS `db_stuscore` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `db_stuscore`;
-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: db_stuscore
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `eproject`
--

DROP TABLE IF EXISTS `eproject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `eproject` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(32) NOT NULL COMMENT '科目名称',
  `comm` varchar(125) NOT NULL COMMENT '备注说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='考试科目表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eproject`
--

LOCK TABLES `eproject` WRITE;
/*!40000 ALTER TABLE `eproject` DISABLE KEYS */;
INSERT INTO `eproject` VALUES (1,'语文','语文考试'),(2,'数学','数学考试'),(3,'英语','英语考试'),(4,'物理','物理考试'),(5,'化学','化学考试'),(6,'生物','生物考试'),(7,'历史','历史考试'),(8,'地理','地理考试'),(9,'政治','政治考试'),(10,'体育','体育考试');
/*!40000 ALTER TABLE `eproject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `etype`
--

DROP TABLE IF EXISTS `etype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `etype` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(32) NOT NULL COMMENT '类别名称',
  `comm` varchar(125) NOT NULL COMMENT '备注说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='考试类别表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `etype`
--

LOCK TABLES `etype` WRITE;
/*!40000 ALTER TABLE `etype` DISABLE KEYS */;
INSERT INTO `etype` VALUES (1,'期中考试','对期中考试进行归档'),(2,'期末考试','对期末考试进行归档'),(3,'月末考核','对月末考核进行归档');
/*!40000 ALTER TABLE `etype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam`
--

DROP TABLE IF EXISTS `exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exam` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(32) NOT NULL COMMENT '考试名称',
  `etime` varchar(19) NOT NULL COMMENT '考试时间',
  `intro` varchar(256) NOT NULL COMMENT '考试说明',
  `epid` int NOT NULL COMMENT '考试科目编号',
  `etid` int NOT NULL COMMENT '考试类型编号',
  `gid` int NOT NULL COMMENT '年级编号',
  PRIMARY KEY (`id`),
  KEY `fk_exam_eproject_epid` (`epid`),
  KEY `fk_exam_etype_etid` (`etid`),
  KEY `fk_exam_grade_gid` (`gid`),
  CONSTRAINT `fk_exam_eproject_epid` FOREIGN KEY (`epid`) REFERENCES `eproject` (`id`),
  CONSTRAINT `fk_exam_etype_etid` FOREIGN KEY (`etid`) REFERENCES `etype` (`id`),
  CONSTRAINT `fk_exam_grade_gid` FOREIGN KEY (`gid`) REFERENCES `grade` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='考试科目表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam`
--

LOCK TABLES `exam` WRITE;
/*!40000 ALTER TABLE `exam` DISABLE KEYS */;
INSERT INTO `exam` VALUES (1,'一年级语文月考','2021-05-18','这是一年级的月考',1,3,1),(2,'一年级数学月考','2021-05-18','这是一年级的月考',2,3,1),(3,'一年级英语月考','2021-05-18','这是一年级的月考',3,3,2),(4,'一年级物理月考','2021-05-18','这是一年级的月考',4,3,2),(5,'高等数学','2021-05-18','高等数学',2,2,6);
/*!40000 ALTER TABLE `exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grade`
--

DROP TABLE IF EXISTS `grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grade` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(32) NOT NULL COMMENT '年级名称',
  `comm` varchar(125) NOT NULL COMMENT '备注说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='年级信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grade`
--

LOCK TABLES `grade` WRITE;
/*!40000 ALTER TABLE `grade` DISABLE KEYS */;
INSERT INTO `grade` VALUES (1,'一年级一班','这是一年级的一班'),(2,'一年级二班','这是一年级的二班'),(4,'二年级一班','这是二年级的一班'),(5,'二年级二班','这是二年级的二班'),(6,'三年级卓工班','这是三年级的尖子班');
/*!40000 ALTER TABLE `grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score`
--

DROP TABLE IF EXISTS `score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `score` (
  `eid` int NOT NULL COMMENT '考试编号',
  `sid` int NOT NULL COMMENT '学生编号',
  `score` double NOT NULL COMMENT '学生成绩',
  PRIMARY KEY (`eid`,`sid`),
  KEY `fk_score_student_sid` (`sid`),
  CONSTRAINT `fk_score_exam_eid` FOREIGN KEY (`eid`) REFERENCES `exam` (`id`),
  CONSTRAINT `fk_score_student_sid` FOREIGN KEY (`sid`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='考试成绩表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score`
--

LOCK TABLES `score` WRITE;
/*!40000 ALTER TABLE `score` DISABLE KEYS */;
INSERT INTO `score` VALUES (1,1,97),(1,2,60),(1,3,99),(1,4,61),(1,5,33),(2,3,100),(2,4,60),(3,5,74),(3,6,75),(4,5,89),(4,6,87),(5,1,98),(5,2,99);
/*!40000 ALTER TABLE `score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(32) NOT NULL COMMENT '学生姓名',
  `sex` int DEFAULT NULL COMMENT '性别, 0-男 1-女',
  `age` int DEFAULT NULL COMMENT '年龄',
  `comm` varchar(125) NOT NULL COMMENT '备注说明',
  `gid` int NOT NULL COMMENT '所属年级',
  PRIMARY KEY (`id`),
  KEY `fk_gid` (`gid`),
  CONSTRAINT `fk_gid` FOREIGN KEY (`gid`) REFERENCES `grade` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学生表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'张翔宇',0,23,'好学生',6),(2,'陈青羽',0,22,'好学生',6),(3,'易瑞童',0,21,'好学生',1),(4,'多福',0,22,'好学生',1),(5,'吴博臣',0,21,'好学生',2),(6,'李丽江',1,21,'好学生',2),(7,'马嘉欢',0,22,'好学生',4),(8,'曹宇晗',0,22,'好学生',4),(11,'韩雪琳',1,22,'好学生',5),(12,'周树林',0,22,'好学生',5);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '用户密码',
  `type` int NOT NULL COMMENT '用户身份, 0-系统管理员 1-普通用户',
  `createtime` varchar(19) NOT NULL COMMENT '创建时间',
  `comm` varchar(125) NOT NULL COMMENT '备注说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'管理员张老师','123456',0,'2021-03-29 11:10:56','这是系统管理员'),(5,'杨丽老师','123456',1,'2021-05-07 00:16:27','杨丽老师'),(6,'刘远社老师','123456',1,'2021-05-07 00:16:53','刘远社老师'),(7,'王术群老师','123456',1,'2021-05-07 00:17:04','王术群老师'),(8,'王欣强老师','123456',1,'2021-05-07 00:17:19','王欣强'),(9,'杨悦老师','123456',1,'2021-05-07 00:17:27','杨悦老师');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-21  6:49:23
