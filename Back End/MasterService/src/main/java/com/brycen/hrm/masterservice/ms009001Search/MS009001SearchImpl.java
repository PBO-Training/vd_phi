package com.brycen.hrm.masterservice.ms009001Search;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ContentResponse;
import com.brycen.hrm.common.checkValue.CheckValueService;
import com.brycen.hrm.entity.CustomerEntity;

/**
 * [Description]: Class connect to database to search customer and send result to interface<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS009001SearchImpl implements MS009001SearchService {
    @Autowired
    public EntityManager em;

    private Query query;

    @Override
    public BaseResponse search(MS009001SearchRequest searchRequest, int companyID) {
        int pageSize = searchRequest.getPageSize();
        StringBuffer countString = new StringBuffer("SELECT COUNT(customer_id) ");
        StringBuffer selectString = new StringBuffer("SELECT * ");
        StringBuffer fromString = new StringBuffer("FROM customer c ");
        StringBuffer whereString = new StringBuffer("WHERE c.company_id= :companyID AND c.is_delete=0 ");
        StringBuffer orderByString = new StringBuffer("order by customer_name asc ");
        StringBuffer queryString = new StringBuffer();
        if (!CheckValueService.checkNull(searchRequest.getCustomerCode())) {
            whereString.append("AND c.customer_code= :customerCode ");
        }
        if (!CheckValueService.checkNull(searchRequest.getCustomerName())) {
            whereString.append("AND c.customer_name LIKE :customerName ");
        }
        queryString.append(selectString).append(fromString).append(whereString).append(orderByString);

        countString.append(fromString).append(whereString);

        // Create query sql to count total data
        query = em.createNativeQuery(countString.toString());

        // Set parameter to count total data
        query.setParameter("companyID", companyID);
        if (!CheckValueService.checkNull(searchRequest.getCustomerCode())) {
            query.setParameter("customerCode", searchRequest.getCustomerCode());
        }

        if (!CheckValueService.checkNull(searchRequest.getCustomerName())) {
            query.setParameter("customerName", "%" + searchRequest.getCustomerName() + "%");
        }

        int totalElements = ((Number) query.getSingleResult()).intValue();

        query = em.createNativeQuery(queryString.toString(), CustomerEntity.class);
        query.setParameter("companyID", companyID);

        if (!CheckValueService.checkNull(searchRequest.getCustomerCode())) {
            query.setParameter("customerCode", searchRequest.getCustomerCode());
        }

        if (!CheckValueService.checkNull(searchRequest.getCustomerName())) {
            query.setParameter("customerName", "%" + searchRequest.getCustomerName() + "%");
        }

        if (searchRequest.getPageSize() == 0) {
            pageSize = 30;
        }

        @SuppressWarnings("unchecked")
        List<CustomerEntity> customerList = query.setFirstResult(searchRequest.getPageNum() * pageSize).setMaxResults(pageSize).getResultList();
        List<Object> result = customerList.stream().map(MS009001SearchResponse::new).collect(Collectors.toList());
        ContentResponse content = new ContentResponse(result, searchRequest.getPageNum(), pageSize, totalElements);
        BaseResponse baseRes = new BaseResponse();
        baseRes.setContent(content);
        baseRes.setError(null);
        return baseRes;
    }
}
