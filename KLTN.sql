-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: kltn_career_website
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `achievement`
--

DROP TABLE IF EXISTS `achievement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `achievement` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `image_id` bigint DEFAULT NULL,
  `owner_id` bigint DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqssc8sm1lsm8qq80q7kjkeh70` (`image_id`),
  KEY `FK86x40nioyti4mcpa74y4vmowv` (`owner_id`),
  CONSTRAINT `FK86x40nioyti4mcpa74y4vmowv` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKqssc8sm1lsm8qq80q7kjkeh70` FOREIGN KEY (`image_id`) REFERENCES `media_resource` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `achievement`
--

LOCK TABLES `achievement` WRITE;
/*!40000 ALTER TABLE `achievement` DISABLE KEYS */;
INSERT INTO `achievement` VALUES (3,'Achievement 1','ACTIVITY','https://www.youtube.com/watch?v=xypzmu5mMPY&list=RDMMZuk5zGv5Un4&index=10',NULL,1,NULL),(4,'Running Viet Race','CERTIFICATE','https://www.youtube.com/watch?v=X7sSE3yCNLI&list=RDMMZuk5zGv5Un4&index=11',23,1,NULL),(5,'string','CERTIFICATE','string',25,1,NULL);
/*!40000 ALTER TABLE `achievement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `complete` tinyint(1) DEFAULT '0',
  `deny` tinyint(1) DEFAULT '0',
  `note` varchar(255) DEFAULT NULL,
  `start_time` datetime(6) NOT NULL,
  `employer` bigint DEFAULT NULL,
  `user` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmfgcxvin4tj2llrl6yiktllxp` (`employer`),
  KEY `FK2c4mn43c0l11hgx6fwmr7wyvj` (`user`),
  CONSTRAINT `FK2c4mn43c0l11hgx6fwmr7wyvj` FOREIGN KEY (`user`) REFERENCES `user` (`id`),
  CONSTRAINT `FKmfgcxvin4tj2llrl6yiktllxp` FOREIGN KEY (`employer`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
INSERT INTO `appointment` VALUES (1,0,1,'Phong van Fresher Angula 12','2023-02-19 12:00:00.000000',3,1),(2,0,1,'Phong van Fresher Angula 1','2023-02-19 10:00:00.000000',3,1),(6,0,0,'Phong van Fresher Angula 1','2023-02-19 11:00:00.000000',3,1);
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `banner`
--

DROP TABLE IF EXISTS `banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `banner` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `image_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK104qa85ws27ig3yxsc0j9idax` (`image_id`),
  CONSTRAINT `FK104qa85ws27ig3yxsc0j9idax` FOREIGN KEY (`image_id`) REFERENCES `media_resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banner`
--

LOCK TABLES `banner` WRITE;
/*!40000 ALTER TABLE `banner` DISABLE KEYS */;
/*!40000 ALTER TABLE `banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog`
--

DROP TABLE IF EXISTS `blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog`
--

LOCK TABLES `blog` WRITE;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_message`
--

DROP TABLE IF EXISTS `chat_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_message` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` mediumtext,
  `time` datetime(6) DEFAULT NULL,
  `user1_seen` tinyint(1) DEFAULT '0',
  `user2_seen` tinyint(1) DEFAULT '0',
  `chat_room_id` bigint NOT NULL,
  `id_image` bigint DEFAULT NULL,
  `sender_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj52yap2xrm9u0721dct0tjor9` (`chat_room_id`),
  KEY `FKbb4f67o4lylmfimmfl46j4y08` (`id_image`),
  CONSTRAINT `FKbb4f67o4lylmfimmfl46j4y08` FOREIGN KEY (`id_image`) REFERENCES `media_resource` (`id`),
  CONSTRAINT `FKj52yap2xrm9u0721dct0tjor9` FOREIGN KEY (`chat_room_id`) REFERENCES `chat_room` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_message`
--

LOCK TABLES `chat_message` WRITE;
/*!40000 ALTER TABLE `chat_message` DISABLE KEYS */;
INSERT INTO `chat_message` VALUES (1,'First message','2023-03-05 11:40:41.603000',1,1,1,NULL,1),(2,'Hi','2023-03-05 11:48:18.944000',1,1,1,NULL,1),(3,'What\'sup4','2023-03-05 11:48:27.031000',1,1,1,NULL,4),(4,'Chao ban','2023-03-05 11:49:51.002000',1,1,3,NULL,4),(5,'Hello','2023-03-05 12:14:59.856000',1,1,1,NULL,4),(6,'Xin chao','2023-03-05 12:15:05.346000',1,1,1,NULL,1),(7,'Wellcome','2023-03-05 12:30:32.323000',1,1,1,NULL,4),(8,'Hi','2023-03-05 12:30:46.148000',1,1,6,NULL,3),(9,'Hi','2023-03-05 12:31:04.306000',1,1,1,NULL,4),(10,'Hello','2023-03-05 12:31:13.469000',1,1,6,NULL,3),(11,'Hja','2023-03-05 12:31:17.352000',1,1,1,NULL,4),(12,'Hello','2023-03-05 12:48:18.330000',1,1,6,NULL,1),(13,'Lo','2023-03-05 12:53:52.069000',1,1,6,NULL,1),(14,'Mem','2023-03-05 12:54:01.224000',1,1,1,NULL,1),(15,'Hii','2023-03-05 12:54:36.308000',1,1,1,NULL,4),(16,'Hl','2023-03-05 12:58:35.493000',1,1,6,NULL,3),(17,'Hi','2023-03-05 13:00:05.537000',1,1,6,NULL,3),(18,'He','2023-03-05 13:03:38.194000',1,1,1,NULL,1),(19,'Hee','2023-03-05 13:04:06.284000',1,1,6,NULL,1),(20,'What sup','2023-03-05 13:20:29.568000',1,1,6,NULL,3),(21,'Sub','2023-03-05 13:20:32.725000',1,1,6,NULL,1),(22,'Xin chao tui la Binh Hoang','2023-03-05 13:24:57.581000',1,1,6,NULL,1),(23,'Chao ban tui co the giup gi','2023-03-05 13:25:19.209000',1,1,6,NULL,3),(24,'K co gi bai bai','2023-03-05 13:25:29.005000',1,1,6,NULL,1),(25,'Hey','2023-03-05 13:26:28.552000',1,1,1,NULL,4),(26,'Ha what was that','2023-03-05 13:26:35.754000',1,1,1,NULL,1),(27,'Hey','2023-03-05 14:49:28.899000',1,1,1,NULL,4),(28,'Chao Ban','2023-03-05 14:50:45.577000',1,1,3,NULL,3),(29,'Hello.','2023-03-05 16:12:31.597000',1,1,6,NULL,3),(30,'Hi','2023-03-05 16:14:08.549000',1,1,1,NULL,1),(31,'Heyy','2023-03-05 16:16:07.055000',1,1,1,NULL,1),(32,'Hi','2023-03-05 16:18:35.871000',1,1,1,NULL,1),(33,'Em iu','2023-03-05 16:25:11.776000',1,1,1,NULL,1),(34,'Hey','2023-03-05 16:26:44.553000',1,1,1,NULL,1),(35,'Heu','2023-03-05 16:27:42.451000',1,1,1,NULL,1),(36,'Hello','2023-03-05 16:27:56.098000',1,1,1,NULL,1),(37,'Diitt','2023-03-05 16:28:37.639000',1,1,1,NULL,1),(38,'!@','2023-03-05 16:40:02.395000',1,1,1,NULL,1),(39,'!@','2023-03-05 16:40:17.860000',1,1,1,NULL,1),(40,'121','2023-03-05 16:40:46.552000',1,1,1,NULL,1),(41,'213','2023-03-05 16:42:29.333000',1,1,1,NULL,1),(42,'123','2023-03-05 16:42:51.765000',1,1,1,NULL,1),(43,'@@','2023-03-05 16:45:43.155000',1,1,1,NULL,1),(44,'1','2023-03-05 16:48:41.516000',1,1,1,NULL,1),(45,'Hello','2023-03-05 16:48:43.970000',1,1,1,NULL,1),(46,'Hello','2023-03-05 16:49:44.557000',1,1,1,NULL,1),(47,'hello','2023-03-05 16:49:48.788000',1,1,1,NULL,1),(48,'Em','2023-03-05 16:50:01.635000',1,1,1,NULL,4),(49,'sao the','2023-03-05 16:50:48.621000',1,1,1,NULL,4),(50,'K co gi anh','2023-03-05 16:50:53.522000',1,1,1,NULL,1),(51,'hello cuc cung','2023-03-07 22:40:54.290000',1,1,1,NULL,4),(52,'22','2023-03-07 22:41:41.562000',1,1,1,NULL,4),(53,'e iu','2023-03-07 22:43:12.219000',1,1,1,NULL,4),(54,'hey','2023-03-07 22:46:31.484000',1,1,1,NULL,4),(55,'sup','2023-03-07 22:47:38.124000',1,1,1,NULL,4),(56,'Yup','2023-03-07 22:49:27.941000',1,1,1,NULL,4),(57,'ha','2023-03-07 22:49:33.262000',1,1,1,NULL,1),(58,'Chafo em','2023-03-07 22:51:21.679000',1,1,6,NULL,3),(59,'Chao em luon','2023-03-07 22:51:27.110000',1,1,1,NULL,4),(60,'Yeu 2 anh','2023-03-07 22:51:31.655000',1,1,1,NULL,1),(61,'sao a','2023-03-07 22:51:37.557000',1,1,6,NULL,1),(62,'hi','2023-03-07 23:04:24.500000',1,1,8,NULL,7),(63,'22','2023-03-07 23:05:00.533000',1,1,8,NULL,7),(64,'ee','2023-03-07 23:05:23.452000',1,1,6,NULL,3),(65,'2','2023-03-07 23:06:41.574000',1,1,8,NULL,7),(66,'22','2023-03-07 23:12:03.543000',1,1,6,NULL,3),(67,'1','2023-03-07 23:12:39.023000',1,1,6,NULL,3),(68,'1','2023-03-07 23:12:52.145000',1,1,6,NULL,3),(69,'2121212121212121212121212121212121212121212121212121','2023-03-07 23:14:39.136000',1,1,6,NULL,3),(70,'Chao em','2023-03-11 13:56:53.332000',1,1,3,NULL,3),(71,'Alo','2023-03-11 13:56:57.608000',1,1,3,NULL,4),(72,'Hello','2023-03-11 14:24:32.954000',1,1,1,NULL,4),(73,'Hey','2023-03-11 14:41:20.065000',1,1,1,NULL,4),(74,'Hello','2023-03-11 14:42:56.773000',1,1,1,NULL,4),(75,'hey','2023-03-11 14:44:03.019000',1,1,1,NULL,4),(76,'alo','2023-03-11 14:44:18.639000',1,1,1,NULL,4),(77,'eee','2023-03-11 14:45:54.473000',1,1,1,NULL,4),(78,'@@','2023-03-11 14:46:28.118000',1,1,1,NULL,4),(79,'Hello','2023-03-11 15:23:52.923000',1,1,1,NULL,4),(80,'Alo alo','2023-03-11 15:24:03.423000',1,1,1,NULL,1),(81,'Khoe k','2023-03-11 15:24:08.643000',1,1,1,NULL,1),(82,'hello','2023-03-11 15:25:08.415000',1,1,8,NULL,7),(83,'Ai z','2023-03-11 15:25:18.163000',1,1,8,NULL,1),(84,'Xin chao','2023-03-12 16:09:38.159000',1,1,1,NULL,1),(85,'Sao ban','2023-03-12 16:09:47.093000',1,1,1,NULL,4),(86,'22','2023-03-12 16:11:08.930000',1,1,1,NULL,1),(87,'hello','2023-03-12 16:11:13.057000',1,1,1,NULL,1),(88,'Hey','2023-03-12 16:12:21.727000',1,1,1,NULL,1),(89,'sao em','2023-03-12 16:12:26.414000',1,1,1,NULL,4),(90,'Khong co gi','2023-03-12 16:12:59.916000',1,1,1,NULL,1),(91,'CHi mon','2023-03-12 16:13:23.354000',1,1,1,NULL,1),(92,'Chill','2023-03-12 16:16:24.663000',1,1,1,NULL,1),(93,'sao.','2023-03-12 16:16:30.760000',1,1,1,NULL,4),(94,'ha','2023-03-12 16:16:33.916000',1,1,1,NULL,1),(95,'Hello','2023-03-12 16:17:37.138000',1,1,1,NULL,4),(96,'H2','2023-03-12 16:17:44.404000',1,1,1,NULL,1),(97,'Hello','2023-03-12 16:21:06.600000',1,1,1,NULL,4),(98,'On khong ban','2023-03-12 16:33:41.663000',1,1,1,NULL,4),(99,'Khong on nha','2023-03-12 16:33:48.633000',1,1,1,NULL,1),(100,'Ok','2023-03-12 16:40:37.035000',1,1,1,NULL,4),(101,'Ban khoe khong','2023-03-12 16:40:42.462000',1,1,1,NULL,1),(102,'Khong khoe nha','2023-03-12 16:40:51.770000',1,1,1,NULL,4),(103,'Tui nef','2023-03-12 16:41:24.125000',1,1,8,NULL,7),(104,'On k','2023-03-12 16:50:26.213000',1,1,8,NULL,1),(105,'On k','2023-03-12 16:50:56.764000',1,1,8,NULL,1),(106,'On nhe','2023-03-12 16:51:44.618000',1,1,8,NULL,7),(107,'2','2023-03-12 16:52:05.453000',1,1,8,NULL,1),(108,'Khe k','2023-03-12 16:52:28.415000',1,1,1,NULL,4),(109,'Khoe nhe','2023-03-12 16:52:36.483000',1,1,1,NULL,1),(110,'Ok ban','2023-03-12 16:54:36.755000',1,1,1,NULL,4),(111,'Ok ban luon nhe','2023-03-12 16:54:49.014000',1,1,1,NULL,1),(112,'Hey','2023-03-15 11:17:34.013000',1,1,6,NULL,3),(113,'hi','2023-03-15 11:21:01.067000',1,1,6,NULL,3),(114,'hj','2023-03-15 11:22:31.620000',1,1,6,NULL,3),(115,'lo','2023-03-15 11:23:39.876000',1,1,6,NULL,3),(116,'Khoe k','2023-03-15 11:24:22.923000',1,1,1,NULL,4),(117,'Heslo','2023-03-15 11:28:23.172000',1,1,8,NULL,7),(118,'what do you mean','2023-03-15 11:28:54.435000',1,1,6,NULL,3),(119,'Up to yoi','2023-03-15 11:29:10.884000',1,1,6,NULL,1),(120,'1','2023-03-15 11:29:17.267000',1,0,6,NULL,1),(121,'Love','2023-03-15 11:29:23.126000',1,0,6,NULL,1);
/*!40000 ALTER TABLE `chat_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_room`
--

DROP TABLE IF EXISTS `chat_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_room` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `chat_name` varchar(255) DEFAULT NULL,
  `last_chat` datetime(6) DEFAULT NULL,
  `user1` bigint DEFAULT NULL,
  `user2` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo5odduew799nt0cna4jc99pdf` (`user1`),
  KEY `FKl875m1de684w34ntc9br92rje` (`user2`),
  CONSTRAINT `FKl875m1de684w34ntc9br92rje` FOREIGN KEY (`user2`) REFERENCES `user` (`id`),
  CONSTRAINT `FKo5odduew799nt0cna4jc99pdf` FOREIGN KEY (`user1`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_room`
--

LOCK TABLES `chat_room` WRITE;
/*!40000 ALTER TABLE `chat_room` DISABLE KEYS */;
INSERT INTO `chat_room` VALUES (1,NULL,'2023-03-15 11:24:22.923000',1,4),(3,NULL,'2023-03-11 13:56:57.608000',3,4),(6,NULL,'2023-03-15 11:29:23.126000',1,3),(7,NULL,NULL,1,5),(8,NULL,'2023-03-15 11:28:23.172000',1,7);
/*!40000 ALTER TABLE `chat_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'TP Há»“ ChÃ­ Minh'),(2,'BÃ¬nh DÆ°Æ¡ng'),(3,'Äá»“ng Nai'),(4,'An Giang'),(5,'BÃ  Rá»‹a â€“ VÅ©ng TÃ u'),(6,'Báº¯c Giang'),(7,'Báº¯c Káº¡n'),(8,'Báº¡c LiÃªu'),(9,'Báº¯c Ninh'),(10,'Báº¿n Tre'),(11,'BÃ¬nh Äá»‹nh'),(12,'BÃ¬nh PhÆ°á»›c'),(13,'BÃ¬nh Thuáº­n'),(14,'CÃ  Mau'),(15,'Cáº§n ThÆ¡'),(16,'Cao Báº±ng '),(17,'ÄÃ  Náºµng'),(18,'Äáº¯k Láº¯k'),(19,'Äáº¯k NÃ´ng'),(20,'Äiá»‡n BiÃªn'),(21,'Äá»“ng ThÃ¡p'),(22,'Gia Lai'),(23,'HÃ  Giang'),(24,'HÃ  Nam'),(25,'HÃ  Ná»™i '),(26,'HÃ  TÄ©nh'),(27,'Háº£i DÆ°Æ¡ng'),(28,'Háº£i PhÃ²ng'),(29,'Háº­u Giang'),(30,'HÃ²a BÃ¬nh'),(31,'HÆ°ng YÃªn'),(32,'KhÃ¡nh HÃ²a'),(33,'KiÃªn Giang'),(34,'Kon Tum'),(35,'Lai ChÃ¢u'),(36,'LÃ¢m Äá»“ng'),(37,'Láº¡ng SÆ¡n'),(38,'LÃ o Cai'),(39,'Long An'),(40,'Nam Äá»‹nh'),(41,'Nghá»‡ An'),(42,'Ninh BÃ¬nh'),(43,'Ninh Thuáº­n'),(44,'PhÃº Thá»'),(45,'PhÃº YÃªn'),(46,'Quáº£ng BÃ¬nh'),(47,'Quáº£ng Nam'),(48,'Quáº£ng NgÃ£i'),(49,'Quáº£ng Ninh'),(50,'Quáº£ng Trá»‹'),(51,'SÃ³c TrÄƒng'),(52,'SÆ¡n La'),(53,'TÃ¢y Ninh'),(54,'ThÃ¡i BÃ¬nh'),(55,'ThÃ¡i NguyÃªn'),(56,'Thanh HÃ³a'),(57,'Thá»«a ThiÃªn Huáº¿'),(58,'Tiá»n Giang'),(59,'TrÃ  Vinh'),(60,'TuyÃªn Quang'),(61,'VÄ©nh Long'),(62,'VÄ©nh PhÃºc'),(63,'YÃªn BÃ¡i'),(64,'ToÃ n quá»‘c'),(65,'NÆ°á»›c ngoÃ i');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `post_id` bigint DEFAULT NULL,
  `reply_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs1slvnkuemjsq2kj4h3vhx7i1` (`post_id`),
  KEY `FKaux3hl25n3q64ww0612uk786n` (`reply_id`),
  KEY `FK8kcum44fvpupyw6f5baccx25c` (`user_id`),
  CONSTRAINT `FK8kcum44fvpupyw6f5baccx25c` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKaux3hl25n3q64ww0612uk786n` FOREIGN KEY (`reply_id`) REFERENCES `comment` (`id`),
  CONSTRAINT `FKs1slvnkuemjsq2kj4h3vhx7i1` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'Good','2023-01-07 13:48:28.168000',2,NULL,1),(2,'Qua hay','2023-01-07 13:48:39.579000',2,NULL,1),(3,'Good cho nao ban ?','2023-01-07 13:49:13.545000',2,1,1),(4,'Good thi good thoi cang the','2023-01-07 13:50:19.187000',2,1,4),(5,'hay cho nao ban ?','2023-11-07 13:50:52.788000',2,2,1),(6,'Sieu hay','2023-11-07 13:51:02.591000',2,2,4),(7,'Sieu hay','2023-02-07 13:53:22.879000',2,2,4),(8,'Xin chao !','2023-01-08 12:15:46.183000',2,NULL,3);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cvsubmit`
--

DROP TABLE IF EXISTS `cvsubmit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cvsubmit` (
  `media_id` bigint NOT NULL,
  `post_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `cover_letter` varchar(1000) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `match_percent` bigint DEFAULT NULL,
  PRIMARY KEY (`media_id`,`post_id`,`user_id`),
  KEY `FKqku9e8075xelp7tm8ilymcu1x` (`post_id`),
  KEY `FKt99c8dc7ivt24v2lef9neah3j` (`media_id`,`user_id`),
  CONSTRAINT `FKqku9e8075xelp7tm8ilymcu1x` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
  CONSTRAINT `FKt99c8dc7ivt24v2lef9neah3j` FOREIGN KEY (`media_id`, `user_id`) REFERENCES `profile` (`media_id`, `user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cvsubmit`
--

LOCK TABLES `cvsubmit` WRITE;
/*!40000 ALTER TABLE `cvsubmit` DISABLE KEYS */;
INSERT INTO `cvsubmit` VALUES (17,2,4,NULL,'2023-01-08 12:08:19.827000',14),(19,2,1,'I love u','2023-02-08 22:15:00.382000',NULL),(19,4,1,'Nothing to say !','2023-04-04 21:54:02.950000',4);
/*!40000 ALTER TABLE `cvsubmit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cvviewed`
--

DROP TABLE IF EXISTS `cvviewed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cvviewed` (
  `media_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `viewer_id` bigint NOT NULL,
  `date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`media_id`,`user_id`,`viewer_id`),
  KEY `FKprobctjn4mux6h55xa26ti2ap` (`viewer_id`),
  CONSTRAINT `FKn7o0hjdtqrj1sctj4rsk7qavh` FOREIGN KEY (`media_id`, `user_id`) REFERENCES `profile` (`media_id`, `user_id`),
  CONSTRAINT `FKprobctjn4mux6h55xa26ti2ap` FOREIGN KEY (`viewer_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cvviewed`
--

LOCK TABLES `cvviewed` WRITE;
/*!40000 ALTER TABLE `cvviewed` DISABLE KEYS */;
/*!40000 ALTER TABLE `cvviewed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follow` (
  `post_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`post_id`,`user_id`),
  KEY `FKjby6aprc2rh3sxi3qu46jb22q` (`user_id`),
  CONSTRAINT `FKjby6aprc2rh3sxi3qu46jb22q` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKpbytwogrbn0l2cgqil249c9u4` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES (2,1,'2023-01-07 12:13:41.831000'),(2,4,'2023-01-08 12:08:09.198000'),(4,1,'2023-01-07 12:11:43.912000'),(4,4,'2023-01-07 12:15:08.189000'),(5,1,'2023-01-07 12:11:45.732000');
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow_user`
--

DROP TABLE IF EXISTS `follow_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follow_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` datetime(6) DEFAULT NULL,
  `follower_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK5v1c5c5e8o2om43stdn7grt15` (`user_id`,`follower_id`),
  KEY `FKnq61ygj2kbyecwfd2oy7r8di5` (`follower_id`),
  CONSTRAINT `FKglag07bsig33or7njtttnoqy9` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKnq61ygj2kbyecwfd2oy7r8di5` FOREIGN KEY (`follower_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow_user`
--

LOCK TABLES `follow_user` WRITE;
/*!40000 ALTER TABLE `follow_user` DISABLE KEYS */;
INSERT INTO `follow_user` VALUES (5,'2023-01-10 22:34:48.139000',1,5),(6,'2023-01-10 22:34:45.653000',2,3),(7,'2023-01-10 22:34:48.139000',2,5),(8,'2023-01-10 22:47:45.510000',1,3);
/*!40000 ALTER TABLE `follow_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `images`
--

DROP TABLE IF EXISTS `images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `images` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `image` longblob,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfccua10sh2240kfshte8bkbpt` (`user_id`),
  CONSTRAINT `FKfccua10sh2240kfshte8bkbpt` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `images`
--

LOCK TABLES `images` WRITE;
/*!40000 ALTER TABLE `images` DISABLE KEYS */;
INSERT INTO `images` VALUES (1,_binary '‰PNG\r\n\Z\n\0\0\0\rIHDR\0\0\0°\0\0\09\0\0\0–¡N\0\0\0sRGB\0®\Î\é\0\0\0gAMA\0\0±üa\0\0\0	pHYs\0\0\Ã\0\0\Ã\Ço¨d\0\0\nIDATx^\í\ÝPTõð¯g—va\Ö\'¢ˆA¦µjHjN@Ê®5\Ý\ÔRPo\Ü&Í²l\n\Ê\Ä|t\Í!©¨™…i6ö\"PDe} hd\à-\"¢D¢À>X~÷lty©4jûwþŸ™3\èÿ<\Ü\ã~\Ï\ïÿ\Ø\í@\"p£\é\'\Ç1‰˜c\Z0\Ç4`Ži<À\Óx€9¦ñ\0sL\ã\æ˜\Æ\Ì1˜c\Z0\Ç4`Ži<À\Óx€9¦ñ\0sL\ã\æ˜\Æ\Ì1˜c\Z0\Ç4`Ži<À\Óx€9¦ñ\0sLc?À—¿CÂ›_ ¤VúýM\Ô_\ÊÀ\âE›QT/5pL³›\Ø\Äxt-\æøòóO£\Ô\ØhCGÿŽT”¢  J\íhD<ÿ,\Æ\è™t^õ÷q2\îC¹ú@;X‹\Þ\Zuçñ\Ê\á\ßGÃ¯“dõ&\\.\ÊÁÁ0‡~„Ü”Hôj¼\Èß¬¾\ä,[²ºSGðó\ï…\Êƒ}\Ý]!§ZTž;ƒ\Üür¸‹s\çbò\0µt\æUõR±2~#ö¤¥\"§\\@¯°\ÕØ³r\"<›\ß_eV\Å\ïÀ©‚c\È*r†ö±è©„…‘\Ã\á*rk.!gû\ÇØª«„\ÒM:G-¦ÎžŠ\îFd\'€\Üþ/cZ€³t\ìf\r°=¹”2“z\ÊAªQ+)\ß,5Š®ü¼^P‘¼\ë¿¿Bj\ÕSúüA\Ôs\ä<J9]%µ‰ªv\ÐLO„n/PŠM3™ŠhW\ì ò\ÛL—¤&ûa¡³k&«\Ù>Q;\é©µÑ•œ)\ì>9=0“>+´H­¶\Êh\Ã4z\ÐGA‚:”–ž2I\í-U$?Kƒ£W\Ð\Â@¥µx\ÝtSŽ\\B9\×.WA?.N]DÓ—E\Ö7\È@¹+\Æ\Ð}£—\Óñ\êBZ=¾\Åî©¾z\è]`wCÇŽ\îp_•L\íw©Q¤\î?‹Þ˜\ß\ÊT¼ûZŽY‡Æ“\È\Ìˆø¤x<\ÝÏ¦†t r9d¤6+GoŒŽžP\äR“ý¨BvvŒò\î(ö?M©NÂ¨!\Î0Ÿù_e^–ZmTe#O\Ä\çCQµ\É\Éû\Ñ\ÊQ\"#ò\n01<\Þ}‡!00ð¦Û°>\Z8Jg\×MÂ¢e\Ç1\à¹1\Ö\Ûú9\ãqcð`\æb$n;„3\Ã0\Üÿ.U_+)\ÈvCŸö\ni õ3›\Äg½™‹iª\Z$¸…Q²u§ùtøh©X»š©\ÞIQ^r’{E\Ñ\Î\æ\ÅÀRA\âp¤\å9·+_SŒŸœ\Ï\ç\è\ÓR›-\Ó	Zü¸X1å¾­V¸\ê^§\Øu…d\È[Ec=’÷§\ä\âV\î\Òtœ\ç$\Ð\á¶ô\rX¨8i<)\áC1_Û¼ñš	)H¡\éC\ã—\åˆ5ù\îajWo±À\"vj‚\Æ\îÖ’\à\à‹!ž\×\ÆÃ·D¦\æ¦\çÔ—\Ä\æ¥\ï\ãý÷Û¹-\Ý\nqh\Øn\ÕY{‘VÜ€NÁ!\î$5^Sò½\ÉøLg†÷˜¹˜þ¸‹\Ô\ÞÈ€c® _P8ûND\Ä8/ \ìkl\Øv\n\Í\ç¶u¿\îGA\× hKj»\È\à¤t†ƒØ»‰\ÜuŽ}1t˜/,uC1!b X“\ï†\\‡\â\ïR¡3jôB‚š¿‡·›©¿\ÖA§k\çv4kÛ»\Ä!p\ïø\Í\âŽ q\âÚµ\É\ÓU‹ó+0\ç\ß\Û!›º\Û\Ö\Ç\Â_)\íjd<ŽŒ\ßý\Ü[\ì\Òe\Ý1:|2úÉ«q0yÒ›<Lu(ÚŸ@ÿ¿2Mð8L\è]\n]VŽ8\è¹Êÿ\r\Ò\ÄI¡§\é\ì/·:\Ô\ÖI;\ï4©ÛkCˆ)ŸPcOj©ü…¾]5‡‚¼ §^ÿ’òn\ÖG\Ýha™´0À‰\à@Q«?¥-›\ÖQÒª\å”øö|Š‰œIQq	”œY\Üf\×l\ÈJ Ù‰\'\èÚ¨À”M\ï©Hº\Ñ\Ôõ…×‡K–BZ=Ÿ¾µØ¶›™Î¦}@Óƒ†RhDEÏžI/¼üe–ž¥]q¤\é<ˆžšö\nm<©—Ž¿³\ì6ÀòA4ý?q9q8ùjT\ä9b\í<SykcW\Æl:ü6\rW\n\äú\Ä\"Ú‘I³t”•™A©»>¡\Å/M¡\à!¡4k\é\Êoõ^Ltlq%4\ÔZ\è·5©³ *0ŽJÉ·”l¢˜—wÝ¶³^ý¡‘˜Ä¶»9¿°\ß\n<YªÀ\æB\Ú>«)\rZq²\Å_X«nG€õ%ô“.‹²²Ú¹Î¥\Òv\ÍbLt\â½`R\n®ô\äò\Ób}k\ÎB\çw\Ç\Ò\0\'W\Z2\ï{º\Ø<¦Jœµˆ2›ÿ™wPd9AÑ—bv__\Ø6—^ú¼ü\Ï_\ß+˜X…0ŸI¢	]Å™uŸ´½\äž\ï\Û`sþJ\Z¥j}]ô†›\ëxJ*jG\r2\ç\Ò\Ò\'\Ä\î^9’–œl\ãñÔ§\Ñ<­‚7ñ\Ú\ÍÖ€Í¹\Ë\éù©\Ôò6«i\ßr‚@]&®¡_\Í”K[\Ê\îf}¼ó˜˜\Ä9ø…!f\Ú@È‹>\Ç\ÊõGP#µ\ßI÷\Ï\Å75>\à\íÛªv\"\Êû\Ö\×E\ê\nÓ‘®3\Â\É?#û¶µ4`\Å4Ô–¡\ì‚Yj³ªCÁ\Þp\Z€–sZG \Z¨øn¶\Üùavn×š\ÝcdBÀYs0¦³G\Öý)…÷\Ê\êQ’ž†ƒz´!#Ð¿ü\ZOÂ¡B3WôöRH­¢ú³H?$CÀ°ŽRCSŽý\'!|LÀpŸ\Ì[Jmz\Ü[ùµ\Ï\0[ûb«é§•ƒ\ïTD[«ðùXµ¦­O™S{\Ó Z~?‚ƒ\ÛX?5\äbc\âz3+¡ý\×4<\åu=õgÓ±¯\Ò¶ž_@\æqa“à§°\à\\ž3´Á½Ú·f\Î\0»pƒ\Ñ£˜\\2\Z`hRh«°\'\Ö.Á†“7øú™\ÙCmƒ\Ø\åŠ×²\íq\íŒ17_¤U¢Á±¼½[–ßš‚T,ŒÀü¯ª\Ð?|)Ö¾ñOh¤}®oMFš“nð¹¸GHžy\Ø	N„`„\Ígó÷\nq\Üf§>§÷^‹£ðG»‘ø~\àÚFGÍ£·\ÖfÐ…kóŽ+´\ï\ÍG\ÉMHý\Ð8Šy+™WJ»¬®¡\Íñ(öiò\ÄB.xÿÓ±´\àd\Ò\Ù÷7\Ógo¤W£\Â\é}Ý¬“ñu:‘\×\Ð	4=j6EÏžE\Ó#ž¡)ž¤\á\Ñø\è\Úøc]²™{r¶Ñ¢\ÈPòS	$¨ü(dÆ«´\î@‹\Þ%fúe\Õx\n?rk+8Œ±Ÿÿ\'\Îø\ÎW\ÔB¦TB\å\âÁl€^o„©ƒ<»ªmº¾j”ÿ}¡ƒ ‚Æ»‹\ÍWkðû¹Ë°(\Äk(Upj¡¯Ñ‹\ÕX†Ž=»´2\ÑaTM9J*	*w5œÉ„jñI\Õ]\Õmª‹QX\ãŸ\î÷\Ú\0ÂŽ¾\Ìq#«\×:`Ži<À\Óx€9¦ñ\0sL\ã\æ˜\Æ\Ì1˜c\Z0\Ç4`Ži<À\Óx€9¦ñ\0sL\ã\æ˜\Æ\Ì1ø?œ™™ß‹\á”\0\0\0\0IEND®B`‚',5),(2,_binary '‰PNG\r\n\Z\n\0\0\0\rIHDR\0\0\0\0\0\0	\0\0\0þX6£\0\0\0sRGB\0®\Î\é\0\0\0gAMA\0\0±üa\0\0\0	pHYs\0\0\Ã\0\0\Ã\Ço¨d\0\0\0IDAT(Sc””úÏ€0Ai¬`\ØH20\0\0V\\\'@¤À\0\0\0\0IEND®B`‚',5);
/*!40000 ALTER TABLE `images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `industry`
--

DROP TABLE IF EXISTS `industry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `industry` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `industry`
--

LOCK TABLES `industry` WRITE;
/*!40000 ALTER TABLE `industry` DISABLE KEYS */;
INSERT INTO `industry` VALUES (1,'IT'),(2,'Sales'),(3,'BANKING'),(4,'CHEF'),(5,'TEACHER'),(6,'ACCOUNTANT'),(7,'FINANCE'),(8,'CONSULTANT'),(9,'DIGITAL-MEDIA'),(10,'PUBLIC-RELATIONS'),(11,'BUSINESS-DEVELOPMENT'),(12,'AVIATION'),(14,'CONSTRUCTION'),(15,'ENGINEERING'),(16,'APPAREL'),(17,'Testing'),(18,'BPO'),(19,'AGRICULTURE'),(20,'Advocate'),(21,'Health and fitness'),(22,'HR'),(23,'AUTOMOBILE');
/*!40000 ALTER TABLE `industry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `media_resource`
--

DROP TABLE IF EXISTS `media_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `media_resource` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `public_id` varchar(255) DEFAULT NULL,
  `resource_type` varchar(255) DEFAULT NULL,
  `url` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media_resource`
--

LOCK TABLES `media_resource` WRITE;
/*!40000 ALTER TABLE `media_resource` DISABLE KEYS */;
INSERT INTO `media_resource` VALUES (6,'pkamcxwnysg4qeccmbxp','image','https://res.cloudinary.com/dh0hs3o2a/image/upload/v1672988934/pkamcxwnysg4qeccmbxp.jpg'),(8,'fxtpukaapclptdy5miof','image','https://res.cloudinary.com/dh0hs3o2a/image/upload/v1672989053/fxtpukaapclptdy5miof.png'),(17,'qvi636rtvl7jlfhjnu5f','image','https://res.cloudinary.com/dh0hs3o2a/image/upload/v1673146190/qvi636rtvl7jlfhjnu5f.pdf'),(18,'kyl5s7lmduoar8sv5f2c','image','https://res.cloudinary.com/dh0hs3o2a/image/upload/v1673146220/kyl5s7lmduoar8sv5f2c.pdf'),(19,'rphkxbmaskwopxim45zk','image','https://res.cloudinary.com/dh0hs3o2a/image/upload/v1673147832/rphkxbmaskwopxim45zk.pdf'),(23,'spa99evbuz1p5nn8rqqb','image','https://res.cloudinary.com/dh0hs3o2a/image/upload/v1673534466/spa99evbuz1p5nn8rqqb.jpg'),(25,'b9fk8q4sxksvsnhomddk','image','https://res.cloudinary.com/dh0hs3o2a/image/upload/v1673535008/b9fk8q4sxksvsnhomddk.png'),(26,'hej5cj1begupj6thmros','image','https://res.cloudinary.com/dh0hs3o2a/image/upload/v1677165642/hej5cj1begupj6thmros.pdf'),(28,'s0qh1tuyfaxia9ljwahf','image','https://res.cloudinary.com/dh0hs3o2a/image/upload/v1678889551/s0qh1tuyfaxia9ljwahf.jpg');
/*!40000 ALTER TABLE `media_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` datetime(6) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `post_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn1l10g2mvj4r1qs93k952fshe` (`post_id`),
  KEY `FKb0yvoep4h4k92ipon31wmdf7e` (`user_id`),
  CONSTRAINT `FKb0yvoep4h4k92ipon31wmdf7e` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKn1l10g2mvj4r1qs93k952fshe` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (1,'2023-01-14 13:15:29.123000','Payment success with order: ',NULL,3),(2,'2023-01-14 21:09:00.670000','Your followed company has posted a new job !',14,1),(3,'2023-01-14 21:09:01.021000','Your followed company has posted a new job !',14,2),(4,'2023-01-14 21:10:35.582000','Payment success with order: PAYID-MPBLPRA43R40014UP153315M',NULL,3),(5,'2023-01-14 21:15:23.996000','1 User has Submitted their CV to your post !',4,3),(6,'2023-01-14 21:17:01.087000','Admin has accepted your post !',9,3),(7,'2023-01-14 21:17:02.604000','Admin has accepted your post !',10,3),(8,'2023-01-14 21:17:03.929000','Admin has accepted your post !',11,3),(9,'2023-01-14 21:17:05.980000','Admin has accepted your post !',12,3),(10,'2023-01-14 21:17:14.355000','Admin has Delete your post !',9,3),(11,'2023-01-14 21:17:34.573000','Admin has accepted your post !',9,3),(12,'2023-01-14 21:24:31.185000','You have changed your password through Reset password.',NULL,1),(13,'2023-01-14 21:25:12.687000','You have confirmed your email address.',NULL,1),(14,'2023-01-14 22:03:42.586000','Admin has Delete your post !',14,3),(15,'2023-01-14 22:05:36.161000','Admin has accepted your post !',14,3),(16,'2023-01-14 22:07:15.634000','Admin has Delete your post !',14,3),(17,'2023-01-14 22:19:27.935000','Your followed company has posted a new job !',15,1),(18,'2023-01-14 22:19:27.935000','Your followed company has posted a new job !',15,2),(19,'2023-01-14 22:19:27.935000','1 User has Submitted their CV to your post !',4,3),(20,'2023-01-14 22:25:30.980000','Payment success with order: PAYID-MPBMQ3I6DG11331TX434040N',NULL,3),(21,'2023-01-14 22:34:02.638000','Your followed company has posted a new job !',16,1),(23,'2023-01-14 22:38:03.065000','1 User has Submitted their CV to your post !',4,3),(24,'2023-01-14 22:40:21.162000','Admin has Delete your post !',14,3),(25,'2023-01-14 22:40:26.018000','Admin has accepted your post !',14,3),(26,'2023-01-14 22:40:43.398000','Admin has accepted your post !',14,3),(27,'2023-02-08 22:15:00.423000','Hoang Van Binh has Submitted his/her CV to your post !',2,3),(28,'2023-02-08 22:27:19.654000','Payment success with order: PAYID-MPR36PI257889335G185681K',NULL,3),(29,'2023-02-18 09:24:21.738000','Binh Company has created an appointment with you !',NULL,1),(30,'2023-02-18 09:24:21.760000','You have created an appointment with Hoang Van Binh !',NULL,3),(31,'2023-02-18 09:24:37.809000','Binh Company has created an appointment with you !',NULL,4),(32,'2023-02-18 09:24:37.822000','You have created an appointment with Thieu Sy Manh !',NULL,3),(33,'2023-02-18 09:25:42.971000','Binh Company has updated an appointment with you !',NULL,1),(34,'2023-02-18 09:25:42.980000','You have updated an appointment with Hoang Van Binh !',NULL,3),(35,'2023-02-18 09:26:01.671000','Binh Company has cancelled an appointment with you !',NULL,4),(36,'2023-02-18 09:26:01.679000','You have cancelled an appointment with Thieu Sy Manh !',NULL,3),(37,'2023-02-18 09:26:51.565000','Hoang Van Binh has denied your appointment !',NULL,3),(38,'2023-02-18 09:34:19.595000','Hoang Van Binh has denied your appointment !',NULL,3),(39,'2023-02-18 09:36:11.886000','Hoang Van Binh has denied your appointment !',NULL,3),(40,'2023-02-18 09:36:30.365000','Hoang Van Binh has denied your appointment !',NULL,3),(41,'2023-02-18 09:36:35.216000','Hoang Van Binh has denied your appointment !',NULL,3),(42,'2023-02-18 09:36:58.607000','Binh Company has created an appointment with you !',NULL,1),(43,'2023-02-18 09:36:58.615000','You have created an appointment with Hoang Van Binh !',NULL,3),(44,'2023-02-18 09:37:22.297000','Binh Company has cancelled an appointment with you !',NULL,1),(45,'2023-02-18 09:37:22.307000','You have cancelled an appointment with Hoang Van Binh !',NULL,3),(46,'2023-02-18 09:40:45.511000','Binh Company has created an appointment with you !',NULL,1),(47,'2023-02-18 09:40:45.550000','You have created an appointment with Hoang Van Binh !',NULL,3),(48,'2023-02-18 09:41:04.967000','Binh Company has updated an appointment with you !',NULL,1),(49,'2023-02-18 09:41:04.975000','You have updated an appointment with Hoang Van Binh !',NULL,3),(50,'2023-02-18 09:41:17.995000','Binh Company has cancelled an appointment with you !',NULL,1),(51,'2023-02-18 09:41:18.003000','You have cancelled an appointment with Hoang Van Binh !',NULL,3),(52,'2023-02-18 10:07:12.933000','Payment success with order: PAYID-MPYEBDY1KP49308FB894291X',NULL,5),(53,'2023-02-23 21:31:58.198000','You have changed your password through Reset password.',NULL,1),(54,'2023-04-04 21:54:03.002000','Hoang Van Binh has Submitted his/her CV to your post !',4,3),(55,'2023-04-04 21:58:05.114000','Admin has accepted your post - Second title !',1,3),(56,'2023-04-04 21:58:08.473000','Your followed company has posted a new job - Second title !',1,1),(57,'2023-04-04 21:58:08.473000','Your followed company has posted a new job - Second title !',1,2);
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `accepted_date` datetime(6) DEFAULT NULL,
  `benifit` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `contact` varchar(255) DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `currency` varchar(30) NOT NULL,
  `description` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `experience` varchar(255) NOT NULL,
  `expiration_date` datetime(6) NOT NULL,
  `gender` int NOT NULL,
  `location` varchar(200) NOT NULL,
  `method` varchar(255) NOT NULL,
  `position` varchar(255) NOT NULL,
  `recruit` bigint DEFAULT NULL,
  `requirement` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `salary` bigint DEFAULT NULL,
  `status` varchar(30) NOT NULL,
  `title` varchar(200) DEFAULT NULL,
  `view_count` int DEFAULT NULL,
  `accepted_by` bigint DEFAULT NULL,
  `author` bigint DEFAULT NULL,
  `city` bigint DEFAULT NULL,
  `industry` bigint DEFAULT NULL,
  `service_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtocx2s7uy9ivf6m60l8qj984c` (`accepted_by`),
  KEY `FKt7refuxfvwatrlcwobvc634fc` (`author`),
  KEY `FKgujw94bnvv7w9j8eyd4mymrtf` (`city`),
  KEY `FKp5dwme3c1f3nuco85k8agpng3` (`industry`),
  KEY `FK26koi7ni7nrc8bgqn94hd5sju` (`service_id`),
  CONSTRAINT `FK26koi7ni7nrc8bgqn94hd5sju` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`),
  CONSTRAINT `FKgujw94bnvv7w9j8eyd4mymrtf` FOREIGN KEY (`city`) REFERENCES `city` (`id`),
  CONSTRAINT `FKp5dwme3c1f3nuco85k8agpng3` FOREIGN KEY (`industry`) REFERENCES `industry` (`id`),
  CONSTRAINT `FKt7refuxfvwatrlcwobvc634fc` FOREIGN KEY (`author`) REFERENCES `user` (`id`),
  CONSTRAINT `FKtocx2s7uy9ivf6m60l8qj984c` FOREIGN KEY (`accepted_by`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,'2023-04-04 21:58:05.083000','Bao hiem suc khoe','MrBinh: 0337445599','2023-01-07 10:56:01.339000','AGREEMENT','Máº·c Ã¡o vÃ o thá»© anh cáº§n lÃ  ná»¥ cÆ°á»i cá»§a em ?','THREE_YEAR','2023-06-06 00:00:00.000000',0,'Binh An, Di an','FULL_TIME','Manager',15,'Toeic 650+ or Ielts 6.0+',NULL,'ACTIVE','Second title',6,2,3,1,1,3),(2,'2023-01-07 11:01:17.143000','Bao hiem suc khoe','MrBinh: 0337445596','2023-02-07 11:02:28.165000','USD','Máº·c Ã¡o vÃ o thá»© anh cáº§n lÃ  ná»¥ cÆ°á»i cá»§a em ?','NONE','2023-06-06 00:00:00.000000',0,'Binh An, Di an','FULL_TIME','Staff',5,'Toeic 550+ or Ielts 5.5+',1000,'ACTIVE','First title',8,2,3,1,1,3),(4,'2023-01-07 11:14:08.058000','Bao hiem suc khoe','MrBinh: 0337445596','2023-03-06 16:40:59.598000','USD','Máº·c Ã¡o vÃ o thá»© anh cáº§n lÃ  ná»¥ cÆ°á»i cá»§a em ?','NONE','2023-06-06 00:00:00.000000',0,'Binh An, Di an','FULL_TIME','Staff',5,'Toeic 550+ or Ielts 5.5+',1000,'ACTIVE','First title',0,2,3,1,3,3),(5,'2023-01-07 11:14:06.631000','Bao hiem suc khoe','MrBinh: 0337445596','2023-03-06 16:42:01.807000','USD','Máº·c Ã¡o vÃ o thá»© anh cáº§n lÃ  ná»¥ cÆ°á»i cá»§a em ?','NONE','2023-06-06 00:00:00.000000',0,'Binh An, Di an','FULL_TIME','Staff',5,'Toeic 550+ or Ielts 5.5+',1000,'ACTIVE','First title',0,2,3,1,3,3),(8,'2023-01-14 21:17:34.543000','Bao hiem suc khoe','MrBinh: 0337445596','2023-01-06 16:45:40.870000','USD','Máº·c Ã¡o vÃ o thá»© anh cáº§n lÃ  ná»¥ cÆ°á»i cá»§a em ?','NONE','2023-06-06 00:00:00.000000',0,'Binh An, Di an','FULL_TIME','Staff',5,'Toeic 550+ or Ielts 5.5+',1000,'WAIT_FOR_ACCEPT','First title',0,NULL,3,1,1,3),(9,'2023-01-14 21:17:34.543000','string','string','2023-01-14 21:02:28.486000','AGREEMENT','string','NONE','2023-06-06 00:00:00.000000',0,'Di An','FULL_TIME','Staff',10,'string',1000,'ACTIVE','string',0,2,3,1,3,3),(10,'2023-01-14 21:17:02.578000','string','string','2023-01-14 21:04:40.412000','AGREEMENT','string','NONE','2023-06-06 00:00:00.000000',0,'Di An','FULL_TIME','Staff',10,'string',1000,'ACTIVE','string',0,2,3,1,1,3),(11,'2023-01-14 21:17:03.895000','string','string','2023-01-14 21:06:51.034000','AGREEMENT','string','NONE','2023-06-06 00:00:00.000000',0,'Di An','FULL_TIME','Staff',10,'string',1000,'ACTIVE','string',0,2,3,1,1,3),(12,'2023-01-14 21:17:05.934000','string','string','2023-01-14 21:07:12.817000','AGREEMENT','string','NONE','2023-06-06 00:00:00.000000',0,'Di An','FULL_TIME','Staff',10,'string',1000,'ACTIVE','string',0,2,3,1,3,3),(14,'2023-01-14 22:40:43.380000','string','string','2023-01-14 21:08:52.529000','AGREEMENT','string','NONE','2023-06-06 00:00:00.000000',0,'Di An','FULL_TIME','Staff',10,'string',1000,'ACTIVE','string',0,2,3,1,1,3),(15,NULL,'string','string','2023-01-14 22:17:01.740000','AGREEMENT','string','NONE','2023-06-06 00:00:00.000000',0,'Di An','FULL_TIME','Staff',10,'string',NULL,'WAIT_FOR_ACCEPT','string',0,NULL,3,1,3,3),(16,NULL,'string','string','2023-01-14 22:34:02.493000','AGREEMENT','string','NONE','2023-06-06 00:00:00.000000',0,'Di An','FULL_TIME','Staff',10,'string',NULL,'WAIT_FOR_ACCEPT','string',0,NULL,3,1,3,3);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profile` (
  `media_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `experience` varchar(255) DEFAULT NULL,
  `is_public` tinyint(1) NOT NULL DEFAULT '0',
  `last_modified` datetime(6) NOT NULL,
  `method` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `skills_and_knowledges` mediumtext,
  `work_experiences` mediumtext,
  PRIMARY KEY (`media_id`,`user_id`),
  KEY `FKawh070wpue34wqvytjqr4hj5e` (`user_id`),
  CONSTRAINT `FKawh070wpue34wqvytjqr4hj5e` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKqitv6qsbqikwwp1h8sgku01vb` FOREIGN KEY (`media_id`) REFERENCES `media_resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` VALUES (17,4,'TWO_YEAR',1,'2023-01-08 09:49:49.230000','INTERN','CV Frontend','Leader','Experience\nWork: 2 months internship at FPT software\nKnowledge of Java, C++, C#, JavaScirpt, OOP.\nDatabase: SQL Server, MySQL, MongoDB.\nTools: Git & Github, VSCode, Eclipse\nWeb: Java Spring, Java Servlet, React, NodeJs, Angular','TODO WEBSITE [ 01/11/2022 - 21/11/2022 ] Team member'),(18,4,'NONE',1,'2023-01-08 12:04:41.995000','FULL_TIME','CV Frontend','Staff','Experience\nWork: 2 months internship at FPT software\nKnowledge of Java, C++, C#, JavaScirpt, OOP.\nDatabase: SQL Server, MySQL, MongoDB.\nTools: Git & Github, VSCode, Eclipse\nWeb: Java Spring, Java Servlet, React, NodeJs, Angular','TODO WEBSITE [ 01/11/2022 - 21/11/2022 ] Team member'),(19,1,'NONE',1,'2023-01-08 10:17:13.354000','PART_TIME','CV Backend','Staff','EDUCATION Bachelor of Arts Business Management Texas A&M University Central Texas 2012 - 2016 Killeen, TX SKILLS Negotiation CRM (Salesforce) Problem-solving Lead Generation (LinkedIn, email) Reporting Results-oriented Microsoft Office (Word, Excel, PowerPoint)','Sales Specialist Humana September 2018 - current San Antonio, TX Â· Created and delivered presentations to decision makers, leading to a 27% improvement over expected lead conversion Â· Recruited physicians and staff to attend local, regional, and national training programs for Humana products, resulting in $285,000 in new revenue Â· Supported the evaluation of new products, and provided clinical feedback to marketing and sales Â· Provided primary clinical training and education to customers, which improved the adoption of new products by 36% Sales Representative TQL April 2016 - September 2018 San Antonio, TX Â· Executed on outbound calling strategy to warm leads, leading to a close rate of 26%, which exceeded expectations by 50% Â· Worked closely with existing customers to understand their needs, resulting in $400,000 in retention revenue Â· Recorded notes in Salesforce to on-board customer service reps and account managers to customer profiles Â· Maintained up-to-date knowledge of sales strategies and product offerings, leading to $225,000 in up-sell revenue Assistant Manager Family Dollar June 2013 - April 2016 Killeen, TX Â· Served as point of contact for customer resolution, successfully deescalating 95% of issues without management involvement Â· Handled merchandise returns, assisted manager with ordering new merchandise, and scheduled store associates to accept deliveries and transfer to stockroom and sales floor Â· Trained 20+ sales associates in running the POS system, customer service practices, and opening and closing processes Â· Deposited cash and checks to bank, and helped the store manager maintain accurate records and time cards for payroll'),(26,1,'UNDER_ONE_YEAR',1,'2023-02-23 22:21:49.832000','FULL_TIME','Binh CV','Staff','Experience\nWork: 2 months internship at FPT software\nKnowledge of Java, C++, C#, JavaScirpt, OOP.\nDatabase: SQL Server, MySQL, MongoDB.\nTools: Git & Github, VSCode, Eclipse\nWeb: Java Spring, Java Servlet, React, NodeJs, Angular','Backend EC Website [ 04/07/2022 - 31/08/2022 ] Team leader\nTeam size: 6 members\nDescription: A Web application that helps searching and ordering\nproducts for buyers and posting products for sale with sellers.\nFrameWorks: MySql, Java Spring Boot');
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` datetime(6) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `handle` tinyint(1) DEFAULT '0',
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `report_content` varchar(255) DEFAULT NULL,
  `post_id` bigint NOT NULL,
  `handle_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnuqod1y014fp5bmqjeoffcgqy` (`post_id`),
  CONSTRAINT `FKnuqod1y014fp5bmqjeoffcgqy` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` VALUES (2,'2023-01-14 11:24:42.247000','binhhv@gmail.com',1,'Demo report','0337445596','string',1,'2023-02-08 23:04:47.138000'),(4,'2023-01-14 11:24:42.247000','binhhv@gmail.com',1,'Demo report','0337445596','string',1,'2023-02-08 23:04:49.895000'),(5,'2023-01-14 11:24:42.247000','binhhv@gmail.com',0,'Demo report','0337445596','string',2,NULL),(6,'2023-01-14 11:24:48.997000','binhhv@gmail.com',0,'Demo report','0337445596','string',4,NULL),(7,'2023-01-14 11:24:54.491000','binhhv@gmail.com',1,'Demo report','0337445596','string',5,NULL);
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `active` tinyint(1) DEFAULT '1',
  `created_date` datetime(6) DEFAULT NULL,
  `currency` varchar(30) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `post_duration` bigint NOT NULL,
  `price` double NOT NULL,
  `type` varchar(30) NOT NULL,
  `can_filter_cv_submit` bit(1) DEFAULT NULL,
  `can_search_cv` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (1,1,'2023-01-06 15:20:28.387000','USD','Basic service will allow employer to post a job recruitment and allow job seeker to submit their Resume to the post. ','Basic Service',1,10,'BASIC',_binary '\0',_binary '\0'),(3,1,'2023-01-06 15:20:48.540000','USD','Premium service will allow employer to post a job recruitment and allow job seeker to submit their Resume to the post. Beside that employer are able to search for job seeker public resume and filter resume submit to their job recruitment.','Premiun Serivce',2,30,'PREMIUM',_binary '',_binary '');
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `active` bit(1) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `email_confirm` bit(1) DEFAULT NULL,
  `name` varchar(120) DEFAULT NULL,
  `password` varchar(120) DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `service_expiration_date` datetime(6) DEFAULT NULL,
  `avatar_id` bigint DEFAULT NULL,
  `city_id` bigint DEFAULT NULL,
  `industry_id` bigint DEFAULT NULL,
  `current_service` bigint DEFAULT NULL,
  `wrong_password_count` bigint DEFAULT NULL,
  `cover_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  KEY `FK64ydoqa8wkadupx8aci0k4v2h` (`avatar_id`),
  KEY `FK29eqyw0gxw5r4f1ommy11nd9i` (`city_id`),
  KEY `FK5b4jwu5ti1o1mtwrhka28wmrp` (`industry_id`),
  KEY `FKiqx6maqf4rf527o7oejjoip3t` (`current_service`),
  KEY `FK7a8x3p8wqax85sr0pvgaw021t` (`cover_id`),
  CONSTRAINT `FK29eqyw0gxw5r4f1ommy11nd9i` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`),
  CONSTRAINT `FK5b4jwu5ti1o1mtwrhka28wmrp` FOREIGN KEY (`industry_id`) REFERENCES `industry` (`id`),
  CONSTRAINT `FK64ydoqa8wkadupx8aci0k4v2h` FOREIGN KEY (`avatar_id`) REFERENCES `media_resource` (`id`),
  CONSTRAINT `FK7a8x3p8wqax85sr0pvgaw021t` FOREIGN KEY (`cover_id`) REFERENCES `media_resource` (`id`),
  CONSTRAINT `FKiqx6maqf4rf527o7oejjoip3t` FOREIGN KEY (`current_service`) REFERENCES `service` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,_binary '','Di An','',NULL,NULL,'thebest11447@gmail.com',_binary '','Hoang Van Binh','$2a$10$96JdF3Z2qbQTFcuawXt7sObwqTVstQk9W6XyrjmfVDdbMtmtXV9vC','0337445595','ROLE_USER',NULL,NULL,1,3,NULL,0,28),(2,_binary '',NULL,NULL,NULL,NULL,'binhhv@admin.com',_binary '','Binh Admin','$2a$10$JcIWLoWBJZY5JiqbhdpOT.DfdVY6ncSHg/s13cV8bLxird6jPGOF.',NULL,'ROLE_ADMIN',NULL,NULL,NULL,NULL,NULL,0,NULL),(3,_binary '','Ba Ria',NULL,'2023-01-07 00:00:00.000000','PhiÃªn báº£n V583 - Lá»… Há»™i GiÃ¡ng Sinh sáº½ Ä‘Æ°á»£c cáº­p nháº­t vÃ o ngÃ y 22/12/2022 vá»›i cÃ¡c tÃ­nh nÄƒng Ä‘Æ°á»£c má»Ÿ rá»™ng cÃ¹ng trang bá»‹ má»›i vá»›i sá»©c máº¡nh chiáº¿n Ä‘áº¥u lá»›n. Nhanh tay tham gia cÃ¹ng VÃµ LÃ¢m Chi Má»™ng tráº£i nghiá»‡m cÃ¡c hoáº¡t Ä‘á»™ng má»›i.','19110170@student.hcmute.edu.vn',_binary '','Binh Company','$2a$10$YiXzKiBd1sYJdfiv8VLMPOgRh2nud9W20j6EgSpSdJT.rsBCQNtty','0337445596','ROLE_EMPLOYER','2024-10-07 00:00:00.000000',NULL,1,1,3,0,NULL),(4,_binary '',NULL,NULL,NULL,NULL,'symanh@gmail.com',_binary '\0','Thieu Sy Manh','$2a$10$qr2JNAH5jeicebzdE4xbku4BDxpAQEkBt1nICWp.wJgjLAWxZaWwC',NULL,'ROLE_USER',NULL,NULL,NULL,NULL,NULL,0,NULL),(5,_binary '','Ba Ria',NULL,'2023-02-07 00:00:00.000000','','19110171@student.hcmute.edu.vn',_binary '','Nam Company','$2a$10$YiXzKiBd1sYJdfiv8VLMPOgRh2nud9W20j6EgSpSdJT.rsBCQNtty','0337445597','ROLE_EMPLOYER','2023-03-18 00:00:00.000000',NULL,2,2,3,0,NULL),(7,_binary '','Ba Ria',NULL,'2023-02-07 00:00:00.000000','','19110172@student.hcmute.edu.vn',_binary '','Tung Company','$2a$10$YiXzKiBd1sYJdfiv8VLMPOgRh2nud9W20j6EgSpSdJT.rsBCQNtty','0337445598','ROLE_EMPLOYER','2023-03-18 00:00:00.000000',NULL,2,2,3,0,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_order`
--

DROP TABLE IF EXISTS `user_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_order` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `currency` varchar(30) NOT NULL,
  `duration` int NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `paid_date` datetime(6) DEFAULT NULL,
  `payment_url` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `status` varchar(30) NOT NULL,
  `total` double NOT NULL,
  `service_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc0udsp041ygmlpgo354elsw0n` (`service_id`),
  KEY `FKj86u1x7csa8yd68ql2y1ibrou` (`user_id`),
  CONSTRAINT `FKc0udsp041ygmlpgo354elsw0n` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`),
  CONSTRAINT `FKj86u1x7csa8yd68ql2y1ibrou` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_order`
--

LOCK TABLES `user_order` WRITE;
/*!40000 ALTER TABLE `user_order` DISABLE KEYS */;
INSERT INTO `user_order` VALUES (24,'2023-02-08 22:26:53.217000','USD',2,'Update service form 2024-08-07 00:00:00.0 to Mon Oct 07 00:00:00 ICT 2024 .','2023-02-08 22:27:19.677000','https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=EC-5RC218284M751240Y',30,'PAID',60,3,3),(25,'2023-02-18 10:05:49.820000','USD',1,'Rent new service for 1 month(s).','2023-02-18 10:07:12.965000','https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=EC-5NV52562W3335100M',30,'PAID',30,3,5);
/*!40000 ALTER TABLE `user_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `view_page`
--

DROP TABLE IF EXISTS `view_page`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `view_page` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` datetime(6) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `viewer_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjmfpmhbyyqioqgm7fpwp0k6wk` (`user_id`),
  KEY `FKmmbkugjryqkg496vmevrbb5wo` (`viewer_id`),
  CONSTRAINT `FKjmfpmhbyyqioqgm7fpwp0k6wk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKmmbkugjryqkg496vmevrbb5wo` FOREIGN KEY (`viewer_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `view_page`
--

LOCK TABLES `view_page` WRITE;
/*!40000 ALTER TABLE `view_page` DISABLE KEYS */;
INSERT INTO `view_page` VALUES (3,'2023-01-08 11:55:03.258000',3,1),(4,'2023-01-08 12:03:22.842000',3,4),(5,'2023-01-08 12:03:42.351000',5,4),(6,'2023-01-08 12:07:16.361000',5,4),(7,'2023-02-08 12:03:22.842000',3,4),(8,'2023-03-08 12:03:22.842000',3,4),(9,'2023-03-23 21:51:06.507000',3,1),(10,'2023-03-23 21:51:06.507000',5,1),(11,'2023-03-23 21:51:06.507000',5,1),(12,'2023-03-23 21:51:06.507000',5,1),(13,'2023-03-23 21:51:06.507000',5,1);
/*!40000 ALTER TABLE `view_page` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `view_post`
--

DROP TABLE IF EXISTS `view_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `view_post` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` datetime(6) NOT NULL,
  `post_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnulxnnivljptifc2dmviu0uc4` (`post_id`),
  CONSTRAINT `FKnulxnnivljptifc2dmviu0uc4` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `view_post`
--

LOCK TABLES `view_post` WRITE;
/*!40000 ALTER TABLE `view_post` DISABLE KEYS */;
INSERT INTO `view_post` VALUES (1,'2023-02-20 00:00:00.000000',1),(2,'2023-02-20 00:00:00.000000',1),(3,'2023-02-20 00:00:00.000000',1),(4,'2023-02-27 00:00:00.000000',1),(5,'2023-02-27 00:00:00.000000',1),(6,'2023-02-27 00:00:00.000000',1),(7,'2023-02-27 00:00:00.000000',2),(8,'2023-02-20 00:00:00.000000',2),(9,'2023-02-20 00:00:00.000000',2),(10,'2023-02-20 00:00:00.000000',2),(11,'2023-02-20 00:00:00.000000',2),(12,'2023-02-20 00:00:00.000000',2),(13,'2023-02-20 00:00:00.000000',2),(14,'2023-02-28 21:41:39.604000',2);
/*!40000 ALTER TABLE `view_post` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-04 22:15:12
