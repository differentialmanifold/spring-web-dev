

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table lectures
# ------------------------------------------------------------

DROP TABLE IF EXISTS `lectures`;

CREATE TABLE `lectures` (
  `id` int(64) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `lectures` WRITE;
/*!40000 ALTER TABLE `lectures` DISABLE KEYS */;

INSERT INTO `lectures` (`id`, `name`)
VALUES
	(1,'math');

/*!40000 ALTER TABLE `lectures` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table schools
# ------------------------------------------------------------

DROP TABLE IF EXISTS `schools`;

CREATE TABLE `schools` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `schools` WRITE;
/*!40000 ALTER TABLE `schools` DISABLE KEYS */;

INSERT INTO `schools` (`id`, `name`)
VALUES
	(1,NULL);

/*!40000 ALTER TABLE `schools` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table students
# ------------------------------------------------------------

DROP TABLE IF EXISTS `students`;

CREATE TABLE `students` (
  `id` int(64) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;

INSERT INTO `students` (`id`, `name`)
VALUES
	(0,'zhang'),
	(1,'wang'),
	(9,'liu'),
	(10,'');

/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_group
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_group`;

CREATE TABLE `t_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '组编码',
  `group_name` varchar(255) DEFAULT NULL COMMENT '组名称',
  `topic_id` int(11) DEFAULT '0' COMMENT '主题编码',
  `level_id` int(11) DEFAULT NULL COMMENT '层级编码',
  `cron` varchar(255) DEFAULT NULL COMMENT '调度表达式',
  `is_enable` tinyint(1) DEFAULT '1' COMMENT '是否启用',
  `desc_info` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_user` varchar(255) DEFAULT NULL COMMENT '创建用户',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组信息';

LOCK TABLES `t_group` WRITE;
/*!40000 ALTER TABLE `t_group` DISABLE KEYS */;

INSERT INTO `t_group` (`id`, `group_name`, `topic_id`, `level_id`, `cron`, `is_enable`, `desc_info`, `create_user`, `create_date`, `update_date`)
VALUES
	(2886,'abc',20,5,'',0,'','abc','2018-03-28 09:49:22','2018-05-11 09:00:26'),
	(2887,'abc_test',20,5,'',0,'','abc','2018-05-07 21:04:38','2018-05-09 12:34:25'),
	(2888,'abc_test1',20,10,'',1,'','abc','2018-05-09 14:18:41','2018-07-01 13:43:24'),
	(2889,'def_test',20,5,'',1,'test','def2','2018-12-27 17:39:37','2018-12-27 17:39:37'),
	(2890,'def_test',20,5,'',1,'test','def2','2018-12-27 17:39:37','2018-12-27 17:39:37'),
	(2891,'def_test',20,5,'',1,'test','def2','2018-12-27 17:39:37','2018-12-27 17:39:37'),
	(2892,'def_test',20,5,'',1,'test','def2','2018-12-27 17:39:37','2018-12-27 17:39:37'),
	(2893,'def_test',20,5,'',1,'test','def2','2018-12-27 17:39:37','2018-12-27 17:39:37'),
	(2894,'def_test',20,5,'',1,'test','def2','2018-12-27 17:39:37','2018-12-27 17:39:37'),
	(2895,'def_test',20,5,'',1,'test','def2','2018-12-27 17:39:37','2018-12-27 17:39:37'),
	(2896,'def_test',20,5,'',1,'test','def2','2018-12-27 17:39:37','2018-12-27 17:39:37'),
	(2897,'def_test',20,5,'',1,'test','def2','2018-12-27 17:39:37','2018-12-27 17:39:37'),
	(2898,'def_test_1',20,5,'',1,'test','def2','2018-12-27 17:39:37','2018-12-27 17:39:37'),
	(2899,'def_test',20,5,'',1,'test','def2','2018-12-27 17:39:37','2018-12-27 17:39:37'),
	(2900,'def_test',20,5,'',1,'test','def2','2018-12-27 17:39:37','2018-12-27 17:39:37'),
	(2901,'def_test',20,5,'',1,'test','def2','2018-12-27 17:39:37','2018-12-27 17:39:37'),
	(2902,'def_test',20,5,'',1,'test','def2','2018-12-27 17:39:37','2018-12-27 17:39:37'),
	(2903,'def_test',20,5,'',1,'test','def2','2018-12-27 17:39:37','2018-12-27 17:39:37'),
	(2904,'def2_test',20,5,'',1,'second test','def2','2018-12-28 16:38:49','2018-12-28 16:38:49'),
	(2906,'restfulapi-test-1',NULL,NULL,NULL,NULL,NULL,'testname','2019-05-25 20:43:45','2019-05-25 20:43:45'),
	(2907,'restfulapi-test-2',NULL,NULL,NULL,NULL,NULL,'testname','2019-05-25 20:59:28','2019-05-25 20:59:28'),
	(2908,'restfulapi-test-3',NULL,NULL,NULL,NULL,NULL,'testname','2019-05-25 21:00:26','2019-05-25 21:00:26'),
	(2909,'restfulapi-test-4',0,NULL,NULL,1,NULL,'testname',NULL,NULL),
	(2910,'restfulapi-test-5',0,NULL,NULL,1,NULL,'testname',NULL,NULL),
	(2911,'restfulapi-test-6',0,NULL,NULL,1,NULL,'testname',NULL,NULL),
	(2912,'restfulapi-test-0',0,NULL,NULL,1,NULL,'testname',NULL,NULL);

/*!40000 ALTER TABLE `t_group` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
