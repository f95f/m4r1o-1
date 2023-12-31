package com.game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.game.gfx.Windows;
import com.game.object.Block;
import com.game.object.Player;
import com.game.object.util.Handler;
import com.game.object.util.KeyInput;

public class Game extends Canvas implements Runnable{

	// Game constants
	private final static int MS_PER_SEC = 1000;
	private final static int NS_PER_SEC = 1000000000;//1_000_000_000;
	private final static double NUM_TICKS = 60.0;
	private final static String NAME	= "Super Mario Bros. 0.1";
	private static final int WINDOW_WIDTH = 960;
	private static final int WINDOW_HEIGHT = 720;

	// Game variables
	private boolean running;
	
	// Game components
	private Thread thread;
	private Handler handler;
	
	public Game() {
		
		initialize();
		
	}
	
	public static void main(String[] args) {

		new Game();	

	}
	
	private void initialize() {
		
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		
		//temp \|/
		
		handler.setPlayer(new Player(32, 32, 1, handler));
		
		for(int i = 0; i < 20; i++) {
			
			handler.addObj(new Block(10 * 32, 32 * i, 32, 32, 1));
			
		}
		for(int i = 0; i < 30; i++) {
			
			handler.addObj(new Block(i * 32, 32 * 15, 32, 32, 1));
			
		}
		
		
		new Windows(WINDOW_WIDTH, WINDOW_HEIGHT, NAME, this);
		start();
		
	}

	private synchronized void start() {
		
		thread = new Thread(this);
		thread.start();
		
		running = true;
	}
	
	private synchronized void stop() {
		
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
	}

	@Override
	public void run() {

		long lastTime = System.nanoTime();
		double amountOfTicks = NUM_TICKS;
		double ns = NS_PER_SEC / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		int updates = 0;
		
		while(running) {
			
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while(delta >= 1) {
				
				tick();
				updates++;
				delta--;
				
			}
			
			if(running) {
				
				render();
				frames++;

			}
			
			if(System.currentTimeMillis() - timer > MS_PER_SEC) {
				
				timer += MS_PER_SEC;
				System.out.println(" > FPS: " + frames + " | > TPS: " + updates);
				updates = 0;
				frames = 0;
				
			}
		}
		stop();
		
	}
	
	private void tick() {
		
		handler.tick();
		
	}
	
	private void render() {
		
		BufferStrategy buf = this.getBufferStrategy();
		if(buf == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = buf.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		handler.render(g);
		
		g.dispose();
		buf.show();
		
	}
	
	public static int getWindowWidth() {
		return WINDOW_WIDTH;
	}
	public static int getWindowHeight() {
		return WINDOW_HEIGHT;
	}
}

























