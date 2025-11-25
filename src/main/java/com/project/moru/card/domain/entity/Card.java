package com.project.moru.card.domain.entity;

import com.project.moru.common.constant.Status;
import com.project.moru.common.domain.entity.BaseEntity;
import com.project.moru.deck.domain.entity.DeckCard;
import com.project.moru.user.domain.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Table(name = "card")
public class Card extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "card_name")
    private String cardName;

    @Column(name = "card_content")
    private String cardContent;

    @Enumerated(EnumType.STRING)
    @Column(name = "is_public")
    @Builder.Default
    private Status isPublic =  Status.PUBLIC;

    @Column(name = "tag_count")
    private Integer tagCount;

    @Column(name = "view_count")
    private Integer viewCount;

    @Column(name = "like_count")
    private Integer likeCount;

    @Column(name = "image_url")
    private String imageUrl;
    
    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeckCard> deckCards = new ArrayList<>();

    public void addViewCount() {
        this.viewCount++;
    }

    public void addLikeCount() {
        this.likeCount++;
    }

    public void updateCard(String cardName, String cardContent, Status isPublic, String imageUrl) {
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