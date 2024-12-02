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

package com.cheatedpixel.cheatedpixeldungeon.items.potions.brews;

import com.cheatedpixel.cheatedpixeldungeon.Assets;
import com.cheatedpixel.cheatedpixeldungeon.Dungeon;
import com.cheatedpixel.cheatedpixeldungeon.actors.blobs.Blob;
import com.cheatedpixel.cheatedpixeldungeon.actors.blobs.Electricity;
import com.cheatedpixel.cheatedpixeldungeon.items.potions.PotionOfParalyticGas;
import com.cheatedpixel.cheatedpixeldungeon.scenes.GameScene;
import com.cheatedpixel.cheatedpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.BArray;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;

public class ShockingBrew extends Brew {
	
	{
		image = ItemSpriteSheet.BREW_SHOCKING;
	}
	
	@Override
	public void shatter(int cell) {
		splash( cell );
		if (Dungeon.level.heroFOV[cell]) {
			Sample.INSTANCE.play( Assets.Sounds.SHATTER );
			Sample.INSTANCE.play(Assets.Sounds.LIGHTNING);
		}
		PathFinder.buildDistanceMap( cell, BArray.not( Dungeon.level.solid, null ), 3 );
		for (int i = 0; i < PathFinder.distance.length; i++) {
			if (PathFinder.distance[i] < Integer.MAX_VALUE) {
				GameScene.add(Blob.seed(i, 20, Electricity.class));
			}
		}
	}
	
	public static class Recipe extends com.cheatedpixel.cheatedpixeldungeon.items.Recipe.SimpleRecipe {
		
		{
			inputs =  new Class[]{PotionOfParalyticGas.class};
			inQuantity = new int[]{1};
			
			cost = 10;
			
			output = ShockingBrew.class;
			outQuantity = 1;
		}
		
	}
}
