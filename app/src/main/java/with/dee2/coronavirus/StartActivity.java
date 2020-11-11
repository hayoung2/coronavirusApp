package with.dee2.coronavirus;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

public class StartActivity extends AppCompatActivity {

    TextView maskText,creator;
    ImageView coronaLogo;
    LinearLayout start_background;
    AnimationDrawable animationDrawable;

    Animation top,bottom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        start_background=findViewById(R.id.start_background);
        animationDrawable=(AnimationDrawable) start_background.getBackground();
        animationDrawable.setEnterFadeDuration(1500);
        animationDrawable.setExitFadeDuration(1700);
        animationDrawable.start();

        creator=findViewById(R.id.creator);
        maskText =findViewById(R.id.maskText);
         coronaLogo=findViewById(R.id.coronaLogo);
        top = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.top_animation);
        bottom = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.bottom_animation);

        Handler mHandler = new Handler();

        coronaLogo.startAnimation(top);
        maskText.startAnimation(bottom);
        creator.startAnimation(bottom);


        mHandler.postDelayed(new Runnable()  {
            public void run() {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
              ActivityOptionsCompat options=ActivityOptionsCompat.makeSceneTransitionAnimation(StartActivity.this,coronaLogo, "imageTransition");
               startActivity(intent,options.toBundle());
                finish();
            }
        }, 4500); //
    }
}
