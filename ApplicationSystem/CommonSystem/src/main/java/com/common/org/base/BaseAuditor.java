package com.common.org.base;

import java.util.Date;

public interface BaseAuditor {

    Date getCreatedDate();

    void setCreatedDate(Date createdDate);

    Date getUpdatedDate();

    void setUpdatedDate(Date updatedDate);

}
