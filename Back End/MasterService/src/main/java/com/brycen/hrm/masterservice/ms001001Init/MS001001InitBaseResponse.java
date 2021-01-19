package com.brycen.hrm.masterservice.ms001001Init;

import com.brycen.hrm.common.base.ContentResponse;
import com.brycen.hrm.common.base.ErrorResponse;

/**
 * [Description]: Base Response for User Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS001001InitBaseResponse {

    /**
     * Content for Response
     */
    private ContentResponse content = null;

    /**
     * Error for Response
     */
    private ErrorResponse error = null;

    public ContentResponse getContent() {
        return content;
    }

    public void setContent(ContentResponse content) {
        this.content = content;
    }

    public ErrorResponse getError() {
        return error;
    }

    public void setError(ErrorResponse error) {
        this.error = error;
    }

}
