package com.example.springdemo.pojo.omss;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SystemOperationLog implements Serializable {
    @ExcelProperty(value ="序号" , index = 0)
    private Long id;
    @ExcelProperty(value ="日志类型", index = 1)
    private String logType;
    @ExcelProperty({"操作","操作类型"})
    private String operationType;
    @ExcelProperty({"操作","操作信息"})
    private String operationInfo;
    @ExcelProperty({"操作","操作状态"})
    private String operationState;
    @ExcelProperty({"操作","操作结果"})
    private String operationResult;
    @ExcelProperty("是否注销")
    private Integer state;
    @ExcelProperty("创建时间")
    private Date createDate;
    @ExcelProperty("创建人")
    private String createBy;
    @ExcelProperty("更新时间")
    private Date updateDate;
    @ExcelProperty("更新人")
    private String updateBy;
    @ExcelProperty("备注")
    private String remark;

}