package com.nasc.ssafy.groups.domains;

import com.nasc.ssafy.commons.domains.Log;
import com.nasc.ssafy.messages.domains.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "tbl_group")
public class Group extends Log {

    @Id
    @Column(name = "group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_setting_id")
    private GroupSetting groupSetting;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<GroupUser> groupUsers = new ArrayList<>();

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<Message> messages = new ArrayList<>();

    private String title;

    private String description;

    private String password;
}
