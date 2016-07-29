package com.wopaitv.interfacetest;

import java.io.IOException;
import java.net.InetAddress;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class Test2 {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		
		//创建HttpClient对象
		CloseableHttpClient httpClient=HttpClients.createDefault();
		//如果发送是GET请求，创建HttpGet对象
		HttpGet httpget = new HttpGet("http://www.baidu.com/"); 
		//执行GET请求
		CloseableHttpResponse response =  httpClient.execute(httpget); 
//		==============================================================
//		Header headers[] = response.getAllHeaders();	
//		Header Serverheaders[] = response.getHeaders("Server");
//
//    	for (Header header : Serverheaders) {
//            //响应头信息名称
//                 System.out.println(header.getName());       
//              //头信息对应的值
//                 System.out.println(header.getValue());
//		} 
//    	//获取响应结果状态码
//    	System.out.println(response.getStatusLine().getStatusCode());
////    	根据主机名获取其可能的所有实际ip地址 
//    	InetAddress[] address = InetAddress.getAllByName("www.baidu.com");
//        for (int i = 0; i < address.length; i++) {
//               System.out.println(address[i]);
//        }	
//		==========================
//        调用HttpResponse的getEntity()方法可获取HttpEntity对象,服务器的响应内容
        HttpEntity httpenEntity=response.getEntity();
        String xml2 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><users><password>123456</password><username>xxx</username></users>";

        String xml = EntityUtils.toString(httpenEntity);
        String xml1="\""+xml+"\"";
//        System.out.println(xml1);
        XMLSerializer xmlSerializer = new XMLSerializer();
        //使用xmlSerializer.read()方法可以将xml 格式的数据转换成json格式数据
         JSON  json = xmlSerializer.read(xml2);
         System.out.println(json);
//         JSONObject jsonObj = JSONObject.fromObject(json);
//         System.out.print(jsonObj.getString("password"));
         
         
         String  res="{\"returncode\": 0,\"message\": \"\", \"count\": 2,\"result\": { \"users\": [{  \"pwd\": \"123456\",  \"name\": \"xxx1\" },{ \"pwd\": \"123456\", \"name\": \"xxx2\"  } ]}}";

       //将json字符串转换为JSONObject对象 
         JSONObject jsonObj = JSONObject.fromObject(res);
         // result是一个json 对象，使用getJSONObject()来获取
         JSONObject resultobj = jsonObj.getJSONObject("result");
         // users是数组对象的话可以使用getJSONArray()来获取一个json数组
         JSONArray userlist = resultobj. getJSONArray("users") ;
         //可以循环获取数组中的对象元素
         for (Object object : userlist  ) {
            JSONObject user= (JSONObject) object;
           
           System.out.println(user.getString("pwd")); 
            System.out.println(user.getString("name"));
         }

		//释放连接
		response.close();
		httpClient.close();

	}

}
