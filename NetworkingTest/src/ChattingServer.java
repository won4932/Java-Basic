import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class ChattingServer implements Runnable {
	Socket sk;
	public ChattingServer(){
		System.out.println("Server starting~~~~");
		try {
			ServerSocket ssk = new ServerSocket(6060);
			//Ŭ���̾�Ʈ�� ����ϴ� ����
			sk = ssk.accept();
			System.out.println("Ŭ���̾�Ʈ�� ����Ǿ����ϴ�...!!!");
			
			//Ŭ���̾�Ʈ���� ����Ÿ�� ���������� ��Ʈ�� ����
			InputStreamReader inR = new InputStreamReader(System.in);
			BufferedReader buR = new BufferedReader(inR);
			
			//Ŭ���̾�Ʈ���� �޼����� ���������� ��Ʈ�� ����
			PrintWriter pout = new PrintWriter(sk.getOutputStream(),true);
			
			Thread thr = new Thread(this);
			thr.start();
			
			String msg = "";
			while((msg = buR.readLine()) !=null){
				pout.println(msg);
			}			
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}//������

	public void run(){
		//Ŭ���̾�Ʈ�� �޼����� �޾Ƽ� �ֿܼ� ����ϴ� ���
		InputStream ins;
		try {
			ins = sk.getInputStream();
			InputStreamReader ir = new InputStreamReader(ins);
			BufferedReader buR = new BufferedReader(ir);
			
			String clientMsg = "";
			while(true){
				clientMsg = buR.readLine();
				System.out.println("Client > "+clientMsg);
			}//while
					
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new ChattingServer();

	}

}
