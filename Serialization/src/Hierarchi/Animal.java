package Hierarchi;

import java.io.Serializable;

public class Animal extends Organism implements Serializable {
	
	private String family;
	
	public Animal (String name, String family){
		super (name);//, "Animal");
		this.family = family;
	}
	
	
	public void setfamily(String family){
		this.family = family;
	}
	
	public String getfamily(){
		return this.family;
	}
	
}