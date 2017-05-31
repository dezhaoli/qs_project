import java.io.File;
import java.io.FileFilter;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.exception.KettleXMLException;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;

/*
 * 文件名：TestKTR2.java	 
 * 时     间：下午1:41:16
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 */
/** 
 * @ClassName: TestKTR2 
 * @描述: (这里用一句话描述这个类的作用) 
 * @author qs
 * @date 2017年5月31日 下午1:41:16 
 */
public class KettleExecu {  
  
    public static void runJob(String jobname) {  
        try {  
            KettleEnvironment.init();  
            // jobname 是Job脚本的路径及名称  
            JobMeta jobMeta = new JobMeta(jobname, null);  
            Job job = new Job(null, jobMeta);  
            // 向Job 脚本传递参数，脚本中获取参数值：${参数名}  
            // job.setVariable(paraname, paravalue);  
            job.start();  
            job.waitUntilFinished();  
            if (job.getErrors() > 0) {  
                System.out.println("decompress fail!");  
            }  
        } catch (KettleException e) {  
            System.out.println(e);  
        }  
    }  
  
    // 调用Transformation示例：  
    public static void runTrans(String filename) {  
        try {  
            KettleEnvironment.init();  
            TransMeta transMeta = new TransMeta(filename);  
            Trans trans = new Trans(transMeta);  
            trans.prepareExecution(null);  
            trans.startThreads();  
            trans.waitUntilFinished();  
            if (trans.getErrors() != 0) {  
                System.out.println("Error");  
            }  
        } catch (KettleXMLException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (KettleException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
  
    public static void main(String[] args) {  
       /* String jobname = "F:\\ETL\\kettle\\例子\\测试\\复制多表\\copymanytablejob.kjb";  
        runJob(jobname);  */
    	File file = new File("D:/dev/dataintegration_gezila.com");
    	String path = "";
		File[] files = file.listFiles(new FileFilter()
		{
			@Override
			public boolean accept(File pathname)
			{
				return pathname.getName().endsWith(".ktr");
			}
		});
		for(File f:files){
			 path = f.getAbsolutePath();
		} 
         runTrans(path);  
    }  
  
}  