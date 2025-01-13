package controller;

import java.util.ArrayList;
import java.util.List;

import model.ReviewDAO;
import model.domain.Category;
import model.domain.Gender;
import model.domain.UserInfo;
import model.dto.ReviewDTO;
import view.FailureView;
import view.OperationView;
import view.SuccessView;

public class ReviewController {
// 1~5
   public static void choice(String reply) {
      try {
         if (reply.equals("1")) { // 리뷰 등록
            // 리뷰 등록 ov 호출
            ReviewDTO input = OperationView.createReview();
            boolean duplicate = checkDuplicated(input);
            if(duplicate) { //true면 중복
            		SuccessView.duplicated(duplicate);
            }
            else {
            	boolean check = ReviewDAO.createReview(input);
            	SuccessView.create(check);
            }
         } else if (reply.equals("2")) { // 리뷰 조회
            String function = OperationView.readSearchFunctionView();
            selectReview(function);
         } else if (reply.equals("3")) { // 리뷰 수정
        	ArrayList<ReviewDTO> myReviews = ReviewDAO.getMyReviews();
        	OperationView.selectMyReview(myReviews);
        	
        	int reviewId = OperationView.readReviewIdView();
            String newContent = OperationView.readContentView();
            
            boolean check = ReviewDAO.updateContent(reviewId, newContent);
            SuccessView.update(check);
         } else { // 리뷰 삭제
				ArrayList<ReviewDTO> myReviews = ReviewDAO.getMyReviews();
				OperationView.selectMyReview(myReviews);

				int reviewId = OperationView.deleteView();

				boolean check = ReviewDAO.deleteReview(reviewId);
				SuccessView.delete(check);
         }
      } catch (Exception e) {
         FailureView.FailRead(e);
      }
   }

   private static boolean checkDuplicated(ReviewDTO input) throws Exception{
      // 중복 처리
      // 같은 userId가 같은 날 같은 음식점 같은 음식을 먹으면?
      return ReviewDAO.checkReview(input);
   }

//R
   public static void selectReview(String ck) {
       try {
    	   ArrayList<ReviewDTO> result;
    	   Gender gender;
    	   int age;
           switch (ck) {
               case "1": // 전체 리뷰 조회
                   result = ReviewDAO.getAllReviews();
                   SuccessView.allReviewRead(result);
                   break;

               case "2": // 별점 높은순 조회
                   result = ReviewDAO.getReviewsByScoreDesc();
                   SuccessView.topRatedRead(result);
                   break;

               case "3": // price 낮은 순 조회
	               result = ReviewDAO.getReviewsSortedByPriceAsc();
	               SuccessView.topPriceRead(result);
                   break;

               case "4": // 내가 쓴 리뷰 조회
                   result = ReviewDAO.getMyReviews();
                   SuccessView.myReviewRead(result);
                   break;
               
               case "5": // 연령대 별 조회
            	   age = OperationView.reviewByAgeView();
            	   result = ReviewDAO.getReviewByAge(age);
            	   SuccessView.ageRead(result);
            	   break;
            	   
               case "6": // gender 별 조회
            	   gender = OperationView.reviewByGenderView(); // 성별 입력값
                   result = ReviewDAO.getReviewByGender(gender);
                   SuccessView.genderRead(result);
                   break;

               case "7": // 연령대와 성별로 리뷰 조회
                   gender = OperationView.reviewByGenderView(); // 성별 입력값 (예: "M" 또는 "F")
                   age = OperationView.reviewByAgeView(); // 연령 입력값 (예: 30)
                   result = ReviewDAO.getReviewByAgeAndGender(gender, age);
                   SuccessView.ageAndGenderRead(result);
                   break;

               case "8": // category 별 조회
                   Category category = OperationView.reviewsByCategoryView(); // 카테고리 입력값
                   result = ReviewDAO.getReviewsByCategory(category);
                   SuccessView.categoryRead(result);
                   break;

               default:
//                   FailureView.FailRead(e);
                   return;
           }
           //SuccessView.Message("리뷰가 등록되었습니다.");
       } catch (Exception e) {
           FailureView.FailRead(e);
       }
   }
}
