drop procedure IF EXISTS updateTaxesInviteWeek;
delimiter //
cREATE  DEFINER=`root`@`localhost` PROCEDURE `updateTaxesInviteWeek`()
-- 周结算系统表更新时间为执行时间
BEGIN

-- 声明一个标志done， 用来判断游标是否遍历完成
  DECLARE done INT DEFAULT 0; 

	-- 声明一个变量，用来存放从游标中提取的数据
	-- 特别注意这里的名字不能与由游标中使用的列明相同，否则得到的数据都是0 
  DECLARE tmid INT(11) DEFAULT 0;
  DECLARE tparent_id int(11) DEFAULT 0;
  DECLARE tscale TINYINT(3) DEFAULT NULL;
  DECLARE mktime int(11) DEFAULT 0;

  DECLARE nowdate date DEFAULT NOW(); -- 开始时间
  DECLARE endtmp date DEFAULT NOW();  -- 结束时间 

  DECLARE total1 int(11) DEFAULT 0;
  DECLARE invitotal1 int(11) DEFAULT 0;
  DECLARE total2 int(11) DEFAULT 0;
  DECLARE invitotal2 int(11) DEFAULT 0;
  DECLARE total3 int(11) DEFAULT 0;
  DECLARE invitotal3 int(11) DEFAULT 0;
  DECLARE ceeds1 int(11) DEFAULT 0;
  DECLARE ceeds2 int(11) DEFAULT 0;
  DECLARE ceeds3 int(11) DEFAULT 0;

  DECLARE counttotal int(11) DEFAULT 0; -- 总金额  
  DECLARE countinvitotal int(11) DEFAULT 0; -- 总招募人数
  DECLARE countceeds int(11) DEFAULT 0;  -- 总金额收益比
  declare allInfo VARCHAR(200) DEFAULT null;
	-- 声明游标对应的 SQL 语句获取代理商所有信息
  DECLARE cur CURSOR FOR
	SELECT mid,parent_id,scale,mktime FROM sc_majiang.memberagents WHERE status=0;


  -- 代理商统计 IFNULL防止查询对象为null 则设置为0
  DECLARE payCount CURSOR FOR 
					select  IFNULL(p.paytotal1,0) ,IFNULL(p.invitetotal1,0),IFNULL(p.proceeds1,0),IFNULL(p.paytotal2,0),IFNULL(p.invitetotal2,0),IFNULL(p.proceeds2,0),IFNULL(p.paytotal3,0),IFNULL(p.invitetotal3,0),IFNULL(p.proceeds3,0),
					CONCAT('[',
												GROUP_CONCAT(
														 CONCAT('[',ifNULL(p.paytotal1,'0')),',',
														 CONCAT(ifNULL(p.proceeds1,'0'),','),
														 CONCAT(ifNULL(p.invitetotal1,'0')),',],',
														 CONCAT('[',ifNULL(p.paytotal2,'0')),',',
														 CONCAT(ifNULL(p.proceeds2,'0'),','),
														 CONCAT(ifNULL(p.invitetotal2,'0')),',],',
														 CONCAT('[',ifNULL(p.paytotal3,'0')),',',
														 CONCAT(ifNULL(p.proceeds3,'0'),','),
														 CONCAT(ifNULL(p.invitetotal3,'0')),',]'
					)
					,']') info 
					from ( 
					SELECT a.paytotal1 paytotal1,a.invitetotal1 invitetotal1,b.paytotal2 paytotal2,b.invitetotal2 invitetotal2,c.paytotal3 paytotal3,c.invitetotal3 invitetotal3,
					(case  when a.paytotal1>=3500 then a.paytotal1*0.4 when  a.paytotal1>3500 and  a.paytotal1<=7000 then a.paytotal1*0.45 when  a.paytotal1>=7000 then a.paytotal1*0.50 end) proceeds1 ,
					(case  when b.paytotal2>=3500 then b.paytotal2*0.4 when  b.paytotal2>3500 and  b.paytotal2<=7000 then b.paytotal2*0.45 when  b.paytotal2>=7000 then b.paytotal2*0.50 end) proceeds2 ,
					(case  when c.paytotal3>=3500 then c.paytotal3*0.4 when  c.paytotal3>3500 and  c.paytotal3<=7000 then c.paytotal3*0.45 when  c.paytotal3>=7000 then c.paytotal3*0.50 end) proceeds3 
					 from (
					SELECT SUM(paytotal) AS paytotal1,SUM(invitetotal) AS invitetotal1 FROM taxes_invite WHERE date>=nowdate AND date<=endtmp AND mid=@mid ) a,
					(SELECT SUM(paytotal) AS paytotal2,SUM(invitetotal) AS invitetotal2 FROM taxes_invite WHERE date>=nowdate AND date<=endtmp AND parentid=@mid) b,
					(SELECT SUM(i.paytotal) AS paytotal3,SUM(i.invitetotal) AS invitetotal3 FROM taxes_invite i INNER JOIN  sc_majiang.memberagents a ON a.parent_id=@mid AND a.status=0 AND i.parentid=a.mid WHERE 
								i.date>=nowdate AND i.date<=endtmp ) c ) p;

	-- 在游标循环到最后会将 done 设置为 1
   DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;
     set nowdate = DATE_ADD(nowdate,INTERVAL -1 week);  
		 set endtmp = DATE_ADD(endtmp,INTERVAL 1 day);  

	-- 执行查询
	open cur;
	-- 遍历游标每一行
    grade_loop: LOOP 
		-- 把一行的信息存放在对应的变量中
		FETCH cur INTO tmid, tparent_id,tscale,mktime;
			IF done=1 THEN
				 LEAVE grade_loop;
			END IF;

      set @mid=tmid;

      -- 第二个游标跟据mid 统计他们金额总数与人数
					open payCount;
						class_loop: LOOP
					FETCH payCount INTO total1 ,invitotal1, total2, invitotal2, total3, invitotal3, ceeds1, ceeds2, ceeds3,allInfo;
						 IF done=1 THEN
							 LEAVE class_loop;
						END IF;
														set counttotal=total1+total2+total3;    -- 金额              
														set countinvitotal=invitotal1+invitotal2+invitotal3; -- 统计人数
														set countceeds=ceeds1+ceeds2+ceeds3;   -- 统计金额后收益比
							--  select counttotal,countinvitotal,countceeds,allInfo;
							-- select total1 ,invitotal1, total2, invitotal2, total3, invitotal3, ceeds1, ceeds2, ceeds3,allInfo;
										if  total1 > 0 or invitotal1 > 0  then 
														
											 -- INSERT into  abc VALUES (total1 ,invitotal1, total2, invitotal2, total3, invitotal3, ceeds1, ceeds2, ceeds3,allInfo,tparent_id);
														 INSERT IGNORE INTO taxes_invite_week SET mid=@mid,parentid=tparent_id,paytotal=counttotal,rebatetotal=countceeds,taxrate=0,
													 bindpeople=countinvitotal,date=DATE_ADD(NOW(),INTERVAL -1 day),info=allInfo;
										 end if ;
						END LOOP class_loop;
					CLOSE payCount;
					-- 第二个游标end

    SET done=0;
END LOOP grade_loop;
 	  -- end WHILE;
	CLOSE cur;
END
//
delimiter;
