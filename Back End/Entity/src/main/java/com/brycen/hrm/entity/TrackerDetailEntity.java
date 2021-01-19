package com.brycen.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.brycen.hrm.common.base.BaseEntity;

/**
 * [Description]:TrackerDetail Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "tracker_detail")
public class TrackerDetailEntity extends BaseEntity {

    /**
     * Tracker Detail Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tracker_detail_id")
    private Long trackerDetailID;

    /**
     * Control Type
     */
    @ManyToOne
    @JoinColumn(name = "field_id", nullable = false)
    private FieldEntity field;

    /**
     * Tracker Id
     */
    @ManyToOne
    @JoinColumn(name = "tracker_id", nullable = false)
    private TrackerEntity tracker;

    public TrackerDetailEntity() {
    }

    public TrackerDetailEntity(Long trackerDetailID, FieldEntity field, TrackerEntity tracker) {
        super();
        this.trackerDetailID = trackerDetailID;
        this.field = field;
        this.tracker = tracker;
    }

    public Long getTrackerDetailID() {
        return trackerDetailID;
    }

    public void setTrackerDetailID(Long trackerDetailID) {
        this.trackerDetailID = trackerDetailID;
    }

    public FieldEntity getField() {
        return field;
    }

    public void setField(FieldEntity field) {
        this.field = field;
    }

    public TrackerEntity getTracker() {
        return tracker;
    }

    public void setTracker(TrackerEntity tracker) {
        this.tracker = tracker;
    }

}
