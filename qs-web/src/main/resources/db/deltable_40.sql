drop procedure IF EXISTS deltable_40;
delimiter //
CREATE PROCEDURE deltable_40() 
BEGIN 
DECLARE rn1 int default 0;
DECLARE rn2 int default 0;
DECLARE rn3 int default 0;
DECLARE rn4 int default 0;
DECLARE rn5 int default 0;
DECLARE rn6 int default 0;

-- DELETE majiang_game_record_sub
SELECT count(*) into rn1 FROM majiang_game_record_sub where date<now()-interval 40 day;
WHILE (rn1 >=0) DO
DELETE FROM majiang_game_record_sub where date<now()-interval 40 day limit 10000; 
SET rn1=rn1-10000; 
commit;
END WHILE;

-- DELETE majiang_game_record
SELECT count(*) into rn2 FROM majiang_game_record where date<now()-interval 40 day;
WHILE (rn2 >=0) DO
DELETE FROM majiang_game_record where date<now()-interval 40 day limit 10000; 
SET rn2=rn2-10000; 
commit;
END WHILE;

-- DELETE gold_log
SELECT count(*) into rn3 FROM gold_log where date<now()-interval 180 day;
WHILE (rn3 >=0) DO
DELETE FROM gold_log where date<now()-interval 180 day limit 10000; 
SET rn3=rn3-10000; 
commit;
END WHILE;

-- DELETE ipaddress_use_log
SELECT count(*) into rn4 FROM gd_majiang.ipaddress_use_log where usetime<now()-interval 60 day;
WHILE (rn4 >=0) DO
DELETE FROM gd_majiang.ipaddress_use_log where usetime<now()-interval 60 day limit 10000; 
SET rn4=rn4-10000; 
commit;
END WHILE;

-- DELETE player_record
SELECT count(*) into rn5 FROM player_record where FROM_UNIXTIME(timestamp,'%Y-%m-%d %H:%m:%s')<now()-interval 40 day;
WHILE (rn5 >=0) DO
DELETE FROM player_record where FROM_UNIXTIME(timestamp,'%Y-%m-%d %H:%m:%s')<now()-interval 40 day limit 10000; 
SET rn5=rn5-10000; 
commit;
END WHILE;

-- DELETE room_record
SELECT count(*) into rn6 FROM room_record where FROM_UNIXTIME(timestamp,'%Y-%m-%d %H:%m:%s')<now()-interval 40 day;
WHILE (rn6 >=0) DO
DELETE FROM room_record where FROM_UNIXTIME(timestamp,'%Y-%m-%d %H:%m:%s')<now()-interval 40 day limit 10000; 
SET rn6=rn6-10000; 
commit;
END WHILE;

END 
//
delimiter ;