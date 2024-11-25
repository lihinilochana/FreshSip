package com.freshsip.freshsip.dto;



import com.freshsip.freshsip.entity.Review;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ReviewDTO {
    private Long reviewId;
    private String email;
    private String review;
}

