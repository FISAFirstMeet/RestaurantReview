package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import model.domain.Category;
import model.domain.Gender;
import model.domain.UserInfo;
import model.dto.ReviewDTO;
import util.DBUtil;
public class ReviewDAO {
	
	private static final UserInfo userInfo = UserInfo.getInstance();
	
	// C
	// 리뷰 등록
	public static boolean createReview(ReviewDTO reviewDTO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		System.out.println("ceraete 들어옴");
			try {
				con = DBUtil.getConnection();
				
				pstmt = con.prepareStatement("INSERT INTO review "
						+ "(user_id, age, gender, restaurant_name, category, menu, price, content, score) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
				pstmt.setString(1, userInfo.getUserId());
				pstmt.setInt(2, userInfo.getAge());
				pstmt.setString(3, userInfo.getGender().name());
				pstmt.setString(4, reviewDTO.getRestaurantName());
				pstmt.setString(5, reviewDTO.getCategory().name());
				pstmt.setString(6, reviewDTO.getMenu());
				pstmt.setInt(7, reviewDTO.getPrice());
				pstmt.setString(8, reviewDTO.getContent());
				pstmt.setDouble(9, reviewDTO.getScore());
				int result = pstmt.executeUpdate();
				if (result == 1) {
					System.out.println("return true");
					return true;
				}
			} finally {
					DBUtil.close(con, pstmt);
			}
			System.out.println("return false");
			return false;
		}
	// R
	//1 전체 review
	public static ArrayList<ReviewDTO> getAllReviews() throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ReviewDTO> reviews = null;
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from review");
			rset = pstmt.executeQuery();
			reviews = new ArrayList<>();
			while (rset.next()) {
				reviews.add(ReviewDTO.builder()
							.reviewId(rset.getInt(1))
							.userId(rset.getString(2))
							.age(rset.getInt(3))
							.gender(Gender.valueOf(rset.getString(4)))
							.restaurantName(rset.getString(5))
							.category(Category.valueOf(rset.getString(6)))
							.menu(rset.getString(7))
							.price(rset.getInt(8))
							.content(rset.getString(9))
							.score(rset.getDouble(10))
							.date(LocalDateTime.now())
							.build());
			}
		} finally {
			DBUtil.close(conn, pstmt, rset);
		}
		
		return reviews;
	}
	
	//2 별점 높은 순
	public static ArrayList<ReviewDTO> getReviewsByScoreDesc() throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ReviewDTO> reviews = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from review order by score desc");
			rset = pstmt.executeQuery();
			reviews = new ArrayList<>();
			while (rset.next()) {
				reviews.add(ReviewDTO.builder()
							.reviewId(rset.getInt(1))
							.userId(rset.getString(2))
							.age(rset.getInt(3))
							.gender(Gender.valueOf(rset.getString(4)))
							.restaurantName(rset.getString(5))
							.category(Category.valueOf(rset.getString(6)))
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
	//R
	public static boolean checkReview(ReviewDTO reviewDTO) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset=null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from review where user_id=? and restaurant_name=? and menu=?");
			System.out.println(userInfo.getUserId());
			pstmt.setString(1, userInfo.getUserId());
			pstmt.setString(2, reviewDTO.getRestaurantName());
			pstmt.setString(3, reviewDTO.getMenu());
			
			rset = pstmt.executeQuery();
			rset.next();
			if (rset.getRow() >= 1) {
				return true;
			}
		} finally {
			DBUtil.close(conn, pstmt);
		}
		return false;
	}
	// 3
	public static ArrayList<ReviewDTO> getReviewsSortedByPriceAsc() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ReviewDTO> reviews = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from review order by price asc");
			rset = pstmt.executeQuery();
			reviews = new ArrayList<>();
			while (rset.next()) {
				reviews.add(ReviewDTO.builder()
							.reviewId(rset.getInt(1))
							.userId(rset.getString(2))
							.age(rset.getInt(3))
							.gender(Gender.valueOf(rset.getString(4)))
							.restaurantName(rset.getString(5))
							.category(Category.valueOf(rset.getString(6)))
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
	public static ArrayList<ReviewDTO> getMyReviews() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ReviewDTO> reviews = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from review where user_id=?");
			pstmt.setString(1, userInfo.getUserId());
			rset = pstmt.executeQuery();
			reviews = new ArrayList<>();
			while (rset.next()) {
				reviews.add(ReviewDTO.builder()
							.reviewId(rset.getInt(1))
							.userId(rset.getString(2))
							.age(rset.getInt(3))
							.gender(Gender.valueOf(rset.getString(4)))
							.restaurantName(rset.getString(5))
							.category(Category.valueOf(rset.getString(6)))
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
	
	// category별 review 조회
	public static ArrayList<ReviewDTO> getReviewsByCategory(Category category) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;
	    ArrayList<ReviewDTO> reviews = null;

	    try {
	        conn = DBUtil.getConnection();
	        pstmt = conn.prepareStatement("select * from review where category=?");
	        pstmt.setString(1, category.name());
	        rset = pstmt.executeQuery();

	        reviews = new ArrayList<>();
	        while (rset.next()) {
	            reviews.add(ReviewDTO.builder()
	                    .reviewId(rset.getInt(1))
	                    .userId(rset.getString(2))
	                    .age(rset.getInt(3))
	                    .gender(Gender.valueOf(rset.getString(4)))
	                    .restaurantName(rset.getString(5))
	                    .category(Category.valueOf(rset.getString(6))) // 영어 값 그대로 매핑
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
	public static ArrayList<ReviewDTO> getReviewByAgeAndGender(Gender gender, int age) throws SQLException{
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
			pstmt.setString(1, gender.name());
			pstmt.setInt(2, startAge);
			pstmt.setInt(3, endAge);
			rset = pstmt.executeQuery();
			
			reviews = new ArrayList<>();
			while(rset.next()) {
				reviews.add(ReviewDTO.builder()
						.reviewId(rset.getInt(1))
						.userId(rset.getString(2))
						.age(rset.getInt(3))
						.gender(Gender.valueOf(rset.getString(4)))
						.restaurantName(rset.getString(5))
						.category(Category.valueOf(rset.getString(6)))
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
	// 5
	// age 별 조회
	public static ArrayList<ReviewDTO> getReviewByAge(int age) throws SQLException {
		int startAge = (age / 10) * 10; // 연령대 시작값 (10단위로 나누어서 계산)
		int endAge = startAge + 9; // 연령대 끝값
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ReviewDTO> reviews = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from review where age between ? and ?");
			pstmt.setInt(1, startAge);
			pstmt.setInt(2, endAge);
			rset = pstmt.executeQuery();
			reviews = new ArrayList<>();
			while (rset.next()) {
				reviews.add(ReviewDTO.builder().reviewId(rset.getInt(1)).userId(rset.getString(2)).age(rset.getInt(3))
						.gender(Gender.valueOf(rset.getString(4))).restaurantName(rset.getString(5))
						.category(Category.valueOf((rset.getString(6)))).menu(rset.getString(7)).price(rset.getInt(8))
						.content(rset.getString(9)).score(rset.getDouble(10))
						.date(rset.getTimestamp(11).toLocalDateTime()).build());
			}
		} finally {
			DBUtil.close(conn, pstmt, rset);
		}
		
		return reviews;
	}
	// 6
	// gender 별 조회
	public static ArrayList<ReviewDTO> getReviewByGender(Gender gender) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ReviewDTO> reviews = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from review where gender=?");
			pstmt.setString(1, gender.name());
			rset = pstmt.executeQuery();
			reviews = new ArrayList<>();
			while (rset.next()) {
				reviews.add(ReviewDTO.builder().reviewId(rset.getInt(1)).userId(rset.getString(2)).age(rset.getInt(3))
						.gender(Gender.valueOf(rset.getString(4))).restaurantName(rset.getString(5))
						.category(Category.valueOf((rset.getString(6)))).menu(rset.getString(7)).price(rset.getInt(8))
						.content(rset.getString(9)).score(rset.getDouble(10))
						.date(rset.getTimestamp(11).toLocalDateTime()).build());
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
	public static boolean deleteReview(int reviewId) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("delete from review where review_id=?");
			pstmt.setInt(1, reviewId);
			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(conn, pstmt);
		}
		return false;
	}
}