package com.wopaitv.interfacetest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Test1 {

	 // 创建JSONObject对象
    private static JSONObject createJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "huangwuyi");
        jsonObject.put("sex", "男");
        jsonObject.put("QQ", "413425430");
        jsonObject.put("Min.score", new Integer(99));
        jsonObject.put("nickname", "梦中心境");
        return jsonObject;
    }
	public static void main(String[] args) {
		JSONObject jsonObject = Test1.createJSONObject();//静待方法，直接通过类名+方法调用
        // 输出jsonobject对象
        System.out.println("jsonObject：" + jsonObject);

        // 判读输出对象的类型
        boolean isArray = jsonObject.isArray();
        boolean isEmpty = jsonObject.isEmpty();
        boolean isNullObject = jsonObject.isNullObject();
        System.out.println("是否为数组:" + isArray + "， 是否为空:" + isEmpty
                + "， isNullObject:" + isNullObject);

        // 添加属性，在jsonObject后面追加元素。
        jsonObject.element("address", "福建省厦门市");
        System.out.println("添加属性后的对象：" + jsonObject);
        
        JSONArray ja=new JSONArray();
        ja.add(0,"数组1");
        ja.add(1,"数组2");
        jsonObject.element("ja1", ja);
        System.out.println(jsonObject);

	}

}
