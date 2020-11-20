package with.dee2.coronavirus;

import android.graphics.Path;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainFragment extends Fragment {

    TextView text,text1;
    EditText date;
    Button search;
    public String data="";

    public MainFragment(){

    }
    public  MainFragment(String data){
        this.data=data;
    }
   @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_main, container, false);
        text=  (TextView) v.findViewById(R.id.corona);
        text1=v.findViewById(R.id.corona2);
        search=v.findViewById(R.id.search);
        date=v.findViewById(R.id.date);
        ExampleThread2 ex1=new ExampleThread2();
        AsyncTask<String, String, String> tmp=ex1.execute();
        data="20201031";

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data=date.getText().toString();
                ExampleThread2 ex1=new ExampleThread2();
                AsyncTask<String, String, String> tmp=ex1.execute();
            }
        });

        return v;
    }
    public class ExampleThread2 extends AsyncTask<String, String, String>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... integers){
            try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                StringBuilder urlBuilder = new
                        StringBuilder(); /*URL*/
                Log.i("성공","1번");
                URL url = new URL(urlBuilder.toString());
                Log.i("성공","2번");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                Log.i("성공","3번");
                conn.setRequestMethod("GET");
                Log.i("성공","4번");
                conn.setRequestProperty("Content-type", "application/json");

                BufferedReader rd;
                if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                    rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                } else {
                    rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                }

                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = rd.readLine()) != null) {
                    sb.append(line);
                }

                rd.close();

                String tmp=sb.toString();
                int count=sb.indexOf("deathCnt");
                int decide_cnt=sb.indexOf("decideCnt");

                Log.d("dd",sb.toString());
                conn.disconnect();

                text.setText(tmp.substring(count+9,count+11));
                text1.setText(tmp.substring(decide_cnt+10,decide_cnt+15));

            }catch (Exception e){
                Log.i(e.toString(),"에러 남");
            }
            return "ddd";
        }
        @Override
        protected void onProgressUpdate(String... params) {

        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }

    }

//    AsyncTask<String, Void, HttpResponse> asyncTask = new AsyncTask<String, Void, HttpResponse>() {
}