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
package quests.Q10789_LettersFromTheQueenSwampOfScreams;

import org.l2jmobius.gameserver.model.Location;
import org.l2jmobius.gameserver.model.actor.Npc;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.quest.QuestState;
import org.l2jmobius.gameserver.network.NpcStringId;
import org.l2jmobius.gameserver.network.serverpackets.ExShowScreenMessage;

import quests.LetterQuest;

/**
 * Letters from the Queen: Swamp of Screams (10789)
 * @author malyelfik
 */
public class Q10789_LettersFromTheQueenSwampOfScreams extends LetterQuest
{
	// NPCs
	private static final int INNOCENTIN = 31328;
	private static final int DOKARA = 33847;
	// Items
	private static final int SOE_RUNE = 39580;
	private static final int SOE_SWAMP_OF_SCREAMS = 39581;
	private static final int ENCHANT_WEAPON_A = 26350;
	// Location
	private static final Location TELEPORT_LOC = new Location(36563, -49178, -1128);
	// Misc
	private static final int MIN_LEVEL = 65;
	private static final int MAX_LEVEL = 69;
	
	public Q10789_LettersFromTheQueenSwampOfScreams()
	{
		super(10789);
		addTalkId(INNOCENTIN, DOKARA);
		setIsErtheiaQuest(true);
		setLevel(MIN_LEVEL, MAX_LEVEL);
		setStartQuestSound("Npcdialog1.serenia_quest_9");
		setStartLocation(SOE_RUNE, TELEPORT_LOC);
		registerQuestItems(SOE_RUNE, SOE_SWAMP_OF_SCREAMS);
	}
	
	@Override
	public String onEvent(String event, Npc npc, Player player)
	{
		String htmltext = null;
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return htmltext;
		}
		
		switch (event)
		{
			case "31328-02.html":
			case "33847-02.html":
			{
				htmltext = event;
				break;
			}
			case "31328-03.html":
			{
				if (qs.isCond(1))
				{
					qs.setCond(2, true);
					giveItems(player, SOE_SWAMP_OF_SCREAMS, 1);
					htmltext = event;
				}
				break;
			}
			case "33847-03.html":
			{
				if (qs.isCond(2))
				{
					giveItems(player, ENCHANT_WEAPON_A, 2);
					giveStoryQuestReward(player, 91);
					addExpAndSp(player, 942690, 226);
					showOnScreenMsg(player, NpcStringId.GROW_STRONGER_HERE_UNTIL_YOU_RECEIVE_THE_NEXT_LETTER_FROM_QUEEN_NAVARI_AT_LV_70, ExShowScreenMessage.TOP_CENTER, 8000);
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
		final QuestState qs = getQuestState(player, false);
		String htmltext = getNoQuestMsg(player);
		if (qs == null)
		{
			return htmltext;
		}
		
		if (qs.isStarted())
		{
			if (npc.getId() == INNOCENTIN)
			{
				htmltext = (qs.isCond(1)) ? "31328-01.html" : "31328-04.html";
			}
			else if (qs.isCond(2))
			{
				htmltext = "33847-01.html";
			}
		}
		return htmltext;
	}
	
	@Override
	public boolean canShowTutorialMark(Player player)
	{
		return !player.isMageClass();
	}
}