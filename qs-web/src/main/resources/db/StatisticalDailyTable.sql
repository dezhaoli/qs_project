DROP PROCEDURE IF EXISTS StatisticalDailyTable;
DELIMITER //
CREATE PROCEDURE StatisticalDailyTable()
  BEGIN
    DECLARE agent_mid INT DEFAULT 0; -- 自定义变量1
    DECLARE bindPeople INT DEFAULT 0; -- 自定义变量2
    DECLARE payTimes INT DEFAULT 0; -- 自定义变量3
    DECLARE payCount INT DEFAULT 0; -- 自定义变量4
    DECLARE nowDate DATE DEFAULT '0000-00-00'; -- 自定义变量5
    DECLARE DATE_SUB0 DATE DEFAULT '0000-00-00'; -- 自定义变量6
    DECLARE DATE_SUB1 DATE DEFAULT '0000-00-00'; -- 自定义变量7
    DECLARE DATE_SUB_WEEKDAY DATE DEFAULT '0000-00-00'; -- 自定义变量8
    DECLARE done INT DEFAULT FALSE; -- 自定义控制游标循环变量,默认false
    DECLARE My_Cursor CURSOR FOR (SELECT mid FROM zun_gd_majiang.memberagents); -- 定义游标并输入结果集
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE; -- 绑定控制变量到游标,游标循环结束自动转true

    SET nowDate = curdate();
    SET DATE_SUB0 = DATE_SUB(nowDate, INTERVAL 0 DAY);
    SET DATE_SUB1 = DATE_SUB(nowDate, INTERVAL 1 DAY);
    SET DATE_SUB_WEEKDAY = date_sub(nowDate, INTERVAL WEEKDAY(nowDate) DAY);

    OPEN My_Cursor; -- 打开游标
    myLoop: LOOP -- 开始循环体,myLoop为自定义循环名,结束循环时用到
      FETCH My_Cursor
      INTO agent_mid; -- 将游标当前读取行的数据顺序赋予自定义变量12
      IF done
      THEN -- 判断是否继续循环
        LEAVE myLoop; -- 结束循环
      END IF;
      -- 	更新所有一级代理商的code

      SET bindPeople = (SELECT IFNULL(invitetotal, 0) invitetotal
                        FROM taxes_invite
                        WHERE date = DATE_SUB1 AND mid = agent_mid
                        LIMIT 0, 1);

      SET payCount = (
        SELECT COUNT(0) AS total
        FROM
          (
            SELECT mp.ptime
            FROM zun_gd_majiang.memberpayment mp INNER JOIN zun_gd_majiang.memberfides0 mf ON mf.mid = mp.fmid
            WHERE
              from_unixtime(mf.bindtime, '%Y-%m-%d') >= DATE_SUB_WEEKDAY AND mf.invite = agent_mid
              AND mp.parentid = agent_mid AND mp.isagent = 0 AND mp.pstatus = 2
            GROUP BY mp.fmid
          ) a
        WHERE from_unixtime(ptime, '%Y-%m-%d') >= DATE_SUB1 AND from_unixtime(ptime, '%Y-%m-%d') < DATE_SUB0);

      SET payTimes = (
        SELECT count(0) AS total
        FROM majiang_game_record mgr INNER JOIN zun_gd_majiang.memberfides0 mf ON mgr.player1 = mf.mid
        WHERE
          from_unixtime(mf.bindtime, '%Y-%m-%d') >= DATE_SUB_WEEKDAY AND mf.invite = agent_mid
          AND mgr.date >= DATE_SUB1 AND mgr.date < DATE_SUB0);

      IF payCount > 0 OR bindPeople > 0 OR payTimes > 0
      THEN
        INSERT IGNORE INTO taxes_directly_day (mid, bindpeople, paycount, playtimes, date)
        VALUES (agent_mid, bindPeople, payCount, payTimes, DATE_SUB1);
      END IF;
      COMMIT; -- 提交事务
    END LOOP myLoop; -- 结束自定义循环体
    CLOSE My_Cursor; -- 关闭游标

  END; -- 结束存储过程

//
DELIMITER ;