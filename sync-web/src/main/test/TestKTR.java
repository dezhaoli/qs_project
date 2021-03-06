import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.util.EnvUtil;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;

public class TestKTR
{
	public static void callNativeTransWithParams(String[] params ,String transFileName) throws Exception{
        // 初始化 
      KettleEnvironment.init();
      EnvUtil.environmentInit(); 
      TransMeta transMeta = new TransMeta(transFileName);
      //转换
      Trans trans = new Trans(transMeta); 
      //执行
      trans.execute(params);
      //等待结束
      trans.waitUntilFinished();
      //抛出异常 
     if(trans.getErrors() > 0){ 
         throw new Exception("There are errors during transformation exception!(传输过程中发生异常)"); 
     } 
  }
	public static void main(String arg[]) throws Exception
	{
		
		
	}
}
