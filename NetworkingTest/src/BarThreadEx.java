import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class BarThreadEx extends JFrame{
	
	MyBar bar = new MyBar(100);
	public BarThreadEx(String title){
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container cp = getContentPane();
		cp.setLayout(null);
		
		bar.setBackground(Color.YELLOW);
		bar.setOpaque(true);
		bar.setLocation(20,50);
		bar.setSize(300,20);
		cp.add(bar);
		
		cp.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke){
				bar.fill();
			}
		});
		
		setSize(350,200);
		setVisible(true);
		
		cp.requestFocus();
		EraserThread th = new  EraserThread(bar);
		th.start();
		
	}
	
	class EraserThread extends Thread{
		MyBar bar;
		public EraserThread(MyBar bar){
			this.bar = bar;
		}
		
		public void run(){
			while(true){
				try {
					sleep(200);
					bar.erase();
				} catch (InterruptedException e) {
					return;
				}				
			}
		}
	}
	
	public static void main(String[] args) {
		new BarThreadEx("아무 키보드나 빨리 눌러 주세요!!!");

	}

}

class MyBar extends JLabel{
	int barSize =0;
	int maxBarSize;
	
	public MyBar(int maxBarSize){
		this.maxBarSize = maxBarSize;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.RED);		
		int width = (int)(((double)(this.getWidth()))/maxBarSize*barSize);
			if(width ==0){
				return;
			}
			
		g.fillRect(0, 0, width, this.getHeight());
	}	
	
	synchronized public void fill(){
		if(barSize == maxBarSize){
			try {
				wait(); // 이벤트 스레드를 대기시킨다.
			} catch (InterruptedException e) {
				return;
			}
		}
		barSize++;
		repaint();
		notify();//eraser스레드를 깨운다.		
	}
	
	synchronized public void erase(){
		if(barSize == 0){
			try {
				wait(); // eraser스레드를 대기시킨다.
			} catch (InterruptedException e) {
				return;
			}
		}
		
		barSize --;
		repaint();
		notify();// 이벤트 스레드를 깨운다.
	}
	
}
