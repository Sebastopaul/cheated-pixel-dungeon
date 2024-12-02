/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2024 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.cheatedpixel.cheatedpixeldungeon.actors.hero;

import com.cheatedpixel.cheatedpixeldungeon.Assets;
import com.cheatedpixel.cheatedpixeldungeon.Badges;
import com.cheatedpixel.cheatedpixeldungeon.Challenges;
import com.cheatedpixel.cheatedpixeldungeon.Dungeon;
import com.cheatedpixel.cheatedpixeldungeon.QuickSlot;
import com.cheatedpixel.cheatedpixeldungeon.SPDSettings;
import com.cheatedpixel.cheatedpixeldungeon.actors.hero.abilities.ArmorAbility;
import com.cheatedpixel.cheatedpixeldungeon.actors.hero.abilities.duelist.Challenge;
import com.cheatedpixel.cheatedpixeldungeon.actors.hero.abilities.duelist.ElementalStrike;
import com.cheatedpixel.cheatedpixeldungeon.actors.hero.abilities.duelist.Feint;
import com.cheatedpixel.cheatedpixeldungeon.actors.hero.abilities.huntress.NaturesPower;
import com.cheatedpixel.cheatedpixeldungeon.actors.hero.abilities.huntress.SpectralBlades;
import com.cheatedpixel.cheatedpixeldungeon.actors.hero.abilities.huntress.SpiritHawk;
import com.cheatedpixel.cheatedpixeldungeon.actors.hero.abilities.mage.ElementalBlast;
import com.cheatedpixel.cheatedpixeldungeon.actors.hero.abilities.mage.WarpBeacon;
import com.cheatedpixel.cheatedpixeldungeon.actors.hero.abilities.mage.WildMagic;
import com.cheatedpixel.cheatedpixeldungeon.actors.hero.abilities.rogue.DeathMark;
import com.cheatedpixel.cheatedpixeldungeon.actors.hero.abilities.rogue.ShadowClone;
import com.cheatedpixel.cheatedpixeldungeon.actors.hero.abilities.rogue.SmokeBomb;
import com.cheatedpixel.cheatedpixeldungeon.actors.hero.abilities.warrior.Endure;
import com.cheatedpixel.cheatedpixeldungeon.actors.hero.abilities.warrior.HeroicLeap;
import com.cheatedpixel.cheatedpixeldungeon.actors.hero.abilities.warrior.Shockwave;
import com.cheatedpixel.cheatedpixeldungeon.items.BrokenSeal;
import com.cheatedpixel.cheatedpixeldungeon.items.Item;
import com.cheatedpixel.cheatedpixeldungeon.items.Waterskin;
import com.cheatedpixel.cheatedpixeldungeon.items.armor.ClothArmor;
import com.cheatedpixel.cheatedpixeldungeon.items.artifacts.CloakOfShadows;
import com.cheatedpixel.cheatedpixeldungeon.items.bags.VelvetPouch;
import com.cheatedpixel.cheatedpixeldungeon.items.food.Food;
import com.cheatedpixel.cheatedpixeldungeon.items.potions.PotionOfHealing;
import com.cheatedpixel.cheatedpixeldungeon.items.potions.PotionOfInvisibility;
import com.cheatedpixel.cheatedpixeldungeon.items.potions.PotionOfLiquidFlame;
import com.cheatedpixel.cheatedpixeldungeon.items.potions.PotionOfMindVision;
import com.cheatedpixel.cheatedpixeldungeon.items.potions.PotionOfStrength;
import com.cheatedpixel.cheatedpixeldungeon.items.scrolls.ScrollOfIdentify;
import com.cheatedpixel.cheatedpixeldungeon.items.scrolls.ScrollOfLullaby;
import com.cheatedpixel.cheatedpixeldungeon.items.scrolls.ScrollOfMagicMapping;
import com.cheatedpixel.cheatedpixeldungeon.items.scrolls.ScrollOfMirrorImage;
import com.cheatedpixel.cheatedpixeldungeon.items.scrolls.ScrollOfRage;
import com.cheatedpixel.cheatedpixeldungeon.items.scrolls.ScrollOfUpgrade;
import com.cheatedpixel.cheatedpixeldungeon.items.wands.WandOfMagicMissile;
import com.cheatedpixel.cheatedpixeldungeon.items.weapon.SpiritBow;
import com.cheatedpixel.cheatedpixeldungeon.items.weapon.melee.Dagger;
import com.cheatedpixel.cheatedpixeldungeon.items.weapon.melee.Gloves;
import com.cheatedpixel.cheatedpixeldungeon.items.weapon.melee.MagesStaff;
import com.cheatedpixel.cheatedpixeldungeon.items.weapon.melee.Rapier;
import com.cheatedpixel.cheatedpixeldungeon.items.weapon.melee.WornShortsword;
import com.cheatedpixel.cheatedpixeldungeon.items.weapon.missiles.ThrowingKnife;
import com.cheatedpixel.cheatedpixeldungeon.items.weapon.missiles.ThrowingSpike;
import com.cheatedpixel.cheatedpixeldungeon.items.weapon.missiles.ThrowingStone;
import com.cheatedpixel.cheatedpixeldungeon.journal.Catalog;
import com.cheatedpixel.cheatedpixeldungeon.messages.Messages;
import com.watabou.utils.DeviceCompat;

public enum HeroClass {

	WARRIOR( HeroSubClass.BERSERKER, HeroSubClass.GLADIATOR ),
	MAGE( HeroSubClass.BATTLEMAGE, HeroSubClass.WARLOCK ),
	ROGUE( HeroSubClass.ASSASSIN, HeroSubClass.FREERUNNER ),
	HUNTRESS( HeroSubClass.SNIPER, HeroSubClass.WARDEN ),
	DUELIST( HeroSubClass.CHAMPION, HeroSubClass.MONK );

	private HeroSubClass[] subClasses;

	HeroClass( HeroSubClass...subClasses ) {
		this.subClasses = subClasses;
	}

	public void initHero( Hero hero ) {

		hero.heroClass = this;
		Talent.initClassTalents(hero);

		Item i = new ClothArmor().identify();
		if (!Challenges.isItemBlocked(i)) hero.belongings.armor = (ClothArmor)i;

		i = new Food();
		if (!Challenges.isItemBlocked(i)) i.collect();

		new VelvetPouch().collect();
		Dungeon.LimitedDrops.VELVET_POUCH.drop();

		Waterskin waterskin = new Waterskin();
		waterskin.collect();

		new ScrollOfIdentify().identify();

		switch (this) {
			case WARRIOR:
				initWarrior( hero );
				break;

			case MAGE:
				initMage( hero );
				break;

			case ROGUE:
				initRogue( hero );
				break;

			case HUNTRESS:
				initHuntress( hero );
				break;

			case DUELIST:
				initDuelist( hero );
				break;
		}

		if (SPDSettings.quickslotWaterskin()) {
			for (int s = 0; s < QuickSlot.SIZE; s++) {
				if (Dungeon.quickslot.getItem(s) == null) {
					Dungeon.quickslot.setSlot(s, waterskin);
					break;
				}
			}
		}

	}

	public Badges.Badge masteryBadge() {
		switch (this) {
			case WARRIOR:
				return Badges.Badge.MASTERY_WARRIOR;
			case MAGE:
				return Badges.Badge.MASTERY_MAGE;
			case ROGUE:
				return Badges.Badge.MASTERY_ROGUE;
			case HUNTRESS:
				return Badges.Badge.MASTERY_HUNTRESS;
			case DUELIST:
				return Badges.Badge.MASTERY_DUELIST;
		}
		return null;
	}

	private static void initWarrior( Hero hero ) {
		(hero.belongings.weapon = new WornShortsword()).identify();
		ThrowingStone stones = new ThrowingStone();
		stones.quantity(3).collect();
		Dungeon.quickslot.setSlot(0, stones);

		if (hero.belongings.armor != null){
			hero.belongings.armor.affixSeal(new BrokenSeal());
			Catalog.setSeen(BrokenSeal.class); //as it's not added to the inventory
		}

		new PotionOfHealing().identify();
		new ScrollOfRage().identify();
	}

	private static void initMage( Hero hero ) {
		MagesStaff staff;

		staff = new MagesStaff(new WandOfMagicMissile());

		(hero.belongings.weapon = staff).identify();
		hero.belongings.weapon.activate(hero);

		Dungeon.quickslot.setSlot(0, staff);

		new ScrollOfUpgrade().identify();
		new PotionOfLiquidFlame().identify();
	}

	private static void initRogue( Hero hero ) {
		(hero.belongings.weapon = new Dagger()).identify();

		CloakOfShadows cloak = new CloakOfShadows();
		(hero.belongings.artifact = cloak).identify();
		hero.belongings.artifact.activate( hero );

		ThrowingKnife knives = new ThrowingKnife();
		knives.quantity(3).collect();

		Dungeon.quickslot.setSlot(0, cloak);
		Dungeon.quickslot.setSlot(1, knives);

		new ScrollOfMagicMapping().identify();
		new PotionOfInvisibility().identify();
	}

	private static void initHuntress( Hero hero ) {

		(hero.belongings.weapon = new Gloves()).identify();
		SpiritBow bow = new SpiritBow();
		bow.identify().collect();

		Dungeon.quickslot.setSlot(0, bow);

		new PotionOfMindVision().identify();
		new ScrollOfLullaby().identify();
	}

	private static void initDuelist( Hero hero ) {

		(hero.belongings.weapon = new Rapier()).identify();
		hero.belongings.weapon.activate(hero);

		ThrowingSpike spikes = new ThrowingSpike();
		spikes.quantity(2).collect();

		Dungeon.quickslot.setSlot(0, hero.belongings.weapon);
		Dungeon.quickslot.setSlot(1, spikes);

		new PotionOfStrength().identify();
		new ScrollOfMirrorImage().identify();
	}

	public String title() {
		return Messages.get(HeroClass.class, name());
	}

	public String desc(){
		return Messages.get(HeroClass.class, name()+"_desc");
	}

	public String shortDesc(){
		return Messages.get(HeroClass.class, name()+"_desc_short");
	}

	public HeroSubClass[] subClasses() {
		return subClasses;
	}

	public ArmorAbility[] armorAbilities(){
		switch (this) {
			case WARRIOR: default:
				return new ArmorAbility[]{new HeroicLeap(), new Shockwave(), new Endure()};
			case MAGE:
				return new ArmorAbility[]{new ElementalBlast(), new WildMagic(), new WarpBeacon()};
			case ROGUE:
				return new ArmorAbility[]{new SmokeBomb(), new DeathMark(), new ShadowClone()};
			case HUNTRESS:
				return new ArmorAbility[]{new SpectralBlades(), new NaturesPower(), new SpiritHawk()};
			case DUELIST:
				return new ArmorAbility[]{new Challenge(), new ElementalStrike(), new Feint()};
		}
	}

	public String spritesheet() {
		switch (this) {
			case WARRIOR: default:
				return Assets.Sprites.WARRIOR;
			case MAGE:
				return Assets.Sprites.MAGE;
			case ROGUE:
				return Assets.Sprites.ROGUE;
			case HUNTRESS:
				return Assets.Sprites.HUNTRESS;
			case DUELIST:
				return Assets.Sprites.DUELIST;
		}
	}

	public String splashArt(){
		switch (this) {
			case WARRIOR: default:
				return Assets.Splashes.WARRIOR;
			case MAGE:
				return Assets.Splashes.MAGE;
			case ROGUE:
				return Assets.Splashes.ROGUE;
			case HUNTRESS:
				return Assets.Splashes.HUNTRESS;
			case DUELIST:
				return Assets.Splashes.DUELIST;
		}
	}
	
	public boolean isUnlocked(){
		//always unlock on debug builds
		if (DeviceCompat.isDebug()) return true;

		switch (this){
			case WARRIOR: default:
				return true;
			case MAGE:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_MAGE);
			case ROGUE:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_ROGUE);
			case HUNTRESS:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_HUNTRESS);
			case DUELIST:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_DUELIST);
		}
	}
	
	public String unlockMsg() {
		return shortDesc() + "\n\n" + Messages.get(HeroClass.class, name()+"_unlock");
	}

}
