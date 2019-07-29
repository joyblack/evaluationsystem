package cn.gmsj.evaluationsystem.common.web.req;

import lombok.Data;
import lombok.ToString;

/**
 * @author Alan
 * 分页列表基础参数
 */
@Data
@ToString
public class BasePageReq {
    /**
     * 页码
     */
    private int page = 1;
    /**
     * 长度
     */
    private int size = 10;
    /**
     * 模糊搜索字段
     */
    private String search;
}
