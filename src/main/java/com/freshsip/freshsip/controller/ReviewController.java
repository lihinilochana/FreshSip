package com.freshsip.freshsip.controller;


import com.freshsip.freshsip.dto.ReviewDTO;
import com.freshsip.freshsip.dto.UserDTO;
import com.freshsip.freshsip.entity.Review;
import com.freshsip.freshsip.entity.User;
import com.freshsip.freshsip.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "FreshSip/")

public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/user/review")
    public String addReview(@RequestBody ReviewDTO reviewDTO) {
        reviewService.addReview(reviewDTO);
        return ("Review added successfully");
    }

    @GetMapping("/adminUser/allReviews")
    public ResponseEntity<List<ReviewDTO>> getAllReviews() {
        List<ReviewDTO> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/user/{reviewId}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable Long reviewId) {
        return ResponseEntity.ok(reviewService.getReviewById(reviewId));
    }

    @DeleteMapping("/user/{reviewId}")
    public boolean deleteReviewByReviewID(@PathVariable Long reviewId) {
        return reviewService.deleteReviewById(reviewId);
    }

    @PutMapping("/user/{reviewId}")
    public String updateReviewById (@PathVariable Long reviewId, @RequestBody Review reviewDTO){
        reviewService.updateReviewById(reviewId, reviewDTO);
        return ("Update Successful");
    }
}

