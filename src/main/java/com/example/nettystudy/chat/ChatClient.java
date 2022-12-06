package com.example.nettystudy.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @Author: shanzhaozhen
 * @Date: 2022-12-06
 * @Description:
 */
public class ChatClient {

    public static void main(String[] args) {

        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
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
                        pipeline.addLast(new ChatClientHandler());
                    }
                });

        ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 9000).sync();
        // 得到 channel
        Channel channel = channelFuture.channel();
        System.out.println("============" + channel.localAddress() + "==============");
        // 客户端需要输入信息，创建一个扫描器



    }

}
