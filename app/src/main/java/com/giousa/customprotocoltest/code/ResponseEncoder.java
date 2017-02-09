package com.giousa.customprotocoltest.code;

import com.giousa.customprotocoltest.constant.ConstantValue;
import com.giousa.customprotocoltest.model.Request;
import com.giousa.customprotocoltest.model.Response;

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
 * | 包头	| 模块号  | 命令号 |   状态码 |  长度  |   数据  |
 * +——----——+——-----——+——----——+——----——+——-----——+
 *
 * 包头4字节
 * 模块号2字节short
 * 命令号2字节short
 * 状态码4个字节int
 * 长度4字节(描述数据部分字节长度)
 */

public class ResponseEncoder extends OneToOneEncoder{
    @Override
    protected Object encode(ChannelHandlerContext channelHandlerContext, Channel channel, Object o) throws Exception {

        Response response = (Response) o;

        ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();

        //包头
        buffer.writeInt(ConstantValue.FLAG);

        //模块号
        buffer.writeShort(response.getModule());

        //cmd
        buffer.writeShort(response.getCmd());

        //状态码
        buffer.writeInt(response.getStateCode());

        //长度
        buffer.writeInt(response.getDataLength());

        //data
        if(response.getData() != null){
            buffer.writeBytes(response.getData());
        }

        return buffer;
    }
}
