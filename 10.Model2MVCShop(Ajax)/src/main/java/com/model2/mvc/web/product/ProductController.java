package com.model2.mvc.web.product;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.review.ReviewService;

@Controller
@RequestMapping("/product/*")
public class ProductController {
	
	//Filed
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService prodService;
	@Autowired
	private ReviewService reviewService;
	
	//Constructor
	public ProductController(){
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	@Value("#{commonProperties['path']}")
	String path;
	
	@RequestMapping(value="addProduct", method = RequestMethod.GET)
	public String addProduct() throws Exception {

		System.out.println("/product/addProduct : GET");
		
		return "forward:/product/addProductView.jsp";
	}
	
	@RequestMapping(value="addProduct", method = RequestMethod.POST)
	public String addProduct(@ModelAttribute("prod") Product prod, MultipartHttpServletRequest request) throws Exception {

		System.out.println("/product/addProduct : POST");
		
		Map<String, MultipartFile> files = request.getFileMap();
		CommonsMultipartFile cmf = (CommonsMultipartFile) files.get("file");
		
		if (cmf.getSize()!=0) {
			//원본 파일명
			String originalFileName = cmf.getOriginalFilename();
			//확장자를 제외한 새로운 파일명
			String newFileName = originalFileName.substring(0, originalFileName.lastIndexOf("."))+"_result";
			//파일 확장자
			String fileExt = originalFileName.substring(originalFileName.lastIndexOf("."));
			prod.setFileName(newFileName+fileExt);
		    	
	    	File f = new File(path+originalFileName);
	    	cmf.transferTo(f);
	    	
	    	BufferedImage image = ImageIO.read(new File(path+originalFileName)); 
			Graphics g = image.getGraphics(); 
			Font titleFont = new Font("Serif", Font.PLAIN, 30);
			Font nameFont = new Font("Serif", Font.PLAIN, 15);
			g.setColor(Color.black);
			g.setFont(titleFont); 
			g.drawString("Hello World!", 570, 130); 
			g.setFont(nameFont); 
			g.drawString("저자명", 750, 170); 
			g.dispose(); 
			
			//텍스트 합성 이미지 생성
			ImageIO.write(image, "png", new File(path+newFileName+fileExt)); 
		}
		
		prod.setManuDate(prod.getManuDate().replace("-", ""));
		
		prodService.addProduct(prod);
		
		prod.setProdNo(prodService.getProdNo());
		
		return "forward:/product/getProduct.jsp";
	}
	
	@RequestMapping(value="getProduct", method=RequestMethod.GET )
	public String getProduct(@RequestParam("prodNo") int prodNo, @RequestParam("menu") String menu, Model model, @RequestParam("currentPage")int currentPage ,Search search) throws Exception {

		System.out.println("/product/getProduct : GET");
		
		Product prod = prodService.getProduct(prodNo);
		
		// listReview 수행
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		} else {
			search.setCurrentPage(currentPage);
		}
		search.setPageSize(pageSize);
		
		Map<String , Object> map=reviewService.getReviewList(prodNo, search);
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);

		
		// Model 과 View 연결
		if (map!=null) {
			model.addAttribute("list", map.get("list"));
			model.addAttribute("search", search);
			model.addAttribute("resultPage", resultPage);
		}
		model.addAttribute("prod", prod);
		
		if (menu.contentEquals("manage")) {
			prod.setManuDate(prod.getManuDate().substring(0, 4)+"-"
					+prod.getManuDate().substring(4 , 6)+"-"+prod.getManuDate().substring(6));
			return "forward:/product/updateProductView.jsp";
		} else {
			return "forward:/product/getProduct.jsp";
		}
	}
	
	@RequestMapping(value="updateProduct", method=RequestMethod.POST )
	public String updateProduct(@ModelAttribute("prod") Product prod) throws Exception {
		
		System.out.println("/product/updateProduct : POST");
		
		prod.setManuDate(prod.getManuDate().replace("-", ""));
		prodService.updateProduct(prod);
		
		return "forward:/product/getProduct.jsp";
	}
	
	@RequestMapping(value="listProduct")
	public String listProduct( @ModelAttribute("search") Search search , Model model) throws Exception{
		
		System.out.println("/product/listProduct : GET / POST");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		
		// Business logic 수행
		Map<String , Object> map=prodService.getProductList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model 과 View 연결
		model.addAttribute("search", search);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		
		
		return "forward:/product/listProduct.jsp";
	}
}
