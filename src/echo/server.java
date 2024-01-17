package echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class server {

	public static void main(String[] args) throws IOException {

		// 서버소켓 생성
		ServerSocket serverSocket = new ServerSocket();

		// 바인딩
		serverSocket.bind(new InetSocketAddress("192.168.0.44", 10001)); // 아이피, 포트번호

		SocketAddress aaa = new InetSocketAddress("192.168.0.44", 10001);
		// 서버시작
		System.out.println("<서버시작>");
		System.out.println("==================================================");
		
		//반복 
		while(true) {
			System.out.println("[연결을 기다리고 있습니다.]");

			// 클라이언트 접속을 하면 accept()가 실행됨
			Socket socket = serverSocket.accept(); // 대기중
		
			//출장 thread.start();
			Thread thread = new ServerThread(socket);
			thread.start();
		
		}
		
		
		//System.out.println("=======================================");
		//System.out.println("=============<서버 종료>=============");
		//System.out.println("=======================================");
		
	}

}
