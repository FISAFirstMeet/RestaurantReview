package controller;

import java.util.ArrayList;

import model.ReviewDAO;
import model.dto.ReviewDTO;
import view.FailureView;
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
					SuccessView.duplicated();
					return;
				} 
				ReviewDAO.createReview(input);
			} else if (reply.equals("2")) { // 리뷰 조회
				String ck = OperationView.sReview();
				selectReview(ck);
			} else if (reply.equals("3")) { // 리뷰 수정
				String col = OperationView.colReview();
				String setdata = OperationView.setDataReview();
				
				updateReview(col,setdata);
			} else { // 리뷰 삭제
				String reviewid = OperateView.getReviewIdView(userid);
				deleteReview(reviewid);
			}
		} catch (Exception e) {
			FailureView.printError("시스템 오류입니다.");
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
				SuccessView.Message("리뷰가 등록되었습니다.");
			} else {
				SuccessView.Message("리뷰 등록에 실패하였습니다.");
			}
		} catch (Exception e) {
			FailView.showError("금일 등록된 리뷰입니다.");
		}
	}

//R
	public static void selectReview(String ck) {
		try {
			if (ck.equals("1")) {// 전체 리뷰 조회
				ReviewDAO.allListReview();
			} else if (ck.equals("2")) {// 별점 높은순 조회
				ReviewDAO.topScoreReview();
			} else if (ck.equals("3")) {// price 높은 순 조회
				ReviewDAO.topPriceReview();
			} else if (ck.equals("4")) {// 내가 쓴 리뷰 조회

			} else if (ck.equals("5")) {// age 별 리뷰 조회
				ArrayList<ReviewDTO> reviewDTOs = ReviewDAO.getReviewByAge(0);
				SuccessView.ageRead(reviewDTOs);
			} else if (ck.equals("6")) {// gender 별 조회

			} else if (ck.equals("7")) {// category 별 조회
				ReviewDAO.getReviewsByCategory(ck);

			} else { // 20대 남성이 쓴 리뷰 조회

			}
			SuccessView.Message("리뷰가 등록되었습니다.");
		} catch (Exception e) {
			FailureView.FailRead();
		}
	}

//U
	public static void updateReview(String col,String setData) {
		try {
			boolean ck = ReviewDAO.createReview(col,setData);
			if(ck=true) {
				SuccessView.Message("리뷰가 수정되었습니다.");
				ReviewDAO.allListReview();
			}else {
				SuccessView.Message("리뷰 수정에 실패하였습니다.");
			}
		} catch (Exception e) {
			FailView.showError("수정 실패 잠시후 다시 실행");
		}
	}

//D
	public static void deleteReview(String reviewId) {
		try {
			boolean ck = ReviewDAO.deleteReview(reviewId);
			if (ck = true) {
				SuccessView.delete();
			} else {
				SuccessView.Message("삭제할 리뷰가 없습니다.");
			}
		} catch (Exception e) {
			FailureView.
		}
	}
}
