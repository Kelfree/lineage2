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
package handlers;

import java.util.logging.Logger;

import org.l2jmobius.gameserver.handler.EffectHandler;

import handlers.effecthandlers.*;

/**
 * Effect Master handler.
 * @author NosBit
 */
public class EffectMasterHandler
{
	private static final Logger LOGGER = Logger.getLogger(EffectMasterHandler.class.getName());
	
	public static void main(String[] args)
	{
		EffectHandler.getInstance().registerHandler("AbnormalShield", AbnormalShield::new);
		EffectHandler.getInstance().registerHandler("AbnormalTimeChange", AbnormalTimeChange::new);
		EffectHandler.getInstance().registerHandler("AbsorbDamage", AbsorbDamage::new);
		EffectHandler.getInstance().registerHandler("Accuracy", Accuracy::new);
		EffectHandler.getInstance().registerHandler("AddHate", AddHate::new);
		EffectHandler.getInstance().registerHandler("AdditionalPotionCp", AdditionalPotionCp::new);
		EffectHandler.getInstance().registerHandler("AdditionalPotionHp", AdditionalPotionHp::new);
		EffectHandler.getInstance().registerHandler("AdditionalPotionMp", AdditionalPotionMp::new);
		EffectHandler.getInstance().registerHandler("AddMaxMagicCriticalRate", AddMaxMagicCriticalRate::new);
		EffectHandler.getInstance().registerHandler("AddMaxPhysicalCriticalRate", AddMaxPhysicalCriticalRate::new);
		EffectHandler.getInstance().registerHandler("AddPcCafePoints", AddPcCafePoints::new);
		EffectHandler.getInstance().registerHandler("AddSkillBySkill", AddSkillBySkill::new);
		EffectHandler.getInstance().registerHandler("AddTeleportBookmarkSlot", AddTeleportBookmarkSlot::new);
		EffectHandler.getInstance().registerHandler("AirBind", AirBind::new);
		EffectHandler.getInstance().registerHandler("AreaDamage", AreaDamage::new);
		EffectHandler.getInstance().registerHandler("AttackAttribute", AttackAttribute::new);
		EffectHandler.getInstance().registerHandler("AttackAttributeAdd", AttackAttributeAdd::new);
		EffectHandler.getInstance().registerHandler("AttackBehind", AttackBehind::new);
		EffectHandler.getInstance().registerHandler("AttackTrait", AttackTrait::new);
		EffectHandler.getInstance().registerHandler("AutoAttackDamageBonus", AutoAttackDamageBonus::new);
		EffectHandler.getInstance().registerHandler("Backstab", Backstab::new);
		EffectHandler.getInstance().registerHandler("Betray", Betray::new);
		EffectHandler.getInstance().registerHandler("Blink", Blink::new);
		EffectHandler.getInstance().registerHandler("BlinkSwap", BlinkSwap::new);
		EffectHandler.getInstance().registerHandler("BlockAbnormalSlot", BlockAbnormalSlot::new);
		EffectHandler.getInstance().registerHandler("BlockAction", BlockAction::new);
		EffectHandler.getInstance().registerHandler("BlockActions", BlockActions::new);
		EffectHandler.getInstance().registerHandler("BlockChat", BlockChat::new);
		EffectHandler.getInstance().registerHandler("BlockControl", BlockControl::new);
		EffectHandler.getInstance().registerHandler("BlockEscape", BlockEscape::new);
		EffectHandler.getInstance().registerHandler("BlockMove", BlockMove::new);
		EffectHandler.getInstance().registerHandler("BlockParty", BlockParty::new);
		EffectHandler.getInstance().registerHandler("BlockResurrection", BlockResurrection::new);
		EffectHandler.getInstance().registerHandler("BlockSkill", BlockSkill::new);
		EffectHandler.getInstance().registerHandler("BlockTarget", BlockTarget::new);
		EffectHandler.getInstance().registerHandler("Bluff", Bluff::new);
		EffectHandler.getInstance().registerHandler("BonusDropAdena", BonusDropAdena::new);
		EffectHandler.getInstance().registerHandler("BonusDropAmount", BonusDropAmount::new);
		EffectHandler.getInstance().registerHandler("BonusDropRate", BonusDropRate::new);
		EffectHandler.getInstance().registerHandler("BonusSpoilRate", BonusSpoilRate::new);
		EffectHandler.getInstance().registerHandler("Breath", Breath::new);
		EffectHandler.getInstance().registerHandler("BuffBlock", BuffBlock::new);
		EffectHandler.getInstance().registerHandler("CallLearnedSkill", CallLearnedSkill::new);
		EffectHandler.getInstance().registerHandler("CallParty", CallParty::new);
		EffectHandler.getInstance().registerHandler("CallPc", CallPc::new);
		EffectHandler.getInstance().registerHandler("CallRandomSkill", CallRandomSkill::new);
		EffectHandler.getInstance().registerHandler("CallSkill", CallSkill::new);
		EffectHandler.getInstance().registerHandler("CallSkillOnActionTime", CallSkillOnActionTime::new);
		EffectHandler.getInstance().registerHandler("CallTargetParty", CallTargetParty::new);
		EffectHandler.getInstance().registerHandler("ChameleonRest", ChameleonRest::new);
		EffectHandler.getInstance().registerHandler("ChangeBody", ChangeBody::new);
		EffectHandler.getInstance().registerHandler("ChangeFace", ChangeFace::new);
		EffectHandler.getInstance().registerHandler("ChangeFishingMastery", ChangeFishingMastery::new);
		EffectHandler.getInstance().registerHandler("ChangeHairColor", ChangeHairColor::new);
		EffectHandler.getInstance().registerHandler("ChangeHairStyle", ChangeHairStyle::new);
		EffectHandler.getInstance().registerHandler("CheapShot", CheapShot::new);
		EffectHandler.getInstance().registerHandler("CheatDeath", CheatDeath::new);
		EffectHandler.getInstance().registerHandler("ClassChange", ClassChange::new);
		EffectHandler.getInstance().registerHandler("Compelling", Compelling::new);
		EffectHandler.getInstance().registerHandler("Confuse", Confuse::new);
		EffectHandler.getInstance().registerHandler("ConsumeBody", ConsumeBody::new);
		EffectHandler.getInstance().registerHandler("ConvertItem", ConvertItem::new);
		EffectHandler.getInstance().registerHandler("CounterPhysicalSkill", CounterPhysicalSkill::new);
		EffectHandler.getInstance().registerHandler("Cp", Cp::new);
		EffectHandler.getInstance().registerHandler("CpHeal", CpHeal::new);
		EffectHandler.getInstance().registerHandler("CpHealOverTime", CpHealOverTime::new);
		EffectHandler.getInstance().registerHandler("CpHealPercent", CpHealPercent::new);
		EffectHandler.getInstance().registerHandler("CpRegen", CpRegen::new);
		EffectHandler.getInstance().registerHandler("CraftingCritical", CraftingCritical::new);
		EffectHandler.getInstance().registerHandler("CraftRate", CraftRate::new);
		EffectHandler.getInstance().registerHandler("CriticalDamage", CriticalDamage::new);
		EffectHandler.getInstance().registerHandler("CriticalDamagePosition", CriticalDamagePosition::new);
		EffectHandler.getInstance().registerHandler("CriticalRate", CriticalRate::new);
		EffectHandler.getInstance().registerHandler("CriticalRatePositionBonus", CriticalRatePositionBonus::new);
		EffectHandler.getInstance().registerHandler("CrystalGradeModify", CrystalGradeModify::new);
		EffectHandler.getInstance().registerHandler("CubicMastery", CubicMastery::new);
		EffectHandler.getInstance().registerHandler("DamageBlock", DamageBlock::new);
		EffectHandler.getInstance().registerHandler("DamageByAttack", DamageByAttack::new);
		EffectHandler.getInstance().registerHandler("DamageShield", DamageShield::new);
		EffectHandler.getInstance().registerHandler("DamageShieldResist", DamageShieldResist::new);
		EffectHandler.getInstance().registerHandler("DamOverTime", DamOverTime::new);
		EffectHandler.getInstance().registerHandler("DamOverTimePercent", DamOverTimePercent::new);
		EffectHandler.getInstance().registerHandler("DeathLink", DeathLink::new);
		EffectHandler.getInstance().registerHandler("DebuffBlock", DebuffBlock::new);
		EffectHandler.getInstance().registerHandler("DefenceAttribute", DefenceAttribute::new);
		EffectHandler.getInstance().registerHandler("DefenceCriticalDamage", DefenceCriticalDamage::new);
		EffectHandler.getInstance().registerHandler("DefenceCriticalRate", DefenceCriticalRate::new);
		EffectHandler.getInstance().registerHandler("DefenceMagicCriticalDamage", DefenceMagicCriticalDamage::new);
		EffectHandler.getInstance().registerHandler("DefenceMagicCriticalRate", DefenceMagicCriticalRate::new);
		EffectHandler.getInstance().registerHandler("DefencePhysicalSkillCriticalDamage", DefencePhysicalSkillCriticalDamage::new);
		EffectHandler.getInstance().registerHandler("DefenceTrait", DefenceTrait::new);
		EffectHandler.getInstance().registerHandler("DeleteHate", DeleteHate::new);
		EffectHandler.getInstance().registerHandler("DeleteHateOfMe", DeleteHateOfMe::new);
		EffectHandler.getInstance().registerHandler("DeleteTopAgro", DeleteTopAgro::new);
		EffectHandler.getInstance().registerHandler("DetectHiddenObjects", DetectHiddenObjects::new);
		EffectHandler.getInstance().registerHandler("Detection", Detection::new);
		EffectHandler.getInstance().registerHandler("DisableSkill", DisableSkill::new);
		EffectHandler.getInstance().registerHandler("DisableTargeting", DisableTargeting::new);
		EffectHandler.getInstance().registerHandler("Disarm", Disarm::new);
		EffectHandler.getInstance().registerHandler("Disarmor", Disarmor::new);
		EffectHandler.getInstance().registerHandler("DispelAll", DispelAll::new);
		EffectHandler.getInstance().registerHandler("DispelByCategory", DispelByCategory::new);
		EffectHandler.getInstance().registerHandler("DispelBySlot", DispelBySlot::new);
		EffectHandler.getInstance().registerHandler("DispelBySlotMyself", DispelBySlotMyself::new);
		EffectHandler.getInstance().registerHandler("DispelBySlotProbability", DispelBySlotProbability::new);
		EffectHandler.getInstance().registerHandler("DoubleCast", DoubleCast::new);
		EffectHandler.getInstance().registerHandler("DragonWeaponDefence", DragonWeaponDefence::new);
		EffectHandler.getInstance().registerHandler("DuelistFury", DuelistFury::new);
		EffectHandler.getInstance().registerHandler("EnableCloak", EnableCloak::new);
		EffectHandler.getInstance().registerHandler("EnchantRate", EnchantRate::new);
		EffectHandler.getInstance().registerHandler("EnergyDamage", EnergyDamage::new);
		EffectHandler.getInstance().registerHandler("EnlargeAbnormalSlot", EnlargeAbnormalSlot::new);
		EffectHandler.getInstance().registerHandler("EnlargeSlot", EnlargeSlot::new);
		EffectHandler.getInstance().registerHandler("Escape", Escape::new);
		EffectHandler.getInstance().registerHandler("ExpModify", ExpModify::new);
		EffectHandler.getInstance().registerHandler("Faceoff", Faceoff::new);
		EffectHandler.getInstance().registerHandler("FakeDeath", FakeDeath::new);
		EffectHandler.getInstance().registerHandler("FatalBlow", FatalBlow::new);
		EffectHandler.getInstance().registerHandler("FatalBlowRate", FatalBlowRate::new);
		EffectHandler.getInstance().registerHandler("FatalBlowRateDefence", FatalBlowRateDefence::new);
		EffectHandler.getInstance().registerHandler("Fear", Fear::new);
		EffectHandler.getInstance().registerHandler("Feed", Feed::new);
		EffectHandler.getInstance().registerHandler("FishingExpSpBonus", FishingExpSpBonus::new);
		EffectHandler.getInstance().registerHandler("Flag", Flag::new);
		EffectHandler.getInstance().registerHandler("FlipBlock", FlipBlock::new);
		EffectHandler.getInstance().registerHandler("FlyAway", FlyAway::new);
		EffectHandler.getInstance().registerHandler("FocusEnergy", FocusEnergy::new);
		EffectHandler.getInstance().registerHandler("FocusMaxMomentum", FocusMaxMomentum::new);
		EffectHandler.getInstance().registerHandler("FocusMomentum", FocusMomentum::new);
		EffectHandler.getInstance().registerHandler("FocusSouls", FocusSouls::new);
		EffectHandler.getInstance().registerHandler("GetAgro", GetAgro::new);
		EffectHandler.getInstance().registerHandler("GetDamageLimit", GetDamageLimit::new);
		EffectHandler.getInstance().registerHandler("GetMomentum", GetMomentum::new);
		EffectHandler.getInstance().registerHandler("GiveClanReputation", GiveClanReputation::new);
		EffectHandler.getInstance().registerHandler("GiveExpAndSp", GiveExpAndSp::new);
		EffectHandler.getInstance().registerHandler("GiveFame", GiveFame::new);
		EffectHandler.getInstance().registerHandler("GiveRecommendation", GiveRecommendation::new);
		EffectHandler.getInstance().registerHandler("GiveSp", GiveSp::new);
		EffectHandler.getInstance().registerHandler("GiveXp", GiveXp::new);
		EffectHandler.getInstance().registerHandler("Grow", Grow::new);
		EffectHandler.getInstance().registerHandler("HairAccessorySet", HairAccessorySet::new);
		EffectHandler.getInstance().registerHandler("Harvesting", Harvesting::new);
		EffectHandler.getInstance().registerHandler("HateAttack", HateAttack::new);
		EffectHandler.getInstance().registerHandler("HeadquarterCreate", HeadquarterCreate::new);
		EffectHandler.getInstance().registerHandler("Heal", Heal::new);
		EffectHandler.getInstance().registerHandler("HealEffect", HealEffect::new);
		EffectHandler.getInstance().registerHandler("HealOverTime", HealOverTime::new);
		EffectHandler.getInstance().registerHandler("HealPercent", HealPercent::new);
		EffectHandler.getInstance().registerHandler("Hide", Hide::new);
		EffectHandler.getInstance().registerHandler("HitNumber", HitNumber::new);
		EffectHandler.getInstance().registerHandler("Hp", Hp::new);
		EffectHandler.getInstance().registerHandler("HpByLevel", HpByLevel::new);
		EffectHandler.getInstance().registerHandler("HpCpHeal", HpCpHeal::new);
		EffectHandler.getInstance().registerHandler("HpCpHealCritical", HpCpHealCritical::new);
		EffectHandler.getInstance().registerHandler("HpDrain", HpDrain::new);
		EffectHandler.getInstance().registerHandler("HpLimit", HpLimit::new);
		EffectHandler.getInstance().registerHandler("HpRegen", HpRegen::new);
		EffectHandler.getInstance().registerHandler("HpToOwner", HpToOwner::new);
		EffectHandler.getInstance().registerHandler("IgnoreDeath", IgnoreDeath::new);
		EffectHandler.getInstance().registerHandler("ImmobileDamageBonus", ImmobileDamageBonus::new);
		EffectHandler.getInstance().registerHandler("ImmobileDamageResist", ImmobileDamageResist::new);
		EffectHandler.getInstance().registerHandler("ImmobilePetBuff", ImmobilePetBuff::new);
		EffectHandler.getInstance().registerHandler("InstantKillResist", InstantKillResist::new);
		EffectHandler.getInstance().registerHandler("JewelSlot", JewelSlot::new);
		EffectHandler.getInstance().registerHandler("KarmaCount", KarmaCount::new);
		EffectHandler.getInstance().registerHandler("KnockBack", KnockBack::new);
		EffectHandler.getInstance().registerHandler("Lethal", Lethal::new);
		EffectHandler.getInstance().registerHandler("LimitCp", LimitCp::new);
		EffectHandler.getInstance().registerHandler("LimitHp", LimitHp::new);
		EffectHandler.getInstance().registerHandler("LimitMp", LimitMp::new);
		EffectHandler.getInstance().registerHandler("Lucky", Lucky::new);
		EffectHandler.getInstance().registerHandler("MagicAccuracy", MagicAccuracy::new);
		EffectHandler.getInstance().registerHandler("MagicalAbnormalDispelDamage", MagicalAbnormalDispelDamage::new);
		EffectHandler.getInstance().registerHandler("MagicalAbnormalResist", MagicalAbnormalResist::new);
		EffectHandler.getInstance().registerHandler("MagicalAttack", MagicalAttack::new);
		EffectHandler.getInstance().registerHandler("MagicalAttackSpeed", MagicalAttackSpeed::new);
		EffectHandler.getInstance().registerHandler("MagicalDamage", MagicalDamage::new);
		EffectHandler.getInstance().registerHandler("MagicalDamageByAbnormal", MagicalDamageByAbnormal::new);
		EffectHandler.getInstance().registerHandler("MagicalDamageByAbnormalSlot", MagicalDamageByAbnormalSlot::new);
		EffectHandler.getInstance().registerHandler("MagicalDamageMp", MagicalDamageMp::new);
		EffectHandler.getInstance().registerHandler("MagicalDamageOverTime", MagicalDamageOverTime::new);
		EffectHandler.getInstance().registerHandler("MagicalDamageRange", MagicalDamageRange::new);
		EffectHandler.getInstance().registerHandler("MagicalDefence", MagicalDefence::new);
		EffectHandler.getInstance().registerHandler("MagicalEvasion", MagicalEvasion::new);
		EffectHandler.getInstance().registerHandler("MagicalSkillPower", MagicalSkillPower::new);
		EffectHandler.getInstance().registerHandler("MagicalSoulDamage", MagicalSoulDamage::new);
		EffectHandler.getInstance().registerHandler("MagicalSuccessResistance", MagicalSuccessResistance::new);
		EffectHandler.getInstance().registerHandler("MagicCriticalDamage", MagicCriticalDamage::new);
		EffectHandler.getInstance().registerHandler("MagicCriticalRate", MagicCriticalRate::new);
		EffectHandler.getInstance().registerHandler("MagicMpCost", MagicMpCost::new);
		EffectHandler.getInstance().registerHandler("ManaCharge", ManaCharge::new);
		EffectHandler.getInstance().registerHandler("ManaDamOverTime", ManaDamOverTime::new);
		EffectHandler.getInstance().registerHandler("ManaHeal", ManaHeal::new);
		EffectHandler.getInstance().registerHandler("ManaHealByLevel", ManaHealByLevel::new);
		EffectHandler.getInstance().registerHandler("ManaHealOverTime", ManaHealOverTime::new);
		EffectHandler.getInstance().registerHandler("ManaHealPercent", ManaHealPercent::new);
		EffectHandler.getInstance().registerHandler("MaxCp", MaxCp::new);
		EffectHandler.getInstance().registerHandler("MaxHp", MaxHp::new);
		EffectHandler.getInstance().registerHandler("MaxMp", MaxMp::new);
		EffectHandler.getInstance().registerHandler("ModifyVital", ModifyVital::new);
		EffectHandler.getInstance().registerHandler("Mp", Mp::new);
		EffectHandler.getInstance().registerHandler("MpConsumePerLevel", MpConsumePerLevel::new);
		EffectHandler.getInstance().registerHandler("MpRegen", MpRegen::new);
		EffectHandler.getInstance().registerHandler("MpShield", MpShield::new);
		EffectHandler.getInstance().registerHandler("MpVampiricAttack", MpVampiricAttack::new);
		EffectHandler.getInstance().registerHandler("Mute", Mute::new);
		EffectHandler.getInstance().registerHandler("NightStatModify", NightStatModify::new);
		EffectHandler.getInstance().registerHandler("NoblesseBless", NoblesseBless::new);
		EffectHandler.getInstance().registerHandler("OpenChest", OpenChest::new);
		EffectHandler.getInstance().registerHandler("OpenCommonRecipeBook", OpenCommonRecipeBook::new);
		EffectHandler.getInstance().registerHandler("OpenDoor", OpenDoor::new);
		EffectHandler.getInstance().registerHandler("OpenDwarfRecipeBook", OpenDwarfRecipeBook::new);
		EffectHandler.getInstance().registerHandler("Passive", Passive::new);
		EffectHandler.getInstance().registerHandler("PhysicalAbnormalResist", PhysicalAbnormalResist::new);
		EffectHandler.getInstance().registerHandler("PhysicalAttack", PhysicalAttack::new);
		EffectHandler.getInstance().registerHandler("PhysicalAttackRange", PhysicalAttackRange::new);
		EffectHandler.getInstance().registerHandler("PhysicalAttackSpeed", PhysicalAttackSpeed::new);
		EffectHandler.getInstance().registerHandler("PhysicalDamage", PhysicalDamage::new);
		EffectHandler.getInstance().registerHandler("PhysicalDamageHpLink", PhysicalDamageHpLink::new);
		EffectHandler.getInstance().registerHandler("PhysicalDamageMute", PhysicalDamageMute::new);
		EffectHandler.getInstance().registerHandler("PhysicalDamageSaveHp", PhysicalDamageSaveHp::new);
		EffectHandler.getInstance().registerHandler("PhysicalDamageWeaponBonus", PhysicalDamageWeaponBonus::new);
		EffectHandler.getInstance().registerHandler("PhysicalDefence", PhysicalDefence::new);
		EffectHandler.getInstance().registerHandler("PhysicalEvasion", PhysicalEvasion::new);
		EffectHandler.getInstance().registerHandler("PhysicalMute", PhysicalMute::new);
		EffectHandler.getInstance().registerHandler("PhysicalShieldAngleAll", PhysicalShieldAngleAll::new);
		EffectHandler.getInstance().registerHandler("PhysicalSkillCriticalDamage", PhysicalSkillCriticalDamage::new);
		EffectHandler.getInstance().registerHandler("PhysicalSkillCriticalRate", PhysicalSkillCriticalRate::new);
		EffectHandler.getInstance().registerHandler("PhysicalSkillPower", PhysicalSkillPower::new);
		EffectHandler.getInstance().registerHandler("PhysicalSoulDamage", PhysicalSoulDamage::new);
		EffectHandler.getInstance().registerHandler("PkCount", PkCount::new);
		EffectHandler.getInstance().registerHandler("Plunder", Plunder::new);
		EffectHandler.getInstance().registerHandler("PolearmSingleTarget", PolearmSingleTarget::new);
		EffectHandler.getInstance().registerHandler("ProtectDeathPenalty", ProtectDeathPenalty::new);
		EffectHandler.getInstance().registerHandler("ProtectionBlessing", ProtectionBlessing::new);
		EffectHandler.getInstance().registerHandler("PullBack", PullBack::new);
		EffectHandler.getInstance().registerHandler("PveMagicalSkillDamageBonus", PveMagicalSkillDamageBonus::new);
		EffectHandler.getInstance().registerHandler("PveMagicalSkillDefenceBonus", PveMagicalSkillDefenceBonus::new);
		EffectHandler.getInstance().registerHandler("PvePhysicalAttackDamageBonus", PvePhysicalAttackDamageBonus::new);
		EffectHandler.getInstance().registerHandler("PvePhysicalAttackDefenceBonus", PvePhysicalAttackDefenceBonus::new);
		EffectHandler.getInstance().registerHandler("PvePhysicalSkillDamageBonus", PvePhysicalSkillDamageBonus::new);
		EffectHandler.getInstance().registerHandler("PvePhysicalSkillDefenceBonus", PvePhysicalSkillDefenceBonus::new);
		EffectHandler.getInstance().registerHandler("PveRaidMagicalSkillDamageBonus", PveRaidMagicalSkillDamageBonus::new);
		EffectHandler.getInstance().registerHandler("PveRaidMagicalSkillDefenceBonus", PveRaidMagicalSkillDefenceBonus::new);
		EffectHandler.getInstance().registerHandler("PveRaidPhysicalAttackDamageBonus", PveRaidPhysicalAttackDamageBonus::new);
		EffectHandler.getInstance().registerHandler("PveRaidPhysicalAttackDefenceBonus", PveRaidPhysicalAttackDefenceBonus::new);
		EffectHandler.getInstance().registerHandler("PveRaidPhysicalSkillDamageBonus", PveRaidPhysicalSkillDamageBonus::new);
		EffectHandler.getInstance().registerHandler("PveRaidPhysicalSkillDefenceBonus", PveRaidPhysicalSkillDefenceBonus::new);
		EffectHandler.getInstance().registerHandler("PvpMagicalSkillDamageBonus", PvpMagicalSkillDamageBonus::new);
		EffectHandler.getInstance().registerHandler("PvpMagicalSkillDefenceBonus", PvpMagicalSkillDefenceBonus::new);
		EffectHandler.getInstance().registerHandler("PvpPhysicalAttackDamageBonus", PvpPhysicalAttackDamageBonus::new);
		EffectHandler.getInstance().registerHandler("PvpPhysicalAttackDefenceBonus", PvpPhysicalAttackDefenceBonus::new);
		EffectHandler.getInstance().registerHandler("PvpPhysicalSkillDamageBonus", PvpPhysicalSkillDamageBonus::new);
		EffectHandler.getInstance().registerHandler("PvpPhysicalSkillDefenceBonus", PvpPhysicalSkillDefenceBonus::new);
		EffectHandler.getInstance().registerHandler("RandomizeHate", RandomizeHate::new);
		EffectHandler.getInstance().registerHandler("RealDamage", RealDamage::new);
		EffectHandler.getInstance().registerHandler("RealDamageResist", RealDamageResist::new);
		EffectHandler.getInstance().registerHandler("RearDamage", RearDamage::new);
		EffectHandler.getInstance().registerHandler("RebalanceHP", RebalanceHP::new);
		EffectHandler.getInstance().registerHandler("RebalanceHPSummon", RebalanceHPSummon::new);
		EffectHandler.getInstance().registerHandler("RecoverVitalityInPeaceZone", RecoverVitalityInPeaceZone::new);
		EffectHandler.getInstance().registerHandler("Recovery", Recovery::new);
		EffectHandler.getInstance().registerHandler("ReduceCancel", ReduceCancel::new);
		EffectHandler.getInstance().registerHandler("ReduceDamage", ReduceDamage::new);
		EffectHandler.getInstance().registerHandler("ReduceDropPenalty", ReduceDropPenalty::new);
		EffectHandler.getInstance().registerHandler("ReflectMagic", ReflectMagic::new);
		EffectHandler.getInstance().registerHandler("ReflectSkill", ReflectSkill::new);
		EffectHandler.getInstance().registerHandler("RefuelAirship", RefuelAirship::new);
		EffectHandler.getInstance().registerHandler("Relax", Relax::new);
		EffectHandler.getInstance().registerHandler("ResetInstanceEntry", ResetInstanceEntry::new);
		EffectHandler.getInstance().registerHandler("ResistAbnormalByCategory", ResistAbnormalByCategory::new);
		EffectHandler.getInstance().registerHandler("ResistDispelByCategory", ResistDispelByCategory::new);
		EffectHandler.getInstance().registerHandler("ResistSkill", ResistSkill::new);
		EffectHandler.getInstance().registerHandler("Restoration", Restoration::new);
		EffectHandler.getInstance().registerHandler("RestorationRandom", RestorationRandom::new);
		EffectHandler.getInstance().registerHandler("Resurrection", Resurrection::new);
		EffectHandler.getInstance().registerHandler("ResurrectionSpecial", ResurrectionSpecial::new);
		EffectHandler.getInstance().registerHandler("Reuse", Reuse::new);
		EffectHandler.getInstance().registerHandler("ReuseSkillById", ReuseSkillById::new);
		EffectHandler.getInstance().registerHandler("Ride", Ride::new);
		EffectHandler.getInstance().registerHandler("Root", Root::new);
		EffectHandler.getInstance().registerHandler("SacrificeSummon", SacrificeSummon::new);
		EffectHandler.getInstance().registerHandler("SafeFallHeight", SafeFallHeight::new);
		EffectHandler.getInstance().registerHandler("SendSystemMessageToClan", SendSystemMessageToClan::new);
		EffectHandler.getInstance().registerHandler("ServitorShare", ServitorShare::new);
		EffectHandler.getInstance().registerHandler("SetCp", SetCp::new);
		EffectHandler.getInstance().registerHandler("SetHp", SetHp::new);
		EffectHandler.getInstance().registerHandler("SetSkill", SetSkill::new);
		EffectHandler.getInstance().registerHandler("ShieldDefence", ShieldDefence::new);
		EffectHandler.getInstance().registerHandler("ShieldDefenceRate", ShieldDefenceRate::new);
		EffectHandler.getInstance().registerHandler("ShotsBonus", ShotsBonus::new);
		EffectHandler.getInstance().registerHandler("SilentMove", SilentMove::new);
		EffectHandler.getInstance().registerHandler("SkillEvasion", SkillEvasion::new);
		EffectHandler.getInstance().registerHandler("SkillMastery", SkillMastery::new);
		EffectHandler.getInstance().registerHandler("SkillMasteryRate", SkillMasteryRate::new);
		EffectHandler.getInstance().registerHandler("SkillPowerAdd", SkillPowerAdd::new);
		EffectHandler.getInstance().registerHandler("SkillTurning", SkillTurning::new);
		EffectHandler.getInstance().registerHandler("SkillTurningOverTime", SkillTurningOverTime::new);
		EffectHandler.getInstance().registerHandler("SoulBlow", SoulBlow::new);
		EffectHandler.getInstance().registerHandler("SoulEating", SoulEating::new);
		EffectHandler.getInstance().registerHandler("Sow", Sow::new);
		EffectHandler.getInstance().registerHandler("Speed", Speed::new);
		EffectHandler.getInstance().registerHandler("SpeedLimit", SpeedLimit::new);
		EffectHandler.getInstance().registerHandler("SphericBarrier", SphericBarrier::new);
		EffectHandler.getInstance().registerHandler("SpModify", SpModify::new);
		EffectHandler.getInstance().registerHandler("Spoil", Spoil::new);
		EffectHandler.getInstance().registerHandler("StatAddForLevel", StatAddForLevel::new);
		EffectHandler.getInstance().registerHandler("StatBonusSkillCritical", StatBonusSkillCritical::new);
		EffectHandler.getInstance().registerHandler("StatBonusSpeed", StatBonusSpeed::new);
		EffectHandler.getInstance().registerHandler("StatByMoveType", StatByMoveType::new);
		EffectHandler.getInstance().registerHandler("StatMulForLevel", StatMulForLevel::new);
		EffectHandler.getInstance().registerHandler("StatUp", StatUp::new);
		EffectHandler.getInstance().registerHandler("StealAbnormal", StealAbnormal::new);
		EffectHandler.getInstance().registerHandler("Summon", Summon::new);
		EffectHandler.getInstance().registerHandler("SummonAgathion", SummonAgathion::new);
		EffectHandler.getInstance().registerHandler("SummonCubic", SummonCubic::new);
		EffectHandler.getInstance().registerHandler("SummonHallucination", SummonHallucination::new);
		EffectHandler.getInstance().registerHandler("SummonMulti", SummonMulti::new);
		EffectHandler.getInstance().registerHandler("SummonNpc", SummonNpc::new);
		EffectHandler.getInstance().registerHandler("SummonPet", SummonPet::new);
		EffectHandler.getInstance().registerHandler("SummonPoints", SummonPoints::new);
		EffectHandler.getInstance().registerHandler("SummonTrap", SummonTrap::new);
		EffectHandler.getInstance().registerHandler("Sweeper", Sweeper::new);
		EffectHandler.getInstance().registerHandler("Synergy", Synergy::new);
		EffectHandler.getInstance().registerHandler("TakeCastle", TakeCastle::new);
		EffectHandler.getInstance().registerHandler("TakeCastleStart", TakeCastleStart::new);
		EffectHandler.getInstance().registerHandler("TakeFort", TakeFort::new);
		EffectHandler.getInstance().registerHandler("TakeFortStart", TakeFortStart::new);
		EffectHandler.getInstance().registerHandler("TalismanSlot", TalismanSlot::new);
		EffectHandler.getInstance().registerHandler("TargetCancel", TargetCancel::new);
		EffectHandler.getInstance().registerHandler("TargetMe", TargetMe::new);
		EffectHandler.getInstance().registerHandler("TargetMeProbability", TargetMeProbability::new);
		EffectHandler.getInstance().registerHandler("Teleport", Teleport::new);
		EffectHandler.getInstance().registerHandler("TeleportToNpc", TeleportToNpc::new);
		EffectHandler.getInstance().registerHandler("TeleportToPlayer", TeleportToPlayer::new);
		EffectHandler.getInstance().registerHandler("TeleportToSummon", TeleportToSummon::new);
		EffectHandler.getInstance().registerHandler("TeleportToTarget", TeleportToTarget::new);
		EffectHandler.getInstance().registerHandler("TransferDamageToPlayer", TransferDamageToPlayer::new);
		EffectHandler.getInstance().registerHandler("TransferDamageToSummon", TransferDamageToSummon::new);
		EffectHandler.getInstance().registerHandler("TransferHate", TransferHate::new);
		EffectHandler.getInstance().registerHandler("Transformation", Transformation::new);
		EffectHandler.getInstance().registerHandler("TrapDetect", TrapDetect::new);
		EffectHandler.getInstance().registerHandler("TrapRemove", TrapRemove::new);
		EffectHandler.getInstance().registerHandler("TriggerSkill", TriggerSkill::new);
		EffectHandler.getInstance().registerHandler("TriggerSkillByAvoid", TriggerSkillByAvoid::new);
		EffectHandler.getInstance().registerHandler("TriggerSkillByDamageDealt", TriggerSkillByDamageDealt::new);
		EffectHandler.getInstance().registerHandler("TriggerSkillByDamageReceived", TriggerSkillByDamageReceived::new);
		EffectHandler.getInstance().registerHandler("TriggerSkillByDeathBlow", TriggerSkillByDeathBlow::new);
		EffectHandler.getInstance().registerHandler("TriggerSkillByHpPercent", TriggerSkillByHpPercent::new);
		EffectHandler.getInstance().registerHandler("TriggerSkillByKill", TriggerSkillByKill::new);
		EffectHandler.getInstance().registerHandler("TriggerSkillByMagicType", TriggerSkillByMagicType::new);
		EffectHandler.getInstance().registerHandler("TriggerSkillBySkill", TriggerSkillBySkill::new);
		EffectHandler.getInstance().registerHandler("TriggerSkillBySkillDamage", TriggerSkillBySkillDamage::new);
		EffectHandler.getInstance().registerHandler("TriggerSkillWithDelay", TriggerSkillWithDelay::new);
		EffectHandler.getInstance().registerHandler("TwoHandedBluntBonus", TwoHandedBluntBonus::new);
		EffectHandler.getInstance().registerHandler("TwoHandedSwordBonus", TwoHandedSwordBonus::new);
		EffectHandler.getInstance().registerHandler("Unsummon", Unsummon::new);
		EffectHandler.getInstance().registerHandler("UnsummonAgathion", UnsummonAgathion::new);
		EffectHandler.getInstance().registerHandler("UnsummonServitors", UnsummonServitors::new);
		EffectHandler.getInstance().registerHandler("Untargetable", Untargetable::new);
		EffectHandler.getInstance().registerHandler("VampiricAttack", VampiricAttack::new);
		EffectHandler.getInstance().registerHandler("VampiricDefence", VampiricDefence::new);
		EffectHandler.getInstance().registerHandler("VitalityExpRate", VitalityExpRate::new);
		EffectHandler.getInstance().registerHandler("VitalityPointsRate", VitalityPointsRate::new);
		EffectHandler.getInstance().registerHandler("VitalityPointUp", VitalityPointUp::new);
		EffectHandler.getInstance().registerHandler("WeightLimit", WeightLimit::new);
		EffectHandler.getInstance().registerHandler("WeightPenalty", WeightPenalty::new);
		EffectHandler.getInstance().registerHandler("WorldChatPoints", WorldChatPoints::new);
		LOGGER.info(EffectMasterHandler.class.getSimpleName() + ": Loaded " + EffectHandler.getInstance().size() + " effect handlers.");
	}
}
