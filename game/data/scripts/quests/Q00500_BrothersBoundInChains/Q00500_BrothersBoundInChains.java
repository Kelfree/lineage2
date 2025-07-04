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
package quests.Q00500_BrothersBoundInChains;

import org.l2jmobius.gameserver.data.xml.SkillData;
import org.l2jmobius.gameserver.enums.QuestType;
import org.l2jmobius.gameserver.enums.SkillFinishType;
import org.l2jmobius.gameserver.model.actor.Npc;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.events.Containers;
import org.l2jmobius.gameserver.model.events.EventType;
import org.l2jmobius.gameserver.model.events.ListenerRegisterType;
import org.l2jmobius.gameserver.model.events.annotations.RegisterEvent;
import org.l2jmobius.gameserver.model.events.annotations.RegisterType;
import org.l2jmobius.gameserver.model.events.impl.creature.npc.OnAttackableKill;
import org.l2jmobius.gameserver.model.events.impl.creature.player.OnPlayerSummonAgathion;
import org.l2jmobius.gameserver.model.events.impl.creature.player.OnPlayerUnsummonAgathion;
import org.l2jmobius.gameserver.model.events.listeners.ConsumerEventListener;
import org.l2jmobius.gameserver.model.quest.Quest;
import org.l2jmobius.gameserver.model.quest.QuestState;
import org.l2jmobius.gameserver.model.quest.State;
import org.l2jmobius.gameserver.model.skill.Skill;

/**
 * Brothers Bound in Chains (500)
 * @author Mathael, Mobius
 * @URL https://l2wiki.com/Brothers_Bound_in_Chains
 */
public class Q00500_BrothersBoundInChains extends Quest
{
	// NPC
	private static final int DARK_JUDGE = 30981;
	// Items
	private static final int GEMSTONE_B = 2132;
	private static final int PENITENT_MANACLES = 36060;
	private static final int CRUMBS_OF_PENITENCE = 36077;
	// Skill
	private static final int HOUR_OF_PENITENCE = 15325;
	// Agathion
	private static final int SIN_EATER = 16098;
	// Others
	private static final int DROP_CHANCE = 5;
	private static final int MIN_LEVEL = 61;
	
	public Q00500_BrothersBoundInChains()
	{
		super(500);
		addStartNpc(DARK_JUDGE);
		addTalkId(DARK_JUDGE);
		registerQuestItems(PENITENT_MANACLES, CRUMBS_OF_PENITENCE);
		addCondMinLevel(MIN_LEVEL, "30981-nopk.htm");
		
		Containers.Global().addListener(new ConsumerEventListener(Containers.Global(), EventType.ON_PLAYER_SUMMON_AGATHION, (OnPlayerSummonAgathion event) -> onPlayerSummonAgathion(event), this));
		Containers.Global().addListener(new ConsumerEventListener(Containers.Global(), EventType.ON_PLAYER_UNSUMMON_AGATHION, (OnPlayerUnsummonAgathion event) -> onPlayerUnsummonAgathion(event), this));
	}
	
	@Override
	public String onEvent(String event, Npc npc, Player player)
	{
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return getNoQuestMsg(player);
		}
		
		switch (event)
		{
			case "buff":
			{
				if ((player != null) && (player.getAgathionId() == SIN_EATER))
				{
					final Skill skill = SkillData.getInstance().getSkill(HOUR_OF_PENITENCE, 1); // Hour of Penitence
					skill.activateSkill(player, player);
					startQuestTimer("buff", 270000, null, player); // Rebuff every 4min30 (retail like)
				}
				return null;
			}
			case "30981-02.htm":
			case "30981-03.htm":
			{
				break;
			}
			case "30981-04.htm":
			{
				if (getQuestItemsCount(player, GEMSTONE_B) >= 200)
				{
					takeItems(player, GEMSTONE_B, 200);
					giveItems(player, PENITENT_MANACLES, 1);
				}
				else
				{
					return "30981-05.html";
				}
				break;
			}
			case "30981-06.htm":
			{
				qs.startQuest();
				break;
			}
			case "30981-09.html": // not retail html.
			{
				if (getQuestItemsCount(player, CRUMBS_OF_PENITENCE) >= 10)
				{
					takeItems(player, CRUMBS_OF_PENITENCE, -1);
					player.setPkKills(Math.max(0, player.getPkKills() - getRandom(1, 10)));
					qs.exitQuest(QuestType.DAILY, true);
				}
				else
				{
					// If player delete QuestItems: Need check how it work on retail.
					qs.setCond(1);
					return "30981-07.html";
				}
				break;
			}
			default:
			{
				return getNoQuestMsg(player);
			}
		}
		
		return event;
	}
	
	@Override
	public String onTalk(Npc npc, Player talker)
	{
		final QuestState qs = getQuestState(talker, true);
		String htmltext = getNoQuestMsg(talker);
		
		switch (qs.getState())
		{
			case State.CREATED:
			{
				htmltext = (talker.getPkKills() > 0) && (talker.getReputation() >= 0) ? "30981-01.htm" : "30981-nopk.htm";
				break;
			}
			case State.STARTED:
			{
				switch (qs.getCond())
				{
					case 1:
					{
						htmltext = "30981-07.html";
						break;
					}
					case 2:
					{
						htmltext = "30981-08.html"; // not retail html.
						break;
					}
				}
				break;
			}
			case State.COMPLETED:
			{
				if (qs.isNowAvailable())
				{
					qs.setState(State.CREATED);
					htmltext = "30981-01.htm";
				}
				break;
			}
		}
		return htmltext;
	}
	
	private void onPlayerSummonAgathion(OnPlayerSummonAgathion event)
	{
		if (event.getAgathionId() != SIN_EATER)
		{
			return;
		}
		final Player player = event.getPlayer();
		if (player == null)
		{
			return;
		}
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return;
		}
		
		startQuestTimer("buff", 2500, null, player);
	}
	
	private void onPlayerUnsummonAgathion(OnPlayerUnsummonAgathion event)
	{
		if (event.getAgathionId() != SIN_EATER)
		{
			return;
		}
		final Player player = event.getPlayer();
		if (player == null)
		{
			return;
		}
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return;
		}
		
		cancelQuestTimer("buff", null, player);
		player.getEffectList().stopSkillEffects(SkillFinishType.REMOVED, HOUR_OF_PENITENCE);
	}
	
	@RegisterEvent(EventType.ON_ATTACKABLE_KILL)
	@RegisterType(ListenerRegisterType.GLOBAL_MONSTERS)
	public void onAttackableKill(OnAttackableKill event)
	{
		final Player player = event.getAttacker();
		if ((player == null) || (player.getAgathionId() != SIN_EATER) || !player.getEffectList().isAffectedBySkill(HOUR_OF_PENITENCE))
		{
			return;
		}
		final QuestState qs = getQuestState(player, false);
		if (qs == null)
		{
			return;
		}
		
		if (getRandom(100) < DROP_CHANCE)
		{
			// Player can drop more than 10 Crumbs of Penitence but there's no point in getting more than 10 (retail).
			giveItems(player, CRUMBS_OF_PENITENCE, 1);
			if (!qs.isCond(2) && (getQuestItemsCount(player, CRUMBS_OF_PENITENCE) >= 10))
			{
				qs.setCond(2, true);
			}
		}
	}
}
