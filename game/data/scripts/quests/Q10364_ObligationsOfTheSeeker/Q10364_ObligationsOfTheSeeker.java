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
package quests.Q10364_ObligationsOfTheSeeker;

import org.l2jmobius.gameserver.model.actor.Npc;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.quest.Quest;
import org.l2jmobius.gameserver.model.quest.QuestState;
import org.l2jmobius.gameserver.model.quest.State;
import org.l2jmobius.gameserver.network.NpcStringId;
import org.l2jmobius.gameserver.network.serverpackets.ExShowScreenMessage;

import quests.Q10363_RequestOfTheSeeker.Q10363_RequestOfTheSeeker;

/**
 * Obligations of the Seeker (10364)
 * @author Gladicek
 */
public class Q10364_ObligationsOfTheSeeker extends Quest
{
	// NPCs
	private static final int CELIN = 33451;
	private static final int WALTER = 33452;
	private static final int DEP = 33453;
	private static final int KRAPHER = 22996;
	private static final int AVIAN = 22994;
	// Items
	private static final int DIRTY_PAPER_PIECES = 17578;
	private static final int LEATHER_SHOES = 37;
	private static final int HEALING_POTION = 1060;
	// Misc
	private static final int MIN_LEVEL = 14;
	private static final int MAX_LEVEL = 25;
	
	public Q10364_ObligationsOfTheSeeker()
	{
		super(10364);
		addStartNpc(CELIN);
		addTalkId(CELIN, WALTER, DEP);
		addKillId(KRAPHER, AVIAN);
		registerQuestItems(DIRTY_PAPER_PIECES);
		addCondMinLevel(MIN_LEVEL, "33451-04.html");
		addCondMaxLevel(MAX_LEVEL, "33451-04.html");
		addCondCompletedQuest(Q10363_RequestOfTheSeeker.class.getSimpleName(), "33451-04.html");
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
			case "33451-02.htm":
			case "33452-02.html":
			case "33452-03.html":
			case "33453-02.html":
			case "33453-03.html":
			{
				htmltext = event;
				break;
			}
			case "33451-03.html":
			{
				qs.startQuest();
				htmltext = event;
				break;
			}
			case "33452-04.html":
			{
				if (qs.isCond(1))
				{
					qs.setCond(2, true);
					htmltext = event;
					break;
				}
				break;
			}
			case "33453-04.html":
			{
				if (qs.isCond(3))
				{
					giveItems(player, LEATHER_SHOES, 1);
					giveAdena(player, 550, true);
					giveItems(player, HEALING_POTION, 50);
					addExpAndSp(player, 95000, 22);
					qs.exitQuest(false, true);
					htmltext = event;
					break;
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(Npc npc, Player killer, boolean isSummon)
	{
		final QuestState qs = getQuestState(killer, false);
		if ((qs != null) && qs.isCond(2))
		{
			if (giveItemRandomly(killer, npc, DIRTY_PAPER_PIECES, 1, 5, 0.5, true))
			{
				qs.setCond(3);
				showOnScreenMsg(killer, NpcStringId.USE_THE_YE_SAGIRA_TELEPORT_DEVICE_TO_GO_TO_EXPLORATION_AREA_4, ExShowScreenMessage.TOP_CENTER, 4500);
			}
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public String onTalk(Npc npc, Player player)
	{
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		switch (qs.getState())
		{
			case State.CREATED:
			{
				if (npc.getId() == CELIN)
				{
					htmltext = "33451-01.htm";
					break;
				}
				break;
			}
			case State.STARTED:
			{
				switch (npc.getId())
				{
					case CELIN:
					{
						if (qs.isCond(1))
						{
							htmltext = "33451-03.html";
							break;
						}
						break;
					}
					case WALTER:
					{
						switch (qs.getCond())
						{
							case 1:
							{
								htmltext = "33452-01.html";
								break;
							}
							case 2:
							{
								htmltext = "33452-05.html";
								break;
							}
							case 3:
							{
								htmltext = "33452-06.html";
								break;
							}
						}
						break;
					}
					case DEP:
					{
						if (qs.isCond(3))
						{
							htmltext = "33453-01.html";
							break;
						}
					}
						break;
				}
				break;
			}
			case State.COMPLETED:
			{
				switch (npc.getId())
				{
					case CELIN:
					{
						htmltext = "33451-05.html";
						break;
					}
					case WALTER:
					{
						htmltext = "33452-07.html";
						break;
					}
					case DEP:
					{
						htmltext = "33453-05.html";
						break;
					}
				}
				break;
			}
		}
		return htmltext;
	}
}