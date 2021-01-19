package com.brycen.hrm.masterservice.ms015001Search;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.entity.ContractTypeEntity;

@Service
public class MS015001SearchImpl implements MS015001SearchService {
    @Autowired
    private MS015001SearchRepository searchRepository;

    @Override
    public BaseResponse search(int companyID) {
        BaseResponse baseRes = new BaseResponse();
        List<ContractTypeEntity> listContractType = searchRepository.findAllByCompanyIDAndIsDelete(companyID, false);
        List<Object> result = listContractType.stream().map(MS015001SearchResponse::new).collect(Collectors.toList());
        baseRes.setContent(result);
        return baseRes;
    }

}
