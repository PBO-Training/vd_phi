package com.brycen.hrm.masterservice.ms017001Search;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.entity.GroupScreenEntity;
import com.brycen.hrm.entity.RoleEntity;

@Service
public class MS017001SearchImpl implements MS017001SearchService {

    @Autowired
    public EntityManager em;

    @SuppressWarnings({ "unused", "unchecked" })
    @Override
    public BaseResponse search(MS017001SearchRequest searchRequest, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        Query query;
        int pageSize = searchRequest.getPageSize();
        StringBuffer queryString = new StringBuffer();
        StringBuffer selectString = new StringBuffer("SELECT * ");
        StringBuffer fromString = new StringBuffer("FROM role r ");
        StringBuffer whereString = new StringBuffer("WHERE r.company_id= :companyID AND r.is_delete=0 ");
        StringBuffer orderByString = new StringBuffer("order by role_id asc ");     
        queryString.append(selectString).append(fromString).append(whereString).append(orderByString);
        query = em.createNativeQuery(queryString.toString(), RoleEntity.class);
        query.setParameter("companyID", companyID);
        List<RoleEntity> listRole = query.getResultList();
        
        queryString.setLength(0);
        fromString.setLength(0);
        whereString.setLength(0);
        orderByString.setLength(0);
        
        fromString.append("FROM group_screen gs ");
        whereString.append("WHERE gs.company_id= :companyID AND gs.is_delete=0 ");
        orderByString.append("order by group_index asc ");
        queryString.append(selectString).append(fromString).append(whereString).append(orderByString);
        query = em.createNativeQuery(queryString.toString(), GroupScreenEntity.class);
        query.setParameter("companyID", companyID);
        List<GroupScreenEntity> listGroupScreen = query.getResultList();
        
        List<Object> resultRole = listRole.stream().map(MS017001RoleResponse::new).collect(Collectors.toList());
        List<Object> resultGroupScreen = listGroupScreen.stream().map(MS017001GroupScreenResponse::new).collect(Collectors.toList());

        baseRes.setContent(new MS017001SearchResponse(resultGroupScreen, resultRole));
        return baseRes;
    }

}
