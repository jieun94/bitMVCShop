package com.model2.mvc.web.review;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Review;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.review.ReviewService;

@RestController
@RequestMapping("/review/*")
public class ReviewRestController {
		
	//Field
	@Autowired
	@Qualifier("reviewServiceImpl")
	private ReviewService reviewService;
	@Autowired
	private PurchaseService purchaseService;
	@Autowired
	private ProductService prodService;
	//@Autowired
	//private UserService userService;
	
	//Constructor
	public ReviewRestController(){
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	@RequestMapping(value="json/getReview/{reviewNo}", method=RequestMethod.GET )
	public Review getReview(@PathVariable("reviewNo") int reviewNo) throws Exception {

		System.out.println("/review/json/getReview : GET");
		
//		Product prod = prodService.getProduct(prodNo);
//		
//		Map map = new HashMap();
//		map.put("product", prod);
		
		return reviewService.getReview(reviewNo);
	}
	
	@RequestMapping(value="json/listReview/{prodNo}", method=RequestMethod.POST )
	public Map listReview(@PathVariable("prodNo") int prodNo, Search search) throws Exception {

		System.out.println("/review/json/listReview : POST");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic 수행
		Map<String , Object> map=reviewService.getReviewList(prodNo, search);
//		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
//		System.out.println(resultPage);
		
		// Model 과 View 연결
		Map map01 = new HashMap();
		map01.put("list", map.get("list"));
		map01.put("search", search);

//		Product prod = prodService.getProduct(prodNo);
//		
//		Map map = new HashMap();
//		map.put("product", prod);
		
		return map01;
	}

}
