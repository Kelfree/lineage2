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
package handlers.targethandlers;

import org.l2jmobius.gameserver.geoengine.GeoEngine;
import org.l2jmobius.gameserver.handler.ITargetTypeHandler;
import org.l2jmobius.gameserver.model.WorldObject;
import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.skill.Skill;
import org.l2jmobius.gameserver.model.skill.targets.TargetType;
import org.l2jmobius.gameserver.network.SystemMessageId;

/**
 * Target enemy or ally if force attacking.
 * @author Nik
 */
public class Enemy implements ITargetTypeHandler
{
	@Override
	public Enum<TargetType> getTargetType()
	{
		return TargetType.ENEMY;
	}
	
	@Override
	public WorldObject getTarget(Creature creature, WorldObject selectedTarget, Skill skill, boolean forceUse, boolean dontMove, boolean sendMessage)
	{
		if (selectedTarget == null)
		{
			return null;
		}
		
		if (!selectedTarget.isCreature())
		{
			return null;
		}
		final Creature target = selectedTarget.asCreature();
		
		// You cannot attack yourself even with force.
		if (creature == target)
		{
			if (sendMessage)
			{
				creature.sendPacket(SystemMessageId.INVALID_TARGET);
			}
			return null;
		}
		
		// You cannot attack dead targets.
		if (target.isDead() && !skill.isStayAfterDeath())
		{
			if (sendMessage)
			{
				creature.sendPacket(SystemMessageId.INVALID_TARGET);
			}
			return null;
		}
		
		// Doors do not care about force attack.
		if (target.isDoor() && !target.isAutoAttackable(creature))
		{
			if (sendMessage)
			{
				creature.sendPacket(SystemMessageId.INVALID_TARGET);
			}
			return null;
		}
		
		// Monsters can attack/be attacked anywhere. Players can attack creatures that aren't autoattackable with force attack.
		if (target.isAutoAttackable(creature) || forceUse)
		{
			// Check for cast range if character cannot move. TODO: char will start follow until within castrange, but if his moving is blocked by geodata, this msg will be sent.
			if (dontMove && (creature.calculateDistance2D(target) > skill.getCastRange()))
			{
				if (sendMessage)
				{
					creature.sendPacket(SystemMessageId.THE_DISTANCE_IS_TOO_FAR_AND_SO_THE_CASTING_HAS_BEEN_STOPPED);
				}
				return null;
			}
			
			// Geodata check when character is within range.
			if (!GeoEngine.getInstance().canSeeTarget(creature, target) || (skill.isFlyType() && !GeoEngine.getInstance().canMoveToTarget(creature, target)))
			{
				if (sendMessage)
				{
					creature.sendPacket(SystemMessageId.CANNOT_SEE_TARGET);
				}
				return null;
			}
			
			// Skills with this target type cannot be used by playables on playables in peace zone, but can be used by and on NPCs.
			if (target.isInsidePeaceZone(creature))
			{
				if (sendMessage)
				{
					creature.sendPacket(SystemMessageId.A_MALICIOUS_SKILL_CANNOT_BE_USED_IN_A_PEACE_ZONE);
				}
				return null;
			}
			
			if (forceUse)
			{
				final Player player = creature.asPlayer();
				final Player targetPlayer = target.asPlayer();
				if ((player != null) && (targetPlayer != null))
				{
					// Siege friend check.
					if (player.isSiegeFriend(target))
					{
						if (sendMessage)
						{
							creature.sendPacket(SystemMessageId.FORCE_ATTACK_IS_IMPOSSIBLE_AGAINST_A_TEMPORARY_ALLIED_MEMBER_DURING_A_SIEGE);
						}
						return null;
					}
				}
			}
			
			return target;
		}
		
		if (sendMessage)
		{
			creature.sendPacket(SystemMessageId.INVALID_TARGET);
		}
		
		return null;
	}
}
