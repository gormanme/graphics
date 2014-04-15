/**
 * 
 */
package net.obviam.opengl;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;

/**
 * @author impaler
 *
 */
public class GlRenderer implements Renderer {

	private Square 		felt;		// the square
	private Square    	card1;
	private Square 		card2;
	private Context 	context;
	
	/** Constructor to set the handed over context */
	public GlRenderer(Context context) {
		this.context = context;
		
		// Set vertice locations
		float felt_verts[] = {
			-1.4f, -2.2f,  0.0f,		// V1 - bottom left
			-1.4f,  2.2f,  0.0f,		// V2 - top left
			 1.4f, -2.2f,  0.0f,		// V3 - bottom right
			 1.4f,  2.2f,  0.0f			// V4 - top right
		};

		float card1_verts[] = {
			-1.1f, -0.7f,  0.0f,		// V1 - bottom left
			-1.1f,  1.0f,  0.0f,		// V2 - top left
			 0.0f, -0.7f,  0.0f,		// V3 - bottom right
			 0.0f,  1.0f,  0.0f			// V4 - top right
		};


		float card2_verts[] = {
			 0.05f, -0.7f,  0.0f,		// V1 - bottom left
			 0.05f,  1.0f,  0.0f,		// V2 - top left
			 1.15f, -0.7f,  0.0f,		// V3 - bottom right
			 1.15f,  1.0f,  0.0f			// V4 - top right
		};

		//Texture coordinates are in "pixel" space at this point
		float card_w = 154.6f;
		float card_h = 369;
		float between_rows = 35.5f;


		float ace_left = 22.0f + card_w * 0;
		float ace_right = ace_left + card_w;
		float ace_top = 395 + (card_h + between_rows) * 3;
		float ace_bottom = ace_top - card_h;

		float heart_left = 22.0f + card_w * 12;
		float heart_right = heart_left + card_w;
		float heart_top = 395 + (card_h + between_rows) * 2;
		float heart_bottom = heart_top - card_h;

		float two_rupees[] = {
							// Mapping coordinates for the vertices
			heart_left, heart_top,		// top left		(V2)
			heart_left, heart_bottom,		// bottom left	(V1)
			heart_right, heart_top,	// top right	(V4)
			heart_right, heart_bottom		// bottom right	(V3)
		};

		float ace_hearts[] = {
							// Mapping coordinates for the vertices
			ace_left, ace_top,		// top left		(V2)
			ace_left, ace_bottom,		// bottom left	(V1)
			ace_right, ace_top,	// top right	(V4)
			ace_right, ace_bottom		// bottom right	(V3)
		};
		float card2_tex[] = {
						// Mapping coordinates for the vertices
			176.0f, 395.0f,		// top left		(V2)
			176.0f, 30.0f,		// bottom left	(V1)
			330.0f, 395.0f,	// top right	(V4)
			330.0f, 30.0f		// bottom right	(V3)
		};

//		float card1_tex[] = {
//						// Mapping coordinates for the vertices
//			22.0f, 395.0f,		// top left		(V2)
//			22.0f, 30.0f,		// bottom left	(V1)
//			175.0f, 395.0f,	// top right	(V4)
//			175.0f, 30.0f		// bottom right	(V3)
//		};

		float card1_tex[] = {
						// Mapping coordinates for the vertices
			22.0f, 796.0f,		// top left		(V2)
			22.0f, 430.0f,		// bottom left	(V1)
			175.0f, 796.0f,	// top right	(V4)
			175.0f, 430.0f		// bottom right	(V3)
		};

		float felt_tex[] = {
						// Mapping coordinates for the vertices
			0.0f, 1.0f,		// top left		(V2)
			0.0f, 0.0f,		// bottom left	(V1)
			1.0f, 1.0f,		// top right	(V4)
			1.0f, 0.0f		// bottom right	(V3)
		};

		card1_tex = pixToTex(card1_tex);
		card2_tex = pixToTex(card2_tex);
		two_rupees = pixToTex(two_rupees);
		ace_hearts = pixToTex(ace_hearts);
		felt = new Square(felt_verts, felt_tex);
		card1 = new Square(card1_verts, ace_hearts);
		card2 = new Square(card2_verts, two_rupees);
	}


	public float[] pixToTex(float[] pix){
		for(int i=0; i<pix.length; ++i){
			pix[i] = pix[i]/2048.0f;
		}
		return pix;
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		//for drawing triangle

		// clear Screen and Depth Buffer
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		// Reset the Modelview Matrix
		gl.glLoadIdentity();

		// Drawing
		gl.glTranslatef(0.0f, 0.0f, -5.0f);		// move 5 units INTO the screen
												// is the same as moving the camera 5 units away
//		gl.glScalef(0.5f, 0.5f, 0.5f);			// scale the square to 50% 
												// otherwise it will be too large
		felt.draw(gl);						// Draw the triangle
		card1.draw(gl);
		card2.draw(gl);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		if(height == 0) { 						//Prevent A Divide By Zero By
			height = 1; 						//Making Height Equal One
		}

		gl.glViewport(0, 0, width, height); 	//Reset The Current Viewport
		gl.glMatrixMode(GL10.GL_PROJECTION); 	//Select The Projection Matrix
		gl.glLoadIdentity(); 					//Reset The Projection Matrix

		//Calculate The Aspect Ratio Of The Window
		GLU.gluPerspective(gl, 45.0f, (float)width / (float)height, 0.1f, 100.0f);

		gl.glMatrixMode(GL10.GL_MODELVIEW); 	//Select The Modelview Matrix
		gl.glLoadIdentity(); 					//Reset The Modelview Matrix
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// Load the texture for the square
		int card_pic = R.drawable.deck;
		int back = R.drawable.felt;
		felt.loadGLTexture(gl, this.context, back);
		card1.loadGLTexture(gl, this.context, card_pic);
		card2.loadGLTexture(gl, this.context, card_pic);

		gl.glEnable(GL10.GL_TEXTURE_2D);			//Enable Texture Mapping ( NEW )
		gl.glShadeModel(GL10.GL_SMOOTH); 			//Enable Smooth Shading
		gl.glClearColor(0.0f, 1.0f, 0.0f, 0.5f); 	//Green Background
		gl.glClearDepthf(1.0f); 					//Depth Buffer Setup
		gl.glEnable(GL10.GL_DEPTH_TEST); 			//Enables Depth Testing
		gl.glDepthFunc(GL10.GL_LEQUAL); 			//The Type Of Depth Testing To Do
		
		//Really Nice Perspective Calculations
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST); 

	}

}
