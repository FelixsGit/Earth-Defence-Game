package earthDefence.frameWorks;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import earthDefence.objects.BadPlayerBullet;
import earthDefence.objects.PlayerShip;
import earthDefence.window.Game;
import earthDefence.window.Game.GAMESTATE;
import earthDefence.window.Handler;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	
	private File buttonClick = new File("res/ClickSound.wav");
	private File menuMusic = new File("res/MenuMusic.wav");
	private File gameMusic = new File("res/GameMusic.wav");
	
	public KeyInput(Handler handler){
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.PlayerShip){
				if(key == KeyEvent.VK_UP){
					tempObject.setVelY(-PlayerShip.playerShipSpeed);
				}
				if(key == KeyEvent.VK_DOWN){
					tempObject.setVelY(PlayerShip.playerShipSpeed);
				}
				if(key == KeyEvent.VK_LEFT){
					tempObject.setVelX(-PlayerShip.playerShipSpeed);
				}
				if(key == KeyEvent.VK_RIGHT){
					tempObject.setVelX(PlayerShip.playerShipSpeed);
				}
				if(key == KeyEvent.VK_SPACE){
					PlayerShip.SHOOTING = true;
				}
				if(Game.state == GAMESTATE.GAME){
					if(key == KeyEvent.VK_ESCAPE){
						Game.playMusic(gameMusic, false);
						Game.state = GAMESTATE.PAUSED;
					}
					if(key == KeyEvent.VK_ENTER && PlayerShip.timeToUpgrade){
						PlayerShip.timeToUpgrade = false;
						Game.playMusic(gameMusic, false);
						Game.state = GAMESTATE.LEVELUPSTATE;
					}
				}
				if(Game.state == GAMESTATE.PAUSED){
					if(key == KeyEvent.VK_ENTER){
						Game.playMusic(gameMusic, true);
						Game.state = GAMESTATE.GAME;
					}
				}
			}
		}
	}
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.PlayerShip){
				if(key == KeyEvent.VK_UP){
					tempObject.setVelY(0);
				}
				if(key == KeyEvent.VK_DOWN){
					tempObject.setVelY(0);
				}
				if(key == KeyEvent.VK_LEFT){
					tempObject.setVelX(0);
				}
				if(key == KeyEvent.VK_RIGHT){
					tempObject.setVelX(0);
				}
				if(key == KeyEvent.VK_SPACE){
			
					PlayerShip.SHOOTING = false;
				}
			}
		}		
	}
}