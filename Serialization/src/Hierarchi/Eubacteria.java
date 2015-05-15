package Hierarchi;

import java.io.Serializable;

public class Eubacteria extends Organism implements Serializable {
	
	private String cellCount;
	
	public Eubacteria (String name, String cellCount){
		super (name);//, "Eubacteria");
		this.cellCount = cellCount;
	}

	
	public void setCellCount(String cellCount){
		this.cellCount = cellCount;
	}
	
	public String getCellCount(){
		return this.cellCount;
	}


}
