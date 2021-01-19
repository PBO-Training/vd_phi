package com.brycen.hrm.masterservice.ms000000Login;

import com.brycen.hrm.common.base.BaseResponse;

public interface MS000000ILoginService {
  
    /**
     * [Description]:Medthod check Login<br/>
     * [ Remarks ]:<br/>
     *
     * @param loginRequest
     * @return
     */
    BaseResponse login(MS000000LogInRequest loginRequest);
}
