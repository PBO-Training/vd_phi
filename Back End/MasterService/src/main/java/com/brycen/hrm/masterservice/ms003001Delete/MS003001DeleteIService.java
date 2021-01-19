package com.brycen.hrm.masterservice.ms003001Delete;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface MS003001DeleteIService {
	int delete(List<Long> skillDelete, int companyID);
}
