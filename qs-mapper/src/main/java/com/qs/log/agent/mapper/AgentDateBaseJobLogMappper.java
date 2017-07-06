package com.qs.log.agent.mapper;

import java.util.Map;

/**
 * 
 * @author zyy
 *
 */
public interface AgentDateBaseJobLogMappper {

	/**
	 * 代理商taxes_invite_week每天更新：2：30
	 * @param param
	 * @author:zyy
	 * @time:2017年4月13日
	 */
	void updateTaxesInviteWeekDataBase(Map<String,Object> param);

	/**
	 * @Author:zun.wei , @Date:2017/6/15 10:03
	 * @Description:统计代理商直属的首冲，开房次数
	 * @param param
	 */
	void statisticalDailyTable(Map<String,Object> param);
	
	
	/**
	 * 清空表数据 表：
	 *  room_record
	 *	player_record
	 *	majiang_game_record_sub
	 *	majiang_game_record
	 *	gold_log
	 *	ipaddress_use_log
	 * 执行时间每周一4:00执行，保留数据40数据
	 * @param param
	 * @author:zyy
	 * @time:2017年7月3日
	 */
	void delTable40(Map<String,Object> param);

	/**
	 * @Author:zun.wei , @Date:2017/7/6 19:17
	 * @Description:创建房卡统计
	 */
	void createRoomCardCount();

	/**
	 * @Author:zun.wei , @Date:2017/7/6 19:35
	 * @Description:drop存储过程
	 */
	void dropProcedureRoomCartCount();

	/**
	 * @Author:zun.wei , @Date:2017/7/6 19:41
	 * @Description:创建存储过程
	 * @param param
	 */
	void createRoomCardCountFuntion(Map<String, Object> param);

	/**
	 * @Author:zun.wei , @Date:2017/7/6 19:42
	 * @Description:执行存储过程
	 */
	void executeRoomCardCount();

}
