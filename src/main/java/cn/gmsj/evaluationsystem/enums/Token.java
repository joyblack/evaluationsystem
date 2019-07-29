package cn.gmsj.evaluationsystem.enums;

/**
 * @author 13562
 */
public enum Token {
    /**
     * 用户
     */
    USER("USER", "用户信息"),
    /**
     * token请求头
     */
    AUTHORIZATION("AUTHORIZATION-GM", "token请求头"),
    /**
     * CLAIMS
     */
    CLAIMS("CLAIMS", "CLAIMS");

    private String name;

    private String describe;

    Token() {
    }

    Token(String name, String describe) {
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
    }
}
