package com.model2.mvc.service.product.dao;

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

public class ProductDAO {
	
	public ProductDAO() {
	}
	
	public void insertProduct(Product productVO) throws Exception {
		
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
	
	public Product findProduct(int productNo) throws Exception {
		
		Connection con = DBUtil.getConnection();
		String sql = "SELECT * FROM product WHERE prod_no=?"; 
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, productNo);
		
		ResultSet rs = pstmt.executeQuery();
		
		Product productVO=null;
		while (rs.next()) {
			productVO = new Product();
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
	
	public Map<String,Object> getProductList(Search search) throws Exception {
		
		Map<String , Object> map = new HashMap<String, Object>();
		
		Connection con = DBUtil.getConnection();
		
		//Original Query
		String sql = "SELECT p.prod_no, p.prod_name, p.prod_detail, p.manufacture_day, p.price, p.image_file, p.reg_date,"
				+ " NVL(t.tran_status_code, '0') tran_status_code, NVL(t.tran_no, '0') tran_no"
				+ " FROM product p, transaction t WHERE p.prod_no = t.prod_no(+)";
		
		if (search.getSearchCondition() != null) {
			if (search.getSearchCondition().equals("0")) {
				sql += " AND p.prod_no LIKE('%" + search.getSearchKeyword()
						+ "%')";
			} else if (search.getSearchCondition().equals("1")) {
				sql += " AND p.prod_name LIKE('%" + search.getSearchKeyword()
						+ "%')";
			}
		}
		sql += " ORDER BY p.prod_no DESC";
		
		System.out.println("ProductDAO::Original SQL :: " + sql);
		
		//==> TotalCount GET
				int totalCount = this.getTotalCount(sql);
				System.out.println("UserDAO :: totalCount  :: " + totalCount);
		
		//==> CurrentPage 게시물만 받도록 Query 다시구성
		sql = makeCurrentPageSql(sql, search);
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
	
		System.out.println(search);
		
		List<Product> list = new ArrayList<Product>();
		
		while(rs.next()){
			Product vo = new Product();
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
		}
		
		//==> totalCount 정보 저장
		map.put("totalCount", new Integer(totalCount));
		//==> currentPage 의 게시물 정보 갖는 List 저장
		map.put("list", list);

		rs.close();
		pStmt.close();
		con.close();

		return map;
	}
	
	public Product updateProduct(Product productVO) throws Exception {
		
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
	
	// 게시판 Page 처리를 위한 전체 Row(totalCount)  return
	private int getTotalCount(String sql) throws Exception {
		
		sql = "SELECT COUNT(*) "+
		          "FROM ( " +sql+ ") countTable";
		
		Connection con = DBUtil.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		int totalCount = 0;
		if( rs.next() ){
			totalCount = rs.getInt(1);
		}
		
		pstmt.close();
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
		
		System.out.println("ProductDAO :: make SQL :: "+ sql);	
		
		return sql;
	}
}
