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

package com.cheatedpixel.cheatedpixeldungeon.items.stones;

import com.cheatedpixel.cheatedpixeldungeon.Assets;
import com.cheatedpixel.cheatedpixeldungeon.Dungeon;
import com.cheatedpixel.cheatedpixeldungeon.actors.Actor;
import com.cheatedpixel.cheatedpixeldungeon.actors.Char;
import com.cheatedpixel.cheatedpixeldungeon.actors.buffs.Buff;
import com.cheatedpixel.cheatedpixeldungeon.actors.buffs.Terror;
import com.cheatedpixel.cheatedpixeldungeon.effects.Flare;
import com.cheatedpixel.cheatedpixeldungeon.sprites.ItemSpriteSheet;
import com.cheatedpixel.cheatedpixeldungeon.tiles.DungeonTilemap;
import com.watabou.noosa.audio.Sample;

public class StoneOfFear extends Runestone {
	
	{
		image = ItemSpriteSheet.STONE_FEAR;
	}
	
	@Override
	protected void activate(int cell) {

		Char ch = Actor.findChar( cell );

		if (ch != null && ch.alignment != Char.Alignment.ALLY ){
			Buff.affect( ch, Terror.class, Terror.DURATION ).object = curUser.id();
		}

		new Flare( 5, 16 ).color( 0xFF0000, true ).show(Dungeon.hero.sprite.parent, DungeonTilemap.tileCenterToWorld(cell), 2f );
		Sample.INSTANCE.play( Assets.Sounds.READ );
		
	}
	
}
