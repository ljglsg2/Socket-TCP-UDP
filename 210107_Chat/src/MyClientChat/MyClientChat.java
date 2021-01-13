package MyClientChat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MyClientChat {
	Scanner sc = new Scanner(System.in);
	
	public MyClientChat() {
		String ServerIP = "192.168.136.1"; //서버 IP 주소
		int ServerPort = 10000;//서버 포트
		Socket socket = null;//서버와 연결될 소켓 레퍼런스
		OutputStream out = null;
		DataOutputStream dos = null;
		InputStream in = null;
		DataInputStream dis = null;
		String sendMsg = null;
		String recvMsg = null;
		
		try {
			System.out.println(clock() + "서버에 연결 중입니다");
			Thread.sleep(1000);
			socket = new Socket(ServerIP, ServerPort);
			System.out.println(clock() + socket.getInetAddress() + "채팅서버에 접속하였습니다");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
			
		}catch (UnknownHostException e) {
			e.printStackTrace();
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		while(true)
		{
			try {				
				recvMsg = dis.readUTF();
				if (recvMsg.equals("exit")) 
				{
					System.out.println("서버(상대) : 상대가 채팅을 종료하였습니다");
					break;
				}
				
				System.out.println("서버(상대) : " + recvMsg);
				
				System.out.println("클라이언트(나) :");
				sendMsg = sc.nextLine();
				dos.writeUTF(sendMsg);				
				if(sendMsg.equals("exit"))break;
				
				
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		System.out.println(clock() + "채팅서버를 종료합니다");
		try {
			dis.close();
			dos.close();
			in.close();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	String clock() 
	{
		// TODO Auto-generated method stub
		Date dtime = new Date();// 시간객체 생성
		String time = new SimpleDateFormat("[HH:mm:ss]").format(dtime);
		return time; //포맷된 문자열 리턴
	}
}
