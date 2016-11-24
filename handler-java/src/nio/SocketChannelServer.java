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
			System.out.println("����������������...");
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
							System.out.println("�����������յ��ͻ��ǵ��������󡣡���");
							ServerSocketChannel server = (ServerSocketChannel)key.channel();
							
							SocketChannel sc = server.accept();
							if(sc!=null){
								sc.configureBlocking(false);
								ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
								selector.selectedKeys().clear();
								sc.register(selector, SelectionKey.OP_READ,byteBuffer);
								System.out.println("�������������˷������ļ��������¼�������");
								System.out.println(sc.socket().getInetAddress().getHostName());
							}
						}else if(key.isReadable()){
							System.out.println("�����������յ��ͻ����͵����ݡ�����");
							SocketChannel sc = (SocketChannel)key.channel();
							ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
							int i = 0;
							String content = "";
							while((i=sc.read(byteBuffer))>0){
								content += new String(byteBuffer.array());
							}
							byteBuffer.clear();
							if(content.equals("Daisy")){
								
								System.out.println("���������ͻ��˷��͵����ݣ�"+content+",I v o");
							}else{
								System.out.println("���������ͻ��˷��͵����ݣ�"+content+",����");
							}
							
							
							System.out.println("���������������˷��͵����ݡ�����");
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
