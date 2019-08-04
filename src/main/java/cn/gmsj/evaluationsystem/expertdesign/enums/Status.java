package cn.gmsj.evaluationsystem.expertdesign.enums;

/**
 * 是否接受枚举
 */

public enum Status {
    /**
     * 接受
     */
    STATUS_ACCEPT("accept"),

    /**
     * 拒绝
     */
    STATUS_REFUSE("refuse"),
    /**
     * 等待
     */
    STATUS_WAIT("wait");


    private String key;

    Status(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
