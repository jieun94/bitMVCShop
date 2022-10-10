package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class UpdateTranCodeAction extends Action {
	
	public String execute(	HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PurchaseVO pcVO = new PurchaseVO();
		pcVO.setTranNo(Integer.parseInt(request.getParameter("tranNo")));
		pcVO.setTranCode(request.getParameter("tranCode"));
		
		System.out.println(pcVO);
		
		PurchaseService service=new PurchaseServiceImpl();
		service.updateTranCode(pcVO);
		
		request.setAttribute("pcVO", pcVO);
		
		return "redirect:/listPurchase.do";
	}
}
