package earthDefence.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import earthDefence.frameWorks.GameObject;
import earthDefence.frameWorks.KeyInput;
import earthDefence.frameWorks.MouseInput;
import earthDefence.frameWorks.ObjectId;
import earthDefence.frameWorks.Texture;
import earthDefence.objects.BadPlayerBullet;
import earthDefence.objects.BossEnemyShip;
import earthDefence.objects.Meteor;
import earthDefence.objects.MineEnemyShip;
import earthDefence.objects.NormalEnemyShip;
import earthDefence.objects.PlayerShip;
import earthDefence.objects.PlayerShip.CURRENTLEVEL;
import earthDefence.objects.RandomMeteorsFour;
import earthDefence.objects.RandomMeteorsOne;
import earthDefence.objects.RandomMeteorsThree;
import earthDefence.objects.RandomMeteorsTwo;
import earthDefence.objects.SuicideEnemyShip;


public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = -386380880785606509L;
	public static int WIDTH;
	public static int HEIGHT; 
	private boolean running = false;
	private Thread thread;
	private Handler handler;
	static Texture tex;
	private BufferedImage backgroundGame = null;
	private BufferedImage backgroundGameFinnalBattle = null;
	private BufferedImage backgroundMenu = null;
	private BufferedImage backgroundHelp = null;
	private BufferedImage backgroundScoreboard = null;
	private BufferedImage backgroundVictory = null;
	private BufferedImageLoader loader = new BufferedImageLoader();
	private int normalEnemyShipSpawnTimer = 0;
	private int suicideEnemyShipSpawnTimer = 0;
	private int mineEnemyShipSpawnTimer = 0;
	private Random random = new Random();
	public static Clip MusicClip;
	public static Clip SoundClip;
	public static boolean toGame = false;
	public static boolean doRestart = false;
	PlayerShip playerShip;
	private File normalEnemyExplosionSound = new File("res/NormalEnemyExplosionSound.wav");
	File suicideShipSpawn = new File("res/SuicideEnemyShipSpawnSound.wav");
	public static int defenceLevel = 1;
	public static int weaponLevel = 1;
	public static int speedLevel = 1;
	public int normalEnemyShipHealth = 150;
	private int timeNeededToSpawnEnemy = 35;
	public static Random trueRandom = new Random();
	
	public static enum GAMESTATE{
		
		GAME,
		PAUSED,
		MENU,
		HELP,
		LEVELUPSTATE,
		SCOREBOARD,
		VICTORY,
	}

	public static GAMESTATE state = GAMESTATE.MENU;
	
	public void init() {
		
		handler = new Handler();
		WIDTH = getWidth();
		HEIGHT = getHeight();
		tex = new Texture();
		BufferedImageLoader loader = new BufferedImageLoader();
		backgroundGame = loader.loadImage("/backgroundGame.png");
		backgroundGameFinnalBattle = loader.loadImage("/backgroundGameFinnalBattle.png");
		backgroundMenu = loader.loadImage("/backgroundMenu.png");
		backgroundHelp = loader.loadImage("/backgroundHelp.png");
		backgroundScoreboard = loader.loadImage("/backgroundScoreboard.png");
		backgroundVictory = loader.loadImage("/backgroundVictory.png");
		

		this.addKeyListener(new KeyInput(handler));	
		this.addMouseListener(new MouseInput());
		handler.addObject(new BossEnemyShip(700 , - 140, handler, ObjectId.BossEnemyShip));
		handler.addObject(new Meteor(370, + 140, handler, ObjectId.Meteor));
		handler.addObject(new RandomMeteorsOne(350, + 140, handler, ObjectId.RandomMeteorsOne));
		handler.addObject(new RandomMeteorsTwo(370, + 120, handler, ObjectId.RandomMeteorsTwo));
		handler.addObject(new RandomMeteorsThree(400, + 115, handler, ObjectId.RandomMeteorsThree));
		handler.addObject(new RandomMeteorsFour(430, + 105,handler , ObjectId.RandomMeteorsFour));
	

		//handler.addObject(new PlayerShip(Game.WIDTH/2 - 32, Game.HEIGHT - 50, handler, ObjectId.PlayerShip));
		//handler.addObject(new NormalEnemyShip(Game.WIDTH/2 - 16, 100, handler, ObjectId.NormalEnemyShip));
		
	}

	public synchronized void start(){
		if (running){
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void run() {
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 45;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		//int updates = 0;
		//int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				//updates++;
				delta--;
			}
			render();
		
			//frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println("FPS: " + frames + " TICKS: " + updates);
				//frames = 0;
				//updates = 0;
			}
		}
	}
	private void tick(){
	
		
		//Full Restart code
		if(doRestart){
			NormalEnemyShip.normalEnemyShipDamageTaken = 3;
			SuicideEnemyShip.suicideEnemyShipDamageTaken = 3;
			NormalEnemyShip.normalShipSpeed = 4;
			BadPlayerBullet.BULLET_MAXSPEED = 1;
			PlayerShip.shipHealth = 200;
			PlayerShip.POINTS = 0;
			PlayerShip.suicideEnemyShipDamage = 50;
			PlayerShip.suicideEnemyShipDamage = 30;
			PlayerShip.playerShipSpeed = (int) 7.5;
			NormalEnemyShip.normalShipSpeed = 4;
			BadPlayerBullet.BULLET_MAXSPEED = 16;
			Game.defenceLevel = 1;
			Game.weaponLevel = 1;
			Game.speedLevel = 1;
			PlayerShip.POINTS = 0;
			PlayerShip.earthPopulation = 200;
			BossEnemyShip.HEALTH = 50000;
			BossEnemyShip.waitForMoonExploding = 0;
			PlayerShip.level = CURRENTLEVEL.ONE;
			PlayerShip.currentLevel = 1;
			BossEnemyShip.HEALTH = 20000;
			BossEnemyShip.bossShipIsExploding = false;
			BossEnemyShip.waitForMoonExploding = 0;
			BossEnemyShip.attackWithBullet = false;
			doRestart = false;
			tex = new Texture();
			handler = new Handler();
			this.addKeyListener(new KeyInput(handler));	
		}
		//Spawn Enemies
		if(Game.state == GAMESTATE.GAME){
			if(PlayerShip.currentLevel != 6){
				if(toGame){
					handler.addObject(new PlayerShip(Game.WIDTH/2 - 32, Game.HEIGHT - 50, handler, ObjectId.PlayerShip));
					toGame = false;
					}
				//spawn normalEnemyShip
				if(normalEnemyShipSpawnTimer >= timeNeededToSpawnEnemy){
					handler.addObject(new NormalEnemyShip(random.nextInt(Game.WIDTH - 64), -64, handler, ObjectId.NormalEnemyShip));
					normalEnemyShipSpawnTimer = 0;
				}
				//spawn suicideEnemyShip
				if(suicideEnemyShipSpawnTimer >= 250){
					handler.addObject(new SuicideEnemyShip(random.nextInt(Game.WIDTH - 64), -64, handler, ObjectId.SuicideEnemyShip));
					Game.playSound(suicideShipSpawn, true);
					suicideEnemyShipSpawnTimer = 0;
				}
				//spawn mineEnemyShip
				if(mineEnemyShipSpawnTimer >=  75){
					handler.addObject(new MineEnemyShip(random.nextInt(Game.WIDTH - 64), -64, handler, ObjectId.MineEnemyShip));
					mineEnemyShipSpawnTimer = 0;
				}
				if(PlayerShip.currentLevel == 2){
					timeNeededToSpawnEnemy = 32;
				}
				
				if(PlayerShip.currentLevel == 3){
					timeNeededToSpawnEnemy = 30;
				}
				
				if(PlayerShip.currentLevel == 4){
					timeNeededToSpawnEnemy = 27;
				}
				
				if(PlayerShip.currentLevel == 5){
					timeNeededToSpawnEnemy = 23;
				}
				suicideEnemyShipSpawnTimer ++;
				normalEnemyShipSpawnTimer ++;
				mineEnemyShipSpawnTimer ++;
			}
			for(int i = 0; i < handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId() == ObjectId.BadPlayerBullet){
					if(tempObject.getY() < 0){
						handler.removeObject(tempObject);
					}
				}
			}
			
			if(PlayerShip.currentLevel == 6 && BossEnemyShip.waitForMoonExploding == 25000 && !BossEnemyShip.bossShipIsExploding){
				timeNeededToSpawnEnemy = 40;
				if(mineEnemyShipSpawnTimer >=  200){
					handler.addObject(new MineEnemyShip(random.nextInt(Game.WIDTH - 64), -64, handler, ObjectId.MineEnemyShip));
					mineEnemyShipSpawnTimer = 0;
				}
				if(normalEnemyShipSpawnTimer >= timeNeededToSpawnEnemy){
					handler.addObject(new NormalEnemyShip(random.nextInt(Game.WIDTH - 64), -64, handler, ObjectId.NormalEnemyShip));
					normalEnemyShipSpawnTimer = 0;
				}
				normalEnemyShipSpawnTimer++;
				mineEnemyShipSpawnTimer ++;
			}
			
			handler.tick();
		}	
	}
	
	public static Texture getInstance(){
		return tex;
	}
	private void render(){

		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		//Draw here
		
		//Player UpgradeMenu after leveling up
		if(state == GAMESTATE.LEVELUPSTATE){

			Rectangle armorButton = new Rectangle( 110, Game.HEIGHT/2 - 155, 418, 80);
			Rectangle weaponButton = new Rectangle( 110, Game.HEIGHT/2 - 55 , 418, 80);
			Rectangle speedButton = new Rectangle( 110, Game.HEIGHT/2 + 45 , 418, 80);
			g.setColor(Color.gray);
			g.fillRect( 110, Game.HEIGHT/2 - 155, 418, 80);
			g.fillRect( 110, Game.HEIGHT/2 - 55 , 418, 80);
			g.fillRect( 110, Game.HEIGHT/2 + 45 , 418, 80);
			g.drawImage(tex.UpgradeMenuIcons[0], 125, Game.HEIGHT/2 - 150, 64, 64,  null);
			g.drawImage(tex.UpgradeMenuIcons[1], 125, Game.HEIGHT/2  - 50, 64, 64, null);
			g.drawImage(tex.UpgradeMenuIcons[2], 125, Game.HEIGHT/2 + 50, 64, 64,  null);
			g.setColor(Color.YELLOW);
			Graphics2D g2d = (Graphics2D) g;
			g2d.draw(armorButton);
			g2d.draw(weaponButton);
			g2d.draw(speedButton);
			Font fnt1 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 20);
			g.setFont(fnt1);
			g.drawString("Upgrade the ships armor", 200, Game.HEIGHT/2 - 120);
			g.drawString("Upgrade the ships weapons", 200, Game.HEIGHT/2 - 20 );
			g.drawString("Upgrade the ships speed", 200, Game.HEIGHT/2 + 80);
			
			
			//defence
			if(defenceLevel == 1){
				g.drawImage(tex.ShipUpgradeMeter[0], 470 , Game.HEIGHT/2 - 145, 64, 64, null);
			}
			if(defenceLevel == 2){
				g.drawImage(tex.ShipUpgradeMeter[1], 470 , Game.HEIGHT/2 - 145, 64, 64, null);
			}
			if(defenceLevel == 3){
				g.drawImage(tex.ShipUpgradeMeter[2], 470 , Game.HEIGHT/2 - 145, 64, 64, null);
			}
			if(defenceLevel == 4){
				g.drawImage(tex.ShipUpgradeMeter[3], 470 , Game.HEIGHT/2 - 145, 64, 64, null);
			}
			
			//weapon
			if(weaponLevel == 1){
				g.drawImage(tex.ShipUpgradeMeter[0], 470 , Game.HEIGHT/2 - 45, 64, 64, null);
			}
			if(weaponLevel == 2){
				g.drawImage(tex.ShipUpgradeMeter[1], 470 , Game.HEIGHT/2 - 45, 64, 64, null);
			}
			if(weaponLevel == 3){
				g.drawImage(tex.ShipUpgradeMeter[2], 470 , Game.HEIGHT/2 - 45, 64, 64, null);
			}
			if(weaponLevel == 4){
				g.drawImage(tex.ShipUpgradeMeter[3], 470 , Game.HEIGHT/2 - 45, 64, 64, null);
			}
			
			//speed
			if(speedLevel == 1){
				g.drawImage(tex.ShipUpgradeMeter[0], 470 , Game.HEIGHT/2 + 55, 64, 64, null);
			}
			if(speedLevel == 2){
				g.drawImage(tex.ShipUpgradeMeter[1], 470 , Game.HEIGHT/2 + 55, 64, 64, null);
			}
			if(speedLevel == 3){
				g.drawImage(tex.ShipUpgradeMeter[2], 470 , Game.HEIGHT/2 + 55, 64, 64, null);
			}
			if(speedLevel == 4){
				g.drawImage(tex.ShipUpgradeMeter[3], 470 , Game.HEIGHT/2 + 55, 64, 64, null);
			}
		}
		
		if(state == GAMESTATE.MENU){
			g.setColor(Color.BLACK);
			g.drawImage(backgroundMenu, 0, 0, null);
			g.setColor(Color.white);
			g.drawString("By Felix Toppar 05/07/2017", 5, Game.HEIGHT - 10);
		}
		
		if(state == GAMESTATE.VICTORY){
			g.setColor(Color.BLACK);
			g.drawImage(backgroundVictory, 0, 0, null);
			g.setColor(Color.yellow);
			Font fnt1 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 20);
			g.setFont(fnt1);
			g.drawString("With the modership dead,", 17, 410);
			g.drawString("victory was achived here today", 17, 440);
			
			g.drawString("You managed to save ", 17, 490);
			g.drawString(((PlayerShip.earthPopulation/ 200) * 100 )+ " % of the earth population", 17, 520);
			Font fnt2 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 25);
			Graphics2D g2d = (Graphics2D) g;
			Rectangle backButton = new Rectangle(Game.WIDTH - 150, Game.HEIGHT - 100 , 100, 50);
			g.setFont(fnt2);
			g.setColor(Color.DARK_GRAY);
			g.drawString("Menu", Game.WIDTH - 135 , Game.HEIGHT - 65);
			g2d.draw(backButton);
		}
		
		if(state == GAMESTATE.HELP){
			Rectangle backButton = new Rectangle(Game.WIDTH - 150, Game.HEIGHT - 100 , 100, 50);
			Graphics2D g2d = (Graphics2D) g;
			g.setColor(Color.GRAY);
			g.drawImage(backgroundHelp, 0, 0, null);
			Font fnt1 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 25);
			g.setFont(fnt1);
			g.drawString("Menu", Game.WIDTH - 135 , Game.HEIGHT - 65);
			g2d.draw(backButton);
			g.setColor(Color.blue);
			Font fnt2 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 20);
			g.setFont(fnt2);
			g.drawString("In this space-shooter game ", 45, 300);
			g.drawString("the objective is to defend.", 45, 330);
			g.drawString("earth from alien invadors.", 45, 360);
			
			g.drawString("Use the arrow keys to ", 45, 410);
			g.drawString("controll your spacecraft ", 45, 440);
			g.drawString("and use SPACE to fire.", 45, 470);
			
			g.drawString("Level up and upgrade your", 45, 510);
			g.drawString("spacecraft to defeat the", 45, 540);
			g.drawString("alien onslaught, good luck!", 45, 570);
			
		}
		if(state == GAMESTATE.GAME){
			g.setColor(Color.BLACK);	
			if(BossEnemyShip.waitForMoonExploding > 15200){
				g.drawImage(backgroundGameFinnalBattle, 0, 0, null);
			}else{
				g.drawImage(backgroundGame, 0, 0, null);
			}
			handler.render(g);
		}
		
		if(state == GAMESTATE.SCOREBOARD){
			g.setColor(Color.BLACK);
			g.drawImage(backgroundScoreboard, 0, 0, null);
			Rectangle backButton = new Rectangle(Game.WIDTH - 150, Game.HEIGHT - 100 , 100, 50);
			Graphics2D g2d = (Graphics2D) g;
			Font fnt1 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 25);
			g.setFont(fnt1);
			g.setColor(Color.DARK_GRAY);
			g.drawString("Back", Game.WIDTH - 135 , Game.HEIGHT - 65);
			g2d.draw(backButton);
			g.setColor(Color.blue);
			Font fnt2 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 35);
			g.setFont(fnt2);
			g.drawString("Game Over", 40 , 300);
			Font fnt3 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 17);
			g.setFont(fnt3);
			g.drawString("Earth was lost, Alien won", 43, 325);
			g.setColor(Color.yellow);
			g.drawString("You reached level " + PlayerShip.currentLevel, 43, 375);
		}
		if(state == GAMESTATE.PAUSED){
			g.setColor(Color.black);
			Font fnt1 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 35);
			g.setFont(fnt1);
			g.drawString("GAME PAUSED...", Game.WIDTH/2 - 140 , 300);
			Font fnt2 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 20);
			g.setFont(fnt2);
			g.drawString("Press ENTER to continue playing", Game.WIDTH/2 - 160, 325);
			Rectangle backButton = new Rectangle(Game.WIDTH - 150, Game.HEIGHT - 100 , 100, 50);
			Graphics2D g2d = (Graphics2D) g;
			Font fnt3 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 25);
			g.setFont(fnt3);
			g.drawString("Back", Game.WIDTH - 135 , Game.HEIGHT - 65);
			g2d.draw(backButton);

		}
		//Stop Drawing here
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args){
		
		File MenuMusic = new File("res/MenuMusic.wav");
		new Window(640, 960, "Earth Defence", new Game());
		playMusic(MenuMusic, true);
	}
	
	public static void playSound(File Sound, boolean run){
		if(run){
			try{
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Sound);
				SoundClip = AudioSystem.getClip();
				SoundClip.open(audioInputStream);
				SoundClip.start();
				
			}catch(Exception e){
				
			}
		}
		else{
			SoundClip.stop();
		}
			
	}
	public static void playMusic(File Sound, boolean run){
		if(run){
			try{
	
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Sound);
				MusicClip = AudioSystem.getClip();
				MusicClip.open(audioInputStream);
				MusicClip.start();
			
			}catch(Exception e){
				
			}
		}
		else
			MusicClip.stop();
	}
}