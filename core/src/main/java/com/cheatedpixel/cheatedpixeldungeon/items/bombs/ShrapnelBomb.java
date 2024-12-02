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

package com.cheatedpixel.cheatedpixeldungeon.items.bombs;

import com.cheatedpixel.cheatedpixeldungeon.Dungeon;
import com.cheatedpixel.cheatedpixeldungeon.actors.Actor;
import com.cheatedpixel.cheatedpixeldungeon.actors.Char;
import com.cheatedpixel.cheatedpixeldungeon.effects.CellEmitter;
import com.cheatedpixel.cheatedpixeldungeon.effects.particles.BlastParticle;
import com.cheatedpixel.cheatedpixeldungeon.mechanics.ShadowCaster;
import com.cheatedpixel.cheatedpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Point;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class ShrapnelBomb extends Bomb {
	
	{
		image = ItemSpriteSheet.SHRAPNEL_BOMB;
	}
	
	@Override
	public boolean explodesDestructively() {
		return false;
	}

	@Override
	protected int explosionRange() {
		return 8;
	}

	@Override
	public void explode(int cell) {
		super.explode(cell);
		
		boolean[] FOV = new boolean[Dungeon.level.length()];
		Point c = Dungeon.level.cellToPoint(cell);
		ShadowCaster.castShadow(c.x, c.y, Dungeon.level.width(), FOV, Dungeon.level.losBlocking, explosionRange());
		
		ArrayList<Char> affected = new ArrayList<>();
		
		for (int i = 0; i < FOV.length; i++) {
			if (FOV[i]) {
				if (Dungeon.level.heroFOV[i] && !Dungeon.level.solid[i]) {
					CellEmitter.center( i ).burst( BlastParticle.FACTORY, 5 );
				}
				Char ch = Actor.findChar(i);
				if (ch != null){
					affected.add(ch);
				}
			}
		}
		
		for (Char ch : affected){
			//regular bomb damage over an FOV up to 8-range
			int damage = Random.NormalIntRange( 4 + Dungeon.scalingDepth(), 12 + 3*Dungeon.scalingDepth() );
			damage -= ch.drRoll();
			ch.damage(damage, this);
			if (ch == Dungeon.hero && !ch.isAlive()) {
				Dungeon.fail(this);
			}
		}
	}
	
	@Override
	public int value() {
		//prices of ingredients
		return quantity * (20 + 50);
	}
}
