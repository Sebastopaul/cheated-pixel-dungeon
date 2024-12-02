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

package com.cheatedpixel.cheatedpixeldungeon.items.armor.glyphs;

import com.cheatedpixel.cheatedpixeldungeon.actors.Char;
import com.cheatedpixel.cheatedpixeldungeon.actors.buffs.Buff;
import com.cheatedpixel.cheatedpixeldungeon.effects.CellEmitter;
import com.cheatedpixel.cheatedpixeldungeon.effects.particles.EarthParticle;
import com.cheatedpixel.cheatedpixeldungeon.items.armor.Armor;
import com.cheatedpixel.cheatedpixeldungeon.items.armor.Armor.Glyph;
import com.cheatedpixel.cheatedpixeldungeon.plants.Earthroot;
import com.cheatedpixel.cheatedpixeldungeon.scenes.PixelScene;
import com.cheatedpixel.cheatedpixeldungeon.sprites.ItemSprite;
import com.cheatedpixel.cheatedpixeldungeon.sprites.ItemSprite.Glowing;
import com.watabou.utils.Random;

public class Entanglement extends Glyph {
	
	private static ItemSprite.Glowing BROWN = new ItemSprite.Glowing( 0x663300 );
	
	@Override
	public int proc(Armor armor, Char attacker, final Char defender, final int damage ) {

		final int level = Math.max( 0, armor.buffedLvl() );
		float procChance = 1/4f * procChanceMultiplier(defender);

		if (Random.Float() < procChance) {

			float powerMulti = Math.max(1f, procChance);

			Buff.affect( defender, Earthroot.Armor.class ).level( Math.round((5 + 2 * level)*powerMulti) );
			CellEmitter.bottom( defender.pos ).start( EarthParticle.FACTORY, 0.05f, 8 );
			PixelScene.shake( 1, 0.4f );
			
		}

		return damage;
	}

	@Override
	public Glowing glowing() {
		return BROWN;
	}
	
}
