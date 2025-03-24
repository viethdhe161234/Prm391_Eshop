package prm392.fpt.edu.vn.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import prm392.fpt.edu.vn.R;

public class PaymentActivity extends AppCompatActivity {

    TextView mSub;
    TextView mTotal;
    Button payBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        double amount;
        amount = getIntent().getDoubleExtra("amount",0.0);
        mSub = findViewById(R.id.sub_total);
        mTotal = findViewById(R.id.total_amt);
        payBtn = findViewById(R.id.pay_btn);

        mSub.setText(amount + "$");
        mTotal.setText(amount + "$");
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PaymentActivity.this, "Checked", Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(PaymentActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 2000);
            }
        });

    }
}