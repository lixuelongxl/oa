/**  
 * All rights Reserved, Designed By www.cyunsoft.com
 * @Title:  Regist.java   
 * @Package com.test   
 * @Description:    描述   
 * @author: lsq     
 * @date:   2019年10月30日 下午3:25:04   
 * @version V1.0 
 * @Copyright:江苏稠云 www.cyunsoft.com 
 */
package com.test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import org.apache.commons.io.FileUtils;
import com.alibaba.fastjson.JSONObject;

import org.core136.common.enums.RegistBean;
import org.core136.common.utils.MD5Utils;
import org.core136.common.utils.SysTools;

/**
 * @author lsq
 *
 */
public class Regist {
	private static String hexStr = "0123456789ABCDEF";

	public static void main(String[] args) throws ParseException, IOException {
		RegistBean rbBean = new RegistBean();
		String sn = getSn("CY智能网络办公系统", 1l, rbBean.getSoftVersion(), 100l);
		rbBean.setModel("OA#ERP#HR#PROJECT#CRM");
		rbBean.setServiceEndTime(SysTools.getTimeStamp("2022-12-30", "yyyy-MM-dd"));
		rbBean.setRegistTime(System.currentTimeMillis());
		rbBean.setMachineCode("3F4DCCFA43BB053C78F76A35F898AF8B");
		rbBean.setOrgCount(1);
		rbBean.setUserCount(10000);
		rbBean.setOrgName("江苏稠云信息技术有限公司");
		rbBean.setSn(sn);
		JSONObject json = (JSONObject) JSONObject.toJSON(rbBean);
		String regStr = json.toJSONString();
		byte[] regarr = regStr.getBytes();
		for (int i = 0; i < regarr.length; i++) {
			int value = regarr[i] + 2;
			regarr[i] = (byte) value;
		}
		
		String hexString = BinaryToHexString(regarr);
		FileUtils.writeStringToFile(new File("d:/attach/sys/reg.lsq"), hexString);
	}

	public static String BinaryToHexString(byte[] bytes) {

		String result = "";
		String hex = "";
		for (int i = 0; i < bytes.length; i++) {
			// 字节高4位
			hex = String.valueOf(hexStr.charAt((bytes[i] & 0xF0) >> 4));
			// 字节低4位
			hex += String.valueOf(hexStr.charAt(bytes[i] & 0x0F));
			result += hex + " ";
		}
		return result;
	}

	public static String getSn(String orgName, Long orgCount, String version, Long userCount) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("orgName", orgName);
		jsonObject.put("orgCount", orgCount);
		jsonObject.put("version", version);
		jsonObject.put("userCount", userCount);
		String str = jsonObject.toJSONString();
		String tempSn = MD5Utils.MD5Encode(str, "UTF-8").toUpperCase();
		tempSn = tempSn.substring(0, 8) + "-" + tempSn.substring(9, 20) + "-" + tempSn.substring(21, 28) + "-"
				+ tempSn.substring(29, 32);
		return tempSn;
	}
	
	
	public static byte[] HexStringToBinary(String hexString) {
		// hexString的长度对2取整，作为bytes的长度
		hexString=hexString.replaceAll(" +","");
		int len = hexString.length() / 2;
		byte[] bytes = new byte[len];
		byte high = 0;// 字节高四位
		byte low = 0;// 字节低四位

		for (int i = 0; i < len; i++) {
			// 右移四位得到高位
			high = (byte) ((hexStr.indexOf(hexString.charAt(2 * i))) << 4);
			low = (byte) hexStr.indexOf(hexString.charAt(2 * i + 1));
			bytes[i] = (byte) (high | low);// 高地位做或运算
		}
		return bytes;
	}

}
