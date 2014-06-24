package org.aiwolf.client.base.player;

import org.aiwolf.client.base.smpl.SampleBodyGuardPlayer;
import org.aiwolf.client.base.smpl.SampleMediumPlayer;
import org.aiwolf.client.base.smpl.SamplePossesedPlayer;
import org.aiwolf.client.base.smpl.SampleSeerPlayer;
import org.aiwolf.client.base.smpl.SampleVillagerPlayer;
import org.aiwolf.client.base.smpl.SampleWereWolfPlayer;
import org.aiwolf.common.data.Agent;
import org.aiwolf.common.data.Player;
import org.aiwolf.common.data.Role;
import org.aiwolf.common.net.GameInfo;

/**
 * 各プレイヤーに使用したいプレイヤーのインスタンスを生成して下さい． 例えば，村人のエージェントだけ自作のエージェントにしたい場合は， Player
 * villagerPlayer = new SampleVillagerPlayer(); ↓ Player villagerPlayer = new
 * [自作プレイヤーのクラス名のコンストラクタ];
 * と変更すれば，村人の役職が割り振られた時は自作のエージェント，それ以外の役職になった時はサンプルエージェントでプレイします．
 * 
 * @author tori
 * 
 */
abstract public class RoleBasePlayer implements Player {

	private Player villagerPlayer = new SampleVillagerPlayer();
	private Player seerPlayer = new SampleSeerPlayer();
	private Player mediumPlayer = new SampleMediumPlayer();
	private Player bodyGuardPlayer = new SampleBodyGuardPlayer();
	private Player possesedPlayer = new SamplePossesedPlayer();
	private Player werewolfPlayer = new SampleWereWolfPlayer();

	private Player rolePlayer;
	GameInfo gameInfo;

	/**
	 * @return villagerPlayer
	 */
	final public Player getVillagerPlayer() {
		return villagerPlayer;
	}

	/**
	 * @param villagerPlayer セットする villagerPlayer
	 */
	final public void setVillagerPlayer(Player villagerPlayer) {
		this.villagerPlayer = villagerPlayer;
	}

	/**
	 * @return seerPlayer
	 */
	final public Player getSeerPlayer() {
		return seerPlayer;
	}

	/**
	 * @param seerPlayer セットする seerPlayer
	 */
	final public void setSeerPlayer(Player seerPlayer) {
		this.seerPlayer = seerPlayer;
	}

	/**
	 * @return mediumPlayer
	 */
	final public Player getMediumPlayer() {
		return mediumPlayer;
	}

	/**
	 * @param mediumPlayer セットする mediumPlayer
	 */
	final public void setMediumPlayer(Player mediumPlayer) {
		this.mediumPlayer = mediumPlayer;
	}

	/**
	 * @return bodyGuardPlayer
	 */
	final public Player getBodyGuardPlayer() {
		return bodyGuardPlayer;
	}

	/**
	 * @param bodyGuardPlayer セットする bodyGuardPlayer
	 */
	final public void setBodyGuardPlayer(Player bodyGuardPlayer) {
		this.bodyGuardPlayer = bodyGuardPlayer;
	}

	/**
	 * @return possesedPlayer
	 */
	final public Player getPossesedPlayer() {
		return possesedPlayer;
	}

	/**
	 * @param possesedPlayer セットする possesedPlayer
	 */
	final public void setPossesedPlayer(Player possesedPlayer) {
		this.possesedPlayer = possesedPlayer;
	}

	/**
	 * @return werewolfPlayer
	 */
	final public Player getWerewolfPlayer() {
		return werewolfPlayer;
	}

	/**
	 * @param werewolfPlayer セットする werewolfPlayer
	 */
	final public void setWerewolfPlayer(Player werewolfPlayer) {
		this.werewolfPlayer = werewolfPlayer;
	}

	@Override
	abstract public String getName();

	@Override
	final public void update(GameInfo gameInfo) {
		if (rolePlayer == null) {
			this.gameInfo = gameInfo;
		} else {
			rolePlayer.update(gameInfo);
		}
	}

	@Override
	final public void initialize() {
		Role myRole = gameInfo.getRole();
		switch (myRole) {
		case villager:
			rolePlayer = villagerPlayer;
			break;

		case seer:
			rolePlayer = seerPlayer;
			break;
		case medium:
			rolePlayer = mediumPlayer;
			break;
		case bodyguard:
			rolePlayer = bodyGuardPlayer;
			break;
		case possessed:
			rolePlayer = possesedPlayer;
			break;
		case werewolf:
			rolePlayer = werewolfPlayer;
			break;
		default:
			rolePlayer = villagerPlayer;
			break;

		}
		rolePlayer.update(gameInfo);
		rolePlayer.initialize();

	}

	@Override
	final public void dayStart() {
		rolePlayer.dayStart();
	}

	@Override
	final public String talk() {
		return rolePlayer.talk();
	}

	@Override
	final public String whisper() {
		return rolePlayer.whisper();
	}

	@Override
	final public Agent vote() {
		return rolePlayer.vote();
	}

	@Override
	final public Agent attack() {
		return rolePlayer.attack();
	}

	@Override
	final public Agent divine() {
		return rolePlayer.divine();
	}

	@Override
	final public Agent guard() {
		return rolePlayer.guard();
	}

	@Override
	final public void finish() {
		rolePlayer.finish();
	}

}
