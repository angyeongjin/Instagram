package com.kbm.instagram.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Follow extends BaseEntity{

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name="following_id")
    private Member following;

    @ManyToOne
    @JoinColumn(name="follower_id")
    private Member follower;

}
