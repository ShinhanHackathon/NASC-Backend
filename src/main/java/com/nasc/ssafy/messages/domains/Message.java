package com.nasc.ssafy.messages.domains;

import com.nasc.ssafy.commons.domains.Image;
import com.nasc.ssafy.groups.domains.Group;
import com.nasc.ssafy.groups.domains.GroupUser;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "tbl_message")
public class Message {

    @Id
    @Column(name = "message_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_user_id")
    private GroupUser writer;

    @OneToMany(mappedBy = "message", fetch = FetchType.LAZY)
    private List<Image> images = new ArrayList<>();

    private String transaction;

    private String transactionMemo;

}
