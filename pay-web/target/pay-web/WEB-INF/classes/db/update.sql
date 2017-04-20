DROP EVENT IF EXISTS `delete_pay_log`;
DELIMITER ;;
CREATE  EVENT `delete_pay_log` ON SCHEDULE EVERY 1 DAY STARTS '2016-04-15 03:00:00' ON COMPLETION NOT PRESERVE ENABLE DO 

BEGIN
   
   insert into pay_fail_log select * from pay_log where status=0;
   delete from pay_log  where status=0;

END
;;
DELIMITER ;


DELETE from pay_fail_log;
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
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8 COMMENT='支付日志表';