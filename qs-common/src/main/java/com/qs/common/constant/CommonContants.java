package com.qs.common.constant;

/**
 * 常量
 * @author moyousheng
 *
 */
public interface CommonContants{
	
	
	String  SUCCESS = "success";
	String  ERROR = "error";
	String  DATA = "data";
	String  MESSAGE = "message";
	String  PAGE = "page";
	
	String  ADD_SUCCESS = "添加成功";
	String  ADD_FAILURE = "添加失败";
	
	String  EDIT_SUCCESS = "编辑成功";
	String  EDIT_FAILURE = "编辑失败";
	
	
	//String  DELETESUCCESS = "删除成功";
	//String  DELETFAILURE = "删除失败";
	
	String  OPERATE_SUCCESS = "操作成功";
	String  OPERATE_FAILURE = "操作失败";

	String UPDATE_SUCCESS = "更新成功";
	String UPDATE_FAILURE = "更新失败";

	String DELETE_SUCCESS = "删除成功";
	String DELETE_FAILURE = "删除失败";

	/**
	 * 返回标记
	 */
	String RESULT = "result";

	/**
	 * 错误描述
	 */
	String ERRORMSG = "errorMsg";
	
	String SUCCESSMSG="successMsg";
	
	/**
	 * 错误代码
	 */
	String ERRORCODE = "errorCode";
	
	/**
	 * http 错误代码.
	 */
	String HTTP_CODE = "httpErrorCode";
	

	/**
	 * 代理 1,2,3 级团队级别
	 */
	static final String ONE_TEMPE="1";
	
	static final String TOW_TEMPE="2";
	
	static final String HTREE_TEMPE="3";
	
	/**
	 * 会员类型设置默认为1，为普通会员，2为代理商
	 */
	static final String TYPE_VIP="1";
	
	static final String TYPE_VIP_Agent="2";
	
	//代理开房提示
	static final String OPEN_ROOME_SUCCESS="开房成功！";
	
	static final String OPEN_ROOME_ERROR="开房操作失败！";
	
	static final String OPEN_ROOME_GB="关闭开房！";
	
	//static final String OPEN_SESSION_KEY="ROPEN|";
	
}
