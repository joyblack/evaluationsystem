package cn.gmsj.evaluationsystem.expertinfo.domain.enums;

/**
 * 专家基本信息状态
 */
public enum ExpertInfoType {

    SAVE("SAVE","保存"),

    COMMIT("COMMIT","提交"),

    AUDIT("AUDIT","审核通过"),

    REFUSE("REFUSE","拒绝");

    private String name;

    private String describe;

    ExpertInfoType(String name, String describe) {
        this.name = name;
        this.describe = describe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }}
