package cn.gmsj.evaluationsystem.expertinfo.web.res;

import lombok.Data;
import lombok.ToString;

/**
 * @auther AliZhou
 * @date 2019/8/2 10:54
 */
@Data
@ToString
public class DeclareMajorListRes {
    private Long id;
    private String declareMajorName;  //申报专业名称
    private String declareMajorTypeName;  //申报专业类型名称
    private Long declareMajorTypeId;   ////申报专业类型id
}
