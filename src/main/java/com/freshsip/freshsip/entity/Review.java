package com.freshsip.freshsip.entity;



import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLInsert;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email", referencedColumnName = "email", nullable = false)
    private User user;
    private String review;
}

