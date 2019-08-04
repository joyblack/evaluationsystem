package cn.gmsj.evaluationsystem.examine.web.req;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * Created by XiaoWen on 2019/8/4
 */
@ToString
@Data
public class ExamineReq {

    @NotNull(message = "id不能为空")
    private Long id;

}
