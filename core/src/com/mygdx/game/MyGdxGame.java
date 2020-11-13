package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.screens.TitleScreen;

public class MyGdxGame extends Game {

	public Resources res;

	@Override
	public void create () {
		res = new Resources();
		setScreen(new TitleScreen(this));
	}



	@Override
	public void dispose () {
		res.dispose();
	}
}
