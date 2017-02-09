package com.giousa.customprotocoltest.client;

import com.giousa.customprotocoltest.model.Response;
import com.giousa.customprotocoltest.model.StateCode;
import com.giousa.customprotocoltest.module.FightReponse;
import com.giousa.customprotocoltest.module.FightRequest;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
/**
 * 消息接受处理类
 * @author -琴兽-
 *
 */
public class ClientHandler extends SimpleChannelHandler {

	/**
	 * 接收消息
	 */
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {

		Response response = (Response) e.getMessage();

		System.out.println("client receievd:"+ response.toString());
		if(response.getModule() == 1){
			if(response.getCmd() == 1){
				FightReponse fightReponse = new FightReponse();
				fightReponse.readFromBytes(response.getData());
				System.out.println("client gold="+fightReponse.getGold());
			}
		}

	}

	/**
	 * 捕获异常
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		System.out.println("exceptionCaught");
		super.exceptionCaught(ctx, e);
	}

	/**
	 * 新连接
	 */
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		System.out.println("channelConnected");
		super.channelConnected(ctx, e);
	}

	/**
	 * 必须是链接已经建立，关闭通道的时候才会触发
	 */
	@Override
	public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		System.out.println("client channelDisconnected");
		super.channelDisconnected(ctx, e);
	}

	/**
	 * channel关闭的时候触发
	 */
	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		System.out.println("client channelClosed");
		super.channelClosed(ctx, e);
	}
}
