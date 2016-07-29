package com.wopaitv.interfacetest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Step3_login {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws ClientProtocolException, IOException {
		Step2_Acesstoken acesstoken=new Step2_Acesstoken();
		
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		HttpGet httpGet=new HttpGet("http://192.168.108.160/restwww/userInfo?"+"access_token="+acesstoken.test());
  
        CloseableHttpResponse response= httpclient.execute(httpGet); 
        String returnStr = EntityUtils.toString(response.getEntity());
        JSONObject jsonObj = JSONObject.fromObject(returnStr);
        JSONObject resultobjt=jsonObj.getJSONObject("result");
        System.out.println(resultobjt.getString("phone"));
	}

}
