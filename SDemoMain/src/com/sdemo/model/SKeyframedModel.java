package com.sdemo.model;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.materials.Material;
import com.badlogic.gdx.graphics.g3d.materials.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.model.keyframe.KeyframedAnimation;
import com.badlogic.gdx.graphics.g3d.model.keyframe.KeyframedModel;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Disposable;
import com.sdemo.md2.MD2MulAnimationLoader;

public class SKeyframedModel implements Disposable {

	protected KeyframedModel model;

	protected Texture texture;

	protected Material material;

	protected SKeyframedModel(FileHandle modelFile, FileHandle textureFile,
			String[] aniNames) {
		model = MD2MulAnimationLoader.loadModel(modelFile, 0.2f, aniNames);
		if (textureFile != null)
			texture = new Texture(textureFile);
		material = new Material("material", new TextureAttribute(texture, 0,
				"s_tex"));
		this.model.setMaterial(material);
	}

	public void render() {
		this.model.render();
	}

	public void render(ShaderProgram program) {
		this.model.render(program);
	}

	public void renderTextured() {
		texture.bind();
		this.render();
	}

	public void setAnimation(String name, float time, boolean loop) {
		this.model.setAnimation(name, time, loop);
	}

	public KeyframedAnimation getAnimation(String name) {
		return model.getAnimation(name);
	}

	public KeyframedModel getModel() {
		return model;
	}

	public void setModel(KeyframedModel model) {
		this.model = model;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	@Override
	public void dispose() {
		model.dispose();
		if (texture != null)
			texture.dispose();
	}

}
