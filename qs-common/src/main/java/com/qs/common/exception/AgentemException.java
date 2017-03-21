package com.qs.common.exception;

/**
 * 
 * @ClassName: SystemException
 * @Description: 系统异常,处理非ajax请求的Exception
 * @author gaogang
 * @date 2016年7月12日 下午3:20:18
 *
 */
public class AgentemException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2119718747432466934L;

	public AgentemException() {
		super();
	}

	public AgentemException(String message) {
		super(message);
	}

	public AgentemException(Throwable cause) {
		super(cause);
	}

	public AgentemException(String message, Throwable cause) {
		super(message, cause);
	}

	public AgentemException(String message, Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace) {
    	super(message, cause, enableSuppression, writableStackTrace);
    }
}
