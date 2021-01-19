package com.brycen.hrm.masterservice.ms003001Search;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ContentResponse;
import com.brycen.hrm.common.checkValue.CheckValueService;
import com.brycen.hrm.constant.TypeInitValue;
import com.brycen.hrm.entity.SkillEntity;

/**
 * [Description]: Class connect to database to search skill and send result to interface<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS003001SearchImpl implements MS003001SearchService {
    @Autowired
    public EntityManager em;

    private Query query;

    @Override
    public BaseResponse search(MS003001SearchRequest searchRequest, int companyID) {
        int pageSize = searchRequest.getPageSize();
        StringBuffer countString = new StringBuffer("SELECT COUNT(skill_id) ");
        StringBuffer selectString = new StringBuffer(
                "SELECT s.skill_id, s.company_id, s.create_by, s.create_date, s.is_delete, s.update_by, s.update_date, s.skill_code, s.skill_description, s.skill_name, st.skill_type_id ");
        StringBuffer fromString = new StringBuffer("FROM skill s ");
        StringBuffer whereString = new StringBuffer("WHERE s.company_id= :companyID And s.is_delete=0 ");
        StringBuffer orderByString = new StringBuffer("order by s.skill_name asc ");
        StringBuffer queryString = new StringBuffer();
        // Left join tables with ID
        fromString.append("LEFT JOIN skill_type st ON st.skill_type_id = s.skill_type_id ");
        fromString.append("AND st.company_id = :companyID AND st.is_delete = 0 ");

        // Condition of relationship
        whereString.append("AND ((IFNULL(st.skill_type_id, 0) = :skillTypeID) or (-1 = :skillTypeID) or ISNULL(:skillTypeID)) ");

        if (!CheckValueService.checkNull(searchRequest.getSkillCode())) {
            whereString.append(" AND s.skill_code= :skillCode ");
        }
        if (!CheckValueService.checkNull(searchRequest.getSkillName())) {
            whereString.append("AND s.skill_name like :skillName ");
        }

        countString.append(fromString).append(whereString);

        // Create query sql to count total data
        query = em.createNativeQuery(countString.toString());

        // Set parameter to count total data
        query.setParameter("companyID", companyID);
        query.setParameter("skillTypeID", searchRequest.getSkillTypeID());
        if (!CheckValueService.checkNull(searchRequest.getSkillCode())) {
            query.setParameter("skillCode", searchRequest.getSkillCode());
        }

        if (!CheckValueService.checkNull(searchRequest.getSkillName())) {
            query.setParameter("skillName", "%" + searchRequest.getSkillName() + "%");
        }
        queryString.append(selectString).append(fromString).append(whereString).append(orderByString);

        int totalElements = ((Number) query.getSingleResult()).intValue();
        Query querySelect = em.createNativeQuery(queryString.toString(), SkillEntity.class);
        querySelect.setParameter("companyID", companyID);
        querySelect.setParameter("skillTypeID", searchRequest.getSkillTypeID());

        if (!CheckValueService.checkNull(searchRequest.getSkillCode())) {
            querySelect.setParameter("skillCode", searchRequest.getSkillCode());
        }

        if (!CheckValueService.checkNull(searchRequest.getSkillName())) {
            querySelect.setParameter("skillName", "%" + searchRequest.getSkillName() + "%");
        }

        if (searchRequest.getPageSize() == 0) {
            pageSize = 30;
        }

        @SuppressWarnings("unchecked")
        List<SkillEntity> skillList = querySelect.setFirstResult(searchRequest.getPageNum() * pageSize).setMaxResults(pageSize).getResultList();
        List<Object> result = skillList.stream().map(MS003001SearchResponse::new).collect(Collectors.toList());
        if (searchRequest.getType() != null && searchRequest.getType().equals(TypeInitValue.CUSTOM_TYPE)) {
            result.add(new MS003001SearchResponse(0L, TypeInitValue.OTHER));
            result.add(0, new MS003001SearchResponse(-1L, TypeInitValue.ALL));
        }
        ContentResponse content = new ContentResponse(result, searchRequest.getPageNum(), pageSize, totalElements);
        BaseResponse baseRes = new BaseResponse();
        baseRes.setContent(content);
        return baseRes;
    }
}
