package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.CharBuffer;
import java.awt.FlowLayout;

public class Main extends JFrame implements Runnable{
	private Thread thread;
	private JPanel contentPane;
	private JLabel label;

	/**
	 * PC ManageMent Program 2019.06.09
	 * 6 Buttion(Function), Chat, SoundBot
	 * File, Memory, Scheduler
	 * 19.10.25
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// jtattio라이브러리 참조 GUI변경 Smart - 버튼 생동감, Noire - 색감(검정)
					 UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
					 UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
//					 UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
						Main frame = new Main();
						// 사이즈 불러와서 해당 모니터의 중앙의 오게 위치
						Dimension frameSize = frame.getSize();
						Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
						frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
						frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setTitle("WPcManager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel northMenu = new JPanel();
		FlowLayout flowLayout = (FlowLayout) northMenu.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(northMenu, BorderLayout.NORTH);
		
		 label = new JLabel();
        
        if(thread == null){
            
            //this의 의미는 Runnable이 구현된 객체를 뜻함(DigitalClock)
            thread = new Thread(this);
            thread.start();
        }
        northMenu.add(label);
		
		JPanel bottomMenu = new JPanel();
		bottomMenu.setBorder(new EmptyBorder(10, 10, 0, 10));
		contentPane.add(bottomMenu, BorderLayout.SOUTH);
		bottomMenu.setLayout(new GridLayout(0, 2, 5, 5));
		
		//Button
		JButton chatBtn = new JButton("Chat Bot");
		bottomMenu.add(chatBtn);
		
		JButton soundBtn = new JButton("Sound Bot");
		bottomMenu.add(soundBtn);
		
		JPanel centerMenu = new JPanel();
		centerMenu.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.add(centerMenu, BorderLayout.CENTER);
		centerMenu.setLayout(new GridLayout(0, 2, 10, 10));
		
		//파일매니저 버튼  FileView Class
		JButton fileBtn = new JButton("File Management");
		centerMenu.add(fileBtn);
		fileBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FileView fileView = new FileView();
				fileView.jFileChooserUtil();
				
			}
		});
		
		JButton memoryBtn = new JButton("Memory Usage");
		centerMenu.add(memoryBtn);
		memoryBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MemoryView memoryView = new MemoryView();
				memoryView.setVisible(true);
				
			}
		});
		
		
		JButton schedulerBtn = new JButton("Scheduler");
		centerMenu.add(schedulerBtn);
		schedulerBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CalendarView calendarView = new CalendarView();
		
			}
		});
		
		JButton cleanBtn = new JButton("Clean Up");
		centerMenu.add(cleanBtn);
		
		JButton setBtn = new JButton("PC Information");
		centerMenu.add(setBtn);  
		
		JButton readBtn = new JButton("Read Me");
		centerMenu.add(readBtn);
		readBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().edit(new File("ReadMe.txt"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	 public void run(){
	        while(true){
	            Calendar cal = Calendar.getInstance();
	            String now = cal.get(Calendar.YEAR)+"년"+
	                    (cal.get(Calendar.MONTH)+1)+"월"+
	                    cal.get(Calendar.DATE)+"일"+
	                    cal.get(Calendar.HOUR)+"시"+
	                    cal.get(Calendar.MINUTE)+"분"+
	                    cal.get(Calendar.SECOND)+"초";
	            label.setText(now);
	            try{
	                Thread.sleep(1000);
	            }catch(InterruptedException e){
	                e.printStackTrace();
	            }
	        }
	    }

}
