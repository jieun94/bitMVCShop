package com.model2.mvc.web.purchase;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;

@RestController
@RequestMapping("/purchase/*")
public class PurchaseRestController {
	
	//Field
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	@Autowired
	private ProductService prodService;
	//@Autowired
	//private UserService userService;
	
	//Constructor
	public PurchaseRestController(){
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	
	@RequestMapping(value="json/addPurchase/{prod_no}/{userId}", method = RequestMethod.GET)
	public Map addPurchase(@PathVariable("prod_no") int prodNo, @PathVariable("userId") String userId, HttpSession session) throws Exception {
		
		System.out.println("/purchase/json/addPurchase : GET");
		
		//User user = (User)session.getAttribute("user");
		User user = new User();
		user.setUserId(userId);
		Product product = prodService.getProduct(prodNo);
		
		Map map = new HashMap();
		map.put("user",user);
		map.put("product", product);
		
		return map;
	}
	
	@RequestMapping(value="json/addPurchase/", method = RequestMethod.POST)
	public Purchase addPurchase(@RequestBody Purchase purchase) throws Exception {
		
		System.out.println("/purchase/json/addPurchase : POST");
		
		purchaseService.addPurchase(purchase);
		
		return purchase;
	}
	
	@RequestMapping(value="json/listPurchase/{userId}", method = RequestMethod.GET)
	public Map listPurchase(@PathVariable("userId") String buyerId) throws Exception{
		
		System.out.println("/purchase/json/listPurchase : GET");
		
		Search search = new Search();
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		//String buyerId = ((User)session.getAttribute("user")).getUserId();
		
		
		// Business logic 수행
		Map<String , Object> map=purchaseService.getPurchaseList(search, buyerId);
		//purchase.setTranCode(purchase.getTranCode().trim());
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model 과 View 연결
		Map map01 = new HashMap();
		map01.put("list", map.get("list"));
		map01.put("resultPage", resultPage);
		map01.put("search", search);
		
		return map01;
	}
	
	@RequestMapping(value="json/listPurchase/", method = RequestMethod.POST)
	public Map listPurchase(HttpSession session) throws Exception{
		
		System.out.println("/purchase/json/listPurchase : POST");
		
		Search search = new Search();
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		String buyerId = ((User)session.getAttribute("user")).getUserId();
		
		
		// Business logic 수행
		Map<String , Object> map=purchaseService.getPurchaseList(search, buyerId);
		//purchase.setTranCode(purchase.getTranCode().trim());
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model 과 View 연결
		Map map01 = new HashMap();
		map01.put("list", map.get("list"));
		map01.put("resultPage", resultPage);
		map01.put("search", search);
		
		return map01;
	}
	
	@RequestMapping(value="json/getPurchase/{tranNo}", method = RequestMethod.GET)
	public Purchase getPurchase(@PathVariable("tranNo") int tranNo) throws Exception {
		
		System.out.println("/purchase/json/getPurchase : GET");
		
		Purchase purchase = new Purchase();
		
		purchase = purchaseService.getPurchase(tranNo);
		purchase.setPaymentOption(purchase.getPaymentOption().trim());
		purchase.setTranCode(purchase.getTranCode().trim());
		
		return purchase;
	}
	
	@RequestMapping(value="json/updatePurchase/{tranNo}", method = RequestMethod.GET)
	public Purchase updatePurchase(@PathVariable("tranNo") int tranNo) throws Exception{
		
		System.out.println("/purchase/json/updatePurchase : GET");
		
		Purchase purchase = new Purchase();
		
		purchase = purchaseService.getPurchase(tranNo);
		purchase.setPaymentOption(purchase.getPaymentOption().trim());
		purchase.setTranCode(purchase.getTranCode().trim());
		
		return purchase;
	}
	
	@RequestMapping(value="json/updatePurchase", method = RequestMethod.POST)
	public Purchase updatePurchase(@RequestBody Purchase purchase) throws Exception {
		
		System.out.println("/purchase/json/updatePurchase : POST");
		
		purchaseService.updatePurcahse(purchase);
		//purchase.setPaymentOption(purchase.getPaymentOption().trim());
		//purchase.setTranCode(purchase.getTranCode().trim());
		
		return purchase;
	}
	
	@RequestMapping(value="json/updateTranCode/{tranNo}/{tranCode}", method = RequestMethod.GET)
	public Purchase updateTranCode( @PathVariable("tranNo") int tranNo, @PathVariable("tranCode") String tranCode ) throws Exception {
		
		System.out.println("/purchase/json/updateTranCode : GET");
		
		Purchase purchase = new Purchase();
		
		purchase.setTranCode(tranCode);
		purchase.setTranNo(tranNo);
		purchaseService.updateTranCode(purchase);
		
		return purchase;
	}
}
