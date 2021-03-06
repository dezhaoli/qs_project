/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.111
Source Server Version : 50709
Source Host           : 192.168.1.111:3306
Source Database       : sc_majiang_cfg

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2017-03-24 13:22:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for acti_systemactivity
-- ----------------------------
DROP TABLE IF EXISTS `acti_systemactivity`;
CREATE TABLE `acti_systemactivity` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '标题',
  `des` varchar(300) CHARACTER SET utf8 NOT NULL,
  `icon` varchar(100) NOT NULL COMMENT '图片',
  `file` varchar(100) CHARACTER SET utf8 NOT NULL,
  `stime` int(11) NOT NULL COMMENT '开始时间',
  `etime` int(11) NOT NULL COMMENT '结束时间',
  `ctime` int(11) NOT NULL COMMENT '关闭时间',
  `time` varchar(30) CHARACTER SET utf8 NOT NULL COMMENT '时间段',
  `paiju` int(11) NOT NULL COMMENT '牌局数',
  `reduce` int(11) NOT NULL COMMENT '每天减少',
  `max` int(11) NOT NULL COMMENT '每天最高经验值',
  `site` tinyint(4) NOT NULL COMMENT '平台（0PC，1移动）',
  `award` varchar(30) NOT NULL COMMENT '奖励',
  `status` tinyint(4) NOT NULL COMMENT '是否打开',
  `order_by` tinyint(4) NOT NULL COMMENT '排序',
  `config` varchar(1000) CHARACTER SET utf8 DEFAULT '' COMMENT '扩展',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COMMENT='玩牌送积分配置表';

-- ----------------------------
-- Records of acti_systemactivity
-- ----------------------------
INSERT INTO `acti_systemactivity` VALUES ('1', '中秋双重礼，特约比赛约定你', '', 'acti/zhongqiu.png', 'midAutumn/index.php', '1473004800', '1474210800', '1474383600', '00:00-00:00', '0', '0', '0', '1', '', '0', '20', '{\"match\":{\"1\":{\"val\":\"14\",\"desc\":\"888金币-中秋特约赛\"},\"2\":{\"val\":\"15\",\"desc\":\"50元话费-中秋特约赛\"},\"3\":{\"val\":\"16\",\"desc\":\"100元话费-中秋特约赛\"}},\"charge\":{\"4\":{\"val\":\"100\",\"desc\":\"20\"}}}');
INSERT INTO `acti_systemactivity` VALUES ('2', '玩牌送积分', '“小手”给大家金猴祝福，60寸小米电视、全自动脚步按摩、只能扫地机器人..', '', 'jifen/index.php', '1473004800', '1474210800', '1474383600', '00:00-00:00', '0', '0', '0', '1', '', '0', '0', '');
INSERT INTO `acti_systemactivity` VALUES ('3', '开服充值特惠', '', 'acti/chongzhi2.png', 'onlinepay/index.php', '1478448000', '1478966400', '1478966400', '00:00-00:00', '0', '0', '0', '1', '', '0', '0', '{\"charge\":{\"1\":{\"val\":\"100\",\"desc\":\"20\"}}}');
INSERT INTO `acti_systemactivity` VALUES ('4', '充值大特惠', '', 'acti/zhuanpan.png', 'payperfer/index.php', '1479052800', '1479052800', '1479052800', '00:00-00:00', '0', '0', '0', '1', '', '0', '0', '{\"scale\":{\"1\":{\"val\":\"10%\",\"desc\":\"30\"},\"2\":{\"val\":\"20%\",\"desc\":\"50\"},\"3\":{\"val\":\"30%\",\"desc\":\"15\"},\"4\":{\"val\":\"50%\",\"desc\":\"5\"}},\"match\":{\"5\":{\"val\":\"5\",\"desc\":\"30\"}}}');
INSERT INTO `acti_systemactivity` VALUES ('5', '充值大赠送', '', 'acti/dazengsong.png', 'paypresent/index.php', '1479312000', '1480953540', '1480953600', '00:00-00:00', '0', '0', '0', '1', '', '1', '0', '{\"param\":{\"1\":{\"val\":\"8\",\"desc\":\"118\"},\"2\":{\"val\":\"18\",\"desc\":\"278\"},\"3\":{\"val\":\"38\",\"desc\":\"638\"},\"4\":{\"val\":\"68\",\"desc\":\"1188\"},\"5\":{\"val\":\"98\",\"desc\":\"1788\"},\"6\":{\"val\":\"198\",\"desc\":\"3888\"}}}');

-- ----------------------------
-- Table structure for acti_systembypaiju_detailcfg
-- ----------------------------
DROP TABLE IF EXISTS `acti_systembypaiju_detailcfg`;
CREATE TABLE `acti_systembypaiju_detailcfg` (
  `did` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `id` int(11) NOT NULL COMMENT '外键',
  `roomid` int(11) NOT NULL COMMENT '房间ID',
  `jifen` float NOT NULL,
  PRIMARY KEY (`did`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COMMENT='玩牌送积分详细';

-- ----------------------------
-- Records of acti_systembypaiju_detailcfg
-- ----------------------------
INSERT INTO `acti_systembypaiju_detailcfg` VALUES ('1', '2', '4', '10');

-- ----------------------------
-- Table structure for acti_systemchenghaocfg
-- ----------------------------
DROP TABLE IF EXISTS `acti_systemchenghaocfg`;
CREATE TABLE `acti_systemchenghaocfg` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT '称号名称',
  `need` int(11) NOT NULL COMMENT '需求经验值',
  `award` int(11) NOT NULL COMMENT '每日可领取金币',
  `icon` varchar(30) NOT NULL COMMENT '图片名',
  `type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1称号2积分',
  `isneedcheck` tinyint(4) NOT NULL COMMENT '是否需要审核',
  `islimit` tinyint(4) NOT NULL COMMENT '是否限制兑换次数',
  PRIMARY KEY (`cid`)
) ENGINE=MyISAM AUTO_INCREMENT=139 DEFAULT CHARSET=utf8 COMMENT='活动称号配置表';

-- ----------------------------
-- Records of acti_systemchenghaocfg
-- ----------------------------
INSERT INTO `acti_systemchenghaocfg` VALUES ('1', '财主', '120', '200000', '', '1', '0', '0');
INSERT INTO `acti_systemchenghaocfg` VALUES ('2', 'BOSS', '480', '600000', '', '1', '0', '0');
INSERT INTO `acti_systemchenghaocfg` VALUES ('3', '土豪', '2400', '2000000', '', '1', '0', '0');
INSERT INTO `acti_systemchenghaocfg` VALUES ('4', 'MacBook Air', '4870000', '5', 'jifen4/mac.png', '2', '1', '1');
INSERT INTO `acti_systemchenghaocfg` VALUES ('8', '天梭(TISSOT)手表', '2250000', '5', 'jifen4/ts.png', '2', '1', '1');
INSERT INTO `acti_systemchenghaocfg` VALUES ('10', '1亿筹码', '1187500', '100000000', 'jifen4/cm.png', '2', '0', '0');
INSERT INTO `acti_systemchenghaocfg` VALUES ('12', '太阳花黄金手链', '980000', '5', 'jifen4/sl.png', '2', '1', '1');
INSERT INTO `acti_systemchenghaocfg` VALUES ('17', 'VIP5会员(30天)', '453600', '50', 'jifen4/vip5.png', '2', '1', '1');
INSERT INTO `acti_systemchenghaocfg` VALUES ('16', '京东500元购物卡', '475000', '50', 'jifen4/jd500.png', '2', '1', '1');
INSERT INTO `acti_systemchenghaocfg` VALUES ('13', '5000万筹码', '594000', '50000000', 'jifen4/cm.png', '2', '0', '0');
INSERT INTO `acti_systemchenghaocfg` VALUES ('22', '1000万筹码', '137500', '10000000', 'jifen4/cm.png', '2', '0', '0');
INSERT INTO `acti_systemchenghaocfg` VALUES ('20', '1888万筹码', '225000', '18880000', 'jifen4/cm.png', '2', '0', '0');
INSERT INTO `acti_systemchenghaocfg` VALUES ('25', '500万筹码', '68800', '5000000', 'jifen4/cm.png', '2', '0', '0');
INSERT INTO `acti_systemchenghaocfg` VALUES ('27', '200万筹码', '27500', '2000000', 'jifen4/cm.png', '2', '0', '0');
INSERT INTO `acti_systemchenghaocfg` VALUES ('135', '小米充电宝10000mAh', '52000', '500', 'jifen4/cdb.png', '2', '1', '1');
INSERT INTO `acti_systemchenghaocfg` VALUES ('24', '100元话费', '95000', '50', 'jifen4/tel100.png', '2', '1', '1');
INSERT INTO `acti_systemchenghaocfg` VALUES ('26', '50元话费', '48000', '100', 'jifen4/tel50.png', '2', '1', '1');
INSERT INTO `acti_systemchenghaocfg` VALUES ('5', 'iPhone 6s(玫瑰金)', '3966000', '5', 'jifen4/6s.png', '2', '1', '1');
INSERT INTO `acti_systemchenghaocfg` VALUES ('9', '坚果(智能家庭影院)', '1874000', '5', 'jifen4/jg.png', '2', '1', '1');
INSERT INTO `acti_systemchenghaocfg` VALUES ('7', '大疆无人机遥控飞机', '3000000', '5', 'jifen4/dfj.png', '2', '1', '1');
INSERT INTO `acti_systemchenghaocfg` VALUES ('21', '飞利浦剃须刀', '225000', '10', 'jifen4/9--txd.png', '2', '1', '1');
INSERT INTO `acti_systemchenghaocfg` VALUES ('11', '小米手机4c（标准版）', '1124000', '10', 'jifen4/xm4c.png', '2', '1', '1');
INSERT INTO `acti_systemchenghaocfg` VALUES ('19', '多功能按摩足疗机', '249500', '100', 'jifen4/jdam.png', '2', '1', '1');
INSERT INTO `acti_systemchenghaocfg` VALUES ('23', '小米路由器', '104000', '20', 'jifen4/ly.png', '2', '1', '1');
INSERT INTO `acti_systemchenghaocfg` VALUES ('29', '10万筹码', '1400', '100000', 'jifen4/cm.png', '2', '0', '0');
INSERT INTO `acti_systemchenghaocfg` VALUES ('28', '50万筹码', '6900', '500000', 'jifen4/cm.png', '2', '0', '0');
INSERT INTO `acti_systemchenghaocfg` VALUES ('6', '小米60寸电视', '3700000', '10', 'jifen4/xmds.png', '2', '1', '1');
INSERT INTO `acti_systemchenghaocfg` VALUES ('14', '智能扫地机器人', '524000', '20', 'jifen4/sdjqr.png', '2', '1', '1');
INSERT INTO `acti_systemchenghaocfg` VALUES ('130', '虚拟现实（VR眼镜）', '1049000', '5', 'jifen4/3D.png', '2', '1', '1');
INSERT INTO `acti_systemchenghaocfg` VALUES ('132', '美图（Meitu）', '1799000', '5', 'jifen4/mt.png', '2', '1', '1');
INSERT INTO `acti_systemchenghaocfg` VALUES ('133', '10Q币', '9500', '999', 'jifen4/QB.png', '2', '1', '1');
INSERT INTO `acti_systemchenghaocfg` VALUES ('134', '50Q币', '47500', '999', 'jifen4/QB.png', '2', '1', '1');
INSERT INTO `acti_systemchenghaocfg` VALUES ('136', 'Beats 入耳式耳机', '419000', '50', 'jifen4/EJ.png', '2', '1', '1');
INSERT INTO `acti_systemchenghaocfg` VALUES ('137', '小米小盒子mini版', '149000', '100', 'jifen4/XMhezi.png', '2', '1', '1');
INSERT INTO `acti_systemchenghaocfg` VALUES ('138', '小米（MI）小米平衡车', '1499000', '50', 'jifen4/che.png', '2', '1', '1');

-- ----------------------------
-- Table structure for acti_systemmiaoshacfg
-- ----------------------------
DROP TABLE IF EXISTS `acti_systemmiaoshacfg`;
CREATE TABLE `acti_systemmiaoshacfg` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT '称号名称',
  `icon` varchar(30) NOT NULL COMMENT '图片名',
  `need` int(10) unsigned NOT NULL COMMENT '总需次数',
  `award` int(10) unsigned NOT NULL COMMENT '奖励',
  `isneedcheck` tinyint(4) NOT NULL COMMENT '是否需要审核',
  `userlimit` smallint(4) NOT NULL COMMENT '限制每人兑换次数',
  PRIMARY KEY (`cid`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='秒杀夺宝配置表';

-- ----------------------------
-- Records of acti_systemmiaoshacfg
-- ----------------------------

-- ----------------------------
-- Table structure for acti_systemnumericalcfg
-- ----------------------------
DROP TABLE IF EXISTS `acti_systemnumericalcfg`;
CREATE TABLE `acti_systemnumericalcfg` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类型1登录基数，2登录连续天数，3，分享基数，4分享连续天数，5邀请基数码相机',
  `num` int(11) NOT NULL COMMENT '数值',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='系统数值表';

-- ----------------------------
-- Records of acti_systemnumericalcfg
-- ----------------------------

-- ----------------------------
-- Table structure for acti_systempaijulotterycfg
-- ----------------------------
DROP TABLE IF EXISTS `acti_systempaijulotterycfg`;
CREATE TABLE `acti_systempaijulotterycfg` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `type` int(10) unsigned NOT NULL COMMENT '类型1金币，2道具,3VIP，4实物',
  `param1` varchar(10) NOT NULL COMMENT '参数1',
  `param2` varchar(10) NOT NULL COMMENT '参数2',
  `icon` varchar(50) NOT NULL COMMENT '图片',
  `name` varchar(30) NOT NULL COMMENT '名字',
  `probability` float NOT NULL COMMENT '概率',
  `status` tinyint(4) NOT NULL COMMENT '状态',
  `rule` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '第几套规则',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='根据玩牌局数抽奖配置表';

-- ----------------------------
-- Records of acti_systempaijulotterycfg
-- ----------------------------

-- ----------------------------
-- Table structure for acti_systemstorecfg
-- ----------------------------
DROP TABLE IF EXISTS `acti_systemstorecfg`;
CREATE TABLE `acti_systemstorecfg` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `money` int(11) NOT NULL COMMENT '人民币',
  `gold` int(11) NOT NULL COMMENT '金币',
  `song` varchar(11) NOT NULL DEFAULT '0' COMMENT '活动赠送金币',
  `img` varchar(60) NOT NULL DEFAULT '' COMMENT '图片路径',
  `starttime` int(11) NOT NULL COMMENT '活动开始时间',
  `endtime` int(11) NOT NULL COMMENT '活动结束时间',
  `product_id` varchar(100) DEFAULT NULL,
  `ext` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unquei_money` (`money`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='商城表';

-- ----------------------------
-- Records of acti_systemstorecfg
-- ----------------------------
INSERT INTO `acti_systemstorecfg` VALUES ('1', '10', '11', '8', 'shop_huodongzhong.png', '1486742400', '1494604800', '', null);
INSERT INTO `acti_systemstorecfg` VALUES ('2', '20', '23', '8', 'shop_huodongzhong.png', '1486742400', '1494604800', null, null);
INSERT INTO `acti_systemstorecfg` VALUES ('3', '50', '59', '40', 'shop_huodongzhong.png', '1486742400', '1494604800', null, null);
INSERT INTO `acti_systemstorecfg` VALUES ('4', '100', '122', '85', 'shop_huodongzhong.png', '1486742400', '1494604800', null, null);
INSERT INTO `acti_systemstorecfg` VALUES ('10', '80', '96', '65', 'shop_huodongzhong.png', '1487779200', '1494604800', null, null);
INSERT INTO `acti_systemstorecfg` VALUES ('11', '500', '1100', '220', 'shop_huodongzhong.png', '1487692800', '1494604800', null, null);

-- ----------------------------
-- Table structure for sys_systemdemomatch
-- ----------------------------
DROP TABLE IF EXISTS `sys_systemdemomatch`;
CREATE TABLE `sys_systemdemomatch` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL DEFAULT '' COMMENT '比赛名称',
  `icon` varchar(60) DEFAULT NULL COMMENT '比赛图标路径',
  `attr_id` char(60) NOT NULL DEFAULT '' COMMENT '包含属性ID',
  `config` text NOT NULL COMMENT '参数配置',
  `msgpushtime` tinyint(3) NOT NULL DEFAULT '5' COMMENT '推送消息时间，距离比赛开始时间，单位分钟',
  `msgpush` text COMMENT '比赛开始前消息推送',
  `desc` text COMMENT '比赛描述',
  `status` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态 1：开启；0：关闭',
  `sort` smallint(5) NOT NULL DEFAULT '10' COMMENT '排序',
  PRIMARY KEY (`id`),
  KEY `sort` (`sort`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='比赛演示表';

-- ----------------------------
-- Records of sys_systemdemomatch
-- ----------------------------
INSERT INTO `sys_systemdemomatch` VALUES ('1', '测试', 'images/demo.png', '3,16,14,10', '{\"m_people\":150,\"16\":{\"date_time\":\"11:00,11:10,11:20,11:30,11:40,11:50,12:00\"},\"m_charge\":[{\"20\":\"\"}],\"14\":{\"name\":\"测试\",\"main\":{\"7\":{\"chip\":\"0\",\"rank_num\":\"150,90,60,30,9,6,3\",\"num\":\"11\",\"base_scale\":\"100\"}}},\"10\":[{\"rank_s\":\"1\",\"rank_e\":\"\",\"21\":{\"gold\":[\"10\"]}},{\"rank_s\":\"2\",\"rank_e\":\"\",\"21\":{\"gold\":[\"8\"]}},{\"rank_s\":\"3\",\"rank_e\":\"\",\"21\":{\"gold\":[\"5\"]}}]}', '3', '距离您报名的1元话费免费赛开始时间还有3分钟', '1111111', '0', '10');
INSERT INTO `sys_systemdemomatch` VALUES ('2', '1元话费赛', null, '2,18,20,14,10', '{\"m_people\":156,\"18\":{\"date_s\":\"2016-07-28\",\"date_e\":\"2016-09-10\",\"date_time\":\"19:00\"},\"m_charge\":[{\"20\":\"\"}],\"14\":{\"name\":\"1元话费赛\",\"main\":{\"6\":{\"chip\":\"10\",\"outline\":\"100\",\"rank_num\":\"10\",\"num\":\"10\",\"base_num\":\"10\",\"base_scale\":\"10\"}}},\"10\":[{\"rank_s\":\"1\",\"rank_e\":\"\",\"21\":{\"gold\":[\"1000\"]}},{\"rank_s\":\"\",\"rank_e\":\"\"}]}', '5', '', '11轮1副瑞士移位，淘汰人数见比赛内每一轮的提示。', '0', '10');
INSERT INTO `sys_systemdemomatch` VALUES ('3', '1000金币夺金赛', null, '3,4,16,13,10', '{\"m_people\":48,\"16\":{\"date_time\":\"21:00\"},\"m_charge\":[{\"22\":{\"tool_id\":\"1-2\",\"tool_num\":\"1\"}},{\"21\":\"200\"}],\"13\":[{\"name\":\"1000金币夺金赛\",\"main\":{\"7\":{\"chip\":\"0\",\"num\":\"7\",\"base_scale\":\"100\",\"rank_num\":\"48,36,27,18,12,6,3\"}}}],\"10\":[{\"rank_s\":\"1\",\"rank_e\":\"\",\"21\":{\"gold\":[\"1000\"]},\"22\":{\"tool\":[{\"id\":\"2-1\",\"num\":\"5\"},{\"id\":\"1-2\",\"num\":\"5\"}]}},{\"rank_s\":\"2\",\"rank_e\":\"\",\"21\":{\"gold\":[\"800\"]}},{\"rank_s\":\"3\",\"rank_e\":\"\",\"21\":{\"gold\":[\"700\"]}},{\"rank_s\":\"4\",\"rank_e\":\"6\",\"21\":{\"gold\":[\"500\"]}},{\"rank_s\":\"7\",\"rank_e\":\"10\",\"21\":{\"gold\":[\"300\"]}},{\"rank_s\":\"11\",\"rank_e\":\"16\",\"21\":{\"gold\":[\"100\"]}}]}', '5', '', '7轮1副瑞士移位，淘汰人数见比赛内每一轮的提示。', '0', '10');
INSERT INTO `sys_systemdemomatch` VALUES ('4', '500元话费赛', null, '3,17,13,10', '{\"m_people\":300,\"17\":[{\"week\":\"1\",\"date_time\":\"16:00\"}],\"m_charge\":[{\"22\":{\"tool_id\":\"1-2\",\"tool_num\":\"1\"}}],\"13\":[{\"name\":\"500元话费赛预赛\",\"main\":{\"6\":{\"chip\":\"40000\",\"num\":\"5\",\"base_num\":\"100\",\"base_scale\":\"50\",\"outline\":\"400\",\"rank_num\":\"120\"}}},{\"name\":\"500元话费赛决赛\",\"main\":{\"8\":{\"chip\":\"0\",\"num\":\"4\",\"base_num\":\"3\",\"base_scale\":\"100\",\"rank_num\":\"\"}}}],\"10\":[{\"rank_s\":\"1\",\"rank_e\":\"\",\"21\":{\"gold\":[\"10000\"]}},{\"rank_s\":\"2\",\"rank_e\":\"\",\"21\":{\"gold\":[\"5000\"]}},{\"rank_s\":\"3\",\"rank_e\":\"\",\"21\":{\"gold\":[\"3000\"]}},{\"rank_s\":\"4\",\"rank_e\":\"6\",\"21\":{\"gold\":[\"2000\"]}},{\"rank_s\":\"7\",\"rank_e\":\"10\",\"21\":{\"gold\":[\"1000\"]}}]}', '5', '', '预赛：打立出局，前120名进入决赛\n决赛：4轮3局定局积分，决出冠军', '0', '10');
INSERT INTO `sys_systemdemomatch` VALUES ('5', '比赛费用测试', null, '3,4,16,14,10', '{\"m_people\":100,\"16\":{\"date_time\":\"15:00\"},\"m_charge\":[{\"21\":\"200\"},{\"22\":{\"tool_id\":\"1-2\",\"tool_num\":\"2\"}},{\"23\":\"20\"}],\"14\":{\"name\":\"12\",\"main\":{\"7\":{\"chip\":\"1\",\"rank_num\":\"1\",\"num\":\"1\",\"base_scale\":\"1\"}}},\"10\":[{\"rank_s\":\"1\",\"rank_e\":\"\",\"21\":{\"gold\":[\"100\"]}}]}', '5', '', '', '0', '10');
INSERT INTO `sys_systemdemomatch` VALUES ('14', '888金币国庆特约赛', null, '4,18,14,10', '{\"m_people\":90,\"18\":{\"date_s\":\"2016-11-09\",\"date_e\":\"2016-11-10\",\"date_time\":\"\"},\"m_charge\":[{\"21\":\"1\"}],\"14\":{\"name\":\"888金币国庆特约赛\",\"main\":{\"7\":{\"chip\":\"0\",\"rank_num\":\"66,48,36,27,18,12,6,3,1\",\"num\":\"9\",\"base_scale\":\"100\"}}},\"10\":[{\"rank_s\":\"1\",\"rank_e\":\"\",\"21\":{\"gold\":[\"88\"]}},{\"rank_s\":\"2\",\"rank_e\":\"\",\"21\":{\"gold\":[\"68\"]}},{\"rank_s\":\"3\",\"rank_e\":\"\",\"21\":{\"gold\":[\"58\"]}},{\"rank_s\":\"4\",\"rank_e\":\"6\",\"21\":{\"gold\":[\"48\"]}},{\"rank_s\":\"7\",\"rank_e\":\"10\",\"21\":{\"gold\":[\"38\"]}},{\"rank_s\":\"11\",\"rank_e\":\"16\",\"21\":{\"gold\":[\"28\"]}},{\"rank_s\":\"17\",\"rank_e\":\"25\",\"21\":{\"gold\":[\"18\"]}},{\"rank_s\":\"26\",\"rank_e\":\"30\",\"21\":{\"gold\":[\"8\"]}}]}', '3', '', '9轮1副瑞士移位，每轮带40%分数到下一轮，淘汰人数见比赛内每一轮的提示。', '1', '10');
INSERT INTO `sys_systemdemomatch` VALUES ('6', '1元话费免费赛', 'bisai/huafk1.png', '2,3,16,14,10', '{\"m_people\":156,\"16\":{\"date_time\":\"13:20,18:20,19:20\"},\"m_charge\":[{\"20\":\"\"}],\"14\":{\"name\":\"1元话费免费赛\",\"main\":{\"7\":{\"chip\":\"0\",\"rank_num\":\"120,90,66,48,36,27,18,12,6,3,1\",\"num\":\"11\",\"base_scale\":\"100\"}}},\"10\":[{\"rank_s\":\"1\",\"rank_e\":\"\",\"22\":{\"tool\":[{\"id\":\"2-1\",\"num\":\"1\"}]}},{\"rank_s\":\"2\",\"rank_e\":\"\",\"21\":{\"gold\":[\"9\"]}},{\"rank_s\":\"3\",\"rank_e\":\"\",\"21\":{\"gold\":[\"8\"]}},{\"rank_s\":\"4\",\"rank_e\":\"6\",\"21\":{\"gold\":[\"7\"]}},{\"rank_s\":\"7\",\"rank_e\":\"10\",\"21\":{\"gold\":[\"6\"]}}]}', '3', '距离您报名的1元话费免费赛开始时间还有3分钟', '11轮1副瑞士移位，每轮带40%分数到下一轮，淘汰人数见比赛内每一轮的提示。', '1', '10');
INSERT INTO `sys_systemdemomatch` VALUES ('7', '5元话费赛', 'bisai/huafk2.png', '3,16,14,10', '{\"m_people\":48,\"16\":{\"date_time\":\"20:30,21:30,22:30\"},\"m_charge\":[{\"22\":{\"tool_id\":\"1-1\",\"tool_num\":\"1\"}},{\"21\":\"5\"}],\"14\":{\"name\":\"5元话费赛\",\"main\":{\"7\":{\"chip\":\"0\",\"rank_num\":\"36,27,18,12,6,3,1\",\"num\":\"7\",\"base_scale\":\"100\"}}},\"10\":[{\"rank_s\":\"1\",\"rank_e\":\"\",\"22\":{\"tool\":[{\"id\":\"2-2\",\"num\":\"1\"}]}},{\"rank_s\":\"2\",\"rank_e\":\"\",\"21\":{\"gold\":[\"40\"]}},{\"rank_s\":\"3\",\"rank_e\":\"\",\"21\":{\"gold\":[\"30\"]}},{\"rank_s\":\"4\",\"rank_e\":\"6\",\"21\":{\"gold\":[\"20\"]}},{\"rank_s\":\"7\",\"rank_e\":\"10\",\"21\":{\"gold\":[\"15\"]}},{\"rank_s\":\"11\",\"rank_e\":\"16\",\"21\":{\"gold\":[\"10\"]}}]}', '3', '距离您报名的5元话赛开始时间还有3分钟', '7轮1副瑞士移位，每轮带40%分数到下一轮，淘汰人数见比赛内每一轮的提示。', '1', '10');
INSERT INTO `sys_systemdemomatch` VALUES ('8', '10元话费赛', 'bisai/huafk3.png', '3,16,14,10', '{\"m_people\":36,\"16\":{\"date_time\":\"18:10,20:10,22:10\"},\"m_charge\":[{\"22\":{\"tool_id\":\"1-2\",\"tool_num\":\"1\"}},{\"21\":\"10\"}],\"14\":{\"name\":\"10元话费赛\",\"main\":{\"7\":{\"chip\":\"0\",\"rank_num\":\"27,18,12,6,3,1\",\"num\":\"6\",\"base_scale\":\"100\"}}},\"10\":[{\"rank_s\":\"1\",\"rank_e\":\"\",\"22\":{\"tool\":[{\"id\":\"2-3\",\"num\":\"1\"}]}},{\"rank_s\":\"2\",\"rank_e\":\"\",\"21\":{\"gold\":[\"80\"]}},{\"rank_s\":\"3\",\"rank_e\":\"\",\"21\":{\"gold\":[\"60\"]}},{\"rank_s\":\"4\",\"rank_e\":\"6\",\"21\":{\"gold\":[\"50\"]}},{\"rank_s\":\"7\",\"rank_e\":\"9\",\"21\":{\"gold\":[\"40\"]}},{\"rank_s\":\"10\",\"rank_e\":\"12\",\"21\":{\"gold\":[\"30\"]}}]}', '3', '距离您报名的10元话赛开始时间还有3分钟', '6轮1副瑞士移位，每轮带40%分数到下一轮，淘汰人数见比赛内每一轮的提示。', '1', '10');
INSERT INTO `sys_systemdemomatch` VALUES ('9', '30元话费赛', null, '3,16,14,10', '{\"m_people\":100,\"16\":{\"date_time\":\"\"},\"m_charge\":[{\"22\":{\"tool_id\":\"1-3\",\"tool_num\":\"1\"}},{\"21\":\"25\"}],\"14\":{\"name\":\"30元话费赛\",\"main\":{\"7\":{\"chip\":\"0\",\"rank_num\":\"36,27,18,12,6,3,1\",\"num\":\"6\",\"base_scale\":\"100\"}}},\"10\":[{\"rank_s\":\"1\",\"rank_e\":\"\",\"22\":{\"tool\":[{\"id\":\"2-4\",\"num\":\"1\"}]}},{\"rank_s\":\"2\",\"rank_e\":\"\",\"21\":{\"gold\":[\"200\"]}},{\"rank_s\":\"3\",\"rank_e\":\"\",\"21\":{\"gold\":[\"150\"]}},{\"rank_s\":\"4\",\"rank_e\":\"6\",\"21\":{\"gold\":[\"100\"]}},{\"rank_s\":\"7\",\"rank_e\":\"9\",\"21\":{\"gold\":[\"80\"]}},{\"rank_s\":\"10\",\"rank_e\":\"12\",\"21\":{\"gold\":[\"60\"]}}]}', '5', '', '6轮1副瑞士移位，每轮带40%分数到下一轮，淘汰人数见比赛内每一轮的提示。', '0', '10');
INSERT INTO `sys_systemdemomatch` VALUES ('10', '70金币免费赛', 'bisai/jinbi.png', '2,4,16,14,10', '{\"m_people\":156,\"16\":{\"date_time\":\"12:30,17:30,18:30,19:30,20:30\"},\"m_charge\":[{\"20\":\"\"}],\"14\":{\"name\":\"70金币免费赛\",\"main\":{\"7\":{\"chip\":\"0\",\"rank_num\":\"120,90,66,48,36,27,18,12,6,3,1\",\"num\":\"11\",\"base_scale\":\"100\"}}},\"10\":[{\"rank_s\":\"1\",\"rank_e\":\"\",\"21\":{\"gold\":[\"10\"]}},{\"rank_s\":\"2\",\"rank_e\":\"\",\"21\":{\"gold\":[\"9\"]}},{\"rank_s\":\"3\",\"rank_e\":\"\",\"21\":{\"gold\":[\"8\"]}},{\"rank_s\":\"4\",\"rank_e\":\"6\",\"21\":{\"gold\":[\"7\"]}},{\"rank_s\":\"7\",\"rank_e\":\"10\",\"21\":{\"gold\":[\"6\"]}}]}', '3', '距离您报名的70金币免费赛开始时间还有3分钟', '11轮1副瑞士移位，每轮带40%分数到下一轮，淘汰人数见比赛内每一轮的提示。', '1', '10');
INSERT INTO `sys_systemdemomatch` VALUES ('11', '500金币夺金赛', 'bisai/jinbiduo.png', '4,16,14,10', '{\"m_people\":27,\"16\":{\"date_time\":\"12:35,13:35,18:35,20:35\"},\"m_charge\":[{\"21\":\"20\"}],\"14\":{\"name\":\"500金币夺金赛\",\"main\":{\"7\":{\"chip\":\"0\",\"rank_num\":\"18,12,6,3,1\",\"num\":\"5\",\"base_scale\":\"100\"}}},\"10\":[{\"rank_s\":\"1\",\"rank_e\":\"\",\"21\":{\"gold\":[\"100\"]}},{\"rank_s\":\"2\",\"rank_e\":\"\",\"21\":{\"gold\":[\"70\"]}},{\"rank_s\":\"3\",\"rank_e\":\"\",\"21\":{\"gold\":[\"60\"]}},{\"rank_s\":\"4\",\"rank_e\":\"6\",\"21\":{\"gold\":[\"50\"]}},{\"rank_s\":\"7\",\"rank_e\":\"10\",\"21\":{\"gold\":[\"30\"]}}]}', '3', '距离您报名的500金币夺金赛开始时间还有3分钟', '5轮1副瑞士移位，每轮带40%分数到下一轮，淘汰人数见比赛内每一轮的提示。', '1', '10');
INSERT INTO `sys_systemdemomatch` VALUES ('13', '2000金币夺金赛', null, '4,18,14,10', '{\"m_people\":18,\"18\":{\"date_s\":\"2016-07-27\",\"date_e\":\"2016-07-28\",\"date_time\":\"\"},\"m_charge\":[{\"21\":\"120\"}],\"14\":{\"name\":\"2000金币夺金赛\",\"main\":{\"7\":{\"chip\":\"0\",\"rank_num\":\"12,6,3,1\",\"num\":\"4\",\"base_scale\":\"100\"}}},\"10\":[{\"rank_s\":\"1\",\"rank_e\":\"\",\"21\":{\"gold\":[\"680\"]}},{\"rank_s\":\"2\",\"rank_e\":\"\",\"21\":{\"gold\":[\"480\"]}},{\"rank_s\":\"3\",\"rank_e\":\"\",\"21\":{\"gold\":[\"380\"]}},{\"rank_s\":\"4\",\"rank_e\":\"\",\"21\":{\"gold\":[\"280\"]}},{\"rank_s\":\"5\",\"rank_e\":\"\",\"21\":{\"gold\":[\"180\"]}}]}', '5', '', '4轮1副瑞士移位，每轮带40%分数到下一轮，淘汰人数见比赛内每一轮的提示。', '0', '10');
INSERT INTO `sys_systemdemomatch` VALUES ('12', '800金币夺金赛', 'bisai/jinbiduo.png', '4,16,14,10', '{\"m_people\":18,\"16\":{\"date_time\":\"13:00,18:00,19:00,21:00\"},\"m_charge\":[{\"21\":\"50\"}],\"14\":{\"name\":\"800金币夺金赛\",\"main\":{\"7\":{\"chip\":\"0\",\"rank_num\":\"12,6,3,1\",\"num\":\"4\",\"base_scale\":\"100\"}}},\"10\":[{\"rank_s\":\"1\",\"rank_e\":\"\",\"21\":{\"gold\":[\"280\"]}},{\"rank_s\":\"2\",\"rank_e\":\"\",\"21\":{\"gold\":[\"200\"]}},{\"rank_s\":\"3\",\"rank_e\":\"\",\"21\":{\"gold\":[\"160\"]}},{\"rank_s\":\"4\",\"rank_e\":\"\",\"21\":{\"gold\":[\"100\"]}},{\"rank_s\":\"5\",\"rank_e\":\"\",\"21\":{\"gold\":[\"80\"]}}]}', '3', '距离您报名的800金币夺金赛开始时间还有3分钟', '4轮1副瑞士移位，每轮带40%分数到下一轮，淘汰人数见比赛内每一轮的提示。', '1', '10');
INSERT INTO `sys_systemdemomatch` VALUES ('16', '100元话费国庆特约赛', 'bisai/jinbiduo.png', '3,18,14,10', '{\"m_people\":180,\"18\":{\"date_s\":\"2016-12-17\",\"date_e\":\"2016-12-24\",\"date_time\":\"16:30,16:40,16:50,17:00,17:10,17:20,17:30\"},\"m_charge\":[{\"21\":\"1\"}],\"14\":{\"name\":\"100元话费国庆特约赛\",\"main\":{\"7\":{\"chip\":\"0\",\"rank_num\":\"150,120,90,66,33,27,18,15,9,6,3,1\",\"num\":\"12\",\"base_scale\":\"100\"}}},\"10\":[{\"rank_s\":\"1\",\"rank_e\":\"\",\"22\":{\"tool\":[{\"id\":\"2-6\",\"num\":\"1\"}]}},{\"rank_s\":\"2\",\"rank_e\":\"\",\"21\":{\"gold\":[\"800\"]}},{\"rank_s\":\"3\",\"rank_e\":\"\",\"21\":{\"gold\":[\"600\"]}},{\"rank_s\":\"4\",\"rank_e\":\"8\",\"21\":{\"gold\":[\"500\"]}},{\"rank_s\":\"9\",\"rank_e\":\"15\",\"21\":{\"gold\":[\"400\"]}},{\"rank_s\":\"16\",\"rank_e\":\"20\",\"21\":{\"gold\":[\"300\"]}},{\"rank_s\":\"21\",\"rank_e\":\"30\",\"21\":{\"gold\":[\"200\"]}},{\"rank_s\":\"31\",\"rank_e\":\"40\",\"21\":{\"gold\":[\"100\"]}}]}', '3', '您报名的100元话费中秋特约赛开始时间还有%d分钟，请到比赛报名界面准备。', '12轮1副瑞士移位，每轮带40%分数到下一轮，淘汰人数见比赛内每一轮的提示。', '0', '10');
INSERT INTO `sys_systemdemomatch` VALUES ('15', '50元话费国庆特约赛', 'bisai/jinbiduo.png', '3,17,14,10', '{\"m_people\":180,\"17\":[{\"week\":\"6\",\"date_time\":\"16:40,16:50,17:00,17:10,17:20,17:30,17:40,17:50\"},{\"week\":\"1\",\"date_time\":\"16:40,16:50,17:00,17:10,17:20,17:30,17:40,17:50\"}],\"m_charge\":[{\"21\":\"1\"}],\"14\":{\"name\":\"50元话费国庆特约赛\",\"main\":{\"7\":{\"chip\":\"0\",\"rank_num\":\"150,120,90,66,33,27,18,15,9,6,3,1\",\"num\":\"12\",\"base_scale\":\"100\"}}},\"10\":[{\"rank_s\":\"1\",\"rank_e\":\"\",\"22\":{\"tool\":[{\"id\":\"2-5\",\"num\":\"1\"}]}},{\"rank_s\":\"2\",\"rank_e\":\"\",\"21\":{\"gold\":[\"200\"]}},{\"rank_s\":\"3\",\"rank_e\":\"\",\"21\":{\"gold\":[\"120\"]}},{\"rank_s\":\"4\",\"rank_e\":\"8\",\"21\":{\"gold\":[\"80\"]}},{\"rank_s\":\"9\",\"rank_e\":\"15\",\"21\":{\"gold\":[\"60\"]}},{\"rank_s\":\"16\",\"rank_e\":\"20\",\"21\":{\"gold\":[\"40\"]}},{\"rank_s\":\"21\",\"rank_e\":\"30\",\"21\":{\"gold\":[\"20\"]}},{\"rank_s\":\"31\",\"rank_e\":\"40\",\"21\":{\"gold\":[\"10\"]}}]}', '3', '您报名的50元话费中秋特约赛开始时间还有%d分钟，请到比赛报名界面准备。', '12轮1副瑞士移位，每轮带40%分数到下一轮，淘汰人数见比赛内每一轮的提示', '0', '10');
INSERT INTO `sys_systemdemomatch` VALUES ('17', '周三八点档', null, '2,17,14,10', '{\"m_people\":90,\"17\":[{\"week\":\"3\",\"date_time\":\"10:00,10:50,11:00,11:10,11:20,11:30,11:40,11:50\"},{\"week\":\"4\",\"date_time\":\"10:00,10:50,11:00,11:10,11:20,11:30,11:40,11:50\"}],\"m_charge\":[{\"21\":\"10\"}],\"14\":{\"name\":\"周日八点档\",\"main\":{\"7\":{\"chip\":\"0\",\"rank_num\":\"90,72,60,48,33,24,15,9,6,3,1\",\"num\":\"11\",\"base_scale\":\"100\"}}},\"10\":[{\"rank_s\":\"1\",\"rank_e\":\"\",\"21\":{\"gold\":[\"100\"]}},{\"rank_s\":\"2\",\"rank_e\":\"\",\"21\":{\"gold\":[\"80\"]}},{\"rank_s\":\"3\",\"rank_e\":\"\",\"21\":{\"gold\":[\"50\"]}},{\"rank_s\":\"4\",\"rank_e\":\"30\",\"21\":{\"gold\":[\"20\"]}}]}', '3', '距离您报名的周日八点档比赛还有3分钟，请到报名界面准备', '11轮1副瑞士移位，淘汰人数见比赛内每一轮的提示。', '0', '10');
INSERT INTO `sys_systemdemomatch` VALUES ('18', '下午茶比赛', null, '4,18,14,10', '{\"m_people\":90,\"18\":{\"date_s\":\"2016-11-21\",\"date_e\":\"2016-11-30\",\"date_time\":\"10:00,10:20,11:00,11:20,12:00,12:20,13:00,13:20,14:00,14:20,15:00,15:20,16:00,16:20\"},\"m_charge\":[{\"21\":\"10\"}],\"14\":{\"name\":\"下午茶比赛\",\"main\":{\"7\":{\"chip\":\"0\",\"rank_num\":\"90,72,60,48,33,24,15,9,6,3,1\",\"num\":\"11\",\"base_scale\":\"100\"}}},\"10\":[{\"rank_s\":\"1\",\"rank_e\":\"\",\"21\":{\"gold\":[\"100\"]}},{\"rank_s\":\"2\",\"rank_e\":\"\",\"21\":{\"gold\":[\"80\"]}},{\"rank_s\":\"3\",\"rank_e\":\"\",\"21\":{\"gold\":[\"50\"]}},{\"rank_s\":\"4\",\"rank_e\":\"30\",\"21\":{\"gold\":[\"30\"]}}]}', '5', '距离您报名的下午茶比赛还有5分钟，请到报名界面准备', '11轮1副瑞士移位，淘汰人数见比赛内每一轮的提示。', '0', '10');

-- ----------------------------
-- Table structure for sys_systemdemomatchattr
-- ----------------------------
DROP TABLE IF EXISTS `sys_systemdemomatchattr`;
CREATE TABLE `sys_systemdemomatchattr` (
  `id` mediumint(8) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL DEFAULT '' COMMENT '属性名称',
  `parent_id` mediumint(8) NOT NULL DEFAULT '0' COMMENT '属性父类ID，0为一级属性',
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`)
) ENGINE=MyISAM AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='比赛属性演示表';

-- ----------------------------
-- Records of sys_systemdemomatchattr
-- ----------------------------
INSERT INTO `sys_systemdemomatchattr` VALUES ('1', '标签', '0');
INSERT INTO `sys_systemdemomatchattr` VALUES ('2', '免费赛', '1');
INSERT INTO `sys_systemdemomatchattr` VALUES ('3', '话费赛', '1');
INSERT INTO `sys_systemdemomatchattr` VALUES ('4', '金币赛', '1');
INSERT INTO `sys_systemdemomatchattr` VALUES ('5', '赛制', '0');
INSERT INTO `sys_systemdemomatchattr` VALUES ('6', '打立出局', '5');
INSERT INTO `sys_systemdemomatchattr` VALUES ('7', '瑞士移位', '5');
INSERT INTO `sys_systemdemomatchattr` VALUES ('8', '定局积分', '5');
INSERT INTO `sys_systemdemomatchattr` VALUES ('9', '奖池', '0');
INSERT INTO `sys_systemdemomatchattr` VALUES ('10', '固定奖池', '9');
INSERT INTO `sys_systemdemomatchattr` VALUES ('11', '浮动奖池', '9');
INSERT INTO `sys_systemdemomatchattr` VALUES ('12', '场次', '0');
INSERT INTO `sys_systemdemomatchattr` VALUES ('13', '系列赛', '12');
INSERT INTO `sys_systemdemomatchattr` VALUES ('14', '单选赛', '12');
INSERT INTO `sys_systemdemomatchattr` VALUES ('15', '比赛时间', '0');
INSERT INTO `sys_systemdemomatchattr` VALUES ('16', '日循环', '15');
INSERT INTO `sys_systemdemomatchattr` VALUES ('17', '周循环', '15');
INSERT INTO `sys_systemdemomatchattr` VALUES ('18', '特定日期', '15');
INSERT INTO `sys_systemdemomatchattr` VALUES ('19', '开赛费用', '0');
INSERT INTO `sys_systemdemomatchattr` VALUES ('20', '免费', '19');
INSERT INTO `sys_systemdemomatchattr` VALUES ('21', '金币', '19');
INSERT INTO `sys_systemdemomatchattr` VALUES ('22', '道具', '19');
INSERT INTO `sys_systemdemomatchattr` VALUES ('23', '积分', '19');

-- ----------------------------
-- Table structure for sys_systemfastmsgcfg
-- ----------------------------
DROP TABLE IF EXISTS `sys_systemfastmsgcfg`;
CREATE TABLE `sys_systemfastmsgcfg` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `msg` varchar(250) NOT NULL COMMENT '话语',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='常用语配置表';

-- ----------------------------
-- Records of sys_systemfastmsgcfg
-- ----------------------------
INSERT INTO `sys_systemfastmsgcfg` VALUES ('1', '快点啊，我等到花都谢了');
INSERT INTO `sys_systemfastmsgcfg` VALUES ('2', '怎么断线了');
INSERT INTO `sys_systemfastmsgcfg` VALUES ('3', '不要走，决战到天亮');
INSERT INTO `sys_systemfastmsgcfg` VALUES ('4', '你的牌打得太好啦');
INSERT INTO `sys_systemfastmsgcfg` VALUES ('5', '你是妹妹还是哥哥');
INSERT INTO `sys_systemfastmsgcfg` VALUES ('6', '跟你合作实在太愉快了');
INSERT INTO `sys_systemfastmsgcfg` VALUES ('7', '大家好，很高兴见到各位');
INSERT INTO `sys_systemfastmsgcfg` VALUES ('8', '我得离开一会');
INSERT INTO `sys_systemfastmsgcfg` VALUES ('9', '不要吵，专心玩游戏');

-- ----------------------------
-- Table structure for sys_systemprops
-- ----------------------------
DROP TABLE IF EXISTS `sys_systemprops`;
CREATE TABLE `sys_systemprops` (
  `pcate` int(10) unsigned NOT NULL COMMENT '道具分类',
  `pframe` int(10) unsigned NOT NULL COMMENT '道具帧号',
  `name` varchar(100) NOT NULL COMMENT '道具名',
  `bind` tinyint(3) unsigned NOT NULL COMMENT '是否绑定',
  `validity` int(10) unsigned NOT NULL COMMENT '有效期0 为永久有效（单位：天）',
  `url` varchar(100) NOT NULL,
  `price` int(10) unsigned NOT NULL,
  `description` varchar(150) NOT NULL COMMENT ' 描述',
  `store` tinyint(3) unsigned NOT NULL COMMENT '是否可从商城购买',
  `isbag` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '是否在背包显示(1:是，2：否)',
  `authority` int(11) NOT NULL COMMENT '使用方式',
  PRIMARY KEY (`pcate`,`pframe`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='道具配置表';

-- ----------------------------
-- Records of sys_systemprops
-- ----------------------------
INSERT INTO `sys_systemprops` VALUES ('1', '1', '5元话费参赛券', '1', '0', 'props/csq5yuan.png', '0', '5元话费赛的参赛券', '0', '1', '0');
INSERT INTO `sys_systemprops` VALUES ('2', '1', '1元话费卡', '1', '0', 'props/huafk1.png', '0', '使用后可获得1元话费', '0', '1', '0');
INSERT INTO `sys_systemprops` VALUES ('2', '2', '5元话费卡', '1', '0', 'props/huafk5.png', '0', '使用后可获得5元话费', '0', '1', '0');
INSERT INTO `sys_systemprops` VALUES ('2', '3', '10元话费卡', '1', '0', 'props/huafk10.png', '10000', '使用后可获得10元话费', '0', '1', '0');
INSERT INTO `sys_systemprops` VALUES ('1', '2', '10元话费参赛券', '1', '0', 'props/csq10yuan.png', '0', '10元话费赛的参赛券', '0', '0', '0');
INSERT INTO `sys_systemprops` VALUES ('1', '3', '30元话费参赛券', '1', '0', 'props/csq30yuan.png', '0', '30元话费赛的参赛券', '0', '0', '0');
INSERT INTO `sys_systemprops` VALUES ('2', '4', '30元话费卡', '1', '0', 'props/huafk30.png', '0', '使用后可获得30元话费', '0', '0', '0');
INSERT INTO `sys_systemprops` VALUES ('2', '5', '50元话费卡', '1', '0', 'props/huafk50.png', '0', '使用后可获得50元话费', '0', '0', '0');
INSERT INTO `sys_systemprops` VALUES ('2', '6', '100元话费卡', '1', '0', 'props/huafk100.png', '0', '使用后可获得100元话费', '1', '0', '0');

-- ----------------------------
-- Table structure for sys_systemroomcfg
-- ----------------------------
DROP TABLE IF EXISTS `sys_systemroomcfg`;
CREATE TABLE `sys_systemroomcfg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ju` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '局数',
  `cost` int(10) unsigned NOT NULL COMMENT '花费金币',
  `begin` int(11) NOT NULL COMMENT '启始房间号',
  `end` int(11) NOT NULL COMMENT '结束房间号',
  `type` varchar(32) CHARACTER SET latin1 NOT NULL COMMENT '类型',
  `name` varchar(32) NOT NULL COMMENT '房间名字',
  `serverid` int(10) unsigned NOT NULL COMMENT '服务器ID ',
  `taxation` int(11) NOT NULL DEFAULT '0' COMMENT '抽水, 单位 1/1000',
  `bettime` int(11) NOT NULL DEFAULT '20' COMMENT '出牌时间',
  `taxes_mode` tinyint(4) NOT NULL DEFAULT '0' COMMENT '抽水模式',
  `sb` int(11) NOT NULL DEFAULT '0' COMMENT '底注',
  `min` int(11) NOT NULL DEFAULT '0' COMMENT '最小携带',
  `max` int(11) NOT NULL DEFAULT '0' COMMENT '最大携带',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '开关',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_systemroomcfg
-- ----------------------------
INSERT INTO `sys_systemroomcfg` VALUES ('20', '4', '1', '100001', '300000', '2', '血战到底', '2000', '0', '20', '0', '0', '0', '0', '1');
INSERT INTO `sys_systemroomcfg` VALUES ('21', '8', '2', '300001', '400000', '2', '血战到底', '2000', '0', '20', '0', '0', '0', '0', '1');
INSERT INTO `sys_systemroomcfg` VALUES ('22', '4', '1', '400001', '500000', '1', '血流成河', '2000', '0', '20', '0', '0', '0', '0', '1');
INSERT INTO `sys_systemroomcfg` VALUES ('23', '8', '2', '500001', '600000', '1', '血流成河', '2000', '0', '20', '0', '0', '0', '0', '1');

-- ----------------------------
-- Table structure for sys_systemwxpaycfg
-- ----------------------------
DROP TABLE IF EXISTS `sys_systemwxpaycfg`;
CREATE TABLE `sys_systemwxpaycfg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf32 NOT NULL COMMENT '名称',
  `money` int(5) NOT NULL COMMENT '人民币',
  `glod` int(11) NOT NULL COMMENT '金币或者房卡',
  `createtime` int(11) NOT NULL COMMENT '创建时间',
  `type` tinyint(2) NOT NULL DEFAULT '1' COMMENT '1：跑得快 2：麻将 3：斗牛',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_systemwxpaycfg
-- ----------------------------
INSERT INTO `sys_systemwxpaycfg` VALUES ('1', '金币', '8', '118', '1483596503', '1');
INSERT INTO `sys_systemwxpaycfg` VALUES ('2', '金币', '18', '278', '1483598550', '1');
INSERT INTO `sys_systemwxpaycfg` VALUES ('3', '金币', '38', '638', '1483599192', '1');
INSERT INTO `sys_systemwxpaycfg` VALUES ('4', '金币', '68', '1188', '1483670950', '1');
INSERT INTO `sys_systemwxpaycfg` VALUES ('5', '金币', '98', '1788', '1483670951', '1');
INSERT INTO `sys_systemwxpaycfg` VALUES ('6', '金币', '198', '3888', '1483670952', '1');
INSERT INTO `sys_systemwxpaycfg` VALUES ('7', '房卡', '18', '12', '1484046686', '2');
INSERT INTO `sys_systemwxpaycfg` VALUES ('8', '房卡', '28', '20', '1484046701', '2');
INSERT INTO `sys_systemwxpaycfg` VALUES ('9', '房卡', '58', '45', '1484046712', '2');
INSERT INTO `sys_systemwxpaycfg` VALUES ('10', '房卡', '88', '75', '1484046726', '2');
INSERT INTO `sys_systemwxpaycfg` VALUES ('11', '房卡', '10', '13', '1484046982', '3');
INSERT INTO `sys_systemwxpaycfg` VALUES ('12', '房卡', '20', '27', '1484047006', '3');
INSERT INTO `sys_systemwxpaycfg` VALUES ('13', '房卡', '50', '70', '1484047028', '3');
INSERT INTO `sys_systemwxpaycfg` VALUES ('14', '房卡', '80', '115', '1484047044', '3');
INSERT INTO `sys_systemwxpaycfg` VALUES ('15', '房卡', '100', '146', '1484047072', '3');
INSERT INTO `sys_systemwxpaycfg` VALUES ('16', '房卡', '500', '1100', '1484047087', '3');
