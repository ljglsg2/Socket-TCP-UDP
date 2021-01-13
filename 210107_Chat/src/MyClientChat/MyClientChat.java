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
		String ServerIP = "192.168.136.1"; //���� IP �ּ�
		int ServerPort = 10000;//���� ��Ʈ
		Socket socket = null;//������ ����� ���� ���۷���
		OutputStream out = null;
		DataOutputStream dos = null;
		InputStream in = null;
		DataInputStream dis = null;
		String sendMsg = null;
		String recvMsg = null;
		
		try {
			System.out.println(clock() + "������ ���� ���Դϴ�");
			Thread.sleep(1000);
			socket = new Socket(ServerIP, ServerPort);
			System.out.println(clock() + socket.getInetAddress() + "ä�ü����� �����Ͽ����ϴ�");
			
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
					System.out.println("����(���) : ��밡 ä���� �����Ͽ����ϴ�");
					break;
				}
				
				System.out.println("����(���) : " + recvMsg);
				
				System.out.println("Ŭ���̾�Ʈ(��) :");
				sendMsg = sc.nextLine();
				dos.writeUTF(sendMsg);				
				if(sendMsg.equals("exit"))break;
				
				
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		System.out.println(clock() + "ä�ü����� �����մϴ�");
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
		Date dtime = new Date();// �ð���ü ����
		String time = new SimpleDateFormat("[HH:mm:ss]").format(dtime);
		return time; //���˵� ���ڿ� ����
	}
}
