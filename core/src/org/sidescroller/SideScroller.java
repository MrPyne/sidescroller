package org.sidescroller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.components.additional.ButtonComponent;
import com.uwsoft.editor.renderer.utils.ItemWrapper;


public class SideScroller extends ApplicationAdapter {

	private SceneLoader sceneLoader;
	private Viewport viewPort;
	private Player player;

	private UIStage stage;

	@Override
	public void create () {
		viewPort = new FitViewport(266, 133);

		sceneLoader = new SceneLoader();
		sceneLoader.loadScene("MainScene", viewPort);

		ItemWrapper root = new ItemWrapper(sceneLoader.getRoot());

		player = new Player();
		root.getChild("player").addScript(player);

		stage = new UIStage(sceneLoader.getRm());

		/*
		sceneLoader.addComponentsByTagName("button", ButtonComponent.class);

		root.getChild("Button1").getEntity().getComponent(ButtonComponent.class).addListener(new ButtonComponent.ButtonListener() {
			@Override
			public void touchUp() {

			}

			@Override
			public void touchDown() {

			}

			@Override
			public void clicked() {
				System.out.println("Hello");
			}
		});
		*/
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		sceneLoader.getEngine().update(Gdx.graphics.getDeltaTime());

		stage.act();
		stage.draw();

		((OrthographicCamera)viewPort.getCamera()).position.set(player.getX(), player.getY(), 0);
	}
	
	@Override
	public void dispose () {

	}
}
