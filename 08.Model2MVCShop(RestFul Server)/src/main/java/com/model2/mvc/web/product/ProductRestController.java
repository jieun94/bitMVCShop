package com.model2.mvc.web.product;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

@RestController
@RequestMapping("/product/*")
public class ProductRestController {
	
	//Filed
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService prodService;
	
	//Constructor
	public ProductRestController(){
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	@RequestMapping(value="json/addProduct", method=RequestMethod.POST)
	public Map addProduct(@RequestBody Product product) throws Exception {

		System.out.println("/product/json/addProduct : POST");
		
//		Map<String, MultipartFile> files = request.getFileMap();
//		CommonsMultipartFile cmf = (CommonsMultipartFile) files.get("file");
//		String path ="C:/Users/user/git/repository/07MiniProject/07.Model2MVCShop(URI,pattern)/WebContent/images/uploadFiles/"+cmf.getOriginalFilename();
//		prod.setFileName(cmf.getOriginalFilename());
//	    	
//    	File f = new File(path);
//    	cmf.transferTo(f);
		
		//prod.setManuDate(prod.getManuDate().replace("-", ""));
		prodService.addProduct(product);
		
		Map map = new HashMap();
		map.put("product", product);
		
		return map;
	}
	
	@RequestMapping(value="json/getProduct/{prodNo}/{menu}", method=RequestMethod.GET )
	public Map getProduct(@PathVariable("prodNo") int prodNo, @PathVariable("menu") String menu) throws Exception {

		System.out.println("/product/json/getProduct : GET");
		
		Product prod = prodService.getProduct(prodNo);
		
		Map map = new HashMap();
		map.put("prod", prod);
		map.put("menu", menu);
		
		return map;
	}
	
	@RequestMapping(value="json/updateProduct", method=RequestMethod.POST )
	public Product updateProduct(@RequestBody Product prod) throws Exception {
		
		System.out.println("/product/json/updateProduct : POST");
		
		prod.setManuDate(prod.getManuDate().replace("-", ""));
		prodService.updateProduct(prod);
		
		return prod;
	}
	
	@RequestMapping(value="json/listProduct", method = RequestMethod.GET)
	public Map listProduct() throws Exception{
		
		System.out.println("/product/json/listProduct : GET");
		
		Search search = new Search();
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		
		// Business logic 수행
		Map<String , Object> map=prodService.getProductList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model 과 View 연결
		Map map01 = new HashMap();
		map01.put("list", map.get("list"));
		map01.put("resultPage", resultPage);
		map01.put("search", search);
		
		
		return map01;
	}
	
	@RequestMapping(value="json/listProduct", method = RequestMethod.POST)
	public Map listProduct(@RequestBody Search search) throws Exception{
		
		System.out.println("/product/json/listProduct : POST");
		
		//search = new Search();
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		
		// Business logic 수행
		Map<String , Object> map=prodService.getProductList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model 과 View 연결
		Map map01 = new HashMap();
		map01.put("list", map.get("list"));
		map01.put("resultPage", resultPage);
		map01.put("search", search);
		
		
		return map01;
	}
	
}
