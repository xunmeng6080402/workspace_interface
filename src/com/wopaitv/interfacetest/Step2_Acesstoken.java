package com.wopaitv.interfacetest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
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



public class Step2_Acesstoken {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public String test() throws ClientProtocolException, IOException {
		Step1_auth t4=new Step1_auth();
		String authcode =t4.test();
		System.out.println("=========================="+authcode);
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		HttpPost httpPost=new HttpPost("http://192.168.108.160/restwww/accessToken");
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
    	nvps.add(new BasicNameValuePair("grant_type", "authorization_code"));
    	nvps.add(new BasicNameValuePair("client_id", "c1ebe466-1cdc-4bd3-ab69-77c3561b9dee"));
    	nvps.add(new BasicNameValuePair("client_secret", "d8346ea2-6017-43ed-ad68-19c0f971738b"));
    	nvps.add(new BasicNameValuePair("code", authcode));
    	nvps.add(new BasicNameValuePair("redirect_uri", "url"));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));  
        CloseableHttpResponse response= httpclient.execute(httpPost); 
        String returnStr = EntityUtils.toString(response.getEntity());
        System.out.print(returnStr);
        
        JSONObject jsonObj = JSONObject.fromObject(returnStr);
        JSONObject resultobjt=jsonObj.getJSONObject("result");
        System.out.println(resultobjt.getString("access_token"));
        return resultobjt.getString("access_token"); 
	}

}
