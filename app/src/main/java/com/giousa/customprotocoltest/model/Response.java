package com.giousa.customprotocoltest.model;

import java.util.Arrays;

/**
 * Description: 返回对象
 * Author:zhangmengmeng
 * Date:2017/2/9
 * Time:下午8:02
 */

public class Response {

    //模块
    private short module;

    //命令号
    private short cmd;

    //状态码
    private int stateCode;

    //数据部分
    private byte[] data;

    public short getModule() {
        return module;
    }

    public void setModule(short module) {
        this.module = module;
    }

    public short getCmd() {
        return cmd;
    }

    public void setCmd(short cmd) {
        this.cmd = cmd;
    }

    public int getStateCode() {
        return stateCode;
    }

    public void setStateCode(int stateCode) {
        this.stateCode = stateCode;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getDataLength(){
        if(data == null){
            return 0;
        }
        return data.length;
    }

    @Override
    public String toString() {
        return "Response{" +
                "module=" + module +
                ", cmd=" + cmd +
                ", stateCode=" + stateCode +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
