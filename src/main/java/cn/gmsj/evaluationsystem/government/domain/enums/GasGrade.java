package cn.gmsj.evaluationsystem.government.domain.enums;

/**
 * liu
 * 瓦斯等级
 */
public enum GasGrade {
    /**
     * 低瓦斯矿井
     */
    LOW_GAS_MINES("低瓦斯矿井"),
    /**
     * 高瓦斯矿井
     */
    HIGHT_GAS_MINES("高瓦斯矿井"),
    /**
     * 煤(岩)与瓦斯(二氧化碳)突出矿井
     */
    EXTRUDE_MINES("煤(岩)与瓦斯(二氧化碳)突出矿井");

    private String message;

    GasGrade(String message){
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
