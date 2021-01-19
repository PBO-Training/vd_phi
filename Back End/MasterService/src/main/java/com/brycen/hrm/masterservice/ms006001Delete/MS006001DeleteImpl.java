package com.brycen.hrm.masterservice.ms006001Delete;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.entity.LevelLanguageEntity;

/**
 * [Description]: This is place to delete level language<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS006001DeleteImpl implements MS006001DeleteService{

    @Autowired
    public EntityManager em;

    private Query query;
    
    @Transactional
    @Override
    public int delete(List<Long> listLanguageLevelID, int companyID) {
        StringBuffer strSql = new StringBuffer();
        strSql.append("UPDATE level_language ll ");
        strSql.append("SET ll.is_delete = 1 ");
        strSql.append("WHERE ll.level_language_id IN (:levelLanguageIDs) ");
        strSql.append("AND ll.company_id = :companyID ");
        query = em.createNativeQuery(strSql.toString(), LevelLanguageEntity.class);
        query.setParameter("levelLanguageIDs", (Collection<Long>) listLanguageLevelID);
        query.setParameter("companyID", companyID);
        int updatedQuantity = query.executeUpdate();
        return updatedQuantity;
    }

}
