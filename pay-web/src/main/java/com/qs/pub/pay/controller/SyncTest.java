package com.qs.pub.pay.controller;

import com.qs.log.game.util.ConstantUtil;

public class SyncTest implements Runnable{  
    private int ticket=10;  
    public SyncTest(){       
    }  
    @Override  
    public void run() {  
        for(int i=0;i<20;i++){  
                if(this.ticket>0){  
                    //休眠1s秒中，为了使效果更明显，否则可能出不了效果  
                    try {  
                        Thread.sleep(3000);  
                    } catch (Exception e) {  
                        e.printStackTrace();  
                    }  
                    this.sale();  
                }  
              
        }  
    }  
      
    public  synchronized void sale(){  
        if(this.ticket>0){  
            System.out.println(Thread.currentThread().getName()+"号窗口卖出："+this.ticket--+"号票");  
        }  
    }  
      
     public static void main(String args[]){  
    	 SyncTest demo=new SyncTest();  
         //基于火车票创建三个窗口  
        /* new Thread(demo,"a").start();  
         new Thread(demo,"b").start();  
         new Thread(demo,"c").start();  
         new Thread(demo,"d").start();*/
         int mid=100002;
         for(int i=0;i<10;i++){
        	 System.out.println("i==2===========::"+i);
            if(ConstantUtil.NoPay.RUNFAST_MID.contains(mid+"")){
				continue;
			}
            System.out.println("i=============::"+i);
         
         }
     }  
      
}  