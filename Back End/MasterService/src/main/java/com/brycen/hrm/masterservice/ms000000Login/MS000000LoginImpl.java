package com.brycen.hrm.masterservice.ms000000Login;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.common.checkValue.CheckValueService;
import com.brycen.hrm.common.gerenateErrorCode.ErrorCodeImplements;
import com.brycen.hrm.common.gerenateErrorCode.ErrorCodeObj;
import com.brycen.hrm.config.security.jwt.JWTUtils;
import com.brycen.hrm.config.user.UserDetailsImpl;
import com.brycen.hrm.config.user.UserDetailsServiceImpl;
import com.brycen.hrm.constant.ErrorValue;
import com.brycen.hrm.entity.CompanyEntity;
import com.brycen.hrm.entity.EmployeeEntity;
import com.brycen.hrm.entity.RoleEntity;
import com.brycen.hrm.entity.UserEntity;
import com.brycen.hrm.logger.LoggerService;
import com.brycen.hrm.repository.CompanyIRepository;
import com.brycen.hrm.repository.RoleEntityIRepository;
import com.brycen.hrm.repository.UserEntityIRepository;

@Service
public class MS000000LoginImpl implements MS000000ILoginService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JWTUtils jwtUtils;

    @Autowired
    UserEntityIRepository userIRepository;

    @Autowired
    RoleEntityIRepository roleIRepository;

    @Autowired
    CompanyIRepository companyIRepository;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    /**
     * Call log service to write log for api
     */
    @Autowired
    LoggerService logger;

    public ErrorResponse checkValue(MS000000LogInRequest loginRequest) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();

        // Check companyCode is null
        if (CheckValueService.checkNull(loginRequest.getCompanyCode())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_LOGIN)
                    .append(ErrorValue.API_LOGIN).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
            errorItemName.append("companyCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        Optional<CompanyEntity> company = companyIRepository.findByCompanyCode(loginRequest.getCompanyCode());
        // Check companyCode unknown
        if (!company.isPresent()) {
            errorItemName.append("companyCode");
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_LOGIN)
                    .append(ErrorValue.API_LOGIN).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_ILLEGAL);
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // Check username is null
        if (CheckValueService.checkNull(loginRequest.getUsername())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_LOGIN)
                    .append(ErrorValue.API_LOGIN).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
            errorItemName.append("username");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // Check password is null
        if (CheckValueService.checkNull(loginRequest.getPassword())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_LOGIN)
                    .append(ErrorValue.API_LOGIN).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
            errorItemName.append("password");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        return null;
    }

    @Override
    public BaseResponse login(MS000000LogInRequest loginRequest) {
        BaseResponse baseReponse = new BaseResponse();
        ErrorResponse errorRes = checkValue(loginRequest);

        if (errorRes != null) {
            baseReponse.setError(errorRes);
            return baseReponse;
        }

        CompanyEntity comp = companyIRepository.findByCompanyCode(loginRequest.getCompanyCode()).get();

        UserEntity user = userIRepository.findByUsernameAndCompanyIDAndIsDelete(loginRequest.getUsername(), comp.getCompanyID(), false);
        if (user == null || encoder.matches(loginRequest.getPassword(), user.getPassword()) == false) {
            // @formatter:off
            ErrorCodeObj errorCode = new ErrorCodeObj(
                    ErrorValue.TYPE_INPUT_VALUE_ERROR, 
                    ErrorValue.SERVICE_API_MASTER, 
                    ErrorValue.PACKAGE_USER,
                    ErrorValue.API_LOGIN, 
                    ErrorValue.METHOD_POST, 
                    ErrorValue.REASON_VALUE_ILLEGAL);
            // @formatter:on
            ErrorCodeImplements error = new ErrorCodeImplements();
            baseReponse.setError(new ErrorResponse(error.generateErrorCode(errorCode), "username"));
            return baseReponse;
        }
        //setAuthentication
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername() + "---" + comp.getCompanyID());
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetail = (UserDetailsImpl) authentication.getPrincipal();

        EmployeeEntity employeeEntity = user.getEmployee();
        if(employeeEntity.getIsDelete()) {
         // @formatter:off
            ErrorCodeObj errorCode = new ErrorCodeObj(
                    ErrorValue.TYPE_INPUT_VALUE_ERROR, 
                    ErrorValue.SERVICE_API_MASTER, 
                    ErrorValue.PACKAGE_USER,
                    ErrorValue.API_LOGIN, 
                    ErrorValue.METHOD_POST, 
                    ErrorValue.REASON_VALUE_ILLEGAL);
            // @formatter:on
            ErrorCodeImplements error = new ErrorCodeImplements();
            baseReponse.setError(new ErrorResponse(error.generateErrorCode(errorCode), "username"));
            return baseReponse;
        }
        
        String jwt = jwtUtils.generateJwtToken(authentication);
        CompanyEntity companyEntity = companyIRepository.findByCompanyID(user.getCompanyID()).get();
        Long employeeID = employeeEntity.getEmployeeID();
        String fullName = (employeeEntity.getFirstName() == null ? "" : employeeEntity.getFirstName()) + " " + (employeeEntity.getLastName() == null ? "" : employeeEntity.getLastName());
        String avataURL = employeeEntity.getAvataUrl();
        List<RoleEntity> roles = roleIRepository.findByRoleCodeAndCompanyID(userDetail.getRoleCode(), comp.getCompanyID());

        List<MS000000RoleResponse> role = roles.stream().map(MS000000RoleResponse::new).collect(Collectors.toList());
        baseReponse.setContent(new MS000000LogInResponse(jwt, userDetail.getId(), userDetail.getUsername(), employeeID, fullName, avataURL, role.get(0),
                companyEntity.getCompanyCode(), companyEntity.getCompanyName()));
        return baseReponse;
    }

}
