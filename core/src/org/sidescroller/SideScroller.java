package org.sidescroller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.uwsoft.editor.renderer.SceneLoader;
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

		player = new Player(sceneLoader.world);
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

		sceneLoader.addComponentsByTagName("plateform", PlatformComponent.class);

		sceneLoader.getEngine().addSystem(new PlatformSystem());
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		sceneLoader.getEngine().update(Gdx.graphics.getDeltaTime());

		stage.act();
		stage.draw();

		((OrthographicCamera)viewPort.getCamera()).position.x = player.getX() + player.getWidth()/2;
	}
	
	@Override
	public void dispose () {

	}
}
