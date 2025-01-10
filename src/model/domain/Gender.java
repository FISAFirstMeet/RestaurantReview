package model.domain;

public enum Gender {

	MALE("남자"),
	FEMALE("여자");
	
	private final String gender;
	
	Gender(final String gender) {
		this.gender = gender;
	}
	
	public String getKorean() {
		return gender;
	}
	
}
