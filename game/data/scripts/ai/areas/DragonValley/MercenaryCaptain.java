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
package ai.areas.DragonValley;

import org.l2jmobius.gameserver.enums.ChatType;
import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.actor.Npc;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.network.NpcStringId;
import org.l2jmobius.gameserver.network.serverpackets.NpcSay;

import ai.AbstractNpcAI;

/**
 * Mercenary Captain AI.
 * @author Mobius
 */
public class MercenaryCaptain extends AbstractNpcAI
{
	// NPC
	private static final int MERCENARY_CAPTAIN = 33970;
	
	private MercenaryCaptain()
	{
		addCreatureSeeId(MERCENARY_CAPTAIN);
	}
	
	@Override
	public String onEvent(String event, Npc npc, Player player)
	{
		if (event.equals("BROADCAST_TEXT") && (npc != null))
		{
			npc.broadcastPacket(new NpcSay(npc.getObjectId(), ChatType.NPC_GENERAL, npc.getId(), NpcStringId.THE_SOUTHERN_PART_OF_DRAGON_VALLEY_IS_MUCH_MORE_DANGEROUS_THAN_THE_NORTH_BE_CAREFUL));
		}
		return super.onEvent(event, npc, player);
	}
	
	@Override
	public String onCreatureSee(Npc npc, Creature creature)
	{
		if (creature.isPlayer())
		{
			startQuestTimer("BROADCAST_TEXT", 3000, npc, null, true);
		}
		return super.onCreatureSee(npc, creature);
	}
	
	public static void main(String[] args)
	{
		new MercenaryCaptain();
	}
}