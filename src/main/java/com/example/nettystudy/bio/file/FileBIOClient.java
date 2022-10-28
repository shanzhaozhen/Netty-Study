package com.example.nettystudy.bio.file;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Author: shanzhaozhen
 * @Date: 2022-10-28
 * @Description:
 */
public class FileBIOClient {

    public static void main(String[] args) {
        try (
                //  1、请求与服务端的Socket链接
                Socket socket = new Socket("127.0.0.1", 9999);
                //  2、把字节输出流包装成一个数据输出流
                InputStream is = Files.newInputStream(Paths.get("文件路径"))
        ) {
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            //  3、先发送上传文件的后缀给服务端
            dos.writeUTF(".png");
            //  4、把文件数据发送给服务端进行接收
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) > 0) {
                dos.write(buffer, 0, len);
            }
            dos.flush();
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
