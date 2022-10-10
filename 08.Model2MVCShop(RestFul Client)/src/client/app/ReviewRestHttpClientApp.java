package client.app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Review;

public class ReviewRestHttpClientApp {
	
	public static void main(String[] args) throws Exception {
		
//		System.out.println("\n====================================\n");
//		// 2.1 Http Get 방식 Request : 
		ReviewRestHttpClientApp.listProductPost();
		

	}

	//================================================================//
	//2.1 Http Protocol POST Request : FromData 전달 / JsonSimple 3rd party lib 사용
	public static void listProductPost() throws Exception{
		
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/review/json/listReview/10082";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		//[ 방법 1 : String 사용]
//				String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//				HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
		
		//[ 방법 3 : codehaus 사용]
		Search search = new Search();
		search.setCurrentPage(2);
		ObjectMapper objectMapper01 = new ObjectMapper();
		//Object ==> JSON Value 로 변환
		String jsonValue = objectMapper01.writeValueAsString(search);
		HttpEntity httpEntity01 = new StringEntity(jsonValue,"utf-8");

		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[Server에서 받은 Data 확인]");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		ObjectMapper objectMapper = new ObjectMapper();
		//List<User> => JSON Value(String) 로 변환
		String jsonManyValue = objectMapper.writeValueAsString(jsonobj.get("list"));
		//JSON Value(String) => List<User> 로 변환 및 값 추출
		List<Review> returnList = objectMapper.readValue(jsonManyValue, new TypeReference<List<Review>>() {
		});
		search = objectMapper.readValue(jsonobj.get("search").toString(), Search.class);
		
		System.out.println(returnList);
		System.out.println(search);
	
	}

}
