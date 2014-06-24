package org.aiwolf.client.lib;

public enum Verb {
	is(Category.ESTIMATE),//"Guen wolf" 等を便宜上"Guen is wolf"という風に扱う．
	comingout(Category.COMINGOUT),
	inspected(Category.RESULT),
	medium_telled(Category.RESULT),
	guarded(Category.RESULT);

	private Category category;

	private Verb(Category c){
		category = c;
	}

	public static Verb fromString(String input){
		for(Verb v: Verb.values()){
			if(input.equalsIgnoreCase(v.name())){
				return v;
			}
		}
		return is;
	}

	public Category getCategory(){
		return category;
	}

}
