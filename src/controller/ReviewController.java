package controller;

import java.util.ArrayList;
import java.util.List;

import model.ReviewDAO;
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
            if (duplicate) { //true = 중복 
               // 중복된 리뷰는 작성 불가해요~ UI로 던져
               //SuccessView.duplicated();
               return;
            }
            boolean check = ReviewDAO.createReview(input);
            SuccessView.create(check);
         } else if (reply.equals("2")) { // 리뷰 조회
            String function = OperationView.readSearchFunctionView();
            selectReview(function);
         } else if (reply.equals("3")) { // 리뷰 수정
        	ArrayList<ReviewDTO> myReviews = ReviewDAO.getMyReviews();
        	OperationView.selectMyReview(myReviews);
        	
        	int reviewId = OperationView.readReviewIdView();
            String newContent = OperationView.readContentView();
            
            ReviewDAO.updateContent(reviewId, newContent);
         } else { // 리뷰 삭제
				ArrayList<ReviewDTO> myReviews = ReviewDAO.getMyReviews();
				OperationView.selectMyReview(myReviews);

				int reviewId = OperationView.readReviewIdView();

				ReviewDAO.deleteReview(reviewId);
         }
      } catch (Exception e) {
         FailureView.FailRead();
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
           switch (ck) {
               case "1": // 전체 리뷰 조회
                   ReviewDAO.getAllReviews();
                   break;

               case "2": // 별점 높은순 조회
                   ArrayList<ReviewDTO> result = ReviewDAO.getReviewsByScoreDesc();
                   break;

               case "3": // price 낮은 순 조회
	               result = ReviewDAO.getReviewsSortedByPriceAsc();
	               SuccessView.topPriceRead(result);
                   break;

               case "4": // 내가 쓴 리뷰 조회
                   result = ReviewDAO.getMyReviews();
                   SuccessView.myReviewRead(result);
               break;

               case "5": // 연령대와 성별로 리뷰 조회
                   String gender = "M"; // 성별 입력값 (예: "M" 또는 "F")
                   int age = 30; // 연령 입력값 (예: 30)
                   ReviewDAO.getReviewByAgeAndGender(gender, age);
                   break;

               case "6": // gender 별 조회
                   String genderForFilter = "F"; // 성별 입력값
                   //ReviewDAO.getReviewsByGender(genderForFilter);
                   break;

               case "7": // category 별 조회
                   String category = "Electronics"; // 카테고리 입력값
                   ReviewDAO.getReviewsByCategory(category);
                   break;

               default:
                   FailureView.FailRead();
                   return;
           }
           //SuccessView.Message("리뷰가 등록되었습니다.");
       } catch (Exception e) {
           FailureView.FailRead();
       }
   }
}
