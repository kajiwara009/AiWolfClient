package org.aiwolf.client.lib;

import javax.security.auth.Subject;

import org.aiwolf.common.*;
import org.aiwolf.common.data.*;
import org.aiwolf.common.net.*;


public class TemplateTalkFactory {
	/**
	 * 今後，対象プレイヤーを(A∧B)∨Cのように論理構造で表すなら，引数としてNoun型を取るものを作って，そっちで入力してもらう．
	 * @param targetPlayer
	 * @return
	 */
	/*public static Utterance estimate(Player subjectPlayer, Player targetPlayer){
		Noun<String> subjectName = new Noun(subjectPlayer.getName());
		Noun<String> targetName = new Noun(targetPlayer.getName());
		Verb verb = Verb.is;
		return null;
	}*/
	/**
	 * 省略型．主語のプレイヤーがIとなるように
	 * @param targetPlayer
	 * @return
	 */
	// agent01 GIFTED (〇〇は占い師だと思う)
	public static Utterance estimate(Agent targetAgent, Role role){
		return new Utterance(getSCsentence(targetAgent, State.fromRole(role)));
	}

	//agent02 comingout seer (〇〇が占い師だとカミングアウトする)
	public static Utterance comingout(Agent subjectAgent, Role role){
		Verb verb = Verb.comingout;
		return new Utterance(getSVCsentence(subjectAgent, verb, State.fromRole(role)));
	}

	//agent03 inspected Human,wolf (〇〇が人狼だと占われた)
	public static Utterance inspected(Agent subjectAgent, Species species){
		Verb verb = Verb.inspected;
		State o = State.fromSpecies(species);
		return new Utterance(getSVCsentence(subjectAgent, verb, o));
	}

	//agent04 medium_telled Human (〇〇が人間だと霊能された)
	public static Utterance medium_telled(Agent subjectAgent, Species species){
		Verb verb = Verb.medium_telled;
		State o = State.fromSpecies(species);
		return new Utterance(getSVCsentence(subjectAgent, verb, o));
	}

	public static Utterance guarded(Agent subjectAgent){
		Verb verb = Verb.guarded;
		return new Utterance(getSVsentence(subjectAgent, verb));
	}

	//SKIP
	public static Utterance skip(){
		return new Utterance("SKIP");
	}

	public static Utterance over(){
		return new Utterance("Over");
	}


	private static String toString(SentenceType sentenceType, Passage passage){
		String text = "";
		text = text + sentenceType.getUv() + " " + sentenceType.getRate() + "% " + " ( " + toString(passage) + " )";
		return text;
	}

	private static String toString(Passage passage){

		return null;
	}

	private static String getSCsentence(Agent agent, State complement){
		return String.valueOf(agent.getAgentIdx()) + " " + complement.name();
	}

	private static String getSVsentence(Agent agent, Verb v){
		return String.valueOf(agent.getAgentIdx()) + " " + v.name();
	}

	private static String getSVCsentence(Agent agent, Verb v, State complement){
		return String.valueOf(agent.getAgentIdx()) + " "+ v.name() + " " + complement.name();
	}

/*	private static String getSVOsentence(Agent agent, Verb v, Obj object){
		return String.valueOf(agent.getIdx()) + v.name() + object.name();
	}
*/
}
