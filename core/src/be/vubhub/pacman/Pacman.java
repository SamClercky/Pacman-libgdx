package be.vubhub.pacman;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Pacman extends ApplicationAdapter {
	Player player;
	OrthographicCamera camera;
	Walls walls;
	
	@Override
	public void create () {
		player = new Player();
		walls = new Walls();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1350, 1350);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		walls.render();
		player.render();

		camera.update();
	}
	
	@Override
	public void dispose () {
		player.dispose();
		walls.dispose();
	}
}
