package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

public class purchaseDAO {

	public purchaseDAO() {
	}
	
	public void insertPurchase(PurchaseVO purchaseVO) throws Exception {
		Connection con = DBUtil.getConnection();
		
		String sql = "INSERT INTO transaction VALUES(seq_transaction_tran_no.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, '1', sysdate, ?)"; 
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, purchaseVO.getPurchaseProd().getProdNo()); 	
		pstmt.setString(2, purchaseVO.getBuyer().getUserId()); 
		pstmt.setString(3, purchaseVO.getPaymentOption());
		pstmt.setString(4, purchaseVO.getReceiverName());
		pstmt.setString(5, purchaseVO.getReceiverPhone());
		pstmt.setString(6, purchaseVO.getDivyAddr());
		pstmt.setString(7, purchaseVO.getDivyRequest());
		pstmt.setString(8, purchaseVO.getDivyDate());
		
		pstmt.executeUpdate();
		
		con.close();
	}
	
	public PurchaseVO findPurchase(int tranNo) throws Exception{
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT * FROM transaction t, product p, users u WHERE t.prod_no = p.prod_no AND u.user_id = t.buyer_id AND t.tran_no = ?"; 
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, tranNo);
		
		ResultSet rs = pstmt.executeQuery();
		
		PurchaseVO purchaseVO=null;
		while (rs.next()) {
			purchaseVO = new PurchaseVO();
			ProductVO prodVO = new ProductVO();
			UserVO userVO = new UserVO();
			
			userVO.setUserId(rs.getString("user_id"));
			purchaseVO.setBuyer(userVO);
			prodVO.setProdNo(rs.getInt("prod_no"));
			purchaseVO.setPurchaseProd(prodVO);
			purchaseVO.setPaymentOption(rs.getString("payment_option"));
			purchaseVO.setReceiverName(rs.getString("receiver_name"));
			purchaseVO.setReceiverPhone(rs.getString("receiver_phone"));
			purchaseVO.setDivyAddr(rs.getString("demailaddr"));
			purchaseVO.setDivyRequest(rs.getString("dlvy_request"));
			purchaseVO.setOrderDate(rs.getDate("order_data"));
			purchaseVO.setDivyDate(rs.getString("dlvy_date"));
			purchaseVO.setTranNo(rs.getInt("tran_no"));
		}
		
		con.close();
		
		return purchaseVO;
	}
	
public PurchaseVO findPurchase2(int prodNo) throws Exception{
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT * FROM transaction t, product p, users u WHERE t.prod_no = p.prod_no AND u.user_id = t.buyer_id AND p.prod_no = ?"; 
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, prodNo);
		
		ResultSet rs = pstmt.executeQuery();
		
		PurchaseVO purchaseVO=null;
		while (rs.next()) {
			purchaseVO = new PurchaseVO();
			ProductVO prodVO = new ProductVO();
			UserVO userVO = new UserVO();
			
			userVO.setUserId(rs.getString("user_id"));
			purchaseVO.setBuyer(userVO);
			prodVO.setProdNo(rs.getInt("prod_no"));
			purchaseVO.setPurchaseProd(prodVO);
			purchaseVO.setPaymentOption(rs.getString("payment_option"));
			purchaseVO.setReceiverName(rs.getString("receiver_name"));
			purchaseVO.setReceiverPhone(rs.getString("receiver_phone"));
			purchaseVO.setDivyAddr(rs.getString("demailaddr"));
			purchaseVO.setDivyRequest(rs.getString("dlvy_request"));
			purchaseVO.setOrderDate(rs.getDate("order_data"));
			purchaseVO.setDivyDate(rs.getString("dlvy_date"));
			purchaseVO.setTranNo(rs.getInt("tran_no"));
		}
		
		con.close();
		
		return purchaseVO;
	}
	
	public HashMap<String, Object> getPurchaseList(SearchVO searchVO, String userId) throws Exception {
		Connection con = DBUtil.getConnection();
		
		
		String sql = "SELECT * FROM transaction WHERE buyer_id = ? ";
		
		PreparedStatement pstmt = 
				con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		pstmt.setString(1, userId);
		ResultSet rs = pstmt.executeQuery();

		rs.last();
		int total = rs.getRow();
		System.out.println("로우의 수:" + total);
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("count", new Integer(total));

		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1);
		System.out.println("searchVO.getPage():" + searchVO.getPage());
		System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());
		
		ArrayList<PurchaseVO> list = new ArrayList<PurchaseVO>();
		if (total > 0) {
			for (int i = 0; i < searchVO.getPageUnit(); i++) {
				PurchaseVO pcVO = new PurchaseVO();
				UserVO userVO = new UserVO();
				userVO.setUserId(rs.getString("buyer_id"));
				pcVO.setReceiverName(rs.getString("RECEIVER_NAME"));
				pcVO.setReceiverPhone(rs.getString("RECEIVER_PHONE"));
				pcVO.setTranCode(rs.getString("tran_status_code"));
				pcVO.setTranNo(rs.getInt("tran_no"));
				pcVO.setBuyer(userVO);
				list.add(pcVO);
				if (!rs.next())
					break;
			}
		}
		System.out.println("list.size() : "+ list.size());
		map.put("list", list);
		System.out.println("map().size() : "+ map.size());

		con.close();
		return map;
	}
	
	public HashMap<String,Object> getSaleList(SearchVO searchVO) throws Exception {
		
		
	return null;
	}
	
	public void updatePurchase(PurchaseVO purchaseVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "UPDATE transaction SET payment_option = ?, receiver_name = ?, receiver_phone = ?, "
				+ "demailaddr = ?, dlvy_request = ?, dlvy_date = ? WHERE tran_no = ?";
		PreparedStatement pstmt = 
				con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		pstmt.setString(1, purchaseVO.getPaymentOption());
		pstmt.setString(2, purchaseVO.getReceiverName());
		pstmt.setString(3, purchaseVO.getReceiverPhone());
		pstmt.setString(4, purchaseVO.getDivyAddr());
		pstmt.setString(5, purchaseVO.getDivyRequest());
		pstmt.setString(6, purchaseVO.getDivyDate());
		pstmt.setInt(7, purchaseVO.getTranNo());
		
		pstmt.executeUpdate();
		
		con.close();
		
	}
	
	public void updateTranCode(PurchaseVO purchaseVO) throws Exception {
		Connection con = DBUtil.getConnection();
		String sql = "UPDATE transaction SET tran_status_code = ? WHERE tran_no = ?";
		
		PreparedStatement pstmt = 
				con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		pstmt.setString(1, purchaseVO.getTranCode());
		pstmt.setInt(2, purchaseVO.getTranNo());
		
		pstmt.executeUpdate();
		
		con.close();
		
	}
}
