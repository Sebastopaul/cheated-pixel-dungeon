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

import com.cheatedpixel.cheatedpixeldungeon.Dungeon;
import com.cheatedpixel.cheatedpixeldungeon.actors.Actor;
import com.cheatedpixel.cheatedpixeldungeon.actors.buffs.Invisibility;
import com.cheatedpixel.cheatedpixeldungeon.items.Item;
import com.cheatedpixel.cheatedpixeldungeon.journal.Catalog;
import com.cheatedpixel.cheatedpixeldungeon.sprites.ItemSpriteSheet;

public abstract class Runestone extends Item {
	
	{
		stackable = true;
		defaultAction = AC_THROW;
	}

	@Override
	protected void onThrow(int cell) {
		///inventory stones are thrown like normal items, other stones don't trigger when thrown into pits
		if (this instanceof InventoryStone ||
				(Dungeon.level.pit[cell] && Actor.findChar(cell) == null)){
			super.onThrow( cell );
		} else {
			Catalog.countUse(getClass());
			activate(cell);
			if (Actor.findChar(cell) == null) Dungeon.level.pressCell( cell );
			Invisibility.dispel();
		}
	}
	
	protected abstract void activate(int cell);
	
	@Override
	public boolean isUpgradable() {
		return false;
	}
	
	@Override
	public boolean isIdentified() {
		return true;
	}
	
	@Override
	public int value() {
		return 15 * quantity;
	}

	@Override
	public int energyVal() {
		return 3 * quantity;
	}

	public static class PlaceHolder extends Runestone {
		
		{
			image = ItemSpriteSheet.STONE_HOLDER;
		}
		
		@Override
		protected void activate(int cell) {
			//does nothing
		}
		
		@Override
		public boolean isSimilar(Item item) {
			return item instanceof Runestone;
		}
		
		@Override
		public String info() {
			return "";
		}
	}
}
