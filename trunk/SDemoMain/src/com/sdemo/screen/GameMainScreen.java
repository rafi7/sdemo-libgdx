package com.sdemo.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.loaders.g3d.G3dLoader;
import com.badlogic.gdx.graphics.g3d.model.still.StillModel;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Logger;
import com.sdemo.util.Room;

public class GameMainScreen implements Screen {

	/** the background texture **/
	private Texture backgroundTexture;
	/** perspective camera **/
	private PerspectiveCamera camera;

	private StillModel model;

	private Room room;

	private static final Vector3 ROOM_SIZE = new Vector3(16, 16, 16);

	private final Logger LOG = new Logger("SDemo");

	public GameMainScreen() {
		super();
		camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());

		LOG.log("1");
		FileHandle file = Gdx.files.internal("objs/obj.g3d");
		model = G3dLoader.loadStillModel(file);

		LOG.log("2");

		room = new Room(ROOM_SIZE);
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		GL10 gl = Gdx.gl10;
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		gl.glDisable(GL10.GL_DITHER);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glEnable(GL10.GL_CULL_FACE);

		setLighting(gl);

		camera.position.set(0, 5, 20); // camera, sometimes you can treat is as
										// your eyes, here set the eyes
										// position.
		camera.direction.set(0, 0, -1); // the sight direction
		camera.update();
		camera.apply(Gdx.gl10);

		gl.glPushMatrix();
		gl.glTranslatef(-8, 0, -8);
		room.renderWireframe(gl);
		gl.glPopMatrix();

		gl.glPushMatrix();
		gl.glTranslatef(0, 0, 15);// location of the model which be rendered
		// next
		gl.glColor4f(1, 0, 0, 1);
		model.render();
		gl.glPopMatrix();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		backgroundTexture.dispose();
		model.dispose();
	}

	float[] direction = { 1, 0.5f, 0, 0 };

	private void setLighting(GL10 gl) {
		gl.glEnable(GL10.GL_LIGHTING);
		gl.glEnable(GL10.GL_LIGHT0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, direction, 0);
		gl.glEnable(GL10.GL_COLOR_MATERIAL);
	}

}
