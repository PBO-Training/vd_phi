package com.brycen.hrm.masterservice.ms017002Init;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.entity.ActionEntity;
import com.brycen.hrm.entity.GroupScreenEntity;
import com.brycen.hrm.entity.ScreenEntity;
import com.brycen.hrm.masterservice.ms017002Init.dto.MS017002InitActionResponse;
import com.brycen.hrm.masterservice.ms017002Init.dto.MS017002InitGroupScreenResponse;
import com.brycen.hrm.masterservice.ms017002Init.dto.MS017002InitResponse;
import com.brycen.hrm.masterservice.ms017002Init.dto.MS017002InitScreenResponse;
import com.brycen.hrm.repository.ActionIRepository;
import com.brycen.hrm.repository.GroupScreenIRepository;
import com.brycen.hrm.repository.ScreenIRepository;

@Service
public class MS017002InitImpl implements MS017002InitService {

    @Autowired
    GroupScreenIRepository groupScreenIRepository;

    @Autowired
    ActionIRepository actionIRepository;

    @Autowired
    ScreenIRepository screenIRepository;

    @Override
    public BaseResponse init(int companyID) {
        BaseResponse baseRes = new BaseResponse();
        List<GroupScreenEntity> listGroupScreen = groupScreenIRepository.findByCompanyIDAndIsDeleteOrderByGroupIndex(companyID, false);
        List<ActionEntity> listAction = actionIRepository.findByCompanyIDAndIsDelete(companyID, false);
        List<ScreenEntity> listScreen = screenIRepository.findByCompanyIDAndIsDeleteOrderByScreenCode(companyID, false);

        List<Object> resultGroupScreen = listGroupScreen.stream().map(MS017002InitGroupScreenResponse::new).collect(Collectors.toList());
        List<Object> resultAction = listAction.stream().map(MS017002InitActionResponse::new).collect(Collectors.toList());
        List<Object> resultScreen = listScreen.stream().map(MS017002InitScreenResponse::new).collect(Collectors.toList());
        baseRes.setContent(new MS017002InitResponse(resultGroupScreen, resultScreen, resultAction));
        return baseRes;
    }

}
