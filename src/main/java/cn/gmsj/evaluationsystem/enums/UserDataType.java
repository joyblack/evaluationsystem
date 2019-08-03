package cn.gmsj.evaluationsystem.enums;

/**
 * @author Alan
 */

public enum UserDataType {

    /**
     * 专家
     */
    SPECIALIST("专家"),

    /**
     * 贵州省能源局
     */
    BUREAU_ENERGY("贵州省能源局"),

    /**
     * 第三方机构
     */
    THIRD_PARTY("第三方机构"),

    /**
     * 贵州省煤监局
     */
    COAL_BUREAU("贵州省煤监局");


    private String name;

    UserDataType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
