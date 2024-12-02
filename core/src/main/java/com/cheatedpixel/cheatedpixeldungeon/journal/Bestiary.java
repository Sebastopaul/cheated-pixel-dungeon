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

package com.cheatedpixel.cheatedpixeldungeon.journal;

import com.cheatedpixel.cheatedpixeldungeon.Badges;
import com.cheatedpixel.cheatedpixeldungeon.actors.hero.abilities.huntress.SpiritHawk;
import com.cheatedpixel.cheatedpixeldungeon.actors.hero.abilities.rogue.ShadowClone;
import com.cheatedpixel.cheatedpixeldungeon.actors.hero.abilities.rogue.SmokeBomb;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Acidic;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Albino;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.ArmoredBrute;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.ArmoredStatue;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Bandit;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Bat;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Bee;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Brute;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.CausticSlime;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Crab;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.CrystalGuardian;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.CrystalMimic;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.CrystalSpire;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.CrystalWisp;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.DM100;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.DM200;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.DM201;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.DM300;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.DemonSpawner;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.DwarfKing;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.EbonyMimic;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Elemental;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Eye;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.FetidRat;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Ghoul;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Gnoll;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.GnollGeomancer;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.GnollGuard;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.GnollSapper;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.GnollTrickster;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.GoldenMimic;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Golem;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Goo;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.GreatCrab;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Guard;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Mimic;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Monk;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Necromancer;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.PhantomPiranha;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Piranha;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Pylon;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Rat;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.RipperDemon;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.RotHeart;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.RotLasher;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Scorpio;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Senior;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Shaman;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Skeleton;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Slime;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Snake;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.SpectralNecromancer;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Spinner;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Statue;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Succubus;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Swarm;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Tengu;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Thief;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.TormentedSpirit;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Warlock;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Wraith;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.YogDzewa;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.YogFist;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.npcs.Blacksmith;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.npcs.Ghost;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.npcs.Imp;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.npcs.MirrorImage;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.npcs.PrismaticImage;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.npcs.RatKing;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.npcs.Sheep;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.npcs.Shopkeeper;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.npcs.Wandmaker;
import com.cheatedpixel.cheatedpixeldungeon.items.artifacts.DriedRose;
import com.cheatedpixel.cheatedpixeldungeon.items.quest.CorpseDust;
import com.cheatedpixel.cheatedpixeldungeon.items.wands.WandOfLivingEarth;
import com.cheatedpixel.cheatedpixeldungeon.items.wands.WandOfRegrowth;
import com.cheatedpixel.cheatedpixeldungeon.items.wands.WandOfWarding;
import com.cheatedpixel.cheatedpixeldungeon.levels.rooms.special.SentryRoom;
import com.cheatedpixel.cheatedpixeldungeon.levels.traps.AlarmTrap;
import com.cheatedpixel.cheatedpixeldungeon.levels.traps.BlazingTrap;
import com.cheatedpixel.cheatedpixeldungeon.levels.traps.BurningTrap;
import com.cheatedpixel.cheatedpixeldungeon.levels.traps.ChillingTrap;
import com.cheatedpixel.cheatedpixeldungeon.levels.traps.ConfusionTrap;
import com.cheatedpixel.cheatedpixeldungeon.levels.traps.CorrosionTrap;
import com.cheatedpixel.cheatedpixeldungeon.levels.traps.CursingTrap;
import com.cheatedpixel.cheatedpixeldungeon.levels.traps.DisarmingTrap;
import com.cheatedpixel.cheatedpixeldungeon.levels.traps.DisintegrationTrap;
import com.cheatedpixel.cheatedpixeldungeon.levels.traps.DistortionTrap;
import com.cheatedpixel.cheatedpixeldungeon.levels.traps.ExplosiveTrap;
import com.cheatedpixel.cheatedpixeldungeon.levels.traps.FlashingTrap;
import com.cheatedpixel.cheatedpixeldungeon.levels.traps.FlockTrap;
import com.cheatedpixel.cheatedpixeldungeon.levels.traps.FrostTrap;
import com.cheatedpixel.cheatedpixeldungeon.levels.traps.GatewayTrap;
import com.cheatedpixel.cheatedpixeldungeon.levels.traps.GeyserTrap;
import com.cheatedpixel.cheatedpixeldungeon.levels.traps.GnollRockfallTrap;
import com.cheatedpixel.cheatedpixeldungeon.levels.traps.GrimTrap;
import com.cheatedpixel.cheatedpixeldungeon.levels.traps.GrippingTrap;
import com.cheatedpixel.cheatedpixeldungeon.levels.traps.GuardianTrap;
import com.cheatedpixel.cheatedpixeldungeon.levels.traps.OozeTrap;
import com.cheatedpixel.cheatedpixeldungeon.levels.traps.PitfallTrap;
import com.cheatedpixel.cheatedpixeldungeon.levels.traps.PoisonDartTrap;
import com.cheatedpixel.cheatedpixeldungeon.levels.traps.RockfallTrap;
import com.cheatedpixel.cheatedpixeldungeon.levels.traps.ShockingTrap;
import com.cheatedpixel.cheatedpixeldungeon.levels.traps.StormTrap;
import com.cheatedpixel.cheatedpixeldungeon.levels.traps.SummoningTrap;
import com.cheatedpixel.cheatedpixeldungeon.levels.traps.TeleportationTrap;
import com.cheatedpixel.cheatedpixeldungeon.levels.traps.TenguDartTrap;
import com.cheatedpixel.cheatedpixeldungeon.levels.traps.ToxicTrap;
import com.cheatedpixel.cheatedpixeldungeon.levels.traps.WarpingTrap;
import com.cheatedpixel.cheatedpixeldungeon.levels.traps.WeakeningTrap;
import com.cheatedpixel.cheatedpixeldungeon.levels.traps.WornDartTrap;
import com.cheatedpixel.cheatedpixeldungeon.messages.Messages;
import com.cheatedpixel.cheatedpixeldungeon.plants.BlandfruitBush;
import com.cheatedpixel.cheatedpixeldungeon.plants.Blindweed;
import com.cheatedpixel.cheatedpixeldungeon.plants.Earthroot;
import com.cheatedpixel.cheatedpixeldungeon.plants.Fadeleaf;
import com.cheatedpixel.cheatedpixeldungeon.plants.Firebloom;
import com.cheatedpixel.cheatedpixeldungeon.plants.Icecap;
import com.cheatedpixel.cheatedpixeldungeon.plants.Mageroyal;
import com.cheatedpixel.cheatedpixeldungeon.plants.Rotberry;
import com.cheatedpixel.cheatedpixeldungeon.plants.Sorrowmoss;
import com.cheatedpixel.cheatedpixeldungeon.plants.Starflower;
import com.cheatedpixel.cheatedpixeldungeon.plants.Stormvine;
import com.cheatedpixel.cheatedpixeldungeon.plants.Sungrass;
import com.cheatedpixel.cheatedpixeldungeon.plants.Swiftthistle;
import com.watabou.utils.Bundle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;

//contains all the game's various entities, mostly enemies, NPCS, and allies, but also traps and plants
public enum Bestiary {

	REGIONAL,
	BOSSES,
	UNIVERSAL,
	RARE,
	QUEST,
	NEUTRAL,
	ALLY,
	TRAP,
	PLANT;

	//tracks whether an entity has been encountered
	private final LinkedHashMap<Class<?>, Boolean> seen = new LinkedHashMap<>();
	//tracks enemy kills, trap activations, plant tramples, or just sets to 1 for seen on allies
	private final LinkedHashMap<Class<?>, Integer> encounterCount = new LinkedHashMap<>();

	//should only be used when initializing
	private void addEntities(Class<?>... classes ){
		for (Class<?> cls : classes){
			seen.put(cls, false);
			encounterCount.put(cls, 0);
		}
	}

	public Collection<Class<?>> entities(){
		return seen.keySet();
	}

	public String title(){
		return Messages.get(this, name() + ".title");
	}

	public int totalEntities(){
		return seen.size();
	}

	public int totalSeen(){
		int seenTotal = 0;
		for (boolean entitySeen : seen.values()){
			if (entitySeen) seenTotal++;
		}
		return seenTotal;
	}

	static {

		REGIONAL.addEntities(Rat.class, Snake.class, Gnoll.class, Swarm.class, Crab.class, Slime.class,
				Skeleton.class, Thief.class, DM100.class, Guard.class, Necromancer.class,
				Bat.class, Brute.class, Shaman.RedShaman.class, Shaman.BlueShaman.class, Shaman.PurpleShaman.class, Spinner.class, DM200.class,
				Ghoul.class, Elemental.FireElemental.class, Elemental.FrostElemental.class, Elemental.ShockElemental.class, Warlock.class, Monk.class, Golem.class,
				RipperDemon.class, DemonSpawner.class, Succubus.class, Eye.class, Scorpio.class);

		BOSSES.addEntities(Goo.class,
				Tengu.class,
				Pylon.class, DM300.class,
				DwarfKing.class,
				YogDzewa.Larva.class, YogFist.BurningFist.class, YogFist.SoiledFist.class, YogFist.RottingFist.class, YogFist.RustedFist.class,YogFist.BrightFist.class, YogFist.DarkFist.class, YogDzewa.class);

		UNIVERSAL.addEntities(Wraith.class, Piranha.class, Mimic.class, GoldenMimic.class, EbonyMimic.class, Statue.class, GuardianTrap.Guardian.class, SentryRoom.Sentry.class);

		RARE.addEntities(Albino.class, CausticSlime.class,
				Bandit.class, SpectralNecromancer.class,
				ArmoredBrute.class, DM201.class,
				Elemental.ChaosElemental.class, Senior.class,
				Acidic.class,
				TormentedSpirit.class, PhantomPiranha.class, CrystalMimic.class, ArmoredStatue.class);

		QUEST.addEntities(FetidRat.class, GnollTrickster.class, GreatCrab.class,
				Elemental.NewbornFireElemental.class, RotLasher.class, RotHeart.class,
				CrystalWisp.class, CrystalGuardian.class, CrystalSpire.class, GnollGuard.class, GnollSapper.class, GnollGeomancer.class);

		NEUTRAL.addEntities(Ghost.class, RatKing.class, Shopkeeper.class, Wandmaker.class, Blacksmith.class, Imp.class, Sheep.class, Bee.class);

		ALLY.addEntities(MirrorImage.class, PrismaticImage.class,
				DriedRose.GhostHero.class,
				WandOfWarding.Ward.class, WandOfWarding.Ward.WardSentry.class, WandOfLivingEarth.EarthGuardian.class,
				ShadowClone.ShadowAlly.class, SmokeBomb.NinjaLog.class, SpiritHawk.HawkAlly.class);

		TRAP.addEntities(WornDartTrap.class, PoisonDartTrap.class, DisintegrationTrap.class, GatewayTrap.class,
				ChillingTrap.class, BurningTrap.class, ShockingTrap.class, AlarmTrap.class, GrippingTrap.class, TeleportationTrap.class, OozeTrap.class,
				FrostTrap.class, BlazingTrap.class, StormTrap.class, GuardianTrap.class, FlashingTrap.class, WarpingTrap.class,
				ConfusionTrap.class, ToxicTrap.class, CorrosionTrap.class,
				FlockTrap.class, SummoningTrap.class, WeakeningTrap.class, CursingTrap.class,
				GeyserTrap.class, ExplosiveTrap.class, RockfallTrap.class, PitfallTrap.class,
				DistortionTrap.class, DisarmingTrap.class, GrimTrap.class);

		PLANT.addEntities(Rotberry.class, Sungrass.class, Fadeleaf.class, Icecap.class,
				Firebloom.class, Sorrowmoss.class, Swiftthistle.class, Blindweed.class,
				Stormvine.class, Earthroot.class, Mageroyal.class, Starflower.class,
				BlandfruitBush.class,
				WandOfRegrowth.Dewcatcher.class, WandOfRegrowth.Seedpod.class, WandOfRegrowth.Lotus.class);

	}

	//some mobs and traps have different internal classes in some cases, so need to convert here
	private static final HashMap<Class<?>, Class<?>> classConversions = new HashMap<>();
	static {
		classConversions.put(CorpseDust.DustWraith.class,      Wraith.class);

		classConversions.put(Necromancer.NecroSkeleton.class,  Skeleton.class);

		classConversions.put(TenguDartTrap.class,              PoisonDartTrap.class);
		classConversions.put(GnollRockfallTrap.class,          RockfallTrap.class);

		classConversions.put(DwarfKing.DKGhoul.class,          Ghoul.class);
		classConversions.put(DwarfKing.DKWarlock.class,        Warlock.class);
		classConversions.put(DwarfKing.DKMonk.class,           Monk.class);
		classConversions.put(DwarfKing.DKGolem.class,          Golem.class);

		classConversions.put(YogDzewa.YogRipper.class,         RipperDemon.class);
		classConversions.put(YogDzewa.YogEye.class,            Eye.class);
		classConversions.put(YogDzewa.YogScorpio.class,        Scorpio.class);
	}

	public static boolean isSeen(Class<?> cls){
		for (Bestiary cat : values()) {
			if (cat.seen.containsKey(cls)) {
				return cat.seen.get(cls);
			}
		}
		return false;
	}

	public static void setSeen(Class<?> cls){
		if (classConversions.containsKey(cls)){
			cls = classConversions.get(cls);
		}
		for (Bestiary cat : values()) {
			if (cat.seen.containsKey(cls) && !cat.seen.get(cls)) {
				cat.seen.put(cls, true);
				Journal.saveNeeded = true;
			}
		}
		Badges.validateCatalogBadges();
	}

	public static int encounterCount(Class<?> cls) {
		for (Bestiary cat : values()) {
			if (cat.encounterCount.containsKey(cls)) {
				return cat.encounterCount.get(cls);
			}
		}
		return 0;
	}

	//used primarily when bosses are killed and need to clean up their minions
	public static boolean skipCountingEncounters = false;

	public static void countEncounter(Class<?> cls){
		countEncounters(cls, 1);
	}

	public static void countEncounters(Class<?> cls, int encounters){
		if (skipCountingEncounters){
			return;
		}
		if (classConversions.containsKey(cls)){
			cls = classConversions.get(cls);
		}
		for (Bestiary cat : values()) {
			if (cat.encounterCount.containsKey(cls) && cat.encounterCount.get(cls) != Integer.MAX_VALUE){
				cat.encounterCount.put(cls, cat.encounterCount.get(cls)+encounters);
				if (cat.encounterCount.get(cls) < -1_000_000_000){ //to catch cases of overflow
					cat.encounterCount.put(cls, Integer.MAX_VALUE);
				}
				Journal.saveNeeded = true;
			}
		}
	}

	private static final String BESTIARY_CLASSES    = "bestiary_classes";
	private static final String BESTIARY_SEEN       = "bestiary_seen";
	private static final String BESTIARY_ENCOUNTERS = "bestiary_encounters";

	public static void store( Bundle bundle ){

		ArrayList<Class<?>> classes = new ArrayList<>();
		ArrayList<Boolean> seen = new ArrayList<>();
		ArrayList<Integer> encounters = new ArrayList<>();

		for (Bestiary cat : values()) {
			for (Class<?> entity : cat.entities()) {
				if (cat.seen.get(entity) || cat.encounterCount.get(entity) > 0){
					classes.add(entity);
					seen.add(cat.seen.get(entity));
					encounters.add(cat.encounterCount.get(entity));
				}
			}
		}

		Class<?>[] storeCls = new Class[classes.size()];
		boolean[] storeSeen = new boolean[seen.size()];
		int[] storeEncounters = new int[encounters.size()];

		for (int i = 0; i < storeCls.length; i++){
			storeCls[i] = classes.get(i);
			storeSeen[i] = seen.get(i);
			storeEncounters[i] = encounters.get(i);
		}

		bundle.put( BESTIARY_CLASSES, storeCls );
		bundle.put( BESTIARY_SEEN, storeSeen );
		bundle.put( BESTIARY_ENCOUNTERS, storeEncounters );

	}

	public static void restore( Bundle bundle ){

		if (bundle.contains(BESTIARY_CLASSES)
				&& bundle.contains(BESTIARY_SEEN)
				&& bundle.contains(BESTIARY_ENCOUNTERS)){
			Class<?>[] classes = bundle.getClassArray(BESTIARY_CLASSES);
			boolean[] seen = bundle.getBooleanArray(BESTIARY_SEEN);
			int[] encounters = bundle.getIntArray(BESTIARY_ENCOUNTERS);

			for (int i = 0; i < classes.length; i++){
				for (Bestiary cat : values()){
					if (cat.seen.containsKey(classes[i])){
						cat.seen.put(classes[i], seen[i]);
						cat.encounterCount.put(classes[i], encounters[i]);
					}
				}
			}
		}

	}

}
