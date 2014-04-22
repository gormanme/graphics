package net.obviam.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class UhmazingSurfaceView extends GLSurfaceView 
{	
	private GlRenderer mRenderer;
    
	public UhmazingSurfaceView(Context context) 
	{
		super(context);		
	}
	
	public UhmazingSurfaceView(Context context, AttributeSet attrs) 
	{
		super(context, attrs);		
	}


	// Hides superclass method.
	public void setRenderer(GlRenderer renderer) 
	{
		mRenderer = renderer;
		super.setRenderer(renderer);
	}
}
