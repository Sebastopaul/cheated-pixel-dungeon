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

package com.cheatedpixel.cheatedpixeldungeon.items.quest;

import com.cheatedpixel.cheatedpixeldungeon.Assets;
import com.cheatedpixel.cheatedpixeldungeon.Dungeon;
import com.cheatedpixel.cheatedpixeldungeon.actors.Actor;
import com.cheatedpixel.cheatedpixeldungeon.actors.Char;
import com.cheatedpixel.cheatedpixeldungeon.actors.buffs.Buff;
import com.cheatedpixel.cheatedpixeldungeon.actors.buffs.Invisibility;
import com.cheatedpixel.cheatedpixeldungeon.actors.buffs.Vulnerable;
import com.cheatedpixel.cheatedpixeldungeon.actors.hero.Hero;
import com.cheatedpixel.cheatedpixeldungeon.actors.hero.HeroClass;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Bat;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Bee;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Crab;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Scorpio;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Spinner;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.Swarm;
import com.cheatedpixel.cheatedpixeldungeon.actors.mobs.npcs.Blacksmith;
import com.cheatedpixel.cheatedpixeldungeon.effects.CellEmitter;
import com.cheatedpixel.cheatedpixeldungeon.effects.Speck;
import com.cheatedpixel.cheatedpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.cheatedpixel.cheatedpixeldungeon.levels.Level;
import com.cheatedpixel.cheatedpixeldungeon.levels.MiningLevel;
import com.cheatedpixel.cheatedpixeldungeon.levels.Terrain;
import com.cheatedpixel.cheatedpixeldungeon.messages.Messages;
import com.cheatedpixel.cheatedpixeldungeon.scenes.GameScene;
import com.cheatedpixel.cheatedpixeldungeon.sprites.ItemSprite.Glowing;
import com.cheatedpixel.cheatedpixeldungeon.sprites.ItemSpriteSheet;
import com.cheatedpixel.cheatedpixeldungeon.ui.AttackIndicator;
import com.cheatedpixel.cheatedpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.PathFinder;

import java.util.ArrayList;

//various code in here supports old blacksmith quest logic from before v2.2.0
public class Pickaxe extends MeleeWeapon {
	
	public static final String AC_MINE	= "MINE";
	
	public static final float TIME_TO_MINE = 2;
	
	private static final Glowing BLOODY = new Glowing( 0x550000 );
	
	{
		image = ItemSpriteSheet.PICKAXE;

		levelKnown = true;
		
		unique = true;
		bones = false;

		tier = 2;
	}
	
	public boolean bloodStained = false;

	@Override
	public int STRReq(int lvl) {
		return super.STRReq(lvl) + 2; //tier 3 strength requirement with tier 2 damage stats
	}

	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		if (Blacksmith.Quest.oldMiningQuest()) {
			actions.add(AC_MINE);
		}
		if (Dungeon.level instanceof MiningLevel){
			actions.remove(AC_DROP);
			actions.remove(AC_THROW);
		}
		return actions;
	}
	
	@Override
	public void execute( final Hero hero, String action ) {

		super.execute( hero, action );
		
		if (action.equals(AC_MINE)) {
			
			if (Dungeon.depth < 11 || Dungeon.depth > 15) {
				GLog.w( Messages.get(this, "no_vein") );
				return;
			}
			
			for (int i = 0; i < PathFinder.NEIGHBOURS8.length; i++) {
				
				final int pos = hero.pos + PathFinder.NEIGHBOURS8[i];
				if (Dungeon.level.map[pos] == Terrain.WALL_DECO) {
				
					hero.spend( TIME_TO_MINE );
					hero.busy();
					
					hero.sprite.attack( pos, new Callback() {
						
						@Override
						public void call() {

							CellEmitter.center( pos ).burst( Speck.factory( Speck.STAR ), 7 );
							Sample.INSTANCE.play( Assets.Sounds.EVOKE );
							
							Level.set( pos, Terrain.WALL );
							GameScene.updateMap( pos );
							
							DarkGold gold = new DarkGold();
							if (gold.doPickUp( Dungeon.hero )) {
								GLog.i( Messages.capitalize(Messages.get(Dungeon.hero, "you_now_have", gold.name())) );
							} else {
								Dungeon.level.drop( gold, hero.pos ).sprite.drop();
							}
							
							hero.onOperateComplete();
						}
					} );
					
					return;
				}
			}
			
			GLog.w( Messages.get(this, "no_vein") );
			
		}
	}
	
	@Override
	public int proc( Char attacker, Char defender, int damage ) {
		if (Blacksmith.Quest.oldBloodQuest() && !bloodStained && defender instanceof Bat) {
			Actor.add(new Actor() {

				{
					actPriority = VFX_PRIO;
				}

				@Override
				protected boolean act() {
					if (!defender.isAlive()){
						bloodStained = true;
						updateQuickslot();
					}

					Actor.remove(this);
					return true;
				}
			});
		}
		return super.proc( attacker, defender, damage );
	}

	@Override
	public boolean keptThroughLostInventory() {
		//pickaxe is always kept when it's needed for the mining level
		return super.keptThroughLostInventory() || Dungeon.level instanceof MiningLevel;
	}

	@Override
	public String defaultAction() {
		if (Dungeon.hero.heroClass == HeroClass.DUELIST && isEquipped(Dungeon.hero)){
			return AC_ABILITY;
		} else if (Blacksmith.Quest.oldMiningQuest()) {
			return AC_MINE;
		} else {
			return super.defaultAction();
		}
	}

	@Override
	public String targetingPrompt() {
		return Messages.get(this, "prompt");
	}

	@Override
	protected void duelistAbility(Hero hero, Integer target) {
		if (target == null) {
			return;
		}

		Char enemy = Actor.findChar(target);
		if (enemy == null || enemy == hero || hero.isCharmedBy(enemy) || !Dungeon.level.heroFOV[target]) {
			GLog.w(Messages.get(this, "ability_no_target"));
			return;
		}

		hero.belongings.abilityWeapon = this;
		if (!hero.canAttack(enemy)){
			GLog.w(Messages.get(this, "ability_target_range"));
			hero.belongings.abilityWeapon = null;
			return;
		}
		hero.belongings.abilityWeapon = null;

		hero.sprite.attack(enemy.pos, new Callback() {
			@Override
			public void call() {
				int damageBoost = 0;
				if (Char.hasProp(enemy, Char.Property.INORGANIC)
						|| enemy instanceof Swarm
						|| enemy instanceof Bee
						|| enemy instanceof Crab
						|| enemy instanceof Spinner
						|| enemy instanceof Scorpio) {
					//+(8+2*lvl) damage, equivalent to +100% damage
					damageBoost = augment.damageFactor(8 + 2*buffedLvl());
				}
				beforeAbilityUsed(hero, enemy);
				AttackIndicator.target(enemy);
				if (hero.attack(enemy, 1, damageBoost, Char.INFINITE_ACCURACY)) {
					if (enemy.isAlive()) {
						Buff.affect(enemy, Vulnerable.class, 3f);
					} else {
						onAbilityKill(hero, enemy);
					}
					Sample.INSTANCE.play(Assets.Sounds.HIT_STRONG);
				}
				Invisibility.dispel();
				hero.spendAndNext(hero.attackDelay());
				afterAbilityUsed(hero);
			}
		});
	}

	@Override
	public String abilityInfo() {
		int dmgBoost = 8 + 2*buffedLvl();
		return Messages.get(this, "ability_desc", augment.damageFactor(min()+dmgBoost), augment.damageFactor(max()+dmgBoost));
	}

	public String upgradeAbilityStat(int level){
		int dmgBoost = 8 + 2*level;
		return augment.damageFactor(min(level)+dmgBoost) + "-" + augment.damageFactor(max(level)+dmgBoost);
	}

	private static final String BLOODSTAINED = "bloodStained";
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		
		bundle.put( BLOODSTAINED, bloodStained );
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		
		bloodStained = bundle.getBoolean( BLOODSTAINED );
	}
	
	@Override
	public Glowing glowing() {
		if (super.glowing() == null) {
			return bloodStained ? BLOODY : null;
		} else {
			return super.glowing();
		}
	}

}