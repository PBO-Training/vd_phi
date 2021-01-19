package com.brycen.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.brycen.hrm.common.base.BaseEntity;

/**
 * [Description]:LevelLanguage Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "level_language")
public class LevelLanguageEntity extends BaseEntity {
    /**
     * Level Language Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "level_language_id")
    private Long levelLanguageID;

    /**
     * Level Language Name
     */
    @Column(name = "level_language_name", nullable = false)
    private String levelLanguageName;

    /**
     * Level Language Code
     */
    @Column(name = "level_language_code", nullable = false, length = 40)
    private String levelLanguageCode;

    /**
     * Level Language Description
     */
    @Column(name = "level_language_description", length = 2000)
    private String levelLanguageDescription;

    /**
     * Level Language Value
     */
    @Column(name = "level_language_value")
    private int levelLanguageValue;

    /**
     * List Employee Language
     */
    // @OneToMany(fetch = FetchType.LAZY, mappedBy = "levelLanguage")
    // private Set<EmployeeLanguageEntity> listEmployeeLanguage = new HashSet<>();

    public LevelLanguageEntity() {

    }

    public LevelLanguageEntity(String levelLanguageName, String levelLanguageCode, String levelLanguageDescription, int levelLanguageValue) {
        this.levelLanguageName = levelLanguageName;
        this.levelLanguageCode = levelLanguageCode;
        this.levelLanguageDescription = levelLanguageDescription;
        this.levelLanguageValue = levelLanguageValue;
    }

    public Long getLevelLanguageID() {
        return levelLanguageID;
    }

    public void setLevelLanguageID(Long levelLanguageID) {
        this.levelLanguageID = levelLanguageID;
    }

    public String getLevelLanguageName() {
        return levelLanguageName;
    }

    public void setLevelLanguageName(String levelLanguageName) {
        this.levelLanguageName = levelLanguageName;
    }

    public String getLevelLanguageCode() {
        return levelLanguageCode;
    }

    public void setLevelLanguageCode(String levelLanguageCode) {
        this.levelLanguageCode = levelLanguageCode;
    }

    public String getLevelLanguageDescription() {
        return levelLanguageDescription;
    }

    public void setLevelLanguageDescription(String levelLanguageDescription) {
        this.levelLanguageDescription = levelLanguageDescription;
    }

    public int getLevelLanguageValue() {
        return levelLanguageValue;
    }

    public void setLevelLanguageValue(int levelLanguageValue) {
        this.levelLanguageValue = levelLanguageValue;
    }

}
