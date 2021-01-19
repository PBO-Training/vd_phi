package com.brycen.hrm.masterservice.ms002001Delete;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface MS002001DeleteIService {
	int delete(List<Long> departmentDelete, int companyID);
}
