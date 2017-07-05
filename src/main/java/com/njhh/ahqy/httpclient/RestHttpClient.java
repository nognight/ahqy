package com.njhh.ahqy.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class RestHttpClient extends HttpConstants{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	HttpConnectionManager connManager;

	public  String getHttpResponse(String url, String method, HashMap<String, String> headers, String contents) throws IOException {
		if (url == null || url.trim().isEmpty()) {
			return null;
		}
		if (Method.HTTP_METHOD_GET.equals(method)) {
			HttpGet httpGet = new HttpGet(url);
			 return execute(httpGet);

		} else if (Method.HTTP_METHOD_POST.equals(method)) {
			HttpPost httpPost = new HttpPost(url);
			if (contents != null) {
				httpPost.setEntity(new StringEntity(contents, ContentType.create("json", "utf-8")));
				if (headers != null && headers.size() > 0) {
					//设置请求头
					Set<Map.Entry<String, String>> sets = headers.entrySet();
					for (Map.Entry<String, String> entry : sets) {
						httpPost.setHeader(entry.getKey(), entry.getValue());
					}
				}
			}
			return execute(httpPost);
		} else {
			return null;
		}
	}


	private String execute(HttpUriRequest req) {

		if (req == null) {
			return null;
		}
		CloseableHttpClient httpClient = connManager.getHttpClient();
		CloseableHttpResponse resp=null;
		//发送https请求，获取响应码
		try {
			 resp = httpClient.execute(req);
			if (resp == null) {
				logger.warn("execute  "  + "|http response null");
				return null;
			} else if (resp.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				logger.warn("execute  "  + "|http response code:"
						+ resp.getStatusLine().getStatusCode());
				return null;
			}

			logger.info(" | resp: " + resp.toString());
			HttpEntity entity = resp.getEntity();
//			String respContent = EntityUtils.toString(bean, "UTF-8");
			String respContent = EntityUtils.toString(entity, "GBK");
			InputStream in=resp.getEntity().getContent();
			in.close();

			return respContent;
		} catch (ClientProtocolException e) {
			logger.warn("execute  "   + "|exception: " + e.getMessage());
			return null;
		} catch (IOException e) {
			logger.warn("execute  "  + "|exception: " + e.getMessage());
			return null;
		} finally {
			try {
				resp.close();
			}catch (IOException e){
				e.getMessage();
			}
		}
	}
}


