package com.giousa.customprotocoltest.server;

import com.giousa.customprotocoltest.model.Request;
import com.giousa.customprotocoltest.model.Response;
import com.giousa.customprotocoltest.model.StateCode;
import com.giousa.customprotocoltest.module.FightReponse;
import com.giousa.customprotocoltest.module.FightRequest;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class ServerHandler extends SimpleChannelHandler {


	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {

		Request request = (Request) e.getMessage();

		System.out.println("server received:"+request.toString());

		if(request.getModule() == 1){
			if(request.getCmd() == 1){
				FightRequest fightRequest = new FightRequest();
				fightRequest.readFromBytes(request.getData());
				System.out.println("server fubenId="+fightRequest.getFubenId());
				System.out.println("server count="+fightRequest.getCount());


				FightReponse fightReponse = new FightReponse();
				fightReponse.setGold(9999);

                Response response = new Response();
                response.setModule((short) 1);
                response.setCmd((short) 1);
                response.setStateCode(StateCode.SUCCESS);
                response.setData(fightReponse.getBytes());

                ctx.getChannel().write(response);
            }
		}

	}

	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		
		System.out.println("channelClosed");
		super.channelClosed(ctx, e);
	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		
		System.out.println("channelConnected");
		super.channelConnected(ctx, e);
	}

	@Override
	public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		
		System.out.println("server channelDisconnected");
		super.channelDisconnected(ctx, e);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		
		System.out.println("server exceptionCaught");
		super.exceptionCaught(ctx, e);
	}

	
}
