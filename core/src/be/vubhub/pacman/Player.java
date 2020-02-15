package be.vubhub.pacman;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Player extends Rectangle {
    private Rectangle pos;
    private SpriteBatch spriteBatch;
    private Texture sprite;

    private int speed = 200;

    Player() {
        pos = new Rectangle(0,0,20,20);
        spriteBatch = new SpriteBatch();
        sprite = new Texture(Gdx.files.internal("badlogic.jpg"));
    }

    private void updatePos() {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            pos.x += speed * Gdx.graphics.getDeltaTime();
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            pos.x -= speed * Gdx.graphics.getDeltaTime();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            pos.y += speed * Gdx.graphics.getDeltaTime();
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            pos.y -= speed * Gdx.graphics.getDeltaTime();
        }
    }

    void render() {
        updatePos();

        spriteBatch.begin();
        spriteBatch.draw(sprite, pos.x, pos.y);
        spriteBatch.end();
    }

    void dispose() {
        spriteBatch.dispose();
        sprite.dispose();
    }

    // Accessors
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Rectangle getPos() {
        return pos;
    }
}
