package cn.gmsj.evaluationsystem.enums;

/**
 * @author Alan
 */

public enum Result {
    /**
     * 数据
     */
    DATA("data"),
    /**
     * 错误类型
     */
    EXCEPTION_DESC("exceptionDesc"),
    /**
     * 错误消息
     */
    MESSAGE("message"),
    /**
     * 总条数
     */
    TOTAL("total"),
    /**
     * 消息标记
     */
    SUCCESS("success"),
    /**
     * 消息状态
     */
    STATUS("status");

    private String key;

    Result(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
