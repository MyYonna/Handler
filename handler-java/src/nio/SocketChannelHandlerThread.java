package nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class SocketChannelHandlerThread implements Runnable {
	private Selector selector;
	private SocketChannel sc ;

	public SocketChannelHandlerThread(Selector selector,SocketChannel sc ) {
		// TODO Auto-generated constructor stub
		this.selector = selector;
		this.sc = sc;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
		sc.configureBlocking(false);
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		selector.selectedKeys().clear();
		sc.register(selector, SelectionKey.OP_READ,byteBuffer);
		System.out.println("服务器：启动了服务器的监听读的事件，，，");} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
