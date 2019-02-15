package punneria;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.Timer;

public class Fader {
Boolean isFading = false;
Boolean isIncreasing = true;
float opacity = 0.01F;
Timer timer = new Timer(50, e ->{
	if(opacity < 0.01F) {
		isFading = false;
		opacity = 0.01F;
//		this.timer.stop();
		System.out.println("stopped");
	}else if(opacity > 0.99) {
		isIncreasing = false;
		System.out.println("decreasing now");
	}
	if(isFading) {
		if(isIncreasing) {
			opacity += 0.01F;
		}else {
			opacity -= 0.01F;
		}
	}
});
public Fader() {
	timer.start();
}
public void fade() {
	if(!isFading) {
	isFading = true;
	isIncreasing = true;
	opacity = 0.01F;
}
}
void paint(Graphics graphics, int width, int height) {
	if(isFading) {
graphics.setColor(new Color(0F, 0F, 0F, opacity));
graphics.fillRect(0, 0, width, height);
	}
}
boolean isAtHeight() {
	if(opacity > 0.99F) {
		return true;
	}else {
		return false;
	}
}
}
