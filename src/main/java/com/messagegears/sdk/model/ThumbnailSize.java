package com.messagegears.sdk.model;

public enum ThumbnailSize {

	MICRO(64, 48),
	SMALL(80, 60),
	SMEDIUM(220,165),
	MEDIUM(232, 174),
	LARGE(400, 300),
	GRANDE(800, 600);
	
	private String imgSize;
	private int height;
	private int width;
	
	private ThumbnailSize(int height, int width) {
		this.imgSize = Integer.toString(height) + "x" + Integer.toString(width);
		this.height = height;
		this.width = width;
	}
	
	public String getImageSize() {
		return imgSize;
	}
	
	public int getImageHeight() {
		return height;
	}
	
	public int getImageWidth() {
		return width;
	}
}
