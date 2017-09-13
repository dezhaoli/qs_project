package com.hzw.monitor.mysqlbinlog.web.controller;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * @author gqliu
 * 2016年1月18日
 *
 */

public abstract class BaseController {

	public String getErrors(BindingResult bindingResult) {
		List<FieldError> errors = bindingResult.getFieldErrors();
		StringBuilder sb = new StringBuilder();
		for(FieldError error:errors) {
			sb.append(error.getField()+ error.getDefaultMessage());
		}
		return sb.toString();
	}
	
}
