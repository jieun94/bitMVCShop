package com.model2.mvc.web.product;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	
	@Value("#{commonProperties['path']}")
	String path;
	
	@RequestMapping(value="json/addProduct", method=RequestMethod.POST)
	public Map addProduct(@RequestParam("file") MultipartFile multipartFile) throws Exception {

		System.out.println("/product/json/addProduct : POST");
		
		String fileRoot = path;	//저장될 외부 파일 경로
		String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
		//String newFileName = originalFileName.substring(0,originalFileName.lastIndexOf("."));
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
				
		String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명
		//String savedFileName = newFileName + extension;	//저장될 파일 명
		
		File targetFile = new File(fileRoot + savedFileName);
		
		Map map = new HashMap();
		
		try {
			multipartFile.transferTo(targetFile);	//파일 저장
			map.put("url", "/images/uploadFiles/"+savedFileName);
			map.put("responseCode", "success");
				
		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
			map.put("responseCode", "error");
			e.printStackTrace();
		}
		
		return map;
	}
	
	@RequestMapping(value="json/getProduct/{prodNo}", method=RequestMethod.GET )
	public Product getProduct(@PathVariable("prodNo") int prodNo) throws Exception {

		System.out.println("/product/json/getProduct : GET");
		
//		Product prod = prodService.getProduct(prodNo);
//		
//		Map map = new HashMap();
//		map.put("product", prod);
		
		return prodService.getProduct(prodNo);
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
