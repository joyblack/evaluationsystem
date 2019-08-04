package cn.gmsj.evaluationsystem.projectmanage.enums;

import javax.naming.Name;

/**
 * liu
 * 项目性质枚举
 *
 */
public enum NatueType {
    /**
     * 新建项目
     */
    NEW_PROJECT("新建项目"),
    /**
     * 扩建项目
     */
    EXPEND_PROJECT("扩建项目");



    private String name;

    NatueType(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
