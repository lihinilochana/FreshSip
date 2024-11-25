package com.freshsip.freshsip.service;

import com.freshsip.freshsip.dto.ReviewDTO;
import com.freshsip.freshsip.entity.Review;
import com.freshsip.freshsip.entity.User;
import com.freshsip.freshsip.repository.ReviewRepository;
import com.freshsip.freshsip.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.util.Optional;

public class ReviewServiceIMPLtest{
    @Mock
    private UserRepository userRepository;

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ReviewServiceIMPL reviewServiceIMPL;

    @Test
    void testAddReviewWithValidUser() {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setEmail("test@example.com");
        reviewDTO.setReview("Great service!");

        User user = new User();
        user.setEmail("test@example.com");

        Review review = new Review();
        review.setReview("Great service!");
        review.setUser(user);

        Mockito.when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        Mockito.when(modelMapper.map(reviewDTO, Review.class)).thenReturn(review);

        reviewServiceIMPL.addReview(reviewDTO);

        Mockito.verify(reviewRepository).save(review);
    }



}
