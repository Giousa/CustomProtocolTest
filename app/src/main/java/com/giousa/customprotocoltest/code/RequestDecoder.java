package com.giousa.customprotocoltest.code;

import com.giousa.customprotocoltest.constant.ConstantValue;
import com.giousa.customprotocoltest.model.Request;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

/**
 * Description: 解码器
 * Author:zhangmengmeng
 * Date:2017/2/9
 * Time:下午7:45
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
 *
 *  读指针 <= 写指针
 *  0 =< readerIndex =< writerIndex
 *
 */

public class RequestDecoder extends FrameDecoder {


    //数据包基本长度
    public static int BASE_LENGTH = 4+2+2+4;

    @Override
    protected Object decode(ChannelHandlerContext channelHandlerContext, Channel channel, ChannelBuffer channelBuffer) throws Exception {

        //可读长度 >= 基本长度,数据才有效
        if(channelBuffer.readableBytes() >= BASE_LENGTH){

            //记录包头开始的index
            int beginReader = channelBuffer.readerIndex();

            //包头
            while (true){
                if(channelBuffer.readInt() == ConstantValue.FLAG){
                    break;
                }
            }

            //模块号
            short module = channelBuffer.readShort();

            //命令号
            short cmd = channelBuffer.readShort();

            //长度
            int length = channelBuffer.readInt();

            //判断请求数据包是否到齐
            if(channelBuffer.readableBytes() < length){

                //还原读指针
                channelBuffer.readerIndex(beginReader);
                return null;
            }

            byte[] data = new byte[length];
            channelBuffer.readBytes(data);

            Request request = new Request();
            request.setModule(module);
            request.setCmd(cmd);
            request.setData(data);

            //继续往下传递
            return request;
        }

        //数据包不完整,需要等待后面的包到来


        return null;
    }
}
