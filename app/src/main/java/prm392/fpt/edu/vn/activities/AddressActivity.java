package prm392.fpt.edu.vn.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import prm392.fpt.edu.vn.R;
import prm392.fpt.edu.vn.adapter.AddressAdapter;
import prm392.fpt.edu.vn.domain.Address;

public class AddressActivity extends AppCompatActivity {

    private RecyclerView mAddressRecyclerView;
    private AddressAdapter mAddressAdapter;
    private Button mAddAddress;
    private Button paymentBtn;
    private List<Address> mAddressList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_address);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mAddressRecyclerView = findViewById(R.id.address_recycler);
        mAddAddress = findViewById(R.id.add_address_btn);
        paymentBtn = findViewById(R.id.payment_btn);

        mAddressList = new ArrayList<>();
        mAddressAdapter = new AddressAdapter(getApplicationContext(), mAddressList);

        mAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddressActivity.this, AddAddressActivity.class);
                startActivity(intent);
            }
        });
    }

}