package view;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

import model.domain.Category;
import model.domain.UserInfo;
import model.dto.ReviewDTO;

public class SuccessView {
	static Scanner scanner = new Scanner(System.in);
	
	
	
	/**
	 * 리뷰 출력
	 */
	private static void printReview(ArrayList<ReviewDTO> reviewDTOs) {
		// 리뷰 출력
		System.out.printf("\n %-4s\t | ", "번호");
		System.out.printf("%-21s\t | ", "레스토랑 이름");
		System.out.printf("%-5s\t | ","카테고리");
		System.out.printf("%-20s\t | ", "메뉴");
		System.out.printf("%-6s\t | ", "가격");
		System.out.printf("%-3s\t\n", "별점");
		System.out.println("-".repeat(104));
		
		int cnt = 1;
		for (ReviewDTO reviewDTO : reviewDTOs) {
			System.out.printf(" %-4d\t | ", cnt);
			System.out.printf("%-21s\t | ", reviewDTO.getRestaurantName());
			System.out.printf("%-5s\t | ", reviewDTO.getCategory().getKorean());
			System.out.printf("%-20s\t | ", reviewDTO.getMenu());
			System.out.printf("%-6d\t | ", reviewDTO.getPrice());
			System.out.printf("%-3.1f\t\n", reviewDTO.getScore());
			
			cnt++;
		}

		
		// 리뷰 자세히 보기
		while(true) {
			System.out.println("\n[리뷰 내용을 보고 싶은 번호를 선택하세요]\n[메뉴로 돌아가려면 0번을 입력해 주세요]");
			String inputString = scanner.nextLine();
			int inputInt = Integer.parseInt(inputString);
			
			if (inputInt == 0) {
				return;
			}
			if (inputInt > 0 && inputInt <= reviewDTOs.size()) {
				System.out.printf("\n[리뷰내용]\n%s\n", reviewDTOs.get(inputInt - 1).getContent());
				
				isContinue();
				return;
			}
			
			System.out.println("잘못된 값을 입력하셨습니다.");
		}
		
	}
	
	/**
	 * 계속 이어서
	 */
	private static void isContinue() {
		System.out.print("[계속하려면 Enter키를 눌러주세요]");
		scanner.nextLine();
	}
	
	public static void allReviewRead(ArrayList<ReviewDTO> reviewDTOs) {
		System.out.println("[모든 리뷰]");
		printReview(reviewDTOs);
	}

	public static void topRatedRead(ArrayList<ReviewDTO> reviewDTOs) {
		System.out.println("[별점 낮은 순]");
		printReview(reviewDTOs);
	}
	
	public static void topPriceRead(ArrayList<ReviewDTO> reviewDTOs) {
		System.out.println("[가격 낮은 순]");
		printReview(reviewDTOs);
	}

	public static void myReviewRead(ArrayList<ReviewDTO> reviewDTOs) {
		System.out.println("[내 리뷰]");
		UserInfo userInfo = UserInfo.getInstance();
		System.out.printf("[내 아이디 : %s]\n", userInfo.getUserId());
		printReview(reviewDTOs);
	}

	public static void ageRead(ArrayList<ReviewDTO> reviewDTOs) {
		System.out.println("[나이대 별 리뷰]");
		System.out.printf("[나이대 : %d]\n", reviewDTOs.get(0).getAge() / 10 * 10); // 10 단위로 구하기
		printReview(reviewDTOs);
	}

	public static void genderRead(ArrayList<ReviewDTO> reviewDTOs) {
		System.out.println("[성별 별 리뷰]");
		System.out.printf("[성별 : %s]\n", reviewDTOs.get(0).getGender().getKorean());
		printReview(reviewDTOs);
	}
	
	public static void ageAndGenderRead(ArrayList<ReviewDTO> reviewDTOs) {
		System.out.println("[나이대 / 성별 별 리뷰]");
		System.out.printf("[나이대 : %d / 성별 : %s]\n", reviewDTOs.get(0).getAge() / 10 * 10, reviewDTOs.get(0).getGender().getKorean());
		printReview(reviewDTOs);
	}
	
	public static void categoryRead(ArrayList<ReviewDTO> reviewDTOs) {
		System.out.println("[카테고리 별 리뷰]");
		System.out.printf("[카테고리 : %s]\n", reviewDTOs.get(0).getCategory().getKorean());
		printReview(reviewDTOs);
	}
	
	public static void create(boolean isOk) {
		if (isOk) {
			System.out.println("[리뷰가 작성되었습니다.]");
		} else {
			System.out.println("[리뷰가 정상적으로 작성되지 않았습니다.]");
		}
		isContinue();
	}
	public static void duplicated(boolean isOk) {
		if(isOk) {
			System.out.println("[금일 등록된 리뷰가 있습니다.]\n[같은 식당은 하루에 1개의 리뷰 등록만 가능합니다.]");
		}
		isContinue();
	}

	public static void update(boolean isOk) {
		if (isOk) {
			System.out.println("[리뷰가 업데이트 되었습니다.]");
		} else {
			System.out.println("[리뷰가 정상적으로 업데이트되지 않았습니다.]");
		}
		isContinue();
	}
	
	public static void delete(boolean isOk) {
		if (isOk) {
			System.out.println("[리뷰가 삭제되었습니다.]");
		} else {
			System.out.println("[리뷰가 정상적으로 삭제되지 않았습니다.]");
		}
		isContinue();
	}
	
	
	
	@Test
	public void test() {
		ArrayList<ReviewDTO> r = new ArrayList<>();
		r.add(ReviewDTO.builder().restaurantName("달인대보 칼숙수집 달인대보 칼숙수집").category(Category.KOREAN).menu("안녕").price(13000).score(4.5).age(24).content("fajkjghkaghkjahgkgajagkjhwaergkjbwagk").build());
		r.add(ReviewDTO.builder().restaurantName("aaa").category(Category.KOREAN).menu("dd").price(13000).score(4.5).content("sgggkjhwaergkjbwagk").build());
		r.add(ReviewDTO.builder().restaurantName("aaa").category(Category.KOREAN).menu("dd").price(13000).score(4.5).content("u6ghkaghkjahgkgajagkjhwaergkjbwagk").build());
		r.add(ReviewDTO.builder().restaurantName("aaa").category(Category.KOREAN).menu("dd").price(13000).score(4.5).content("2t4ghkjahgkgajagkjhwaergkjbwagk").build());
		ageRead(r);
	}
}
