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

package com.cheatedpixel.cheatedpixeldungeon.levels.traps;

import com.cheatedpixel.cheatedpixeldungeon.Assets;
import com.cheatedpixel.cheatedpixeldungeon.Badges;
import com.cheatedpixel.cheatedpixeldungeon.Dungeon;
import com.cheatedpixel.cheatedpixeldungeon.ShatteredPixelDungeon;
import com.cheatedpixel.cheatedpixeldungeon.actors.Actor;
import com.cheatedpixel.cheatedpixeldungeon.actors.Char;
import com.cheatedpixel.cheatedpixeldungeon.actors.hero.Hero;
import com.cheatedpixel.cheatedpixeldungeon.effects.Beam;
import com.cheatedpixel.cheatedpixeldungeon.items.Heap;
import com.cheatedpixel.cheatedpixeldungeon.mechanics.Ballistica;
import com.cheatedpixel.cheatedpixeldungeon.messages.Messages;
import com.cheatedpixel.cheatedpixeldungeon.tiles.DungeonTilemap;
import com.cheatedpixel.cheatedpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class DisintegrationTrap extends Trap {

	{
		color = VIOLET;
		shape = CROSSHAIR;
		
		canBeHidden = false;
		avoidsHallways = true;
	}

	@Override
	public void activate() {
		Char target = Actor.findChar(pos);

		//find the closest char that can be aimed at
		//can't target beyond view distance, with a min of 6 (torch range)
		int range = Math.max(6, Dungeon.level.viewDistance);
		if (target == null){
			float closestDist = Float.MAX_VALUE;
			for (Char ch : Actor.chars()){
				if (!ch.isAlive()) continue;
				float curDist = Dungeon.level.trueDistance(pos, ch.pos);
				//invis targets are considered to be at max range
				if (ch.invisible > 0) curDist = Math.max(curDist, range);
				Ballistica bolt = new Ballistica(pos, ch.pos, Ballistica.PROJECTILE);
				if (bolt.collisionPos == ch.pos
						&& ( curDist < closestDist || (curDist == closestDist && target instanceof Hero))){
					target = ch;
					closestDist = curDist;
				}
			}
			if (closestDist > range){
				target = null;
			}
		}
		
		Heap heap = Dungeon.level.heaps.get(pos);
		if (heap != null) heap.explode();
		
		if (target != null) {
			if (Dungeon.level.heroFOV[pos] || Dungeon.level.heroFOV[target.pos]) {
				Sample.INSTANCE.play(Assets.Sounds.RAY);
				ShatteredPixelDungeon.scene().add(new Beam.DeathRay(DungeonTilemap.tileCenterToWorld(pos), target.sprite.center()));
			}
			target.damage( Random.NormalIntRange(30, 50) + scalingDepth(), this );
			if (target == Dungeon.hero){
				Hero hero = (Hero)target;
				if (!hero.isAlive()){
					Badges.validateDeathFromGrimOrDisintTrap();
					Dungeon.fail( this );
					GLog.n( Messages.get(this, "ondeath") );
					if (reclaimed) Badges.validateDeathFromFriendlyMagic();
				}
			}
		}

	}
}
