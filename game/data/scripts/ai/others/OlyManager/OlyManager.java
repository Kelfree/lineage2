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
package ai.others.OlyManager;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.l2jmobius.Config;
import org.l2jmobius.gameserver.data.xml.MultisellData;
import org.l2jmobius.gameserver.enums.CategoryType;
import org.l2jmobius.gameserver.enums.ClassId;
import org.l2jmobius.gameserver.handler.BypassHandler;
import org.l2jmobius.gameserver.handler.IBypassHandler;
import org.l2jmobius.gameserver.model.Location;
import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.actor.Npc;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.olympiad.CompetitionType;
import org.l2jmobius.gameserver.model.olympiad.Olympiad;
import org.l2jmobius.gameserver.model.olympiad.OlympiadGameManager;
import org.l2jmobius.gameserver.model.olympiad.OlympiadGameTask;
import org.l2jmobius.gameserver.model.olympiad.OlympiadManager;
import org.l2jmobius.gameserver.network.SystemMessageId;
import org.l2jmobius.gameserver.network.serverpackets.ExOlympiadMatchList;

import ai.AbstractNpcAI;

/**
 * Olympiad Manager AI.
 * @author St3eT
 */
public class OlyManager extends AbstractNpcAI implements IBypassHandler
{
	private static final Logger LOGGER = Logger.getLogger(OlyManager.class.getName());
	
	// NPC
	private static final int MANAGER = 31688;
	// Misc
	private static final Map<CategoryType, Integer> EQUIPMENT_MULTISELL = new EnumMap<>(CategoryType.class);
	static
	{
		EQUIPMENT_MULTISELL.put(CategoryType.SIXTH_SIGEL_GROUP, 917);
		EQUIPMENT_MULTISELL.put(CategoryType.SIXTH_TIR_GROUP, 918);
		EQUIPMENT_MULTISELL.put(CategoryType.SIXTH_OTHEL_GROUP, 919);
		EQUIPMENT_MULTISELL.put(CategoryType.SIXTH_YR_GROUP, 920);
		EQUIPMENT_MULTISELL.put(CategoryType.SIXTH_FEOH_GROUP, 921);
		EQUIPMENT_MULTISELL.put(CategoryType.SIXTH_IS_GROUP, 923);
		EQUIPMENT_MULTISELL.put(CategoryType.SIXTH_WYNN_GROUP, 922);
		EQUIPMENT_MULTISELL.put(CategoryType.SIXTH_EOLH_GROUP, 924);
	}
	
	private static final String[] BYPASSES =
	{
		"watchmatch",
		"arenachange"
	};
	
	private OlyManager()
	{
		if (Config.OLYMPIAD_ENABLED)
		{
			addStartNpc(MANAGER);
			addFirstTalkId(MANAGER);
			addTalkId(MANAGER);
			BypassHandler.getInstance().registerHandler(this);
		}
	}
	
	@Override
	public String onEvent(String event, Npc npc, Player player)
	{
		String htmltext = null;
		switch (event)
		{
			case "OlyManager-info.html":
			case "OlyManager-infoHistory.html":
			case "OlyManager-infoRules.html":
			case "OlyManager-infoPoints.html":
			case "OlyManager-infoPointsCalc.html":
			case "OlyManager-rank.html":
			case "OlyManager-rewards.html":
			{
				htmltext = event;
				break;
			}
			case "index":
			{
				htmltext = onFirstTalk(npc, player);
				break;
			}
			case "joinMatch":
			{
				if (OlympiadManager.getInstance().isRegistered(player))
				{
					htmltext = "OlyManager-registred.html";
				}
				else
				{
					switch (LocalDate.now().get(WeekFields.of(DayOfWeek.MONDAY, 7).weekOfMonth()))
					{
						case 1:
						case 2:
						case 3: // First 3 weeks of month is 1v1 + 1v1 class matches
						{
							htmltext = getHtm(player, "OlyManager-joinMatch.html");
							break;
						}
						default:// Rest is only 1v1 class matches
						{
							htmltext = getHtm(player, "OlyManager-joinMatchClass.html");
							break;
						}
					}
					
					htmltext = htmltext.replace("%olympiad_round%", String.valueOf(Olympiad.getInstance().getPeriod()));
					htmltext = htmltext.replace("%olympiad_week%", String.valueOf(Olympiad.getInstance().getCurrentCycle()));
					htmltext = htmltext.replace("%olympiad_participant%", String.valueOf(OlympiadManager.getInstance().getCountOpponents()));
				}
				break;
			}
			case "register1v1":
			case "register1v1class":
			{
				if (player.isSubClassActive())
				{
					htmltext = "OlyManager-subclass.html";
				}
				else if (!player.isInCategory(CategoryType.SIXTH_CLASS_GROUP))
				{
					htmltext = "OlyManager-awaken.html";
				}
				else if (Olympiad.getInstance().getNoblePoints(player) <= 0)
				{
					htmltext = "OlyManager-noPoints.html";
				}
				else if (!player.isInventoryUnder80(false))
				{
					player.sendPacket(SystemMessageId.UNABLE_TO_PROCESS_THIS_REQUEST_UNTIL_YOUR_INVENTORY_S_WEIGHT_AND_SLOT_COUNT_ARE_LESS_THAN_80_PERCENT_OF_CAPACITY);
				}
				else if (event.equals("register1v1"))
				{
					OlympiadManager.getInstance().registerNoble(player, CompetitionType.NON_CLASSED);
				}
				else
				{
					OlympiadManager.getInstance().registerNoble(player, CompetitionType.CLASSED);
				}
				break;
			}
			case "unregister":
			{
				OlympiadManager.getInstance().unRegisterNoble(player);
				break;
			}
			case "calculatePoints":
			{
				if (player.getVariables().getInt(Olympiad.UNCLAIMED_OLYMPIAD_POINTS_VAR, 0) > 0)
				{
					htmltext = "OlyManager-calculateEnough.html";
				}
				else
				{
					htmltext = "OlyManager-calculateNoEnough.html";
				}
				break;
			}
			case "calculatePointsDone":
			{
				if (player.isInventoryUnder80(false))
				{
					final int tradePoints = player.getVariables().getInt(Olympiad.UNCLAIMED_OLYMPIAD_POINTS_VAR, 0);
					if (tradePoints > 0)
					{
						player.getVariables().remove(Olympiad.UNCLAIMED_OLYMPIAD_POINTS_VAR);
						giveItems(player, Config.OLYMPIAD_COMP_RITEM, tradePoints * Config.OLYMPIAD_MARK_PER_POINT);
					}
				}
				else
				{
					player.sendPacket(SystemMessageId.UNABLE_TO_PROCESS_THIS_REQUEST_UNTIL_YOUR_INVENTORY_S_WEIGHT_AND_SLOT_COUNT_ARE_LESS_THAN_80_PERCENT_OF_CAPACITY);
				}
				break;
			}
			case "showEquipmentReward":
			{
				int multisellId = -1;
				if (player.getClassId() == ClassId.SAYHA_SEER)
				{
					multisellId = 926;
				}
				else if (player.getClassId() == ClassId.EVISCERATOR)
				{
					multisellId = 925;
				}
				else
				{
					for (Entry<CategoryType, Integer> entry : EQUIPMENT_MULTISELL.entrySet())
					{
						if (player.isInCategory(entry.getKey()))
						{
							multisellId = entry.getValue().intValue();
							break;
						}
					}
				}
				
				if (multisellId > 0)
				{
					MultisellData.getInstance().separateAndSend(multisellId, player, npc, false);
				}
				break;
			}
			case "rank_148": // Sigel Phoenix Knight
			case "rank_149": // Sigel Hell Knight
			case "rank_150": // Sigel Eva's Templar
			case "rank_151": // Sigel Shillien Templar
			case "rank_152": // Tyrr Duelist
			case "rank_153": // Tyrr Dreadnought
			case "rank_154": // Tyrr Titan
			case "rank_155": // Tyrr Grand Khavatari
			case "rank_156": // Tyrr Maestro
			case "rank_157": // Tyrr Doombringer
			case "rank_158": // Othell Adventurer
			case "rank_159": // Othell Wind Rider
			case "rank_160": // Othell Ghost Hunter
			case "rank_161": // Othell Fortune Seeker
			case "rank_162": // Yul Sagittarius
			case "rank_163": // Yul Moonlight Sentinel
			case "rank_164": // Yul Ghost Sentinel
			case "rank_165": // Yul Trickster
			case "rank_166": // Feoh Archmage
			case "rank_167": // Feoh Soultaker
			case "rank_168": // Feoh Mystic Muse
			case "rank_169": // Feoh Storm Screamer
			case "rank_170": // Feoh Soul Hound
			case "rank_171": // Iss Hierophant
			case "rank_172": // Iss Sword Muse
			case "rank_173": // Iss Spectral Dancer
			case "rank_174": // Iss Dominator
			case "rank_175": // Iss Doomcryer
			case "rank_176": // Wynn Arcana Lord
			case "rank_177": // Wynn Elemental Master
			case "rank_178": // Wynn Spectral Master
			case "rank_179": // Aeore Cardinal
			case "rank_180": // Aeore Eva's Saint
			case "rank_181": // Aeore Shillien Saint
			case "rank_188": // Eviscerator
			case "rank_189": // Sayha's Seer
			{
				final int classId = Integer.parseInt(event.replace("rank_", ""));
				final List<String> names = Olympiad.getInstance().getClassLeaderBoard(classId);
				htmltext = getHtm(player, "OlyManager-rankDetail.html");
				int index = 1;
				for (String name : names)
				{
					htmltext = htmltext.replace("%Rank" + index + "%", String.valueOf(index));
					htmltext = htmltext.replace("%Name" + index + "%", name);
					index++;
					if (index > 15)
					{
						break;
					}
				}
				for (; index <= 15; index++)
				{
					htmltext = htmltext.replace("%Rank" + index + "%", "");
					htmltext = htmltext.replace("%Name" + index + "%", "");
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onFirstTalk(Npc npc, Player player)
	{
		String htmltext = null;
		if (!player.isCursedWeaponEquipped())
		{
			htmltext = player.isNoble() ? "OlyManager-noble.html" : "OlyManager-noNoble.html";
		}
		else
		{
			htmltext = "OlyManager-noCursed.html";
		}
		return htmltext;
	}
	
	@Override
	public boolean useBypass(String command, Player player, Creature bypassOrigin)
	{
		try
		{
			final Npc olymanager = player.getLastFolkNPC();
			if (command.startsWith(BYPASSES[0])) // list
			{
				if (!Olympiad.getInstance().inCompPeriod())
				{
					player.sendPacket(SystemMessageId.THE_OLYMPIAD_GAMES_ARE_NOT_CURRENTLY_IN_PROGRESS);
					return false;
				}
				
				player.sendPacket(new ExOlympiadMatchList());
			}
			else if ((olymanager == null) || (olymanager.getId() != MANAGER) || (!player.inObserverMode() && !player.isInsideRadius2D(olymanager, 300)))
			{
				return false;
			}
			else if (OlympiadManager.getInstance().isRegisteredInComp(player))
			{
				player.sendPacket(SystemMessageId.YOU_MAY_NOT_OBSERVE_A_OLYMPIAD_GAMES_MATCH_WHILE_YOU_ARE_ON_THE_WAITING_LIST);
				return false;
			}
			else if (!Olympiad.getInstance().inCompPeriod())
			{
				player.sendPacket(SystemMessageId.THE_OLYMPIAD_GAMES_ARE_NOT_CURRENTLY_IN_PROGRESS);
				return false;
			}
			else if (player.isRegisteredOnEvent())
			{
				player.sendMessage("You can not observe games while registered on an event.");
				return false;
			}
			else
			{
				final int arenaId = Integer.parseInt(command.substring(12).trim());
				final OlympiadGameTask nextArena = OlympiadGameManager.getInstance().getOlympiadTask(arenaId);
				if (nextArena != null)
				{
					final List<Location> spectatorSpawns = nextArena.getStadium().getZone().getSpectatorSpawns();
					if (spectatorSpawns.isEmpty())
					{
						LOGGER.warning(getClass().getSimpleName() + ": Zone: " + nextArena.getStadium().getZone() + " doesn't have specatator spawns defined!");
						return false;
					}
					final Location loc = spectatorSpawns.get(getRandom(spectatorSpawns.size()));
					player.enterOlympiadObserverMode(loc, arenaId);
				}
			}
			return true;
		}
		catch (Exception e)
		{
			LOGGER.log(Level.WARNING, "Exception in " + getClass().getSimpleName(), e);
		}
		return false;
	}
	
	@Override
	public String[] getBypassList()
	{
		return BYPASSES;
	}
	
	public static void main(String[] args)
	{
		new OlyManager();
	}
}