package com.easieride.webutils.http.fileUpload;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClientBuilder;

public class FileUpload {
	
	public FileUpload() {
		
	}
	
	
	public String uploadFile(String url,File file, String fileParamName,Map<String,String> otherParams) {
		
		HttpClientBuilder clientBuilder = HttpClientBuilder.create();
		MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
		
		HttpClient httpclient = clientBuilder.build(); 
		HttpPost post = new HttpPost(url);
		
		BufferedReader bufReader = null;
		String result = null;
		try {
			
			if(otherParams!=null){
				Set<String> keys = otherParams.keySet();
				Iterator<String> it = keys.iterator();
				while(it.hasNext()){
					String paramName= it.next();
					entityBuilder.addTextBody(paramName,otherParams.get(paramName));				
				}
			}
			entityBuilder.addPart(fileParamName, new FileBody(file));
			HttpEntity entity = entityBuilder.build();
			post.setEntity(entity);
			
			HttpResponse response = httpclient.execute(post);
			HttpEntity respEntity = response.getEntity();			
			result = new HttpClientEntityUtils().toString(respEntity);
			post.releaseConnection();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(bufReader != null){
				try {
					bufReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			post.releaseConnection();
		}
		return result;
	}
	
	
}
