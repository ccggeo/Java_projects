//runs the game

package bricks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

	private boolean play = false;
	private int score = 0;
	private int totalBricks = 20;

	private Timer timer;
	private int delay = 8;

	private int playerX = 300;

	private int ballPostX = 120;
	private int ballPostY = 350;
	private int ballXdir = -1;
	private int ballYdir = -2;
	
	private Gen map;

	// contructor to add values when main object is created.
	public Gameplay() {
		map = new Gen(3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}

	public void paint(Graphics g) {
		// backgorund
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);

		//draw
		map.draw((Graphics2D)g);
		
		// borders
		g.setColor(Color.orange);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);

		// club thing
		g.setColor(Color.white);
		g.fillRect(playerX, 550, 100, 8);

		// dode
		g.setColor(Color.magenta);
		g.fillOval(ballPostX, ballPostY, 20, 20);

		g.dispose();

	}

	// keylisteners

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if (play) {

			if (new Rectangle(ballPostX, ballPostY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
				ballYdir = -ballYdir;

			}
			
			//ballz detecting intersection
			A: for(int i=0; i<map.map.length; i++){
				for (int j= 0; j<map.map[0].length; j++){
					if(map.map[i][j] >0){
						 int brickX =j*map.brickWidth +80;
						 int brickY = i* map.brickHeight +50;
						 int brickWidth= map.brickWidth;
						 int brickheight = map.brickHeight;
						
						 Rectangle rect1  = new Rectangle(brickX, brickY, brickWidth, brickheight);
						 Rectangle ballRect = new Rectangle(ballPostX, ballPostY, 20, 20);
						 Rectangle brickRect = rect1;
						 
						 //if ball intersects rectable, call a box value and turn it to 0
						 
						 if(ballRect.intersects(brickRect)){
							 map.setBrickvalue(0, i, j);
							 //one less than previous value
							 totalBricks --;
							 score +=5; 
							 //or
							 if(ballPostX+19<=brickRect.x || ballPostX+1>=brickRect.x + brickRect.width){
								 ballXdir=- ballXdir;
							 }
							 else {
								 ballYdir = -ballYdir;
							 
							 }
						 break A;
						 
						 }
						 
					}
					
				}
				
			}

			ballPostX += ballXdir;
			ballPostY += ballYdir;
			if (ballPostX < 0) {
				ballXdir = -ballXdir;
			}
			if (ballPostY < 0) {
				ballYdir = -ballYdir;
			}
			if (ballPostX > 670) {
				ballXdir = -ballXdir;
			}
		}

		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (playerX >= 600) {
				playerX = 600;
			}
		} else {
			moveRight();
		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (playerX < 10) {
				playerX = 10;
			}
		} else {
			moveLeft();
		}
	}

	public void moveRight() {
		play = true;
		playerX += 20;

	}

	public void moveLeft() {
		play = true;
		playerX -= 20;
	}

}