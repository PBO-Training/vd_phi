package com.brycen.hrm.masterservice.ms004001Delete;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.entity.LevelSkillEntity;

/**
 * [Description]: Delete Service Implementation for Skill Level Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS004001DeleteImpl implements MS004001DeleteIService {

    @Autowired
    public EntityManager em;

    private Query query;

    @Transactional
    @Override
    public int delete(List<Long> skillLevelDelete, int companyID) {
        // TODO Auto-generated method stub
        StringBuffer strSql = new StringBuffer();
        strSql.append("UPDATE level_skill lk ");
        strSql.append("SET lk.is_delete = 1 ");
        strSql.append("WHERE lk.level_skill_id IN (:levelSkillIDs) ");
        strSql.append("AND lk.company_id = :companyID ");
        query = em.createNativeQuery(strSql.toString(), LevelSkillEntity.class);
        query.setParameter("levelSkillIDs", (Collection<Long>) skillLevelDelete);
        query.setParameter("companyID", companyID);

        int updatedQuantity = query.executeUpdate();
        return updatedQuantity;

    }

}
