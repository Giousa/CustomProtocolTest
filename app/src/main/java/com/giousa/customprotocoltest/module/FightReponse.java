package com.giousa.customprotocoltest.module;

import com.giousa.customprotocoltest.utils.Serializer;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2017/2/9
 * Time:下午8:29
 */

public class FightReponse extends Serializer {

    private int gold;

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    @Override
    protected void read() {
        this.gold = readInt();
    }

    @Override
    protected void write() {
        writeInt(gold);
    }
}
