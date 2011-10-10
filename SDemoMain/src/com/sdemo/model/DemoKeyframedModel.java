package com.sdemo.model;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g3d.model.keyframe.KeyframedAnimation;

public class DemoKeyframedModel extends SKeyframedModel {

	public enum AnimationEnum {
		STAND("stand"), RUN("run"), ATTACK("attack"), PAIN("pain"), JUMP("jump"), FLIP(
				"flip"), SALUTE("salute"), TAUNT("taunt"), WAVE("wave"), POINT(
				"point"), CRSTND("crstnd"), CRWALK("crwalk"), CRATTK("crattk"), CRPAIN(
				"crpain"), CRDEATH("crdeath"), DEATH("death");

		String animName;

		AnimationEnum(String animName) {
			this.animName = animName;
		}

		static String[] valuesToArray() {
			String[] valuesArray = new String[AnimationEnum.values().length];

			for (int i = 0; i < AnimationEnum.values().length; i++) {
				valuesArray[i] = AnimationEnum.values()[i].animName;
				System.out.println(valuesArray[i]);
			}

			return valuesArray;
		}
		
		public String getValue() {
			return animName;
		}
	}

	public DemoKeyframedModel(FileHandle modelFile) {
		this(modelFile, null);
	}

	public DemoKeyframedModel(FileHandle modelFile, FileHandle textureFile) {
		super(modelFile, textureFile, AnimationEnum.valuesToArray());
	}

	public void setAnimation(AnimationEnum animation, float time, boolean loop) {
		this.model.setAnimation(animation.animName, time, loop);
	}
	
	public KeyframedAnimation getAnimation(AnimationEnum name) {
		return super.getAnimation(name.getValue());
	}
	
	public static void main(String[] args) {
		System.out.println(AnimationEnum.STAND.getValue());
	}
}
