package com.sdemo.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.model.keyframe.KeyframedAnimation;
import com.badlogic.gdx.math.Vector3;
import com.sdemo.model.DemoKeyframedModel;
import com.sdemo.model.SKeyframedModel;
import com.sdemo.screen.inputprocessor.IPGameMainScreen;
import com.sdemo.util.Room;

public class GameMainScreen implements Screen {

	private final int WIDTH;
	private final int HEIGHT;

	private IPGameMainScreen processor;

	// 3d stuff
	private PerspectiveCamera cam3D;
	private SKeyframedModel model;
	private KeyframedAnimation animation;
	float angle = 0;
	float animTime = 0;
	private static final Vector3 ROOM_SIZE = new Vector3(80, 80, 150);
	private Room room;

	// 2d stuff
	private SpriteBatch spriteBatch;
	private OrthographicCamera cam2D;
	private Sprite ballSprites;

	// private final Logger LOG = new Logger("WAWATIME");

	public GameMainScreen() {
		super();

		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();

		processor = new IPGameMainScreen();
		Gdx.input.setInputProcessor(processor);

		cam3D = new PerspectiveCamera(67, WIDTH / 2, HEIGHT / 2);
		cam3D.far = 151;
		model = new DemoKeyframedModel(Gdx.files.internal("objs/knight.md2"),
				Gdx.files.internal("images/knight.jpg"));
		room = new Room(ROOM_SIZE);
		room.setColor(1, 0, 0);

		cam2D = new OrthographicCamera(WIDTH, HEIGHT);
		cam2D.position.set(WIDTH / 2, HEIGHT / 2, 0);
		cam2D.update();
		spriteBatch = new SpriteBatch();
		Texture ballTexture = new Texture(
				Gdx.files.internal("images/ball.png"));
		ballTexture.setFilter(Texture.TextureFilter.Linear,
				Texture.TextureFilter.Linear);
		ballSprites = new Sprite(ballTexture);
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		GL10 gl = Gdx.gl10;
		gl.glClearColor(1, 1, 1, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		gl.glDisable(GL10.GL_DITHER);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glEnable(GL10.GL_CULL_FACE);

		setLighting(gl);

		cam3D.position.set(0, 20, 0); // camera, sometimes you can treat is as
										// your eyes, here set the eyes
										// position.
		cam3D.direction.set(0, 0, -1); // the sight direction
		cam3D.update();
		cam3D.apply(Gdx.gl10);

		int key = 0;
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_0)) {
			key = 0;
		} else if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
			key = 1;
		} else if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
			key = 2;
		} else if (Gdx.input.isKeyPressed(Input.Keys.NUM_3)) {
			key = 3;
		} else if (Gdx.input.isKeyPressed(Input.Keys.NUM_4)) {
			key = 4;
		} else if (Gdx.input.isKeyPressed(Input.Keys.NUM_5)) {
			key = 5;
		} else if (Gdx.input.isKeyPressed(Input.Keys.NUM_6)) {
			key = 6;
		} else if (Gdx.input.isKeyPressed(Input.Keys.NUM_7)) {
			key = 7;
		} else if (Gdx.input.isKeyPressed(Input.Keys.NUM_8)) {
			key = 8;
		} else if (Gdx.input.isKeyPressed(Input.Keys.NUM_9)) {
			key = 9;
		}

		DemoKeyframedModel.AnimationEnum ani = DemoKeyframedModel.AnimationEnum
				.values()[key];
		animation = model.getAnimation(ani.getValue());

		Gdx.gl.glEnable(GL10.GL_TEXTURE_2D);
		// texture.bind();
		// angle += 45 * Gdx.graphics.getDeltaTime();
		// Gdx.gl10.glRotatef(angle, 0, 1, 0);
		animTime += Gdx.graphics.getDeltaTime();
		if (animTime > animation.totalDuration - animation.frameDuration) {
			animTime = 0;
		}

		model.setAnimation(ani.getValue(), animTime, false);

		gl.glPushMatrix();
		gl.glTranslatef(0, 25, -70);// location of the model which be rendered
									// next
		model.render();
		gl.glPopMatrix();
		Gdx.gl.glDisable(GL10.GL_TEXTURE_2D);

		Gdx.gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glPushMatrix();
		gl.glTranslatef(-40, 0, -150);
		room.setColor(1, 0, 0);
		room.renderWireframe(gl);
		gl.glPopMatrix();
		Gdx.gl.glDisable(GL10.GL_TEXTURE_2D);

		// 2D render
		spriteBatch.setProjectionMatrix(cam2D.combined);
		spriteBatch.begin();
		ballSprites.draw(spriteBatch);
		spriteBatch.end();

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
		model.dispose();
	}

	float[] lightColor = { 1, 1, 1, 0 };
	float[] lightPosition = { 2, 5, 10, 0 };

	private void setLighting(GL10 gl) {
		gl.glEnable(GL10.GL_LIGHTING);
		gl.glEnable(GL10.GL_COLOR_MATERIAL);
		gl.glEnable(GL10.GL_LIGHT0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, lightColor, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, lightPosition, 0);
	}

}
