import java.applet.Applet;
import java.awt.Graphics;


public class AppletLifeCycle extends Applet {

	
		
		public String state = "";
		
		public void init(){
			state +="initȣ�� ";
		}
		
		public void start(){
			state +="startȣ�� ";
		}
		
		public void stop(){
			state +="stopȣ�� ";
		}
		
		public void	destroy(){
			state +="destroyȣ��";
		}
		
		public void paint(Graphics g){
			
			g.drawString(state, 10, 50);
		}

	

}
