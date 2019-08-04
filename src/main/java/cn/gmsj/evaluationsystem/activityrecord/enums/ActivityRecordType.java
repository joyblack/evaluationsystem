package cn.gmsj.evaluationsystem.activityrecord.enums;


/**
 * 活动记录操作类型
 * @author Administrator
 */
public enum ActivityRecordType {
    /**
     * 提交
     */
    SUBMIT("提交"),

    /**
     * 拒绝
     */
    refuse("拒绝"),

    /**
     * 接受
     */
    ACCEPT("接受"),

    /**
     * 通过
     */
    PASS_THROUGH("通过");


    private String name;

    ActivityRecordType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
