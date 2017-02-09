package com.giousa.customprotocoltest.module;

import com.giousa.customprotocoltest.utils.Serializer;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2017/2/9
 * Time:下午8:24
 */

public class FightRequest extends Serializer {

    private int fubenId;

    private int count;

    public int getFubenId() {
        return fubenId;
    }

    public void setFubenId(int fubenId) {
        this.fubenId = fubenId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    protected void read() {
        this.fubenId = readInt();
        this.count = readInt();
    }

    @Override
    protected void write() {
        writeInt(fubenId);
        writeInt(count);
    }
}
