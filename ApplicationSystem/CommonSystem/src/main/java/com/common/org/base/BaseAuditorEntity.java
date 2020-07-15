package com.common.org.base;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseAuditorEntity extends BaseEntity implements BaseAuditor {
    /**
     * 创建日期
     */
    @ApiModelProperty(hidden = true)
    @Column(name = "created_date", updatable = false, length = 20)
    @CreationTimestamp
    private Date createdDate;
    /**
     * 更新日期
     */
    @ApiModelProperty(hidden = true)
    @Column(name = "updated_date", length = 20)
    @UpdateTimestamp
    private Date updatedDate;

}
