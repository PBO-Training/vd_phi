package com.brycen.hrm.masterservice.ms010001Search;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ContentResponse;
import com.brycen.hrm.common.checkValue.CheckValueService;
import com.brycen.hrm.entity.PositionProjectEntity;

/**
 * [Description]: Class connect to database to search positionProject and send result to interface<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS010001SearchImpl implements MS010001SearchService {
    @Autowired
    public EntityManager em;

    private Query query;

    @SuppressWarnings("unchecked")
    @Override
    public BaseResponse search(MS010001SearchRequest searchRequest, int companyID) {
        int pageSize = searchRequest.getPageSize();
        List<PositionProjectEntity> positionProjectList;
        StringBuffer countString = new StringBuffer("SELECT COUNT(position_project_id) ");
        StringBuffer selectString = new StringBuffer("SELECT * ");
        StringBuffer fromString = new StringBuffer("FROM position_project pp ");
        StringBuffer whereString = new StringBuffer("WHERE pp.company_id= :companyID AND pp.is_delete=0 ");
        StringBuffer orderByString = new StringBuffer("order by position_project_name asc ");
        StringBuffer queryString = new StringBuffer();
        if (!CheckValueService.checkNull(searchRequest.getPositionProjectCode())) {
            whereString.append("AND pp.position_project_code= :positionProjectCode ");
        }
        if (!CheckValueService.checkNull(searchRequest.getPositionProjectName())) {
            whereString.append("AND pp.position_project_name LIKE :positionProjectName ");
        }
        queryString.append(selectString).append(fromString).append(whereString).append(orderByString);

        countString.append(fromString).append(whereString);

        // Create query sql to count total data
        query = em.createNativeQuery(countString.toString());

        // Set parameter to count total data
        query.setParameter("companyID", companyID);
        if (!CheckValueService.checkNull(searchRequest.getPositionProjectCode())) {
            query.setParameter("positionProjectCode", searchRequest.getPositionProjectCode());
        }

        if (!CheckValueService.checkNull(searchRequest.getPositionProjectName())) {
            query.setParameter("positionProjectName", "%" + searchRequest.getPositionProjectName() + "%");
        }

        int totalElements = ((Number) query.getSingleResult()).intValue();

        query = em.createNativeQuery(queryString.toString(), PositionProjectEntity.class);
        query.setParameter("companyID", companyID);

        if (!CheckValueService.checkNull(searchRequest.getPositionProjectCode())) {
            query.setParameter("positionProjectCode", searchRequest.getPositionProjectCode());
        }

        if (!CheckValueService.checkNull(searchRequest.getPositionProjectName())) {
            query.setParameter("positionProjectName", "%" + searchRequest.getPositionProjectName() + "%");
        }

        if (searchRequest.getPageSize() == 0) {
            pageSize = 30;
        }

        positionProjectList = query.setFirstResult(searchRequest.getPageNum() * pageSize).setMaxResults(pageSize).getResultList();
        List<Object> result = positionProjectList.stream().map(MS010001SearchResponse::new).collect(Collectors.toList());
        ContentResponse content = new ContentResponse(result, searchRequest.getPageNum(), pageSize, totalElements);
        BaseResponse baseRes = new BaseResponse();
        baseRes.setContent(content);
        return baseRes;
    }
}
