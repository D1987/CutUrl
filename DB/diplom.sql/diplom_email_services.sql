-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: diplom
-- ------------------------------------------------------
-- Server version	5.7.9-log

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
-- Table structure for table `email_services`
--

DROP TABLE IF EXISTS `email_services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `email_services` (
  `domain` char(24) NOT NULL,
  `name` char(32) NOT NULL,
  `url` char(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email_services`
--

LOCK TABLES `email_services` WRITE;
/*!40000 ALTER TABLE `email_services` DISABLE KEYS */;
INSERT INTO `email_services` VALUES ('inbox.ru','Почта Mail.Ru (inbox.ru)','https://e.mail.ru/\r'),('yandex.ru','Яндекс.Почта','https://mail.yandex.ru/\r'),('ya.ru','Яндекс.Почта','https://mail.yandex.ru/\r'),('yandex.by','Яндекс.Почта','https://mail.yandex.by/\r'),('yandex.com','Yandex.Mail','https://mail.yandex.com/\r'),('gmail.com','Gmail','https://mail.google.com/\r'),('googlemail.com','Gmail','https://mail.google.com/\r'),('outlook.com','Outlook.com','https://mail.live.com/\r'),('hotmail.com','Outlook.com (Hotmail)','https://mail.live.com/\r'),('live.ru','Outlook.com (live.ru)','https://mail.live.com/\r'),('live.com','Outlook.com (live.com)','https://mail.live.com/\r'),('me.com','iCloud Mail','https://www.icloud.com/\r'),('icloud.com','iCloud Mail','https://www.icloud.com/\r'),('rambler.ru','Рамблер-Почта','https://mail.rambler.ru/\r'),('yahoo.com','Yahoo! Mail','https://mail.yahoo.com/\r'),('tut.by','Почта tut.by','https://mail.tut.by/'),('mail.ru','Почта Mail.Ru','https://e.mail.ru/');
/*!40000 ALTER TABLE `email_services` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-04 12:12:23
