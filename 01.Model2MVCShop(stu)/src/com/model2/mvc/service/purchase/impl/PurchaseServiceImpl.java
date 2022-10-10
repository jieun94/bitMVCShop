package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.purchaseDAO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class PurchaseServiceImpl implements PurchaseService{
	
	private ProductDAO prodDAO;
	private purchaseDAO dao;
	
	public PurchaseServiceImpl(){
		dao = new purchaseDAO();
	}
	
	public void addPurchase(PurchaseVO purchaseVO) throws Exception{
		dao.insertPurchase(purchaseVO);
	}
	
	public PurchaseVO getPurchase(int tranNo) throws Exception{
		return dao.findPurchase(tranNo);
	}
	
	public PurchaseVO getPurchase2(int ProdNo) throws Exception{
		return dao.findPurchase2(ProdNo);
	}
	
	public HashMap<String,Object> getPurchaseList(SearchVO searchVO,String buyerId) throws Exception{
		return dao.getPurchaseList(searchVO,buyerId);
	}
	
	public HashMap<String,Object> getSaleList(SearchVO searchVO) throws Exception{
		return dao.getSaleList(searchVO);
	}
	
	public void updatePurcahse(PurchaseVO purchaseVO) throws Exception{
		dao.updatePurchase(purchaseVO);
	}
	
	public void updateTranCode(PurchaseVO purchaseVO) throws Exception{
		dao.updateTranCode(purchaseVO);
	}
	
	
}
