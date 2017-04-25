/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50634
Source Host           : localhost:3306
Source Database       : gd_majiang_stat

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2017-04-23 22:27:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for biz_invite_week
-- ----------------------------
DROP TABLE IF EXISTS `biz_invite_week`;
CREATE TABLE `biz_invite_week` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL COMMENT '日期',
  `paytotal` decimal(10,2) NOT NULL COMMENT '总充值',
  `rebatetotal` decimal(10,2) NOT NULL COMMENT '返现',
  `bindpeople` int(11) NOT NULL COMMENT '绑定人数',
  `gametype` tinyint(4) NOT NULL,
  `bizid` smallint(5) unsigned NOT NULL COMMENT '商务ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `date` (`date`,`gametype`,`bizid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='分公司分游戏结算周表';

-- ----------------------------
-- Records of biz_invite_week
-- ----------------------------

-- ----------------------------
-- Table structure for member_retain
-- ----------------------------
DROP TABLE IF EXISTS `member_retain`;
CREATE TABLE `member_retain` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL COMMENT '日期(2014-06-30)',
  `regist` int(11) NOT NULL,
  `login` int(11) NOT NULL COMMENT '登录人数',
  `1day` int(11) NOT NULL,
  `2day` int(11) NOT NULL,
  `3day` int(11) NOT NULL,
  `4day` int(11) NOT NULL,
  `5day` int(11) NOT NULL,
  `6day` int(11) NOT NULL,
  `7day` int(11) NOT NULL,
  `14day` int(11) NOT NULL,
  `huiliu` int(11) NOT NULL,
  `huoyue` int(11) NOT NULL,
  `play` int(11) NOT NULL COMMENT '玩牌',
  `charge` int(10) unsigned NOT NULL COMMENT '充值',
  `gametype` tinyint(3) unsigned NOT NULL,
  `company` tinyint(4) NOT NULL COMMENT '分公司ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `date_2` (`date`,`gametype`),
  KEY `date` (`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户留存表';

-- ----------------------------
-- Records of member_retain
-- ----------------------------

-- ----------------------------
-- Table structure for player_online_day
-- ----------------------------
DROP TABLE IF EXISTS `player_online_day`;
CREATE TABLE `player_online_day` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL COMMENT '日期',
  `onlinemax` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最高在线',
  `onlineavg` int(10) unsigned NOT NULL COMMENT '平均在线',
  `playmax` int(10) unsigned NOT NULL COMMENT '最高在玩',
  `playavg` int(10) unsigned NOT NULL COMMENT '平均在玩',
  `gametype` tinyint(3) unsigned NOT NULL COMMENT '游戏类型',
  `company` tinyint(4) NOT NULL COMMENT '分公司ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `date_2` (`date`,`gametype`),
  KEY `date` (`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='在玩在线日表';

-- ----------------------------
-- Records of player_online_day
-- ----------------------------

-- ----------------------------
-- Table structure for player_pay_day
-- ----------------------------
DROP TABLE IF EXISTS `player_pay_day`;
CREATE TABLE `player_pay_day` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL COMMENT '日期',
  `paytotal` decimal(10,2) NOT NULL COMMENT '总充值',
  `granttotal` int(10) unsigned NOT NULL COMMENT '授权代理商人数',
  `gametype` tinyint(4) NOT NULL,
  `bizid` smallint(5) unsigned NOT NULL COMMENT '商务ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `date` (`date`,`gametype`,`bizid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='分公司分游戏充值日表';

-- ----------------------------
-- Records of player_pay_day
-- ----------------------------
