package com.sdemo.md2;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g3d.model.keyframe.Keyframe;
import com.badlogic.gdx.graphics.g3d.model.keyframe.KeyframedAnimation;
import com.badlogic.gdx.graphics.g3d.model.keyframe.KeyframedModel;

public class MD2AnimationSpliter {

	public static KeyframedModel split(KeyframedModel model,
			String... animationNames) {

		KeyframedAnimation allAnimation = model.getAnimation("all");
		List<KeyframedAnimation> animations = new ArrayList<KeyframedAnimation>();

		float duration = allAnimation.frameDuration;
		Keyframe[] orFrames = allAnimation.keyframes;

		if (allAnimation != null) {
			KeyframedAnimation anim;
			for (int i = 0; i < animationNames.length; i++) {
				String animationName = animationNames[i];
				List<Keyframe> frames = new ArrayList<Keyframe>();
				Keyframe tempFrame;
				for (int j = 0, k = 0; j < orFrames.length; j++) {
					tempFrame = orFrames[j];
					if (tempFrame.name.startsWith(animationName)) {
						tempFrame.timeStamp = (k++) * duration;
						frames.add(tempFrame);
					}
				}

				Keyframe[] kefr = new Keyframe[frames.size()];
				for (int j = 0; j < kefr.length; j++) {
					kefr[j] = frames.get(j);
				}

				anim = new KeyframedAnimation(animationNames[i], duration, kefr);
				animations.add(anim);
			}
			model.setAnimation(animations);
		}

		return model;
	}
}
