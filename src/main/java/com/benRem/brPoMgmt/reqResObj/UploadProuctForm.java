package com.benRem.brPoMgmt.reqResObj;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by debnathchatterjee on 25/03/18.
 */
@Data
public class UploadProuctForm {

    String company;

    String prodType;

    MultipartFile[] productExcel;

}
