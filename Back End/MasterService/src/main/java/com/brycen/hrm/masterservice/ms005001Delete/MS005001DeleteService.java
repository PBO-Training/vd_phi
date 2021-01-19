package com.brycen.hrm.masterservice.ms005001Delete;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface MS005001DeleteService {
    int delete(List<Long> languageDelete, int companyID);
}
