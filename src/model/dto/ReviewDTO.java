package model.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReviewDTO {
	private int reviewId;
	private String userId;
	private int age;
	private String gender;
	private String restaurantName;
	private String category;
	private String menu;
	private int price;
	private String content;
	private double score;
	private LocalDateTime date;
}
