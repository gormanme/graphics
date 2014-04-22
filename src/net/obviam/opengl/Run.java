package net.obviam.opengl;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.util.Log;
import android.widget.Button;
import android.widget.SeekBar.*;
import android.widget.SeekBar;


public class Run extends Activity  implements OnSeekBarChangeListener{
	
	/** The OpenGL view */
	private UhmazingSurfaceView glSurfaceView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // requesting to turn the title OFF
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // making it full screen
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Initiate the Open GL view and
        // create an instance with this activity
       // glSurfaceView = new UhmazingSurfaceView(this);
        
        // set our renderer to be the main renderer with
        // the current activity context
     //   setContentView(glSurfaceView);

        setContentView(R.layout.main);

        glSurfaceView = (UhmazingSurfaceView)findViewById(R.id.gl_surface_view);
        glSurfaceView.setRenderer(new GlRenderer(this));

        if(glSurfaceView == null)
        	Log.e("Meghan", "glSurfaceView is null!!!");

        SeekBar seekbar = (SeekBar)findViewById(R.id.bet_seekbar); // make seekbar object
        seekbar.setOnSeekBarChangeListener(this); 
        seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                    boolean fromUser) {
                // TODO Auto-generated method stub
                Button button = (Button)findViewById(R.id.button_bet);
                button.setText("Bet: $"+progress);

            }
        });

    }

    public void update_bet(View view){

    }

    public void fold(View view){
    	((Button)findViewById(R.id.button_bet)).setEnabled(false);
    }

	/**
	 * Remember to resume the glSurface
	 */
	@Override
	protected void onResume() {
		super.onResume();
		glSurfaceView.onResume();
	}

	/**
	 * Also pause the glSurface
	 */
	@Override
	protected void onPause() {
		super.onPause();
		glSurfaceView.onPause();
	}

}