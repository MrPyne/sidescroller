package org.sidescroller;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.uwsoft.editor.renderer.components.DimensionsComponent;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.physics.PhysicsBodyLoader;
import com.uwsoft.editor.renderer.scripts.IScript;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;

/**
 * Created by matts on 13/05/17.
 */
public class Player implements IScript {

    private World world;
    private Entity player;
    private TransformComponent transformComponent;
    private DimensionsComponent dimensionsComponent;

    private Vector2 speed;
    private float gravity = -500f;

    private final float jumpSpeed = 80f;

    public Player(World world) {
        this.world = world;
    }

    @Override
    public void init(Entity entity) {
        player = entity;

        transformComponent = ComponentRetriever.get(entity, TransformComponent.class);
        dimensionsComponent = ComponentRetriever.get(entity, DimensionsComponent.class);

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

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            speed.y = jumpSpeed;
        }

        speed.y += gravity * delta;

        transformComponent.y += speed.y * delta;

        rayCast();
    }

    public float getX() {
        return transformComponent.x;
    }

    public float getY() {
        return transformComponent.y;
    }

    public void setY(float y) {
        transformComponent.y = y;
    }

    public void setX(float x) {
        transformComponent.x = x;
    }

    public float getWidth() {
        return dimensionsComponent.width;
    }

    public float getHeight() {
        return dimensionsComponent.height;
    }

    private void rayCast() {
        final float rayGap = getHeight() / 2;

        float raySize = -(speed.y + Gdx.graphics.getDeltaTime()) * Gdx.graphics.getDeltaTime();

        if (speed.y > 0) return;

        Vector2 rayFrom = new Vector2((getX() + getWidth() / 2) * PhysicsBodyLoader.getScale(), (getY() + rayGap) * PhysicsBodyLoader.getScale());
        Vector2 rayTo = new Vector2((getX() + getWidth() / 2) * PhysicsBodyLoader.getScale(), (getY() - raySize) * PhysicsBodyLoader.getScale());

        world.rayCast(new RayCastCallback() {
            @Override
            public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {

                speed.y = 0;

                setY(point.y / PhysicsBodyLoader.getScale() + 0.1f);

                return 0;
            }
        }, rayFrom, rayTo);

    }

    @Override
    public void dispose() {

    }

}
