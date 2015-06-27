package com.icetea.manager.pagodiario.utils;

public class Counter {
	int value = 0;
	
	public final int increment(){
		return ++this.value;
	}
	
	public final int get(){
		return this.value;
	}
	
}
