package com.nasc.ssafy.users.domains;

import com.nasc.ssafy.commons.domains.Log;
import com.nasc.ssafy.feeds.domains.Feed;
import com.nasc.ssafy.groups.domains.GroupUser;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "tbl_user")
public class User extends Log {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Feed> feeds = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<GroupUser> groupUsers = new ArrayList<>();

    private String loginId;

    private String password;

    private String name;

    private String accountNum;
}
