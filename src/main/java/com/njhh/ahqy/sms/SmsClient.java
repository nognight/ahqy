package com.njhh.ahqy.sms;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sms.client.SmsSendClient;
import org.sms.util.SmsReportCallback;
import org.sms.vo.Sms;
import org.sms.vo.SmsReport;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 短信下发测试  需要在现网测试  本地无法连接短信分发服务中心
 * @author pingxl
 *
 */
public class SmsClient {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
    
	private static SmsClient client = null;
	private static AtomicLong idCreate = new AtomicLong(System.currentTimeMillis());//序列号生成器 多线程安全,一个客户端建议用一个实例即可;
	private SmsSendClient smsSendClient = null;
	
	private SmsClient()
	{
		/*
		 * 创建短信发送客户端  该类最好在客户端进程中是单例形式存在。
		 * 创建客户端实例工厂方法 
		 * createClient(String sendHost,int sendPort,int receivePort,SmsReportCallback callback)
		 * sendHost 服务端短信分发中心ip
		 * sendPort 服务端短信分发中心端口
		 * receivePort 短信状态报告回调地址端口（客户端所在进程会监听此端口的状态报告）  如果端口在客户端操作系统被暂用就换个试试
		 * callback 回调类  注意 当且仅当如果客户端需要多个不同的回调处理类时，可以创建多个client实例
		 */
		//目前短信发下服务部署在192.168.100.10上，监听端口为 9001
		try
		{
		 smsSendClient = SmsSendClient.createClient("192.168.100.10", 9001, 9004, new SmsReportCallback(){
			@Override
			//短信状态报告回调方法
			public void callback(SmsReport... reports) {
				for(SmsReport smsReport : reports){
					String smsId = smsReport.getSmsId();//短信唯一标识，和提交短信的smsId对应。
					byte status = smsReport.getStatus();//状态  0 待处理  1处理中  2处理完成  3状态报告反馈回来 
					logger.info("sms report: smsId=" + smsId + ",status=" + status);
					if(status==2){
						//表示该短信已经提交到网关
						byte result = smsReport.getResult();  //结果  0 成功  非0 失败
						String resultMsg = smsReport.getMsg();//消息提示
						logger.info(",result=" + result + ",resultMsg=" + resultMsg);
					}
					else if(status ==3){
						//表示该短信接收到了状态报告  目前短信下发还未返回status==3的情况，不过代码可以先写处理逻辑
						byte result = smsReport.getResult();  //结果  0 成功  非0 失败
						String resultMsg = smsReport.getMsg();//消息提示
						logger.info(",result=" + result + ",resultMsg=" + resultMsg);
					}
				}
			}
		});
			
		smsSendClient.start();//启动客户端  一个SmsSendClient实例只需要启动一次 在发送短信前  必须先启动		
		//idCreate = new AtomicLong(System.currentTimeMillis());//序列号生成器 多线程安全,一个客户端建议用一个实例即可
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static SmsClient getInstance(){
		if (client == null)
		{
			client = new SmsClient();
		}
		return client;
	}
	
	public void sendSms(String phoneNum, String smsContent)
	{
		Sms sms = new Sms();// 创建一个短信实例
		if (idCreate == null) {
			idCreate = new AtomicLong(System.currentTimeMillis());//序列号生成器 多线程安全,一个客户端建议用一个实例即可
		}
		sms.setSmsId("FMCP_" + idCreate.incrementAndGet());   //短信唯一标识  为了和不同客户端区分，建议不同客户端调用发送创建的smsId为：客户端应用前缀+序列号
		sms.setSendPhone(phoneNum);//发送号码
		sms.setMsg(smsContent);//发送内容
		sms.setIsRapidly(Sms.ISREPORT);//是否及时发送        Sms.ISREPORT 及时发送    Sms.NOTRAPIDLY 在短信下发繁忙的时候 会有一定延迟     默认为Sms.NOTRAPIDLY
		sms.setIsReport(Sms.ISREPORT);//是否需要状态报告      Sms.ISREPORT 需要（采用异步回调的机制回调）     Sms.NOTREPORT 不需要             默认为Sms.NOTREPORT
		sms.setIsStorage(Sms.ISSTORAGE);//是否需要服务端记录短信记录 Sms.ISSTORAGE 需要   Sms.NOTSTORAGE 不需要				            默认为Sms.NOTSTORAGE
		smsSendClient.sendSms(sms);  //执行发送   当有状态报告返回的时候  会异步调用callback，根据smsId匹配发送短信状态
		logger.info("send sms: " + phoneNum + "|" + smsContent);;
	}
	
	//当客户端 不需要发送短信 或者 客户端进程需要退出  需要关闭client，不然内部会有线程池运行会导致进程无法退出
	public void closeClient()
	{
		try{
			client = null;
			//idCreate = null;
			if (smsSendClient != null)
			{
			   smsSendClient.close();
			   smsSendClient = null;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		SmsClient.getInstance().sendSms("13312345678", "test");
		try
		{
			Thread.sleep(10*1000);
			SmsClient.getInstance().closeClient();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
