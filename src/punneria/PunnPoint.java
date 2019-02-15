package punneria;
import com.alibaba.fastjson.JSON;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleText;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.AttributeSet;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
public class PunnPoint extends JPanel implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener, AccessibleText{ 
	PunnSet punnset = new PunnSet();
	float BlackOpacity = 1F;
	Float acceleration = 0F;
	Sound sound = new Sound();
	Fader fader = new Fader();
	Float moonsG = 6F;
	boolean isFadingToSelector = false;
	boolean isFadingFromMission = false;
	boolean isFadingFromSelector = false;
	boolean intro = false;
	boolean day2 = false;
	boolean tunnel2 = false;
	boolean timeZone = false;
	boolean isOnPlatform() {
		if(footy > x/3*5-1 && footy < x/3*5+5 && lavaX+20 < footx && footx < lavaX+screenSize.width/2-100+20 && (tunnels || tunnel2)) {
			return true;
		}else {
			return false;
		}
	}
	boolean isOnMoon() {
		if(moon || slug || selector || tunnels || missionComplete || tunnel2 || timeZone) {
			return true;
		}else {
			return false;
		}
	}
	boolean fade = false;
		
	ImageObserver observer = null;
	static BufferedImage icon = null;
	void creatIcon(){
	try{
		icon = ImageIO.read(new File("moon.png"));		
	}catch(Exception e){
creatIcon();
	}
	}
	{
	creatIcon(); 
	}
	static BufferedImage image2 = null;
void creatEarth(){
	try{
		image2 = ImageIO.read(new File("earth.png"));
	}catch(Exception e){
		creatEarth();
	}
}
{
	creatEarth();
}
static BufferedImage image3 = null;
void creatSun(){
	try{
		image3 = ImageIO.read(new File("sun.png"));
	}catch(Exception e){
		creatSun();
	}
}
{
	creatSun();
}
	void drawStar(Graphics graphics, int xL, int yL){
		graphics.setColor(Color.WHITE);
		graphics.fillOval(xL-2, yL-2, 4, 4);
	}
int missions = 0;
int missions(Graphics graphics, Boolean a){
	if(a){
	if(missions == 1){
		graphics.setColor(Color.BLUE);
		return 1;
	}else if(missions == 2){
		graphics.setColor(Color.GREEN);
		return 2;
	}else if(missions == 3){
		graphics.setColor(Color.RED);
		return 3;
	}else{
		graphics.setColor(Color.WHITE);
		return missions;
	}
	}else{
		return missions;
	}
}
long p$ = 0;
	BooleanSaver saver = new BooleanSaver();
	boolean missionComplete = false;
	Color button = Color.WHITE;
	Color buttonTwo = Color.WHITE;
	public Boolean settings = false;
	public Boolean namer = false;
	public Boolean ALPHA = false;
	void modeSave(){
		saver.setDay(day);
		saver.setNight(night);
		saver.setMoon(moon);
		saver.setSlug(slug);
		saver.setSelector(selector);
		saver.setTunnels(tunnels);
		saver.setMissionComplete(missionComplete);
		saver.setTunnel2(tunnel2);
		saver.setTimeZone(timeZone);
		saver.setDay2(day2);
		saver.setGrass(grass);
		saver.setSky(sky);
		saver.setWords(words);
		namer = false;
	}
	void retreveBooleans(){
		day = saver.getDay();
		night = saver.getNight();
		moon = saver.getMoon();
		slug = saver.getSlug();
		selector = saver.getSelector();
		tunnels = saver.getTunnels();
		missionComplete = saver.getMissionComplete();
		tunnel2 = saver.GetTunnel2();
		timeZone = saver.getTimeZone();
		day2 = saver.getDay2();
		grass = saver.getGrass();
		sky = saver.getSky();
		words = saver.getWords();
		namer = false;
	}
	Color no = Color.WHITE;
	public void setSettings() throws IOException{
			punnset.setTriangle(no);
			punnset.setAcross(size);
			punnset.setName(textbox.box);
			punnset.setP$(p$);
			punnset.setMissions(missions);
		String stats = JSON.toJSONString(punnset);
		Files.write(Paths.get("stats.json"), stats.getBytes());
		String modes = JSON.toJSONString(saver);
		Files.write(Paths.get("progress.json"), modes.getBytes());
	}
public void setStats() {
	try{
	byte[] bytes = Files.readAllBytes(Paths.get("stats.json"));
	String stats = new String(bytes);
	PunnSet deSerialised = JSON.parseObject(stats, PunnSet.class);
	no = deSerialised.getTriangle();
		size = deSerialised.getAcross();
		textbox.box = deSerialised.getName();
		p$ = deSerialised.getP$();
		missions = deSerialised.getMissions();
	}catch(Exception e){
	}
}
public void setProgress(){
	try{
		byte[] bytes = Files.readAllBytes(Paths.get("progress.json"));
		String progress = new String(bytes);
		BooleanSaver deserialised = JSON.parseObject(progress, BooleanSaver.class);
		day = deserialised.getDay();
		night = deserialised.getNight();
		moon = deserialised.getMoon();
		slug = deserialised.getSlug();
		selector = deserialised.getSelector();
		tunnels = deserialised.getTunnels();
		missionComplete = deserialised.getMissionComplete();
		tunnel2 = deserialised.GetTunnel2();
		timeZone = deserialised.getTimeZone();
		day2 = deserialised.getDay2();
		grass = deserialised.getGrass();
		sky = deserialised.getSky();
	}catch(Exception e){
	}
}
 textBox textbox = new textBox();
 Random rand = new Random();
	public boolean fileRead(String path, Boolean mode, Color Sky, Color cGrass, String Words, String delete){
	try{
		List<String> str = Files.readAllLines(Paths.get(path));
		if(str != null){
			day = false;
			grass = cGrass;
			sky = Sky;
			try{
				Files.delete(Paths.get(delete));
			}catch(Exception e){
			}
		words = Words;
			return true;
		}else{
			return false;
		}
	}catch(Exception e){
		return false;
	}
}
	Font font = new Font("", 0, 20);
	@Override
	public AccessibleContext getAccessibleContext(){
		return super.getAccessibleContext();
	}
	boolean menu = false;
	String name = ("");
	static boolean tunnelSave = false;
	static int second = 0;	
	void mouseMove(int X, int Y){
		Robot robot;
		try{
			robot = new Robot();
			robot.mouseMove(X, Y);
		} catch (AWTException e){
			e.printStackTrace();
		}
	}
	Pattern pattern = new Pattern("a c a c a f a d a a a b aw");
	static boolean tunnels = false;
	int size = 100;
	final Color orange = new Color(255, 100, 0);
	public boolean start = true;
	static boolean day = false;
	static boolean selector = false;
	static boolean night = false;
	boolean moon = false;
	public static String words   = new String("press s to start");
	Player player = new Player();
	int acl = 0;
	boolean slug = false;
	int sun = 20;
	void mouseHide(){
		Robot robot;
		try{
			robot = new Robot();
			robot.mouseMove(-100000, -1000000);
		} catch (AWTException e){
			e.printStackTrace();
		}
	}
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	void mouseShow(){
		Robot robot;
		try{
			robot = new Robot();
			robot.mouseMove(screenSize.width/2,screenSize.height/2);
		} catch (AWTException e){
			e.printStackTrace();
		}
	}

	public Dimension getPreferredSize() {
		return new Dimension(screenSize.width, screenSize.height);
	}
	Player play = new Player();
	Polygon poly = new Polygon(new int[]{screenSize.width/2,screenSize.width/2-10,screenSize.width/2+10}, new int[]{screenSize.height/2-10, screenSize.height/2-20, screenSize.height/2+10}, 3);
	int footx = screenSize.width/2;
	int x = screenSize.height/3;
	Float footy = (float) (screenSize.height-x);
	int lavaX = screenSize.width/2+size;
	int y = 20;
	static Color sky = new Color(0, 0, 255);
	static Color grass = new Color(0, 100, 0);
	{
		javax.swing.Timer timer = new Timer(10, e -> {
			this.repaint();
			textbox.allowed = namer;
			if(fader.isAtHeight()) {
			if(isFadingToSelector) {
				isFadingToSelector = false;
				selector = true;
				sky = Color.WHITE;
				words = "";
				grass = Color.WHITE;
				slug = false;
			}else if(isFadingFromSelector) {
				isFadingFromSelector = false;
				tunnels = true;
				selector = false;
				words = "find the green artifact but beware, orange ground is lava and you will die if you touch it";
			}else if(isFadingFromMission) {
				isFadingFromMission = false;
				missionComplete = true; tunnel2 = false; tunnels = false; x = screenSize.height/3; lavaX = screenSize.width/2+size; missions += 1; p$ += 1000000000;
			}
			}
			if(isOnMoon()) {
				moonsG = 1F;
			}else {
				moonsG = 6F;
			}
			if(start){
				settings = false;
			}
			if(!ALPHA){
				buttonTwo = Color.WHITE;
			}else{
				buttonTwo = new Color(127, 127, 127);
			}
			if(namer){
				button = new Color(125, 125, 125);
			}else{
				button = Color.WHITE;
			}
			if(slug){
				grass = new Color(150, 150, 150);
			}
			if(lavaX < footx+1 && screenSize.height-x == footy && footx < lavaX+screenSize.width/2-100){
				System.exit(0);
			}
			if(textbox.box == "call" && moon == true){
				acl = 20;
				textbox.box = "";
			}
			if(acl == 20){
				sky = new Color(200, 100, 0);
				night = false;
				acl = 1000;
			}
			if(night){
				day = false;
			}
			if(y > screenSize.height/3){
				x = screenSize.height/3;
				moon = true;
				grass = new Color(50, 50, 50);
				night = false;
			}
			if(moon){
				words = "type call to contact";
			}
			if(slug){
				words = "press the completely normal purple ball to reach the completely open secret underground caves.";
			}
		});
		timer.start();
	}
	Timer times = new Timer(100, e ->{
		if(night){
			words = "go up to progress, (use the arrow keys to move)";
		}
		if(day){
		sky = new Color(0, 0, Math.max(sky.getBlue()-3, 0));
		}else{
			sky = new Color(Math.max(sky.getRed(), 5), Math.max(sky.getGreen(), 5), Math.max(sky.getBlue(), 5));
		}
		if(sky.getBlue()<6 && day){
			night = true;
		}
	}); 
	Timer time = new Timer(33, e ->{
		if(day) {
		sun = sun+1;
		}
		if(slug){
			sky = new Color(sky.getRed()-2, sky.getGreen()-1, 0);
		}
		if(sun > screenSize.height-x-200){
			times.start();
		} 
	});
	@Override
	public synchronized void addMouseMotionListener(MouseMotionListener l) {
		super.addMouseMotionListener(l);
	}
	public static void main(String[] args){
		JFrame punn = new JFrame("punneria");
		punn.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		PunnPoint punnpoint;
		try {
			punnpoint = new PunnPoint();
			punn.getAccessibleContext();
			punn.getContentPane().add(punnpoint);
			FlowLayout layout = new FlowLayout();
			punn.setLayout(layout);
			JLabel label = new JLabel("test label 1");
			label.getAccessibleContext().setAccessibleName("quests: " + words);
			label.setFocusable(true);
			punn.add(label);
			JLabel button = new JLabel("test label 2");
			button.getAccessibleContext().setAccessibleName("activity time: " + second + "seconds");
			button.setFocusable(true);
			punn.add(button);			
			punn.pack();
			punn.setVisible(true);
			punn.setIconImage(icon);
		} catch (IOException e){
			e.printStackTrace();
			System.out.println("the game has crashed, please try again");
			System.exit(-1);
		}
	}
	public PunnPoint() throws IOException {
		
		addKeyListener(this);
		addKeyListener(textbox);
		addMouseListener	(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		
		setFocusable(true);
		requestFocusInWindow();
	}


	@Override
	public void mouseDragged(MouseEvent t){
		System.out.println("testing");
	}
	@Override
	public void mouseMoved(MouseEvent t){
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent t){

	}
	@Override
	public synchronized void addMouseWheelListener(MouseWheelListener l) {
		super.addMouseWheelListener(l);
	}	
@Override
	public void keyTyped(KeyEvent t) {

	}
void jump(double g) {
			acceleration = 1.5F;
}
	void lavaLeft(int num, int speed){
		lavaX = lavaX+speed;
		if(lavaX < num){
			lavaX = num;
		}
	}
	void lavaRight(int num, int speed){
		lavaX = lavaX+speed;
		if(lavaX > num){
			lavaX = num;
		}
	}
	@Override
	public void keyPressed(KeyEvent t) {
		if(selector){
			if(t.getKeyCode()==KeyEvent.VK_MINUS){
				size = size-1;
			}
			if(size < 0){
				size = 0;
			}
			if(size > 200){
				size = 200;
			}
			if(t.getKeyCode()==KeyEvent.VK_EQUALS){
				size = size+1;
			}
		}
		if(t.getKeyCode () == KeyEvent.VK_UP){
			if(night){
				x = x-1;
				y = y+1;
			}
		}
		if(tunnels || tunnel2){
			if(t.getKeyCode()==KeyEvent.VK_RIGHT){
				lavaLeft(0, -3);
			}
			if(t.getKeyCode()==KeyEvent.VK_LEFT){
				lavaRight(screenSize.width/2+100, 3);
			}
		}
		if(ALPHA && settings){
			if(t.getKeyCode()==KeyEvent.VK_EQUALS){
				no = new Color(no.getRed(), no.getGreen(), no.getBlue(), no.getAlpha()+1);
			}else if(t.getKeyCode()==KeyEvent.VK_MINUS){
				no = new Color(no.getRed(), no.getGreen(), no.getBlue(), no.getAlpha()-1);
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent t) {
			if(t.getKeyCode()==KeyEvent.VK_SPACE && (isOnPlatform() || footy == screenSize.height-x)) {
				jump(2D);
			}
		if(t.getKeyCode()==KeyEvent.VK_ESCAPE){
			if (settings){
				settings = false;
				retreveBooleans();
			} else {
				modeSave();
				settings = true;
				namer = false;
				day = false;
				night = false;
				moon = false;
				slug = false;
				selector = false;
				tunnels = false;
				missionComplete = false;
				tunnel2 = false;
				timeZone = false;
				day2 = false;
			}
		}
		if(settings){
			if(t.getKeyCode()==KeyEvent.VK_Q){
				System.out.println("the game successfully closed");
				System.exit(0);
			}
			if(t.getKeyCode()==KeyEvent.VK_N){
				namer = !namer;	
			}
			if(t.getKeyCode()==KeyEvent.VK_S){
				try {
					setSettings();
				} catch (Exception e) {
				}
			}
			if(t.getKeyCode()==KeyEvent.VK_O){
				ALPHA = !ALPHA;
			}
		}
		if(t.getKeyCode ()==KeyEvent.VK_S && day){
			time.start();
		}
		if(moon){
			if(t.getKeyCode ()==KeyEvent.VK_C){
				acl = acl+5;
			}
			if(t.getKeyCode ()==KeyEvent.VK_A){
				acl = acl+5;
			}
			if(t.getKeyCode()==KeyEvent.VK_L){
				acl = acl+5;
			}
		}
		if(acl == 20){
			slug = true;
			moon = false;
		}
		if(selector){
			if(t.getKeyCode()==KeyEvent.VK_P){
				size = rand.nextInt(200);
				}
			if(t.getKeyCode()==KeyEvent.VK_C){
				no = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255), no.getAlpha());
			}
			if(t.getKeyCode()==KeyEvent.VK_G){
				no = Color.GREEN;
			}
			if(t.getKeyCode()==KeyEvent.VK_B){
				no = Color.BLUE;
			}
			if(t.getKeyCode()==KeyEvent.VK_R){
				no = Color.RED;
			}
		}
	}
	@Override
	public String getAfterIndex(int part, int index) {
		return null;
	}
	@Override
	public String getAtIndex(int part, int index) {
		return null;
	}
	@Override
	public String getBeforeIndex(int part, int index) {
		return null;
	}
	@Override
	public int getCaretPosition() {
		return 0;
	}
	@Override
	public AttributeSet getCharacterAttribute(int i) {
		return null;
	}
	@Override
	public Rectangle getCharacterBounds(int i) {
		return null;
	}
	@Override
	public int getCharCount() {
		return 0;
	}
	@Override
	public int getIndexAtPoint(Point p) {
		return 0;
	}
	@Override
	public String getSelectedText() {
		return null;
	}
	@Override
	public int getSelectionEnd() {
		return 0;
	}
	@Override
	public int getSelectionStart() {
		return 0;
	}
	@Override
	protected void paintComponent(Graphics graphics){
		super.paintComponent(graphics);
		graphics.setColor(sky);	
		graphics.fillRect(0,  0,  screenSize.width, screenSize.height);
		if(namer){
			graphics.setColor(Color.WHITE);
			graphics.drawRect(footx-size-25, screenSize.height/2-65, size*2+50, 30);
		}
		if(start){
			sky = orange;
			grass = orange;
			graphics.setColor(Color.BLACK);
			graphics.drawString("reset", screenSize.width/2-8, 95);
			graphics.drawRoundRect(screenSize.width/2-45, 85, 100, 25, 50, 50);
			graphics.drawString("load save", screenSize.width/2-13, 70);
			graphics.drawRoundRect(screenSize.width/2-45, 60, 100, 25, 50, 50);
		}
		if(textbox.box.length() == 53 || textbox.box.length() > 53){
			textbox.box =  textbox.box.substring(0, textbox.box.length()-1);
		}
		if(moon){
			graphics.setColor(Color.WHITE);
			graphics.drawString("Call", 15, 25);
			graphics.drawRect(0, 0, 50, 50);
			night = false;
		}
		if(timeZone) {
			graphics.setColor(Color.WHITE);
			graphics.drawRect(screenSize.width-50, 0, 50, 50);
			graphics.drawString("next", screenSize.width-45, 25);
			graphics.setColor(Color.BLACK);
			graphics.drawString("hint: press escape to enter settings and you can save your progress from any point in the game", screenSize.width/2-100, 300);
		}
		if(intro) {
			graphics.setColor(Color.WHITE);
			graphics.drawRect(screenSize.width/2-100, 0, 200, 50);
			graphics.drawString("continue", screenSize.width/2-10, 25);
		}
		graphics.setColor(new Color(240, 200, 20));
		if(day || day2){
			graphics.drawImage(image3, screenSize.width/2-63, sun, 126, 126, observer);
			if(day) {
			graphics.setColor(Color.BLACK);
			graphics.drawString("the triangle is you", screenSize.width-200, 100);
			graphics.drawString("press escape to enter settings", screenSize.width-300, 300);
			}
		}
		if(tunnels || tunnel2) {
			graphics.setColor(new Color(127, 127, 127));
			graphics.fillRect(lavaX+20, x/3*5, screenSize.width/2-100, 5);
		}
		graphics.setColor(no);
		graphics.fillPolygon(new int[]{footx, screenSize.width/2+size, screenSize.width/2-size}, new int[]{footy.intValue(), (int) (footy-100), (int) (footy-100)}, 3);
		if(settings){
			sky = new Color(10, 10, 10);
			grass = new Color(10, 10, 10);
		}
		graphics.drawString("" + textbox.box + "", screenSize.width/2-100, screenSize.height/2-50);
		graphics.setColor(grass);
		graphics.fill3DRect(0, screenSize.height-x, screenSize.width, screenSize.height, true);
		if(night && !settings){
			graphics.drawImage(icon, screenSize.width/2-63, y, 126, 126, observer);
			drawStar(graphics, screenSize.width-100, 50);
			drawStar(graphics, screenSize.width/2+100, 50);
		}
		if(moon){
			graphics.drawImage(image2, screenSize.width/2-50, 20, 100, 100, observer);
			y = 20;
		}
		if(timeZone) {
			graphics.setColor(Color.BLUE);
		}
		graphics.drawString("Quest: "+ words +"", 100, 100);
		if(slug){
			graphics.setColor(new Color(200, 0, 200));
			graphics.fillOval(0, 0, 50, 50);
			graphics.drawString("hello, my name is Punnum, I will show you to the secret underground caves. Note: Visiters are always welcome!", 0, 80);
			graphics.setColor(Color.BLACK);
			graphics.fillRect(screenSize.width/2-100, screenSize.height-x, 200, screenSize.height);
		}
		if(selector){
			graphics.setColor(Color.BLACK);
			graphics.drawString("press R to select Red, B to select Blue, G to select Green, equals to increase size, minus to shrink, p to randomize size, c to randomize color", 100, 100);
			graphics.drawRect(screenSize.width-50, 0, 50, 50);
			graphics.drawString("next", screenSize.width-45, 25);
		}
		if(tunnels || tunnel2){
			graphics.setColor(new Color(150, 150, 150));
			sky = Color.BLACK;
			grass = new Color(150, 150, 150);
			graphics.setColor(orange);
			graphics.fillRect(lavaX, screenSize.height-x, screenSize.width/2-100, 50);
			if(tunnels) {
			graphics.setColor(new Color(0, 255, 0));
			}else {
				graphics.setColor(new Color(200, 0, 200));
			}
			graphics.fillRect(lavaX+900, screenSize.height-x-200, 100, 100);
		}
		if(lavaX < 1){
			if(tunnels) {
				fader.fade();
				isFadingFromMission = true;
			}else {
				fader.fade();
				isFadingFromMission = true;
			}
		}
		if(missionComplete){
			graphics.setColor(new Color(200, 0, 200));
				graphics.fillOval(0, 0, 50, 50);
				graphics.drawString("Well done, you completed my mission, press continue to progress.", 100, 20);
				missions(graphics, true);
				graphics.fillRect(screenSize.width/2-50, 50, 100, 100);
				graphics.drawString("continue", screenSize.width/2-10, 200);
				graphics.setColor(Color.BLACK);
				graphics.drawString("mission: " + missions(graphics, false) + "/3", screenSize.width/2-45, 100);
				graphics.setColor(Color.WHITE);
				graphics.drawString("p$" + p$, screenSize.width-200, 25);
				if(missions == 1) {
			words = "press the box to continue";
				}else if(missions == 2) {
					words = "Press the box to enter the time portal";
				}
		}
		if(tunnel2) {
			words = "find the purple time portal...";
		}
		if(settings){
			graphics.setColor(Color.WHITE);
			graphics.drawRect(0, 0, 50, 50);
			graphics.drawString("save", 0, 25);
			graphics.drawRect(screenSize.width-50, screenSize.height-125, 50, 50);
			graphics.drawString("quit", screenSize.width-50, screenSize.height-100);
			graphics.setColor(button);
			graphics.drawRect(0, screenSize.height-125, 50, 50);
			graphics.drawString("name", 0, screenSize.height-100);
			graphics.setColor(buttonTwo);
			graphics.drawRect(screenSize.width-50, 0, 50, 50);
			graphics.drawString("opacity", screenSize.width-50, 25);
			graphics.setColor(Color.WHITE);
			if(ALPHA){
				graphics.drawString("use = to increase opacity and minus to decrease", 100, 50);
			}
		}
		graphics.setColor(Color.RED);
		graphics.drawString("Activity time: " + second + "seconds", 100, 150);
		fader.paint(graphics, screenSize.width, screenSize.height);
	}
	@Override
	public synchronized void addMouseListener(MouseListener l) {
		super.addMouseListener(l);
	}
	@Override
	public void mouseClicked(MouseEvent t){

	}
	@Override
	public void mouseReleased(MouseEvent t){
		if(t.getX() < 50){
			if(t.getY() < 50){
				if(slug){
fade = true;
fader.fade();
					isFadingToSelector = true;
				}
			}
		}
		if(selector){
			if(t.getX()>screenSize.width-50){
				if(t.getY()<50){
					isFadingFromSelector = true;
					fader.fade();
				}
			}
		}
		if(t.getX() < 50 && t.getY() < 50 && settings == true){
			try {
				setSettings();
			} catch (Exception e) {
			}
		} 
		if(t.getX() > screenSize.width/2-45 && t.getX() < screenSize.width/2-45+100 && start && t.getY() > 85 && t.getY() < 110){
			start = false;
			intro = true;
			sky = Color.BLACK;
			grass = Color.BLACK;
			sound.setClipFile(new File("storyLine1.aif"));
			sound.play();
		}
		if(t.getY() < 85 && t.getY() > 60 && start && t.getX() < screenSize.width/2-45+100 && t.getX() > screenSize.width/2-45){
			setProgress();
			setStats();
			start = false;
		}
		if(t.getX() < 50 && t.getY() > screenSize.height-125 && settings){
			namer = !namer;
		}
		if(t.getY() > screenSize.height-125 && t.getX() > screenSize.width-50 && settings == true){
			System.out.println("the game successfully closed");
			System.exit(0);
		}
		if(t.getX() > screenSize.width-50 && t.getY() < 50 && settings){
			ALPHA = !ALPHA;
		}
	if(missionComplete && t.getY() > 50 && t.getY() < 150 && t.getX() > screenSize.width/2-50 && t.getX() < screenSize.width+50) {
	if(missions == 1) {
		missionComplete = false;
		tunnel2 = true;
		sky = Color.BLACK;
	}else if(missions == 2) {
		sun = 20;
		missionComplete = false;
		timeZone = true;
		words = "press next to travel to the earth, before it exploded... It exploded because it was so frustrated that you flew away from it.";
		sky = new Color(200, 0, 200);
		grass = new Color(200, 0, 200);
	}
	}
	if(timeZone && t.getY() < 50 && t.getX() > screenSize.width-50) {
		day2 = true;
		timeZone = false;
		sky = Color.BLUE;
		grass = new Color(0, 100, 0);
	}
	if(t.getX() > screenSize.width/2-100 && t.getX() < screenSize.width/2+100 && intro && t.getY() < 50) {
		if(!namer) {
			namer = true;
			sound.setClipFile(new File("namer.aif"));
			sound.play();
		}else {
			day = true;
			intro = false;
			namer = false;
			sky = Color.BLUE;
			grass = new Color(0, 100, 0);
		}
	}
	}
	@Override
	public void mouseEntered(MouseEvent t) {

	}
	@Override
	public void mouseExited(MouseEvent t) {

	}
	@Override
	public void mousePressed(MouseEvent t) {

	}
	{		
			Timer count = new Timer(1000, e ->{
				second = second+1;
			});
			count.start();

		new Thread(()->{
			while(true){
				if(intro) {
					pattern = new Pattern("");
				}
				if(timeZone) {
					pattern.setInstrument("PIANO");
					pattern = new Pattern("cw, ch, ch, cw, cw");
				}
				if(night){
					pattern = new Pattern("f f f, dw, fw");
					pattern.setInstrument("PIANO");
				}
				if(start){
					pattern.setInstrument("STEEL_DRUMS");
				}
				if(day || day2){
					pattern.setInstrument("PIANO");
					pattern = new Pattern("a c a c a f a d a a a b aw");
				}
				if(slug){
					pattern = new Pattern("awawaw d d d d d d d dq dh dw dw");
					pattern.setInstrument("ACOUSTIC_BASS");
				}
				if(selector){
					pattern = new Pattern("awawaw d d d d d d d dq dh dw dw");
					pattern.setInstrument("ACOUSTIC_BASS");
				}
				if(moon){
					pattern = new Pattern("awawaw d d d d d d d dq dh dw dw");
					pattern.setInstrument("ACOUSTIC_BASS");
				}
				if(tunnels || tunnel2){
					pattern = new Pattern("c d cw a ah");
					pattern.setInstrument("ACOUSTIC_BASS");
				}
				if(missionComplete){
					pattern = new Pattern("e a d g b Ew");
					pattern.setInstrument("ACOUSTIC_BASS");
				}
				player.play(pattern);
			}	
	}).start();
		Timer fall = new Timer(10, e ->{
			int groundY = screenSize.height-x;
			footy -= acceleration;
			if(isOnPlatform()) {
			groundY = x/3*5;
			}
			if(footy > screenSize.height-x) {
				groundY = screenSize.height-x;
			}
			if(night) {
				groundY = screenSize.height-(screenSize.height/3);
			}
				if(footy > groundY) {
					footy = (float) groundY;
				}else {
					acceleration -= moonsG/100F;
				}
	});
		{
			fall.start();
	}
	}
}