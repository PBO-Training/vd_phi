package com.brycen.hrm.masterservice.ms004001Search;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ContentResponse;
import com.brycen.hrm.common.checkValue.CheckValueService;
import com.brycen.hrm.entity.LevelSkillEntity;

/**
 * [Description]: Search Service Implementation for Skill Level Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS004001SearchImpl implements MS004001SearchIService {

    @Autowired
    public EntityManager em;

    private Query query;

    @Override
    public BaseResponse search(MS004001SearchRequest ms004001SearchRequest, int companyID) {
        int pageSize = ms004001SearchRequest.getPageSize();
        StringBuffer countString = new StringBuffer("SELECT COUNT(level_skill_id) ");
        StringBuffer selectString = new StringBuffer("SELECT * ");
        StringBuffer fromString = new StringBuffer("FROM level_skill ls ");
        StringBuffer whereString = new StringBuffer("WHERE ls.company_id= :companyID AND ls.is_delete=0 ");
        StringBuffer orderByString = new StringBuffer("order by level_skill_name asc ");
        StringBuffer queryString = new StringBuffer();
        if (!CheckValueService.checkNull(ms004001SearchRequest.getLevelSkillCode())) {
            whereString.append("AND ls.level_skill_code= :skillLevelCode ");
        }
        if (!CheckValueService.checkNull(ms004001SearchRequest.getLevelSkillName())) {
            whereString.append("AND ls.level_skill_name LIKE :skillLevelName ");
        }
        queryString.append(selectString).append(fromString).append(whereString).append(orderByString);
        
        countString.append(fromString).append(whereString);

        // Create query sql to count total data
        query = em.createNativeQuery(countString.toString());

        // Set parameter to count total data
        query.setParameter("companyID", companyID);
        if (!CheckValueService.checkNull(ms004001SearchRequest.getLevelSkillCode())) {
            query.setParameter("skillLevelCode", ms004001SearchRequest.getLevelSkillCode());
        }

        if (!CheckValueService.checkNull(ms004001SearchRequest.getLevelSkillName())) {
            query.setParameter("skillLevelName", "%" + ms004001SearchRequest.getLevelSkillName() + "%");
        }

        int totalElements = ((Number) query.getSingleResult()).intValue();
        
        query = em.createNativeQuery(queryString.toString(), LevelSkillEntity.class);
        query.setParameter("companyID", companyID);
        if (!CheckValueService.checkNull(ms004001SearchRequest.getLevelSkillCode())) {
            query.setParameter("skillLevelCode", ms004001SearchRequest.getLevelSkillCode());
        }
        if (!CheckValueService.checkNull(ms004001SearchRequest.getLevelSkillName())) {
            query.setParameter("skillLevelName", "%" + ms004001SearchRequest.getLevelSkillName() + "%");
        }
        
        if (ms004001SearchRequest.getPageSize() == 0) {
            pageSize = 30;
        }
        
        @SuppressWarnings("unchecked")
        List<LevelSkillEntity> skillLevelList = query.setFirstResult(ms004001SearchRequest.getPageNum() * pageSize).setMaxResults(pageSize).getResultList();
        List<Object> result = skillLevelList.stream().map(MS004001SearchResponse::new).collect(Collectors.toList());
        ContentResponse content = new ContentResponse(result, ms004001SearchRequest.getPageNum(), pageSize, totalElements);
        BaseResponse baseRes = new BaseResponse();
        baseRes.setContent(content);
        return baseRes;
    }

}
