# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.22)
# Database: lattice_example
# Generation Time: 2018-06-06 05:25:21 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table sys_logs
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_logs`;

CREATE TABLE `sys_logs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `username` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名',
  `operation` varchar(50) NOT NULL DEFAULT '' COMMENT '用户操作',
  `method` varchar(200) NOT NULL DEFAULT '' COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `ip` varchar(64) NOT NULL DEFAULT '' COMMENT 'IP地址',
  `created_time` datetime NOT NULL COMMENT '响应时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志';

LOCK TABLES `sys_logs` WRITE;
/*!40000 ALTER TABLE `sys_logs` DISABLE KEYS */;

INSERT INTO `sys_logs` (`id`, `user_id`, `username`, `operation`, `method`, `params`, `ip`, `created_time`)
VALUES
	(1,1,'admin','登录系统','doLogin','','127.0.0.1','2018-06-05 22:35:37'),
	(2,1,'admin','登录系统','doLogin','','127.0.0.1','2018-06-05 22:37:41'),
	(3,1,'admin','登录系统','doLogin','','127.0.0.1','2018-06-05 22:40:34'),
	(4,1,'admin','登录系统','doLogin','','127.0.0.1','2018-06-05 22:44:07'),
	(5,1,'admin','登录系统','doLogin','','127.0.0.1','2018-06-05 22:46:41'),
	(6,1,'admin','登录系统','doLogin','','127.0.0.1','2018-06-05 22:49:12'),
	(7,1,'admin','登录系统','doLogin','','127.0.0.1','2018-06-05 22:51:40');

/*!40000 ALTER TABLE `sys_logs` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_menus
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_menus`;

CREATE TABLE `sys_menus` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `parent_id` bigint(20) NOT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) NOT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单管理';

LOCK TABLES `sys_menus` WRITE;
/*!40000 ALTER TABLE `sys_menus` DISABLE KEYS */;

INSERT INTO `sys_menus` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`, `created_time`, `modified_time`)
VALUES
	(1,0,'系统管理',NULL,'',0,'fa fa-coffee',0,'2017-08-09 22:49:47','2017-10-26 10:03:40'),
	(2,1,'系统菜单','/base/menu/list.html',NULL,1,'fa fa-th-list',1,'2017-08-09 22:55:15','2017-08-17 10:00:12'),
	(3,0,'组织机构',NULL,NULL,0,'fa fa-desktop',1,'2017-08-09 23:06:55','2017-08-17 09:54:28'),
	(4,1,'通用字典','/base/macro/list.html',NULL,1,'fa fa-book',2,'2017-08-09 23:06:58','2017-08-17 10:00:24'),
	(6,3,'用户管理','/base/user/list.html',NULL,1,'fa fa-user',2,'2017-08-10 14:12:11','2017-08-17 09:57:40'),
	(7,3,'角色管理','/base/role/list.html',NULL,1,'fa fa-paw',1,'2017-08-10 14:13:19','2017-08-17 09:57:32'),
	(11,6,'刷新',NULL,'sys:user:list',2,NULL,0,'2017-08-14 10:51:05',NULL),
	(12,6,'新增',NULL,'sys:user:save',2,NULL,0,'2017-08-14 10:51:35',NULL),
	(13,6,'编辑',NULL,'sys:user:edit',2,NULL,0,'2017-08-14 10:52:06',NULL),
	(14,6,'删除',NULL,'sys:user:remove',2,NULL,0,'2017-08-14 10:52:24',NULL),
	(15,7,'刷新',NULL,'sys:role:list',2,NULL,0,'2017-08-14 10:56:37',NULL),
	(16,7,'新增',NULL,'sys:role:save',2,NULL,0,'2017-08-14 10:57:02',NULL),
	(17,7,'编辑',NULL,'sys:role:edit',2,NULL,0,'2017-08-14 10:57:31','2017-08-17 14:28:27'),
	(18,7,'删除',NULL,'sys:role:remove',2,NULL,0,'2017-08-14 10:57:50',NULL),
	(19,7,'操作权限',NULL,'sys:role:authorizeOpt',2,NULL,0,'2017-08-14 10:58:55','2017-08-17 13:48:54'),
	(20,2,'刷新',NULL,'sys:menu:list',2,NULL,0,'2017-08-14 10:59:32',NULL),
	(21,2,'新增',NULL,'sys:menu:save',2,NULL,0,'2017-08-14 10:59:56',NULL),
	(22,2,'编辑',NULL,'sys:menu:edit',2,NULL,0,'2017-08-14 11:00:26',NULL),
	(23,2,'删除',NULL,'sys:menu:remove',2,NULL,0,'2017-08-14 11:00:58',NULL),
	(24,6,'启用',NULL,'sys:user:enable',2,NULL,0,'2017-08-14 17:27:18',NULL),
	(25,6,'停用',NULL,'sys:user:disable',2,NULL,0,'2017-08-14 17:27:43',NULL),
	(26,6,'重置密码',NULL,'sys:user:resetPassword',2,NULL,0,'2017-08-14 17:28:34',NULL),
	(27,1,'系统日志','/base/syslog/list.html',NULL,1,'fa fa-warning',3,'2017-08-14 22:11:53','2017-08-17 09:55:19'),
	(28,27,'刷新',NULL,'sys:log:list',2,NULL,0,'2017-08-14 22:30:22',NULL),
	(29,27,'删除',NULL,'sys:log:remove',2,NULL,0,'2017-08-14 22:30:43',NULL),
	(30,27,'清空',NULL,'sys:log:clear',2,NULL,0,'2017-08-14 22:31:02',NULL),
	(32,4,'刷新',NULL,'sys:macro:list',2,NULL,0,'2017-08-15 16:55:33',NULL),
	(33,4,'新增',NULL,'sys:macro:save',2,NULL,0,'2017-08-15 16:55:52',NULL),
	(34,4,'编辑',NULL,'sys:macro:edit',2,NULL,0,'2017-08-15 16:56:09',NULL),
	(35,4,'删除',NULL,'sys:macro:remove',2,NULL,0,'2017-08-15 16:56:29',NULL),
	(46,7,'数据权限',NULL,'sys:role:authorizeData',2,NULL,0,'2017-08-17 13:48:11',NULL);

/*!40000 ALTER TABLE `sys_menus` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_role_menus
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_role_menus`;

CREATE TABLE `sys_role_menus` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

LOCK TABLES `sys_role_menus` WRITE;
/*!40000 ALTER TABLE `sys_role_menus` DISABLE KEYS */;

INSERT INTO `sys_role_menus` (`id`, `role_id`, `menu_id`)
VALUES
	(1008,1,1),
	(1014,1,2),
	(1015,1,20),
	(1016,1,21),
	(1017,1,22),
	(1018,1,23),
	(1019,1,4),
	(1020,1,32),
	(1021,1,33),
	(1022,1,34),
	(1023,1,35),
	(1024,1,27),
	(1025,1,28),
	(1026,1,29),
	(1027,1,30),
	(1045,1,3),
	(1051,1,7),
	(1052,1,15),
	(1053,1,16),
	(1054,1,17),
	(1055,1,18),
	(1056,1,19),
	(1057,1,46),
	(1058,1,6),
	(1059,1,11),
	(1060,1,12),
	(1061,1,13),
	(1062,1,14),
	(1063,1,24),
	(1064,1,25),
	(1065,1,26);

/*!40000 ALTER TABLE `sys_role_menus` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_roles
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_roles`;

CREATE TABLE `sys_roles` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(100) NOT NULL DEFAULT '' COMMENT '角色名称',
  `role_sign` varchar(100) NOT NULL DEFAULT '' COMMENT '角色标识',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `created_id` bigint(255) NOT NULL COMMENT '创建用户id',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色';

LOCK TABLES `sys_roles` WRITE;
/*!40000 ALTER TABLE `sys_roles` DISABLE KEYS */;

INSERT INTO `sys_roles` (`role_id`, `role_name`, `role_sign`, `remark`, `created_id`, `created_time`, `modified_time`)
VALUES
	(1,'超级管理员','admin','【系统内置】',2,'2017-08-12 00:43:52','2017-08-12 19:14:59'),
	(39,'测试用户','test',NULL,1,'2017-08-17 19:41:15','2017-08-17 19:42:39');

/*!40000 ALTER TABLE `sys_roles` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_user_roles
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_user_roles`;

CREATE TABLE `sys_user_roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

LOCK TABLES `sys_user_roles` WRITE;
/*!40000 ALTER TABLE `sys_user_roles` DISABLE KEYS */;

INSERT INTO `sys_user_roles` (`id`, `user_id`, `role_id`)
VALUES
	(1,1,1);

/*!40000 ALTER TABLE `sys_user_roles` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_users
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_users`;

CREATE TABLE `sys_users` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(50) NOT NULL DEFAULT '' COMMENT '密码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(255) NOT NULL COMMENT '状态 0:禁用，1:正常',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `created_id` bigint(255) NOT NULL COMMENT '创建用户id',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户';

LOCK TABLES `sys_users` WRITE;
/*!40000 ALTER TABLE `sys_users` DISABLE KEYS */;

INSERT INTO `sys_users` (`user_id`, `username`, `password`, `email`, `mobile`, `status`, `remark`, `created_id`, `created_time`, `modified_time`)
VALUES
	(1,'admin','a66abb5684c45962d887564f08346e8d','admin@example.com','13000000001',1,NULL,1,'2018-06-05 21:40:39','2017-08-15 21:41:00');

/*!40000 ALTER TABLE `sys_users` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
