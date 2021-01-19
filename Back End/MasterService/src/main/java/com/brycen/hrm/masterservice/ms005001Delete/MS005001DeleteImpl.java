package com.brycen.hrm.masterservice.ms005001Delete;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.entity.LanguageEntity;

@Service
public class MS005001DeleteImpl implements MS005001DeleteService {

    @Autowired
    public EntityManager em;

    private Query query;

    @Transactional
    @Override
    public int delete(List<Long> languageDelete, int companyID) {
        // TODO Auto-generated method stub
        StringBuffer strSql = new StringBuffer();
        strSql.append("UPDATE language l ");
        strSql.append("SET l.is_delete = 1 ");
        strSql.append("WHERE l.language_id IN (:languageIDs) ");
        strSql.append("AND l.company_id = :companyID ");
        query = em.createNativeQuery(strSql.toString(), LanguageEntity.class);
        query.setParameter("languageIDs", (Collection<Long>) languageDelete);
        query.setParameter("companyID", companyID);

        int updatedQuantity = query.executeUpdate();
        return updatedQuantity;
    }

}
