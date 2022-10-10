package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;

public class UpdatePurchaseAction extends Action {
	
public String execute(	HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PurchaseVO pcVO = new PurchaseVO();
		UserVO userVO = new UserVO();
		userVO.setUserId(request.getParameter("buyerId"));
		pcVO.setTranNo(Integer.parseInt(request.getParameter("tranNo")));
		pcVO.setPaymentOption(request.getParameter("paymentOption"));
		pcVO.setReceiverName(request.getParameter("receiverName"));
		pcVO.setReceiverPhone(request.getParameter("receiverPhone"));
		pcVO.setDivyAddr(request.getParameter("receiverAddr"));
		pcVO.setDivyRequest(request.getParameter("receiverRequest"));
		pcVO.setDivyDate(request.getParameter("divyDate"));
		pcVO.setBuyer(userVO);
		
		request.setAttribute("pcVO", pcVO);
		
		System.out.println(pcVO);
		
		PurchaseService service = new PurchaseServiceImpl();
		service.updatePurcahse(pcVO);
		
		return "redirect:/getPurchase.do?tranNo="+pcVO.getTranNo();
	}

}
