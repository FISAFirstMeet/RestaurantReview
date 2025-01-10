package controller;

import java.util.ArrayList;

import model.ReviewDAO;
import model.dto.ReviewDTO;

public class ReviewController {
// 1~5
	public static void choice(String reply) {
		if (reply.equals("1")) { // 리뷰 등록
			// 리뷰 등록 ov 호출
			ReviewDTO reviewDTO = OperationView.createReview();
			createReview(reviewDTO);

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

			} else if (ck.equals("6")) {// gender 별 조회

			} else if (ck.equals("7")) {// category 별 조회

			} else { // 20대 남성이 쓴 리뷰 조회

			}
			SuccessView.Message("리뷰가 등록되었습니다.");
		} catch (Exception e) {
			FailView.showError("조회 실패 잠시후 다시 실행");
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
				SuccessView.Message("리뷰가 삭제되었습니다.");
			} else {
				SuccessView.Message("삭제할 리뷰가 없습니다.");
			}
		} catch (Exception e) {
			FailView.showError("삭제에 실패하였습니다.");
		}
	}
}
