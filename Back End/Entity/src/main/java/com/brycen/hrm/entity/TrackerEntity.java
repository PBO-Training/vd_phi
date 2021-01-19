package com.brycen.hrm.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.brycen.hrm.common.base.BaseEntity;

/**
 * [Description]:Tracker Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "tracker")
public class TrackerEntity extends BaseEntity {

    /**
     * Tracker Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tracker_id")
    private Long trackerID;

    /**
     * Tracker Name
     */
    @Column(name = "tracker_name", nullable = false)
    private String trackerName;

    /**
     * Tracker Code
     */
    @Column(name = "tracker_code", length = 40, nullable = false)
    private String trackerCode;

    /**
     * Tracker Description
     */
    @Column(name = "tracker_description", length = 2000)
    private String trackerDescription;

    /**
     * List Issue
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tracker")
    private Set<IssueEntity> listIssue = new HashSet<>();

    /**
     * List Tracker Detail
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tracker")
    private Set<TrackerDetailEntity> listTrackerDetail = new HashSet<>();

    public TrackerEntity() {

    }

    public TrackerEntity(String trackerName, String trackerCode, String trackerDescription) {
        this.trackerName = trackerName;
        this.trackerCode = trackerCode;
        this.trackerCode = trackerDescription;
    }

    public Long getTrackerID() {
        return trackerID;
    }

    public void setTrackerID(Long trackerID) {
        this.trackerID = trackerID;
    }

    public String getTrackerName() {
        return trackerName;
    }

    public void setTrackerName(String trackerName) {
        this.trackerName = trackerName;
    }

    public String getTrackerCode() {
        return trackerCode;
    }

    public void setTrackerCode(String trackerCode) {
        this.trackerCode = trackerCode;
    }

    public String getTrackerDescription() {
        return trackerDescription;
    }

    public void setTrackerDescription(String trackerDescription) {
        this.trackerDescription = trackerDescription;
    }

    public Set<IssueEntity> getListIssue() {
        return listIssue;
    }

    public void setListIssue(Set<IssueEntity> listIssue) {
        this.listIssue = listIssue;
    }

    public Set<TrackerDetailEntity> getListTrackerDetail() {
        return listTrackerDetail;
    }

    public void setListTrackerDetail(Set<TrackerDetailEntity> listTrackerDetail) {
        this.listTrackerDetail = listTrackerDetail;
    }

}
