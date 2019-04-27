package com.example.demo.utils;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zhuhaitao
 * @date 2018/12/21 18:16
 */

@Data
@Accessors(chain = true)
public class ServiceResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private String rtnInfo;
    private String errorMessage;
    private String rtnCode;
    private String rtnPage;
    private boolean isSuccess;


}
