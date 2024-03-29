/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.badlogic.gdx.graphics.g3d.model.keyframe;

public class Keyframe {
	public final String name;
	public float timeStamp;
	public final float[] vertices;

	public Keyframe(float timeStamp, float[] vertices) {
		this("G3DFrame", timeStamp, vertices);
	}

	public Keyframe(String name, float timeStamp, float[] vertices) {
		this.name = name;
		this.timeStamp = timeStamp;
		this.vertices = vertices;
	}

}