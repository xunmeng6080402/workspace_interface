package com.wopaitv.interfacetest;

import java.io.IOException;
import java.net.InetAddress;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

public class Demo1 {

	public static void get() throws ClientProtocolException, IOException{
		
		//创建HttpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();	 
		//如果发送是GET请求，创建HttpGet对象
		HttpGet httpget = new HttpGet("http://www.baidu.com/");  
		
		//可以设置请求和传输超时时间
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();
		httpget.setConfig(requestConfig);		
		//执行GET请求
		CloseableHttpResponse response =  httpClient.execute(httpget);	    
		//获取文件头信息
	    Header headers[] = response.getAllHeaders();	    
    	for (Header header : headers) {
    		System.out.println(header.getName()+"----"+header.getValue()+"  ");
		}     
    	//获取服务器返回状态码，如果等于200就说明请求和响应都成功了:
    	System.out.println(response.getStatusLine().getStatusCode());		    
    	//调用HttpResponse的getEntity()方法可获取HttpEntity对象,获取服务器的响应内容
    	String rteurnStr = EntityUtils.toString(response.getEntity());	
    	System.out.println(rteurnStr);
    	
    	InetAddress[] address = InetAddress.getAllByName("www.baidu.com");
		  	for (int i = 0; i < address.length; i++) {
		  		System.out.println(address[i]);
		  	}		  	
		  	
		  //释放连接
		  response.close();
		  httpClient.close();
	}
	
	
	public static void post() throws ClientProtocolException, IOException{
		
		CloseableHttpClient httpClient = HttpClients.createDefault();	  
		HttpPost httppost = new HttpPost("http://192.168.225.130:80/applogin/login");
	
		//设置请求和传输超时时间
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();//设置请求和传输超时时间	
		httppost.setConfig(requestConfig);		
		
		//post请求参数配置
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			formparams.add(new BasicNameValuePair("name", "zhangsan"));
			formparams.add(new BasicNameValuePair("pwd", "95e83a45125010241f08b4a98c1bea1a"));
			formparams.add(new BasicNameValuePair("deviceToken", "866654021464968"));	
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");   //设置编码格式为utf-8
			
		//设置POST请求参数
		httppost.setEntity(uefEntity);	
		
		//使用httpclient的execute方法发送接口请求
		CloseableHttpResponse response =  httpClient.execute(httppost);	
	    
		//获取服务器所有响应头信息
	    Header headers[] = response.getAllHeaders();
	    
    	for (Header header : headers) {
    		System.out.println(header.getName()+"----"+header.getValue()+"  ");
		}    	
	    
    	//获取服务器指定名称的响应头信息
    	Header Serverheaders[] = response.getHeaders("Server");
    	
    	//获取服务器返回状态码，如果等于200就说明请求和响应都成功了:
    	System.out.println(response.getStatusLine().getStatusCode());	
    	
    	//调用HttpResponse的getEntity()方法可获取HttpEntity对象,服务器的响应内容
    	String jsonStr = EntityUtils.toString(response.getEntity());	
    	System.out.println(jsonStr);
    	
    	//根据主机名获取其可能的所有InetAddress对象 
    	InetAddress[] address = InetAddress.getAllByName("account.autohome.com.cn");
		  	for (int i = 0; i < address.length; i++) {
		  		System.out.println(address[i]);
		  	}
		  	
		  	//释放连接
		  	response.close();
		  	httpClient.close();
	}
	
	
	
	public static void httpsClientDefault() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, ClientProtocolException, IOException{
	

		  SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
		         //信任所有
		         public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		                     return true;
		                     }}).build();

		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
	    CloseableHttpClient httpClient  = HttpClients.custom().setSSLSocketFactory(sslsf).build();

	    HttpGet  httpget = new HttpGet("https://testerhome.com/");      
	    
	    //可以设置请求和传输超时时间
	  		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();
	  		httpget.setConfig(requestConfig);		
	  		//执行GET请求
	  		CloseableHttpResponse response =  httpClient.execute(httpget);	    
	  		//获取文件头信息
	  	    Header headers[] = response.getAllHeaders();	    
	      	for (Header header : headers) {
	      		System.out.println(header.getName()+"----"+header.getValue()+"  ");
	  		}     
	  	   
	      	System.out.println(response.getStatusLine().getStatusCode());		    
	      	//获取json串 
	      	String jsonStr = EntityUtils.toString(response.getEntity());	
	      	System.out.println(jsonStr);
	      	
	      	InetAddress[] address = InetAddress.getAllByName("testerhome.com");
	  		  	for (int i = 0; i < address.length; i++) {
	  		  		System.out.println(address[i]);
	  		  	}
	  		  	
	  		  	
	  		  //释放连接
	  		  response.close();
	  		  httpClient.close();

		}
	
	
	public static void postjson() throws ClientProtocolException, IOException{
		
		CloseableHttpClient httpClient = HttpClients.createDefault();	  
		HttpPost httppost = new HttpPost("http://192.168.225.130:80/rcm/autoappfind/lands");  
		
		httppost.setHeader("Content-type","application/json;charset=utf-8");
		httppost.setHeader("Accept","application/json");		
		//设置POST请求参数
		httppost.setEntity(new  StringEntity("{\"ReqType\":\"0\", \"OsType\":\"1\", \"Uid\":\"126432\", \"DeviceId\":\"9FC243C2FA388C151E0C8BAA1CCEFEF0\", \"GPS\":\"32.34323423,42.43243243\", \"CityId\":\"340100\", \"CouponModule\":{\"ShowIndex\":\"1\", \"Num\":\"3\", \"OpList\":[{\"Pos\":\"2\", \"ItemId\":\"444\"}]}, \"AutoModule\":{\"ShowIndex\":\"2\", \"Num\":\"2\", \"OpList\":[{\"Pos\":\"2\", \"ItemId\":\"666\"}]}, \"ProdModule\":{\"ShowIndex\":\"3\", \"PageNum\":\"1\", \"PageSize\":\"10\", \"PageConf\":[{\"ProdType\":\"1\", \"Num\":\"4\"}, {\"ProdType\":\"2\", \"Num\":\"0000\"}, {\"ProdType\":\"3\", \"Num\":\"6\"}]}}"));	
		
		//使用httpclient的execute方法发送接口请求
		CloseableHttpResponse response =  httpClient.execute(httppost);	
		String rteurnStr = EntityUtils.toString(response.getEntity());	
    	System.out.println(rteurnStr);
    
		  	
		  	//释放连接
		  	response.close();
		  	httpClient.close();
		
	}
	
	
	public static void main(String[] args) throws ClientProtocolException, IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException{

//		get();
//		post();
//		httpsClientDefault();
//		postjson();
		
	}


}

