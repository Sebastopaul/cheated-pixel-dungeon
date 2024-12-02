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

package com.cheatedpixel.cheatedpixeldungeon.items.spells;

import com.cheatedpixel.cheatedpixeldungeon.Challenges;
import com.cheatedpixel.cheatedpixeldungeon.Dungeon;
import com.cheatedpixel.cheatedpixeldungeon.effects.Speck;
import com.cheatedpixel.cheatedpixeldungeon.effects.Transmuting;
import com.cheatedpixel.cheatedpixeldungeon.items.Generator;
import com.cheatedpixel.cheatedpixeldungeon.items.Item;
import com.cheatedpixel.cheatedpixeldungeon.items.potions.Potion;
import com.cheatedpixel.cheatedpixeldungeon.items.potions.brews.Brew;
import com.cheatedpixel.cheatedpixeldungeon.items.potions.elixirs.Elixir;
import com.cheatedpixel.cheatedpixeldungeon.items.potions.exotic.ExoticPotion;
import com.cheatedpixel.cheatedpixeldungeon.items.scrolls.Scroll;
import com.cheatedpixel.cheatedpixeldungeon.items.scrolls.ScrollOfTransmutation;
import com.cheatedpixel.cheatedpixeldungeon.items.scrolls.exotic.ExoticScroll;
import com.cheatedpixel.cheatedpixeldungeon.items.stones.Runestone;
import com.cheatedpixel.cheatedpixeldungeon.items.weapon.missiles.darts.TippedDart;
import com.cheatedpixel.cheatedpixeldungeon.messages.Messages;
import com.cheatedpixel.cheatedpixeldungeon.plants.Plant;
import com.cheatedpixel.cheatedpixeldungeon.sprites.ItemSpriteSheet;
import com.cheatedpixel.cheatedpixeldungeon.utils.GLog;
import com.watabou.utils.Reflection;

public class Recycle extends InventorySpell {
	
	{
		image = ItemSpriteSheet.RECYCLE;

		talentFactor = 2;
		talentChance = 1/(float)Recipe.OUT_QUANTITY;
	}

	@Override
	protected boolean usableOnItem(Item item) {
		return (item instanceof Potion && !(item instanceof Elixir || item instanceof Brew)) ||
				item instanceof Scroll ||
				item instanceof Plant.Seed ||
				item instanceof Runestone ||
				item instanceof TippedDart;
	}

	@Override
	protected void onItemSelected(Item item) {
		Item result;
		do {
			if (item instanceof Potion) {
				result = Generator.randomUsingDefaults(Generator.Category.POTION);
				if (item instanceof ExoticPotion){
					result = Reflection.newInstance(ExoticPotion.regToExo.get(result.getClass()));
				}
			} else if (item instanceof Scroll) {
				result = Generator.randomUsingDefaults(Generator.Category.SCROLL);
				if (item instanceof ExoticScroll){
					result = Reflection.newInstance(ExoticScroll.regToExo.get(result.getClass()));
				}
			} else if (item instanceof Plant.Seed) {
				result = Generator.randomUsingDefaults(Generator.Category.SEED);
			} else if (item instanceof Runestone) {
				result = Generator.randomUsingDefaults(Generator.Category.STONE);
			} else {
				result = TippedDart.randomTipped(1);
			}
		} while (result.getClass() == item.getClass() || Challenges.isItemBlocked(result));
		
		item.detach(curUser.belongings.backpack);
		GLog.p(Messages.get(this, "recycled", result.name()));
		if (!result.collect()){
			Dungeon.level.drop(result, curUser.pos).sprite.drop();
		}
		Transmuting.show(curUser, item, result);
		curUser.sprite.emitter().start(Speck.factory(Speck.CHANGE), 0.2f, 10);
	}
	
	@Override
	public int value() {
		return (int)(60 * (quantity/(float)Recipe.OUT_QUANTITY));
	}

	@Override
	public int energyVal() {
		return (int)(12 * (quantity/(float)Recipe.OUT_QUANTITY));
	}
	
	public static class Recipe extends com.cheatedpixel.cheatedpixeldungeon.items.Recipe.SimpleRecipe {

		private static final int OUT_QUANTITY = 12;
		
		{
			inputs =  new Class[]{ScrollOfTransmutation.class};
			inQuantity = new int[]{1};
			
			cost = 12;
			
			output = Recycle.class;
			outQuantity = OUT_QUANTITY;
		}
		
	}
}
