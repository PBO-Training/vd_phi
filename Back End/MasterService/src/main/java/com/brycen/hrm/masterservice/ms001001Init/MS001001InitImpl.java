package com.brycen.hrm.masterservice.ms001001Init;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.generateDropDownList.GenerateDropDownList;
import com.brycen.hrm.entity.DepartmentEntity;
import com.brycen.hrm.entity.RoleEntity;
import com.brycen.hrm.masterservice.ms002001Search.MS002001SearchRepository;
import com.brycen.hrm.repository.RoleEntityIRepository;

/**
 * [Description]: Init Service Implementation for User Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS001001InitImpl implements MS001001InitIService {

    /**
     * Support find all role
     */
    @Autowired
    private RoleEntityIRepository roleEntityIRepository;

    @Autowired
    private MS002001SearchRepository ms002001SearchRepository;

    @Override
    public MS001001InitResponse init(int companyID, HttpServletRequest req) {
        MS001001InitResponse ms001001InitResponse = new MS001001InitResponse();
        List<DepartmentEntity> listDepartmentEnities = this.ms002001SearchRepository.findAllByCompanyIDAndIsDelete(companyID, false);
        ms001001InitResponse.setListDepartment(GenerateDropDownList
                .generateDefaultValue(listDepartmentEnities.stream().map(MS001001InitDepartmentResponse::new).collect(Collectors.toList())));

        List<RoleEntity> listRoleEnities = this.roleEntityIRepository.findAllByCompanyIDAndIsDelete(companyID, false);
        ms001001InitResponse.setListRole(GenerateDropDownList
                .generateDefaultValue(listRoleEnities.stream().map(MS001001InitRoleResponse::new).collect(Collectors.toList())));

        return ms001001InitResponse;
    }

}
