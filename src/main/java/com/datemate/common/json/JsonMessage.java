package com.datemate.common.json;

import com.datemate.common.constants.Constants;

import java.util.HashMap;
import java.util.Map;

public class JsonMessage {
    
    private String responseCode;
    private Object message;
    
    private String errMsg;
    
    public JsonMessage() {
        
    }
    
    public JsonMessage(String responseCode, Object message) {
        this.responseCode = responseCode;
        this.message = message;
    }
    
    public String getResponseCode() {
        return responseCode;
    }
    
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
    
    public Object getMessage() {
        return message;
    }
    
    public void setMessage(Object message) {
        this.message = message;
    }
    
    public String getErrMsg() {
        return errMsg;
    }
    
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
    
    public void setErrorMsgWithCode(String errMsg) {
        this.setResponseCode(Constants.FAILED);
        this.setErrMsg(errMsg);
    }

    @SuppressWarnings("unchecked")
    public void addAttribute(String key, Object attr) {
        
        this.setResponseCode(Constants.SUCCESS);
        
        if (this.message == null) {
            this.message = new HashMap();
        }
        
        ((Map) this.message).put(key, attr);
        
    }
    
}
