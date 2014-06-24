package org.aiwolf.client.lib;

import java.util.ArrayList;

/**
 * 発話文(Utterance)の集合．発話文が一つの時はUtteranceと変わらない．
 * @author kengo
 *
 */
public class Protocol {
	private String text;

	private ArrayList<Utterance> utterances = new ArrayList<>();

	private Conjection c = null;

	public Protocol(String input){
		text = input;
		if(!text.startsWith("(")){
			utterances.add(new Utterance(text));
		}else{
			LogicalStracture split = LogicalStracture.splitStracture(text);
			c = split.getC();
			ArrayList<String> splitString = split.getElement();
			for(String s: splitString){
				utterances.add(new Utterance(s));
			}
		}
	}

	public Protocol(ArrayList<Utterance> inputU){
		if(inputU.size() == 1){
			Utterance u = inputU.get(0);
			text = u.getText();
			utterances.add(u);
			return;
		}
		
		String conjectionString = "and ";
		utterances = inputU;
		ArrayList<String> textSplit = new ArrayList<>();
		for(Utterance u: utterances){
			textSplit.add( "( " + u.getText() + " ) " );
			textSplit.add(conjectionString);
		}
		textSplit.remove(textSplit.size()-1);
		text = LogicalStracture.bondStrings(textSplit, "");
	}

	public Protocol(Utterance inputU){
		text = inputU.getText();
		utterances.add(inputU);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ArrayList<Utterance> getUtterances() {
		return utterances;
	}

	public void setUtterances(ArrayList<Utterance> utterances) {
		this.utterances = utterances;
	}

	public Conjection getC() {
		return c;
	}

	public void setC(Conjection c) {
		this.c = c;
	}

}
