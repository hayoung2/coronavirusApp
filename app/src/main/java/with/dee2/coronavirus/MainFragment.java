package with.dee2.coronavirus;

import android.graphics.Path;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainFragment extends Fragment {

    TextView text;
//http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson?serviceKey=mftadouy%2Bo9ROaRw7EADk02FYgh06%2BF%2FXs1rAnkzfifweeV%2Bhg22qr3lLY93MJTTtczz%2FG33BnlimtbBPwur8Q%3D%3D&pageNo=1&numOfRows=10&startCreateDt=20200310&endCreateDt=20200315
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ExampleThread ex1=new ExampleThread();
        ex1.execute(2);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_main, container, false);
    }


//    AsyncTask<String, Void, HttpResponse> asyncTask = new AsyncTask<String, Void, HttpResponse>() {
}