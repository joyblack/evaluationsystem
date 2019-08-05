package cn.gmsj.evaluationsystem.projectmanage.enums;

/**
 * 邀请参与方向
 *
 * @author 13562
 */

public enum InviteDirection {
    /**
     * 技术咨询报告
     */
    TECHNOLOGY_CONSULTING_REPORT("技术咨询报告");

    private String name;

    InviteDirection(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
