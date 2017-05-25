/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.111
Source Server Version : 50709
Source Host           : 192.168.1.111:3306
Source Database       : data_center

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2017-05-25 21:14:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for base_param
-- ----------------------------
DROP TABLE IF EXISTS `base_param`;
CREATE TABLE `base_param` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `code` varchar(100) DEFAULT NULL COMMENT '编码',
  `value` varchar(100) DEFAULT NULL COMMENT '值',
  `ext` varchar(100) DEFAULT NULL COMMENT '扩展字段',
  `status` varchar(10) DEFAULT NULL COMMENT '1 可用\n            0 不可用',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT NULL,
  `creater_id` int(11) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dc_account_log
-- ----------------------------
DROP TABLE IF EXISTS `dc_account_log`;
CREATE TABLE `dc_account_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) NOT NULL,
  `app_id` varchar(30) DEFAULT NULL COMMENT '应用ID',
  `app_version` varchar(30) DEFAULT NULL COMMENT '应用版本号',
  `terminal_type` varchar(30) DEFAULT NULL COMMENT '终端类型',
  `channel_id` varchar(30) DEFAULT NULL COMMENT '渠道ID',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `pay_way` varchar(30) DEFAULT NULL COMMENT '支付方式',
  `pay_amount` float(10,4) DEFAULT NULL COMMENT '支付金额',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='账单日志';

-- ----------------------------
-- Table structure for dc_create_room
-- ----------------------------
DROP TABLE IF EXISTS `dc_create_room`;
CREATE TABLE `dc_create_room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) DEFAULT NULL COMMENT '用户ID',
  `app_id` varchar(30) DEFAULT NULL COMMENT '游戏ID',
  `play_id` varchar(100) DEFAULT NULL COMMENT '玩法id',
  `play_name` varchar(100) DEFAULT NULL COMMENT '玩法名称',
  `create_room_time` datetime DEFAULT NULL COMMENT '创建房间时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3241 DEFAULT CHARSET=utf8 COMMENT='房间明细';

-- ----------------------------
-- Table structure for dc_playing
-- ----------------------------
DROP TABLE IF EXISTS `dc_playing`;
CREATE TABLE `dc_playing` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) DEFAULT NULL COMMENT '用户ID',
  `app_id` varchar(30) DEFAULT NULL COMMENT '游戏ID',
  `play_id` varchar(100) DEFAULT NULL COMMENT '玩法id',
  `play_name` varchar(100) DEFAULT NULL COMMENT '玩法名称',
  `game_start_time` datetime DEFAULT NULL COMMENT '游戏开始时间',
  `game_stop_time` datetime DEFAULT NULL COMMENT '游戏结束时间',
  PRIMARY KEY (`id`),
  KEY `app_id` (`app_id`)
) ENGINE=MyISAM AUTO_INCREMENT=82076 DEFAULT CHARSET=utf8 COMMENT='在线游戏用户明细';

-- ----------------------------
-- Table structure for dc_user_add_log
-- ----------------------------
DROP TABLE IF EXISTS `dc_user_add_log`;
CREATE TABLE `dc_user_add_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) NOT NULL COMMENT '用户id',
  `u_name` varchar(50) DEFAULT '' COMMENT '用户名',
  `app_id` varchar(30) DEFAULT NULL COMMENT '应用id',
  `app_version` varchar(30) DEFAULT NULL COMMENT '应用版本号',
  `terminal_type` varchar(30) DEFAULT NULL COMMENT '终端类型',
  `channel_id` varchar(30) DEFAULT NULL COMMENT '渠道ID',
  `if_update` varchar(3) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='新增用户日志';

-- ----------------------------
-- Table structure for dc_user_login_log
-- ----------------------------
DROP TABLE IF EXISTS `dc_user_login_log`;
CREATE TABLE `dc_user_login_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) DEFAULT NULL COMMENT '用户ID',
  `app_id` varchar(30) DEFAULT NULL COMMENT '应用ID',
  `app_version` varchar(30) DEFAULT NULL COMMENT '版本号',
  `terminal_type` varchar(30) DEFAULT NULL COMMENT '终端类型',
  `channel_id` varchar(50) DEFAULT NULL COMMENT '渠道ID',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `logout_time` datetime DEFAULT NULL COMMENT '登出时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='用户登录日志';

-- ----------------------------
-- Table structure for pay_fail_log
-- ----------------------------
DROP TABLE IF EXISTS `pay_fail_log`;
CREATE TABLE `pay_fail_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) NOT NULL COMMENT '邀请者mid',
  `add_date` date NOT NULL COMMENT '添加日期',
  `rebatetotal` decimal(10,2) NOT NULL COMMENT '返现',
  `game_type` int(4) NOT NULL COMMENT '游戏类型',
  `status` int(4) NOT NULL COMMENT '状态',
  `ip` varchar(200) DEFAULT NULL COMMENT 'ip地址',
  `extend1` varchar(200) DEFAULT NULL,
  `extend2` varchar(200) DEFAULT NULL,
  `extend3` varchar(200) DEFAULT NULL,
  `c_creator_id` int(11) DEFAULT NULL,
  `c_create_time` datetime DEFAULT NULL,
  `c_modifier_id` int(11) DEFAULT NULL,
  `c_modify_time` datetime DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `mid` (`mid`,`add_date`,`game_type`,`extend1`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8 COMMENT='支付日志表';

-- ----------------------------
-- Table structure for pay_log
-- ----------------------------
DROP TABLE IF EXISTS `pay_log`;
CREATE TABLE `pay_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) NOT NULL COMMENT '邀请者mid',
  `add_date` date NOT NULL COMMENT '添加日期',
  `rebatetotal` decimal(10,2) NOT NULL COMMENT '返现',
  `game_type` int(4) NOT NULL COMMENT '游戏类型',
  `status` int(4) NOT NULL COMMENT '状态',
  `ip` varchar(200) DEFAULT NULL COMMENT 'ip地址',
  `extend1` varchar(200) DEFAULT NULL,
  `extend2` varchar(200) DEFAULT NULL,
  `extend3` varchar(200) DEFAULT NULL,
  `c_creator_id` int(11) DEFAULT NULL,
  `c_create_time` datetime DEFAULT NULL,
  `c_modifier_id` int(11) DEFAULT NULL,
  `c_modify_time` datetime DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `mid` (`mid`,`add_date`,`game_type`,`extend1`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8 COMMENT='支付日志表';

-- ----------------------------
-- Table structure for pay_white_list
-- ----------------------------
DROP TABLE IF EXISTS `pay_white_list`;
CREATE TABLE `pay_white_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `mid` int(11) NOT NULL COMMENT 'memberId',
  `game_type` int(11) DEFAULT NULL COMMENT '游戏类型',
  `money` int(11) NOT NULL COMMENT '金额',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `extend1` varchar(200) DEFAULT NULL,
  `extend2` varchar(200) DEFAULT NULL,
  `extend3` varchar(200) DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modifier_id` int(11) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8 COMMENT='支付白名单表';

-- ----------------------------
-- Table structure for tb_dict
-- ----------------------------
DROP TABLE IF EXISTS `tb_dict`;
CREATE TABLE `tb_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(100) NOT NULL COMMENT '数据值',
  `name` varchar(100) NOT NULL COMMENT '标签名',
  `type` varchar(100) DEFAULT NULL COMMENT '类型',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `sort` decimal(10,0) NOT NULL COMMENT '排序（升序）',
  `parent_id` int(64) DEFAULT '0' COMMENT '父级id',
  `ext_code` varchar(100) DEFAULT '0' COMMENT '扩展编号',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '状态 1启用 0禁用',
  `icon` varchar(255) DEFAULT NULL,
  `extend1` varchar(255) DEFAULT NULL COMMENT '扩展字段1',
  `extend2` varchar(255) DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`id`),
  KEY `sys_dict_value` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Table structure for tb_log_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_log_info`;
CREATE TABLE `tb_log_info` (
  `l_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `u_id` bigint(20) NOT NULL,
  `l_account_name` varchar(100) DEFAULT NULL,
  `l_operation` varchar(255) DEFAULT NULL COMMENT '用户所做的操作',
  `l_content` varchar(1000) DEFAULT NULL COMMENT '日志内容',
  `l_create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`l_id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_login_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_login_info`;
CREATE TABLE `tb_login_info` (
  `l_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) DEFAULT NULL,
  `u_account_name` varchar(255) DEFAULT NULL,
  `l_ip` varchar(255) DEFAULT NULL,
  `l_region` varchar(255) DEFAULT NULL,
  `l_province` varchar(255) DEFAULT NULL,
  `l_city` varchar(255) DEFAULT NULL,
  `l_login_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`l_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1470 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_resource
-- ----------------------------
DROP TABLE IF EXISTS `tb_resource`;
CREATE TABLE `tb_resource` (
  `s_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资源id',
  `s_parent_id` int(11) DEFAULT NULL COMMENT '资源父id',
  `s_name` varchar(100) NOT NULL COMMENT '资源名称',
  `s_source_key` varchar(100) NOT NULL COMMENT '资源唯一标识',
  `s_type` int(11) NOT NULL COMMENT '资源类型,0:目录;1:菜单;2:按钮',
  `s_source_url` varchar(500) DEFAULT NULL COMMENT '资源url',
  `s_level` int(11) DEFAULT NULL COMMENT '层级',
  `s_icon` varchar(100) DEFAULT '' COMMENT '图标',
  `s_is_hide` int(11) DEFAULT '0' COMMENT '是否隐藏',
  `s_description` varchar(100) DEFAULT NULL COMMENT '描述',
  `s_create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `s_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `seq` int(11) DEFAULT NULL COMMENT '排序值',
  PRIMARY KEY (`s_id`)
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8 COMMENT='资源表';

-- ----------------------------
-- Table structure for tb_resources_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_resources_role`;
CREATE TABLE `tb_resources_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `s_id` int(11) DEFAULT NULL COMMENT '资源id',
  `r_id` int(11) DEFAULT NULL COMMENT '角色id',
  `t_create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `FK_r_resource_role` (`s_id`),
  KEY `FK_r_role_resource` (`r_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3811 DEFAULT CHARSET=utf8 COMMENT='角色权限映射表';

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `r_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `r_name` varchar(50) NOT NULL COMMENT '角色名称',
  `r_key` varchar(50) NOT NULL COMMENT '角色key',
  `r_status` int(11) DEFAULT '0' COMMENT '角色状态,0：正常；1：删除',
  `r_description` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `r_create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `r_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`r_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Table structure for tb_role_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_user`;
CREATE TABLE `tb_role_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `r_id` int(11) DEFAULT NULL COMMENT '角色id',
  `u_id` int(11) DEFAULT NULL COMMENT '用户id',
  `t_create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `FK_r_role_user` (`r_id`),
  KEY `FK_r_user_role` (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='用户角色映射表';

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `u_name` varchar(100) NOT NULL COMMENT '真实姓名',
  `u_account_name` varchar(100) NOT NULL COMMENT '账户名称',
  `u_password` varchar(100) NOT NULL COMMENT '用户密码',
  `u_delete_status` int(11) DEFAULT '0' COMMENT '逻辑删除状态',
  `u_locked` int(11) DEFAULT '0' COMMENT '是否锁定',
  `u_description` varchar(200) DEFAULT NULL COMMENT '用户描述',
  `u_credentials_salt` varchar(500) NOT NULL COMMENT '加密盐',
  `u_creator_name` varchar(100) NOT NULL COMMENT '创建者',
  `u_create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `u_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`u_id`),
  UNIQUE KEY `u_account_name_unique` (`u_account_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='用户账户表';

-- ----------------------------
-- Table structure for tb_user_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_info`;
CREATE TABLE `tb_user_info` (
  `u_id` int(11) NOT NULL COMMENT '用户id',
  `u_sex` int(11) DEFAULT NULL COMMENT '性别',
  `u_birthday` date DEFAULT NULL COMMENT '出生日期',
  `u_telephone` varchar(20) DEFAULT NULL COMMENT '电话',
  `u_email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `u_address` varchar(200) DEFAULT NULL COMMENT '住址',
  `u_create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户扩展信息表';
