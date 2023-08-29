package com.nasc.ssafy.commons.domains;

import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@MappedSuperclass
public abstract class Log {

    private Timestamp createDate;

    private Timestamp updateDate;

    private Timestamp deleteDate;
}
