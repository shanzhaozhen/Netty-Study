package com.example.nettystudy.bio.multi;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @Author: shanzhaozhen
 * @Date: 2022-10-27
 * @Description:
 */
public class ServerReadThread extends Thread {
    private final Socket socket;

    public ServerReadThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        //（3）从Socket管道中得到一个字节输入流。
        try (InputStream inputStream = socket.getInputStream()) {
            //（4）把字节输入流包装成自己需要的流进行数据的读取。
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            //（5）读取数据
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println("服务端收到：" + socket.getRemoteSocketAddress() + ":" + line);
            }
        } catch (Exception e) {
            System.out.println(socket.getRemoteSocketAddress() + "下线了！");
        }
    }

}