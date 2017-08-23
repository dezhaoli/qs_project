package com.qs.webside.query.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.constant.Constants;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.util.PageUtil;
import com.qs.log.game.model.RoomRecord;
import com.qs.log.game.service.IPlayerRecordService;
import com.qs.log.game.service.IRoomRecordService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * //@Author:zun.wei, @Date:2017/4/17 11:16
 * 牌局查询
 * Created by zun.wei on 2017/4/17.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Controller
@RequestMapping(value = "/query/")
public class CardRecordController extends BaseController {

    //   /query/cardRecordUi.html

    @Resource
    private IPlayerRecordService playerRecordService;

    @Resource
    private IRoomRecordService roomRecordService;
    
    
    @Value("${game.gametype}")
    private String gameType;

    @RequestMapping(value = "cardRecordUi.html", method = RequestMethod.GET)
    public String cardRecordUi(Model model, HttpServletRequest request) {
        PageUtil page = super.getPage(request);
        model.addAttribute("page", page);
        return "WEB-INF/view/web/query/cardRecord_query_list";
    }

    /**
     * //@Author:zun.wei, @Date:2017/4/17 19:14
     * 订单明细
     * @param gridPager
     * @return
     * @throws Exception
     */
    @RequestMapping("cardRecord.html")
    @ResponseBody
    public Object cardRecord(String gridPager) throws Exception {
        Map<String, Object> parameters = null;
        // 映射Pager对象
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        // 判断是否包含自定义参数
        parameters = pager.getParameters();
        if (parameters.size() < 0) {
            parameters.put("name", null);
        }
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        List<Map<String,Object>> list = playerRecordService.queryCardRecordByPage(parameters);
        return getReturnPage(pager, page, list);
    }

    /**
     * @Author:zun.wei , @Date:2017/5/22 16:17
     * @Description:根据房间编号查询房间详情
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "roomDetailUi.html", method = RequestMethod.GET)
    //@ResponseBody
    public String roomDetailUi(Model model, HttpServletRequest request) {
        String uuid = request.getParameter("uuid");
        RoomRecord roomRecord = roomRecordService.queryByUuid(uuid);
        Map<String, Object> resultMap = new  HashMap<String, Object>();
        StringBuilder  content = new StringBuilder ();
   	  
        if (roomRecord != null) {
            String info = roomRecord.getInfo(); 
            
            JSONArray jsonArray = JSONArray.parseArray(info);
            Object [] arrList = jsonArray.toArray();
            for(Object myList:arrList){
              System.out.println("myList=====::"+myList);
              JSONArray subJsonArray = JSONArray.parseArray(myList.toString());
              Object [] subArrList = subJsonArray.toArray();
              
             if(Constants.GameType.KX_BEARD.equals(gameType)){  //开心跑胡子
                if("0".equals(subArrList[0].toString())){ //游戏开始
            	  String start="<span style='color: indigo;font-weight: bold'>游戏开始:</span><br/>";
            	  content.append(start);
            	  content.append("庄家座位：").append(subArrList[1].toString()).append("<br>");
            	  content.append("亮张的牌：").append(subArrList[2].toString()).append("<br>");
            	  for(int i=3;i<subArrList.length;i++){
            		  JSONArray seatJsonArray = JSONArray.parseArray(subArrList[i].toString());
                      Object [] seatArrList = seatJsonArray.toArray();
                      content.append("玩家坐位：").append(seatArrList[0].toString()).append(" ");
                      content.append("MID为: ").append(seatArrList[1].toString()).append(" ");
                      content.append("积分:").append(seatArrList[2].toString()).append(" ");
                      content.append("金币:").append(seatArrList[3].toString()).append(" ");
                      content.append("手牌:").append(seatArrList[4].toString()).append("<br>");
            		  
            	  }
            	  content.append("<br>");
            	  
              }else if("1".equals(subArrList[0].toString())){ //牌局轮次切换
            	  String start="<span style='color: indigo;font-weight: bold'>牌局轮次切换:</span><br/>";
            	  content.append(start);
            	  content.append("座位号:").append(subArrList[1].toString()).append("<br>");
            	   String opType="";
            	   if("1".equals(subArrList[2].toString())){
            		   opType="吃";
            	   }else if("2".equals(subArrList[2].toString())){
            		   opType="碰";
            	   }else if("3".equals(subArrList[2].toString())){
            		   opType="跑";
            	   }else if("6".equals(subArrList[2].toString())){
            		   opType="偎";
            	   }else if("8".equals(subArrList[2].toString())){
            		   opType="提";
            	   }else if("9".equals(subArrList[2].toString())){
            		   opType="胡";
            	   }else if("10".equals(subArrList[2].toString())){
            		   opType="过牌";
            	   }else if("11".equals(subArrList[2].toString())){
            		   opType="出牌";
            	   }else if("12".equals(subArrList[2].toString())){
            		   opType="摸牌";
            	   }else if("15".equals(subArrList[2].toString())){
            		   opType="报警";
            	   }
            	  content.append("操作:").append(opType).append("<br>");
            	  content.append("出牌:").append(subArrList[3].toString()).append("<br>");
            	  
              }else if("2".equals(subArrList[0].toString())){ //可以做的操作
            	  String start="<span style='color: indigo;font-weight: bold'>牌局内玩家操作:</span><br/>";
            	  content.append(start);
            	  content.append("操作的座位号:").append(subArrList[1].toString()).append("<br>");
            	  content.append("操作:");
            	  String mycontent[]= subArrList[2].toString().split(",");
            	  for(int i=0;i<mycontent.length;i++){
            		  String allowAction="";
               	   if("0".equals(mycontent[i])){
               		   allowAction="胡";
               	   }else if("1".equals(mycontent[i])){
               		   allowAction="碰";
               	   }else if("2".equals(mycontent[i])){
               		   allowAction="吃牌";
               	   }else if("3".equals(mycontent[i])){
               		   allowAction="报警";
               	   }
            		  content.append(allowAction).append(" ");
            	  }
                  content.append("<br>");
            	  
              }else if("3".equals(subArrList[0].toString())){ //牌局结束
            	  String start="<span style='color: indigo;font-weight: bold'>牌局结束:</span><br/>";
            	  content.append(start);
            	  content.append("赢家座位：").append(subArrList[1].toString()).append("<br>");
            	  for(int i=2;i<subArrList.length;i++){
            		  
            		  JSONArray seatJsonArray = JSONArray.parseArray(subArrList[i].toString());
                      Object [] seatArrList = seatJsonArray.toArray();
                      
                      content.append("玩家坐位：").append(seatArrList[0].toString()).append(" ");
                      content.append("本局所赢积分:").append(seatArrList[1].toString()).append(" ");
                      if(null!=seatArrList&&seatArrList.length>2){
                       content.append("本房间所赢积分:").append(seatArrList[2].toString()).append(" ");
                      }
                     if(null!=seatArrList&&seatArrList.length>3){
                       content.append("总积分:").append(seatArrList[3].toString()).append("<br>");
                     }
            		  
            	  }
            	  content.append("<br>");
            	  
              }
             }else{  //麻将类型
            	 
                 if("0".equals(subArrList[0].toString())){ //游戏开始
               	   String start="<span style='color: indigo;font-weight: bold'>游戏开始:</span><br/>";
               	   content.append(start); 
         		   Map mymap = (Map)JSON.parse(subArrList[1].toString());
         		   content.append("房间ID：").append(mymap.get("room")).append("<br/>");
         		   content.append("房主MID：").append(mymap.get("roomowner")).append("<br/>");
         		   content.append("庄家座位号：").append(mymap.get("banker")).append("<br/>");
         		   content.append("房间人数：").append(mymap.get("players")).append("<br/>");
         		   content.append("房间局数：").append(mymap.get("ju")).append("<br/>");
         		   content.append("已玩局数：").append(mymap.get("playednum")).append("<br/>");
         		   
         		  content.append("房间类型：").append(mymap.get("type")).append("<br/>");
         		  content.append("玩法：").append(mymap.get("playtype")).append("<br/>");
         		  content.append("房间选项：").append(mymap.get("operation")).append("<br/>");
         		  content.append("扎码个数：").append(mymap.get("zhama")).append("<br/>");
         		  for(int i=2;i<subArrList.length;i++){
                      Map seatMap = (Map)JSON.parse(subArrList[i].toString());
                      content.append("玩家坐位：").append(seatMap.get("seatno")).append(" ");
                      content.append("MID为: ").append(seatMap.get("mid")).append(" ");
                      content.append("积分:").append(seatMap.get("score")).append(" ");
                      content.append("手牌:").append(seatMap.get("cards")).append("<br>");
            		  
            	  }
         		 content.append("<br>");
         		  
                 }else if("1".equals(subArrList[0].toString())){ //牌局轮次切换
               	   String start="<span style='color: indigo;font-weight: bold'>牌局轮次切换:</span><br/>";
               	   content.append(start); 
            	   content.append("座位号:").append(subArrList[1].toString()).append("<br>");
            	   String opType="";
            	   if("100".equals(subArrList[2].toString())){
            		   opType="出牌";
            	   }else if("101".equals(subArrList[2].toString())){
            		   opType="碰";
            	   }else if("102".equals(subArrList[2].toString())){
            		   opType="杠";
            	   }else if("103".equals(subArrList[2].toString())){
            		   opType="胡";
            	   }else if("104".equals(subArrList[2].toString())){
            		   opType="过";
            	   }else if("105".equals(subArrList[2].toString())){
            		   opType="摸牌";
            	   }
            	   content.append("操作:").append(opType).append("<br>");
            	   content.append("出牌:").append(subArrList[3].toString()).append("<br>");
            	   content.append("<br>");
                 }else if("2".equals(subArrList[0].toString())){ //可以做的操作
                    String start="<span style='color: indigo;font-weight: bold'>牌局结束:</span><br/>";
               	    content.append(start); 
               	    Map mymap = (Map)JSON.parse(subArrList[1].toString());
       		        content.append("赢家MID：").append(mymap.get("mid")).append("<br/>");
       		        content.append("赢家座位：").append(mymap.get("seatno")).append("<br/>");
       		        content.append("赢家本局所得积分：").append(mymap.get("score")).append("<br/><br/>");
       		        for(int i=2;i<subArrList.length;i++){
                     Map seatMap = (Map)JSON.parse(subArrList[i].toString());
                     content.append("玩家MID: ").append(seatMap.get("mid")).append(" ");
                     content.append("玩家座位:").append(seatMap.get("seatno")).append(" ");
                     content.append("玩家本局所得积分:").append(seatMap.get("score")).append("<br>");
           	 	  
           	       }
       		       content.append("<br>");
                 }
             }
            	
            }
            
            model.addAttribute("info", content.toString());
            model.addAttribute("info2", info);
        }
        //return content.toString();
        return "WEB-INF/view/web/query/roomDetail_query_list";
    }



    /**
     * @Author:zun.wei , @Date:2017/5/22 16:27
     * @Description:格式化json
     * @param jsonStr
     * @return
     */
    private static String formatJson(String jsonStr) {
        if (null == jsonStr || "".equals(jsonStr))
            return "";
        StringBuilder sb = new StringBuilder();
        char last = '\0';
        char current = '\0';
        int indent = 0;
        boolean isInQuotationMarks = false;
        for (int i = 0; i < jsonStr.length(); i++) {
            last = current;
            current = jsonStr.charAt(i);
            switch (current) {
                case '"':
                    if (last != '\\'){
                        isInQuotationMarks = !isInQuotationMarks;
                    }
                    sb.append(current);
                    break;
                case '{':
                case '[':
                    sb.append(current);
                    if (!isInQuotationMarks) {
                        sb.append('\n');
                        indent++;
                        addIndentBlank(sb, indent);
                    }
                    break;
                case '}':
                case ']':
                    if (!isInQuotationMarks) {
                        sb.append('\n');
                        indent--;
                        addIndentBlank(sb, indent);
                    }
                    sb.append(current);
                    break;
                case ',':
                    sb.append(current);
                    if (last != '\\' && !isInQuotationMarks) {
                        sb.append('\n');
                        addIndentBlank(sb, indent);
                    }
                    break;
                default:
                    sb.append(current);
            }
        }

        return sb.toString();
    }

    /**
     * @Author:zun.wei , @Date:2017/5/22 16:27
     * @Description:添加space
     * @param sb
     * @param indent
     */
    private static void addIndentBlank(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append('\t');
        }
    }

}
