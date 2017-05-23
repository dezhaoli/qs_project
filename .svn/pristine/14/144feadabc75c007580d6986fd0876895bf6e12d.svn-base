drop procedure IF EXISTS dc_agent_services_down;
delimiter //
CREATE PROCEDURE `dc_agent_services_down`()
BEGIN
declare v_max float(10,2) DEFAULT 0.0;
declare v_min float(10,2) DEFAULT 0.0;
declare v_mid int(10) DEFAULT 0;
declare v_date VARCHAR(15) DEFAULT '2017-05';
declare v_max_date datetime DEFAULT 0;
declare v_min_date datetime DEFAULT 0;
DECLARE done INT DEFAULT 0;
declare midCursor cursor for select gd.mid from taxes_invite_week gd group by gd.mid;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

delete from dc_taxes_invite_week_down  where DATE_FORMAT(date, '%Y-%m') = v_date;

open midCursor;

start_loop : loop

		IF done=1 THEN
				 LEAVE start_loop;
		END IF;

		fetch midCursor INTO v_mid;

		select  IFNULL(max(a.paytotal),0) max ,IFNULL(min(a.paytotal),0) min INTO v_max,v_min  from 
			taxes_invite_week a
		WHERE
			DATE_FORMAT(a.date, '%Y-%m') = v_date and a.mid = v_mid;


select  IFNULL(max(a.paytotal),0) max ,a.date INTO v_max ,v_max_date from 
			taxes_invite_week a 
WHERE
			DATE_FORMAT(a.date, '%Y-%m') = v_date and a.paytotal= v_max and a.mid = v_mid;


select  IFNULL(max(a.paytotal),0) max ,a.date INTO v_min ,v_min_date from 
			taxes_invite_week a 
WHERE
			DATE_FORMAT(a.date, '%Y-%m') = v_date and a.paytotal= v_min and a.mid = v_mid;
#select v_max,v_min,v_mid;

if(v_max - v_min) > 20 and v_max_date < v_min_date THEN
	insert into dc_taxes_invite_week_down (mid,date,paytotal,rebatetotal,bindpeople,scale,taxrate,isaward,parentid,info)  
select w.* from taxes_invite_week w where DATE_FORMAT(w.date, '%Y-%m') = v_date and w.mid = v_mid;
end if;

		
set v_max = 0.0;
set v_min := 0.0;

end loop start_loop;
commit;
-- 关闭游标
close midCursor;

END
//
delimiter ;