package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;

public class AddPurchaseAction extends Action{
	
	public String execute(	HttpServletRequest request, HttpServletResponse response) throws Exception {
		////////////ProductVO////////////
		int prodNo=Integer.parseInt(request.getParameter("prodNo"));
		
		ProductService prodService=new ProductServiceImpl();
		ProductVO prodVO=prodService.getProduct(prodNo);
		
		request.setAttribute("prodVO", prodVO);
		//////////////UserVO///////////////
		String userId = request.getParameter("buyerId");
		
		UserService userService = new UserServiceImpl();
		UserVO userVO = userService.getUser(userId);
		
		request.setAttribute("userVO", userVO);
		////////////PurchaseVO/////////////////////
		PurchaseVO pcVO = new PurchaseVO();
		
		pcVO.setPaymentOption(request.getParameter("paymentOption"));
		pcVO.setReceiverName(request.getParameter("receiverName"));
		pcVO.setReceiverPhone(request.getParameter("receiverPhone"));
		pcVO.setDivyAddr(request.getParameter("receiverAddr"));
		pcVO.setDivyRequest(request.getParameter("receiverRequest"));
		pcVO.setDivyDate(request.getParameter("receiverDate"));
		pcVO.setPurchaseProd((ProductVO)request.getAttribute("prodVO"));
		pcVO.setBuyer((UserVO)request.getAttribute("userVO"));
		
		System.out.println(pcVO);
		
		request.setAttribute("pcVO", pcVO);
		
		PurchaseService service=new PurchaseServiceImpl();
		service.addPurchase(pcVO);
		
		return "forward:/purchase/addPurchase.jsp";
	}
}
