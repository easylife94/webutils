package com.easieride.webutils.http.fileUpload;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertNotNull;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class FileUploadTest {
	
	
	/**
	 * 如果使用以下 文件上传地址。可以在本人的 开源项目study上下载项目struts2.file帮助测试
	 * 项目地址<url>https://github.com/easylife94/study</url>
	 */
	@Test
	public void testFileUpload() {
	  
	  String uploadUrl = "http://localhost:8080/struts2.file/file/upload";
	  FileUpload fileUpload = new FileUpload();
	  File file  = new File(this.getClass().getResource("").getPath()+"/test.log");
	  Map<String,String> params = new HashMap<String,String>();
	  params.put("fileName", "test");
	  params.put("fileType", "txt");
	  String result = fileUpload.uploadFile(uploadUrl, file, "file", params);
	  assertNotNull(result);
	  System.out.println(result);
	}

	@Test
	public void testUploadFile() {
		fail("Not yet implemented");
	}

}
