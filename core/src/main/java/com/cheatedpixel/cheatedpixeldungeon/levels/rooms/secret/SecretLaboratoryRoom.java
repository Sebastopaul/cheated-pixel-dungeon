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

package com.cheatedpixel.cheatedpixeldungeon.levels.rooms.secret;

import com.cheatedpixel.cheatedpixeldungeon.actors.blobs.Alchemy;
import com.cheatedpixel.cheatedpixeldungeon.actors.blobs.Blob;
import com.cheatedpixel.cheatedpixeldungeon.items.EnergyCrystal;
import com.cheatedpixel.cheatedpixeldungeon.items.potions.Potion;
import com.cheatedpixel.cheatedpixeldungeon.items.potions.PotionOfExperience;
import com.cheatedpixel.cheatedpixeldungeon.items.potions.PotionOfFrost;
import com.cheatedpixel.cheatedpixeldungeon.items.potions.PotionOfHaste;
import com.cheatedpixel.cheatedpixeldungeon.items.potions.PotionOfHealing;
import com.cheatedpixel.cheatedpixeldungeon.items.potions.PotionOfInvisibility;
import com.cheatedpixel.cheatedpixeldungeon.items.potions.PotionOfLevitation;
import com.cheatedpixel.cheatedpixeldungeon.items.potions.PotionOfLiquidFlame;
import com.cheatedpixel.cheatedpixeldungeon.items.potions.PotionOfMindVision;
import com.cheatedpixel.cheatedpixeldungeon.items.potions.PotionOfParalyticGas;
import com.cheatedpixel.cheatedpixeldungeon.items.potions.PotionOfPurity;
import com.cheatedpixel.cheatedpixeldungeon.items.potions.PotionOfToxicGas;
import com.cheatedpixel.cheatedpixeldungeon.items.potions.exotic.ExoticPotion;
import com.cheatedpixel.cheatedpixeldungeon.items.trinkets.ExoticCrystals;
import com.cheatedpixel.cheatedpixeldungeon.levels.Level;
import com.cheatedpixel.cheatedpixeldungeon.levels.Terrain;
import com.cheatedpixel.cheatedpixeldungeon.levels.painters.Painter;
import com.watabou.utils.Point;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.util.HashMap;

public class SecretLaboratoryRoom extends SecretRoom {
	
	private static HashMap<Class<? extends Potion>, Float> potionChances = new HashMap<>();
	static{
		potionChances.put(PotionOfHealing.class,        1f);
		potionChances.put(PotionOfMindVision.class,     2f);
		potionChances.put(PotionOfFrost.class,          3f);
		potionChances.put(PotionOfLiquidFlame.class,    3f);
		potionChances.put(PotionOfToxicGas.class,       3f);
		potionChances.put(PotionOfHaste.class,          4f);
		potionChances.put(PotionOfInvisibility.class,   4f);
		potionChances.put(PotionOfLevitation.class,     4f);
		potionChances.put(PotionOfParalyticGas.class,   4f);
		potionChances.put(PotionOfPurity.class,         4f);
		potionChances.put(PotionOfExperience.class,     6f);
	}
	
	public void paint( Level level ) {
		
		Painter.fill( level, this, Terrain.WALL );
		Painter.fill( level, this, 1, Terrain.EMPTY_SP );
		
		entrance().set( Door.Type.HIDDEN );
		
		Point pot = center();
		Painter.set( level, pot, Terrain.ALCHEMY );

		Blob.seed( pot.x + level.width() * pot.y, 1, Alchemy.class, level );

		int pos;
		do {
			pos = level.pointToCell(random());
		} while (level.map[pos] != Terrain.EMPTY_SP || level.heaps.get( pos ) != null);
		level.drop( new EnergyCrystal().quantity(Random.IntRange(3, 5)), pos );

		do {
			pos = level.pointToCell(random());
		} while (level.map[pos] != Terrain.EMPTY_SP || level.heaps.get( pos ) != null);
		level.drop( new EnergyCrystal().quantity(Random.IntRange(3, 5)), pos );

		int n = Random.IntRange( 2, 3 );
		HashMap<Class<? extends Potion>, Float> chances = new HashMap<>(potionChances);
		for (int i=0; i < n; i++) {
			do {
				pos = level.pointToCell(random());
			} while (level.map[pos] != Terrain.EMPTY_SP || level.heaps.get( pos ) != null);
			
			Class<?extends Potion> potionCls = Random.chances(chances);
			chances.put(potionCls, 0f);

			if (ExoticPotion.regToExo.containsKey(potionCls)){
				if (Random.Float() < ExoticCrystals.consumableExoticChance()){
					potionCls = ExoticPotion.regToExo.get(potionCls);
				}
			}

			level.drop( Reflection.newInstance(potionCls), pos );
		}
		
	}
	
}
