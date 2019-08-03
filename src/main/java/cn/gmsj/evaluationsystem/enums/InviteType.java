package cn.gmsj.evaluationsystem.enums;

/**
 * 邀请类型
 */

public enum InviteType {
    /**
     * 技术咨询报告
     */
    SCIENCE_REPOT("技术咨询报告"),

    /**
     * 邀请第三方机构
     */
    INVITE_THIRD_PARTY("邀请第三方机构");



    private String name;

    InviteType(String name) {
        this.name  = name ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
