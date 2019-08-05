package cn.gmsj.evaluationsystem.government.domain.enums;

/**
 * 邀请类型
 *
 * @author 13562
 */

public enum Invite {
    /**
     * 邀请专家
     */
    EXPERT("专家"),

    /**
     * 邀请第三方机构
     */
    THIRD_PARTY("第三方机构");

    private String name;

    Invite(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
