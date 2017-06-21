package com.benRem.brPoMgmt.reqResObj.response;

import lombok.Data;

import java.util.List;

/**
 * Created by debnathchatterjee on 06/06/17.
 */

@Data
public class AjaxResponseBody {

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Object> getResult() {
        return result;
    }

    public void setResult(List<Object> result) {
        this.result = result;
    }

    private String msg;

    private List<Object> result;


}
