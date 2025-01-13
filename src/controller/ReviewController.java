package controller;

import java.util.ArrayList;

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
            boolean check = checkDuplicated(input);
            if (check) {
               // 중복된 리뷰는 작성 불가해요~ UI로 던져
               //SuccessView.duplicated();
               return;
            }
            ReviewDAO.createReview(input);
         } else if (reply.equals("2")) { // 리뷰 조회
            String function = OperationView.readSearchFunctionView();
            selectReview(function);
         } else if (reply.equals("3")) { // 리뷰 수정
            String col = OperationView.colReview();
            String setdata = OperationView.setDataReview();

            updateReview(col,setdata);
         } else { // 리뷰 삭제
        	 // 리뷰보여주기
            String reviewid = OperateView.getReviewIdView(userid);
            deleteReview(reviewid);
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

//C
   // 생성
   public static void createReview(ReviewDTO reviewDTO) {
      try {
         boolean ck = ReviewDAO.createReview(reviewDTO);
         if (ck = true) {
            //SuccessView.Message("리뷰가 등록되었습니다.");
         } else {
            //SuccessView.Message("리뷰 등록에 실패하였습니다.");
         }
      } catch (Exception e) {
         //FailView.showError("금일 등록된 리뷰입니다.");
      }
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
                   String userId = UserInfo.getInstance().getUserId(); // 사용자 ID를 적절히 전달해야 함
                   result = ReviewDAO.getMyReviews(userId);
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


//U
   public static void updateReview(String col,String setData) {
      try {
         boolean ck = ReviewDAO.createReview(col,setData);
         if(ck=true) {
            //SuccessView.Message("리뷰가 수정되었습니다.");
            ReviewDAO.getAllReviews();
         }else {
            //SuccessView.Message("리뷰 수정에 실패하였습니다.");
         }
      } catch (Exception e) {
         FailureView.FailRead();
      }
   }

//D
   public static void deleteReview(int reviewId) {
      try {
         boolean ck = ReviewDAO.deleteReview(reviewId);
         if (ck = true) {
            SuccessView.delete();
         } else {
            //SuccessView.Message("삭제할 리뷰가 없습니다.");
         }
      } catch (Exception e) {
         FailureView.FailRead();
      }
   }
}
