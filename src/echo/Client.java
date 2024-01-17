package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

	public static void main(String[] args) throws IOException {
		
		Socket socket = new Socket();
		
		System.out.println("<클라이언트 시작>");
		System.out.println("==================================================");
		System.out.println("[서버에 연결을 요청합니다.]");
		socket.connect(new InetSocketAddress("192.168.0.79", 10001));
		
		System.out.println("[서버에 연결 되었습니다.]");
		
		//메세지 보내기: write
		//OutputStream out = new FileOutputStream("C:\\javaStudy\\song.txt");
		
		OutputStream os = socket.getOutputStream(); // 소켓<---내놔
		OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);
		
		//메세지 받기용 스트림
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		
		//스캐너 준비: 키보드를 소켓으로 보내도록 바꾸기
		//Scanner sc = new Scanner(System.in);
		InputStream sc = System.in;
		InputStreamReader scIsr = new InputStreamReader(sc, "UTF-8");
		BufferedReader scBr = new BufferedReader(scIsr);
		
		
		while(true) {
			//키보드 입력
			//String str = sc.nextLine();
			String str = scBr.readLine();
			
			
			if("/q".equals(str)) {
				break;
			}
			
			//메세지 보내기
			bw.write(str);
			bw.newLine();
			bw.flush();
			//Buffered는 일정한 양을 모아서 보냄
					//일정한 양이 안 차도 보내도록 설정 필요
			
			//메세지 받기
			String remsg = br.readLine();
			System.out.println("받은 메세지: [" + remsg +"]");
		}
		
		System.out.println("=======================================");
		System.out.println("=========<클라이언트 종료>=========");
		System.out.println("=======================================");
		
		///////////////////////////
		//println 만들기
		OutputStream pos = System.out;
		OutputStreamWriter posw = new OutputStreamWriter(pos, "UTF-8");
		BufferedWriter pbw = new BufferedWriter(posw);
		
		pbw.write("println 테스트");
		pbw.newLine();
		pbw.flush();
		
		
		//닫기
		socket.close();
		br.close();
		bw.close();
		sc.close();
	}

}
