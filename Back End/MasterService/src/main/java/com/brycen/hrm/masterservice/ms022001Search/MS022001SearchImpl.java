package com.brycen.hrm.masterservice.ms022001Search;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ContentResponse;
import com.brycen.hrm.common.checkValue.CheckValueService;
import com.brycen.hrm.entity.SkillProjectEntity;

@Service
public class MS022001SearchImpl implements MS022001SearchService {
    @Autowired
    private EntityManager em;

    private Query query;

    /**
     * [Description]: Use query to connect database and search language category with params<br/>
     * [ Remarks ]:<br/>
     *
     * @param searchRequest - A object is created to receive model from client request
     * @return content - MS022001SearchResponse - A object is created to contain data and send to client
     */
    @Override
    @SuppressWarnings("unchecked")
    public BaseResponse search(MS022001SearchRequest searchRequest, int companyID) {
        int pageSize = searchRequest.getPageSize();
        StringBuffer countString = new StringBuffer("SELECT COUNT(skill_project_id) ");
        StringBuffer selectString = new StringBuffer("SELECT * ");
        StringBuffer fromString = new StringBuffer("FROM skill_project sp ");
        StringBuffer whereString = new StringBuffer("WHERE sp.company_id= :companyID AND sp.is_delete=0 ");
        StringBuffer orderByString = new StringBuffer("order by sp.skill_project_name, sp.skill_project_code asc ");
        StringBuffer queryString = new StringBuffer();
        if (!CheckValueService.checkNull(searchRequest.getSkillProjectCode())) {
            whereString.append(" AND sp.skill_project_code= :skillProjectCode ");
        }
        if (!CheckValueService.checkNull(searchRequest.getSkillProjectName())) {
            whereString.append(" AND sp.skill_project_name LIKE :skillProjectName ");
        }
        queryString.append(selectString).append(fromString).append(whereString).append(orderByString);
        countString.append(fromString).append(whereString);

        // Create query sql to count total data
        query = em.createNativeQuery(countString.toString());

        // Set parameter to count total data
        query.setParameter("companyID", companyID);
        if (!CheckValueService.checkNull(searchRequest.getSkillProjectCode())) {
            query.setParameter("skillProjectCode", searchRequest.getSkillProjectCode());
        }

        if (!CheckValueService.checkNull(searchRequest.getSkillProjectName())) {
            query.setParameter("skillProjectName", "%" + searchRequest.getSkillProjectName() + "%");
        }
        int totalElements = ((Number) query.getSingleResult()).intValue();

        query = em.createNativeQuery(queryString.toString(), SkillProjectEntity.class);
        query.setParameter("companyID", companyID);
        if (!CheckValueService.checkNull(searchRequest.getSkillProjectCode())) {
            query.setParameter("skillProjectCode", searchRequest.getSkillProjectCode());
        }
        if (!CheckValueService.checkNull(searchRequest.getSkillProjectName())) {
            query.setParameter("skillProjectName", "%" + searchRequest.getSkillProjectName() + "%");
        }

        if (searchRequest.getPageSize() == 0) {
            pageSize = 30;
        }

        List<SkillProjectEntity> listSkillProject = query.setFirstResult(searchRequest.getPageNum() * pageSize).setMaxResults(searchRequest.getPageSize())
                .getResultList();
        List<Object> result = listSkillProject.stream().map(MS022001SearchResponse::new).collect(Collectors.toList());
        ContentResponse content = new ContentResponse(result, searchRequest.getPageNum(), pageSize, totalElements);
        BaseResponse baseRes = new BaseResponse();
        baseRes.setContent(content);
        return baseRes;
    }
}
