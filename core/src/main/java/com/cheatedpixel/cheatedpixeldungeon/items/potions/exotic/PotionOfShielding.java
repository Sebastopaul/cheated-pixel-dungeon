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

import com.cheatedpixel.cheatedpixeldungeon.Challenges;
import com.cheatedpixel.cheatedpixeldungeon.Cheats;
import com.cheatedpixel.cheatedpixeldungeon.Dungeon;
import com.cheatedpixel.cheatedpixeldungeon.actors.buffs.Barrier;
import com.cheatedpixel.cheatedpixeldungeon.actors.buffs.Buff;
import com.cheatedpixel.cheatedpixeldungeon.actors.hero.Hero;
import com.cheatedpixel.cheatedpixeldungeon.effects.FloatingText;
import com.cheatedpixel.cheatedpixeldungeon.items.potions.PotionOfHealing;
import com.cheatedpixel.cheatedpixeldungeon.sprites.CharSprite;
import com.cheatedpixel.cheatedpixeldungeon.sprites.ItemSpriteSheet;

public class PotionOfShielding extends ExoticPotion {
	
	{
		icon = ItemSpriteSheet.Icons.POTION_SHIELDING;
	}
	
	@Override
	public void apply(Hero hero) {
		identify();

		if (!Cheats.isCheated(Cheats.DISABLE_CHALLENGES_EFFECTS) && Dungeon.isChallenged(Challenges.NO_HEALING)){
			PotionOfHealing.pharmacophobiaProc(hero);
		} else {
			//~75% of a potion of healing
			Buff.affect(hero, Barrier.class).setShield((int) (0.6f * hero.HT + 10));
			hero.sprite.showStatusWithIcon( CharSprite.POSITIVE, Integer.toString((int) (0.6f * hero.HT + 10)), FloatingText.SHIELDING );
		}
	}
}
