package cn.gmsj.evaluationsystem.projectmanage.enums;

/**
 * @author 13562
 */

public enum InviteState {
    /**
     * 接受
     */
    ACCEPT("接受"),
    /**
     * 拒绝
     */
    REFUSE("拒绝"),
    /**
     * 等待
     */
    AWAIT("等待");

    private String name;

    InviteState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
