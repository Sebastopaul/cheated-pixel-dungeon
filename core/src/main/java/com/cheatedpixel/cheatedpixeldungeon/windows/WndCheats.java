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

package com.cheatedpixel.cheatedpixeldungeon.windows;

import com.cheatedpixel.cheatedpixeldungeon.Cheats;
import com.cheatedpixel.cheatedpixeldungeon.SPDSettings;
import com.cheatedpixel.cheatedpixeldungeon.ShatteredPixelDungeon;
import com.cheatedpixel.cheatedpixeldungeon.ui.*;
import com.cheatedpixel.cheatedpixeldungeon.*;
import com.cheatedpixel.cheatedpixeldungeon.messages.Messages;
import com.cheatedpixel.cheatedpixeldungeon.scenes.PixelScene;
import com.cheatedpixel.cheatedpixeldungeon.ui.*;
import com.watabou.noosa.Camera;
import com.watabou.noosa.ui.Component;

import java.util.ArrayList;

public class WndCheats extends Window {

	private static final int WIDTH		= 120;
	private static final int HEIGHT		= 180;
	private static final int TTL_HEIGHT = 16;
	private static final int BTN_WIDTH = 16;
	private static final int BTN_HEIGHT = 20;
	private static final int GAP        = 2;

	private final boolean editable;
	private ArrayList<CheckBox> boxes;
	private final Component content;
	private float pos = TTL_HEIGHT;

	public WndCheats(int checked, boolean editable ) {
		super();

		this.editable = editable;

		resize( WIDTH, HEIGHT );

		ScrollPane scroll = new ScrollPane( new Component() );
		add( scroll );

		content = scroll.content();
		content.clear();

		RenderedTextBlock title = PixelScene.renderTextBlock( Messages.get(this, "title"), 12 );
		title.hardlight( TITLE_COLOR );
		title.setPos(
				(WIDTH - title.width()) / 2,
				(TTL_HEIGHT - title.height()) / 2
		);
		PixelScene.align(title);
		content.add( title );

		addCheckBoxes(checked);
		pos += GAP;
		addSliders();

		content.setSize( WIDTH, pos + 10 );

		scroll.setRect( 0, 0, WIDTH, HEIGHT);
		scroll.scrollTo( 0, 0 );
	}

	@Override
	public void onBackPressed() {

		if (editable) {
			int value = 0;
			for (int i=0; i < boxes.size(); i++) {
				if (boxes.get( i ).checked()) {
					value |= Cheats.BOOL_MASKS[i];
				}
			}
			SPDSettings.boolCheats( value );
		}

		super.onBackPressed();
	}

	private void addCheckBoxes(int checked) {
		boxes = new ArrayList<>();

		for (int i = 0; i < Cheats.BOOL_NAME_IDS.length; i++) {

			final String cheat = Cheats.BOOL_NAME_IDS[i];

			CheckBox cb = new CheckBox( Messages.titleCase(Messages.get(this, cheat)) );
			cb.checked( (checked & Cheats.BOOL_MASKS[i]) != 0 );
			cb.active = editable;

			updatePosAndAddInfos(cb, cheat, i);
			boxes.add(cb);
		}
	}

	private void addSliders() {
		for (int i = 0; i < Cheats.SLIDERS_NAME_IDS.length; i++) {
			final String cheat = Cheats.SLIDERS_NAME_IDS[i];

			OptionSlider os = new OptionSlider(Messages.get(this, cheat),
					Messages.get(this, cheat + "_min"), Messages.get(this, cheat + "_max"), Cheats.SLIDERS_MIN_VALUES[i], Cheats.SLIDERS_MAX_VALUES[i]) {
				@Override
				protected void onChange() {
					SPDSettings.cheatSlider(cheat, getSelectedValue());
				}
			};
			os.setSelectedValue(SPDSettings.cheatSlider(
					cheat,
					Cheats.SLIDERS_DEF_VALUES[i],
					Cheats.SLIDERS_MIN_VALUES[i],
					Cheats.SLIDERS_MAX_VALUES[i]
			));

			updatePosAndAddInfos(os, cheat, i);
		}
	}

	private void updatePosAndAddInfos(Component component, String cheat, int i) {
		if (i > 0) {
			pos += GAP;
		}
		component.setRect( 0, pos, WIDTH - BTN_WIDTH, BTN_HEIGHT );

		content.add(component);

		IconButton info = new IconButton(Icons.get(Icons.INFO)){
			@Override
			protected void onClick() {
				super.onClick();
				ShatteredPixelDungeon.scene().add(
						new WndMessage(Messages.get(this, cheat + "_desc"))
				);
			}
		};
		info.setRect(component.right(), pos, BTN_WIDTH, BTN_HEIGHT);
		content.add(info);

		pos = component.bottom();
	}
}