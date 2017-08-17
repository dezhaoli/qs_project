import com.qs.common.util.HttpClientUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
 * 文件名：Test.java	 
 * 时     间：下午4:38:20
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
/** 
 * @ClassName: Test 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年5月17日 下午4:38:20 
 */
public class Test
{
	private static final String key = "Fk$M$EbTAZqdK!BV";
	public static void main(String[] args)
	{
		/*Map map = new HashMap();
		map.put("logType", "3");
		map.put("mid", "555");
		map.put("appId", "106");
		map.put("loginTime", "");
		map.put("ip", "www.baidu.com");
		map.put("lgtm", "");
		map.put("mtime", "");
		map.put("extend1", "长安县");
		map.put("extend2", "太乙镇");*/

		Map map = new HashMap();

		map.put("logType", "5");
		map.put("mid", "3234");
		map.put("appId", "107");
		map.put("createRoomTime",new Date());
		map.put("playId", "222");
		map.put("playName", "贵阳玩法2");
		map.put("playNum", "1");
		map.put("gold", "1");


		/*map.put("logType", "4");
		map.put("mid", "666");
		map.put("appId", "106");
		map.put("loginTime", "");
		map.put("ip", "www.baidu.com");
		map.put("lgtm", "");
		map.put("mtime", "");
		map.put("gameStartTime", "");
		map.put("gameStopTime", "");
		map.put("extend1", "长安县");
		map.put("extend2", "太乙镇");*/
		
		/*map.put("login_time", new Date());
		map.put("logout_time", new Date());*/
		//String params = JSON.toJSONString(map);
		//http://gcadmin.jiaheyx.com/datacenter/dataCenter/dataCenterLogDispatch.html
		//http://192.168.1.92:8080/datacenter/dataCenter/dataCenterLogDispatch.html
		//http://datacenter.longzupoker.com:8888/datacenter/dataCenter/dataCenterLogDispatch.html
		//http://192.168.1.92:8080/sync-producer/dataCenter/dataCenterLogDispatch.html
		try{
		String res = "";
		for(int i =0;i<1;i++){
			 res = HttpClientUtil.httpClientByPost("http://datacenter.longzupoker.com:8888/datacenter/dataCenter/dataCenterLogDispatch.html",map);
		}
		System.out.println(res);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
