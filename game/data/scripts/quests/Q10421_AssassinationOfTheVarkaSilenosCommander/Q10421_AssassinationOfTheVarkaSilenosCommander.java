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
package quests.Q10421_AssassinationOfTheVarkaSilenosCommander;

import org.l2jmobius.gameserver.enums.CategoryType;
import org.l2jmobius.gameserver.enums.Race;
import org.l2jmobius.gameserver.model.actor.Npc;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.quest.Quest;
import org.l2jmobius.gameserver.model.quest.QuestState;
import org.l2jmobius.gameserver.model.quest.State;

import quests.Q10420_TheVarkaSilenosSupporters.Q10420_TheVarkaSilenosSupporters;

/**
 * Assassination of the Varka Silenos Commander (10421)
 * @author St3eT
 */
public class Q10421_AssassinationOfTheVarkaSilenosCommander extends Quest
{
	// NPCs
	private static final int HANSEN = 33853;
	private static final int COMMANDER_MOS = 27502;
	// Items
	private static final int EAS = 26353; // Scroll: Enchant Armor (S-grade)
	// Misc
	private static final int MIN_LEVEL = 76;
	
	public Q10421_AssassinationOfTheVarkaSilenosCommander()
	{
		super(10421);
		addStartNpc(HANSEN);
		addTalkId(HANSEN);
		addKillId(COMMANDER_MOS);
		addCondNotRace(Race.ERTHEIA, "33853-08.html");
		addCondInCategory(CategoryType.FIGHTER_GROUP, "33853-09.htm");
		addCondMinLevel(MIN_LEVEL, "33853-09.htm");
		addCondCompletedQuest(Q10420_TheVarkaSilenosSupporters.class.getSimpleName(), "33853-09.htm");
	}
	
	@Override
	public String onEvent(String event, Npc npc, Player player)
	{
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return null;
		}
		
		String htmltext = null;
		switch (event)
		{
			case "33853-02.htm":
			case "33853-03.htm":
			{
				htmltext = event;
				break;
			}
			case "33853-04.htm":
			{
				qs.startQuest();
				htmltext = event;
				break;
			}
			case "reward_9546":
			case "reward_9547":
			case "reward_9548":
			case "reward_9549":
			case "reward_9550":
			case "reward_9551":
			{
				if (qs.isCond(2))
				{
					final int stoneId = Integer.parseInt(event.replaceAll("reward_", ""));
					qs.exitQuest(false, true);
					giveItems(player, stoneId, 15);
					giveItems(player, EAS, 2);
					giveStoryQuestReward(player, 30);
					if (player.getLevel() >= MIN_LEVEL)
					{
						addExpAndSp(player, 7_665_840, 1839);
					}
					htmltext = "33853-07.html";
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onTalk(Npc npc, Player player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = null;
		switch (qs.getState())
		{
			case State.CREATED:
			{
				htmltext = "33853-01.htm";
				break;
			}
			case State.STARTED:
			{
				htmltext = qs.isCond(1) ? "33853-05.html" : "33853-06.html";
				break;
			}
			case State.COMPLETED:
			{
				htmltext = getAlreadyCompletedMsg(player);
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(Npc npc, Player killer, boolean isSummon)
	{
		final QuestState qs = getQuestState(killer, false);
		if ((qs != null) && qs.isCond(1))
		{
			qs.setCond(2, true);
		}
		return super.onKill(npc, killer, isSummon);
	}
}