package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import bio.IOHandlerThread;

public class SocketChannelServer {

	public SocketChannelServer() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args){
		try {
			ServerSocketChannel ssc = ServerSocketChannel.open();
			ServerSocket serverSocket = ssc.socket();
			serverSocket.setReuseAddress(true);
			serverSocket.bind(new InetSocketAddress(10086));
			System.out.println("服务器：服务启动...");
			ssc.configureBlocking(false);
			Selector  selector = Selector.open();
			int interestSet = SelectionKey.OP_ACCEPT;
			ssc.register(selector, interestSet);
			ExecutorService pool = Executors.newFixedThreadPool(3);
				while(true){
					selector.select();
					Set<SelectionKey> keys = selector.selectedKeys();
					for(SelectionKey key:keys){
						if(key.isAcceptable()){
							System.out.println("服务器：接收到客户那的连接请求。。。");
							ServerSocketChannel server = (ServerSocketChannel)key.channel();
							
							SocketChannel sc = server.accept();
							if(sc!=null){
								sc.configureBlocking(false);
								ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
								selector.selectedKeys().clear();
								sc.register(selector, SelectionKey.OP_READ,byteBuffer);
								System.out.println("服务器：启动了服务器的监听读的事件，，，");
								System.out.println(sc.socket().getInetAddress().getHostName());
							}
						}else if(key.isReadable()){
							System.out.println("服务器：接收到客户发送的数据。。。");
							SocketChannel sc = (SocketChannel)key.channel();
							ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
							int i = 0;
							String content = "";
							while((i=sc.read(byteBuffer))>0){
								content += new String(byteBuffer.array());
							}
							byteBuffer.clear();
							if(content.equals("Daisy")){
								
								System.out.println("服务器：客户端发送的数据："+content+",I v o");
							}else{
								System.out.println("服务器：客户端发送的数据："+content+",滚蛋");
							}
							
							
							System.out.println("服务器：服务器端发送的数据。。。");
							ByteBuffer byteBuffer1 = ByteBuffer.wrap(new String(content).getBytes());
							sc.write(byteBuffer1);
							byteBuffer1.flip();
							byteBuffer1.clear();
							sc.close();
						}
					}
				}
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
