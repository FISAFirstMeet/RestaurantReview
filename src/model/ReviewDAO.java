package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import model.dto.ReviewDTO;
import util.DBUtil;

public class ReviewDAO {
	
	// C
	
	// R

	// 3
	public static ArrayList<ReviewDTO> getReviewsSortedByPriceDesc() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ReviewDTO> reviews = null;
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from review order by price desc");
			rset = pstmt.executeQuery();
			
			reviews = new ArrayList<>();
			while (rset.next()) {
				reviews.add(ReviewDTO.builder()
							.reviewId(rset.getInt(1))
							.userId(rset.getString(2))
							.age(rset.getInt(3))
							.gender(rset.getString(4))
							.restaurantName(rset.getString(5))
							.category(rset.getString(6))
							.menu(rset.getString(7))
							.price(rset.getInt(8))
							.content(rset.getString(9))
							.score(rset.getDouble(10))
							.date(rset.getTimestamp(11).toLocalDateTime())
							.build());
			}
		} finally {
			DBUtil.close(conn, pstmt, rset);
		}
		return reviews;
	}
	
	// 4
	public static ArrayList<ReviewDTO> getMyReviews(String userId) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ReviewDTO> reviews = null;
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from review where user_id=?");
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			
			reviews = new ArrayList<>();
			while (rset.next()) {
				reviews.add(ReviewDTO.builder()
							.reviewId(rset.getInt(1))
							.userId(rset.getString(2))
							.age(rset.getInt(3))
							.gender(rset.getString(4))
							.restaurantName(rset.getString(5))
							.category(rset.getString(6))
							.menu(rset.getString(7))
							.price(rset.getInt(8))
							.content(rset.getString(9))
							.score(rset.getDouble(10))
							.date(rset.getTimestamp(11).toLocalDateTime())
							.build());
			}
		} finally {
			DBUtil.close(conn, pstmt, rset);
		}
		return reviews;
	}
	
	//category별 review 조회
	public static ArrayList<ReviewDTO> getReviewsByCategory(String category) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ReviewDTO> reviews = null;
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from review where category=?");
			pstmt.setString(1,category);
			rset = pstmt.executeQuery();
			
			reviews = new ArrayList<>();
			while(rset.next()) {
				reviews.add(ReviewDTO.builder()
						.reviewId(rset.getInt(1))
						.userId(rset.getString(2))
						.age(rset.getInt(3))
						.gender(rset.getString(4))
						.restaurantName(rset.getString(5))
						.category(rset.getString(6))
						.menu(rset.getString(7))
						.price(rset.getInt(8))
						.content(rset.getString(9))
						.score(rset.getDouble(10))
						.date(rset.getTimestamp(11).toLocalDateTime())
						.build());
			}
		} finally {
			DBUtil.close(conn, pstmt, rset);
		}
		
		return reviews;
	}
	
	
	//연령대와 gender 받아서 review 조회
	//연령대는 10단위로 숫자 받아서 계산해야함
	public static ArrayList<ReviewDTO> getReviewByAgeAndGender(String gender, int age) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ReviewDTO> reviews = null;
		
		try {
			int startAge = (age / 10) * 10; // 연령대 시작값 (10단위로 나누어서 계산)
			int endAge = startAge + 9; // 연령대 끝값

			String sql = "select * from review where gender = ? and age between ? and ?";
			
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, gender); // gender는 String 타입으로 가정
			pstmt.setInt(2, startAge);
			pstmt.setInt(3, endAge);
			rset = pstmt.executeQuery();
			
			reviews = new ArrayList<>();
			while(rset.next()) {
				reviews.add(ReviewDTO.builder()
						.reviewId(rset.getInt(1))
						.userId(rset.getString(2))
						.age(rset.getInt(3))
						.gender(rset.getString(4))
						.restaurantName(rset.getString(5))
						.category(rset.getString(6))
						.menu(rset.getString(7))
						.price(rset.getInt(8))
						.content(rset.getString(9))
						.score(rset.getDouble(10))
						.date(rset.getTimestamp(11).toLocalDateTime())
						.build());
			}
		} finally {
			DBUtil.close(conn, pstmt, rset)
		}
		
		return reviews;
	}
		
	// U
	public static boolean updateContent(int reviewId, String content) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtil.getConnection();
			
			pstmt = conn.prepareStatement("update review set content=? where review_id=?");
			pstmt.setString(1, content);
			pstmt.setInt(2, reviewId);
			
			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(conn, pstmt);
		}
		return false;
	}
	
	// D
	
}
