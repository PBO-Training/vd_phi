package com.brycen.hrm.masterservice.ms002001Delete;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.entity.DepartmentEntity;

@Service
public class MS002001DeleteImpl implements MS002001DeleteIService {

	@Autowired
	public EntityManager em;

	private Query query;

	@Transactional
	@Override
	public int delete(List<Long> departmentDelete, int companyID) {
		// TODO Auto-generated method stub
		StringBuffer strSql = new StringBuffer();
		strSql.append("UPDATE department d ");
		strSql.append("SET d.is_delete = 1 ");
		strSql.append("WHERE d.department_id IN (:departmentIDs) ");
		strSql.append("AND d.company_id = :companyID ");
		query = em.createNativeQuery(strSql.toString(), DepartmentEntity.class);
		query.setParameter("departmentIDs", (Collection<Long>) departmentDelete);
		query.setParameter("companyID", companyID);
		int updatedQuantity = query.executeUpdate();
		return updatedQuantity;
	}

}
