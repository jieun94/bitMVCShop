package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {	"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })
public class ProductServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	//@Test
	public void testAddProduct() throws Exception {
		
		Product prod = new Product();
		prod.setProdName("testProdName");
		prod.setProdDetail("testDetail");
		prod.setManuDate("20200513");
		prod.setPrice(12000);
		prod.setFileName("test");
		
		productService.addProduct(prod);

		//==> console 확인
		System.out.println(prod);
		
		//==> API 확인
		Assert.assertEquals("testProdName", prod.getProdName());
		Assert.assertEquals("testDetail", prod.getProdDetail());
		Assert.assertEquals("20200513", prod.getManuDate());
		Assert.assertEquals(12000, prod.getPrice());
		Assert.assertEquals("test", prod.getFileName());
	}
	
	@Test
	public void testGetProduct() throws Exception {
	 
		Product prod = new Product(); 
		 
		prod = productService.getProduct(10044);
		  
		  //==> console 확인 //System.out.println(user);
		System.out.println(prod);
		 
		 //==> API 확인 
		/*
		 * Assert.assertEquals(10044, prod.getProdNo());
		 * Assert.assertEquals("testProdName", prod.getProdName());
		 * Assert.assertEquals("testDetail", prod.getProdDetail());
		 * Assert.assertEquals("20200513", prod.getManuDate());
		 * Assert.assertEquals(12000, prod.getPrice()); Assert.assertEquals("test",
		 * prod.getFileName());
		 */
		
		Assert.assertEquals(10044, prod.getProdNo());
		Assert.assertEquals("change", prod.getProdName());
		Assert.assertEquals("changeDetail", prod.getProdDetail());
		Assert.assertEquals("20200101", prod.getManuDate());
		Assert.assertEquals(5000, prod.getPrice()); 
		Assert.assertEquals("change", prod.getFileName());
		Assert.assertEquals(0, prod.getTranNo()); 
	  
	}
	 
	//@Test
	 public void testUpdateProduct() throws Exception{
		 
		Product prod = productService.getProduct(10044);
		Assert.assertNotNull(prod);
		
		Assert.assertEquals(10044, prod.getProdNo());
		Assert.assertEquals("testProdName", prod.getProdName());
		Assert.assertEquals("testDetail", prod.getProdDetail());
		Assert.assertEquals("20200513", prod.getManuDate());
		Assert.assertEquals(12000, prod.getPrice()); 
		Assert.assertEquals("test", prod.getFileName());

		prod.setProdName("change");
		prod.setProdDetail("changeDetail");
		prod.setManuDate("20200101");
		prod.setPrice(5000);
		prod.setFileName("change");
		
		productService.updateProduct(prod);
		
		prod = productService.getProduct(10044);
		Assert.assertNotNull(prod);
		
		//==> console 확인
		System.out.println(prod);
			
		//==> API 확인
		Assert.assertEquals(10044, prod.getProdNo());
		Assert.assertEquals("change", prod.getProdName());
		Assert.assertEquals("changeDetail", prod.getProdDetail());
		Assert.assertEquals("20200101", prod.getManuDate());
		Assert.assertEquals(5000, prod.getPrice()); 
		Assert.assertEquals("change", prod.getFileName());
	 }
	
	 //@Test
	 public void testGetProductListAll() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("");
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
	 	//==> console 확인
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	 //@Test
	 public void testGetProductListByProdNo() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("10044");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
		/*
		 * search.setSearchCondition("0");
		 * search.setSearchKeyword(""+System.currentTimeMillis()); 
		 * map = productService.getProductList(search);
		 * 
		 * list = (List<Object>)map.get("list"); Assert.assertEquals(0, list.size());
		 * 
		 * //==> console 확인 System.out.println(list);
		 * 
		 * totalCount = (Integer)map.get("totalCount"); 
		 * System.out.println(totalCount);
		 */
	 }
	 
	 //@Test
	 public void testGetProductListByProdName() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword("change");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(2, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
		/*
		 * search.setSearchCondition("1");
		 * search.setSearchKeyword(""+System.currentTimeMillis()); map =
		 * productService.getProductList(search);
		 * 
		 * list = (List<Object>)map.get("list"); Assert.assertEquals(0, list.size());
		 * 
		 * //==> console 확인 System.out.println(list);
		 * 
		 * totalCount = (Integer)map.get("totalCount"); System.out.println(totalCount);
		 */
	}	 
	 
	 //@Test
	 public void testGetProductListByPrice() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("2");
	 	search.setSearchKeyword("5000");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(2, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
		/*
		 * search.setSearchCondition("1");
		 * search.setSearchKeyword(""+System.currentTimeMillis()); map =
		 * productService.getProductList(search);
		 * 
		 * list = (List<Object>)map.get("list"); Assert.assertEquals(0, list.size());
		 * 
		 * //==> console 확인 System.out.println(list);
		 * 
		 * totalCount = (Integer)map.get("totalCount"); System.out.println(totalCount);
		 */
	}	 
	
}