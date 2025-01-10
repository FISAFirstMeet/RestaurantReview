package model.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import model.domain.Category;
import model.domain.Gender;

@Getter
@Setter
@Builder
public class ReviewDTO {
	private int reviewId;
	private String userId;
	private int age;
	private Gender gender;
	private String restaurantName;
	private Category category;
	private String menu;
	private int price;
	private String content;
	private double score;
	private LocalDateTime date;
}
