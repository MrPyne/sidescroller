package org.sidescroller;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.scripts.IScript;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;

/**
 * Created by matts on 13/05/17.
 */
public class Player implements IScript {

    private Entity player;
    private TransformComponent transformComponent;

    private Vector2 speed;
    private float gravity = -500f;

    private final float jumpSpeed = 80f;

    @Override
    public void init(Entity entity) {
        player = entity;

        transformComponent = ComponentRetriever.get(entity, TransformComponent.class);
        speed = new Vector2(100, 0);
    }

    @Override
    public void act(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            transformComponent.x -= speed.x * delta;
            transformComponent.scaleX = -1f;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            transformComponent.x += speed.x * delta;
            transformComponent.scaleX = 1f;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            speed.y = jumpSpeed;
        }

        speed.y += gravity * delta;

        transformComponent.y += speed.y * delta;

        if(transformComponent.y < 7f){
            speed.y = 0;
            transformComponent.y = 7f;
        }
    }

    public float getX(){
        return transformComponent.x;
    }

    public float getY(){
        return transformComponent.y;
    }

    @Override
    public void dispose() {

    }
}