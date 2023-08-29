package com.nasc.ssafy.groups.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "tbl_group_setting")
public class GroupSetting {

    @Id
    @Column(name = "group_setting_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "groupSetting", fetch = FetchType.LAZY)
    private Group group;

    private boolean shareAllFlag;

    private boolean updateUsageFlag;

    private boolean passwordFlag;

    private int limitNum;
}
