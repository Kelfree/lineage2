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
package quests.Q10324_FindingMagisterGallint;

import org.l2jmobius.gameserver.model.actor.Npc;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.quest.Quest;
import org.l2jmobius.gameserver.model.quest.QuestState;
import org.l2jmobius.gameserver.model.quest.State;
import org.l2jmobius.gameserver.network.serverpackets.TutorialShowHtml;

import quests.Q10323_TrainLikeItsReal.Q10323_TrainLikeItsReal;

/**
 * Finding Magister Gallint (10324)
 * @author ivantotov, Gladicek
 */
public class Q10324_FindingMagisterGallint extends Quest
{
	// NPCs
	private static final int SHANNON = 32974;
	private static final int GALLINT = 32980;
	// Misc
	private static final int MAX_LEVEL = 20;
	
	public Q10324_FindingMagisterGallint()
	{
		super(10324);
		addStartNpc(SHANNON);
		addTalkId(SHANNON, GALLINT);
		addCondMaxLevel(MAX_LEVEL, "32974-01a.html");
		addCondCompletedQuest(Q10323_TrainLikeItsReal.class.getSimpleName(), "32974-01a.html");
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
			case "32974-03.html":
			{
				qs.startQuest();
				htmltext = event;
				break;
			}
			case "32974-02.htm":
			{
				htmltext = event;
				break;
			}
			case "32980-02.html":
			{
				if (qs.isStarted())
				{
					player.sendPacket(new TutorialShowHtml(npc.getObjectId(), "..\\L2Text\\QT_004_skill_01.htm", TutorialShowHtml.LARGE_WINDOW));
					giveAdena(player, 110, true);
					addExpAndSp(player, 3100, 5);
					qs.exitQuest(false, true);
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
		final QuestState qs = getQuestState(player, true);
		String htmltext = null;
		switch (qs.getState())
		{
			case State.CREATED:
			{
				htmltext = npc.getId() == SHANNON ? "32974-01.htm" : "32980-04.html";
				break;
			}
			case State.STARTED:
			{
				htmltext = npc.getId() == SHANNON ? "32974-04.html" : "32980-01.html";
				break;
			}
			case State.COMPLETED:
			{
				htmltext = npc.getId() == SHANNON ? "32974-05.html" : "32980-03.html";
				break;
			}
		}
		return htmltext;
	}
}