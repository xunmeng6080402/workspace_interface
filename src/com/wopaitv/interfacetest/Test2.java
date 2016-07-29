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
		
		//����HttpClient����
		CloseableHttpClient httpClient=HttpClients.createDefault();
		//���������GET���󣬴���HttpGet����
		HttpGet httpget = new HttpGet("http://www.baidu.com/"); 
		//ִ��GET����
		CloseableHttpResponse response =  httpClient.execute(httpget); 
//		==============================================================
//		Header headers[] = response.getAllHeaders();	
//		Header Serverheaders[] = response.getHeaders("Server");
//
//    	for (Header header : Serverheaders) {
//            //��Ӧͷ��Ϣ����
//                 System.out.println(header.getName());       
//              //ͷ��Ϣ��Ӧ��ֵ
//                 System.out.println(header.getValue());
//		} 
//    	//��ȡ��Ӧ���״̬��
//    	System.out.println(response.getStatusLine().getStatusCode());
////    	������������ȡ����ܵ�����ʵ��ip��ַ 
//    	InetAddress[] address = InetAddress.getAllByName("www.baidu.com");
//        for (int i = 0; i < address.length; i++) {
//               System.out.println(address[i]);
//        }	
//		==========================
//        ����HttpResponse��getEntity()�����ɻ�ȡHttpEntity����,����������Ӧ����
        HttpEntity httpenEntity=response.getEntity();
        String xml2 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><users><password>123456</password><username>xxx</username></users>";

        String xml = EntityUtils.toString(httpenEntity);
        String xml1="\""+xml+"\"";
//        System.out.println(xml1);
        XMLSerializer xmlSerializer = new XMLSerializer();
        //ʹ��xmlSerializer.read()�������Խ�xml ��ʽ������ת����json��ʽ����
         JSON  json = xmlSerializer.read(xml2);
         System.out.println(json);
//         JSONObject jsonObj = JSONObject.fromObject(json);
//         System.out.print(jsonObj.getString("password"));
         
         
         String  res="{\"returncode\": 0,\"message\": \"\", \"count\": 2,\"result\": { \"users\": [{  \"pwd\": \"123456\",  \"name\": \"xxx1\" },{ \"pwd\": \"123456\", \"name\": \"xxx2\"  } ]}}";

       //��json�ַ���ת��ΪJSONObject���� 
         JSONObject jsonObj = JSONObject.fromObject(res);
         // result��һ��json ����ʹ��getJSONObject()����ȡ
         JSONObject resultobj = jsonObj.getJSONObject("result");
         // users���������Ļ�����ʹ��getJSONArray()����ȡһ��json����
         JSONArray userlist = resultobj. getJSONArray("users") ;
         //����ѭ����ȡ�����еĶ���Ԫ��
         for (Object object : userlist  ) {
            JSONObject user= (JSONObject) object;
           
           System.out.println(user.getString("pwd")); 
            System.out.println(user.getString("name"));
         }

		//�ͷ�����
		response.close();
		httpClient.close();

	}

}
