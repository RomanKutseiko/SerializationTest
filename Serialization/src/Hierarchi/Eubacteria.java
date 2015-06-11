package Hierarchi;

import java.io.Serializable;

import organismPack.Organism;

public class Eubacteria extends Organism implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7229482503652807101L;
	private String family;
	private String cellCount;

	
	public Eubacteria (String name, String family, String cellCount){
		super (name);
		this.cellCount = cellCount;
		this.family = family;
	}

	
	public void setCellCount(String cellCount){
		this.cellCount = cellCount;
	}
	
	public String getCellCount(){
		return this.cellCount;
	}
	
	public void setfamily(String family){
		this.family = family;
	}
	
	public String getfamily(){
		return this.family;
	}


}
