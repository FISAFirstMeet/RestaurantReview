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
				reviews.add(ReviewDTO.builder())
							.
			}
		} finally {
			DBUtil.close(conn, pstmt, rset);
		}
		return reviews;
	}
}
