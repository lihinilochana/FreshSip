package com.freshsip.freshsip.controller;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import com.freshsip.freshsip.dto.ReviewDTO;
import com.freshsip.freshsip.entity.Review;
import com.freshsip.freshsip.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@WebMvcTest(ReviewController.class)
@ExtendWith(MockitoExtension.class)
public class ReviewControllertest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    @Test
    void testAddReview_Success() throws Exception {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setEmail("test@example.com");
        reviewDTO.setReview("Great service!");

        Mockito.doNothing().when(reviewService).addReview(Mockito.any(ReviewDTO.class));

        mockMvc.perform(post("/FreshSip/user/review")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\", \"review\":\"Great service!\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Review added successfully"))
                .andDo(print());
    }

    @Test
    void testGetAllReviews_Success() throws Exception {
        ReviewDTO review1 = new ReviewDTO();
        review1.setReview("Great service!");
        review1.setEmail("user1@example.com");

        ReviewDTO review2 = new ReviewDTO();
        review2.setReview("Amazing experience!");
        review2.setEmail("user2@example.com");

        List<ReviewDTO> reviewDTOs = List.of(review1, review2);

        Mockito.when(reviewService.getAllReviews()).thenReturn(reviewDTOs);

        mockMvc.perform(get("/FreshSip/adminUser/allReviews"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].email").value("user1@example.com"))
                .andExpect(jsonPath("$[0].review").value("Great service!"))
                .andDo(print());
    }

    @Test
    void testGetReviewById_Success() throws Exception {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setReview("Great service!");
        reviewDTO.setEmail("test@example.com");

        Mockito.when(reviewService.getReviewById(1L)).thenReturn(reviewDTO);

        mockMvc.perform(get("/FreshSip/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@example.com"))
                .andExpect(jsonPath("$.review").value("Great service!"))
                .andDo(print());
    }

    @Test
    void testDeleteReviewById_Success() throws Exception {
        Mockito.when(reviewService.deleteReviewById(1L)).thenReturn(true);

        mockMvc.perform(delete("/FreshSip/user/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"))
                .andDo(print());
    }

    @Test
    void testUpdateReviewById_Success() throws Exception {
        Review updatedReview = new Review();
        updatedReview.setReview("Updated review");

        Mockito.when(reviewService.updateReviewById(Mockito.eq(1L), Mockito.any(Review.class)))
                .thenReturn("Update Successful");

        mockMvc.perform(put("/FreshSip/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"review\":\"Updated review\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Update Successful"))
                .andDo(print());
    }
}

