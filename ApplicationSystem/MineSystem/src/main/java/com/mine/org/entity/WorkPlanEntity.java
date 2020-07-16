package com.mine.org.entity;

import com.common.org.base.BaseAuditorEntity;
import com.mine.org.entity.enums.WorkStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @Author: YU
 * @Date: 9:57 2020/7/16
 * @Description: 工作计划
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "work_plan")
public class WorkPlanEntity extends BaseAuditorEntity {

    /* 工作内容 **/
    @Column(name = "work_plan_content")
    private String workPlan;

    /* 工作结束时间 **/
    @Column(name = "work_end_time", length = 50)
    private String worKEndTime;

    /* 工作标题 **/
    @Column(name = "work_title", length = 100)
    private String workTitle;

    /* 工作状态 **/
    @Column(name = "work_state", length = 20)
    private WorkStatusEnum workState;

}
