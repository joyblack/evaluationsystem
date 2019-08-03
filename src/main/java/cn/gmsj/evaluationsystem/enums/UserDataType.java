package cn.gmsj.evaluationsystem.enums;

/**
 * @author Alan
 */

public enum UserDataType {

    SPECIALIST("专家"),


    BUREAU_ENERGY ("贵州省能源局"),


    THIRD_PARTY("第三方机构"),


    COAL_BUREAU ("贵州省煤监局");


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
