package view;

import model.domain.Category;
import model.domain.Gender;
import model.dto.ReviewDTO;

import java.util.Scanner;

public class OperationView {

   private static final Scanner scanner = new Scanner(System.in);

   public static Category reviewsByCategoryView() {
      return null;
   }
   
	public static int reviewByAgeView() {
		System.out.print("검색할 나이를 입력해 주세요 > ");
		return scanner.nextInt();
	}

	public static Gender reviewByGenderView() {
		System.out.print("성별을 선택해 주세요. (숫자로 입력)\n1. 여자\n2. 남자\n> ");
		switch (scanner.nextInt()) {
		case 1:
			return Gender.FEMALE;
		case 2:
			return Gender.MALE;
		default:
			return null;
		}
	}
   
   // AgeAndGender는 이미 존재하는 Age, Gender 두번 부르기
   
   public static ReviewDTO createView() {
      return null;
   }
   
   public static int readReviewIdView() {
	   System.out.println("수정할 리뷰 번호를 입력해주세요.");
	   return Integer.parseInt(scanner.next().trim());
   }
   
   public static String readContentView() {
	   System.out.println("수정할 리뷰 내용을 입력해주세요.");
	   return scanner.next().trim();
   }

   public static int deleteView() {
      return 0;
   }

   public static String readSearchFunctionView() {
      StringBuilder sb = new StringBuilder();
      sb.append("조회 기능\n");
      sb.append("----------\n");
      sb.append("1. 전체 리뷰 조회\n");
      sb.append("2. 평점 높은 순 조회\n");
      sb.append("3. 가격 낮은 순 조회\n");
      sb.append("4. 내가 쓴 리뷰 조회\n");
      sb.append("5. 연령대별 조회\n");
      sb.append("6. 성별별 조회\n");
      sb.append("7. 음식 카테고리별 조회\n");

      String input = scanner.next().trim();
      return input;
   }
}
