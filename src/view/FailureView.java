package view;

public class FailureView {
	public static void FailCreate() {
		System.out.println("생성에 실패했습니다.");
	}
	
	public static void FailRead(Exception e) {
		System.out.println(e);
		System.out.println("읽기에 실패했습니다.");
	}
	
	public static void FailUpdate() {
		System.out.println("업데이트에 실패했습니다.");
	}
	
	public static void FailDelete() {
		System.out.println("삭제에 실패했습니다.");
	}
}
