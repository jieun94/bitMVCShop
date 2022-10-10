package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.dao.purchaseDAO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class ProductDAO {
	
	public ProductDAO() {
	}
	
	public void insertProduct(ProductVO productVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		String sql = "INSERT INTO product VALUES(seq_product_prod_no.NEXTVAL,?,?,?,?,?,sysdate)"; 
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, productVO.getProdName());
		pstmt.setString(2, productVO.getProdDetail());
		pstmt.setString(3, productVO.getManuDate());
		pstmt.setInt(4, productVO.getPrice());
		pstmt.setString(5, productVO.getFileName());
		
		pstmt.executeUpdate();
		
		con.close();
		
	}
	
	public ProductVO findProduct(int productNo) throws Exception {
		
		Connection con = DBUtil.getConnection();
		String sql = "SELECT * FROM product WHERE prod_no=?"; 
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, productNo);
		
		ResultSet rs = pstmt.executeQuery();
		
		ProductVO productVO=null;
		while (rs.next()) {
			productVO = new ProductVO();
			productVO.setProdNo(rs.getInt("prod_no"));
			productVO.setProdName(rs.getString("prod_name"));
			productVO.setProdDetail(rs.getString("prod_detail"));
			productVO.setManuDate(rs.getString("manufacture_day"));
			productVO.setPrice(rs.getInt("price"));
			productVO.setFileName(rs.getString("image_file"));
			productVO.setRegDate(rs.getDate("reg_date"));
		}
		
		con.close();
		
		return productVO;
	}
	
	public HashMap<String,Object> getProductList(SearchVO searchVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		String sql = "SELECT p.prod_no, p.prod_name, p.prod_detail, p.manufacture_day, p.price, p.image_file, p.reg_date, NVL(t.tran_status_code, '0') tran_status_code, NVL(t.tran_no, '0') tran_no"
				+ " FROM product p, transaction t WHERE p.prod_no = t.prod_no(+)";
		if (searchVO.getSearchCondition() != null) {
			if (searchVO.getSearchCondition().equals("0")) {
				sql += " AND p.prod_no LIKE('%" + searchVO.getSearchKeyword()
						+ "%')";
			} else if (searchVO.getSearchCondition().equals("1")) {
				sql += " AND p.prod_name LIKE('%" + searchVO.getSearchKeyword()
						+ "%')";
			}
		}
		sql += " ORDER BY p.prod_no DESC";
		
		PreparedStatement pstmt = 
				con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = pstmt.executeQuery();

		rs.last();
		int total = rs.getRow();
		System.out.println("로우의 수:" + total);
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("count", new Integer(total));

		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1);
		System.out.println("searchVO.getPage():" + searchVO.getPage());
		System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());
		
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		if (total > 0) {
			for (int i = 0; i < searchVO.getPageUnit(); i++) {
				ProductVO vo = new ProductVO();
				vo.setProdNo(rs.getInt("prod_no"));
				vo.setProdName(rs.getString("prod_name"));
				vo.setProdDetail(rs.getString("prod_detail"));
				vo.setManuDate(rs.getString("manufacture_day"));
				vo.setPrice(rs.getInt("price"));
				vo.setFileName(rs.getString("image_file"));
				vo.setRegDate(rs.getDate("reg_date"));
				vo.setProTranCode(rs.getString("tran_status_code"));
				vo.setTranNo(rs.getInt("tran_no"));

				list.add(vo);
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
	
	public ProductVO updateProduct(ProductVO productVO) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "UPDATE product SET prod_name=?, prod_detail=?, manufacture_day=?, price =? , image_file = ? "
				+ "WHERE prod_no=?";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, productVO.getProdName());
		pstmt.setString(2, productVO.getProdDetail());
		pstmt.setString(3, productVO.getManuDate());
		pstmt.setInt(4, productVO.getPrice());
		pstmt.setString(5, productVO.getFileName());
		pstmt.setInt(6, productVO.getProdNo());
		pstmt.executeUpdate();
		
		con.close();
		
		return productVO;
	}
}
