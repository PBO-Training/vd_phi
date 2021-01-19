package com.brycen.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.brycen.hrm.common.base.BaseEntity;

@Entity
@Table(name = "system_setting")
public class SystemSettingEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "system_setting_id")
    private Long systemSettingID;    

    /**
     * Time start work AM
     */
    @Column(name = "time_start_work_am", nullable = true, length = 40)
    private String timeStartWorkAM;

    /**
     * Time end work AM
     */
    @Column(name = "time_end_work_am", nullable = true, length = 40)
    private String timeEndWorkAM;
    
    /**
     * Time start work PM
     */
    @Column(name = "time_start_work_pm", nullable = true, length = 40)
    private String timeStartWorkPM;

    /**
     * Time end work PM
     */
    @Column(name = "time_end_work_pm", nullable = true, length = 40)
    private String timeEndWorkPM;
    
    /**
     * Step break Time
     */
    @Column(name = "step_break_time", nullable = true)
    private long stepBreakTime;
    
    /**
     * Last Weekend
     */
    @Column(name = "last_weekend", nullable = true, length = 40)
    private String lastWeekend;
    
    /**
     * Vacation bonus range
     */
    @Column(name = "vacation_bonus_range", nullable = true)
    private long vacationBonusRange;
    
    /**
     * Vacation bonus day
     */
    @Column(name = "vacation_bonus_day", nullable = true)
    private float vacationBonusDay;
    
    /**
    * Automatically statistics
    */
   @Column(name = "automatically_statistics", columnDefinition = "boolean default false")
   private Boolean automaticallyStatistics;

    public SystemSettingEntity() {
    }

    public Long getSystemSettingID() {
        return systemSettingID;
    }

    public void setSystemSettingID(Long systemSettingID) {
        this.systemSettingID = systemSettingID;
    }  
    
    public String getTimeStartWorkAM() {
        return timeStartWorkAM;
    }

    public void setTimeStartWorkAM(String timeEndWorkAM) {
        this.timeEndWorkAM = timeEndWorkAM;
    }  
    
    public String getTimeEndWorkAM() {
        return timeEndWorkAM;
    }

    public void setTimeEndWorkAM(String timeEndWorkAM) {
        this.timeEndWorkAM = timeEndWorkAM;
    }  
    
    public String getTimeStartWorkPM() {
        return timeStartWorkPM;
    }

    public void setTimeStartWorkPM(String timeEndWorkPM) {
        this.timeEndWorkPM = timeEndWorkPM;
    }  
    
    public String getTimeEndWorkPM() {
        return timeEndWorkPM;
    }

    public void setTimeEndWorkPM(String timeEndWorkPM) {
        this.timeEndWorkPM = timeEndWorkPM;
    }  
    
    public Long getStepBreakTime() {
        return stepBreakTime;
    }

    public void setStepBreakTime(Long stepBreakTime) {
        this.stepBreakTime = stepBreakTime;
    }  
    
    public String getLastWeekend() {
        return lastWeekend;
    }

    public void setLastWeekend(String lastWeekend) {
        this.lastWeekend = lastWeekend;
    }

    public Boolean getAutomaticallyStatistics() {
        return automaticallyStatistics;
    }

    public void setAutomaticallyStatistics(Boolean automaticallyStatistics) {
        this.automaticallyStatistics = automaticallyStatistics;
    }   
    
    public long getVacationBonusRange() {
        return vacationBonusRange;
    }

    public void setVacationBonusRange(long vacationBonusRange) {
        this.vacationBonusRange = vacationBonusRange;
    } 
    
    public float getVacationBonusDay() {
        return vacationBonusDay;
    }

    public void setVacationBonusDay(float vacationBonusDay) {
        this.vacationBonusDay = vacationBonusDay;
    } 

}
