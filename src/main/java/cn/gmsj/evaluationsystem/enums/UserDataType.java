package cn.gmsj.evaluationsystem.enums;

/**
 * @author Alan
 */

public enum UserDataType {
    /**
     * 数据
     */
    PERSONAGE("个人"),
    /**
     * 错误类型
     */
    BUREAU_ENERGY ("能源局"),
    /**
     * 错误消息
     */
    THIRD_PARTY("第三方机构"),
    /**
     * 总条数
     */
    COAL_BUREAU ("煤监局");


    private String name;

    UserDataType(String name) {
        this.name  = name ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
