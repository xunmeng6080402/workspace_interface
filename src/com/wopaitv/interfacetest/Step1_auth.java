package com.wopaitv.interfacetest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Step1_auth {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public String test() throws ClientProtocolException, IOException {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		
		HttpPost httpPost=new HttpPost("http://192.168.108.160/restwww/authorize");

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
		nvps.add(new BasicNameValuePair("response_type","code")) ;
		nvps.add(new BasicNameValuePair("client_id","c1ebe466-1cdc-4bd3-ab69-77c3561b9dee")) ;
		nvps.add(new BasicNameValuePair("username", "15010707894"));  
        nvps.add(new BasicNameValuePair("password", "21218cca77804d2ba1922c33e0151105"));  
		
		
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));  
        CloseableHttpResponse response= httpclient.execute(httpPost);  
       
        Header headers[] = response.getAllHeaders();
        for (Header header : headers) {
            //响应头信息名称
                 System.out.println(header.getName());       
              //头信息对应的值
                 System.out.println(header.getValue());
		} 
        System.out.println(response.getStatusLine().getStatusCode());

//      返回的是json串
        String returnStr = EntityUtils.toString(response.getEntity());
        System.out.println(returnStr);
//        将json串转化成
        JSONObject jsonObj = JSONObject.fromObject(returnStr);
//        System.out.println(jsonObj.getString("code"))  ;
        JSONObject resultobjt=jsonObj.getJSONObject("result");
        System.out.println(resultobjt.getString("authorization_code"));
//        System.out.println(jsonObj.getString("result")+"==================================")  ;
      
        return resultobjt.getString("authorization_code"); 
      
       
	}

}
