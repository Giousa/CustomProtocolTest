package com.giousa.customprotocoltest.server;

import com.giousa.customprotocoltest.code.RequestDecoder;
import com.giousa.customprotocoltest.code.ResponseEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class Server {

	public static void main(String[] args) {
		
		//服务类
		ServerBootstrap bootstrap = new ServerBootstrap();
		//创建线程池
		ExecutorService boss = Executors.newCachedThreadPool();
		ExecutorService worker = Executors.newCachedThreadPool();
		//设置NioSocket工厂
		bootstrap.setFactory(new NioServerSocketChannelFactory(boss, worker));
		//设置管道工厂
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
				pipeline.addLast("decoder", new RequestDecoder());
				pipeline.addLast("encode", new ResponseEncoder());
				pipeline.addLast("serverHandler", new ServerHandler());
				return pipeline;
			}
		});
		
		bootstrap.bind(new InetSocketAddress(7788));
		System.out.println("Server Start");
	}

}
