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
-- Table structure for table `reference`
--

DROP TABLE IF EXISTS `reference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reference` (
  `id_reference` int(11) NOT NULL AUTO_INCREMENT,
  `full_ref` longtext NOT NULL,
  `cut_ref` varchar(22) NOT NULL,
  `description` mediumtext,
  `count` varchar(100) DEFAULT '0',
  `tag` varchar(80) DEFAULT NULL,
  `qrcode` longblob,
  `id_users` int(11) DEFAULT NULL,
  `idU` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_reference`),
  UNIQUE KEY `cut_ref_UNIQUE` (`cut_ref`),
  KEY `id_users_idx` (`id_users`),
  CONSTRAINT `id_users` FOREIGN KEY (`id_users`) REFERENCES `users` (`id_users`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=336 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reference`
--

LOCK TABLES `reference` WRITE;
/*!40000 ALTER TABLE `reference` DISABLE KEYS */;
INSERT INTO `reference` VALUES (256,'http://devcolibri.com/1250','localhost:81/by/Pf3JCG','devcolibri','28','dev','‰PNG\r\n\Z\n\0\0\0\rIHDR\0\0\0d\0\0\0d\0\0\0ÿ€\0\0„IDATx\Ú\í\ÚQ’\Ã \0\Ð\Üÿ\Ò\í\Ç^@)fŸv\Ú\è\ë\Ä \äùˆ\åxÀ‚,X`Á‚\ëc=¹Xùå­«w\Î¬iX{wø\Â\ÚbU\ÃaÁšµrÿ¯Œ\Ä>\Ú\ZÙš!,X¯Çªú,X¿\Â\êœ!,Xó±ª¾µu¸-\\6,X÷b%«E\r#\ï)þÁ‚u¨;:-W\íP—µ\Â`Á\ÚÀ7ô{\Z:I°`ÅŠ%\Ü\Égrb\'®\Ö¬­\É%\Ò\r|°`]„Kbüž\Ý\ç7õ,X°§\ÉþJyr\"•‡kV\ìzU5\è­#Á\Ä¬Š\Ô!9Rµ\r%i`Áº+–\rT\íYU›× <¬\Ò=«êµƒ\Øjcû,X7bUE•c²¥t\Ù+G°`%Ë¼\Ë+Î°`½+YóªZRg\r\Z¬±X\çN\ÔU%*X°`¶gb«­zu	¬—aU„“…¶pJ\Ö(¬s\Å\èdo)¶cÂ‚uVg\ç&9Ÿd\æ\Ö4,,X°`	X°`Á‚Kü\Å\ãJ7ž5\0\0\0\0IEND®B`‚',78,80),(272,'https://stepic.org/users/2054363/learn','localhost:81/by/osWU3T','','2','stepic','‰PNG\r\n\Z\n\0\0\0\rIHDR\0\0\0d\0\0\0d\0\0\0ÿ€\0\0IDATx\Ú\í\Ú;\Â0À\ÜÿÒ¡ ¤‰­\ç_X—0	ö2Qd\É\×m<X°`Á‚,X°`ý1\ÖUO\î\Ü4™3„k7¬¶\'üÁ\Úúnø{y|†°`\íõ\äùoZR1À¥f\Öë±ŠIÀ\ÌÂ‚«þ,X¯\ÇJ]Õ´¤?\Ý\îÀ‚­Mø\ä=\Å?X°uGj\ïþb„:¬VôŸ\ê÷\Ì\ì$Á‚µVÊ±ø\îO5~–¥°`%°&\Ì;•4…WX°\Â\ê›\\<©/{`=¬{R\Ìj)î–‹?\ZL\åaÁ\Úk\\«X\ÏJU\Ê`Á\Ú«kú‚E\ê\Åúsð°`­\ÛHO¨:õý³ó,X°Ç¬bºŸ*™õ¥û°`„5.•wThD	¬…Xñ“\ÅX\ï\ãÂ‚uV<B+\×ñ,XaO\Z\îq±¬\×cw\æ\Å\Ø7û´2,X÷\Ö1+:®5\ÖþX©btñ°{_–?»\Ö\Þõ¬qÝx\Û	¬µX,X°`Á‚eÀ‚,X\Æw|\0\nd/J „\0\0\0\0IEND®B`‚',80,NULL),(288,'http://expo.hh.ru/it/?utm_source=smm&utm_medium=vkontakte&utm_content=hh_post&utm_campaign=soisk_post_cot_proger_2801','localhost:81/by/1WPfT8','','1','','‰PNG\r\n\Z\n\0\0\0\rIHDR\0\0\0d\0\0\0d\0\0\0ÿ€\0\0}IDATx\Ú\í\Ú\Ùƒ0@úo:i¤\çÖŸd\0{™Qd\É\×Ï¸=.°`Á‚X°`Áú0\Ö\Õwž\Ü]Æ°Â‚µVm\Í\ÞYd|†°`\íU[@œ/>CX°^õ\è§Gù°`Á\Zu\Ç¬/c¥\îª¦omw`ÁŠV‹&\\yOñ¬AÝ‘\'Cj\×}j+¬Fa7\Õï©½bh\'	¬…Xµ•\Äw\æÍ‰Õ¦\n\Ö&Xµ÷\Å7\ÒK¾+,X›`¥Œ\âE«¦,,Xa5Sðq	Gü9°`m‹Õ¬15; ñ\ÃL³SX°\Ædðö\ÆñòôF\ç\àaÁ\Z\\ÏŠgµ/´Q\ê\0\Öô˜•:8®“Ö‰X©\Ñ<\\»¶\ÖZ¬øÉ¿\Ô]rX°ÞÕŒP©Bó\Ì\Z4,X\Ûb5\ãH¼.\\\Ë\àaÁú Vj\Þ5‘Ù§•aÁúm³š¡*\Ïð°`\Í\Ê\à›•\ëf#¬ƒ°Rõ¬™¤e<,X[vwN°`Á‚–,X°`-$\ë•M|x\0\0\0\0IEND®B`‚',80,NULL),(290,'http://forum.grodno.net/index.php?board=55.0','localhost:81/by/x1N4mo','','1','','‰PNG\r\n\Z\n\0\0\0\rIHDR\0\0\0d\0\0\0d\0\0\0ÿ€\0\0ˆIDATx\Ú\í\ÚA’„ P\ï\éžÅ¬gJ’\"ýXv\n\ÏC\Âõ\Ñn·,X°`Á‚…\0,X°¾\ë\êµ;W®\rcÁaÁ\Ú\rk\ì\rÿ»WÍ±yÁ~/X°¶ÂºóþÏ›\íÐ½†F\ÖñXC5,X°f`\r]¬\ã±R½†¦œ6,X\ï\Åjf‹ürNò¬IÕ‘Ò·?µ†¾¬Vô_«÷<[I‚k7¬f² \\#º,X/\Â\Z\Z\\*ù»r„°`m‹5”®\å|¯>\Ï$ÿ`ÁŠ†ó\Ò\ÊÍ­õ\ÔP¬=±j¥\Ðf\Ä\ÐL~­\Þ\îÀ‚5\'th\ÖD›\ËG\ê\Ð,X/\Âz6^OÕ„`Áš¼f¥*@5£\Ô1AX°ö\ÇJµø`Þ½`Á\Ú\r+~ò/\Õ=¾ªÂ‚µ?Vj…ª=˜T˜\ÖXñS\ïñ,X°>¹S\ïÍ´Zð0,X`5³N\ÍE°ù„`Á\Ú\r+Nœ\n\îk+&,X/\ÂJ%¤\æm¶›\Ç\raÁ\ÚKƒ,X°4X°`Á‚Kûm?\ÊO€o\á\Í\0\0\0\0IEND®B`‚',80,NULL);
/*!40000 ALTER TABLE `reference` ENABLE KEYS */;
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
