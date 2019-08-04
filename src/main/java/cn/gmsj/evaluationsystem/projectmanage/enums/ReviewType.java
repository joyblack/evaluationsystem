package cn.gmsj.evaluationsystem.projectmanage.enums;

/**
 * @author 13562
 */

public enum ReviewType {
    /**
     * 初设评审
     */
    PRIMARY_DESIGN("初设评审"),
    /**
     * 安设评审
     */
    SAFE_DESIGN("安设评审");
    private String name;

    ReviewType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
