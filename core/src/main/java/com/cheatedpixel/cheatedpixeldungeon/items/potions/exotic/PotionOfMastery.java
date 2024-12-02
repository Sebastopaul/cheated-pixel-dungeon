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

package com.cheatedpixel.cheatedpixeldungeon.items.potions.exotic;

import com.cheatedpixel.cheatedpixeldungeon.Assets;
import com.cheatedpixel.cheatedpixeldungeon.actors.hero.Hero;
import com.cheatedpixel.cheatedpixeldungeon.actors.hero.Talent;
import com.cheatedpixel.cheatedpixeldungeon.items.Item;
import com.cheatedpixel.cheatedpixeldungeon.items.armor.Armor;
import com.cheatedpixel.cheatedpixeldungeon.items.weapon.Weapon;
import com.cheatedpixel.cheatedpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.cheatedpixel.cheatedpixeldungeon.journal.Catalog;
import com.cheatedpixel.cheatedpixeldungeon.messages.Messages;
import com.cheatedpixel.cheatedpixeldungeon.scenes.GameScene;
import com.cheatedpixel.cheatedpixeldungeon.sprites.ItemSprite;
import com.cheatedpixel.cheatedpixeldungeon.sprites.ItemSpriteSheet;
import com.cheatedpixel.cheatedpixeldungeon.utils.GLog;
import com.cheatedpixel.cheatedpixeldungeon.windows.WndBag;
import com.cheatedpixel.cheatedpixeldungeon.windows.WndOptions;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class PotionOfMastery extends ExoticPotion {
	
	{
		icon = ItemSpriteSheet.Icons.POTION_MASTERY;

		unique = true;

		talentFactor = 2f;
	}

	protected static boolean identifiedByUse = false;

	@Override
	//need to override drink so that time isn't spent right away
	protected void drink(final Hero hero) {

		if (!isKnown()) {
			identify();
			curItem = detach( hero.belongings.backpack );
			identifiedByUse = true;
		} else {
			identifiedByUse = false;
		}

		GameScene.selectItem(itemSelector);
	}

	protected WndBag.ItemSelector itemSelector = new WndBag.ItemSelector() {

		@Override
		public String textPrompt() {
			return Messages.get(PotionOfMastery.class, "prompt");
		}

		@Override
		public boolean itemSelectable(Item item) {
			return
					(item instanceof MeleeWeapon && !((MeleeWeapon) item).masteryPotionBonus)
					|| (item instanceof Armor && !((Armor) item).masteryPotionBonus);
		}

		@Override
		public void onSelect(Item item) {

			if (item == null && identifiedByUse){
				GameScene.show( new WndOptions(new ItemSprite(PotionOfMastery.this),
						Messages.titleCase(name()),
						Messages.get(ExoticPotion.class, "warning"),
						Messages.get(ExoticPotion.class, "yes"),
						Messages.get(ExoticPotion.class, "no") ) {
					@Override
					protected void onSelect( int index ) {
						switch (index) {
							case 0:
								curUser.spendAndNext(1f);
								identifiedByUse = false;
								break;
							case 1:
								GameScene.selectItem(itemSelector);
								break;
						}
					}
					public void onBackPressed() {}
				} );
			} else if (item != null) {

				if (item instanceof Weapon) {
					((Weapon) item).masteryPotionBonus = true;
					GLog.p( Messages.get(PotionOfMastery.class, "weapon_easier") );
				} else if (item instanceof Armor) {
					((Armor) item).masteryPotionBonus = true;
					GLog.p( Messages.get(PotionOfMastery.class, "armor_easier") );
				}
				updateQuickslot();

				Sample.INSTANCE.play( Assets.Sounds.DRINK );
				curUser.sprite.operate(curUser.pos);

				if (!identifiedByUse) {
					curItem.detach(curUser.belongings.backpack);
				}
				identifiedByUse = false;

				if (!anonymous) {
					Catalog.countUse(PotionOfMastery.class);
					if (Random.Float() < talentChance) {
						Talent.onPotionUsed(curUser, curUser.pos, talentFactor);
					}
				}
			}

		}
	};
	
}
