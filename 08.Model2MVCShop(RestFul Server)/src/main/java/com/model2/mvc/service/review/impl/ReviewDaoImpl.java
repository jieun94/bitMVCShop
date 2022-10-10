package com.model2.mvc.service.review.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Review;
import com.model2.mvc.service.review.ReviewDao;

@Repository("reviewDaoImpl")
public class ReviewDaoImpl implements ReviewDao {
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public ReviewDaoImpl() {
		System.out.println(this.getClass());
	}
	
	public void insertReview(Review review) throws Exception {
		sqlSession.insert("ReviewMapper.insertReview", review);
	}
	
	public List<Review> getReviewList(int prodNo, Search search) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", search);
		map.put("prodNo", prodNo);
		return sqlSession.selectList("ReviewMapper.getReviewList", map);
	}

//	public List<Review> getReviewList(int prodNo) throws Exception {
//		return sqlSession.selectList("ReviewMapper.getReviewList", prodNo);
//	}

	public int getTotalCount(int prodNo) throws Exception {
		return sqlSession.selectOne("ReviewMapper.getTotalCount", prodNo);
	}
	
	public Review getReview(int reviewNo) throws Exception {
		return sqlSession.selectOne("ReviewMapper.getReview", reviewNo);
	}

}
