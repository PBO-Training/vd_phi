package com.brycen.hrm.masterservice.ms003001Delete;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.entity.SkillEntity;

@Service
public class MS003001DeleteImpl implements MS003001DeleteIService {

	@Autowired
	public EntityManager em;

	private Query query;

	@Transactional
	@Override
	public int delete(List<Long> skillDelete, int companyID) {
		// TODO Auto-generated method stub
		StringBuffer strSql = new StringBuffer();
		strSql.append("UPDATE skill d ");
		strSql.append("SET d.is_delete = 1 ");
		strSql.append("WHERE d.skill_id IN (:skillIDs) ");
		strSql.append("AND d.company_id = :companyID ");
		query = em.createNativeQuery(strSql.toString(), SkillEntity.class);
		query.setParameter("skillIDs", (Collection<Long>) skillDelete);
		query.setParameter("companyID", companyID);

		int updatedQuantity = query.executeUpdate();
		return updatedQuantity;

	}

}
