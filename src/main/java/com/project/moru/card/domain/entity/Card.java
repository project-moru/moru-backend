package com.project.moru.card.domain.entity;

import com.project.moru.cardlink.domain.entity.CardLinkBlock;
import com.project.moru.common.constant.Status;
import com.project.moru.common.domain.entity.BaseEntity;
import com.project.moru.data_field.domain.entity.DataField;
import com.project.moru.deck.domain.entity.DeckCard;
import com.project.moru.user.domain.entity.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@Entity
@Getter
@Table(name = "card")
public class Card extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "card_name")
    private String cardName;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @Builder.Default
    private Status status =  Status.PUBLIC;

    @Column(name = "tag_count")
    private Integer tagCount;

    @Column(name = "view_count")
    private Integer viewCount;

    @Column(name = "like_count")
    private Integer likeCount;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "data_field_id")
    private DataField dataField;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeckCard> deckCards = new ArrayList<>();
    
    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CardLinkBlock> cardLinkBlocks = new ArrayList<>();


    public void addViewCount() {
        this.viewCount++;
    }

    public void addLikeCount() {
        this.likeCount++;
    }

    public void updateCard(String cardName,  Status status, DataField dataField, String imageUrl) {
        if (cardName != null) this.cardName = cardName;
        if (status != null) this.status = status;
        if (imageUrl != null) this.imageUrl = imageUrl;

        // ID 기반 빌더가 아닌 실제 엔티티 객체 할당
        if (dataField != null) {
            this.dataField = dataField;
        }
    }
}