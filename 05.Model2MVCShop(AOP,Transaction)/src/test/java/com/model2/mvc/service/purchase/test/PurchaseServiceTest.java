package com.model2.mvc.service.purchase.test;

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
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {	"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })
public class PurchaseServiceTest {

	//==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;

	//@Test
	public void testAddPurchase() throws Exception {
		
		Purchase pc = new Purchase();
		Product prod = new Product();
		User user = new User();
		
		prod.setProdNo(10044);
		user.setUserId("user01");
		pc.setBuyer(user);
		pc.setPurchaseProd(prod);
		
		pc.setPaymentOption("1");
		pc.setReceiverName("SCOTT");
		pc.setReceiverPhone("01033335555");
		pc.setDivyAddr("������ ���뱸");
		pc.setDivyRequest("����");
		pc.setDivyDate("20201010");
		
		purchaseService.addPurchase(pc);

		//==> console Ȯ��
		System.out.println(pc);
		
		//==> API Ȯ��
		Assert.assertEquals(10044, pc.getPurchaseProd().getProdNo());
		Assert.assertEquals("user01", pc.getBuyer().getUserId());
		Assert.assertEquals("1", pc.getPaymentOption());
		Assert.assertEquals("SCOTT", pc.getReceiverName());
		Assert.assertEquals("01033335555", pc.getReceiverPhone());
		Assert.assertEquals("������ ���뱸",pc.getDivyAddr());
		Assert.assertEquals("����",pc.getDivyRequest());
		Assert.assertEquals("20201010",pc.getDivyDate());
	}
	
	
	//@Test
	public void testGetPurchase() throws Exception {
	 
		Purchase pc = purchaseService.getPurchase(10041);
		
		  
		  //==> console Ȯ�� //System.out.println(user);
		System.out.println(pc);
		 
		 //==> API Ȯ�� 
		/*
		 * Assert.assertEquals(10044, prod.getProdNo());
		 * Assert.assertEquals("testProdName", prod.getProdName());
		 * Assert.assertEquals("testDetail", prod.getProdDetail());
		 * Assert.assertEquals("20200513", prod.getManuDate());
		 * Assert.assertEquals(12000, prod.getPrice()); Assert.assertEquals("test",
		 * prod.getFileName());
		 */
		
		Assert.assertEquals(10044,pc.getPurchaseProd().getProdNo());
		Assert.assertEquals("change",pc.getPurchaseProd().getProdName());
		Assert.assertEquals("user01", pc.getBuyer().getUserId());
		Assert.assertEquals("1", pc.getPaymentOption().trim());
		Assert.assertEquals("SCOTT", pc.getReceiverName());
		Assert.assertEquals("01033335555", pc.getReceiverPhone());
		Assert.assertEquals("������ ���뱸",pc.getDivyAddr());
		Assert.assertEquals("����",pc.getDivyRequest());
		Assert.assertEquals("2020-10-10 00:00:00.0",pc.getDivyDate());
		Assert.assertEquals("1",pc.getTranCode().trim());
	  
	}
	
	//@Test
	public void testUpdatePurchase() throws Exception{
		
		Purchase pc = purchaseService.getPurchase(10041);
		Assert.assertNotNull(pc);
		
		Assert.assertEquals(10044,pc.getPurchaseProd().getProdNo());
		Assert.assertEquals("change",pc.getPurchaseProd().getProdName());
		Assert.assertEquals("user01", pc.getBuyer().getUserId());
		Assert.assertEquals("1", pc.getPaymentOption().trim());
		Assert.assertEquals("SCOTT", pc.getReceiverName());
		Assert.assertEquals("01033335555", pc.getReceiverPhone());
		Assert.assertEquals("������ ���뱸",pc.getDivyAddr());
		Assert.assertEquals("����",pc.getDivyRequest());
		Assert.assertEquals("2020-10-10 00:00:00.0",pc.getDivyDate());
		Assert.assertEquals("1",pc.getTranCode().trim());

		pc.setPaymentOption("2");
		pc.setReceiverName("changeName");
		pc.setReceiverPhone("99911112222");
		pc.setDivyAddr("����� ������");
		pc.setDivyRequest("õõ��");
		pc.setDivyDate("20200101");
		
		purchaseService.updatePurcahse(pc);
		
		pc = purchaseService.getPurchase(10041);
		Assert.assertNotNull(pc);
		
		//==> console Ȯ��
		System.out.println(pc);
			
		//==> API Ȯ��
		Assert.assertEquals(10044,pc.getPurchaseProd().getProdNo());
		Assert.assertEquals("change",pc.getPurchaseProd().getProdName());
		Assert.assertEquals("user01", pc.getBuyer().getUserId());
		Assert.assertEquals("2", pc.getPaymentOption().trim());
		Assert.assertEquals("changeName", pc.getReceiverName());
		Assert.assertEquals("99911112222", pc.getReceiverPhone());
		Assert.assertEquals("����� ������",pc.getDivyAddr());
		Assert.assertEquals("õõ��",pc.getDivyRequest());
		Assert.assertEquals("2020-01-01 00:00:00.0",pc.getDivyDate());
		Assert.assertEquals("1",pc.getTranCode().trim());
	 }
	
	//@Test
	public void testUpdateTranCode() throws Exception{
		
		Purchase pc = purchaseService.getPurchase(10041);
		Assert.assertNotNull(pc);
		
		Assert.assertEquals(10044,pc.getPurchaseProd().getProdNo());
		Assert.assertEquals("change",pc.getPurchaseProd().getProdName());
		Assert.assertEquals("user01", pc.getBuyer().getUserId());
		Assert.assertEquals("2", pc.getPaymentOption().trim());
		Assert.assertEquals("changeName", pc.getReceiverName());
		Assert.assertEquals("99911112222", pc.getReceiverPhone());
		Assert.assertEquals("����� ������",pc.getDivyAddr());
		Assert.assertEquals("õõ��",pc.getDivyRequest());
		Assert.assertEquals("2020-01-01 00:00:00.0",pc.getDivyDate());
		Assert.assertEquals("1",pc.getTranCode().trim());

		pc.setTranCode("2");
		
		purchaseService.updateTranCode(pc);;
		
		pc = purchaseService.getPurchase(10041);
		Assert.assertNotNull(pc);
		
		//==> console Ȯ��
		System.out.println(pc);
			
		//==> API Ȯ��
		Assert.assertEquals(10044,pc.getPurchaseProd().getProdNo());
		Assert.assertEquals("change",pc.getPurchaseProd().getProdName());
		Assert.assertEquals("user01", pc.getBuyer().getUserId());
		Assert.assertEquals("2", pc.getPaymentOption().trim());
		Assert.assertEquals("changeName", pc.getReceiverName());
		Assert.assertEquals("99911112222", pc.getReceiverPhone());
		Assert.assertEquals("����� ������",pc.getDivyAddr());
		Assert.assertEquals("õõ��",pc.getDivyRequest());
		Assert.assertEquals("2020-01-01 00:00:00.0",pc.getDivyDate());
		Assert.assertEquals("2",pc.getTranCode().trim());
	 }
	
	 @Test
	 public void testGetProductListAll() throws Exception{
		 
	 	Search search = new Search();
	 	
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	String buyerId= "user02";
	 	
	 	Map<String,Object> map = purchaseService.getPurchaseList(search, buyerId);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	}
}