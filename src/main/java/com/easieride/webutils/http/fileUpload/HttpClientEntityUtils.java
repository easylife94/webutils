package com.easieride.webutils.http.fileUpload;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;

public class HttpClientEntityUtils {
	
	public String toString(HttpEntity entity){
		BufferedReader bufReader = null;
		StringBuilder result = null;
		try {
			bufReader = new BufferedReader(
					new InputStreamReader(entity.getContent(),"UTF-8"));
			result = new StringBuilder();
			String line = null;
			while ((line = bufReader.readLine()) != null) {
				result.append(line);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(bufReader != null){
				try {
					bufReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result==null?null:result.toString();
	}
}
