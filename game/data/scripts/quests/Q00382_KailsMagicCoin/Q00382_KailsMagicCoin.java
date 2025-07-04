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
package quests.Q00382_KailsMagicCoin;

import java.util.HashMap;
import java.util.Map;

import org.l2jmobius.gameserver.model.actor.Npc;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.holders.ItemChanceHolder;
import org.l2jmobius.gameserver.model.quest.Quest;
import org.l2jmobius.gameserver.model.quest.QuestState;
import org.l2jmobius.gameserver.util.Util;

/**
 * Kail's Magic Coin (382)
 * @author Sdw, jurchicks
 */
public class Q00382_KailsMagicCoin extends Quest
{
	// NPCs
	private static final int VERGARA = 30687;
	// Monsters
	private static final int FALLEN_ORC = 21017;
	private static final int FALLEN_ORC_ARCHER = 21019;
	private static final int FALLEN_ORC_SHAMAN = 21020;
	private static final int FALLEN_ORC_CAPTAIN = 21022;
	// Items
	private static final int ROYAL_MEMBERSHIP = 5898;
	private static final int KAILS_SILVER_BASILISK = 5961;
	private static final int KAILS_GOLD_GOLEM = 5962;
	private static final int KAILS_BLOOD_DRAGON = 5963;
	// Drops
	private static final double ORC_CAPTAIN_DROP_CHANCE = 0.069;
	private static final Map<Integer, ItemChanceHolder> MONSTER_DROPS = new HashMap<>();
	static
	{
		MONSTER_DROPS.put(FALLEN_ORC, new ItemChanceHolder(KAILS_SILVER_BASILISK, 0.073));
		MONSTER_DROPS.put(FALLEN_ORC_ARCHER, new ItemChanceHolder(KAILS_GOLD_GOLEM, 0.075));
		MONSTER_DROPS.put(FALLEN_ORC_SHAMAN, new ItemChanceHolder(KAILS_BLOOD_DRAGON, 0.073));
	}
	
	// Misc
	private static final int MIN_LEVEL = 55;
	
	public Q00382_KailsMagicCoin()
	{
		super(382);
		addStartNpc(VERGARA);
		addTalkId(VERGARA);
		addKillId(FALLEN_ORC, FALLEN_ORC_ARCHER, FALLEN_ORC_SHAMAN, FALLEN_ORC_CAPTAIN);
	}
	
	@Override
	public String onEvent(String event, Npc npc, Player player)
	{
		final QuestState qs = getQuestState(player, false);
		String htmltext = null;
		if (qs == null)
		{
			return htmltext;
		}
		
		switch (event)
		{
			case "30687-03.htm":
			{
				if (qs.isCreated())
				{
					qs.startQuest();
					htmltext = event;
				}
				break;
			}
			case "30687-05.htm":
			case "30687-06.htm":
			{
				if (qs.isStarted())
				{
					htmltext = event;
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onTalk(Npc npc, Player talker)
	{
		final QuestState qs = getQuestState(talker, true);
		String htmltext = getNoQuestMsg(talker);
		if (qs.isCreated())
		{
			htmltext = ((talker.getLevel() >= MIN_LEVEL) && hasQuestItems(talker, ROYAL_MEMBERSHIP)) ? "30687-02.htm" : "30687-01.htm";
		}
		else if (qs.isStarted())
		{
			htmltext = "30687-04.htm";
		}
		return htmltext;
	}
	
	@Override
	public String onKill(Npc npc, Player killer, boolean isSummon)
	{
		final QuestState qs = getQuestState(killer, false);
		if ((qs != null) && hasQuestItems(killer, ROYAL_MEMBERSHIP) && Util.checkIfInRange(1500, npc, killer, true))
		{
			if (npc.getId() == FALLEN_ORC_CAPTAIN)
			{
				giveItemRandomly(killer, KAILS_SILVER_BASILISK + getRandom(3), 1, 0, ORC_CAPTAIN_DROP_CHANCE, true);
			}
			else
			{
				final ItemChanceHolder ih = MONSTER_DROPS.get(npc.getId());
				giveItemRandomly(killer, ih.getId(), 1, 0, ih.getChance(), true);
			}
		}
		return super.onKill(npc, killer, isSummon);
	}
}
