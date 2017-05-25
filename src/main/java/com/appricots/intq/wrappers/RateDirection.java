package com.appricots.intq.wrappers;

public enum RateDirection {
	UP(1),
	DOWN(-1);
	
	private int delta;
	RateDirection(int delta){
		this.delta = delta;
	}
	
	public int getNumericDelta(){
		return delta;
	}
}
