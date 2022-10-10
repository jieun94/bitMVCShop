package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class UpdateProductAction extends Action{
	
	public String execute(	HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Product productVO=new Product();
		productVO.setProdName(request.getParameter("prodName"));
		productVO.setProdDetail(request.getParameter("prodDetail"));
		if (request.getParameter("manuDate").indexOf("-")==-1) {
			productVO.setManuDate(request.getParameter("manuDate"));
		}else {
			productVO.setManuDate(request.getParameter("manuDate").replace("-", ""));
		}
		productVO.setPrice(Integer.parseInt(request.getParameter("price")));
		productVO.setFileName(request.getParameter("fileName"));
		productVO.setProdNo(Integer.parseInt(request.getParameter("prodNo")));
		
		System.out.println(productVO);
		
		request.setAttribute("prodVO", productVO);
		
		ProductService service=new ProductServiceImpl();
		service.updateProduct(productVO);
		
		return "forward:/product/getProduct.jsp";
	}

}
