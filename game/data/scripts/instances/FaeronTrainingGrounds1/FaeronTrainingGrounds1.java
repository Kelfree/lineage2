/*
 * This file is part of the L2J Mobius project.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package instances.FaeronTrainingGrounds1;

import org.l2jmobius.gameserver.enums.QuestSound;
import org.l2jmobius.gameserver.model.Location;
import org.l2jmobius.gameserver.model.StatSet;
import org.l2jmobius.gameserver.model.WorldObject;
import org.l2jmobius.gameserver.model.actor.Npc;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.holders.ItemHolder;
import org.l2jmobius.gameserver.model.instancezone.Instance;
import org.l2jmobius.gameserver.model.quest.QuestState;
import org.l2jmobius.gameserver.model.skill.Skill;
import org.l2jmobius.gameserver.network.NpcStringId;
import org.l2jmobius.gameserver.network.serverpackets.ExShowScreenMessage;
import org.l2jmobius.gameserver.network.serverpackets.TutorialShowHtml;

import instances.AbstractInstance;
import quests.Q10735_ASpecialPower.Q10735_ASpecialPower;

/**
 * Fearon Training Grounds Instance Zone.
 * @author Sdw, malyelfik
 */
public class FaeronTrainingGrounds1 extends AbstractInstance
{
	// NPCs
	private static final int AYANTHE = 33942;
	private static final int AYANTHE_2 = 33944;
	// Monsters
	private static final int FLOATO = 27526;
	private static final int FLOATO2 = 27531;
	private static final int RATEL = 27527;
	// Items
	private static final ItemHolder SPIRITSHOTS_TRAINING = new ItemHolder(2509, 150);
	// Locations
	private static final Location[] MOB_SPAWNS =
	{
		new Location(-74721, 240513, -3584),
		new Location(-74760, 240773, -3560)
	};
	// Misc
	private static final int TEMPLATE_ID = 251;
	private static final double DAMAGE_BY_SKILL = 0.5d; // Percent
	
	public FaeronTrainingGrounds1()
	{
		super(TEMPLATE_ID);
		addStartNpc(AYANTHE, AYANTHE_2);
		addFirstTalkId(AYANTHE_2);
		addTalkId(AYANTHE, AYANTHE_2);
		addKillId(FLOATO, FLOATO2, RATEL);
		addSkillSeeId(RATEL);
	}
	
	@Override
	public String onEvent(String event, Npc npc, Player player)
	{
		final QuestState qs = player.getQuestState(Q10735_ASpecialPower.class.getSimpleName());
		String htmltext = null;
		if (qs == null)
		{
			return htmltext;
		}
		
		switch (event)
		{
			case "enter_instance":
			{
				enterInstance(player, npc, TEMPLATE_ID);
				break;
			}
			case "exit_instance":
			{
				finishInstance(player, 0);
				break;
			}
			case "33944-03.html":
			{
				if (qs.isCond(6))
				{
					spawnMonsters(RATEL, player);
					showOnScreenMsg(player, NpcStringId.FIGHT_USING_SKILLS, ExShowScreenMessage.TOP_CENTER, 10000);
				}
				else
				{
					final int npcId = (qs.isCond(4)) ? FLOATO2 : FLOATO;
					spawnMonsters(npcId, player);
					showOnScreenMsg(player, NpcStringId.ATTACK_THE_MONSTER, ExShowScreenMessage.TOP_CENTER, 10000);
				}
				htmltext = event;
				break;
			}
			case "33944-07.html":
			{
				if (qs.isCond(5))
				{
					qs.setCond(6, true);
					showOnScreenMsg(player, NpcStringId.FIGHT_USING_SKILLS, ExShowScreenMessage.TOP_CENTER, 10000);
					spawnMonsters(RATEL, player);
					htmltext = event;
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onTalk(Npc npc, Player player)
	{
		final QuestState qs = player.getQuestState(Q10735_ASpecialPower.class.getSimpleName());
		String htmltext = getNoQuestMsg(player);
		if (qs == null)
		{
			return htmltext;
		}
		
		if ((npc.getId() == AYANTHE_2) && qs.isStarted())
		{
			switch (qs.getCond())
			{
				case 1:
				{
					qs.setCond(2, true);
					spawnMonsters(FLOATO, player);
					showOnScreenMsg(player, NpcStringId.ATTACK_THE_MONSTER, ExShowScreenMessage.TOP_CENTER, 10000);
					htmltext = "33944-01.html";
					break;
				}
				case 2:
				case 4:
				case 6:
				{
					htmltext = "33944-02.html";
					break;
				}
				case 3:
				{
					if (qs.getInt("ss") == 1)
					{
						spawnMonsters(FLOATO2, player);
						showOnScreenMsg(player, NpcStringId.ATTACK_THE_MONSTER, ExShowScreenMessage.TOP_CENTER, 10000);
						qs.setCond(4, true);
						htmltext = "33944-05.html";
					}
					else
					{
						qs.set("ss", 1);
						giveItems(player, SPIRITSHOTS_TRAINING);
						showOnScreenMsg(player, NpcStringId.AUTOMATE_SPIRITSHOT_AS_SHOWN_IN_THE_TUTORIAL, ExShowScreenMessage.TOP_CENTER, 10000);
						player.sendPacket(new TutorialShowHtml(npc.getObjectId(), "..\\L2Text\\QT_003_bullet_01.htm", TutorialShowHtml.LARGE_WINDOW));
						htmltext = "33944-04.html";
					}
					break;
				}
				case 5:
				{
					player.sendPacket(new TutorialShowHtml(npc.getObjectId(), "..\\L2Text\\QT_004_skill_01.htm", TutorialShowHtml.LARGE_WINDOW));
					htmltext = "33944-06.html";
					break;
				}
				case 7:
				{
					htmltext = "33944-08.html";
					break;
				}
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(Npc npc, Player killer, boolean isSummon)
	{
		// Check if monster is inside instance
		final Instance world = npc.getInstanceWorld();
		if (world == null)
		{
			return super.onKill(npc, killer, isSummon);
		}
		
		// Remove monster from instance spawn holder
		world.setParameter("Mob" + npc.getScriptValue(), null);
		
		// Handle quest state
		final QuestState qs = killer.getQuestState(Q10735_ASpecialPower.class.getSimpleName());
		if (qs != null)
		{
			switch (npc.getId())
			{
				case FLOATO:
				case FLOATO2:
				{
					if ((qs.isCond(2) || qs.isCond(4)) && onKillQuestChange(killer, qs))
					{
						despawnMonsters(killer);
						if (qs.isCond(5) && (killer.getLevel() < 5))
						{
							addExpAndSp(killer, 1716, 0);
						}
					}
					break;
				}
				case RATEL:
				{
					if (qs.isCond(6) && onKillQuestChange(killer, qs))
					{
						despawnMonsters(killer);
						showOnScreenMsg(killer, NpcStringId.TALK_TO_AYANTHE_TO_LEAVE_THE_TRAINING_GROUNDS, ExShowScreenMessage.TOP_CENTER, 10000);
					}
					break;
				}
			}
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public String onSkillSee(Npc npc, Player player, Skill skill, WorldObject[] targets, boolean isSummon)
	{
		if (!npc.isDead() && (player.getTarget() == npc))
		{
			final double dmg = npc.getMaxHp() * DAMAGE_BY_SKILL;
			npc.reduceCurrentHp(dmg, player, null);
		}
		return super.onSkillSee(npc, player, skill, targets, isSummon);
	}
	
	/**
	 * Handle death of training monster. When all monsters are killed, quest cond is increased.
	 * @param killer player who killed monster
	 * @param qs quest state of killer
	 * @return {@code true} when all monsters are killed, otherwise {@code false}
	 */
	private boolean onKillQuestChange(Player killer, QuestState qs)
	{
		final int value = qs.getMemoStateEx(Q10735_ASpecialPower.KILL_COUNT_VAR) + 1;
		if (value >= 2)
		{
			qs.setCond(qs.getCond() + 1, true);
			qs.setMemoStateEx(Q10735_ASpecialPower.KILL_COUNT_VAR, 0);
			return true;
		}
		playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
		qs.setMemoStateEx(Q10735_ASpecialPower.KILL_COUNT_VAR, value);
		qs.getQuest().sendNpcLogList(killer);
		return false;
	}
	
	/**
	 * Spawn training monsters inside instance
	 * @param npcId template id of training monster
	 * @param player player that owns instance
	 */
	private void spawnMonsters(int npcId, Player player)
	{
		final Instance world = player.getInstanceWorld();
		if (world != null)
		{
			final StatSet params = world.getParameters();
			for (int i = 0; i < MOB_SPAWNS.length; i++)
			{
				if (params.getObject("Mob" + i, Npc.class) == null)
				{
					final Npc npc = addSpawn(npcId, MOB_SPAWNS[i], false, 0, false, world.getId());
					npc.setScriptValue(i);
					params.set("Mob" + i, npc);
				}
			}
		}
	}
	
	/**
	 * Despawn training monsters inside instance
	 * @param player player that owns instance
	 */
	private void despawnMonsters(Player player)
	{
		final Instance world = player.getInstanceWorld();
		if (world != null)
		{
			final StatSet params = world.getParameters();
			for (int i = 0; i < MOB_SPAWNS.length; i++)
			{
				final Npc mob = params.getObject("Mob" + i, Npc.class);
				if (mob != null)
				{
					mob.deleteMe();
					params.remove("Mob" + i);
				}
			}
		}
	}
	
	public static void main(String[] args)
	{
		new FaeronTrainingGrounds1();
	}
}