package client.app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;

public class PurchaseRestHttpClientApp {
	
	public static void main(String[] args) throws Exception {
		
//		System.out.println("\n====================================\n");
//		// 2.1 Http Post 방식 Request : 
//		PurchaseRestHttpClientApp.addPurchaseGet();
		
//		System.out.println("\n====================================\n");
//		// 2.1 Http Get 방식 Request : 
//		PurchaseRestHttpClientApp.addPurchasePost();

//		System.out.println("\n====================================\n");
//		// 2.1 Http Get 방식 Request : 
//		PurchaseRestHttpClientApp.listPurchaseGet();

//		System.out.println("\n====================================\n");
//		// 2.1 Http Get 방식 Request : 
//		PurchaseRestHttpClientApp.getPurchaseGET();

//		System.out.println("\n====================================\n");
//		// 2.1 Http Post 방식 Request : 
//		PurchaseRestHttpClientApp.updatePurchasePost();

//		System.out.println("\n====================================\n");
//		// 2.1 Http Get 방식 Request : 
		PurchaseRestHttpClientApp.updateTranCodeGet();
	}
	
	//================================================================//
	//2.1 Http Protocol GET Request : FromData 전달 
	public static void addPurchaseGet() throws Exception{
			
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/purchase/json/addPurchase/10081/user01";
		// HttpGet : Http Protocol 의 GET 방식 Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol 응답 Message 추상화
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
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
		Product product = objectMapper.readValue(jsonobj.get("product").toString(), Product.class);
		User user = objectMapper.readValue(jsonobj.get("user").toString(), User.class);
		System.out.println(product);
		System.out.println(user);
	}
	
	//================================================================//
	//2.1 Http Protocol GET Request : FromData 전달 
	public static void addPurchasePost() throws Exception{
			
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/purchase/json/addPurchase/";
		// HttpGet : Http Protocol 의 GET 방식 Request
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		JSONObject json = new JSONObject();
		json.put("paymentOption", "1");
		json.put("receiverName", "SCOTT");
		json.put("tranNum", "1");
		
		JSONObject buyer = new JSONObject();
		buyer.put("userId", "user01");
		json.put("buyer", buyer);
		
		
		JSONObject purchaseProd = new JSONObject();
		purchaseProd.put("prodNo", 10081);
		json.put("purchaseProd", purchaseProd);
		
		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

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
		System.out.println(jsonobj);

	}

	//================================================================//
	//2.1 Http Protocol GET Request : FromData 전달 
	public static void listPurchaseGet() throws Exception{
			
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/purchase/json/listPurchase/user01";
		// HttpGet : Http Protocol 의 GET 방식 Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol 응답 Message 추상화
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
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
		List<Purchase> returnList = objectMapper.readValue(jsonManyValue, new TypeReference<List<Purchase>>() {
		});
//		Page resultPage = objectMapper.readValue(jsonobj.get("resultPage").toString(), Page.class);
//		Search search = objectMapper.readValue(jsonobj.get("search").toString(), Search.class);
		
		System.out.println(returnList);
//		System.out.println(resultPage);
//		System.out.println(search);
	}
	
	//================================================================//
	//2.1 Http Protocol GET Request : FromData 전달 / JsonSimple 3rd party lib 사용
	public static void getPurchaseGET() throws Exception{
		
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/purchase/json/getPurchase/10044";
		// HttpGet : Http Protocol 의 GET 방식 Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol 응답 Message 추상화
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
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
		System.out.println(jsonobj);
	
	}
	
	//================================================================//
	//2.1 Http Protocol GET Request : FromData 전달 / JsonSimple 3rd party lib 사용
	public static void updatePurchaseGET() throws Exception{
		
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/purchase/json/updatePurchase/10044";
		// HttpGet : Http Protocol 의 GET 방식 Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol 응답 Message 추상화
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
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
		System.out.println(jsonobj);
	
	}
	
	//================================================================//
	//2.1 Http Protocol GET Request : FromData 전달 
	public static void updatePurchasePost() throws Exception{
			
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/purchase/json/updatePurchase/";
		// HttpGet : Http Protocol 의 GET 방식 Request
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		JSONObject json = new JSONObject();
		json.put("tranNo", "10044");
		json.put("paymentOption", "2");
		json.put("receiverName", "CHANGE");
		json.put("divyAddr", "서울시 강남구");
		json.put("tranNum", "1");
		
		JSONObject buyer = new JSONObject();
		buyer.put("userId", "user01");
		json.put("buyer", buyer);
		
		
		JSONObject purchaseProd = new JSONObject();
		purchaseProd.put("prodNo", 10044);
		json.put("purchaseProd", purchaseProd);
		
		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

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
		System.out.println(jsonobj);

	}

	//================================================================//
	//2.1 Http Protocol GET Request : FromData 전달 
	public static void updateTranCodeGet() throws Exception{
			
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/purchase/json/updateTranCode/10044/1";
		// HttpGet : Http Protocol 의 GET 방식 Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol 응답 Message 추상화
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
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
		System.out.println(jsonobj);
	}
}
