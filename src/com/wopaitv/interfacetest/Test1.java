package com.wopaitv.interfacetest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Test1 {

	 // ����JSONObject����
    private static JSONObject createJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "huangwuyi");
        jsonObject.put("sex", "��");
        jsonObject.put("QQ", "413425430");
        jsonObject.put("Min.score", new Integer(99));
        jsonObject.put("nickname", "�����ľ�");
        return jsonObject;
    }
	public static void main(String[] args) {
		JSONObject jsonObject = Test1.createJSONObject();//����������ֱ��ͨ������+��������
        // ���jsonobject����
        System.out.println("jsonObject��" + jsonObject);

        // �ж�������������
        boolean isArray = jsonObject.isArray();
        boolean isEmpty = jsonObject.isEmpty();
        boolean isNullObject = jsonObject.isNullObject();
        System.out.println("�Ƿ�Ϊ����:" + isArray + "�� �Ƿ�Ϊ��:" + isEmpty
                + "�� isNullObject:" + isNullObject);

        // ������ԣ���jsonObject����׷��Ԫ�ء�
        jsonObject.element("address", "����ʡ������");
        System.out.println("������Ժ�Ķ���" + jsonObject);
        
        JSONArray ja=new JSONArray();
        ja.add(0,"����1");
        ja.add(1,"����2");
        jsonObject.element("ja1", ja);
        System.out.println(jsonObject);

	}

}
