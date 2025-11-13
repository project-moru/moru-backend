package com.project.moru.domain.entity.card;

import com.project.moru.domain.entity.BaseEntity;
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

    @Column(name = "card_name")
    private String cardName;

    @Column(name = "card_content")
    private String cardContent;

    @Column(name = "is_public")
    private Boolean isPublic;

    @Column(name = "tag_count")
    private Integer tagCount;

    @Column(name = "view_count")
    private Integer viewCount;

    @Column(name = "like_count")
    private Integer likeCount;

    @Column(name = "image_url")
    private String imageUrl;

    public void addViewCount() {
        this.viewCount++;
    }

    public void addLikeCount() {
        this.likeCount++;
    }

    public void updateCard(String cardName, String cardContent, Boolean isPublic, String imageUrl) {
        if (cardName != null) {
            this.cardName = cardName;
        }
        if (cardContent != null) {
            this.cardContent = cardContent;
        }
        if (isPublic != null) {
            this.isPublic = isPublic;
        }
        if (imageUrl != null) {
            this.imageUrl = imageUrl;
        }
    }
}

