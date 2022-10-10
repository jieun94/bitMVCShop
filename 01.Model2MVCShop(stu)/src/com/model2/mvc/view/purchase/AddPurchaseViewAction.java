package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;

public class AddPurchaseViewAction extends Action{
	
	public String execute(	HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int prodNo=Integer.parseInt(request.getParameter("prod_no"));
		
		ProductService prodService=new ProductServiceImpl();
		ProductVO prodVO=prodService.getProduct(prodNo);
		
		request.setAttribute("prodVO", prodVO);
		
		HttpSession session = request.getSession();
		UserVO dbVO = (UserVO)session.getAttribute("user");
		
		request.setAttribute("userVO", dbVO);
		
		return "forward:/purchase/addPurchaseView.jsp";
	}
}
