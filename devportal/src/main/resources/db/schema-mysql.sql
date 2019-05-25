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
	(2898,'def_test',20,5,'',1,'test','def2','2018-12-27 17:39:37','2018-12-27 17:39:37'),
	(2899,'def_test',20,5,'',1,'test','def2','2018-12-27 17:39:37','2018-12-27 17:39:37'),
	(2900,'def_test',20,5,'',1,'test','def2','2018-12-27 17:39:37','2018-12-27 17:39:37'),
	(2901,'def_test',20,5,'',1,'test','def2','2018-12-27 17:39:37','2018-12-27 17:39:37'),
	(2902,'def_test',20,5,'',1,'test','def2','2018-12-27 17:39:37','2018-12-27 17:39:37'),
	(2903,'def_test',20,5,'',1,'test','def2','2018-12-27 17:39:37','2018-12-27 17:39:37'),
	(2904,'def2_test',20,5,'',1,'second test','def2','2018-12-28 16:38:49','2018-12-28 16:38:49');

/*!40000 ALTER TABLE `t_group` ENABLE KEYS */;
UNLOCK TABLES;
