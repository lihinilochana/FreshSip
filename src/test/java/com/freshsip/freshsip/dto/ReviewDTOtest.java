package com.freshsip.freshsip.dto;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReviewDTOtest {
    @Test
    void testReviewDTO_AllArgsConstructor() {
        // Arrange
        Long reviewId = 1L;
        String email = "test@example.com";
        String review = "Great service!";

        // Act
        ReviewDTO reviewDTO = new ReviewDTO(reviewId, email, review);

        // Assert
        assertEquals(reviewId, reviewDTO.getReviewId());
        assertEquals(email, reviewDTO.getEmail());
        assertEquals(review, reviewDTO.getReview());
    }

    @Test
    void testReviewDTO_NoArgsConstructorAndSetters() {
        // Arrange
        ReviewDTO reviewDTO = new ReviewDTO();

        // Act
        reviewDTO.setReviewId(2L);
        reviewDTO.setEmail("user@example.com");
        reviewDTO.setReview("Amazing experience!");

        // Assert
        assertEquals(2L, reviewDTO.getReviewId());
        assertEquals("user@example.com", reviewDTO.getEmail());
        assertEquals("Amazing experience!", reviewDTO.getReview());
    }

    @Test
    void testReviewDTO_ToString() {
        // Arrange
        ReviewDTO reviewDTO = new ReviewDTO(3L, "user2@example.com", "Fantastic!");

        // Act
        String result = reviewDTO.toString();

        // Assert
        assertTrue(result.contains("reviewId=3"));
        assertTrue(result.contains("email=user2@example.com"));
        assertTrue(result.contains("review=Fantastic!"));
    }

    @Test
    void testReviewDTO_Equality() {
        // Arrange
        ReviewDTO review1 = new ReviewDTO(1L, "test@example.com", "Great service!");
        ReviewDTO review2 = new ReviewDTO(1L, "test@example.com", "Great service!");

        // Act & Assert
        assertEquals(review1, review2); // Ensures the equals() method works
        assertEquals(review1.hashCode(), review2.hashCode()); // Ensures the hashCode() method works
    }
}


