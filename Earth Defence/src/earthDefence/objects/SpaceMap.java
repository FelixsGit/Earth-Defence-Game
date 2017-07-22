package earthDefence.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import earthDefence.frameWorks.GameObject;
import earthDefence.frameWorks.ObjectId;
import earthDefence.window.Game;
import earthDefence.window.Handler;

public class SpaceMap extends GameObject{

	private Handler handler;
	
	public SpaceMap(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		
	}

	public void tick(LinkedList<GameObject> object) {
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		for(int i = 0; i < Game.HEIGHT; i += 32){
			g.fillRect((int) x, (int) y, 32, i);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

}