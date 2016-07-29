package com.wopaitv.interfacetest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import com.wopaitv.interfacetest.Test4;

public class Test3 extends AbstractJavaSamplerClient{
	 
	   public static String label="login";
//	   public String name; 
//	   private String password;
//	   private String response_type;
//	   private String client_id;
	   public String res=null;
		public void setupTest(JavaSamplerContext arg0){
			System.out.print("haa");
		}

		public void teardownTest(JavaSamplerContext arg0) {
			
		} 
	
		@Override
		public SampleResult runTest(JavaSamplerContext arg0) {
//			name=arg0.getParameter("name");
//			password=arg0.getParameter("password");
//			client_id=arg0.getParameter("client_id");
//			response_type=arg0.getParameter("response_type");
			System.out.print("执行前");

			SampleResult sr=new SampleResult();
			sr.setSampleLabel(label);
			sr.sampleStart();//用来统计执行时间--start--
//			sr.setSampleLabel(label);
			Test4 t4=new Test4();
			
			try {
				
				res=t4.test();
				System.out.print(res+"ggggggg");
			} catch (ClientProtocolException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.print("执行后");
			
			
			try{             
			//记录程序执行时间，以及执行结果           
			
			sr.setResponseMessage(res);
			sr.setSuccessful(true);   
			
			System.out.println("send over!");      }
			catch(Throwable e){           
				sr.setSuccessful(false);     
			}finally{  
				
				sr.sampleEnd();      
				}
			
		
			
			return sr;
		}
//		public Arguments getDefaultParameters() {    
//			Arguments params = new Arguments();    
//			params.addArgument("response_type","code");     
//			params.addArgument("client_id","c1ebe466-1cdc-4bd3-ab69-77c3561b9dee");   
//			params.addArgument("username", "15010707894"); 
//			params.addArgument("password", "21218cca77804d2ba1922c33e0151105"); 
//			return params; 
//			}
}