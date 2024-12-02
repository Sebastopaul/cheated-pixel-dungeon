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

package com.cheatedpixel.cheatedpixeldungeon.items.potions.elixirs;

import com.cheatedpixel.cheatedpixeldungeon.Challenges;
import com.cheatedpixel.cheatedpixeldungeon.Cheats;
import com.cheatedpixel.cheatedpixeldungeon.Dungeon;
import com.cheatedpixel.cheatedpixeldungeon.actors.buffs.Buff;
import com.cheatedpixel.cheatedpixeldungeon.actors.hero.Hero;
import com.cheatedpixel.cheatedpixeldungeon.effects.FloatingText;
import com.cheatedpixel.cheatedpixeldungeon.items.Item;
import com.cheatedpixel.cheatedpixeldungeon.items.potions.PotionOfHealing;
import com.cheatedpixel.cheatedpixeldungeon.items.quest.GooBlob;
import com.cheatedpixel.cheatedpixeldungeon.journal.Catalog;
import com.cheatedpixel.cheatedpixeldungeon.messages.Messages;
import com.cheatedpixel.cheatedpixeldungeon.sprites.CharSprite;
import com.cheatedpixel.cheatedpixeldungeon.sprites.ItemSpriteSheet;
import com.cheatedpixel.cheatedpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;
import com.watabou.utils.Bundle;
import com.watabou.utils.GameMath;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class ElixirOfAquaticRejuvenation extends Elixir {
	
	{
		image = ItemSpriteSheet.ELIXIR_AQUA;
	}
	
	@Override
	public void apply(Hero hero) {
		if (!Cheats.isCheated(Cheats.DISABLE_CHALLENGES_EFFECTS) && Dungeon.isChallenged(Challenges.NO_HEALING)){
			PotionOfHealing.pharmacophobiaProc(hero);
		} else {
			Buff.affect(hero, AquaHealing.class).set(Math.round(hero.HT * 1.5f));
		}
	}
	
	public static class AquaHealing extends Buff {
		
		{
			type = buffType.POSITIVE;
			announced = true;
		}
		
		private int left;
		
		public void set( int amount ){
			if (amount > left) left = amount;
		}
		
		@Override
		public boolean act() {
			
			if (!target.flying && Dungeon.level.water[target.pos] && target.HP < target.HT){
				float healAmt = GameMath.gate( 1, target.HT/50f, left );
				healAmt = Math.min(healAmt, target.HT - target.HP);
				if (Random.Float() < (healAmt % 1)){
					healAmt = (float)Math.ceil(healAmt);
				} else {
					healAmt = (float)Math.floor(healAmt);
				}
				target.HP += (int)healAmt;
				left -= (int)healAmt;
				target.sprite.showStatusWithIcon( CharSprite.POSITIVE, Integer.toString((int)healAmt), FloatingText.HEALING );

				if (target.HP >= target.HT) {
					target.HP = target.HT;
					if (target instanceof Hero) {
						((Hero) target).resting = false;
					}
				}
			}
			
			if (left <= 0){
				detach();
				if (target instanceof Hero) {
					((Hero) target).resting = false;
				}
			} else {
				spend(TICK);
			}
			return true;
		}
		
		@Override
		public int icon() {
			return BuffIndicator.HEALING;
		}

		@Override
		public void tintIcon(Image icon) {
			icon.hardlight(0, 0.75f, 0.75f);
		}

		@Override
		public float iconFadePercent() {
			float max = Math.round(target.HT * 1.5f);
			return Math.max(0, (max - left) / max);
		}

		@Override
		public String iconTextDisplay() {
			return Integer.toString(left);
		}
		
		@Override
		public String desc() {
			return Messages.get(this, "desc", left);
		}
		
		private static final String LEFT = "left";
		
		@Override
		public void storeInBundle( Bundle bundle ) {
			super.storeInBundle( bundle );
			bundle.put( LEFT, left );
		}
		
		@Override
		public void restoreFromBundle( Bundle bundle ) {
			super.restoreFromBundle( bundle );
			left = bundle.getInt( LEFT );
			
		}
	}
	
	public static class Recipe extends com.cheatedpixel.cheatedpixeldungeon.items.Recipe.SimpleRecipe {
		
		{
			inputs =  new Class[]{PotionOfHealing.class, GooBlob.class};
			inQuantity = new int[]{1, 1};
			
			cost = 6;
			
			output = ElixirOfAquaticRejuvenation.class;
			outQuantity = 1;
		}

		@Override
		public Item brew(ArrayList<Item> ingredients) {
			Catalog.countUse(GooBlob.class);
			return super.brew(ingredients);
		}
	}
	
}
