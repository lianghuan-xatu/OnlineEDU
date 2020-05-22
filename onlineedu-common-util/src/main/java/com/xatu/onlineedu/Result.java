package com.xatu.onlineedu;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回结果
 */
public class Result {
    //是否成功
    private Boolean success;
    //返回码
    private Integer code;
    //返回消息
    private String message;
    //返回数据
    private Map<String,Object> data = new HashMap<>();

    private Result(){
        //构造方法私有化，设置类使用权限
    }

    /**
     * 成功静态方法
     * @return
     */
    public static Result success(){
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("成功");
        return result;
    }
    /**
     * 失败静态方法
     * @return
     */
    public static Result fail(){
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultCode.ERROR);
        result.setMessage("失败");
        return result;
    }

    public Result success(Boolean success){
        this.setSuccess(success);
        return this;
    }
    public Result code(Integer resultCode){
        this.setCode(resultCode);
        return this;
    }
    public Result message(String message){
        this.setMessage(message);
        return this;
    }
    public Result data(String key,Object value){
        this.data.put(key,value);
        return this;
    }


    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

}
