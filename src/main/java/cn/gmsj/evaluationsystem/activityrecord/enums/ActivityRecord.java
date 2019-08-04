package cn.gmsj.evaluationsystem.activityrecord.enums;


/**
 * 活动记录 --
 * @author Administrator
 */
public enum ActivityRecord {
    /**
     * 入库申请
     */
    STORAGE_APPLICATION("入库申请"),

    /**
     * 审查邀请
     */
    REVIEW_THE_INVITATION("审查邀请");


    private String name;

    ActivityRecord(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
