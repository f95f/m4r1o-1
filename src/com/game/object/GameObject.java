package com.game.object;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.object.util.ObjectId;

public abstract class GameObject {

	private ObjectId id;
	private float x, y;
	private float velX, velY;
	private float width, height;
	private int scale;
	
	public GameObject(ObjectId id, float x, float y, float width, float height, int scale) {
		
		this.x = x * scale;
		this.y = y * scale;
		this.id = id;
		this.width = width * scale;
		this.height = height * scale;
		this.scale = scale;
		
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public void applyGravity() {
		velY += .5f; //defauld .5
	}
	
	public void setId(ObjectId id) {
		this.id = id;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width * scale;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height * scale;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public Object getId() {
		return this.id;
	}
	
	
}
