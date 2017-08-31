/*
 * 文件名：SyncKtrTask.java	 
 * 时     间：下午2:52:57
 * 作     者：wangzhen      
 * 版     权：2014-2022  牵手互动, 公司保留所有权利.
 * 
 
package com.qs.pub.sync.task;

import java.io.File;
import java.io.FileFilter;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.exception.KettleXMLException;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

*//**
 * @ClassName: SyncKtrTask
 * @描述: 同步ktr
 * @author qs
 * @date 2017年5月31日 下午2:52:57
 *//*
@Component
@Configuration
@EnableAsync
@EnableScheduling
public class SyncKtrTask
{
	
	@Value("${ktr.basefilepath}")
	private String basefilePath;
	
	*//**
	 * 
	 * @标题: runJob 
	 * @描述:  kettle java调用job
	 *
	 * @参数信息
	 *    @param jobname
	 *
	 * @返回类型 void
	 * @开发者 wangzhen
	 * @可能抛出异常
	 *//*
	public  void runJob() {  
        try {  
        	String jobname = "";
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
	*//**
	 * 
	 * @标题: runTrans 
	 * @描述:  kettle java 调用 Trans
	 *
	 * @参数信息
	 *    @param filename
	 *
	 * @返回类型 void
	 * @开发者 wangzhen
	 * @可能抛出异常
	 *//*
	@Scheduled(cron="0 0/30 * * * ?")
	public  void runTrans()
	{
		try
		{
			//String filePath = "D:/dev/dataintegration_gezila.com";
			String filename = "";
			File file = new File(basefilePath);
			File[] files = file.listFiles(new FileFilter()
			{
				@Override
				public boolean accept(File pathname)
				{
					return pathname.getName().endsWith(".ktr");
				}
			});
			for(File f:files){
				filename = f.getAbsolutePath();
			}
			KettleEnvironment.init();
			TransMeta transMeta = new TransMeta(filename);
			Trans trans = new Trans(transMeta);
			trans.prepareExecution(null);
			trans.startThreads();
			trans.waitUntilFinished();
			if (trans.getErrors() != 0)
			{
				System.out.println("Error");
			}
		} catch (KettleXMLException e)
		{
			e.printStackTrace();
		} catch (KettleException e)
		{
			e.printStackTrace();
		}
	}
}
*/