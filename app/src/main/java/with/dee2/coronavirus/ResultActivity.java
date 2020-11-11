package with.dee2.coronavirus;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    int value;
    public ResultActivity(){}
    public ResultActivity(int cnt) {
        this.value=cnt;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
    }
}
