package com.wopaitv.interfacetest;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.log.Logger;

public class TestLength extends AbstractJavaSamplerClient {
	private SampleResult results;
	private String testStr;
	private Logger log = getLogger();
	@Override
	public void setupTest(JavaSamplerContext arg0) {
	     log.info("execute setupTest...");
	     results = new SampleResult();
	     testStr = arg0.getParameter("testStr");
	     log.info(testStr);
	     if (testStr != null && testStr.length() > 0) {
	         results.setSamplerData(testStr);
	        }
	    }
	public Arguments getDefaultParameters() {
	     log.info("execute getDefaultParameters...");
	     Arguments params = new Arguments();
	     params.addArgument("testStr", "123");   
	        return params;
	    }
	public SampleResult runTest(JavaSamplerContext arg0) {
		 if(testStr.length() < 5){
		     log.info("fail...");
		     System.out.println(testStr);
		     results.setSuccessful(false);   
		        } else {   
		        	 System.out.println(testStr);
		         log.info("Success...dayu5555555");
		         results.setSuccessful(true);
		        }
		 log.info(testStr); 
		        return results;
	}
	public void teardownTest(JavaSamplerContext arg0) {
	   }
}
