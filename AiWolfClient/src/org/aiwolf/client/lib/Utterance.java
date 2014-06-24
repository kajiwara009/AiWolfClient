package org.aiwolf.client.lib;

/**
 * Protocolクラスのフィールド
 * @author kengo
 *
 */
public class Utterance {
	private String text;


	//発話文のタイプ(Declareとか)
	private SentenceType sentenceType;

	//発話の中身(comingout seerとか)
	private Passage passage;

	/**
	 * String to passage
	 *
	 * 今回はDeclareのみなので，ここのパースは省略して，stringをそのままpassage
	 * SKIPの時は，UtteranceのtextにのみSKIPが入っている．
	 *
	 * テキストに加えて，発話者も必要？(“I”の扱い)
	 * 主語がRoleの時は，CO状況も必要．(これは流石にエージェントの方で)
	 * @param input
	 */
	public Utterance(String input){
		text = input;
		if(input.equals("SKIP")){
			passage = new Passage(input);
			return;
		}else if(input.equals("Over")){
			passage = new Passage(input);
			return;
		}

		if(true){
			passage = new Passage(text);
		}

		String[] textSplit = text.split("\\s+");
		try {
			sentenceType.setUv(UtteranceVerb.fromString(textSplit[0]));
			if(sentenceType.getUv() == UtteranceVerb.declare){
				sentenceType.setRate(Integer.parseInt(textSplit[1]));
			}
		} catch (Exception e) {
			// TODO: handle exception
			//
		}
	}

	public String getText() {
		return text;
	}

	public SentenceType getSentenceType() {
		return sentenceType;
	}

	public Passage getPassage() {
		return passage;
	}

}
