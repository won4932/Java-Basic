import java.applet.Applet;
import java.awt.Graphics;


public class AppletLifeCycle extends Applet {

	
		
		public String state = "";
		
		public void init(){
			state +="init호출 ";
		}
		
		public void start(){
			state +="start호출 ";
		}
		
		public void stop(){
			state +="stop호출 ";
		}
		
		public void	destroy(){
			state +="destroy호출";
		}
		
		public void paint(Graphics g){
			
			g.drawString(state, 10, 50);
		}

	

}
