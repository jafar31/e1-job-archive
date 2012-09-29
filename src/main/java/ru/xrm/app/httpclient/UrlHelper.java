package ru.xrm.app.httpclient;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class UrlHelper {

	MessageDigest md;
	
	public UrlHelper(){
		try {
			md=MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	public String encode(String url){
		md.update(url.getBytes());
		byte[] digest=md.digest();
		StringBuilder sb=new StringBuilder();

		for (int i=0;i<digest.length;i++){
			String hex=Integer.toHexString(0xFF & digest[i]);
			if (hex.length()==1){
				sb.append('0');
			}
			sb.append(hex);
		}
		return sb.toString();
	}
	
	public String getBasename(String address){
		// http://google.com/a/yandex.ru
		// return http://google.com
		String result="";
		try {
			URL url=new URL(address);
			result=url.getProtocol()+"://"+url.getHost();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return result;
	}
}