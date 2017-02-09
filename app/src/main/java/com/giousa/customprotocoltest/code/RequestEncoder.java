package com.giousa.customprotocoltest.code;

import com.giousa.customprotocoltest.constant.ConstantValue;
import com.giousa.customprotocoltest.model.Request;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

/**
 * Description: 编码器
 * Author:zhangmengmeng
 * Date:2017/2/9
 * Time:下午7:35
 *
 * 数据包格式
 * +——----——+——-----——+——----——+——----——+——-----——+
 * | 包头	| 模块号  | 命令号 |  长度  |   数据  |
 * +——----——+——-----——+——----——+——----——+——-----——+
 *
 * 包头4字节
 * 模块号2字节short
 * 命令号2字节short
 * 长度4字节(描述数据部分字节长度)
 */

public class RequestEncoder extends OneToOneEncoder{
    @Override
    protected Object encode(ChannelHandlerContext channelHandlerContext, Channel channel, Object o) throws Exception {

        Request request = (Request) o;

        ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();

        //包头
        buffer.writeInt(ConstantValue.FLAG);

        //模块号
        buffer.writeShort(request.getModule());

        //cmd
        buffer.writeShort(request.getCmd());

        //长度
        buffer.writeInt(request.getDataLength());

        //data
        if(request.getData() != null){
            buffer.writeBytes(request.getData());
        }

        return buffer;
    }
}
