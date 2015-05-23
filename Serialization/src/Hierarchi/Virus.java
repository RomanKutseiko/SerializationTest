package Hierarchi;

import java.io.Serializable;

import organismPack.Organism;

public class Virus extends Organism implements Serializable {
	
	private String family;
	private String cellCount;
	
	public Virus (String name, String family, String cellCount){
		super (name);
		this.family = family;
		this.cellCount = cellCount;

	}

	public void setfamily(String family){
		this.family = family;
	}
	
	public String getfamily(){
		return this.family;
	}


	public void setCellCount(String cellCount){
		this.cellCount = cellCount;
	}
	
	public String getCellCount(){
		return this.cellCount;
	}

}
