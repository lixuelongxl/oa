/**  
 * All rights Reserved, Designed By www.cyunsoft.com
 * @Title:  Base64Test.java   
 * @Package com.test   
 * @Description:    描述   
 * @author: lsq     
 * @date:   2019年11月21日 下午4:01:23   
 * @version V1.0 
 * @Copyright:江苏稠云 www.cyunsoft.com 
 */
package com.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;



/**
 * @author lsq
 *
 */
public class Base64Test {
	private static final Charset charset = Charset.forName("UTF-8");  	
	
public static void main(String[] args) throws IOException {
	
//	String key = "3076608663";
//	BASE64Encoder encoder = new BASE64Encoder();
//	byte[] textByte = key.getBytes("UTF-8");
//	String encodedText = encoder.encode(textByte);
//	System.out.println(encodedText);
//	BASE64Decoder decoder = new BASE64Decoder();
//3595855133
//MzU5NTg1NTEzMw
//3008382176
//MzAwODM4MjE3Ng
//3076608663
//MzA3NjYwODY2Mw
//	System.out.println(new String(decoder.decodeBuffer("MzA3NjYwODY2Mw=="), "UTF-8"));
	String imgStr ="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\r\n";
	String password = "MzA3NjYwODY2Mw";
	//String imgStr = "磊定刷机桔罗曼蒂克桑德菲杰阿斯蒂芬234234234DSFSD;FSDKF";
	byte[] imgarr = imgStr.getBytes();
//	for (int i = 0; i < imgarr.length; i++) {
//		int value = imgarr[i] + 2;
//		imgarr[i] = (byte) value;
//	}
	String hexString = encode(password,imgStr);
	System.out.println(hexString);
	String rssString = new String(decode(password,hexString));
	System.out.println(rssString);
	
}




public static String encode(String password,String enc) throws UnsupportedEncodingException{  
	byte[] keyBytes = password.getBytes();
    byte[] b = enc.getBytes("UTF-8");  
    for(int i=0,size=b.length;i<size;i++){  
        for(byte keyBytes0:keyBytes){  
            b[i] = (byte) (b[i]^keyBytes0);  
        }  
    }  
    return new String(b,"UTF-8");  
}  


public static String decode(String password,String dec) throws UnsupportedEncodingException{  
	byte[] keyBytes = password.getBytes();
	byte[] e = dec.getBytes("UTF-8");  
    byte[] dee = e;  
    for(int i=0,size=e.length;i<size;i++){  
        for(byte keyBytes0:keyBytes){  
            e[i] = (byte) (dee[i]^keyBytes0);  
        }  
    }  
    return new String(e ,"UTF-8");  
}  

/**
 * 
 * @Title: BinaryToHexString   
 * @Description: TODO 二进制转成十六进制  
 * @param: password
 * @param: bytes
 * @param: @return      
 * @return: String      
 * @throws
 */
public static String BinaryToHexString(String password,byte[] bytes) {

	String result = "";
	String hex = "";
	for (int i = 0; i < bytes.length; i++) {
		// 字节高4位
		hex = String.valueOf(password.charAt((bytes[i] & 0xF0) >> 4));
		// 字节低4位
		hex += String.valueOf(password.charAt(bytes[i] & 0x0F));
		result += hex + " ";
	}
	return result;
}
/**
 * 
 * @Title: HexStringToBinary   
 * @Description: TODO 16进制转成二进制
 * @param: password
 * @param: hexString
 * @param: @return      
 * @return: byte[]      
 * @throws
 */
public static byte[] HexStringToBinary(String password,String hexString) {
	// hexString的长度对2取整，作为bytes的长度
	hexString = hexString.replaceAll(" +", "");
	int len = hexString.length() / 2;
	byte[] bytes = new byte[len];
	byte high = 0;// 字节高四位
	byte low = 0;// 字节低四位

	for (int i = 0; i < len; i++) {
		// 右移四位得到高位
		high = (byte) ((password.indexOf(hexString.charAt(2 * i))) << 4);
		low = (byte) password.indexOf(hexString.charAt(2 * i + 1));
		bytes[i] = (byte) (high | low);// 高地位做或运算
	}
	return bytes;
}


}
