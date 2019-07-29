package cn.gmsj.evaluationsystem.utils;

import cn.gmsj.evaluationsystem.enums.Result;
import com.alibaba.fastjson.JSONObject;

/**
 * @author Alan
 */
public class ResultUtil {
    /**
     * 请求成功返回结果
     *
     * @param data
     * @return
     */
    public static JSONObject success(Object data) {
        JSONObject res = new JSONObject();
        res.put(Result.DATA.getKey(), data);
        res.put(Result.SUCCESS.getKey(), true);
        res.put(Result.STATUS.getKey(), 200);
        return res;
    }

    /**
     * 请求成功不返回详细信息
     *
     * @return
     */
    public static JSONObject success() {
        JSONObject res = new JSONObject();
        res.put(Result.SUCCESS.getKey(), true);
        res.put(Result.STATUS.getKey(), 200);
        return res;
    }


    /**
     * 请求成功并分页
     *
     * @param data
     * @return
     */
    public static JSONObject pageSuccess(Object data, long total) {
        JSONObject res = new JSONObject();
        res.put(Result.TOTAL.getKey(), total);
        res.put(Result.DATA.getKey(), data);
        res.put(Result.SUCCESS.getKey(), true);
        res.put(Result.STATUS.getKey(), 200);
        return res;
    }

    public static JSONObject error(Object data) {
        JSONObject res = new JSONObject();
        res.put(Result.EXCEPTION_DESC.getKey(), "");
        res.put(Result.MESSAGE.getKey(), data);
        res.put(Result.SUCCESS.getKey(), false);
        res.put(Result.STATUS.getKey(), 406);
        return res;
    }

    public static JSONObject error() {
        JSONObject res = new JSONObject();
        res.put(Result.EXCEPTION_DESC.getKey(), "");
        res.put(Result.SUCCESS.getKey(), false);
        res.put(Result.STATUS.getKey(), 406);
        return res;
    }
}
