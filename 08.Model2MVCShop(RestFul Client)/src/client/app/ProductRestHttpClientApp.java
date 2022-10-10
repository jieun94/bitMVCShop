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
import com.model2.mvc.service.domain.User;

public class ProductRestHttpClientApp {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		////////////////////////////////////////////////////////////////////////////////////////////
		// �ּ��� �ϳ��� ó���ذ��� �ǽ�
		////////////////////////////////////////////////////////////////////////////////////////////
		
//		System.out.println("\n====================================\n");
//		// 2.1 Http Post ��� Request : JsonSimple lib ���
//		ProductRestHttpClientApp.addProductTest_JsonSimple();
		
//		System.out.println("\n====================================\n");
//		// 2.1 Http Post ��� Request : 
//		ProductRestHttpClientApp.getProductGET();
		
//		System.out.println("\n====================================\n");
//		// 2.1 Http Post ��� Request : 
//		ProductRestHttpClientApp.updateProductPOST();
		
//		System.out.println("\n====================================\n");
//		// 2.1 Http Get ��� Request : 
		ProductRestHttpClientApp.listProductGet();
		
//		System.out.println("\n====================================\n");
//		// 2.1 Http Get ��� Request : 
		ProductRestHttpClientApp.listProductPost();
		

	}
	
	//================================================================//
	//2.1 Http Protocol POST Request : FromData ���� / JsonSimple 3rd party lib ���
	public static void addProductTest_JsonSimple() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/product/json/addProduct";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		//[ ��� 1 : String ���]
//				String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//				HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
		
		//[ ��� 2 : JSONObject ���]
		JSONObject json = new JSONObject();
		json.put("prodName", "jsonTest");
		json.put("prodDetail", "1234");
		json.put("price", "10000");
		json.put("manuDate", "20200602");
		json.put("prodNum", "1");
		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[Server���� ���� Data Ȯ��]");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		ObjectMapper objectMapper = new ObjectMapper();
		Product product = objectMapper.readValue(jsonobj.get("product").toString(), Product.class);
		System.out.println(product);
	
	}
	
	//================================================================//
	//2.1 Http Protocol GET Request : FromData ���� / JsonSimple 3rd party lib ���
	public static void getProductGET() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/product/json/getProduct/10000/search";
		// HttpGet : Http Protocol �� GET ��� Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol ���� Message �߻�ȭ
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[Server���� ���� Data Ȯ��]");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		ObjectMapper objectMapper = new ObjectMapper();
		Product product = objectMapper.readValue(jsonobj.get("prod").toString(), Product.class);
		String menu = objectMapper.writeValueAsString(jsonobj.get("menu").toString());
		System.out.println(product);
		System.out.println(menu);
	
	}
	
	//================================================================//
	//2.1 Http Protocol POST Request : FromData ���� / JsonSimple 3rd party lib ���
	public static void updateProductPOST() throws Exception{

		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= 	"http://127.0.0.1:8080/product/json/updateProduct";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		//[ ��� 1 : String ���]
//					String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//					HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
		
		//[ ��� 2 : JSONObject ���]
		JSONObject json = new JSONObject();
		json.put("prodNo", "10081");
		json.put("prodName", "jsonChange");
		json.put("prodDetail", "changeTest");
		json.put("manuDate", "20200520");
		json.put("price", "123123");
		json.put("prodNum", "3");
		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		//==> �����б�(JSON Value Ȯ��)
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
		
	}
	
	//================================================================//
	//1.1 Http Protocol GET Request : JsonSimple 3rd party lib ���
	public static void listProductGet() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= 	"http://127.0.0.1:8080/product/json/listProduct";
				
		// HttpGet : Http Protocol �� GET ��� Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol ���� Message �߻�ȭ
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[Server���� ���� Data Ȯ��]");
		String serverData = br.readLine();
		System.out.println(serverData);
		System.out.println();
		
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		ObjectMapper objectMapper = new ObjectMapper();
		//List<User> => JSON Value(String) �� ��ȯ
		String jsonManyValue = objectMapper.writeValueAsString(jsonobj.get("list"));
		//JSON Value(String) => List<User> �� ��ȯ �� �� ����
		List<Product> returnList = objectMapper.readValue(jsonManyValue, new TypeReference<List<Product>>() {
		});
		Page resultPage = objectMapper.readValue(jsonobj.get("resultPage").toString(), Page.class);
		Search search = objectMapper.readValue(jsonobj.get("search").toString(), Search.class);
		
		System.out.println(returnList);
		System.out.println(resultPage);
		System.out.println(search);
	}
	
	//================================================================//
	//2.1 Http Protocol POST Request : FromData ���� / JsonSimple 3rd party lib ���
	public static void listProductPost() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/product/json/listProduct";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		//[ ��� 1 : String ���]
//				String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//				HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
		
		//[ ��� 3 : codehaus ���]
		Search search = new Search();
		search.setSearchCondition("1");
		search.setSearchKeyword("������");
		ObjectMapper objectMapper01 = new ObjectMapper();
		//Object ==> JSON Value �� ��ȯ
		String jsonValue = objectMapper01.writeValueAsString(search);
		HttpEntity httpEntity01 = new StringEntity(jsonValue,"utf-8");

		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[Server���� ���� Data Ȯ��]");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		ObjectMapper objectMapper = new ObjectMapper();
		//List<User> => JSON Value(String) �� ��ȯ
		String jsonManyValue = objectMapper.writeValueAsString(jsonobj.get("list"));
		//JSON Value(String) => List<User> �� ��ȯ �� �� ����
		List<Product> returnList = objectMapper.readValue(jsonManyValue, new TypeReference<List<Product>>() {
		});
		Page resultPage = objectMapper.readValue(jsonobj.get("resultPage").toString(), Page.class);
		search = objectMapper.readValue(jsonobj.get("search").toString(), Search.class);
		
		System.out.println(returnList);
		System.out.println(resultPage);
		System.out.println(search);
	
	}
	

}
