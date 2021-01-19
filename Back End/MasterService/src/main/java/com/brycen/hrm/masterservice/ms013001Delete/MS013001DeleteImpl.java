package com.brycen.hrm.masterservice.ms013001Delete;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.entity.HolidayDetailEntity;
import com.brycen.hrm.entity.HolidayEntity;

@Service
public class MS013001DeleteImpl implements MS013001DeleteService {

    @Autowired
    public EntityManager em;

    private Query query;

    @Transactional
    @Override
    public int delete(List<Long> holidayDelete, int companyID) {
        StringBuffer strSql = new StringBuffer();
        strSql.append("UPDATE holiday hol ");
        strSql.append("SET hol.is_delete = 1 ");
        strSql.append("WHERE hol.holiday_id IN (:holidayID) ");
        strSql.append("AND hol.company_id = :companyID ");
        query = em.createNativeQuery(strSql.toString(), HolidayEntity.class);
        query.setParameter("holidayID", (Collection<Long>) holidayDelete);
        query.setParameter("companyID", companyID);
        int updatedQuantity = query.executeUpdate();

        StringBuffer deleteDetail = new StringBuffer();
        deleteDetail.append("UPDATE holiday_detail hd ");
        deleteDetail.append("LEFT JOIN holiday h ");
        deleteDetail.append("ON h.holiday_id = hd.holiday_id ");
        deleteDetail.append("SET hd.is_delete = 1 ");
        deleteDetail.append("WHERE h.holiday_id IN (:holidayID) ");
        deleteDetail.append("AND h.company_id = :companyID ");
        deleteDetail.append("AND hd.company_id = :companyID ");
        query = em.createNativeQuery(deleteDetail.toString(), HolidayDetailEntity.class);
        query.setParameter("holidayID", (Collection<Long>) holidayDelete);
        query.setParameter("companyID", companyID);
        updatedQuantity = query.executeUpdate();

        return updatedQuantity;
    }

}
