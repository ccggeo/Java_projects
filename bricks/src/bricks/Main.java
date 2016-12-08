package bricks;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		JFrame obj = new JFrame(); 
		//gameplay object 
		Gameplay gamePlay = new Gameplay();
		obj.setBounds(10, 10, 700, 600);
		obj.setTitle("Oh muh Balls");		
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gamePlay);
		
	}

}
