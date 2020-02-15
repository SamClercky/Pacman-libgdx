package be.vubhub.pacman;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class Player {
    private enum Direction {LEFT, RIGHT, UP, DOWN};

    private Rectangle pos;
    private SpriteBatch batch;
    private Texture sprite;

    private long cooldownTime = 75;
    private long lastTimeClicked = 0;

    Player() {
        pos = new Rectangle(50,50,20,20);
        batch = new SpriteBatch();
        sprite = new Texture(Gdx.files.internal("wall.png"));
    }

    private boolean canGo(Direction dir) {
        int x = 0, y = 0;
        switch (dir) {
            case LEFT:
                x = (int)((pos.x - Walls.getOffset())/Walls.getOffset());
                y = (int)(pos.y / Walls.getOffset());
                break;
            case RIGHT:
                x = (int)((pos.x + pos.width + Walls.getOffset())/Walls.getOffset());
                y = (int)(pos.y / Walls.getOffset());
                break;
            case DOWN:
                x = (int)(pos.x / Walls.getOffset());
                y = (int)((pos.y - Walls.getOffset())/Walls.getOffset());
                break;
            case UP:
                x = (int)(pos.x / Walls.getOffset());
                y = (int)((pos.y + pos.height + Walls.getOffset())/Walls.getOffset());
                break;
        }
        return Walls.walls[y][x] == 0;
    }

    private void updatePos() {
        final long currTime = TimeUtils.millis();
        if (lastTimeClicked + cooldownTime < currTime) {
            lastTimeClicked = currTime;
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && canGo(Direction.RIGHT)) {
                pos.x += Walls.getOffset();
            } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && canGo(Direction.LEFT)) {
                pos.x -= Walls.getOffset();
            }

            if (Gdx.input.isKeyPressed(Input.Keys.UP) && canGo(Direction.UP)) {
                pos.y += Walls.getOffset();
            } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && canGo(Direction.DOWN)) {
                pos.y -= Walls.getOffset();
            }
        }
    }

    void render() {
        updatePos();

        batch.begin();
        batch.draw(sprite, pos.x, pos.y);
        batch.end();
    }

    void dispose() {
        batch.dispose();
        sprite.dispose();
    }

    // Accessors
    public long getCooldonwTime() {
        return cooldownTime;
    }

    public void setCooldownTime(long time) {
        this.cooldownTime = time;
    }

    public Rectangle getPos() {
        return pos;
    }
}
