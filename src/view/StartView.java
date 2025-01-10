package view;

import java.util.Scanner;

public class StartView {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("안녕하세요. 맛집 리뷰 프로그램입니다.\n");

		while (true) {
			System.out.println("기능을 선택해주세요.\n"
					+ "1. 리뷰 작성\n"
					+ "2. 리뷰 조회\n"
					+ "3. 리뷰 수정\n"
					+ "4. 리뷰 삭제\n"
					+ "Q. 종료\n");
			String reply = scanner.next().trim();
			if (reply.equals("Q")) {
				break;
			}
			//ReviewController.run(reply);
		}
		
	}

}
