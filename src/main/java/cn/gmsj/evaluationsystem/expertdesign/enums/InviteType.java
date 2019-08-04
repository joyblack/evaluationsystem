package cn.gmsj.evaluationsystem.expertdesign.enums;

/**
 * 邀请类型枚举
 */

public enum InviteType {
    /**
     * 技术咨询报告
     */
    SCIENCE_REPOT("技术咨询报告"),

    /**
     * 贵州省能源局
     */
    PROVINCIAL_ENERGY_BUREAU("贵州省能源局"),

    /**
     * 贵州省煤矿安全局
     */
    ＰROVINCIAL_COAL_BUREAU("贵州省煤矿安全局"),

    /**
     * 贵州天天上当科技有限公司
     */
    SCIENCE_COMPANY("贵州天天上当科技有限公司");



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
