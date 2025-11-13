package com.project.moru.domain.entity;

import com.project.moru.domain.entity.user.User;
import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Table(name = "card")
public class Card extends BaseEntity {
    @Id
    @Column(name = "card_id")
    private Long cardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String cardName;

    private String cardContent;

    private Boolean isPublic;

    private Integer tagCount;

    private Integer viewCount;

    private Integer likeCount;

    private String imageUrl;
}
