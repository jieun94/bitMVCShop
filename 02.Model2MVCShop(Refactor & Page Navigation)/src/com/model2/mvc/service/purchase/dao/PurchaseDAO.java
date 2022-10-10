package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;

public class PurchaseDAO {

	public PurchaseDAO() {
	}
	
	public void insertPurchase(Purchase purchaseVO) throws Exception {
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
	
	public Purchase findPurchase(int tranNo) throws Exception{
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT * FROM transaction t, product p, users u WHERE t.prod_no = p.prod_no AND u.user_id = t.buyer_id AND t.tran_no = ?"; 
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, tranNo);
		
		ResultSet rs = pstmt.executeQuery();
		
		Purchase purchaseVO=null;
		while (rs.next()) {
			purchaseVO = new Purchase();
			Product prodVO = new Product();
			User userVO = new User();
			
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
	
	public Purchase findPurchase2(int prodNo) throws Exception{
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT * FROM transaction t, product p, users u WHERE t.prod_no = p.prod_no AND u.user_id = t.buyer_id AND p.prod_no = ?"; 
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, prodNo);
		
		ResultSet rs = pstmt.executeQuery();
		
		Purchase purchaseVO=null;
		while (rs.next()) {
			purchaseVO = new Purchase();
			Product prodVO = new Product();
			User userVO = new User();
			
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
	
	public Map<String, Object> getPurchaseList(Search search, String userId) throws Exception {
		
		Map<String , Object>  map = new HashMap<String, Object>();
		
		Connection con = DBUtil.getConnection();
		
		
		String sql = "SELECT * FROM transaction WHERE buyer_id = '"+userId+"'";
		
		System.out.println("PurchaseDAO::Original SQL :: " + sql);
		
		//==> TotalCount GET
		int totalCount = this.getTotalCount(sql);
		System.out.println("PurchaseDAO :: totalCount  :: " + totalCount);
		
		sql = makeCurrentPageSql(sql, search);
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		System.out.println(search);

		List<Purchase> list = new ArrayList<Purchase>();
		
		while(rs.next()){
			Purchase pcVO = new Purchase();
			User userVO = new User();
			userVO.setUserId(rs.getString("buyer_id"));
			pcVO.setReceiverName(rs.getString("RECEIVER_NAME"));
			pcVO.setReceiverPhone(rs.getString("RECEIVER_PHONE"));
			pcVO.setTranCode(rs.getString("tran_status_code"));
			pcVO.setTranNo(rs.getInt("tran_no"));
			pcVO.setBuyer(userVO);
			list.add(pcVO);
		}
		
		//==> totalCount 정보 저장
		map.put("totalCount", new Integer(totalCount));
		//==> currentPage 의 게시물 정보 갖는 List 저장
		map.put("list", list);

		rs.close();
		pstmt.close();
		con.close();

		return map;
	}
	
	public HashMap<String,Object> getSaleList(Search searchVO) throws Exception {
		
		
	return null;
	}
	
	public void updatePurchase(Purchase purchaseVO) throws Exception {
		
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
	
	public void updateTranCode(Purchase purchaseVO) throws Exception {
		Connection con = DBUtil.getConnection();
		String sql = "UPDATE transaction SET tran_status_code = ? WHERE tran_no = ?";
		
		PreparedStatement pstmt = 
				con.prepareStatement(sql);
		pstmt.setString(1, purchaseVO.getTranCode());
		pstmt.setInt(2, purchaseVO.getTranNo());
		
		pstmt.executeUpdate();
		
		con.close();
		
	}

	// 게시판 Page 처리를 위한 전체 Row(totalCount)  return
	private int getTotalCount(String sql) throws Exception {
		
		sql = "SELECT COUNT(*) "+
		          "FROM ( " +sql+ ") countTable";
		
		Connection con = DBUtil.getConnection();
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
		
		int totalCount = 0;
		if( rs.next() ){
			totalCount = rs.getInt(1);
		}
		
		pStmt.close();
		con.close();
		rs.close();
		
		return totalCount;
	}
	
	// 게시판 currentPage Row 만  return 
	private String makeCurrentPageSql(String sql , Search search){
		sql = 	"SELECT * "+ 
					"FROM (		SELECT inner_table. * ,  ROWNUM AS row_seq " +
									" 	FROM (	"+sql+" ) inner_table "+
									"	WHERE ROWNUM <="+search.getCurrentPage()*search.getPageSize()+" ) " +
					"WHERE row_seq BETWEEN "+((search.getCurrentPage()-1)*search.getPageSize()+1) +" AND "+search.getCurrentPage()*search.getPageSize();
		
		System.out.println("UserDAO :: make SQL :: "+ sql);	
		
		return sql;
	}
}
