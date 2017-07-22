package earthDefence.frameWorks;


import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import earthDefence.objects.PlayerShip;
import earthDefence.objects.PlayerShip.CURRENTLEVEL;
import earthDefence.window.Game;
import earthDefence.window.Game.GAMESTATE;



public class MouseInput implements MouseListener{
	
	private File buttonClick = new File("res/ClickSound.wav");
	private File menuMusic = new File("res/MenuMusic.wav");
	private File gameMusic = new File("res/GameMusic.wav");
	private File gameOverMusic = new File("res/GameOverMusic.wav");
	private File victoryMusic = new File("res/VictoryMusic.wav");


	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		/*
		Rectangle armorButton = new Rectangle( 110, Game.HEIGHT/2 - 155, 418, 80);
		Rectangle weaponButton = new Rectangle( 110, Game.HEIGHT/2 - 55 , 418, 80);
		Rectangle speedButton = new Rectangle( 110, Game.HEIGHT/2 + 45 , 418, 80);
		*/
		
		//Level 2 Ding, Choose upgrade read MouseInput.
		if(Game.state == GAMESTATE.LEVELUPSTATE && PlayerShip.level == CURRENTLEVEL.TWO){
			int xm = e.getX();
			int ym = e.getY();	
			if(xm >= 110 && xm <= 528){
				if(ym >= Game.HEIGHT/2 - 155 && ym <= Game.HEIGHT/2 - 75 ){
					//pressed armorButton
					Game.state = GAMESTATE.GAME;
					Game.playMusic(gameMusic, true);
					Game.playSound(buttonClick, true);
					Game.defenceLevel++;
					PlayerShip.upgradeArmor = true;
				}
			}
			if(xm >= 110 && xm <= 528){
				if(ym >= Game.HEIGHT/2 - 55 && ym <= Game.HEIGHT/2 + 25){
					//pressed weaponButton
					Game.state = GAMESTATE.GAME;
					Game.playMusic(gameMusic, true);
					Game.playSound(buttonClick, true);
					Game.weaponLevel++;
					PlayerShip.upgradeWeapon = true;
				}
			}
			if(xm >= 110 && xm <= 528){
				if(ym >= Game.HEIGHT/2 + 45 && ym <= Game.HEIGHT/2 +125){
					//pressed speedButton
					Game.state = GAMESTATE.GAME;
					Game.playMusic(gameMusic, true);
					Game.playSound(buttonClick, true);
					Game.speedLevel++;
					PlayerShip.upgradeSpeed = true;
				}
			}
		}
		//Level 3 Ding, Choose upgrade read MouseInput.
		if(Game.state == GAMESTATE.LEVELUPSTATE && PlayerShip.level == CURRENTLEVEL.THREE){
			int xm = e.getX();
			int ym = e.getY();	
			if(xm >= 110 && xm <= 528){
				if(ym >= Game.HEIGHT/2 - 155 && ym <= Game.HEIGHT/2 - 75 ){
					//pressed armorButton
					Game.state = GAMESTATE.GAME;
					Game.playMusic(gameMusic, true);
					Game.playSound(buttonClick, true);
					Game.defenceLevel++;
					PlayerShip.upgradeArmor = true;
				}
			}
			if(xm >= 110 && xm <= 528){
				if(ym >= Game.HEIGHT/2 - 55 && ym <= Game.HEIGHT/2 + 25){
					//pressed weaponButton
					Game.state = GAMESTATE.GAME;
					Game.playMusic(gameMusic, true);
					Game.playSound(buttonClick, true);
					Game.weaponLevel++;
					PlayerShip.upgradeWeapon = true;
				}
			}
			if(xm >= 110 && xm <= 528){
				if(ym >= Game.HEIGHT/2 + 45 && ym <= Game.HEIGHT/2 +125){
					//pressed speedButton
					Game.state = GAMESTATE.GAME;
					Game.playMusic(gameMusic, true);
					Game.playSound(buttonClick, true);
					Game.speedLevel++;
					PlayerShip.upgradeSpeed = true;
				}
			}
		}
		//Level 4 Ding, Choose upgrade read MouseInput.
		if(Game.state == GAMESTATE.LEVELUPSTATE && PlayerShip.level == CURRENTLEVEL.FOUR){
			int xm = e.getX();
			int ym = e.getY();	
			if(xm >= 110 && xm <= 528){
				if(ym >= Game.HEIGHT/2 - 155 && ym <= Game.HEIGHT/2 - 75 ){
					//pressed armorButton
					Game.state = GAMESTATE.GAME;
					Game.playMusic(gameMusic, true);
					Game.playSound(buttonClick, true);
					Game.defenceLevel++;
					PlayerShip.upgradeArmor = true;
				}
			}
			if(xm >= 110 && xm <= 528){
				if(ym >= Game.HEIGHT/2 - 55 && ym <= Game.HEIGHT/2 + 25){
					//pressed weaponButton
					Game.state = GAMESTATE.GAME;
					Game.playMusic(gameMusic, true);
					Game.playSound(buttonClick, true);
					Game.weaponLevel++;
					PlayerShip.upgradeWeapon = true;
				}
			}
			if(xm >= 110 && xm <= 528){
				if(ym >= Game.HEIGHT/2 + 45 && ym <= Game.HEIGHT/2 +125){
					//pressed speedButton
					Game.state = GAMESTATE.GAME;
					Game.playMusic(gameMusic, true);
					Game.playSound(buttonClick, true);
					Game.speedLevel++;
					PlayerShip.upgradeSpeed = true;
				}
			}
		}
		//Level 5 Ding, Choose upgrade read MouseInput.
		if(Game.state == GAMESTATE.LEVELUPSTATE && PlayerShip.level == CURRENTLEVEL.FIVE){
			int xm = e.getX();
			int ym = e.getY();	
			if(xm >= 110 && xm <= 528){
				if(ym >= Game.HEIGHT/2 - 155 && ym <= Game.HEIGHT/2 - 75 ){
					//pressed armorButton
					if(Game.defenceLevel == 4){
						Game.playSound(buttonClick, true);
					}
					else{
						Game.state = GAMESTATE.GAME;
						Game.playMusic(gameMusic, true);
						Game.playSound(buttonClick, true);
						Game.defenceLevel++;
						PlayerShip.upgradeArmor = true;
					}
				}
			}
			if(xm >= 110 && xm <= 528){
				if(ym >= Game.HEIGHT/2 - 55 && ym <= Game.HEIGHT/2 + 25){
					//pressed weaponButton
					if(Game.weaponLevel == 4){
						Game.playSound(buttonClick, true);
					}
					else{
						Game.state = GAMESTATE.GAME;
						Game.playMusic(gameMusic, true);
						Game.playSound(buttonClick, true);
						Game.weaponLevel++;
						PlayerShip.upgradeWeapon = true;
					}
				}
			}
			if(xm >= 110 && xm <= 528){
				if(ym >= Game.HEIGHT/2 + 45 && ym <= Game.HEIGHT/2 +125){
					//pressed sppedButton
					if(Game.speedLevel == 4){
						Game.playSound(buttonClick, true);
					}
					else{
						Game.state = GAMESTATE.GAME;
						Game.playMusic(gameMusic, true);
						Game.playSound(buttonClick, true);
						Game.speedLevel++;
						PlayerShip.upgradeSpeed = true;
					}
				}
			}
		}
		
		//Level 6 Ding, Choose upgrade read MouseInput.
		if(Game.state == GAMESTATE.LEVELUPSTATE && PlayerShip.level == CURRENTLEVEL.SIX){
			int xm = e.getX();
			int ym = e.getY();	
			if(xm >= 110 && xm <= 528){
				if(ym >= Game.HEIGHT/2 - 155 && ym <= Game.HEIGHT/2 - 75 ){
					//pressed armorButton
					if(Game.defenceLevel == 4){
						Game.playSound(buttonClick, true);
					}
					else{
						Game.state = GAMESTATE.GAME;
						Game.playMusic(gameMusic, true);
						Game.playSound(buttonClick, true);
						Game.defenceLevel++;
						PlayerShip.upgradeArmor = true;
					}
				}
			}
			if(xm >= 110 && xm <= 528){
				if(ym >= Game.HEIGHT/2 - 55 && ym <= Game.HEIGHT/2 + 25){
					//pressed weaponButton
					if(Game.weaponLevel == 4){
						Game.playSound(buttonClick, true);
					}
					else{
						Game.state = GAMESTATE.GAME;
						Game.playMusic(gameMusic, true);
						Game.playSound(buttonClick, true);
						Game.weaponLevel++;
						PlayerShip.upgradeWeapon = true;
					}
				}
			}
			if(xm >= 110 && xm <= 528){
				if(ym >= Game.HEIGHT/2 + 45 && ym <= Game.HEIGHT/2 +125){
					//pressed sppedButton
					if(Game.speedLevel == 4){
						Game.playSound(buttonClick, true);
					}
					else{
						Game.state = GAMESTATE.GAME;
						Game.playMusic(gameMusic, true);
						Game.playSound(buttonClick, true);
						Game.speedLevel++;
						PlayerShip.upgradeSpeed = true;
					}
				}
			}
		}
		if(Game.state == GAMESTATE.MENU){
			int xm = e.getX();
			int ym = e.getY();	
			if(xm >= 30 && xm <= 260){
				if(ym >= 300 && ym <= 410){
					//Pressed PlayButton
					Game.playSound(buttonClick, true);
					Game.playMusic(menuMusic, false);
					Game.playMusic(gameMusic, true);
					Game.state = GAMESTATE.GAME;
					Game.toGame = true;
				}
			}
			if(xm >= 30 && xm <= 260){
				if(ym >= 435 && ym <= 545){
					Game.playSound(buttonClick, true);
					Game.state = GAMESTATE.HELP;
				}
			}
		}
			
		if(Game.state == GAMESTATE.HELP){
			int xm = e.getX();
			int ym = e.getY();		
			if((xm >= Game.WIDTH - 150) && (xm <= Game.WIDTH - 50)){
				if((ym >= Game.HEIGHT - 100 ) && (ym <= Game.HEIGHT - 50)){
					//Pressed backButton
					Game.playSound(buttonClick, true);
					Game.state = GAMESTATE.MENU;
				}
			}
		}
		if(Game.state == GAMESTATE.SCOREBOARD){
			int xm = e.getX();
			int ym = e.getY();
			if((xm >= Game.WIDTH - 150) && (xm <= Game.WIDTH - 50)){
				if((ym >= Game.HEIGHT - 100 ) && (ym <= Game.HEIGHT - 50)){
					//Pressed MenuButton
					Game.playSound(buttonClick, true);
					Game.state = GAMESTATE.MENU;
					Game.playMusic(gameOverMusic, false);
					Game.playMusic(menuMusic, true);
					Game.doRestart = true;
	
				}
			}
		}
		if(Game.state == GAMESTATE.VICTORY){
			int xm = e.getX();
			int ym = e.getY();
			if((xm >= Game.WIDTH - 150) && (xm <= Game.WIDTH - 50)){
				if((ym >= Game.HEIGHT - 100 ) && (ym <= Game.HEIGHT - 50)){
					//Pressed MenuButton
					Game.playSound(buttonClick, true);
					Game.state = GAMESTATE.MENU;
					Game.playMusic(victoryMusic, false);
					Game.playMusic(menuMusic, true);
					Game.doRestart = true;
			
				}
			}
		}
		if(Game.state == GAMESTATE.PAUSED){
			int xm = e.getX();
			int ym = e.getY();
			if((xm >= Game.WIDTH - 150) && (xm <= Game.WIDTH - 50)){
				if((ym >= Game.HEIGHT - 100 ) && (ym <= Game.HEIGHT - 50)){
					Game.playSound(buttonClick, true);
					Game.state = GAMESTATE.MENU;
					Game.playMusic(menuMusic, true);
					Game.doRestart = true;
				}
			}
		}
		if(Game.state == GAMESTATE.HELP){
			int xm = e.getX();
			int ym = e.getY();
			if((xm >= Game.WIDTH - 150) && (xm <= Game.WIDTH - 50)){
				if((ym >= Game.HEIGHT - 100 ) && (ym <= Game.HEIGHT - 50)){
					Game.playSound(buttonClick, true);
					//Pressed MenuButton
					Game.state = GAMESTATE.MENU;
				}
			}
		}
	}

	@SuppressWarnings("unused")
	private void addKeyListener(KeyInput keyInput) {
		
	}

	public void mouseReleased(MouseEvent e) {

	}
}