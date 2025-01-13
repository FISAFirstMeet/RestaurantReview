package view;

import model.domain.Category;
import model.domain.Gender;
import model.domain.UserInfo;
import model.dto.ReviewDTO;

import java.util.Scanner;

public class OperationView {

   private static final Scanner scanner = new Scanner(System.in);

   private static UserInfo userInfo = UserInfo.getInstance();
   
   public static Category reviewsByCategoryView() {
	  String input = scanner.nextLine();
	  Category category = Category.valueOf(input);
      
	  return category;
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
   
   public static ReviewDTO createReview() {
	   //생성할 리뷰에 대한 정보 입력 받기
	   //기입력 정보 : userid,age,gender
	   //미입력 : restaurant_name,category,menu,price,content,score
	   System.out.print("음식점 이름을 입력해주세요 : "); 
	   String restaurantName = scanner.nextLine();
	   
	   System.out.print("카테고리를 입력해주세요 : "); 
	   String category = scanner.nextLine();

	   System.out.print("메뉴를 입력해주세요 : "); 
	   String menu = scanner.nextLine();

	   System.out.print("가격을 입력해주세요 : "); 
	   int price = scanner.nextInt();

	   System.out.print("내용을 입력해주세요 : "); 
	   String content = scanner.nextLine();

	   System.out.print("별점을 입력해주세요(0~5.0 사이) : "); 
	   double score = scanner.nextDouble();
	   
	   ReviewDTO review = ReviewDTO.builder()
			   				.restaurantName(restaurantName)
			   				.category(Category.valueOf(category))
			   				.menu(menu)
			   				.price(price)
			   				.content(content)
			   				.score(score).build();
	   
      return review;
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
