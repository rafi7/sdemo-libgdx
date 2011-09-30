package com.sdemo.util;

// rendering a floorgrid on x, z - NOT x,y

import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class FloorGrid {
	int xcount, zcount;
	private Mesh path;
	private Vector3 color = new Vector3(1, 1, 1);

	public FloorGrid(int tx, int tz) {
	}

	public FloorGrid(Vector2 fieldSize) {
		path = oneTile(1, 0, 1);
		xcount = (int) fieldSize.x;
		zcount = (int) fieldSize.y;
	}

	public Mesh oneTile(int x, int y, int z) {
		path = new Mesh(true, 4, 5, new VertexAttribute(Usage.Position, 3,
				"a_position"));
		path.setVertices(new float[] { 0, 0, 0, x, 0, 0, x, 0, z, 0, 0, z });
		path.setIndices(new short[] { 0, 1, 2, 3, 0 });
		return path;
	}

	public void render(GL10 gl, int renderType) {
		gl.glPushMatrix();
		for (float x = 0; x < xcount; x ++) {
			for (float z = 0; z < zcount; z ++) {
				gl.glPushMatrix();
				gl.glTranslatef(x, 0, z);
				path.render(renderType);
				gl.glPopMatrix();
			}
		}
		gl.glPopMatrix();
	}

	public FloorGrid setColor(float r, float g, float b) {
		color = new Vector3(r, g, b);
		return this;
	}

	public void renderWireframe(GL10 gl) {
		gl.glColor4f(color.x, color.y, color.z, 1);
		render(gl, GL10.GL_LINE_STRIP);
	}

}
