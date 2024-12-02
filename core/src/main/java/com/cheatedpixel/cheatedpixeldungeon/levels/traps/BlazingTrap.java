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
import com.cheatedpixel.cheatedpixeldungeon.Dungeon;
import com.cheatedpixel.cheatedpixeldungeon.actors.blobs.Blob;
import com.cheatedpixel.cheatedpixeldungeon.actors.blobs.Fire;
import com.cheatedpixel.cheatedpixeldungeon.effects.CellEmitter;
import com.cheatedpixel.cheatedpixeldungeon.effects.particles.FlameParticle;
import com.cheatedpixel.cheatedpixeldungeon.scenes.GameScene;
import com.watabou.utils.BArray;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;

public class BlazingTrap extends Trap {

	{
		color = ORANGE;
		shape = STARS;
	}


	@Override
	public void activate() {
		PathFinder.buildDistanceMap( pos, BArray.not( Dungeon.level.solid, null ), 2 );
		for (int i = 0; i < PathFinder.distance.length; i++) {
			if (PathFinder.distance[i] < Integer.MAX_VALUE) {
				if (Dungeon.level.pit[i] || Dungeon.level.water[i])
					GameScene.add(Blob.seed(i, 1, Fire.class));
				else
					GameScene.add(Blob.seed(i, 5, Fire.class));
				CellEmitter.get(i).burst(FlameParticle.FACTORY, 5);
			}
		}
		Sample.INSTANCE.play(Assets.Sounds.BURNING);
	}
}
