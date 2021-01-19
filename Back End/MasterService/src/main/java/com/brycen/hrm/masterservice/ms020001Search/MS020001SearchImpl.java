package com.brycen.hrm.masterservice.ms020001Search;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ContentResponse;
import com.brycen.hrm.common.checkValue.CheckValueService;
import com.brycen.hrm.entity.DegreeEntity;

/**
 * [Description]: Class connect to database to search Degree and send result to interface<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS020001SearchImpl implements MS020001SearchService {
    @Autowired
    public EntityManager em;

    private Query query;

    @SuppressWarnings("unchecked")
    @Override
    public BaseResponse search(MS020001SearchRequest searchRequest, int companyID) {
        int pageSize = searchRequest.getPageSize();
        List<DegreeEntity> degreeList;
        StringBuffer countString = new StringBuffer("SELECT COUNT(degree_id) ");
        StringBuffer selectString = new StringBuffer("SELECT * ");
        StringBuffer fromString = new StringBuffer("FROM degree d ");
        StringBuffer whereString = new StringBuffer("WHERE d.company_id= :companyID AND d.is_delete=0 ");
        StringBuffer orderByString = new StringBuffer("order by degree_name asc ");
        StringBuffer queryString = new StringBuffer();

        // Build SQL query
        if (!CheckValueService.checkNull(searchRequest.getDegreeCode())) {
            whereString.append("AND d.degree_code= :degreeCode ");
        }

        if (!CheckValueService.checkNull(searchRequest.getDegreeName())) {
            whereString.append("AND d.degree_name LIKE :degreeName ");
        }

        queryString.append(selectString).append(fromString).append(whereString).append(orderByString);

        countString.append(fromString).append(whereString);

        // Create query sql to count total data
        query = em.createNativeQuery(countString.toString());

        // Set parameter to count total data
        query.setParameter("companyID", companyID);
        if (!CheckValueService.checkNull(searchRequest.getDegreeCode())) {
            query.setParameter("degreeCode", searchRequest.getDegreeCode());
        }

        if (!CheckValueService.checkNull(searchRequest.getDegreeName())) {
            query.setParameter("degreeName", "%" + searchRequest.getDegreeName() + "%");
        }
        int totalElements = ((Number) query.getSingleResult()).intValue();

        // Create query sql to get data
        query = em.createNativeQuery(queryString.toString(), DegreeEntity.class);

        // Set parameter to get data
        query.setParameter("companyID", companyID);

        if (!CheckValueService.checkNull(searchRequest.getDegreeCode())) {
            query.setParameter("degreeCode", searchRequest.getDegreeCode());
        }

        if (!CheckValueService.checkNull(searchRequest.getDegreeName())) {
            query.setParameter("degreeName", "%" + searchRequest.getDegreeName() + "%");
        }

        if (searchRequest.getPageSize() == 0) {
            pageSize = 30;
        }

        degreeList = query.setFirstResult(searchRequest.getPageNum() * pageSize).setMaxResults(pageSize).getResultList();
        List<Object> result = degreeList.stream().map(MS020001SearchResponse::new).collect(Collectors.toList());
        ContentResponse content = new ContentResponse(result, searchRequest.getPageNum(), pageSize, totalElements);
        BaseResponse baseRes = new BaseResponse();
        baseRes.setContent(content);
        return baseRes;
    }
}
