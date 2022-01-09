package view;

import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Calendar;
 
import javax.swing.JFrame;
import javax.swing.JLabel;
 
public class Test2 extends JFrame implements Runnable{
    private Thread thread;
    private JLabel label;
 
    public Test2(){
        super("������ �ð�");
 
        setLayout(new FlowLayout());
 
        label = new JLabel();
        label.setFont(new Font("Serif",Font.PLAIN, 20));
 
        if(thread == null){
             
            //this�� �ǹ̴� Runnable�� ������ ��ü�� ����(DigitalClock)
            thread = new Thread(this);
            thread.start();
        }
        add(label);
        setBounds(100,100,400,100);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        new Test2();
    }
    public void run(){
        while(true){
            Calendar cal = Calendar.getInstance();
            String now = cal.get(Calendar.YEAR)+"��"+
                    (cal.get(Calendar.MONTH)+1)+"��"+
                    cal.get(Calendar.DATE)+"��"+
                    cal.get(Calendar.HOUR)+"��"+
                    cal.get(Calendar.MINUTE)+"��"+
                    cal.get(Calendar.SECOND)+"��";
            label.setText(now);
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}