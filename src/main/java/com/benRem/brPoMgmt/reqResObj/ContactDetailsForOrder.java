package com.benRem.brPoMgmt.reqResObj;

import lombok.Data;
import org.json.JSONObject;

/**
 * Created by debnathchatterjee on 13/07/17.
 */
@Data
public class ContactDetailsForOrder {


    private String contactName;

    private String contactEmail;



    private String contactPhone;

    public String toString(){
        String info = "";

        JSONObject jsonInfo = new JSONObject();
        jsonInfo.put("contactName",this.contactName);
        jsonInfo.put("contactEmail",this.contactEmail);
        jsonInfo.put("contactPhone",this.contactPhone);

        info = jsonInfo.toString();
        return info;
    }


}
