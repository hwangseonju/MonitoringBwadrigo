-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: k6s104.p.ssafy.io    Database: test
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `apply`
--

DROP TABLE IF EXISTS `apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apply` (
  `apply_id` int NOT NULL AUTO_INCREMENT,
  `apply_create_date` datetime(6) DEFAULT NULL,
  `apply_bedding_count` int DEFAULT NULL,
  `apply_change` int DEFAULT NULL,
  `apply_cleaning_count` int DEFAULT NULL,
  `apply_date` datetime(6) DEFAULT NULL,
  `apply_delivery_count` int DEFAULT NULL,
  `apply_shirt_count` int DEFAULT NULL,
  `apply_update_date` datetime(6) DEFAULT NULL,
  `apply_wash_count` int DEFAULT NULL,
  `member_id` int DEFAULT NULL,
  `month_plan_id` int DEFAULT NULL,
  PRIMARY KEY (`apply_id`),
  UNIQUE KEY `UK_pojic59wuqwgt8i7ns5ooo7qy` (`member_id`),
  KEY `FKh3q670muti4w0vltwl6hlex5e` (`month_plan_id`),
  CONSTRAINT `FKd0c5b59aerf7c0fe6vbmdvwyh` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `FKh3q670muti4w0vltwl6hlex5e` FOREIGN KEY (`month_plan_id`) REFERENCES `month_plan` (`month_plan_id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apply`
--

LOCK TABLES `apply` WRITE;
/*!40000 ALTER TABLE `apply` DISABLE KEYS */;
INSERT INTO `apply` VALUES (27,NULL,0,3,3,'2022-05-09 05:58:28.696042',3,20,NULL,3,1,2),(31,NULL,0,NULL,12,'2022-05-11 14:28:32.716331',2,0,NULL,0,44,4),(39,NULL,0,NULL,3,'2022-05-17 02:41:53.716946',3,20,NULL,3,82,2),(42,NULL,0,4,3,'2022-05-18 12:12:42.915393',3,20,NULL,3,43,2),(43,NULL,0,NULL,3,'2022-05-18 15:41:43.360002',3,0,NULL,3,84,6),(44,NULL,0,NULL,3,'2022-05-18 15:51:26.732091',3,0,NULL,3,9,5);
/*!40000 ALTER TABLE `apply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `collect`
--

DROP TABLE IF EXISTS `collect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `collect` (
  `collect_id` bigint NOT NULL AUTO_INCREMENT,
  `collect_create_date` datetime(6) DEFAULT NULL,
  `collect_request_date` datetime(6) NOT NULL,
  `collect_type` varchar(255) NOT NULL,
  `collect_update_date` datetime(6) DEFAULT NULL,
  `collect_withdraw_date` datetime(6) DEFAULT NULL,
  `employee_id` int DEFAULT NULL,
  `member_id` int DEFAULT NULL,
  PRIMARY KEY (`collect_id`),
  KEY `FK23e5j2c9uu76q5u6odfncdts9` (`employee_id`),
  KEY `FKmovoilw4q0vgc68v3pw1s5h3a` (`member_id`),
  CONSTRAINT `FK23e5j2c9uu76q5u6odfncdts9` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`),
  CONSTRAINT `FKmovoilw4q0vgc68v3pw1s5h3a` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collect`
--

LOCK TABLES `collect` WRITE;
/*!40000 ALTER TABLE `collect` DISABLE KEYS */;
INSERT INTO `collect` VALUES (1,'2022-05-04 00:08:32.000000','2022-05-03 00:08:32.000000','wash','2022-05-04 00:08:32.000000',NULL,1,9),(2,'2022-05-04 00:09:06.000000','2022-05-03 00:09:06.000000','cleaning','2022-05-04 00:09:06.000000',NULL,1,9),(3,'2022-05-04 00:09:12.000000','2022-05-03 00:09:12.000000','shirt','2022-05-04 00:09:12.000000',NULL,1,9),(6,NULL,'2022-05-06 08:26:01.747107','bedding',NULL,'2022-05-06 12:09:20.899804',NULL,9),(7,NULL,'2022-05-06 08:26:01.747107','shirts',NULL,'2022-05-06 12:10:43.714362',NULL,9),(8,'2022-05-06 00:17:35.000000','2022-05-05 00:17:35.000000','shirt','2022-05-06 00:17:35.000000',NULL,1,9),(9,NULL,'2022-05-06 12:11:42.915074','wash',NULL,'2022-05-06 12:12:12.433456',NULL,9),(10,NULL,'2022-05-06 12:11:42.915074','bedding',NULL,'2022-05-06 12:15:23.810121',NULL,9),(11,NULL,'2022-05-06 12:11:42.915074','shirts',NULL,'2022-05-06 12:15:23.897102',NULL,9),(12,NULL,'2022-05-06 12:11:42.915074','cleaning',NULL,'2022-05-06 12:15:24.284102',NULL,9),(13,NULL,'2022-05-06 12:15:42.431904','bedding',NULL,'2022-05-06 12:46:37.560270',NULL,9),(14,NULL,'2022-05-06 12:15:42.431904','wash',NULL,'2022-05-06 12:46:37.644941',NULL,9),(15,NULL,'2022-05-06 12:15:42.431904','shirts',NULL,'2022-05-06 12:46:37.718406',NULL,9),(16,NULL,'2022-05-06 12:15:42.431904','cleaning',NULL,'2022-05-06 12:46:38.099630',NULL,9),(17,NULL,'2022-05-06 12:47:31.656317','wash',NULL,'2022-05-06 13:05:44.364467',NULL,9),(18,NULL,'2022-05-06 12:47:31.656317','bedding',NULL,NULL,1,9),(19,NULL,'2022-05-06 12:47:31.656317','shirts',NULL,NULL,1,9),(20,NULL,'2022-05-09 05:59:44.070164','bedding',NULL,NULL,NULL,1),(21,NULL,'2022-05-09 05:59:47.160830','bedding',NULL,NULL,NULL,1),(22,NULL,'2022-05-09 07:15:20.039338','wash',NULL,'2022-05-09 07:17:09.696893',NULL,9),(23,NULL,'2022-05-09 07:17:16.527201','wash',NULL,'2022-05-09 07:43:06.253961',NULL,9),(24,NULL,'2022-05-09 07:43:37.852212','wash',NULL,'2022-05-09 07:43:59.180514',NULL,9),(25,NULL,'2022-05-09 07:44:05.330013','wash',NULL,NULL,1,9),(26,NULL,'2022-05-09 15:47:19.889674','wash',NULL,'2022-05-09 15:47:33.948901',NULL,9),(27,NULL,'2022-05-11 14:34:02.316829','bedding',NULL,NULL,NULL,44),(28,NULL,'2022-05-11 14:34:02.316849','wash',NULL,NULL,NULL,44),(29,NULL,'2022-05-11 14:35:01.379899','shirts',NULL,NULL,NULL,44),(30,NULL,'2022-05-11 14:35:01.379910','bedding',NULL,NULL,NULL,44),(31,NULL,'2022-05-11 14:41:24.299828','wash',NULL,'2022-05-12 06:24:22.372596',NULL,9),(32,NULL,'2022-05-11 14:41:24.299828','bedding',NULL,'2022-05-12 06:24:22.785950',NULL,9),(33,NULL,'2022-05-12 06:24:29.363414','wash',NULL,'2022-05-16 11:28:54.435248',NULL,9),(34,NULL,'2022-05-12 06:24:29.363414','shirts',NULL,'2022-05-16 11:28:54.534457',NULL,9),(35,NULL,'2022-05-16 12:38:25.152662','bedding',NULL,'2022-05-16 12:38:40.989942',NULL,9),(36,NULL,'2022-05-16 12:38:25.152662','cleaning',NULL,'2022-05-16 12:38:41.232125',NULL,9),(37,NULL,'2022-05-16 14:53:08.302690','wash',NULL,'2022-05-17 16:06:22.962391',NULL,9),(38,NULL,'2022-05-16 14:53:08.302701','shirts',NULL,'2022-05-17 16:06:23.044799',NULL,9),(39,NULL,'2022-05-17 02:42:17.278031','wash',NULL,NULL,NULL,82),(40,NULL,'2022-05-17 02:42:17.278044','bedding',NULL,'2022-05-17 02:42:51.677884',NULL,82),(41,NULL,'2022-05-17 16:06:27.022307','wash',NULL,'2022-05-17 16:06:31.039920',NULL,9),(42,NULL,'2022-05-17 16:06:27.022320','shirts',NULL,'2022-05-17 16:06:33.951166',NULL,9),(43,NULL,'2022-05-18 03:42:04.417735','bedding',NULL,'2022-05-18 11:01:06.685069',NULL,83),(44,NULL,'2022-05-18 03:42:04.417748','shirts',NULL,'2022-05-18 11:01:08.787818',NULL,83),(45,NULL,'2022-05-18 11:03:51.010388','wash',NULL,'2022-05-18 14:13:52.395879',NULL,7),(46,NULL,'2022-05-18 11:03:51.010397','bedding',NULL,'2022-05-18 14:13:52.650891',NULL,7),(47,NULL,'2022-05-18 11:03:51.010399','shirts',NULL,'2022-05-18 14:13:52.784083',NULL,7),(48,NULL,'2022-05-18 11:03:51.010402','cleaning',NULL,'2022-05-18 14:13:53.109218',NULL,7),(49,NULL,'2022-05-18 11:37:22.540432','wash',NULL,'2022-05-18 14:13:52.040945',NULL,7),(50,NULL,'2022-05-18 11:37:22.540440','shirts',NULL,'2022-05-18 14:13:52.298686',NULL,7),(51,NULL,'2022-05-18 13:37:44.634395','wash',NULL,'2022-05-18 14:13:52.432465',NULL,7),(52,NULL,'2022-05-18 13:37:44.634404','bedding',NULL,'2022-05-18 14:13:52.754639',NULL,7),(53,NULL,'2022-05-18 13:37:44.634406','shirts',NULL,'2022-05-18 14:13:53.004855',NULL,7),(54,NULL,'2022-05-18 14:24:56.566941','bedding',NULL,'2022-05-18 14:25:48.326806',NULL,7),(55,NULL,'2022-05-18 14:24:56.566950','shirts',NULL,'2022-05-18 14:25:48.375130',NULL,7),(56,NULL,'2022-05-18 14:24:56.566952','cleaning',NULL,'2022-05-18 14:25:48.743992',NULL,7),(57,NULL,'2022-05-18 14:26:38.625245','wash',NULL,'2022-05-18 14:26:52.340760',NULL,7),(58,NULL,'2022-05-18 14:26:38.625254','bedding',NULL,'2022-05-18 14:26:52.391062',NULL,7),(59,NULL,'2022-05-18 14:26:38.625256','shirts',NULL,'2022-05-18 14:26:52.743573',NULL,7),(60,NULL,'2022-05-18 14:26:59.263133','wash',NULL,'2022-05-18 14:36:51.039540',NULL,7),(61,NULL,'2022-05-18 14:26:59.263142','bedding',NULL,'2022-05-18 14:36:51.132673',NULL,7),(62,NULL,'2022-05-18 14:26:59.263145','shirts',NULL,'2022-05-18 14:36:51.497840',NULL,7),(63,NULL,'2022-05-18 14:37:11.369730','wash',NULL,'2022-05-18 14:49:06.332709',NULL,7),(64,NULL,'2022-05-18 14:37:11.369749','bedding',NULL,'2022-05-18 14:49:06.692291',NULL,7),(65,NULL,'2022-05-18 14:37:11.369752','shirts',NULL,'2022-05-18 14:49:06.953519',NULL,7),(66,NULL,'2022-05-18 14:37:11.369754','cleaning',NULL,'2022-05-18 14:49:07.052596',NULL,7);
/*!40000 ALTER TABLE `collect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `employee_id` int NOT NULL AUTO_INCREMENT,
  `employee_create_date` datetime(6) DEFAULT NULL,
  `employee_name` varchar(30) NOT NULL,
  `employee_phone` varchar(30) NOT NULL,
  `employee_update_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'2022-05-04 00:04:49.000000','ssafy','010-1234-5678','2022-05-04 00:04:49.000000'),(2,'2022-05-06 00:17:35.000000','ssafy','010-1234-5678','2022-05-06 00:17:35.000000');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `laundry_plan`
--

DROP TABLE IF EXISTS `laundry_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `laundry_plan` (
  `laundry_plan_id` int NOT NULL AUTO_INCREMENT,
  `laundry_plan_create_date` datetime(6) DEFAULT NULL,
  `laundry_plan_description` varchar(1000) NOT NULL,
  `laundry_plan_details` varchar(50) NOT NULL,
  `laundry_plan_price` int NOT NULL,
  `laundry_plan_type_eng` varchar(20) NOT NULL,
  `laundry_plan_type_kor` varchar(20) NOT NULL,
  `laundry_plan_update_date` datetime(6) DEFAULT NULL,
  `laundry_create_date` datetime(6) DEFAULT NULL,
  `laundry_update_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`laundry_plan_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `laundry_plan`
--

LOCK TABLES `laundry_plan` WRITE;
/*!40000 ALTER TABLE `laundry_plan` DISABLE KEYS */;
INSERT INTO `laundry_plan` VALUES (1,'2022-04-28 21:38:18.000000','생활빨래는 건조 어후 무게를 측정하여 무게 단위로 과금되며, 기본 금액 이후 초과하는 만큼 추가 과금됩니다.별도의 검수 없이 기계 새약 및 열품 건조 후, 다림질 없이 개어 보내드립니다. 열풍 건조로 인해 소재에 따라 수축이 발생할 수 있으며, 고객 부주의로 인한 손상 시 보상이 불가하모니 물세탁 또는 열풍 건조가 불가능한 의류는 개별 클리닝으로 맡겨주셔야 합니다.','생활빨래3kg까지',8000,'wash','생활빨래',NULL,NULL,NULL),(2,'2022-04-28 21:38:20.000000','3kg 기준에서 0.5kg 단위로 추가 과금됩니다.','0.5kg 초과',1000,'wash','생활빨래',NULL,NULL,NULL),(3,'2022-04-28 21:38:21.000000','일반셔츠로 구분되지 않는 남성 정장 셔츠 와이셔츠 예외브랜드 안내 (일반셔츠로 구분 됩니다) 타임, 띠어리, 솔리드움므, 시스템, 준지, 프라다. 랑방, 아크마니, 보스, 마르니, 버버리, 챔브라운, 로로피아니, 에르메네질도 제냐, 꼼데가르송. 지방시, 톰포드, 디올, 구찌, 입생로랑/생로랑, 발렌시아가, 까날리, 닐바렛, 마크제이콥스, 지방시, 에르메스, 베르사체, 메종마르지엘라, 우영미, 니나리치, 오프화이트, 페라가모, 폴스미스, 들체앤가바나, 아미, 몽클레어, 아페쎄(AP.C), 산드로, 팬디,브루넬로 쿠치넬리 세탁 요청사항에 팔주름 다림질 요청 시 일반셔츠로 과금','와이셔츠',1800,'shirt','와이셔츠',NULL,NULL,NULL),(4,'2022-04-28 21:38:22.000000','민소매, 짧은팔, 긴팔의 모든 일반 티셔츠 소재, 조리복 상의','티셔츠',4000,'cleaning','개별클리닝',NULL,NULL,NULL),(5,'2022-04-28 21:38:23.000000','청/모직/실크/마/레이온 함유량이 1% 이상인 서츠\n자개단추, 장식위 (자개단추, 와펜 등) 부착된 셔츠','일반셔츠',2800,'cleaning','개별클리닝',NULL,NULL,NULL),(6,'2022-04-28 21:38:25.000000','디자인과 형태가 기성 셔츠 상태에서 벗어나는 경우\n단추 위치가 왼쪽인 경우, 허리절개선, 레어스, 프함, 디자인특이 소매\n길이, 앞뒤 길이 다른 정우\n실크소재 셔츠류는 블라우스로 과금','블라우스',4500,'cleaning','개별클리닝',NULL,NULL,NULL),(7,'2022-04-28 21:38:26.000000','','원피스, 점프수트',6000,'cleaning','개별클리닝',NULL,NULL,NULL),(8,'2022-04-28 21:38:28.000000','안감에 타월직, 기모, 융, 네오프렌 소재로 되어 있는 형태','후드티, 맨투맨티',4500,'cleaning','개별클리닝',NULL,NULL,NULL),(9,'2022-04-28 21:38:30.000000','짜임이 있는 조직이 있는 의류','니트, 스웨터',5000,'cleaning','개별클리닝',NULL,NULL,NULL),(10,'2022-04-28 21:38:31.000000','지퍼, 단추 등이 있는 오픈형의 디자인','가디건',6000,'cleaning','개별클리닝',NULL,NULL,NULL),(11,'2022-04-28 21:38:34.000000','','인조가죽, 인조세무상의',6500,'cleaning','개별클리닝',NULL,NULL,NULL),(12,'2022-04-28 21:38:46.000000','정장, 교복, 교복 니트 조끼','정장조끼',3000,'cleaning','개별클리닝',NULL,NULL,NULL),(13,'2022-04-28 21:38:48.000000','후리스,니트 등 정장조끼, 패딩조끼를 제외한 모든 조끼','캐주얼 조끼',5000,'cleaning','개별클리닝',NULL,NULL,NULL),(14,'2022-04-28 21:38:49.000000','개량한복, 아동한복 조끼','한복 조끼',3000,'cleaning','개별클리닝',NULL,NULL,NULL),(15,'2022-04-28 21:38:50.000000','개령한복, 아동한복 상의','한복 상의',6000,'cleaning','개별클리닝',NULL,NULL,NULL),(16,'2022-04-28 21:38:52.000000','','스키복, 보드복 상의',15000,'cleaning','개별클리닝',NULL,NULL,NULL),(17,'2022-04-28 21:38:53.000000','정장하의를 제외한 먼바지, 청바지, 골덴바지, 마바지 등 모든 바지류','바지',4500,'cleaning','개별클리닝',NULL,NULL,NULL),(18,'2022-04-28 21:38:55.000000','수트로 나오는 디자인의 정장하의\n여성 하의의 경우 핏이나 소재 등의 어유로 세탁 과정이 차이가 나므로\n바지품목으로 분류입니다.\n제외소재 및 품목 안내 (바지로 분류 됩니다)\n- 면,실크,마: 50% 이상\n- 캐시미어 100%  \n- 벨벳, 코듀로이','정장바지',4000,'cleaning','개별클리닝',NULL,NULL,NULL),(19,'2022-04-28 21:38:57.000000','','스커트',4500,'cleaning','개별클리닝',NULL,NULL,NULL),(20,'2022-04-28 21:38:59.000000','','패딩바지',9000,'cleaning','개별클리닝',NULL,NULL,NULL),(21,'2022-04-28 21:39:00.000000','','인조가죽, 인조세무상의',8500,'cleaning','개별클리닝',NULL,NULL,NULL),(22,'2022-04-28 21:39:02.000000','','스키복, 보드복 하의',10000,'cleaning','개별클리닝',NULL,NULL,NULL),(23,'2022-04-28 21:39:03.000000','개량한복, 아동한복 하의, 한복 속치마','한복 하의',7000,'cleaning','개별클리닝',NULL,NULL,NULL),(24,'2022-04-28 21:39:04.000000','야상, 청재킷, 항공점퍼, 집업','점퍼',6500,'cleaning','개별클리닝',NULL,NULL,NULL),(25,'2022-04-28 21:39:04.000000','정장재킷을 제외한 모든 재킷류, 린넨 재킷, 실크 재킷\n면,실크, 마,별벳,린넨,코듀로이,캐시미어(100%) 소재의 남성 정장상의','재킷',7500,'cleaning','개별클리닝',NULL,NULL,NULL),(26,'2022-04-28 21:39:09.000000','수트로 나오는 디자인의 정장상의\n여성 상의의 경우 허리 절개선등의 이유로 세탁 과정이 차이가 나므로\n재킷 품목으로 분류됩니다.\n제외소재 및 품목 안내 (재킷으로 분류됩니다)\n-면,실크,마: 50% 이상\n-캐시미어: 100%\n-벨벳, 코듀로이','정장재킷',4500,'cleaning','개별클리닝',NULL,NULL,NULL),(27,'2022-04-28 21:39:11.000000','경량, 일반패딩','경량, 일반패딩',10000,'cleaning','개별클리닝',NULL,NULL,NULL),(28,'2022-04-28 21:39:12.000000','','다운패딩',15000,'cleaning','개별클리닝',NULL,NULL,NULL),(29,'2022-04-28 21:39:14.000000','기장이 100cm 어상인 경우','롱패딩점퍼',18000,'cleaning','개별클리닝',NULL,NULL,NULL),(30,'2022-04-28 21:39:16.000000','[프리미엄 패딩 브랜드 안내]\n캐나다구스, 몽클레어, 무스너클, 스톤아일랜드, 버버리, 아틱베이, 맥\n케이지, 이브살로몬, 에르노, 페트레이, 나이젤카본, 미스터앤미세스퍼\n이상, 무레르, 파라점퍼스,울리치,몬테꼬레,C.P컴퍼니,프로젝트포체,\n노비스,CMFR,Ten c 등','프리미엄패딩',25000,'cleaning','개별클리닝',NULL,NULL,NULL),(31,'2022-04-28 21:39:22.000000','','패딩조끼',7000,'cleaning','개별클리닝',NULL,NULL,NULL),(32,'2022-04-28 21:39:19.000000','민소매코트, 목사가운, 성당가운 포함','코트, 트렌치코트',12000,'cleaning','개별클리닝',NULL,NULL,NULL),(33,'2022-04-28 21:39:20.000000','캐시미어, 알파카, 라마, 양모 소재 혼용을 30% 이상','캐시미어,알파카,라마,양모 코트',18000,'cleaning','개별클리닝',NULL,NULL,NULL),(34,'2022-04-28 21:39:21.000000','[프리미엄 코트 브랜드 안내! 프라다, 버버리, 보스, 펜디, 페라가모, 에르메스,발렌시아가,베르사체, 셀린느, 사넬,구찌, 알렉산더왕,막스마라,발렌티노,이자벨 마랑, 아크네스튜디오,크리스찬디올,지방시,생로랑,아르마니.돌체앤가바나,로샤스, 루이비통,쟈딕앤볼테르,로로피아나,랑방,마크제이콥스,마르니,겐조,비비안웨스트우드 등','프리미엄코트',22000,'cleaning','개별클리닝',NULL,NULL,NULL),(35,'2022-04-28 21:39:24.000000','해당 소재가 50% 이상으로 포함된 아우티','인조가죽, 인조세무, 인조모피, 인조무스탕',15000,'cleaning','개별클리닝',NULL,NULL,NULL),(36,'2022-04-28 21:39:25.000000','의류 일부에 모피, 가죽이 있는 경우 카라 부터 밑단까지의 부분 모피, 내피 앞부 모피 등','부분 천연 모피/가죽 코트, 점퍼',20000,'cleaning','개별클리닝',NULL,NULL,NULL),(37,'2022-04-28 21:39:27.000000','등산용, 비람막이, 골프용 들 기능성 소재가 포함된 의류','기능성의류',9000,'cleaning','개별클리닝',NULL,NULL,NULL),(38,'2022-04-28 21:39:29.000000','침대 커버, 매트리스 커버, 이불커버, 홑이불, 이불패드 이불과 커버를 분리하여 세탁하므로 별도 과금됩니다.','커버류, 이불패드',9000,'bedding','이불',NULL,NULL,NULL),(39,'2022-04-28 21:39:30.000000','홑이볼, 일반 이불, 극세사 패드 이불과 커버를 분리하여 세탁하므로 별도 과금됩니다.','일반 이불, 극세사 패드',12000,'bedding','이불',NULL,NULL,NULL),(40,'2022-04-28 21:39:32.000000','극세사 패드를 재외한 극세사 이불류, 일반솜 토퍼 이불과 커버를 분리하며 세탁하므로 별도 과금됩니다.','극세사 이불, 일반 토퍼',18000,'bedding','이불',NULL,NULL,NULL),(41,'2022-04-28 21:39:32.000000','이불과 커버를 분리하여 세탁하므로 별도 과금됩니다.','구스이불, 양모이불, 실크이불',23000,'bedding','이불',NULL,NULL,NULL),(42,'2022-04-28 21:39:34.000000','','구스토퍼',30000,'bedding','이불',NULL,NULL,NULL),(43,'2022-04-28 21:39:35.000000','','구스 베개',12000,'bedding','이불',NULL,NULL,NULL),(44,'2022-04-28 21:39:36.000000','','아동이불, 무릎 담요',7000,'bedding','이불',NULL,NULL,NULL),(45,'2022-04-28 21:39:38.000000','수거 배송 횟수를 모두 사용하시는 경우 추가 이용하는 만큼 다음 결제일에 배송비가 추가 결제 됩니다.','배송비',3500,'delivery','배송비',NULL,NULL,NULL);
/*!40000 ALTER TABLE `laundry_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `member_id` int NOT NULL AUTO_INCREMENT,
  `member_address` varchar(100) DEFAULT NULL,
  `member_age` int DEFAULT NULL,
  `member_create_date` datetime(6) DEFAULT NULL,
  `member_email` varchar(30) NOT NULL,
  `member_gender` bit(1) DEFAULT NULL,
  `member_name` varchar(30) NOT NULL,
  `member_password` varchar(100) NOT NULL,
  `member_phone` varchar(30) NOT NULL,
  `member_status` varchar(255) DEFAULT NULL,
  `member_update_date` datetime(6) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`member_id`),
  UNIQUE KEY `UK_3orqjaukiw2b73e2gw8rer4rq` (`member_email`),
  UNIQUE KEY `UK_ogs2lh992eo5eh0lwsdk7w9uu` (`member_phone`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'강남구 역삼동 2',10,NULL,'test@test.com',_binary '','테스터','$2a$10$G73sjEAPvJGQHuzKQnmgyOgpMqgjGT/ZOc2dMk/iK3ezihv3wz5fm','010-2234-5678','ACTIVATE',NULL,'ROLE_USER'),(3,'광주',10,'2022-04-30 20:17:39.723004','test2@test.com',_binary '','테스터','$2a$10$2EUowU9eZU0SNE1dDcFxBew1.4M9Re8TKId5hLtkrkqvGdsVtR8Fu','010-3234-5678','ACTIVATE','2022-04-30 20:17:39.783004','ROLE_USER'),(5,'광주',10,'2022-05-03 16:51:04.815563','test1@test.com',_binary '','테스터','$2a$10$hqMWjS99TN7JXxAr6QzQuuEfcuB4jWg.rTkmD.2h50DPPSjS7LpMm','010-1233-1334','ACTIVATE','2022-05-03 16:51:04.894562','ROLE_USER'),(7,'동탄순환대로12길85',10,'2022-05-04 02:14:46.004573','hi@hi.com',_binary '','하이요','$2a$10$fI.mSTsmIwkT9uloRGDCc.ogCur2NEg/6PdgzHSMoAFbL96JeLy76','010-4321-4321','ACTIVATE','2022-05-04 02:14:46.106024','ROLE_USER'),(9,'광주광역시 북구',0,NULL,'abc@abc.com',_binary '\0','abc','$2a$10$yDAt7.YFapaOSw7JI4ldjO8lSdP7KFDMYDsIja1lzIoNz./fDz2Gq','010-9876-5432','ACTIVATE',NULL,'ROLE_USER'),(10,'팡주',0,NULL,'asd@asd.com',_binary '\0','asdasd','$2a$10$GTnKj.tYEBgEvC5ZofZU1.PB69BtKvWxrd/P5GeZPEK7Jt3krEwfe','010-1478-5236','ACTIVATE',NULL,'ROLE_USER'),(12,'바뀐광주',10,'2022-05-06 10:38:53.419035','test3@test.com',_binary '','바뀐테스터','$2a$10$/ZD8yJCdak/7HjkWa0hNyO6DJ0G90Y96gw4cQ9Fr3bGmz.AeO0QJa','010-2234-8878','ACTIVATE','2022-05-06 10:38:53.480535','ROLE_USER'),(13,'팡주',0,NULL,'asdas@asd.com',_binary '\0','테스터123','$2a$10$9rwRbcOPKXUqdoG3RqAN0OBwZKBJxkWvlGzfCSaV0D3k4y8O.Epq.','010-4567-9873','ACTIVATE',NULL,'ROLE_USER'),(31,'바뀐광주',10,'2022-05-06 10:40:40.319952','test4@test.com',_binary '','바뀐테스터','$2a$10$4.urlYTKZV5//QpnE0NJ1uHmycyv8AYV76iAKUE4J11afrhPNE9dm','010-2231-8878','ACTIVATE','2022-05-06 10:40:40.381451','ROLE_USER'),(32,'123',0,NULL,'123@123.com',_binary '\0','123','$2a$10$RbTtXSP2mgDiZK7ZR63J1eOSjxNPfuHg7sqXSiM9xRxVDgdJMDwQW','010-4567-6853','ACTIVATE',NULL,'ROLE_USER'),(33,'1234',0,NULL,'1234@1234.com',_binary '\0','1234','$2a$10$hlqNWsjY6KVGEJ6negoNz.lnX1aSa8Q9.qIMD3He//It4BkIObZGu','010-1245-7836','ACTIVATE',NULL,'ROLE_USER'),(41,NULL,10,'2022-05-11 10:42:54.232209','test1234@test.com',_binary '','테스터','$2a$10$AMKqt4h8xaEkCMhZ.n0OeOe1I38rya0bJnhV8Nl206uuYCyMKJL7O','010-1113-1334','ACTIVATE','2022-05-11 10:42:54.232209','ROLE_USER'),(43,'동탄순환대로12길85',10,'2022-05-11 11:09:06.266656','hello@hello.com',_binary '\0','헬로우','$2a$10$nDX4vZGmWA8ha8VtcTsWkusv7I3/5oLmzE5Fdw.NbJ1RQMLHSTblq','010-6473-3939','ACTIVATE','2022-05-11 11:09:06.267638','ROLE_USER'),(44,'팡주',0,'2022-05-11 14:28:07.406680','124@124.com',_binary '\0','124','$2a$10$D2wZ0TTWgQoLHMnNRD.RSO81LJI7HLV2EDxZQoBOVPbp8rGzKb0z.','010-8456-6854','ACTIVATE','2022-05-11 14:28:07.406691','ROLE_USER'),(45,'광광',0,'2022-05-12 09:31:15.801550','qwe@qwe.com',_binary '\0','qwe','$2a$10$64ut9BAJn87G5ZRwXUN1D.wMNqv2402qag5oUHU/fCQsv6gotFhM2','123-4567-8910','ACTIVATE','2022-05-12 09:31:15.801550','ROLE_USER'),(49,'충장로',10,'2022-05-13 10:36:12.987587','default@default.com',_binary '','디폴트테스트','$2a$10$YcfyLJW6ThzRn6l96nSKIeMb99uM4bWW09KHHhm6lQCTKPsOudTG.','010-2232-8434',NULL,'2022-05-13 10:36:12.987587',NULL),(50,'충장로',10,'2022-05-13 10:59:59.840022','default2@default.com',_binary '','디폴트테스트','$2a$10$oKNEEfEqAwCsq.WrQysW2eC6nEcB69ugZWuke4s.v2IU7VKGXi8PC','010-2245-8434',NULL,'2022-05-13 10:59:59.840022',NULL),(67,'qweqwe',0,'2022-05-13 11:24:28.049215','qwe@qwe.qwe',_binary '\0','qweqwe','$2a$10$PNLVBvLt8ei36Q6gHlhZnebGRd0rgw5SDdYE/L2c5Kmen0wRhhfza','qweqwe','ACTIVATE','2022-05-13 11:24:28.049215','ROLE_USER'),(70,'충장로',10,NULL,'default4@default.com',_binary '','디폴트테스트','$2a$10$KB3.ggH4Wor5Aiw6gQi5t.CgNH6TojqEPe6AentKLDXCbSY1QMN1q','010-2344-8434',NULL,NULL,NULL),(71,'충장로',10,'2022-05-13 13:44:44.976597','default5@default.com',_binary '','디폴트테스트','$2a$10$lfF6c0p5PdF85c1EhMm3vuRRjJQTydTsgeS/IkEf53rhCMZ19ci8W','010-5344-8434',NULL,'2022-05-13 13:44:44.976597',NULL),(72,'충장로',10,'2022-05-13 14:01:31.291956','default6@default.com',_binary '','디폴트테스트','$2a$10$XuxGVIP9CpardQXGDUbKSuN9tPMITRKh9y9mwO6dBTYkca5oFprUm','010-5364-8434',NULL,'2022-05-13 14:01:31.291956',NULL),(73,'충장로',10,'2022-05-13 14:10:37.633010','default7@default.com',_binary '','디폴트테스트','$2a$10$ArLiHzqz9UQYmvA5BxoPWOg0DRe8jHogoRFgCGWZRSdRGhiJtCr.2','010-5374-8434','ACTIVATE','2022-05-13 14:10:37.633010','ROLE_USER'),(74,'충장로',10,'2022-05-13 14:12:15.341264','default8@default.com',_binary '','디폴트테스트','$2a$10$Fu84ljflVQKmakc9MBsovuFY.MyF/nCJOAOezMku.OnWnnFaENVTa','010-5384-8434','ACTIVATE','2022-05-13 14:12:15.341264','ROLE_USER'),(75,'충장로',10,'2022-05-13 14:14:09.595174','default9@default.com',_binary '','디폴트테스트','$2a$10$NPQC3YdIAFNi7Z.yYfHBB.ueSwzygQdX3XKiFjGWR5G30Z8eSTywC','010-5394-8434',NULL,'2022-05-13 14:14:09.595174',NULL),(76,'충장로',10,'2022-05-13 14:20:00.513806','default10@default.com',_binary '','디폴트테스트','$2a$10$kXkWSFDThVj3eKJCwPe0u.9OYiQpeaF/rBwCgjf3eUEPk0H2gfTy6','010-5304-8434',NULL,'2022-05-13 14:20:00.513806',NULL),(77,NULL,0,'2022-05-13 14:24:13.415553','test@testtest.com',_binary '\0','rr','testss','010-6388-1456',NULL,'2022-05-13 14:24:13.415553',NULL),(78,'충장로',10,'2022-05-13 14:42:07.379641','default11@default.com',_binary '','디폴트테스트','$2a$10$Eadom7DYBgyd2RA96aHpoOegoJ8/XwrtvuiOrttuepdJJvOvcx6ZW','010-5314-8434',NULL,'2022-05-13 14:42:07.379641',NULL),(79,'충장로',10,'2022-05-13 14:44:18.543614','default12@default.com',_binary '','디폴트테스트','$2a$10$bzKqTkNiWRzbahPQ.c6g2.fciBKekEgSzEtSGsJj.XbC..wgdsbLG','010-5324-8434',NULL,'2022-05-13 14:44:18.543614',NULL),(81,'qrw[jop',0,NULL,'asdasd@as.da',_binary '\0','qkadsfkl','$2a$10$3speWISj6K9fYKxEBARbzeI7gnLlG4/TmCqcm60S.x61eHbxY1xQK','pjqerwj','ACTIVATE',NULL,'ROLE_USER'),(82,'',0,NULL,'test1212@test.com',_binary '\0','test1212','$2a$10$rG4BYT8YwJCjw9U4RntQGutnuHgAvHpx.JNrXP3qxbWoQ1Owe.O52','010-2992-3903','ACTIVATE',NULL,'ROLE_USER'),(83,'',0,NULL,'K6S104@ssafy.com',_binary '\0','김싸피','$2a$10$5EwMIPr0BpdpdcwwDVG8EuJBQE4d4LXyzENz7.twl3nVgHXHcH9SS','010-0147-8520','ACTIVATE',NULL,'ROLE_USER'),(84,'부산',0,NULL,'tjswn12@naver.com',_binary '\0','황선주','$2a$10$TfPqLYdGTrubvlhW0njr/ODlDZJ4G2ssY9oUtx8fOpa2CmySkClgK','010-3428-3948','ACTIVATE',NULL,'ROLE_USER');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `month_plan`
--

DROP TABLE IF EXISTS `month_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `month_plan` (
  `month_plan_id` int NOT NULL AUTO_INCREMENT,
  `month_plan_bedding_count` int DEFAULT NULL,
  `month_plan_cleaning_count` int DEFAULT NULL,
  `month_plan_create_date` datetime(6) DEFAULT NULL,
  `month_plan_delivery_count` int DEFAULT NULL,
  `month_plan_name` varchar(30) NOT NULL,
  `month_plan_price` int NOT NULL,
  `month_plan_shirt_count` int DEFAULT NULL,
  `month_plan_update_date` datetime(6) DEFAULT NULL,
  `month_plan_wash_count` int DEFAULT NULL,
  PRIMARY KEY (`month_plan_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `month_plan`
--

LOCK TABLES `month_plan` WRITE;
/*!40000 ALTER TABLE `month_plan` DISABLE KEYS */;
INSERT INTO `month_plan` VALUES (1,0,0,'2022-04-28 21:42:30.000000',0,'자유',0,0,NULL,0),(2,0,3,'2022-04-28 21:42:33.000000',3,'올인원',64200,20,NULL,3),(3,0,2,'2022-04-28 21:42:34.000000',2,'와이셔츠 & 드라이',43500,20,NULL,0),(4,0,12,'2022-04-28 21:42:35.000000',2,'드라이온리',58600,0,NULL,0),(5,0,3,'2022-04-28 21:42:37.000000',3,'런드리&드라이',47400,0,NULL,3),(6,0,3,'2022-04-28 21:42:38.000000',3,'런드리온리',38700,0,NULL,3),(7,3,1,'2022-04-28 21:42:42.000000',1,'배딩온리',28700,0,NULL,0);
/*!40000 ALTER TABLE `month_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pay`
--

DROP TABLE IF EXISTS `pay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pay` (
  `pay_id` bigint NOT NULL AUTO_INCREMENT,
  `pay_create_date` datetime(6) DEFAULT NULL,
  `pay_pick_date` datetime(6) DEFAULT NULL,
  `pay_request_count` int NOT NULL,
  `pay_response_date` datetime(6) DEFAULT NULL,
  `pay_update_date` datetime(6) DEFAULT NULL,
  `collect_id` bigint DEFAULT NULL,
  `laundry_plan_id` int DEFAULT NULL,
  `member_id` int DEFAULT NULL,
  PRIMARY KEY (`pay_id`),
  KEY `FKnvexl1nxb1edwkudy2tbn46ye` (`collect_id`),
  KEY `FKmvn0tj66jjkdatk5ioelbfe4y` (`laundry_plan_id`),
  KEY `FKkm5dw145o5phn8vnggr0cd59w` (`member_id`),
  CONSTRAINT `FKkm5dw145o5phn8vnggr0cd59w` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `FKmvn0tj66jjkdatk5ioelbfe4y` FOREIGN KEY (`laundry_plan_id`) REFERENCES `laundry_plan` (`laundry_plan_id`),
  CONSTRAINT `FKnvexl1nxb1edwkudy2tbn46ye` FOREIGN KEY (`collect_id`) REFERENCES `collect` (`collect_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pay`
--

LOCK TABLES `pay` WRITE;
/*!40000 ALTER TABLE `pay` DISABLE KEYS */;
INSERT INTO `pay` VALUES (6,NULL,'2022-05-03 00:13:47.000000',1,'2021-11-05 00:13:47.000000',NULL,3,3,9),(9,NULL,'2022-05-03 00:15:36.000000',1,'2021-11-04 00:15:36.000000',NULL,3,3,9),(15,NULL,'2022-05-05 00:17:35.000000',1,'2021-11-06 00:17:35.000000',NULL,3,3,9),(16,NULL,'2022-05-06 12:47:31.656317',1,'2022-05-06 14:17:33.664306',NULL,18,38,9),(17,NULL,'2022-05-06 12:47:31.656317',1,'2022-05-06 14:18:02.282725',NULL,19,3,9),(18,NULL,'2022-05-09 07:44:05.330013',5,'2022-05-09 07:45:42.585824',NULL,25,1,9);
/*!40000 ALTER TABLE `pay` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-19  0:38:05
