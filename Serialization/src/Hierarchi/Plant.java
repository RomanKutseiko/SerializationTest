package Hierarchi;

import java.io.Serializable;

import organismPack.Organism;

public class Plant extends Organism implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7851487620542760612L;
	private String family;
	
	public Plant(String name, String family){
		super (name);
		this.family = family;
	}
	
	
	public void setfamily(String family){
		this.family = family;
	}
	
	public String getfamily(){
		return this.family;
	}



}
