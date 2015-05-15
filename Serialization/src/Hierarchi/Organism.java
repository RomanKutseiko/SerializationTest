package Hierarchi;

import java.io.Serializable;

public class Organism implements Serializable {
	String name = new String();
	//String type = new String();
	
	protected Organism(String name){//, String type){
		this.name = name;
		//this.type = type;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	/*public void setType(String type){
		this.type = type;
	}*/
	
	public String getName() {
		return this.name;
	}
	
	/*public String getType() {
		return this.type;
	}*/
}
