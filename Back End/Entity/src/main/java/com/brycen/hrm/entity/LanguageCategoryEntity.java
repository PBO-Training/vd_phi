package com.brycen.hrm.entity;

import java.util.List;

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
 * [Description]:Language Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 4.0
 */
@Entity
@Table(name = "language_category")
public class LanguageCategoryEntity extends BaseEntity {
    /**
     * Language Category Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_category_id")
    private Long languageCategoryID;

    /**
     * Language Name
     */
    @Column(name = "language_category_name", nullable = false, length = 255)
    private String languageCategoryName;

    /**
     * Language Code
     */
    @Column(name = "language_category_code", nullable = false, length = 40)
    private String languageCategoryCode;

    /**
     * Language Description
     */
    @Column(name = "language_category_description", length = 2000)
    private String languageCategoryDescription;

    /**
     * List Language Category
     */
    @OneToMany(mappedBy = "languageCategory", fetch = FetchType.LAZY)
    private List<LanguageEntity> listLanguage;

    public LanguageCategoryEntity() {

    }

    public Long getLanguageCategoryID() {
        return languageCategoryID;
    }

    public void setLanguageCategoryID(Long languageCategoryID) {
        this.languageCategoryID = languageCategoryID;
    }

    public String getLanguageCategoryName() {
        return languageCategoryName;
    }

    public void setLanguageCategoryName(String languageCategoryName) {
        this.languageCategoryName = languageCategoryName;
    }

    public String getLanguageCategoryCode() {
        return languageCategoryCode;
    }

    public void setLanguageCategoryCode(String languageCategoryCode) {
        this.languageCategoryCode = languageCategoryCode;
    }

    public String getLanguageCategoryDescription() {
        return languageCategoryDescription;
    }

    public void setLanguageCategoryDescription(String languageCategoryDescription) {
        this.languageCategoryDescription = languageCategoryDescription;
    }

    public List<LanguageEntity> getListLanguage() {
        return listLanguage;
    }

    public void setListLanguage(List<LanguageEntity> listLanguage) {
        this.listLanguage = listLanguage;
    }

}