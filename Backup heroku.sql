-- MySQL dump 10.13  Distrib 5.6.50, for Linux (x86_64)
--
-- Host: localhost    Database: heroku_46500e17176aaa5
-- ------------------------------------------------------
-- Server version	5.6.50-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
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
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `achievement` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `image_id` bigint(20) DEFAULT NULL,
  `owner_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqssc8sm1lsm8qq80q7kjkeh70` (`image_id`),
  KEY `FK86x40nioyti4mcpa74y4vmowv` (`owner_id`),
  CONSTRAINT `FK86x40nioyti4mcpa74y4vmowv` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKqssc8sm1lsm8qq80q7kjkeh70` FOREIGN KEY (`image_id`) REFERENCES `media_resource` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `achievement`
--

LOCK TABLES `achievement` WRITE;
/*!40000 ALTER TABLE `achievement` DISABLE KEYS */;
INSERT INTO `achievement` VALUES (3,NULL,'Achievement 1 Technology','ACTIVITY','https://www.youtube.com/watch?v=xypzmu5mMPY&list=RDMMZuk5zGv5Un4&index=10',124,1),(4,NULL,'Running Viet Race','CERTIFICATE','https://www.youtube.com/watch?v=X7sSE3yCNLI&list=RDMMZuk5zGv5Un4&index=11',23,1),(14,'2023-04-28 18:58:16.724000','Olypic mathematic asian44','CERTIFICATE','https://www.youtube.com',104,1);
/*!40000 ALTER TABLE `achievement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `complete` tinyint(1) DEFAULT '0',
  `deny` tinyint(1) DEFAULT '0',
  `note` varchar(255) DEFAULT NULL,
  `start_time` datetime(6) NOT NULL,
  `employer` bigint(20) DEFAULT NULL,
  `user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmfgcxvin4tj2llrl6yiktllxp` (`employer`),
  KEY `FK2c4mn43c0l11hgx6fwmr7wyvj` (`user`),
  CONSTRAINT `FK2c4mn43c0l11hgx6fwmr7wyvj` FOREIGN KEY (`user`) REFERENCES `user` (`id`),
  CONSTRAINT `FKmfgcxvin4tj2llrl6yiktllxp` FOREIGN KEY (`employer`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
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
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `banner` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `image_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK104qa85ws27ig3yxsc0j9idax` (`image_id`),
  CONSTRAINT `FK104qa85ws27ig3yxsc0j9idax` FOREIGN KEY (`image_id`) REFERENCES `media_resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` mediumtext,
  `sender_id` bigint(20) NOT NULL,
  `time` datetime(6) DEFAULT NULL,
  `user1_seen` tinyint(1) DEFAULT '0',
  `user2_seen` tinyint(1) DEFAULT '0',
  `chat_room_id` bigint(20) NOT NULL,
  `id_image` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj52yap2xrm9u0721dct0tjor9` (`chat_room_id`),
  KEY `FKbb4f67o4lylmfimmfl46j4y08` (`id_image`),
  CONSTRAINT `FKbb4f67o4lylmfimmfl46j4y08` FOREIGN KEY (`id_image`) REFERENCES `media_resource` (`id`),
  CONSTRAINT `FKj52yap2xrm9u0721dct0tjor9` FOREIGN KEY (`chat_room_id`) REFERENCES `chat_room` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=345 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_message`
--

LOCK TABLES `chat_message` WRITE;
/*!40000 ALTER TABLE `chat_message` DISABLE KEYS */;
INSERT INTO `chat_message` VALUES (1,'First message',1,'2023-03-05 11:40:41.603000',1,1,1,NULL),(2,'Hi',1,'2023-03-05 11:48:18.944000',1,1,1,NULL),(3,'What\'sup4',4,'2023-03-05 11:48:27.031000',1,1,1,NULL),(4,'Chao ban',4,'2023-03-05 11:49:51.002000',1,1,3,NULL),(5,'Hello',4,'2023-03-05 12:14:59.856000',1,1,1,NULL),(6,'Xin chao',1,'2023-03-05 12:15:05.346000',1,1,1,NULL),(7,'Wellcome',4,'2023-03-05 12:30:32.323000',1,1,1,NULL),(8,'Hi',3,'2023-03-05 12:30:46.148000',1,1,6,NULL),(9,'Hi',4,'2023-03-05 12:31:04.306000',1,1,1,NULL),(10,'Hello',3,'2023-03-05 12:31:13.469000',1,1,6,NULL),(11,'Hja',4,'2023-03-05 12:31:17.352000',1,1,1,NULL),(12,'Hello',1,'2023-03-05 12:48:18.330000',1,1,6,NULL),(13,'Lo',1,'2023-03-05 12:53:52.069000',1,1,6,NULL),(14,'Mem',1,'2023-03-05 12:54:01.224000',1,1,1,NULL),(15,'Hii',4,'2023-03-05 12:54:36.308000',1,1,1,NULL),(16,'Hl',3,'2023-03-05 12:58:35.493000',1,1,6,NULL),(17,'Hi',3,'2023-03-05 13:00:05.537000',1,1,6,NULL),(18,'He',1,'2023-03-05 13:03:38.194000',1,1,1,NULL),(19,'Hee',1,'2023-03-05 13:04:06.284000',1,1,6,NULL),(20,'What sup',3,'2023-03-05 13:20:29.568000',1,1,6,NULL),(21,'Sub',1,'2023-03-05 13:20:32.725000',1,1,6,NULL),(22,'Xin chao tui la Binh Hoang',1,'2023-03-05 13:24:57.581000',1,1,6,NULL),(23,'Chao ban tui co the giup gi',3,'2023-03-05 13:25:19.209000',1,1,6,NULL),(24,'K co gi bai bai',1,'2023-03-05 13:25:29.005000',1,1,6,NULL),(25,'Hey',4,'2023-03-05 13:26:28.552000',1,1,1,NULL),(26,'Ha what was that',1,'2023-03-05 13:26:35.754000',1,1,1,NULL),(27,'Hey',4,'2023-03-05 14:49:28.899000',1,1,1,NULL),(28,'Chao Ban',3,'2023-03-05 14:50:45.577000',1,1,3,NULL),(29,'Hello.',3,'2023-03-05 16:12:31.597000',1,1,6,NULL),(30,'Hi',1,'2023-03-05 16:14:08.549000',1,1,1,NULL),(31,'Heyy',1,'2023-03-05 16:16:07.055000',1,1,1,NULL),(32,'Hi',1,'2023-03-05 16:18:35.871000',1,1,1,NULL),(33,'Em iu',1,'2023-03-05 16:25:11.776000',1,1,1,NULL),(34,'Hey',1,'2023-03-05 16:26:44.553000',1,1,1,NULL),(35,'Heu',1,'2023-03-05 16:27:42.451000',1,1,1,NULL),(36,'Hello',1,'2023-03-05 16:27:56.098000',1,1,1,NULL),(37,'Diitt',1,'2023-03-05 16:28:37.639000',1,1,1,NULL),(38,'!@',1,'2023-03-05 16:40:02.395000',1,1,1,NULL),(39,'!@',1,'2023-03-05 16:40:17.860000',1,1,1,NULL),(40,'121',1,'2023-03-05 16:40:46.552000',1,1,1,NULL),(41,'213',1,'2023-03-05 16:42:29.333000',1,1,1,NULL),(42,'123',1,'2023-03-05 16:42:51.765000',1,1,1,NULL),(43,'@@',1,'2023-03-05 16:45:43.155000',1,1,1,NULL),(44,'1',1,'2023-03-05 16:48:41.516000',1,1,1,NULL),(45,'Hello',1,'2023-03-05 16:48:43.970000',1,1,1,NULL),(46,'Hello',1,'2023-03-05 16:49:44.557000',1,1,1,NULL),(47,'hello',1,'2023-03-05 16:49:48.788000',1,1,1,NULL),(48,'Em',4,'2023-03-05 16:50:01.635000',1,1,1,NULL),(49,'sao the',4,'2023-03-05 16:50:48.621000',1,1,1,NULL),(50,'K co gi anh',1,'2023-03-05 16:50:53.522000',1,1,1,NULL),(51,'hello cuc cung',4,'2023-03-07 22:40:54.290000',1,1,1,NULL),(52,'22',4,'2023-03-07 22:41:41.562000',1,1,1,NULL),(53,'e iu',4,'2023-03-07 22:43:12.219000',1,1,1,NULL),(54,'hey',4,'2023-03-07 22:46:31.484000',1,1,1,NULL),(55,'sup',4,'2023-03-07 22:47:38.124000',1,1,1,NULL),(56,'Yup',4,'2023-03-07 22:49:27.941000',1,1,1,NULL),(57,'ha',1,'2023-03-07 22:49:33.262000',1,1,1,NULL),(58,'Chafo em',3,'2023-03-07 22:51:21.679000',1,1,6,NULL),(59,'Chao em luon',4,'2023-03-07 22:51:27.110000',1,1,1,NULL),(60,'Yeu 2 anh',1,'2023-03-07 22:51:31.655000',1,1,1,NULL),(61,'sao a',1,'2023-03-07 22:51:37.557000',1,1,6,NULL),(62,'hi',7,'2023-03-07 23:04:24.500000',1,1,8,NULL),(63,'22',7,'2023-03-07 23:05:00.533000',1,1,8,NULL),(64,'ee',3,'2023-03-07 23:05:23.452000',1,1,6,NULL),(65,'2',7,'2023-03-07 23:06:41.574000',1,1,8,NULL),(66,'22',3,'2023-03-07 23:12:03.543000',1,1,6,NULL),(67,'1',3,'2023-03-07 23:12:39.023000',1,1,6,NULL),(68,'1',3,'2023-03-07 23:12:52.145000',1,1,6,NULL),(69,'2121212121212121212121212121212121212121212121212121',3,'2023-03-07 23:14:39.136000',1,1,6,NULL),(70,'Chao em',3,'2023-03-11 13:56:53.332000',1,1,3,NULL),(71,'Alo',4,'2023-03-11 13:56:57.608000',1,1,3,NULL),(72,'Hello',4,'2023-03-11 14:24:32.954000',1,1,1,NULL),(73,'Hey',4,'2023-03-11 14:41:20.065000',1,1,1,NULL),(74,'Hello',4,'2023-03-11 14:42:56.773000',1,1,1,NULL),(75,'hey',4,'2023-03-11 14:44:03.019000',1,1,1,NULL),(76,'alo',4,'2023-03-11 14:44:18.639000',1,1,1,NULL),(77,'eee',4,'2023-03-11 14:45:54.473000',1,1,1,NULL),(78,'@@',4,'2023-03-11 14:46:28.118000',1,1,1,NULL),(79,'Hello',4,'2023-03-11 15:23:52.923000',1,1,1,NULL),(80,'Alo alo',1,'2023-03-11 15:24:03.423000',1,1,1,NULL),(81,'Khoe k',1,'2023-03-11 15:24:08.643000',1,1,1,NULL),(82,'hello',7,'2023-03-11 15:25:08.415000',1,1,8,NULL),(83,'Ai z',1,'2023-03-11 15:25:18.163000',1,1,8,NULL),(84,'Xin chao',1,'2023-03-12 16:09:38.159000',1,1,1,NULL),(85,'Sao ban',4,'2023-03-12 16:09:47.093000',1,1,1,NULL),(86,'22',1,'2023-03-12 16:11:08.930000',1,1,1,NULL),(87,'hello',1,'2023-03-12 16:11:13.057000',1,1,1,NULL),(88,'Hey',1,'2023-03-12 16:12:21.727000',1,1,1,NULL),(89,'sao em',4,'2023-03-12 16:12:26.414000',1,1,1,NULL),(90,'Khong co gi',1,'2023-03-12 16:12:59.916000',1,1,1,NULL),(91,'CHi mon',1,'2023-03-12 16:13:23.354000',1,1,1,NULL),(92,'Chill',1,'2023-03-12 16:16:24.663000',1,1,1,NULL),(93,'sao.',4,'2023-03-12 16:16:30.760000',1,1,1,NULL),(94,'ha',1,'2023-03-12 16:16:33.916000',1,1,1,NULL),(95,'Hello',4,'2023-03-12 16:17:37.138000',1,1,1,NULL),(96,'H2',1,'2023-03-12 16:17:44.404000',1,1,1,NULL),(97,'Hello',4,'2023-03-12 16:21:06.600000',1,1,1,NULL),(98,'On khong ban',4,'2023-03-12 16:33:41.663000',1,1,1,NULL),(99,'Khong on nha',1,'2023-03-12 16:33:48.633000',1,1,1,NULL),(100,'Ok',4,'2023-03-12 16:40:37.035000',1,1,1,NULL),(101,'Ban khoe khong',1,'2023-03-12 16:40:42.462000',1,1,1,NULL),(102,'Khong khoe nha',4,'2023-03-12 16:40:51.770000',1,1,1,NULL),(103,'Tui nef',7,'2023-03-12 16:41:24.125000',1,1,8,NULL),(104,'On k',1,'2023-03-12 16:50:26.213000',1,1,8,NULL),(105,'On k',1,'2023-03-12 16:50:56.764000',1,1,8,NULL),(106,'On nhe',7,'2023-03-12 16:51:44.618000',1,1,8,NULL),(107,'2',1,'2023-03-12 16:52:05.453000',1,1,8,NULL),(108,'Khe k',4,'2023-03-12 16:52:28.415000',1,1,1,NULL),(109,'Khoe nhe',1,'2023-03-12 16:52:36.483000',1,1,1,NULL),(110,'Ok ban',4,'2023-03-12 16:54:36.755000',1,1,1,NULL),(111,'Ok ban luon nhe',1,'2023-03-12 16:54:49.014000',1,1,1,NULL),(112,'Hey',3,'2023-03-15 11:17:34.013000',1,1,6,NULL),(113,'hi',3,'2023-03-15 11:21:01.067000',1,1,6,NULL),(114,'hj',3,'2023-03-15 11:22:31.620000',1,1,6,NULL),(115,'lo',3,'2023-03-15 11:23:39.876000',1,1,6,NULL),(116,'Khoe k',4,'2023-03-15 11:24:22.923000',1,1,1,NULL),(117,'Heslo',7,'2023-03-15 11:28:23.172000',1,1,8,NULL),(118,'what do you mean',3,'2023-03-15 11:28:54.435000',1,1,6,NULL),(119,'Up to yoi',1,'2023-03-15 11:29:10.884000',1,1,6,NULL),(120,'1',1,'2023-03-15 11:29:17.267000',1,1,6,NULL),(121,'Love',1,'2023-03-15 11:29:23.126000',1,1,6,NULL),(124,'Hi',1,'2023-05-02 16:29:17.154000',1,1,6,NULL),(134,'What í up man',3,'2023-05-02 16:29:23.290000',1,1,6,NULL),(144,'Hello',1,'2023-05-13 09:35:47.071000',1,1,6,NULL),(154,'Khoe k',1,'2023-05-13 09:36:01.795000',1,1,6,NULL),(164,'KHoe',3,'2023-05-13 09:36:04.672000',1,1,6,NULL),(174,'Hello anh binh',3,'2023-05-13 10:26:17.986000',1,1,6,NULL),(184,'Hello anh binh',3,'2023-05-13 10:26:30.303000',1,1,6,NULL),(194,'Hello',1,'2023-05-13 10:27:21.611000',1,1,6,NULL),(204,'On khong ban',1,'2023-05-13 10:27:25.322000',1,1,6,NULL),(214,'On chu',3,'2023-05-13 10:27:28.076000',1,1,6,NULL),(224,'Chào cậu',3,'2023-05-15 16:19:54.323000',1,1,6,NULL),(234,'hello',3,'2023-05-15 16:26:10.981000',1,1,6,NULL),(244,'22',1,'2023-05-15 16:28:24.925000',1,1,6,NULL),(254,'Hello banj',3,'2023-05-15 16:28:34.774000',1,1,6,NULL),(264,'view',3,'2023-05-15 16:28:52.950000',1,1,6,NULL),(274,'vwwe',3,'2023-05-15 16:29:13.604000',1,1,6,NULL),(284,'asd',3,'2023-05-15 16:29:21.701000',1,1,6,NULL),(294,'hay vui len',3,'2023-05-15 16:30:06.606000',1,1,6,NULL),(304,'qoek',3,'2023-05-15 16:35:49.249000',1,1,6,NULL),(314,'yoolo',3,'2023-05-15 16:36:17.352000',1,1,6,NULL),(324,'hello man',3,'2023-05-15 16:36:59.753000',1,1,6,NULL),(334,'hello',3,'2023-05-18 17:20:10.771000',1,1,6,NULL),(344,'thay roi ne',1,'2023-05-18 17:20:18.832000',1,1,6,NULL);
/*!40000 ALTER TABLE `chat_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_room`
--

DROP TABLE IF EXISTS `chat_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat_room` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `chat_name` varchar(255) DEFAULT NULL,
  `last_chat` datetime(6) DEFAULT NULL,
  `user1` bigint(20) DEFAULT NULL,
  `user2` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo5odduew799nt0cna4jc99pdf` (`user1`),
  KEY `FKl875m1de684w34ntc9br92rje` (`user2`),
  CONSTRAINT `FKl875m1de684w34ntc9br92rje` FOREIGN KEY (`user2`) REFERENCES `user` (`id`),
  CONSTRAINT `FKo5odduew799nt0cna4jc99pdf` FOREIGN KEY (`user1`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_room`
--

LOCK TABLES `chat_room` WRITE;
/*!40000 ALTER TABLE `chat_room` DISABLE KEYS */;
INSERT INTO `chat_room` VALUES (1,NULL,'2023-03-15 11:24:22.923000',1,4),(3,NULL,'2023-03-11 13:56:57.608000',3,4),(6,NULL,'2023-05-18 17:20:18.832000',1,3),(7,NULL,NULL,1,5),(8,NULL,'2023-03-15 11:28:23.172000',1,7);
/*!40000 ALTER TABLE `chat_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `city` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'TP Hồ Chí Minh'),(2,'Bình Dương'),(3,'Đồng Nai'),(4,'An Giang'),(5,'Bà Rịa – Vũng Tàu'),(6,'Bắc Giang'),(7,'Bắc Kạn'),(8,'Bạc Liêu'),(9,'Bắc Ninh'),(10,'Bến Tre'),(11,'Bình Định'),(12,'Bình Phước'),(13,'Bình Thuận'),(14,'Cà Mau'),(15,'Cần Thơ'),(16,'Cao Bằng '),(17,'Đà Nẵng'),(18,'Đắk Lắk'),(19,'Đắk Nông'),(20,'Điện Biên'),(21,'Đồng Tháp'),(22,'Gia Lai'),(23,'Hà Giang'),(24,'Hà Nam'),(25,'Hà Nội '),(26,'Hà Tĩnh'),(27,'Hải Dương'),(28,'Hải Phòng'),(29,'Hậu Giang'),(30,'Hòa Bình'),(31,'Hưng Yên'),(32,'Khánh Hòa'),(33,'Kiên Giang'),(34,'Kon Tum'),(35,'Lai Châu'),(36,'Lâm Đồng'),(37,'Lạng Sơn'),(38,'Lào Cai'),(39,'Long An'),(40,'Nam Định'),(41,'Nghệ An'),(42,'Ninh Bình'),(43,'Ninh Thuận'),(44,'Phú Thọ'),(45,'Phú Yên'),(46,'Quảng Bình'),(47,'Quảng Nam'),(48,'Quảng Ngãi'),(49,'Quảng Ninh'),(50,'Quảng Trị'),(51,'Sóc Trăng'),(52,'Sơn La'),(53,'Tây Ninh'),(54,'Thái Bình'),(55,'Thái Nguyên'),(56,'Thanh Hóa'),(57,'Thừa Thiên Huế'),(58,'Tiền Giang'),(59,'Trà Vinh'),(60,'Tuyên Quang'),(61,'Vĩnh Long'),(62,'Vĩnh Phúc'),(63,'Yên Bái'),(64,'Toàn quốc'),(65,'Nước ngoài');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cvsubmit`
--

DROP TABLE IF EXISTS `cvsubmit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cvsubmit` (
  `media_id` bigint(20) NOT NULL,
  `post_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `cover_letter` mediumtext,
  `date` datetime(6) DEFAULT NULL,
  `match_percent` bigint(20) DEFAULT NULL,
  `personality` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`media_id`,`post_id`,`user_id`),
  KEY `FKqku9e8075xelp7tm8ilymcu1x` (`post_id`),
  KEY `FKt99c8dc7ivt24v2lef9neah3j` (`media_id`,`user_id`),
  CONSTRAINT `FKqku9e8075xelp7tm8ilymcu1x` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
  CONSTRAINT `FKt99c8dc7ivt24v2lef9neah3j` FOREIGN KEY (`media_id`, `user_id`) REFERENCES `profile` (`media_id`, `user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cvsubmit`
--

LOCK TABLES `cvsubmit` WRITE;
/*!40000 ALTER TABLE `cvsubmit` DISABLE KEYS */;
INSERT INTO `cvsubmit` VALUES (19,1,1,'Painting the world with the colors of my soul. Interpret it as you like - helping others by volunteering, teaching, giving everything you have.  Personal growth - learning as much about the universe...|||6 months ago I met this ENTP guy at my university. We are in the same group, so we basically have to be together everyday, and most likely have to study together for the next 6 years. So cutting off...|||1984 all the way|||I don\'t like people in groups because it\'s harder to enslave them.|||ESxJ or ISxJ, because I\'ve noticed that ISxJs don\'t have much trouble with small talk either.  I\'d say ISTJ.|||secret :)|||I really like the movie, and Alice is my alter ego in internet, since people find my real name a bit weird.|||Sense of smell easily, because my sense of smell has always been kind of weak. I think it\'s better that way, because I don\'t feel and suffer from disgusting smells people seem to feel everyday. I...|||I don\'t think there\'s a certain type who would tend to do this. I think it depends on other factors and influences throughout the person\'s life.|||Honey badgers|||ENFP - creative and challenging conversations. Fun to have both small talk and philosophical rambling.  ENTJ/ENFJ - adventurous friend who allows me to see things from different perspective. ENTP -...|||Please, change my username to A Clockwork Alice|||dodartt|||I\'d say you\'re an ENFP, possibly an ESFP too.|||Completely agree with everything OP said. I am cautious around new people and usually hold my true-self inside. Because of that people assume that I am a very calm, shy, possibly even slow and...|||Less planning, more random writing. Let the words flow out of you. Try to write in start and add the characters later. Basically do the opposite of what you\'ve done before and see what happens.|||My native language is Latvian. English is my second language, but I have become fluent enough in that. (At least I hope so). I know a little bit of Russian and German, but I have no further interest...|||Basically I ask myself questions which affect me. Those ordinary existential ones - what is the meaning of my life, what am I, where am I going, what could happen and what couldn\'t, what changes...|||Blind|||I like learning languages, but I\'m too lazy to do it. For now, I\'m fluent in English and Latvian, but forcing myself to learn Russian. I really want to learn at least one of Scandinavian languages...|||I was definitely an ENFP. Until society broke me.|||Everyday would be sunny, grass would always be green, all people would be smiling for no reason, everyone would go to church on Sundays etc. All of the earth would be just a big suburb full of sensor...|||1) Once 3) 24 h 4) I like to talk like Yoda. Also Doctor Who quotes. (Let\'s go poke it with a stick!) 6) I wanted to be a scientist or a writer. Hasn\'t changed much ever since. 7) Sometimes and I...|||In some way communication is probably one of the things that lead to intelligence. Like, for example, someone asks you a philosophical question. This triggers your mind and urges you to think about...|||Yes, this happens to me all the time. But then again, by telling the truth, you get real friends and get rid of those who can\'t accept you. I think it\'s the best way of living there is.|||I\'m thinking ENTP.|||Not all of them, I bet there are Sensors who have achieved the same thing with hard work.|||Jumping out from the third floor window and breaking a few bones. I was 10 years old and spent 4 h in pain until my parents came home.  That\'s about it.  Now that I think about it, definitely UTI|||I see dead people|||I don\'t have a system. I just write down important stuff in a chaotic way, so I\'m the only person who can make sense out of it later. Also idk what I\'d do without photographic memory, but I\'m not...|||Love both the books and the series.|||OMG SO FREAKING AWESOME. Thank you!|||The Shawshank redemption.|||I\'ve had a similar problem, only I\'m the INTP. For example: A person might have hurt me in the past, and I have forgiven him, but I can\'t stop living in the past, so I bring back the event...|||Can I have one too? :kitteh: If possible, dark green shield, metallic color - grey.|||Something tells me Gatsby is introverted. All those parties he made and never took part in.|||Happens to me very often. When I get into a new class or just meet new people, in start they don\'t like me (and they show it), so I just ignore them, but after few months they usually warm up to me...|||No there aren\'t. There are days when I do everything as scheduled, but then I get bored of it and turn back to my normal self.|||Not knowing the answers to everything - yet.|||If I don\'t know a person very well I\'m too busy with the social interaction and I\'m not comfortable with expressing my humor, because the person probably wouldn\'t understand it. Like once I made a...|||Yes, but mostly with people I\'ve known for years. I think it has definitely something to do with Ne. Sometimes I annoy people because I can already tell what they\'re going to say by their face...|||Procrastinating, trolling people and acting like it wasn\'t me, reading/watching something. Thinking up weird case scenarios, like when driving by bus I imagine how it would crash and people would...|||People have labeled me as \'\'socially retarded\'\', \'\'introverted freak\'\' - I\'m not very communicative, so I\'m comfortable with the labels unless people start to link \'\'introvert\'\' with \'\'ignorant\'\' -...|||I\'m thinking ISFP or ISTP.|||Welcome to the forum! :kitteh:|||Biggest cliché of them all probably is the never-ending fight between good and evil - good always wins.     A socially awkward/miserable guy turns into biggest hero of them all and in the end...|||Welcome, fellow ENFP! :kitteh:|||My guess would be ISTJ/ISFJ, somehow I don\'t see anything from INTJ.|||My Ni-Te is almost as strong as Ti-Ne, so I\'ve had similar problems. Also close people think I\'m extroverted, while others think I\'m introverted. I sense a lot of Ni, so you might be INTJ/INFJ, but...|||Both, but lately more adapting, because I like a good challenge.','2023-05-06 12:22:45.763000',4,'INTP'),(19,2,1,'\'\'That sounds like a beautiful relationship already. But don\'t push too fast as it seems she needs to be appreciated as a friend. But at the end of the day you can only feel what happens when the...|||beautiful :)|||love Xxxxx|||Has anyone been misstyped, maybe as an enfp? I was stuggling trying to find some similarities to enfp as I always got this type when doing internet tests. But using the gognitive functions test I...|||Thanks for all your input, I think I may buy the book. Think I was having a bit of a rant in the end lol|||I barely have the energy to write this but as I do I feel my strength coming back to me. I have been through alot in my life not as much as some and I would really like to get over my low self...|||Has anyone read this book? It was featured breifley in the movie \'bring it on.\'  I would like to talk to you and ask a few questions about men Mostly it is a subject that really bugs me alot, I am...|||What action can be taken against a whole school of bullies? \'Legally obliged to do something\' is word springs to mind when it comes to bullying, but this kind of behaviour is causeing people to...|||I am an ENFP and my mum is an ISFP and we don\'t get along especially when I talk about something she dosen\'t like, she tends to explode at me with mixed results, sometimes i\'ll stand up for myself...|||Meee- ENFP my mum- ISFP (I find we tend to clash alot, is that normal?) my sister-INFJ (we don\'t get along in heated topics but she thinks I am sweet but a bit of a dork) stepdad-ISTP (he thinks I...|||I think some of you out there missed my intention. Be who you want to be, not what others want you to be. I know Some of you may have already given up this thread, but for those who understood my...|||A simple \'thanks\' is not enough.. Thank you, humaning. :)|||But first...  http://www.youtube.com/watch?NR=1&feature=endscreen&v=tM3qBye5FIw and  FUCK YEAH SCREW THEM ALL!! and if I cause any offence it is meant with Sincerity and should not affect your day...|||Oh dear I am afraid I have caused turmoil in what little pool water there is left. I shall leave :(|||I don\'t care if it was misleading I\'m just happy I posted it. :) I may have done some shell removal afterwards. Lets raise our glasses.. I am (insert name here) and I refuse to apologise for being...|||The ever-present danger for  writers is sacrificing truth and intellectual quality (T) for the sake of recognition or popularity (F).  From personality junkie intp  I don\'t care what you are or...|||What makes my friend raania happy is looking after animals. It\'s the same for me as I am doing animal care in college. Today a fully grown doberman sat on her lap, fell asleep and nearly hit a...|||My name is raania and I would also like to post, I am using my friend abitsilly\'s profile. - Someone who is funny, kind, someone who would be able to get along with my friends and family....|||http://www.youtube.com/watch?v=cq_VeUMtyzU|||absolutely I think it would be un-NF-ish not to help another in need.|||If you need advice or someone to talk to come here! Post your questions or dilemma\'s and a panel of experts will get back to you...  I am having a dilemma please go to my recent blog entry and read...|||:unsure: I\'m not sure I can consider myself to be old being the big 24 (not to be confused with the big 25, 26 and 27 etc. etc.) When do you consider yourself to be beyond saving, is it when you...|||creepy exes..oh boy. You could say that, one even stayed outside my house hiding in a neighbors garden till he was caught.  They just don\'t want to let go. Which is something I can\'t understand.|||Someone who\'ll look after my mind body and spirit and do it effortlessly   Someone who really wants to talk to you at the end of the day  Someone who likes books, not t.v  Someone who really...|||I was just listening to bohemian rhapsody by Queen and resonated with it.(not about the part where he killed a man.lol)  People think we are just day dreamers, no hopers, with no direction or...|||OMG you haven\'t read Terry Pratchett\'s Discworld novels? You have not lived, it\'s enfp paradise. Inna book!|||I too have felt the nastiness of parents and the using by friends, families and fiance. It sometimes seems the only option is to be alone. But for us that is no easy option, as we crave the...|||Or rather my restlessness comes from not being able to find my niche.|||I know about this as I have experienced this a lot, I have no definitive solution but when i\'m bored I go out for walks, catch the bus into town, I find that helps alleviate the boredom. My boredom...|||It\'s true in england they use the arts including humanities.|||You are not alone, all of us are on a journey of self discovery. To err is human to forgive divine. I too am in the same boat as you.. I also had alot of childhood trauma to sift through. But I am...|||Has anyone studied religion and theology, how did it go for you?|||Understanding art is also part of the syllabus. :)|||The arts include things like religion and history... :)|||We all have a need to serve humanity with what we do, yes? So is taking an religious education course right for us.. idk Personally it was my best subject in school and I wish to take it up again,...|||abitsilly feels like a horney little succubus http://t3.gstatic.com/images?q=tbn:ANd9GcRJJQ8aiD3wCrZNZQM--Q5TflW8_ealQ0fc3SJIOL9pmL3bNkFVIg|||Kisses slivermoon passionately hands running down her body and exposed slippery breasts finds her way down to her ass and pulls her in closer as she kisses her with a passion close to madness. ...|||takes slivermoon passionately in her arms and brings jackdaw in too, the three of them rubbing bodies slowly bobbing in the pool like some lesbian succubi.|||woops thought you were a guy, maybe I was wrong. I have to find out- her hands start winding their way around jackdaw\'s body. I never knew this could be so pleasurable.|||Fights jackdaw for silvermoon, splashes issue from the pool as she straddles him hands reaching out for his throat.|||Dam you guys are so hot!|||moans, I want silvermoon back on the enfp thread!|||Wriggles out of trousers. And pulls silvermoon in the pool with her. Arggghhh! lol|||Get naked peeps, takes her top off and throwing it away with careless abandon! woop woop *giggles* who wants a smooch* abitsilly is feeling very huggable.|||I love water, especially making love in it. But I wouldn\'t want to dirty up the pool.|||Love Terry Pratchett, and recently i have been reading the celestine prophocey. Anyone else read it?|||Hello I am from england, thought I might interrupt your thoughts by asking you to read my literotica blog. I would love to meet another INFP but they would have to live close to bristol.|||MWahaha my favorite subject. Everyone is entitled to they\'re own opinion... I believe religion is just another system of control a very big one. Richest and most influential system.  It takes the...|||I like people who like me, I dislike people who hate me. I loathe people with no morals-e.g steal your bf/gf etc. etc. People who rub me up the wrong way (you\'d have to be pretty bad to do that)...|||I was the teachers pet always have been. Thus not many friends in fact I can say with some certainty I have never met anyone who has been a true friend of mine. I was always super observant as a...\'\'','2023-05-06 12:24:07.552000',4,'INFJ'),(19,4,1,'\'\'That sounds like a beautiful relationship already. But don\'t push too fast as it seems she needs to be appreciated as a friend. But at the end of the day you can only feel what happens when the...|||beautiful :)|||love Xxxxx|||Has anyone been misstyped, maybe as an enfp? I was stuggling trying to find some similarities to enfp as I always got this type when doing internet tests. But using the gognitive functions test I...|||Thanks for all your input, I think I may buy the book. Think I was having a bit of a rant in the end lol|||I barely have the energy to write this but as I do I feel my strength coming back to me. I have been through alot in my life not as much as some and I would really like to get over my low self...|||Has anyone read this book? It was featured breifley in the movie \'bring it on.\'  I would like to talk to you and ask a few questions about men Mostly it is a subject that really bugs me alot, I am...|||What action can be taken against a whole school of bullies? \'Legally obliged to do something\' is word springs to mind when it comes to bullying, but this kind of behaviour is causeing people to...|||I am an ENFP and my mum is an ISFP and we don\'t get along especially when I talk about something she dosen\'t like, she tends to explode at me with mixed results, sometimes i\'ll stand up for myself...|||Meee- ENFP my mum- ISFP (I find we tend to clash alot, is that normal?) my sister-INFJ (we don\'t get along in heated topics but she thinks I am sweet but a bit of a dork) stepdad-ISTP (he thinks I...|||I think some of you out there missed my intention. Be who you want to be, not what others want you to be. I know Some of you may have already given up this thread, but for those who understood my...|||A simple \'thanks\' is not enough.. Thank you, humaning. :)|||But first...  http://www.youtube.com/watch?NR=1&feature=endscreen&v=tM3qBye5FIw and  FUCK YEAH SCREW THEM ALL!! and if I cause any offence it is meant with Sincerity and should not affect your day...|||Oh dear I am afraid I have caused turmoil in what little pool water there is left. I shall leave :(|||I don\'t care if it was misleading I\'m just happy I posted it. :) I may have done some shell removal afterwards. Lets raise our glasses.. I am (insert name here) and I refuse to apologise for being...|||The ever-present danger for  writers is sacrificing truth and intellectual quality (T) for the sake of recognition or popularity (F).  From personality junkie intp  I don\'t care what you are or...|||What makes my friend raania happy is looking after animals. It\'s the same for me as I am doing animal care in college. Today a fully grown doberman sat on her lap, fell asleep and nearly hit a...|||My name is raania and I would also like to post, I am using my friend abitsilly\'s profile. - Someone who is funny, kind, someone who would be able to get along with my friends and family....|||http://www.youtube.com/watch?v=cq_VeUMtyzU|||absolutely I think it would be un-NF-ish not to help another in need.|||If you need advice or someone to talk to come here! Post your questions or dilemma\'s and a panel of experts will get back to you...  I am having a dilemma please go to my recent blog entry and read...|||:unsure: I\'m not sure I can consider myself to be old being the big 24 (not to be confused with the big 25, 26 and 27 etc. etc.) When do you consider yourself to be beyond saving, is it when you...|||creepy exes..oh boy. You could say that, one even stayed outside my house hiding in a neighbors garden till he was caught.  They just don\'t want to let go. Which is something I can\'t understand.|||Someone who\'ll look after my mind body and spirit and do it effortlessly   Someone who really wants to talk to you at the end of the day  Someone who likes books, not t.v  Someone who really...|||I was just listening to bohemian rhapsody by Queen and resonated with it.(not about the part where he killed a man.lol)  People think we are just day dreamers, no hopers, with no direction or...|||OMG you haven\'t read Terry Pratchett\'s Discworld novels? You have not lived, it\'s enfp paradise. Inna book!|||I too have felt the nastiness of parents and the using by friends, families and fiance. It sometimes seems the only option is to be alone. But for us that is no easy option, as we crave the...|||Or rather my restlessness comes from not being able to find my niche.|||I know about this as I have experienced this a lot, I have no definitive solution but when i\'m bored I go out for walks, catch the bus into town, I find that helps alleviate the boredom. My boredom...|||It\'s true in england they use the arts including humanities.|||You are not alone, all of us are on a journey of self discovery. To err is human to forgive divine. I too am in the same boat as you.. I also had alot of childhood trauma to sift through. But I am...|||Has anyone studied religion and theology, how did it go for you?|||Understanding art is also part of the syllabus. :)|||The arts include things like religion and history... :)|||We all have a need to serve humanity with what we do, yes? So is taking an religious education course right for us.. idk Personally it was my best subject in school and I wish to take it up again,...|||abitsilly feels like a horney little succubus http://t3.gstatic.com/images?q=tbn:ANd9GcRJJQ8aiD3wCrZNZQM--Q5TflW8_ealQ0fc3SJIOL9pmL3bNkFVIg|||Kisses slivermoon passionately hands running down her body and exposed slippery breasts finds her way down to her ass and pulls her in closer as she kisses her with a passion close to madness. ...|||takes slivermoon passionately in her arms and brings jackdaw in too, the three of them rubbing bodies slowly bobbing in the pool like some lesbian succubi.|||woops thought you were a guy, maybe I was wrong. I have to find out- her hands start winding their way around jackdaw\'s body. I never knew this could be so pleasurable.|||Fights jackdaw for silvermoon, splashes issue from the pool as she straddles him hands reaching out for his throat.|||Dam you guys are so hot!|||moans, I want silvermoon back on the enfp thread!|||Wriggles out of trousers. And pulls silvermoon in the pool with her. Arggghhh! lol|||Get naked peeps, takes her top off and throwing it away with careless abandon! woop woop *giggles* who wants a smooch* abitsilly is feeling very huggable.|||I love water, especially making love in it. But I wouldn\'t want to dirty up the pool.|||Love Terry Pratchett, and recently i have been reading the celestine prophocey. Anyone else read it?|||Hello I am from england, thought I might interrupt your thoughts by asking you to read my literotica blog. I would love to meet another INFP but they would have to live close to bristol.|||MWahaha my favorite subject. Everyone is entitled to they\'re own opinion... I believe religion is just another system of control a very big one. Richest and most influential system.  It takes the...|||I like people who like me, I dislike people who hate me. I loathe people with no morals-e.g steal your bf/gf etc. etc. People who rub me up the wrong way (you\'d have to be pretty bad to do that)...|||I was the teachers pet always have been. Thus not many friends in fact I can say with some certainty I have never met anyone who has been a true friend of mine. I was always super observant as a...\'\'','2023-05-06 05:44:54.488000',4,'INFJ');
/*!40000 ALTER TABLE `cvsubmit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cvviewed`
--

DROP TABLE IF EXISTS `cvviewed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cvviewed` (
  `media_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `viewer_id` bigint(20) NOT NULL,
  `date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`media_id`,`user_id`,`viewer_id`),
  KEY `FKprobctjn4mux6h55xa26ti2ap` (`viewer_id`),
  CONSTRAINT `FKn7o0hjdtqrj1sctj4rsk7qavh` FOREIGN KEY (`media_id`, `user_id`) REFERENCES `profile` (`media_id`, `user_id`),
  CONSTRAINT `FKprobctjn4mux6h55xa26ti2ap` FOREIGN KEY (`viewer_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `follow` (
  `post_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`post_id`,`user_id`),
  KEY `FKjby6aprc2rh3sxi3qu46jb22q` (`user_id`),
  CONSTRAINT `FKjby6aprc2rh3sxi3qu46jb22q` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKpbytwogrbn0l2cgqil249c9u4` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES (1,1,'2023-05-13 22:19:27.050000'),(2,4,'2023-01-08 12:08:09.198000'),(4,4,'2023-01-07 12:15:08.189000'),(5,1,'2023-01-07 12:11:45.732000');
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow_user`
--

DROP TABLE IF EXISTS `follow_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `follow_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime(6) DEFAULT NULL,
  `follower_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK5v1c5c5e8o2om43stdn7grt15` (`user_id`,`follower_id`),
  KEY `FKnq61ygj2kbyecwfd2oy7r8di5` (`follower_id`),
  CONSTRAINT `FKglag07bsig33or7njtttnoqy9` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKnq61ygj2kbyecwfd2oy7r8di5` FOREIGN KEY (`follower_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow_user`
--

LOCK TABLES `follow_user` WRITE;
/*!40000 ALTER TABLE `follow_user` DISABLE KEYS */;
INSERT INTO `follow_user` VALUES (6,'2023-01-10 22:34:45.653000',2,3),(7,'2023-01-10 22:34:48.139000',2,5),(8,'2023-01-10 22:47:45.510000',1,3);
/*!40000 ALTER TABLE `follow_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `images`
--

DROP TABLE IF EXISTS `images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `images` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `image_id` bigint(20) DEFAULT NULL,
  `image` longblob,
  PRIMARY KEY (`id`),
  KEY `FKfccua10sh2240kfshte8bkbpt` (`user_id`),
  KEY `FKndlnfal8wmbq0k9k9v2y8minj` (`image_id`),
  CONSTRAINT `FKfccua10sh2240kfshte8bkbpt` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKndlnfal8wmbq0k9k9v2y8minj` FOREIGN KEY (`image_id`) REFERENCES `media_resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `images`
--

LOCK TABLES `images` WRITE;
/*!40000 ALTER TABLE `images` DISABLE KEYS */;
/*!40000 ALTER TABLE `images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `industry`
--

DROP TABLE IF EXISTS `industry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `industry` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `industry`
--

LOCK TABLES `industry` WRITE;
/*!40000 ALTER TABLE `industry` DISABLE KEYS */;
INSERT INTO `industry` VALUES (1,'IT'),(2,'Sales'),(3,'BANKING'),(4,'CHEF'),(5,'TEACHER'),(6,'ACCOUNTANT'),(7,'FINANCE'),(8,'CONSULTANT'),(9,'DIGITAL-MEDIA'),(10,'PUBLIC-RELATIONS'),(11,'BUSINESS-DEVELOPMENT'),(12,'AVIATION'),(14,'CONSTRUCTION'),(15,'ENGINEERING'),(16,'APPAREL'),(22,'HR');
/*!40000 ALTER TABLE `industry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `media_resource`
--

DROP TABLE IF EXISTS `media_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `media_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `public_id` varchar(255) DEFAULT NULL,
  `resource_type` varchar(255) DEFAULT NULL,
  `url` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=265 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media_resource`
--

LOCK TABLES `media_resource` WRITE;
/*!40000 ALTER TABLE `media_resource` DISABLE KEYS */;
INSERT INTO `media_resource` VALUES (6,'pkamcxwnysg4qeccmbxp','image','https://res.cloudinary.com/dh0hs3o2a/image/upload/v1672988934/pkamcxwnysg4qeccmbxp.jpg'),(8,'fxtpukaapclptdy5miof','image','https://res.cloudinary.com/dh0hs3o2a/image/upload/v1672989053/fxtpukaapclptdy5miof.png'),(17,'qvi636rtvl7jlfhjnu5f','image','https://res.cloudinary.com/dh0hs3o2a/image/upload/v1673146190/qvi636rtvl7jlfhjnu5f.pdf'),(18,'kyl5s7lmduoar8sv5f2c','image','https://res.cloudinary.com/dh0hs3o2a/image/upload/v1673146220/kyl5s7lmduoar8sv5f2c.pdf'),(19,'rphkxbmaskwopxim45zk','image','https://res.cloudinary.com/dh0hs3o2a/image/upload/v1673147832/rphkxbmaskwopxim45zk.pdf'),(23,'spa99evbuz1p5nn8rqqb','image','https://res.cloudinary.com/dh0hs3o2a/image/upload/v1673534466/spa99evbuz1p5nn8rqqb.jpg'),(26,'hej5cj1begupj6thmros','image','https://res.cloudinary.com/dh0hs3o2a/image/upload/v1677165642/hej5cj1begupj6thmros.pdf'),(74,'iolgjegkzo5t87xr8uhu','image','https://res.cloudinary.com/dh0hs3o2a/image/upload/v1682658630/iolgjegkzo5t87xr8uhu.png'),(94,'mlmxay2y7ecd5v40wcyy','image','https://res.cloudinary.com/dh0hs3o2a/image/upload/v1682658948/mlmxay2y7ecd5v40wcyy.png'),(104,'eopse7okt960ociemoxe','image','https://res.cloudinary.com/dh0hs3o2a/image/upload/v1682708297/eopse7okt960ociemoxe.png'),(124,'zdr27b7lskxualtebfup','image','https://res.cloudinary.com/dh0hs3o2a/image/upload/v1682911452/zdr27b7lskxualtebfup.png'),(234,'mpffjkvqpibdwtn8oehj','image','https://res.cloudinary.com/dh0hs3o2a/image/upload/v1683042110/mpffjkvqpibdwtn8oehj.pdf'),(254,'llkwx64ziy3xget2zdrc','image','https://res.cloudinary.com/dh0hs3o2a/image/upload/v1683043512/llkwx64ziy3xget2zdrc.pdf'),(264,'tq2ove2d6caojpu3pz4o','image','https://res.cloudinary.com/dh0hs3o2a/image/upload/v1683044646/tq2ove2d6caojpu3pz4o.pdf');
/*!40000 ALTER TABLE `media_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime(6) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn1l10g2mvj4r1qs93k952fshe` (`post_id`),
  KEY `FKb0yvoep4h4k92ipon31wmdf7e` (`user_id`),
  CONSTRAINT `FKb0yvoep4h4k92ipon31wmdf7e` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKn1l10g2mvj4r1qs93k952fshe` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (124,'2023-05-06 12:22:49.922000','Hoang Van Binh has Submitted his/her CV to your post !',1,3),(134,'2023-05-06 12:24:11.588000','Hoang Van Binh has Submitted his/her CV to your post !',2,3);
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `accepted_date` datetime(6) DEFAULT NULL,
  `benifit` mediumtext CHARACTER SET utf8mb4,
  `contact` varchar(255) DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `currency` varchar(30) NOT NULL,
  `description` mediumtext CHARACTER SET utf8mb4,
  `experience` varchar(255) NOT NULL,
  `expiration_date` datetime(6) NOT NULL,
  `gender` int(11) NOT NULL,
  `location` varchar(200) NOT NULL,
  `method` varchar(255) NOT NULL,
  `position` varchar(255) NOT NULL,
  `recruit` bigint(20) DEFAULT NULL,
  `requirement` mediumtext CHARACTER SET utf8mb4,
  `salary` bigint(20) DEFAULT NULL,
  `status` varchar(30) NOT NULL,
  `title` varchar(200) DEFAULT NULL,
  `view_count` int(11) DEFAULT NULL,
  `accepted_by` bigint(20) DEFAULT NULL,
  `author` bigint(20) DEFAULT NULL,
  `city` bigint(20) DEFAULT NULL,
  `industry` bigint(20) DEFAULT NULL,
  `service_id` bigint(20) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,'2023-04-04 21:58:05.083000','Bao hiem suc khoe','MrBinh: 0337445599','2023-01-07 10:56:01.339000','AGREEMENT','Mặc áo vào thứ anh cần là nụ cười của em ?','THREE_YEAR','2023-06-06 00:00:00.000000',0,'Binh An, Di an','FULL_TIME','Manager',15,'Toeic 650+ or Ielts 6.0+',NULL,'ACTIVE','Second title',45,2,3,1,1,3),(2,'2023-01-07 11:01:17.143000','Bao hiem suc khoe','MrBinh: 0337445596','2023-02-07 11:02:28.165000','USD','Mặc áo vào thứ anh cần là nụ cười của em ?','NONE','2023-06-06 00:00:00.000000',0,'Binh An, Di an','FULL_TIME','Staff',5,'Toeic 550+ or Ielts 5.5+',1000,'ACTIVE','First title',40,2,3,1,1,3),(4,'2023-01-07 11:14:08.058000','Bao hiem suc khoe','MrBinh: 0337445596','2023-03-06 16:40:59.598000','USD','Mặc áo vào thứ anh cần là nụ cười của em ?','NONE','2023-06-06 00:00:00.000000',0,'Binh An, Di an','FULL_TIME','Staff',5,'Toeic 550+ or Ielts 5.5+',1000,'ACTIVE','First title',27,2,3,1,3,3),(5,'2023-01-07 11:14:06.631000','Bao hiem suc khoe','MrBinh: 0337445596','2023-03-06 16:42:01.807000','USD','Mặc áo vào thứ anh cần là nụ cười của em ?','NONE','2023-06-06 00:00:00.000000',0,'Binh An, Di an','FULL_TIME','Staff',5,'Toeic 550+ or Ielts 5.5+',1000,'ACTIVE','First title',0,2,3,1,3,3),(8,'2023-04-22 16:20:03.618000','Bao hiem suc khoe','MrBinh: 0337445596','2023-01-06 16:45:40.870000','USD','Mặc áo vào thứ anh cần là nụ cười của em ?','NONE','2023-06-06 00:00:00.000000',0,'Binh An, Di an','FULL_TIME','Staff',5,'Toeic 550+ or Ielts 5.5+',2000,'ACTIVE','First title',0,2,3,1,1,3),(9,'2023-01-14 21:17:34.543000','string','string','2023-01-14 21:02:28.486000','AGREEMENT','string','NONE','2023-06-06 00:00:00.000000',0,'Di An','FULL_TIME','Staff',10,'string',1000,'ACTIVE','string',0,2,3,1,3,3),(10,'2023-01-14 21:17:02.578000','string','string','2023-01-14 21:04:40.412000','AGREEMENT','string','NONE','2023-06-06 00:00:00.000000',0,'Di An','FULL_TIME','Staff',10,'string',1000,'ACTIVE','string',0,2,3,1,1,3),(11,'2023-01-14 21:17:03.895000','string','string','2023-01-14 21:06:51.034000','AGREEMENT','string','NONE','2023-06-06 00:00:00.000000',0,'Di An','FULL_TIME','Staff',10,'string',1000,'ACTIVE','string',0,2,3,1,1,3),(12,'2023-01-14 21:17:05.934000','string','string','2023-01-14 21:07:12.817000','AGREEMENT','string','NONE','2023-06-06 00:00:00.000000',0,'Di An','FULL_TIME','Staff',10,'string',1000,'ACTIVE','string',0,2,3,1,3,3),(14,'2023-01-14 22:40:43.380000','string','string','2023-01-14 21:08:52.529000','AGREEMENT','string','NONE','2023-06-06 00:00:00.000000',0,'Di An','FULL_TIME','Staff',10,'string',1000,'ACTIVE','string',0,2,3,1,1,3),(15,'2023-04-22 16:22:42.946000','string','string','2023-01-14 22:17:01.740000','AGREEMENT','string','NONE','2023-06-06 00:00:00.000000',0,'Di An','FULL_TIME','Staff',10,'string',NULL,'ACTIVE','string',0,2,3,1,3,3),(16,NULL,'string','string','2023-01-14 22:34:02.493000','AGREEMENT','string','NONE','2023-06-06 00:00:00.000000',0,'Di An','FULL_TIME','Staff',10,'string',NULL,'WAIT_FOR_ACCEPT','string',0,NULL,3,1,3,3),(24,NULL,'string','string','2023-04-23 15:38:00.068000','VND','string','NONE','2023-06-23 00:00:00.000000',0,'string','FULL_TIME','Staff',1,'string',1000000,'WAIT_FOR_ACCEPT','string',0,NULL,3,1,1,3),(34,NULL,'string','string','2023-05-15 13:10:55.644000','VND','string','NONE','2023-05-16 00:00:00.000000',0,'string','FULL_TIME','Staff',1,'string',0,'WAIT_FOR_ACCEPT','string',0,NULL,3,1,5,3),(44,NULL,'<p>tttttt</p>','0337445596','2023-05-15 13:45:37.666000','VND','<p>ttttttt</p>','NONE','2023-05-16 00:00:00.000000',2,'Ba Ria','FULL_TIME','Staff',1,'<p>ttttttt</p>',0,'WAIT_FOR_ACCEPT','ttttt',0,NULL,3,1,1,3),(54,NULL,'<p>tttttt</p>','0337445596','2023-05-15 13:46:53.698000','VND','<p>ttttttt</p>','NONE','2023-05-16 00:00:00.000000',2,'Ba Ria','FULL_TIME','Staff',1,'<p>ttttttt</p>',0,'WAIT_FOR_ACCEPT','ttttt',0,NULL,3,1,1,3);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profile` (
  `media_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `experience` varchar(255) DEFAULT NULL,
  `is_public` tinyint(1) NOT NULL DEFAULT '0',
  `last_modified` datetime(6) NOT NULL,
  `method` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `skills_and_knowledges` mediumtext CHARACTER SET utf8mb4,
  `work_experiences` mediumtext CHARACTER SET utf8mb4,
  PRIMARY KEY (`media_id`,`user_id`),
  KEY `FKawh070wpue34wqvytjqr4hj5e` (`user_id`),
  CONSTRAINT `FKawh070wpue34wqvytjqr4hj5e` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKqitv6qsbqikwwp1h8sgku01vb` FOREIGN KEY (`media_id`) REFERENCES `media_resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` VALUES (17,4,'TWO_YEAR',1,'2023-01-08 09:49:49.230000','INTERN','CV Frontend','Leader','Experience\nWork: 2 months internship at FPT software\nKnowledge of Java, C++, C#, JavaScirpt, OOP.\nDatabase: SQL Server, MySQL, MongoDB.\nTools: Git & Github, VSCode, Eclipse\nWeb: Java Spring, Java Servlet, React, NodeJs, Angular','TODO WEBSITE [ 01/11/2022 - 21/11/2022 ] Team member'),(18,4,'NONE',1,'2023-01-08 12:04:41.995000','FULL_TIME','CV Frontend','Staff','Experience\nWork: 2 months internship at FPT software\nKnowledge of Java, C++, C#, JavaScirpt, OOP.\nDatabase: SQL Server, MySQL, MongoDB.\nTools: Git & Github, VSCode, Eclipse\nWeb: Java Spring, Java Servlet, React, NodeJs, Angular','TODO WEBSITE [ 01/11/2022 - 21/11/2022 ] Team member'),(19,1,'NONE',1,'2023-01-08 10:17:13.354000','PART_TIME','CV Backend','Staff','EDUCATION Bachelor of Arts Business Management Texas A&M University Central Texas 2012 - 2016 Killeen, TX SKILLS Negotiation CRM (Salesforce) Problem-solving Lead Generation (LinkedIn, email) Reporting Results-oriented Microsoft Office (Word, Excel, PowerPoint)','Sales Specialist Humana September 2018 - current San Antonio, TX · Created and delivered presentations to decision makers, leading to a 27% improvement over expected lead conversion · Recruited physicians and staff to attend local, regional, and national training programs for Humana products, resulting in $285,000 in new revenue · Supported the evaluation of new products, and provided clinical feedback to marketing and sales · Provided primary clinical training and education to customers, which improved the adoption of new products by 36% Sales Representative TQL April 2016 - September 2018 San Antonio, TX · Executed on outbound calling strategy to warm leads, leading to a close rate of 26%, which exceeded expectations by 50% · Worked closely with existing customers to understand their needs, resulting in $400,000 in retention revenue · Recorded notes in Salesforce to on-board customer service reps and account managers to customer profiles · Maintained up-to-date knowledge of sales strategies and product offerings, leading to $225,000 in up-sell revenue Assistant Manager Family Dollar June 2013 - April 2016 Killeen, TX · Served as point of contact for customer resolution, successfully deescalating 95% of issues without management involvement · Handled merchandise returns, assisted manager with ordering new merchandise, and scheduled store associates to accept deliveries and transfer to stockroom and sales floor · Trained 20+ sales associates in running the POS system, customer service practices, and opening and closing processes · Deposited cash and checks to bank, and helped the store manager maintain accurate records and time cards for payroll'),(26,1,'UNDER_ONE_YEAR',1,'2023-02-23 22:21:49.832000','FULL_TIME','Binh CV','Staff','Experience\nWork: 2 months internship at FPT software\nKnowledge of Java, C++, C#, JavaScirpt, OOP.\nDatabase: SQL Server, MySQL, MongoDB.\nTools: Git & Github, VSCode, Eclipse\nWeb: Java Spring, Java Servlet, React, NodeJs, Angular','Backend EC Website [ 04/07/2022 - 31/08/2022 ] Team leader\nTeam size: 6 members\nDescription: A Web application that helps searching and ordering\nproducts for buyers and posting products for sale with sellers.\nFrameWorks: MySql, Java Spring Boot'),(234,1,'NONE',1,'2023-05-02 23:15:46.980000','FULL_TIME','Demo 1','Staff','Chibaku tensei None NONE None NONE None NONE None NONE None NONE None ','NONE None NONE None NONE None NONE None NONE None NONE None NONE None NONE None NONE None NONE None NONE None NONE None NONE None '),(264,1,'TWO_YEAR',1,'2023-05-02 16:25:28.495000','FULL_TIME','Demo 02','Leader','Đẹp trai vui tính không ai bằng anh hết.','Đẹp trai vui tính không ai bằng anh.');
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime(6) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `handle` tinyint(1) DEFAULT '0',
  `handle_date` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `report_content` varchar(255) DEFAULT NULL,
  `post_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnuqod1y014fp5bmqjeoffcgqy` (`post_id`),
  CONSTRAINT `FKnuqod1y014fp5bmqjeoffcgqy` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` VALUES (4,'2023-05-14 01:58:18.088000','strings@gmail.com',0,NULL,'string','0329548930','string string string string string string string string',1),(14,'2023-05-14 18:46:14.507000','thebest11447@gmail.com',0,NULL,'Hoang Van Binh','0422995300','Nội dung không cụ thể không có gì cung cấp thông tin về công việc',1);
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `service` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` tinyint(1) DEFAULT '1',
  `can_filter_cv_submit` bit(1) DEFAULT NULL,
  `can_search_cv` bit(1) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `currency` varchar(30) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `post_duration` bigint(20) NOT NULL,
  `price` double NOT NULL,
  `type` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (1,1,'','','2023-01-06 15:20:28.387000','USD','Basic service will allow employer to post a job recruitment and allow job seeker to submit their Resume to the post. ','Basic Service',1,10,'BASIC'),(3,1,'','','2023-01-06 15:20:48.540000','USD','Premium service will allow employer to post a job recruitment and allow job seeker to submit their Resume to the post. Beside that employer are able to search for job seeker public resume and filter resume submit to their job recruitment.','Premiun Serivce',2,30,'PREMIUM');
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
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
  `wrong_password_count` bigint(20) DEFAULT NULL,
  `avatar_id` bigint(20) DEFAULT NULL,
  `city_id` bigint(20) DEFAULT NULL,
  `cover_id` bigint(20) DEFAULT NULL,
  `industry_id` bigint(20) DEFAULT NULL,
  `current_service` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  KEY `FK64ydoqa8wkadupx8aci0k4v2h` (`avatar_id`),
  KEY `FK29eqyw0gxw5r4f1ommy11nd9i` (`city_id`),
  KEY `FK7a8x3p8wqax85sr0pvgaw021t` (`cover_id`),
  KEY `FK5b4jwu5ti1o1mtwrhka28wmrp` (`industry_id`),
  KEY `FKiqx6maqf4rf527o7oejjoip3t` (`current_service`),
  CONSTRAINT `FK29eqyw0gxw5r4f1ommy11nd9i` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`),
  CONSTRAINT `FK5b4jwu5ti1o1mtwrhka28wmrp` FOREIGN KEY (`industry_id`) REFERENCES `industry` (`id`),
  CONSTRAINT `FK64ydoqa8wkadupx8aci0k4v2h` FOREIGN KEY (`avatar_id`) REFERENCES `media_resource` (`id`),
  CONSTRAINT `FK7a8x3p8wqax85sr0pvgaw021t` FOREIGN KEY (`cover_id`) REFERENCES `media_resource` (`id`),
  CONSTRAINT `FKiqx6maqf4rf527o7oejjoip3t` FOREIGN KEY (`current_service`) REFERENCES `service` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'','574/45 Lý thường kiệt','5MJK052GZD',NULL,'<p><br></p>','thebest11447@gmail.com','\0','Hoang Van Binh','$2a$10$qvS.CuxO9QQQgVrBhRi4DOugn2634WwMiWN5TDptz3UBEpdO5FLLG','0422995300','ROLE_USER',NULL,0,74,1,94,3,NULL),(2,'',NULL,NULL,NULL,NULL,'binhhv@admin.com','','Binh Admin','$2a$10$JcIWLoWBJZY5JiqbhdpOT.DfdVY6ncSHg/s13cV8bLxird6jPGOF.',NULL,'ROLE_ADMIN',NULL,0,NULL,NULL,NULL,NULL,NULL),(3,'','Ba Ria',NULL,'2023-01-07 00:00:00.000000','<p>Phiên bản V583 - Lễ Hội Giáng Sinh sẽ được cập nhật vào ngày 22/12/2022 với các tính năng được mở rộng cùng trang bị mới với sức mạnh chiến đấu lớn. Nhanh tay tham gia cùng Võ Lâm Chi Mộng trải nghiệm các hoạt động mới.</p><p>Có tuyển intern</p>','19110170@student.hcmute.edu.vn','','Binh Company','$2a$10$YiXzKiBd1sYJdfiv8VLMPOgRh2nud9W20j6EgSpSdJT.rsBCQNtty','0337445596','ROLE_EMPLOYER','2025-01-07 00:00:00.000000',0,NULL,1,NULL,1,3),(4,'',NULL,NULL,NULL,NULL,'symanh@gmail.com','\0','Thieu Sy Manh','$2a$10$qr2JNAH5jeicebzdE4xbku4BDxpAQEkBt1nICWp.wJgjLAWxZaWwC',NULL,'ROLE_USER',NULL,0,NULL,NULL,NULL,NULL,NULL),(5,'','Ba Ria',NULL,'2023-02-07 00:00:00.000000','','19110171@student.hcmute.edu.vn','','Nam Company','$2a$10$YiXzKiBd1sYJdfiv8VLMPOgRh2nud9W20j6EgSpSdJT.rsBCQNtty','0337445597','ROLE_EMPLOYER','2023-03-18 00:00:00.000000',0,NULL,2,NULL,2,3),(7,'','Ba Ria',NULL,'2023-02-07 00:00:00.000000','','19110172@student.hcmute.edu.vn','','Tung Company','$2a$10$YiXzKiBd1sYJdfiv8VLMPOgRh2nud9W20j6EgSpSdJT.rsBCQNtty','0337445598','ROLE_EMPLOYER','2023-03-18 00:00:00.000000',0,NULL,2,NULL,2,3),(14,'','Ho Chi Minh City',NULL,'2023-04-22 09:56:08.078000',NULL,'employer1@gmail.com','','Employer 1','$2a$10$74lrQPGL63oVHyJBKeH6keMJzB8m4ySIXgGi6.tF34UOKNnwUtnFS','0120120120','ROLE_EMPLOYER','2024-10-07 00:00:00.000000',0,NULL,1,NULL,1,3),(24,'',NULL,'PEE45OSSEN','2023-04-22 19:43:48.388000',NULL,'quangssoss@gmail.com','','Quang Tran','4ASYE4TD8PFQ9R40N5D4',NULL,'ROLE_USER',NULL,0,NULL,NULL,NULL,NULL,NULL),(34,'',NULL,'MC8BVGR2WW','2023-04-23 18:04:40.583000',NULL,'fzay001@gmail.com','','Trần Quang','9YIJ7W33YJDJWKY9ALFO',NULL,'ROLE_USER',NULL,0,NULL,NULL,NULL,NULL,NULL),(44,'',NULL,NULL,'2023-04-23 20:48:58.497000',NULL,'quangsss@gmail.com','\0','Quang Tran','$2a$10$4eoB3hS7tmRujlc3WpX/KeHQnv/u8jAdO8FJAhJmeMzxSuSaaEPPG',NULL,'ROLE_USER',NULL,0,NULL,NULL,NULL,NULL,NULL),(54,'',NULL,NULL,'2023-04-23 20:49:49.067000',NULL,'quangsoss@gmail.com','\0','Quang Tran','$2a$10$e5pQpnBpO8zXLt3cDKxoCuVgxEFhXt5eKiqr1raGBXUb4IqWQxH8.',NULL,'ROLE_USER',NULL,0,NULL,NULL,NULL,NULL,NULL),(64,'',NULL,NULL,'2023-04-23 20:54:17.591000',NULL,'quanoss@gmail.com','\0','Quang Tran','$2a$10$6EM7fBAMl5MjTycHRzRnCugJKde1/3VWR3ivWv68cPrMJHZwb4k7W',NULL,'ROLE_USER',NULL,0,NULL,NULL,NULL,NULL,NULL),(84,'','Ha noi ',NULL,'2023-04-23 21:55:44.244000',NULL,'usdgsgser@example.com','\0','string','$2a$10$PJxhcbnZzCgJWXAkSrTmvu4EjGPiUYMrSLMoCwc7llrzo0VChEZLW','0387564732','ROLE_EMPLOYER',NULL,0,NULL,1,NULL,1,NULL),(94,'',NULL,NULL,'2023-04-23 22:39:19.584000',NULL,'quangs111soss@gmail.com','\0','Quang Tran','$2a$10$Clko8xISkM72FlA0An.4C.4NNiFLoKzvZv.p4Z8V43nyDMcLFjVPO',NULL,'ROLE_USER',NULL,0,NULL,NULL,NULL,NULL,NULL),(104,'',NULL,NULL,'2023-04-23 22:43:48.496000',NULL,'quangs22soss@gmail.com','\0','Quang Tran','$2a$10$urxmYTa0tnqeXKjrRhOjyOjeAMSM5m17G.H5zvT1UmvywvQYAx2VW',NULL,'ROLE_USER',NULL,0,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_order`
--

DROP TABLE IF EXISTS `user_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `currency` varchar(30) NOT NULL,
  `duration` int(11) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `paid_date` datetime(6) DEFAULT NULL,
  `payment_url` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `status` varchar(30) NOT NULL,
  `total` double NOT NULL,
  `service_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc0udsp041ygmlpgo354elsw0n` (`service_id`),
  KEY `FKj86u1x7csa8yd68ql2y1ibrou` (`user_id`),
  CONSTRAINT `FKc0udsp041ygmlpgo354elsw0n` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`),
  CONSTRAINT `FKj86u1x7csa8yd68ql2y1ibrou` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_order`
--

LOCK TABLES `user_order` WRITE;
/*!40000 ALTER TABLE `user_order` DISABLE KEYS */;
INSERT INTO `user_order` VALUES (24,'2023-02-08 22:26:53.217000','USD',2,'Update service form 2024-08-07 00:00:00.0 to Mon Oct 07 00:00:00 ICT 2024 .','2023-02-08 22:27:19.677000','https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=EC-5RC218284M751240Y',30,'PAID',60,3,3),(25,'2023-02-18 10:05:49.820000','USD',1,'Rent new service for 1 month(s).','2023-02-18 10:07:12.965000','https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=EC-5NV52562W3335100M',30,'PAID',30,3,5),(34,'2023-04-22 10:37:52.005000','USD',1,'Update service form 2024-10-07 00:00:00.0 to Thu Nov 07 00:00:00 UTC 2024 .','2023-04-22 03:38:30.520000','https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=EC-7UG73732FT059680K',30,'PAID',30,3,3),(44,'2023-04-22 16:50:44.421000','USD',1,'Update service form 2024-11-07 00:00:00.0 to Sat Dec 07 00:00:00 UTC 2024 .','2023-04-22 16:51:08.091000','https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=EC-1R585861YN887703T',30,'PAID',30,3,3),(54,'2023-04-23 15:38:54.878000','USD',1,'Update service form 2024-12-07 00:00:00.0 to Tue Jan 07 00:00:00 UTC 2025 .','2023-04-23 08:40:08.051000','https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=EC-9TG31082H7206735S',30,'PAID',30,3,3),(64,'2023-05-08 20:21:47.604000','USD',1,NULL,NULL,'https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=EC-1T019692X97366825',30,'WAIT_FOR_PAYMENT',30,3,3);
/*!40000 ALTER TABLE `user_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `view_page`
--

DROP TABLE IF EXISTS `view_page`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `view_page` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime(6) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `viewer_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjmfpmhbyyqioqgm7fpwp0k6wk` (`user_id`),
  KEY `FKmmbkugjryqkg496vmevrbb5wo` (`viewer_id`),
  CONSTRAINT `FKjmfpmhbyyqioqgm7fpwp0k6wk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKmmbkugjryqkg496vmevrbb5wo` FOREIGN KEY (`viewer_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `view_page`
--

LOCK TABLES `view_page` WRITE;
/*!40000 ALTER TABLE `view_page` DISABLE KEYS */;
INSERT INTO `view_page` VALUES (3,'2023-01-08 11:55:03.258000',3,1),(4,'2023-01-08 12:03:22.842000',3,4),(5,'2023-01-08 12:03:42.351000',5,4),(6,'2023-01-08 12:07:16.361000',5,4),(7,'2023-02-08 12:03:22.842000',3,4),(8,'2023-03-08 12:03:22.842000',3,4),(9,'2023-03-23 21:51:06.507000',3,1),(10,'2023-03-23 21:51:06.507000',5,1),(11,'2023-03-23 21:51:06.507000',5,1),(12,'2023-03-23 21:51:06.507000',5,1),(13,'2023-03-23 21:51:06.507000',5,1),(14,'2023-05-18 11:00:56.374000',5,1);
/*!40000 ALTER TABLE `view_page` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `view_post`
--

DROP TABLE IF EXISTS `view_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `view_post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime(6) NOT NULL,
  `post_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnulxnnivljptifc2dmviu0uc4` (`post_id`),
  CONSTRAINT `FKnulxnnivljptifc2dmviu0uc4` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=995 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `view_post`
--

LOCK TABLES `view_post` WRITE;
/*!40000 ALTER TABLE `view_post` DISABLE KEYS */;
INSERT INTO `view_post` VALUES (1,'2023-02-20 00:00:00.000000',1),(2,'2023-02-20 00:00:00.000000',1),(3,'2023-02-20 00:00:00.000000',1),(4,'2023-02-27 00:00:00.000000',1),(5,'2023-02-27 00:00:00.000000',1),(6,'2023-02-27 00:00:00.000000',1),(7,'2023-02-27 00:00:00.000000',2),(8,'2023-02-20 00:00:00.000000',2),(9,'2023-02-20 00:00:00.000000',2),(10,'2023-02-20 00:00:00.000000',2),(11,'2023-02-20 00:00:00.000000',2),(12,'2023-02-20 00:00:00.000000',2),(13,'2023-02-20 00:00:00.000000',2),(14,'2023-02-28 21:41:39.604000',2),(24,'2023-04-26 01:45:08.018000',1),(34,'2023-05-13 21:44:16.509000',1),(44,'2023-05-14 07:31:01.686000',1),(54,'2023-05-14 07:31:02.576000',1),(64,'2023-05-14 07:31:10.889000',1),(74,'2023-05-14 07:33:15.498000',1),(84,'2023-05-14 07:34:23.471000',1),(94,'2023-05-14 07:36:16.103000',2),(104,'2023-05-14 07:36:16.989000',2),(114,'2023-05-14 07:36:35.658000',1),(124,'2023-05-14 07:36:36.474000',1),(134,'2023-05-14 07:37:55.779000',4),(144,'2023-05-14 07:38:01.293000',4),(154,'2023-05-14 07:38:02.126000',4),(164,'2023-05-14 08:09:36.335000',4),(174,'2023-05-14 08:09:36.893000',4),(184,'2023-05-14 08:11:31.075000',4),(194,'2023-05-14 08:20:51.046000',4),(204,'2023-05-14 08:20:51.338000',4),(214,'2023-05-14 08:23:18.618000',4),(224,'2023-05-14 08:23:19.478000',4),(234,'2023-05-14 08:23:20.241000',4),(244,'2023-05-14 08:23:20.832000',4),(254,'2023-05-14 08:24:34.012000',4),(264,'2023-05-14 08:24:35.663000',4),(274,'2023-05-14 08:24:36.290000',4),(284,'2023-05-14 08:24:36.913000',4),(294,'2023-05-14 08:25:48.144000',4),(304,'2023-05-14 08:25:48.958000',4),(314,'2023-05-14 08:25:49.573000',4),(324,'2023-05-14 08:25:50.052000',4),(334,'2023-05-14 08:29:38.656000',4),(344,'2023-05-14 08:31:46.965000',4),(354,'2023-05-14 08:32:07.309000',4),(364,'2023-05-14 08:58:12.087000',4),(374,'2023-05-14 08:58:13.446000',4),(384,'2023-05-14 08:58:14.034000',4),(394,'2023-05-14 08:58:14.584000',4),(404,'2023-05-14 18:26:58.105000',1),(414,'2023-05-17 14:42:51.661000',1),(424,'2023-05-17 14:42:52.018000',1),(434,'2023-05-17 14:42:52.324000',1),(444,'2023-05-17 14:43:05.363000',1),(454,'2023-05-17 14:43:05.671000',1),(464,'2023-05-17 14:43:05.975000',1),(474,'2023-05-17 22:24:34.248000',1),(484,'2023-05-17 22:24:34.601000',1),(494,'2023-05-17 22:24:34.909000',1),(504,'2023-05-18 13:42:33.299000',1),(514,'2023-05-18 14:09:27.509000',1),(524,'2023-05-18 14:09:27.876000',1),(534,'2023-05-18 14:22:56.281000',1),(544,'2023-05-18 14:22:56.640000',1),(554,'2023-05-18 14:25:58.462000',1),(564,'2023-05-18 14:25:58.782000',1),(574,'2023-05-18 14:27:50.405000',1),(584,'2023-05-18 14:28:49.796000',1),(594,'2023-05-18 14:30:34.483000',1),(604,'2023-05-18 14:32:46.850000',1),(614,'2023-05-18 14:32:59.159000',1),(624,'2023-05-18 14:34:54.038000',1),(634,'2023-05-18 14:35:24.315000',1),(644,'2023-05-18 18:51:48.612000',2),(654,'2023-05-18 18:51:48.963000',2),(664,'2023-05-18 18:52:15.322000',2),(674,'2023-05-18 18:52:15.642000',2),(684,'2023-05-18 18:52:46.156000',2),(694,'2023-05-18 18:54:04.715000',2),(704,'2023-05-18 18:56:28.734000',2),(714,'2023-05-18 18:56:29.047000',2),(724,'2023-05-18 19:03:35.243000',2),(734,'2023-05-18 19:03:35.556000',2),(744,'2023-05-18 19:08:01.142000',2),(754,'2023-05-18 19:14:55.831000',2),(764,'2023-05-18 19:14:56.139000',2),(774,'2023-05-18 19:16:21.875000',2),(784,'2023-05-18 19:17:07.084000',2),(794,'2023-05-18 19:17:51.895000',2),(804,'2023-05-18 19:18:06.522000',2),(814,'2023-05-18 19:19:18.344000',2),(824,'2023-05-18 19:23:29.209000',2),(834,'2023-05-18 19:26:09.724000',2),(844,'2023-05-18 19:33:00.125000',2),(854,'2023-05-18 19:37:56.205000',2),(864,'2023-05-18 19:37:56.505000',2),(874,'2023-05-18 19:39:05.112000',2),(884,'2023-05-18 19:39:55.243000',2),(894,'2023-05-18 19:45:03.817000',2),(904,'2023-05-18 19:45:04.144000',2),(914,'2023-05-18 19:45:29.912000',2),(924,'2023-05-18 19:47:40.407000',2),(934,'2023-05-18 19:47:59.585000',2),(944,'2023-05-18 21:45:00.991000',1),(954,'2023-05-18 21:45:01.352000',1),(964,'2023-05-18 21:45:01.685000',1),(974,'2023-05-18 21:45:13.252000',1),(984,'2023-05-18 21:45:13.554000',1),(994,'2023-05-18 21:46:22.027000',1);
/*!40000 ALTER TABLE `view_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'heroku_46500e17176aaa5'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-21  2:17:14
