package com.model2.mvc.service.product.impl;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDAO;

public class ProductServiceImpl implements ProductService{
	
	private ProductDAO dao;
	
	public ProductServiceImpl() {
		dao = new ProductDAO();
	}
	
	public void addProduct(Product productVO) throws Exception {
		dao.insertProduct(productVO);
	}

	public Product getProduct(int prodNo) throws Exception {
		return dao.findProduct(prodNo);
	}

	public Map<String,Object> getProductList(Search searchVO) throws Exception {
		return dao.getProductList(searchVO);
	}

	public void updateProduct(Product productVO) throws Exception {
		dao.updateProduct(productVO);
	}
}
