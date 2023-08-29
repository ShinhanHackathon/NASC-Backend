package com.nasc.ssafy.feeds.domains;

import com.nasc.ssafy.commons.domains.Image;
import com.nasc.ssafy.commons.domains.Log;
import com.nasc.ssafy.users.domains.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "tbl_feed")
public class Feed extends Log {

    @Id
    @Column(name = "feed_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "feed", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "feed", fetch = FetchType.LAZY)
    private List<Image> images = new ArrayList<>();

    @Lob
    private String transaction;

    private String title;

    private String transactionMemo;

    @Lob
    private String description;

}
