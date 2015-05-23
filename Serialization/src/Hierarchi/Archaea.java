package Hierarchi;

import java.io.Serializable;

import organismPack.Organism;

public class Archaea extends Organism implements Serializable {
	
	private String cellCount;
	
	public Archaea (String name, String cellCount){
		super (name);
		this.cellCount = cellCount;
	}

	
	public void setCellCount(String cellCount){
		this.cellCount = cellCount;
	}
	
	public String getCellCount(){
		return this.cellCount;
	}


}
