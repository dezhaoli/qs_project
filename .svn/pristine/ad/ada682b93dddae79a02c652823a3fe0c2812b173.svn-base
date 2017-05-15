package com.qs.sync.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qs.sync.model.LogSuccess;
import com.qs.sync.service.LogSuccessService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 成功日志
 * @author moyousheng
 *
 */
@Controller
@RequestMapping("/sync/success")
public class LogSuccessController {

    @Autowired
    private LogSuccessService logSuccessService;

/*    @ResponseBody 
    @RequestMapping("/lst.do")
    public Pagination<LogSuccess> lst(@RequestBody()LogSuccessQuery query) {
        return logSuccessService.query(query);
    }
      
    @ResponseBody
    @RequestMapping("/query.do")
    public Pagination<LogSuccess> query(@RequestBody()LogSuccessQuery query) {
        return logSuccessService.query(query);
    }*/

    @ResponseBody
    @RequestMapping("/find.do")
    public LogSuccess find(String id) {
        return logSuccessService.find(id);
    }

    @ResponseBody
    @RequestMapping("/save.do")
    public LogSuccess save(@RequestBody()LogSuccess logSuccess) {
        return logSuccessService.save(logSuccess);
    }

    @ResponseBody
    @RequestMapping("/update.do")
    public void update(@RequestBody()LogSuccess logSuccess) {
    	logSuccessService.update(logSuccess);
    }


    @ResponseBody
    @RequestMapping("/remove.do")
    public void remove(String id) {
    	logSuccessService.remove(id);
    }



}
