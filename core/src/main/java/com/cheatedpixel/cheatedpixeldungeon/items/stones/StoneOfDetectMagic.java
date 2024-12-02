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

import com.cheatedpixel.cheatedpixeldungeon.actors.hero.Belongings;
import com.cheatedpixel.cheatedpixeldungeon.items.EquipableItem;
import com.cheatedpixel.cheatedpixeldungeon.items.Item;
import com.cheatedpixel.cheatedpixeldungeon.items.armor.Armor;
import com.cheatedpixel.cheatedpixeldungeon.items.wands.Wand;
import com.cheatedpixel.cheatedpixeldungeon.items.weapon.Weapon;
import com.cheatedpixel.cheatedpixeldungeon.journal.Catalog;
import com.cheatedpixel.cheatedpixeldungeon.messages.Messages;
import com.cheatedpixel.cheatedpixeldungeon.sprites.ItemSpriteSheet;
import com.cheatedpixel.cheatedpixeldungeon.utils.GLog;

public class StoneOfDetectMagic extends InventoryStone {

	{
		preferredBag = Belongings.Backpack.class;
		image = ItemSpriteSheet.STONE_DETECT;
	}

	@Override
	public boolean usableOnItem(Item item){
		return (item instanceof EquipableItem || item instanceof Wand)
				&& (!item.isIdentified() || !item.cursedKnown);
	}

	@Override
	protected void onItemSelected(Item item) {

		item.cursedKnown = true;
		useAnimation();

		boolean negativeMagic = false;
		boolean positiveMagic = false;

		negativeMagic = item.cursed;
		if (!negativeMagic){
			if (item instanceof Weapon && ((Weapon) item).hasCurseEnchant()){
				negativeMagic = true;
			} else if (item instanceof Armor && ((Armor) item).hasCurseGlyph()){
				negativeMagic = true;
			}
		}

		positiveMagic = item.trueLevel() > 0;
		if (!positiveMagic){
			if (item instanceof Weapon && ((Weapon) item).hasGoodEnchant()){
				positiveMagic = true;
			} else if (item instanceof Armor && ((Armor) item).hasGoodGlyph()){
				positiveMagic = true;
			}
		}

		if (!positiveMagic && !negativeMagic){
			GLog.i(Messages.get(this, "detected_none"));
		} else if (positiveMagic && negativeMagic) {
			GLog.h(Messages.get(this, "detected_both"));
		} else if (positiveMagic){
			GLog.p(Messages.get(this, "detected_good"));
		} else if (negativeMagic){
			GLog.w(Messages.get(this, "detected_bad"));
		}

		curItem.detach( curUser.belongings.backpack );
		Catalog.countUse(getClass());

	}

}