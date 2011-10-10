package com.sdemo.util;

// rendering a floorgrid on x, z - NOT x,y

import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.math.Vector3;

public class Room {
	int xcount, ycount, zcount;
	private Mesh fcPath, fbPath, lrPath;
	private Vector3 color;

	public Room(Vector3 roomSize) {
		oneTile();
		xcount = (int) roomSize.x;
		ycount = (int) roomSize.y;
		zcount = (int) roomSize.z;
		color = new Vector3(1f, 1f, 1f);
	}

	public void oneTile() {
		int x = 10, y = 10, z = 10;
		fcPath = new Mesh(true, 4, 5, new VertexAttribute(Usage.Position, 3,
				"a_position"));
		fcPath.setVertices(new float[] { 0, 0, 0, x, 0, 0, x, 0, z, 0, 0, z });
		fcPath.setIndices(new short[] { 0, 1, 2, 3, 0 });

		fbPath = new Mesh(true, 4, 5, new VertexAttribute(Usage.Position, 3,
				"a_position"));
		fbPath.setVertices(new float[] { 0, 0, 0, x, 0, 0, x, y, 0, 0, y, 0 });
		fbPath.setIndices(new short[] { 0, 1, 2, 3, 0 });

		lrPath = new Mesh(true, 4, 5, new VertexAttribute(Usage.Position, 3,
				"a_position"));
		lrPath.setVertices(new float[] { 0, 0, 0, 0, 0, z, 0, y, z, 0, y, 0 });
		lrPath.setIndices(new short[] { 0, 1, 2, 3, 0 });
	}

	public void render(GL10 gl, int renderType) {
		gl.glPushMatrix();
		for (float x = 0; x < xcount; x += 10) {
			for (float z = 0; z < zcount; z += 10) {
				gl.glPushMatrix();
				gl.glTranslatef(x, 0, z);
				fcPath.render(renderType);
				gl.glTranslatef(0, ycount, 0);
				fcPath.render(renderType);
				gl.glPopMatrix();
			}
		}

		for (float x = 0; x < xcount; x += 10) {
			for (float y = 0; y < ycount; y += 10) {
				gl.glPushMatrix();
				gl.glTranslatef(x, y, 0);
				fbPath.render(renderType);
				// gl.glTranslatef(0, 0, zcount);
				// fbPath.render(renderType);
				gl.glPopMatrix();
			}
		}

		for (float z = 0; z < zcount; z += 10) {
			for (float y = 0; y < ycount; y += 10) {
				gl.glPushMatrix();
				gl.glTranslatef(0, y, z);
				lrPath.render(renderType);
				gl.glTranslatef(xcount, 0, 0);
				lrPath.render(renderType);
				gl.glPopMatrix();
			}
		}
		gl.glPopMatrix();
	}

	public Room setColor(float r, float g, float b) {
		color = new Vector3(r, g, b);
		return this;
	}

	public void renderWireframe(GL10 gl) {
		gl.glColor4f(color.x, color.y, color.z, 1);
		render(gl, GL10.GL_LINE_STRIP);
	}

}
