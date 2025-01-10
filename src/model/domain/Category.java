package model.domain;

public enum Category {

	KOREAN("한식"),
	JAPANESE("일식"),
	CHINESE("중식"),
	WESTERN("양식"),
	OTHER("기타");
	
	private final String category;
	
	Category(final String category) {
		this.category = category;
	}
	
	public String getKorean() {
		return category;
	}
	
}
