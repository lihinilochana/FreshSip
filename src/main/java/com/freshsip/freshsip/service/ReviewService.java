package com.freshsip.freshsip.service;



import com.freshsip.freshsip.dto.ReviewDTO;
import com.freshsip.freshsip.dto.UserDTO;
import com.freshsip.freshsip.entity.Review;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewService {
    void addReview(ReviewDTO reviewDTO);

    List<ReviewDTO> getAllReviews();

    ReviewDTO getReviewById(Long reviewId);

    boolean deleteReviewById(Long reviewId);

    String updateReviewById(Long reviewId, Review reviewDTO);
}
