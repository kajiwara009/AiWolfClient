package org.aiwolf.client.lib;

import java.util.List;

import org.aiwolf.common.*;
import org.aiwolf.common.data.*;
import org.aiwolf.common.net.*;

public class Passage {
	String text;

	String[] split;

	Agent subject;

	Category category;

	Verb verb;


	//comingoutのROLE
	State object = null;

	//estimateのROLE
	State state = null;

	//resultのSpecies(guard以外)
	State species = null;

	public Passage(String input) {
		text = input;
		split = input.split("\\s+");// 一つ以上の空白で区切る

		if(split[0].equals("SKIP")){
			category = Category.SKIP;
		}else if(split[0].equals("Over")){
			category = Category.OVER;
		}else{
			verb = Verb.fromString(split[1]);
			category = verb.getCategory();

			switch (category) {
			case COMINGOUT:
				object = State.fromString(split[2]);
				break;
			case ESTIMATE:
				state = State.fromString(split[1]);
				break;
			case RESULT:
				if(verb != Verb.guarded){
					species = State.fromString(split[2]);
				}
			case SKIP:
			case OVER:
			default:
				break;
			}
		}
	}
/*
	public Agent getSubject(List<Agent> agentList) {
		if (category == Category.SKIP) {
			return null;
		} else {
			int idx = Integer.parseInt(split[0]);
			for (Agent agent : agentList) {
				if (agent.getAgentIdx() == idx) {
					return agent;
				}
			}
		}
		return null;
	}
	*/

	/**
	 *
	 * @param agentList
	 * @return
	 * @author tori
	 */
	public Agent getSubject() {
		if (category == Category.SKIP) {
			return null;
		} else {
			int idx = Integer.parseInt(split[0]);
			return Agent.getAgent(idx);
		}
	}


	public Category getCategory() {
		return category;
	}

	public Verb getVerb() {
		return verb;
	}

	/**
	 * comingoutの結果の役職を返す
	 * @return
	 */
	public Role getObject() {
		if(category == Category.COMINGOUT){
			return object.toRole();
		}
		return null;
	}

	/**
	 * estimateの結果の役職を返す
	 * @return
	 */
	public Role getState(){
		if(category == Category.ESTIMATE){
			return state.toRole();
		}else{
			return null;
		}
	}

	public Verb getAction(){
		if(category == Category.RESULT){
			return verb;
		}else{
			return null;
		}
	}

	/**
	 * inspectとmedium_tellingの結果を返す
	 * @return
	 */
	public Species getAttribution(){
		if(category == Category.RESULT){
			return species.toSpecies();
		}else{
			return null;
		}
	}

}
