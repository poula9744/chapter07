package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerThread extends Thread {

	// 필드
	private Socket socket;

	// 생성자
	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	// 메소드

	public void run() {
			
			try {
				System.out.println("[클라이언트가 연결되었습니다.]");

				// in 메세지 받기용 스트림
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is, "UTF-8");
				BufferedReader br = new BufferedReader(isr);

				// out 메세지 보내기용 스트림
				OutputStream os = socket.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
				BufferedWriter bw = new BufferedWriter(osw);

				// 스캐너 준비
				Scanner sc = new Scanner(System.in);

				while (true) {
					// 읽기
					String message = br.readLine();
					String str = sc.nextLine();
						
						if (message == null) {
							break;
						}
	
					System.out.println("받은 메세지: " + message);
	
	
					// 메세지 보내기
					bw.write(str);
					bw.newLine();
					bw.flush();
				} 
				
				sc.close();
			}catch (IOException e) {
				System.out.println(e.toString());
			}
		
				

		}

	}

