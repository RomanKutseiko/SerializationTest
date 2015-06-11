package Hierarchi;

import java.io.Serializable;

import organismPack.Organism;

public class Mushroom extends Organism implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7579063404427115448L;
	private String family;
	
	public Mushroom (String name, String family){
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
