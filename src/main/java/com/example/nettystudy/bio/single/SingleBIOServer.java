package com.example.nettystudy.bio.single;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: shanzhaozhen
 * @Date: 2022-10-26
 * @Description:
 */
public class SingleBIOServer {


    public static void main(String[] args) {
        System.out.println("==服务器的启动==");
        // （1）注册端口
        try (ServerSocket serverSocket = new ServerSocket(9999)) {
            //（2）开始在这里暂停等待接收客户端的连接,得到一个端到端的Socket管道
            Socket socket = serverSocket.accept();
            //（3）从Socket管道中得到一个字节输入流。
            InputStream inputStream = socket.getInputStream();
            //（4）把字节输入流包装成自己需要的流进行数据的读取。
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            //（5）读取数据
            String msg;
            while ((msg = bufferedReader.readLine()) != null) {
                System.out.println("服务端接收到的消息为：" + msg);
            }

//            while (true) {
//                while ((msg = bufferedReader.readLine()) != null) {
//                    System.out.println("服务端接收到的消息为：" + msg);
//                }
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
