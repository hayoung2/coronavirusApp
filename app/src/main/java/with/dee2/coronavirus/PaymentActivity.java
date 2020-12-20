package with.dee2.coronavirus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import kr.co.bootpay.Bootpay;
import kr.co.bootpay.BootpayAnalytics;
import kr.co.bootpay.enums.UX;
import kr.co.bootpay.listener.CancelListener;
import kr.co.bootpay.listener.CloseListener;
import kr.co.bootpay.listener.ConfirmListener;
import kr.co.bootpay.listener.DoneListener;
import kr.co.bootpay.listener.ErrorListener;
import kr.co.bootpay.listener.ReadyListener;
import kr.co.bootpay.model.BootExtra;
import kr.co.bootpay.model.BootUser;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class PaymentActivity extends AppCompatActivity {

    private int stuck = 10;
    Button payment,add;
    TextView itemName,itemPrice,number;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        BootpayAnalytics.init(this, "");
        Intent intent=getIntent();
        payment=findViewById(R.id.payment);
        itemName=findViewById(R.id.itemName);
        itemPrice=findViewById(R.id.itemPrice);
        add=findViewById(R.id.add);
        number=findViewById(R.id.number);


        itemName.setText(intent.getStringExtra("name"));
        itemPrice.setText(intent.getStringExtra("price"));

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buyWithAll();
            }
        });
        final int fPrice=parseInt(itemPrice.getText().toString()
                .replace("원","")
                .replace(",","")
                .replaceAll(" ",""));
       add.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               int num=parseInt(number.getText().toString());
               int price=fPrice;
               number.setText(Integer.toString(num+1));
               price *=(num+1);
               itemPrice.setText(price+"원");
           }
       });

    }

    public void buyWithAll() {
        // 결제호출
        BootUser bootUser = new BootUser().setEmail("dudgkdl1@naver.com");
        BootExtra bootExtra = new BootExtra().setQuotas(new int[] {0,2,3});

        Bootpay.init(getFragmentManager())
                .setApplicationId("5fdf37802fa5c2001d038af4") // 해당 프로젝트(안드로이드)의 application id 값
                .setContext(this)
                .setBootUser(bootUser)
                .setBootExtra(bootExtra)
                .setUX(UX.PG_DIALOG)
//                .setUserPhone("010-1234-5678") // 구매자 전화번호
                .setName(itemName.getText().toString()) // 결제할 상품명
                .setOrderId("1234") // 결제 고유번호expire_month
                .setPrice(parseInt(itemPrice.getText().toString().replace("원","").replace(",","").replaceAll(" ",""))) // 결제할 금액
               .onConfirm(new ConfirmListener() { // 결제가 진행되기 바로 직전 호출되는 함수로, 주로 재고처리 등의 로직이 수행
                    @Override
                    public void onConfirm(@Nullable String message) {

                        if (0 < stuck) Bootpay.confirm(message); // 재고가 있을 경우.
                        else Bootpay.removePaymentWindow(); // 재고가 없어 중간에 결제창을 닫고 싶을 경우
                        Log.d("confirm", message);
                    }
                })
                .onDone(new DoneListener() { // 결제완료시 호출, 아이템 지급 등 데이터 동기화 로직을 수행합니다
                    @Override
                    public void onDone(@Nullable String message) {
                        Log.d("done", message);
                        Toast.makeText(getApplicationContext(),"결제 완료되었습니다!",
                                Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(PaymentActivity.this,MainActivity.class));
                    }
                })
                .onReady(new ReadyListener() { // 가상계좌 입금 계좌번호가 발급되면 호출되는 함수입니다.
                    @Override
                    public void onReady(@Nullable String message) {
                        Log.d("ready", message);
                    }
                })
                .onCancel(new CancelListener() { // 결제 취소시 호출
                    @Override
                    public void onCancel(@Nullable String message) {
                        Toast.makeText(getApplicationContext(),"결제가 취소되었습니다.",
                                Toast.LENGTH_SHORT).show();
                        Log.d("cancel", message);

                    }
                })
                .onError(new ErrorListener() { // 에러가 났을때 호출되는 부분
                    @Override
                    public void onError(@Nullable String message) {
                        Log.d("error", message);
                    }
                })
                .onClose(
                        new CloseListener() { //결제창이 닫힐때 실행되는 부분
                            @Override
                            public void onClose(String message) {
                                Log.d("close", "close");
                            }
                        })
                .request();
    }

}