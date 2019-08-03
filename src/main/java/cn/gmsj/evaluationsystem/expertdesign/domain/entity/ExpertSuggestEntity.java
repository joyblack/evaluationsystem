package cn.gmsj.evaluationsystem.expertdesign.domain.entity;

import cn.gmsj.evaluationsystem.common.domain.entity.BaseEntity;
import cn.gmsj.evaluationsystem.projectmanage.domain.entity.ProjectEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ToString
@Entity(name = "all_exper_suggest")
public class ExpertSuggestEntity extends BaseEntity implements Serializable {


    private static final long serialVersionUID = -6984004830277560755L;

    private String experSuggest;//专家建议

   // @NotNull(message = "不能为空")
    @JoinColumn(name = "positional_type_id")
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    private ProjectEntity projectEntity;



}
