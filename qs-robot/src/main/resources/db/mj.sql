drop table pay_log;

CREATE TABLE `pay_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) NOT NULL COMMENT '邀请者mid',
  `add_date` date NOT NULL COMMENT '添加日期',
  `rebatetotal` int(10)  NOT NULL COMMENT '返现',
  `game_type` int(4) NOT NULL COMMENT '游戏类型',
  `status` int(4) NOT NULL COMMENT '状态 1 成功 0 失败',
  `ip` varchar(200) DEFAULT NULL  COMMENT 'ip地址',
  `extend1` varchar(200) DEFAULT NULL,
  `extend2` varchar(200) DEFAULT NULL,
  `extend3` varchar(200) DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modifier_id` int(11) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `mid` (`mid`,`add_date`,`game_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付日志表';

