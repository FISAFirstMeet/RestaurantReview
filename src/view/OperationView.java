package view;

import model.domain.Category;
import model.domain.Gender;
import model.dto.ReviewDTO;

public class OperationView {
	public static Category reviewsByCategoryView() {
		return null;
	}
	
	public static int reviewByAgeView() {
		return 0;
	}
	
	public static Gender reviewByGenderView() {
		return null;
	}
	
	// AgeAndGender는 이미 존재하는 Age, Gender 두번 부르기
	
	public static ReviewDTO createView() {
		return null;
	}
	
	public static int updateReviewIdView() {
		return 0;
	}
	
	public static String updateContentView() {
		return null;
	}

	public static int deleteView() {
		return 0;
	}
}
