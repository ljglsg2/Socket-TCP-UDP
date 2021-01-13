package MyServerChat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MyServerChat {
	
		Scanner sc = new Scanner(System.in);

		public MyServerChat() 
		{
		
		ServerSocket chatServerSocket = null;
		int listenPort = 10000; //TCP port
		Socket clientSocket = null;
		OutputStream out = null;
		DataOutputStream dos = null;
		InputStream in = null;
		DataInputStream dis = null;
		String sendMsg = null;
		String recvMsg = null;
		try {
			System.out.println(clock() + "ä�� ������ ���� ���Դϴ�....");
			Thread.sleep(1000);
			chatServerSocket = new ServerSocket(listenPort);
			System.out.println(clock() + "ä�� ������ �����Ͽ����ϴ�");
			Thread.sleep(1000);
			System.out.println(clock() + "Ŭ���̾�Ʈ�� ������ ��ٸ��� �ֽ��ϴ�");
			clientSocket = chatServerSocket.accept();
			System.out.println(clock() + clientSocket.getInetAddress() + "Ŭ���ƾ�Ʈ�� �����Ͽ����ϴ�");
			out = clientSocket.getOutputStream();
			dos = new DataOutputStream(out);
			in = clientSocket.getInputStream();
			dis = new DataInputStream(in);
			System.out.println(clock()+"ä���� ���۵Ǿ����ϴ�");
			
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		
		while(true)
		{
			try {
				System.out.println("����(��) : ");
				sendMsg = sc.nextLine();
				dos.writeUTF(sendMsg);
				if (sendMsg.equals("exit"))break;
				recvMsg = dis.readUTF();
				if(recvMsg.equals("exit")) {
					System.out.println("Ŭ���̾�Ʈ(���) : ������ ä���� �����Ͽ����ϴ�");
					break;
					
				}
				System.out.println("Ŭ���̾�Ʈ(���) : " + recvMsg);
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		System.out.println(clock() + "ä�ü����� �����մϴ�");
		try {
			dis.close();
			dos.close();
			chatServerSocket.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		

 }

		String clock() {
			// TODO Auto-generated method stub
			Date dtime = new Date();
			String time = new SimpleDateFormat("[HH:mm:ss]").format(dtime);
			return time;
		}
}
