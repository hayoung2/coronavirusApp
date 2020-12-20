package with.dee2.coronavirus;

import android.graphics.Path;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import kr.co.bootpay.BootpayAnalytics;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainFragment extends Fragment {

    TextView decideCnt,deathCnt,examCnt,accExamCnt,careCnt,clearCnt,dateView,dayDecideCnt,dateValue;
    EditText date;
    Button search;
    public String data="";
    String yesterday="";

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
        deathCnt= v.findViewById(R.id.death_cnt);
        decideCnt=v.findViewById(R.id.decide_cnt);
        examCnt=v.findViewById(R.id.exam_cnt);
        accExamCnt=v.findViewById(R.id.acc_exam_cnt);
        careCnt=v.findViewById(R.id.care_cnt);
        clearCnt=v.findViewById(R.id.clear_cnt);
        dayDecideCnt=v.findViewById(R.id.dayDecideCnt);
        dateView=v.findViewById(R.id.dateView);
        search=v.findViewById(R.id.search);
        date=v.findViewById(R.id.date);
        dateValue=v.findViewById(R.id.dateValue);

        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyyMMdd");
        Date dateV =new Date();
        dateV = new Date(dateV.getTime()+(1000*60*60*24*-1));
        dateValue.setText(simpleDate.format(dateV));
        ExampleThread2 ex1=new ExampleThread2();
        AsyncTask<String, String, String> tmp=ex1.execute();
        data=simpleDate.format(dateV).replaceAll(" ","");

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data=date.getText().toString();
                ExampleThread2 ex1=new ExampleThread2();
                AsyncTask<String, String, String> tmp=ex1.execute();
                dateValue.setText(data);
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
                        StringBuilder("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson?serviceKey=mftadouy%2Bo9ROaRw7EADk02FYgh06%2BF%2FXs1rAnkzfifweeV%2Bhg22qr3lLY93MJTTtczz%2FG33BnlimtbBPwur8Q%3D%3D&pageNo=1&numOfRows=10&startCreateDt="+data+"&endCreateDt="+data); /*URL*/
                Log.i("성공","1번");

                data=Integer.toString(Integer.parseInt(data)-1);
                StringBuilder urlBuilder2 = new
                        StringBuilder("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson?serviceKey=mftadouy%2Bo9ROaRw7EADk02FYgh06%2BF%2FXs1rAnkzfifweeV%2Bhg22qr3lLY93MJTTtczz%2FG33BnlimtbBPwur8Q%3D%3D&pageNo=1&numOfRows=10&startCreateDt="+data+"&endCreateDt="+data); /*URL*/
                Log.i("성공","1번");



                URL url = new URL(urlBuilder.toString());
                URL url2 = new URL(urlBuilder2.toString());
                Log.i("성공","2번");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
                Log.i("성공","3번");
                conn.setRequestMethod("GET");
                conn2.setRequestMethod("GET");
                Log.i("성공","4번");
                conn.setRequestProperty("Content-type", "application/json");
                conn2.setRequestProperty("Content-type", "application/json");


                BufferedReader rd;
                BufferedReader rd2;

                if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                    rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                } else {
                    rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                }

                if (conn2.getResponseCode() >= 200 && conn2.getResponseCode() <= 300) {
                    rd2 = new BufferedReader(new InputStreamReader(conn2.getInputStream()));
                } else {
                    rd2 = new BufferedReader(new InputStreamReader(conn2.getErrorStream()));
                }

                final StringBuilder sb = new StringBuilder();
                final StringBuilder sb2 = new StringBuilder();
                String line;
                String line2;
                while ((line = rd.readLine()) != null) {
                    sb.append(line);
                }
                while ((line2 = rd2.readLine()) != null) {
                    sb2.append(line2);
                }

                rd.close();
                conn.disconnect();
                rd2.close();
                conn2.disconnect();
                new Handler(Looper.getMainLooper()).post(new Runnable(){
                    @Override
                    public void run() {
                        int count=sb.toString().indexOf("deathCnt");
                        int decide_cnt=sb.toString().indexOf("decideCnt");

                        deathCnt.setText(sb.toString().substring(count+6,count+20).replaceAll("[^0-9]", "")+"명");
                        decideCnt.setText(sb.substring(decide_cnt+6,decide_cnt+20).replaceAll("[^0-9]", "")+"명");

                        decide_cnt=sb2.toString().indexOf("decideCnt");
                        dayDecideCnt.setText("일일 확진자 : "+Integer.toString(Integer.parseInt(decideCnt.getText().toString().replaceAll("[^0-9]", ""))
                        -Integer.parseInt(sb2.substring(decide_cnt,decide_cnt+25).replaceAll("[^0-9]", "")))+"명");


                        count=sb.toString().indexOf("examCnt");
                        examCnt.setText(sb.toString().substring(count+6,count+20).replaceAll("[^0-9]", "")+"명");
                        count=sb.toString().indexOf("accExamCnt");
                        accExamCnt.setText(sb.toString().substring(count+6,count+20).replaceAll("[^0-9]", "")+"명");
                        count=sb.toString().indexOf("careCnt");
                        careCnt.setText(sb.toString().substring(count+6,count+20).replaceAll("[^0-9]", "")+"명");
                        count=sb.toString().indexOf("clearCnt");
                        clearCnt.setText(sb.toString().substring(count+6,count+20).replaceAll("[^0-9]", "")+"명");
                    }
                });

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


}