package com.example.nettystudy.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @Author: shanzhaozhen
 * @Date: 2022-12-05
 * @Description:
 */
public class ChatServer {

    public static void main(String[] args) {
        NioEventLoopGroup bossEventExecutors = new NioEventLoopGroup(1);
        NioEventLoopGroup workerEventExecutors = new NioEventLoopGroup(8);

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(bossEventExecutors, workerEventExecutors)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
//                .childHandler((ChannelInitializer) (ch) -> {
//                    ChannelPipeline pipeline =
//
//                })
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        // 计入特殊分隔符分包解码器
//                        pipeline.addLast(new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer("_".getBytes())));
                        // 向 pipeline 加入解码器
                        pipeline.addLast("decoder", new StringDecoder());
                        // 向 pipeline 加入编码器
                        pipeline.addLast("encoder", new StringEncoder());
                        // 加入自己的业务处理handler
                        pipeline.addLast(new ChatServerHandler());
                    }
                });

        try {
            System.out.println("聊天室 Server 启动。。。");
            ChannelFuture channelFuture = serverBootstrap.bind(9000).sync();
            // 关闭通道
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            bossEventExecutors.shutdownGracefully();
            workerEventExecutors.shutdownGracefully();
        }

    }

}
