package com.game.object.util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	private boolean[] keyDown = new boolean[4];
	private Handler handler;
	
	public KeyInput(Handler handler) {
		
		this.handler = handler;
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_ESCAPE) { // Passar para o keyReleased
			System.exit(0);
		}
		if(key == KeyEvent.VK_W) {
			
			if(!handler.getPlayer().hasJumped()) {
			
				handler.getPlayer().setVelY(-15);
				keyDown[0] = true;
				handler.getPlayer().setJumped(true);
				
			}
			
		}
		if(key == KeyEvent.VK_A) {
			handler.getPlayer().setVelX(-8);
			keyDown[1] = true;
			
		}
		if(key == KeyEvent.VK_D) {
			handler.getPlayer().setVelX(8);
			keyDown[2] = true;

		}

		if(key == KeyEvent.VK_S) {
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	
		int key = e.getKeyCode();
	
		if(key == KeyEvent.VK_W) {
			keyDown[0] = false;
		}
		if(key == KeyEvent.VK_A) {
			keyDown[1] = false;
			
		}
		if(key == KeyEvent.VK_S) {
			
		}
		if(key == KeyEvent.VK_D) {
			keyDown[2] = false;
			
		}
		if(!keyDown[1] && !keyDown[2]) {
			
			handler.getPlayer().setVelX(0);
			
		}
		
	}
}
