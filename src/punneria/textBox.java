package punneria;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class textBox implements KeyListener{
public Boolean allowed = true;
public String box = "";
	public textBox() {
 
	}
	
	public void keyTyped(KeyEvent i){
		
	}
public void keyPressed(KeyEvent i){
	
}
	public void keyReleased(KeyEvent i){
				if(allowed){
					if(i.isActionKey()== false && Character.isLetterOrDigit(i.getKeyChar()) || i.getKeyCode()==KeyEvent.VK_SPACE){
					box = box+i.getKeyChar();
					}else if(i.getKeyCode()==KeyEvent.VK_BACK_SPACE){
						box = box.substring(0, box.length()-1);
					}
	}
}
}