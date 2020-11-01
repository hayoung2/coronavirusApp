package with.dee2.coronavirus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView; // 바텀 네비게이션 뷰
    private FragmentManager fm;
    private FragmentTransaction ft;
    private MainFragment mainFragment;
    private MarketFragment marketFragment;
    private CheckFragment checkFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.nav_home:
                        setFrag(0);
                        break;
                    case R.id.nav_check:
                        setFrag(1);
                        break;
                    case R.id.nav_market:
                        setFrag(2);
                        break;
                }
                return true;
            }
        });

        mainFragment=new MainFragment();
        checkFragment=new CheckFragment();
        marketFragment=new MarketFragment();
        setFrag(0); // 첫 프래그먼트 화면 지정
    }
    private void setFrag(int n)
    {
        fm = getSupportFragmentManager();
        ft= fm.beginTransaction();
        switch (n)
        {
            case 0:
                ft.replace(R.id.Main_Frame,mainFragment);
                ft.commit();
                break;

            case 1:
                ft.replace(R.id.Main_Frame,checkFragment);
                ft.commit();
                break;

            case 2:
                ft.replace(R.id.Main_Frame,marketFragment);
                ft.commit();
                break;
        }
    }

}