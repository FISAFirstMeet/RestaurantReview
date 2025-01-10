package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.ReviewDTO;
import probono.model.dto.ActivistDTO;

public class ReviewDAO {
	
	// C
	
	// R

	// 3
	public static ArrayList<ReviewDTO> getReviewsSortedByPriceDesc() {
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
	public static ArrayList<ReviewDTO> getMyReviews(String userId) {
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
