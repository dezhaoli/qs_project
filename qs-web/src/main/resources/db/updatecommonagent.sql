
drop procedure IF EXISTS updateCommonAgentsInfo;
delimiter //
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateCommonAgentsInfo`(in dbname VARCHAR (100))
BEGIN

-- 声明一个标志done， 用来判断游标是否遍历完成
	DECLARE done INT DEFAULT 0;

	-- 声明一个变量，用来存放从游标中提取的数据
	-- 特别注意这里的名字不能与由游标中使用的列明相同，否则得到的数据都是NULL
	DECLARE tmid varchar(50) DEFAULT NULL;
	DECLARE tsitemid varchar(50) DEFAULT NULL;
    DECLARE tinfo VARCHAR(200) DEFAULT NULL;
	-- 声明游标对应的 SQL 语句
	DECLARE cur CURSOR FOR
		SELECT CONCAT('{"',dbname,'":',a.mid,'}') mid,b.sitemid FROM memberagents AS a LEFT JOIN members AS b ON a.mid=b.mid;

	-- 在游标循环到最后会将 done 设置为 1
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

	-- 执行查询
	open cur;
	-- 遍历游标每一行
	REPEAT
		-- 把一行的信息存放在对应的变量中
		FETCH cur INTO tmid, tsitemid;
		if not done then
			-- 这里就可以使用 tname， tpass 对应的信息了

       if  tsitemid is not null then
             set @query=(SELECT info FROM common_agents WHERE sitemid=tsitemid LIMIT 1);
					 if (@query is not null) then
										-- 更新表common_agents
										update common_agents set info=tmid where sitemid=tsitemid;
									 /*INSERT INTO mids VALUES (tmid,tsitemid);*/
								 else 
										INSERT INTO common_agents SET sitemid=tsitemid,info=tmid ;
					end if;
       end if ;
  
end if;
 	UNTIL done END REPEAT;
	CLOSE cur;

END
//
delimiter ;