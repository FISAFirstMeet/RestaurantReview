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
	
	public static Category from(String input) {
        for (Category c : Category.values()) {
            if (c.category.equals(input)) {
                return c;
            }
        }
        throw new IllegalArgumentException("해당 한글 값에 해당하는 카테고리가 없습니다: " + input);
    }
}
