package com.freshsip.freshsip.service;



import com.freshsip.freshsip.dto.ReviewDTO;
import com.freshsip.freshsip.entity.Review;
import com.freshsip.freshsip.entity.User;
import com.freshsip.freshsip.repository.ReviewRepository;
import com.freshsip.freshsip.repository.UserRepository;
import com.freshsip.freshsip.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceIMPL implements ReviewService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addReview (ReviewDTO reviewDTO){

        String email = reviewDTO.getEmail();

        System.out.println(email);
        Optional<User>userOptional = userRepository.findByEmail(email);

        if(userOptional.isPresent()){

            User user = userOptional.get();

            Review review = modelMapper.map(reviewDTO, Review.class);
            review.setUser(user);
            reviewRepository.save(review);

        }

        else{
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public List<ReviewDTO> getAllReviews() {
        List<Review> reviews = reviewRepository.findAllReviewsWithUser();
        return reviews.stream().map(review -> {
            ReviewDTO reviewDTO = new ReviewDTO();
            reviewDTO.setReview(review.getReview());
            reviewDTO.setEmail(review.getUser().getEmail());
            reviewDTO.setReviewId(review.getReviewId());
            // Get email from associated User
            return reviewDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public ReviewDTO getReviewById(Long reviewId){
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);

        return modelMapper.map(reviewOptional, ReviewDTO.class);
    }

    @Override
    public boolean deleteReviewById(Long reviewId){
        reviewRepository.deleteById(reviewId);
        return true;
    }

    @Override
    public String updateReviewById(Long reviewId, Review updateReview){
        ReviewDTO reviewDTO = new ReviewDTO();

        Optional<Review>reviewOptional = reviewRepository.findById(reviewId);

        Review existingReview = reviewOptional.get();

        existingReview.setReview(updateReview.getReview());
        Review saveReview = reviewRepository.save(existingReview);

        return ("Update Successful");

    }


}

