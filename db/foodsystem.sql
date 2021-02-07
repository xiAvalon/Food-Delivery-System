-- MySQL dump 10.13  Distrib 8.0.23, for osx10.16 (x86_64)
--
-- Host: localhost    Database: foodsystem
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Current Database: `foodsystem`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `foodsystem` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `foodsystem`;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `ID` bigint NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `CUSTOMER_NAME` varchar(30) NOT NULL,
  `CUSTOMER_ADDRESS` varchar(255) NOT NULL,
  `phoneNumber` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (23124645656,'karimsayed','22','Karim Sayed','Bandar Sunway, ER-1Y, Jalan PJS ','112585654','1@g.co'),(60174643475,'zouabi','11','Alzouabi Mohammad','Selangor, Cyberjaya, Hostel A-3-12','125894948','2@b.co'),(73564464526,'habib','55','Ammar Habib','Selangor, Cyberjaya, Mutiara Ville, J--2-7','138498494','3@h.co'),(73635174574,'ana','33','Moira Ana','Bandar Puteri, House Town, Port Klang','154648984','4@p.co'),(83612312331,'mohd','44','Mohammad Mohammad','Selangor, Cyberjaya, The Arc D-3-12','165489498','5@b.co'),(83612312332,'112','112','ammar habib','malaysia','11','a@gmail.com'),(83612312333,'moh','aaaa','mohammad mohammad','asda','1111','sdad'),(83612312334,'mo','mo','mo mo','mo','11','mo');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail` (
  `ID` int NOT NULL,
  `ORDER_NUMBER` int NOT NULL,
  `PRODUCT_ID` char(5) NOT NULL,
  `cancelled` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ORDER_NUMBER` (`ORDER_NUMBER`),
  KEY `PRODUCT_ID` (`PRODUCT_ID`),
  CONSTRAINT `order_detail_ibfk_1` FOREIGN KEY (`ORDER_NUMBER`) REFERENCES `orders` (`ID`),
  CONSTRAINT `order_detail_ibfk_2` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `product` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (1,3,'F1003','0'),(2,1,'F1002','1'),(3,5,'F1005','0'),(4,1,'F1001','1'),(5,2,'F1002','0');
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `ID` int NOT NULL,
  `ORDER_TOTALPRICE` decimal(5,2) NOT NULL,
  `CUSTOMER_NUMBER` bigint NOT NULL,
  `RESTAURANT_NUMBER` bigint NOT NULL,
  `RIDER_NUMBER` bigint NOT NULL,
  `delivered` tinyint NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `CUSTOMER_NUMBER _idx` (`CUSTOMER_NUMBER`),
  KEY `RESTAURANT_NUMBER_idx` (`RESTAURANT_NUMBER`),
  KEY `RIDER_NUMBER_idx` (`RIDER_NUMBER`),
  CONSTRAINT `CUSTOMER_NUMBER ` FOREIGN KEY (`CUSTOMER_NUMBER`) REFERENCES `customer` (`ID`),
  CONSTRAINT `RESTAURANT_NUMBER` FOREIGN KEY (`RESTAURANT_NUMBER`) REFERENCES `restaurant` (`ID`),
  CONSTRAINT `RIDER_NUMBER` FOREIGN KEY (`RIDER_NUMBER`) REFERENCES `rider` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,0.00,83612312331,83612347931,3453453123,0),(2,0.00,73564464526,73564565426,3453453124,0),(3,0.00,60174643475,60174834275,3453453125,0),(4,0.00,23124645656,23124151356,3453453126,0),(5,0.00,73635174574,73635163874,3453453128,0);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `ID` char(5) NOT NULL,
  `ORDER_NUMBER` int NOT NULL,
  `amount` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ORDER_NUMBER` (`ORDER_NUMBER`),
  CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`ORDER_NUMBER`) REFERENCES `orders` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES ('P0001',1,'14.0'),('P0002',2,'15.0'),('P0003',3,'50.0'),('P0004',4,'16.0'),('P0005',5,'40.0');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `ID` char(5) NOT NULL,
  `PRODUCT_NAME` varchar(30) NOT NULL,
  `PRODUCT_PRICE` decimal(5,2) NOT NULL,
  `RESTAURANT_ID` bigint NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `product_ibfk_1_idx` (`RESTAURANT_ID`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`RESTAURANT_ID`) REFERENCES `restaurant` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('F1001','Madhbi Lamb',19.99,83612347931),('F1002','Haneeth Lamb',19.99,60174834275),('F1003','Birysni Lamb',14.99,73635163874),('F1004','Madhbi Chicken',14.99,60174834275),('F1005','Haneeth Chicken',14.99,73635163874),('F1006','Birysni Chicken',10.99,83612347931),('F1007','Nasi Goreng',7.99,23124151356),('F1008','Nasi Lemak',7.99,23124151356),('F2001','Mountain Dew',2.25,83612347931),('F2002','Pepsi',2.25,23124151356),('F2003','Orange Juice',8.00,83612347931),('F2004','Mango Juice',8.00,23124151356),('F2005','Chocolate Waffle',19.00,73635163874),('F2006','Waffle Nuts & CHocolate',24.00,23124151356);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant`
--

DROP TABLE IF EXISTS `restaurant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `restaurant` (
  `ID` bigint NOT NULL,
  `RESTAURANT_NAME` varchar(30) NOT NULL,
  `RESTAURANT_ADDRESS` varchar(255) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `phoneNumber` bigint NOT NULL,
  `open` tinyint NOT NULL,
  `minimum_order` decimal(4,2) NOT NULL,
  `deliveryTime` int NOT NULL,
  `deliveryFee` decimal(4,0) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant`
--

LOCK TABLES `restaurant` WRITE;
/*!40000 ALTER TABLE `restaurant` DISABLE KEYS */;
INSERT INTO `restaurant` VALUES (23124151356,'Shwarma Rewstaurant','Bandar Sunway, OB-1C, Jalan PJS ','shaw','1111',1564984984,1,15.00,45,2),(60174834275,'Tais Restaurant','Selangor, Cyberjaya, Domain-A2, 512','tais','894',1549848584,1,20.00,30,0),(73564565426,'Rozvan Restaurant','Selangor, Cyberjaya, Dpulze Shopping Center','roz','84987',1564897897,1,10.00,60,1),(73635163874,'Borla Restaurant','Bandar Puteri, 41200, Port Klang','borla','874',1564984874,0,15.15,60,0),(83612347931,'Taiba Restaurant','Selangor, Cyberjaya, No 7G, Cottage Walk, 123','taiba','84',1498977214,0,15.00,60,0),(83612347932,'Qasr b','malaysia','Al','112',11,0,15.00,45,1),(83612347933,'mo wq','qw','pp','123',123,0,9.00,45,1),(83612347934,'ww ww','ww','ww','ww',11,0,1.00,1,1);
/*!40000 ALTER TABLE `restaurant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rider`
--

DROP TABLE IF EXISTS `rider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rider` (
  `ID` bigint NOT NULL,
  `RIDER_NAME` varchar(30) NOT NULL,
  `VEHICLE_PLATE` char(7) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `phoneNumber` bigint NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rider`
--

LOCK TABLES `rider` WRITE;
/*!40000 ALTER TABLE `rider` DISABLE KEYS */;
INSERT INTO `rider` VALUES (3453453123,'Mohammad Komar','AR35343','mohdkom','4894',11654984),(3453453124,'Omar Husan','ES32123','omarh','asfd',16549844),(3453453125,'Abo Jwad','QT34323','abuj','asfwee',14989498),(3453453126,'Sofian Ahmad','TR62443','sofi','asdwet',14198488),(3453453127,'Hassan Deep','UR94213','hassanD','aser',15649848),(3453453128,'Moner Shahada','HF12453','moneer','qwet',12654984),(3453453129,'aa aaa','a12','a','112',11),(3453453130,'ddd dds','211','mo','123',231),(3453453131,'qq qq','12','qq','qq',11);
/*!40000 ALTER TABLE `rider` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-07 12:58:46
