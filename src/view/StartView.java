package view;

import java.util.Scanner;

import org.junit.Test;

import model.domain.Gender;
import model.domain.UserInfo;

public class StartView {
	
	private static final Scanner scanner = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		
		initialize();
		
		while (true) {
			StringBuilder sb = new StringBuilder();
			sb.append("기능을 선택해주세요.\n")
				.append("1. 리뷰 작성\n")
				.append("2. 리뷰 조회\n")
				.append("3. 리뷰 수정\n")
				.append("4. 리뷰 삭제\n")
				.append("Q. 종료\n");
			System.out.println(sb.toString());
			String reply = scanner.next().trim();
			if (reply.equals("Q")) {
				break;
			}
<<<<<<< HEAD
			//ReviewController.run(reply);
=======
//			ReviewController.run(reply);
>>>>>>> e7f6fa80cb46858407f5319fbeb70caf30e0995d
		}
		
	}
	
	private static void initialize() {
		StringBuilder sb = new StringBuilder();
		sb.append("안녕하세요. 맛집 리뷰 프로그램입니다.\n")
			.append("아이디를 입력해주세요.");
		String str = sb.toString();
		System.out.println(str);
		String userId = scanner.next();
		
		System.out.println("만 나이를 입력해주세요.");
		int age = scanner.nextInt();
		
		System.out.println("성별을 입력해주세요. (남성 or 여성)");
		String input = scanner.next();
		Gender gender = null;
		if (input.equals("남성")) {
			gender = Gender.MALE;
		} else if (input.equals("여성")) {
			gender = Gender.FEMALE;
		}
		
		UserInfo userInfo = UserInfo.getInstance();
		userInfo.setUserId(userId);
		userInfo.setAge(age);
		userInfo.setGender(gender);
		
		UserInfo user1 = UserInfo.getInstance();
		System.out.println(user1.getAge());
	}

}
