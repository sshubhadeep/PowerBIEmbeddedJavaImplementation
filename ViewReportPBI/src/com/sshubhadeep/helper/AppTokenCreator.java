package com.sshubhadeep.helper;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


import org.apache.tomcat.util.codec.binary.Base64;



public class AppTokenCreator {
	private static final String MAC_ALOG = "HmacSHA256";
	private static final String TEXT_ENCODING = "UTF-8";

	static String rfc4648Base64Encode(String arg) throws UnsupportedEncodingException {
		String res = Base64.encodeBase64String(arg.getBytes(TEXT_ENCODING));//encoder.encodeToString(arg.getBytes(TEXT_ENCODING));
		res = res.replace("/", "_");
		res = res.replace("+", "-");
		res = res.replace("=", "");
		return res;
	}

	static String rfc4648Base64Encode(byte[] arg) throws UnsupportedEncodingException {
		String res = Base64.encodeBase64String(arg);//encoder.encodeToString(arg);
		res = res.replace("/", "_");
		res = res.replace("+", "-");
		res = res.replace("=", "");
		return res;
	}

	public static String createAppToken(String workspaceid, String reportId, String workspaceCollectionName,
			String accessToken) {
		String apptoken = "";
		
		Date oldDate = new Date();
		final long hoursInMillis = 60L * 60L * 1000L;
		Date newDate = new Date(oldDate.getTime() + 
		                        (1L * hoursInMillis));
		
		try {
			String token1 = "{\"typ\":\"JWT\",\"alg\":\"HS256\"}";
			String token2 = "{" + //
					"\"wid\":\"" + workspaceid + "\"," + // workspaceid
					"\"rid\":\"" + reportId + "\"," + // reportid
					"\"wcn\":\"" + workspaceCollectionName + "\"," + // workspace_collection_name
					"\"iss\":\"PowerBISDK\"," + //
					"\"ver\":\"0.2.0\"," + //
					"\"aud\":\"https://analysis.windows.net/powerbi/api\"," + //
					"\"nbf\":" + (oldDate.getTime() / 1000) + ","//
					+ "\"exp\":" + (newDate.getTime() / 1000) + "}";//
			String inputval = rfc4648Base64Encode(token1) + "." + rfc4648Base64Encode(token2);
			SecretKeySpec sk = new SecretKeySpec(accessToken.getBytes(TEXT_ENCODING), MAC_ALOG);
			Mac mac = Mac.getInstance(MAC_ALOG);
			mac.init(sk);
			byte[] mac_bytes = mac.doFinal(inputval.getBytes(TEXT_ENCODING));

			apptoken = inputval + "." + rfc4648Base64Encode(mac_bytes);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException("PowerBI Helper class something wrong", e);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException("PowerBI Helper class something wrong", e);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			throw new RuntimeException("PowerBI Helper class something wrong", e);
		}
		return apptoken;
	}
	
}
