package com.brycen.hrm.masterservice.ms013001Delete;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface MS013001DeleteService {
    int delete(List<Long> holidayDelete, int companyID);
}
