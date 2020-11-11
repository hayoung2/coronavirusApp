package with.dee2.coronavirus;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spark.submitbutton.SubmitButton;

import javax.xml.transform.Result;

public class CheckFragment extends Fragment {

    SubmitButton spark;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v=inflater.inflate(R.layout.fragment_check, container, false);
        spark=v.findViewById(R.id.spark_button);

        spark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread thread=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3500);
                            Intent intent=new Intent(getActivity(), ResultActivity.class);
                            startActivity(intent);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();

            }
        });


        return v;
    }
}