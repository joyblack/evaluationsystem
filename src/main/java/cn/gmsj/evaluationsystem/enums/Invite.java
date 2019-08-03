package cn.gmsj.evaluationsystem.enums;

/**
 * 邀请类型
 */

public enum Invite {
    /**
     * 邀请专家
     */
    INVITE_XPERT("邀请专家"),

    /**
     * 邀请第三方机构
     */
    INVITE_THIRD_PARTY("邀请第三方机构");



    private String name;

    Invite(String name) {
        this.name  = name ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
