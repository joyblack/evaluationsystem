package cn.gmsj.evaluationsystem.thirdparty.web.res;

import cn.gmsj.evaluationsystem.user.domain.entity.UserEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

@Data
@ToString
public class ThirdPartyRes {
    private String companyName;

    private String socialCreditCode;

    private String province;

    private String area;

    private String county;

    private String companyAddress;

    private String postalCode;

    private String companyTelephone;

    private String companyMailbox;

    private String legalRepresent;

    private String legalRepresentPhone;

    private String phone;

    private String relatedWorkAchievement;
}
