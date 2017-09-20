package com.qs.pub.sync.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qs.pub.sync.service.LogErrorService;
import com.qs.sync.model.LogError;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 错误日志
 * @author moyousheng
 *
 */
@Controller
@RequestMapping("/sync/error")
public class LogErrorController {

    @Autowired
    private LogErrorService logErrorService;

/*    @ResponseBody 
    @RequestMapping("/lst.do")
    public Pagination<LogError> lst(@RequestBody()LogErrorQuery query) {
        return logErrorService.query(query);
    }
      
    @ResponseBody
    @RequestMapping("/query.do")
    public Pagination<LogError> query(@RequestBody()LogErrorQuery query) {
        return logErrorService.query(query);
    }*/

    @ResponseBody
    @RequestMapping("/find.do")
    public LogError find(String id) {
        return logErrorService.find(id);
    }

    @ResponseBody
    @RequestMapping("/save.do")
    public LogError save(@RequestBody()LogError logError) {
        return logErrorService.save(logError);
    }

    @ResponseBody
    @RequestMapping("/update.do")
    public void update(@RequestBody()LogError logError) {
    	logErrorService.update(logError);
    }


    @ResponseBody
    @RequestMapping("/remove.do")
    public void remove(String id) {
    	logErrorService.remove(id);
    }



}
