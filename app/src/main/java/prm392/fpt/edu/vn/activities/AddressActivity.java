package prm392.fpt.edu.vn.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import prm392.fpt.edu.vn.R;
import prm392.fpt.edu.vn.adapter.AddressAdapter;
import prm392.fpt.edu.vn.domain.Address;
import prm392.fpt.edu.vn.domain.BestSell;
import prm392.fpt.edu.vn.domain.Category;
import prm392.fpt.edu.vn.domain.Feature;
import prm392.fpt.edu.vn.domain.Items;

public class AddressActivity extends AppCompatActivity {

    private RecyclerView mAddressRecyclerView;
    private AddressAdapter mAddressAdapter;
    private Button mAddAddress;
    private Button paymentBtn;
    private List<Address> mAddressList;
    private FirebaseFirestore mStore;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_address);

        Object obj = getIntent().getSerializableExtra("item");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mAddressRecyclerView = findViewById(R.id.address_recycler);
        mAddAddress = findViewById(R.id.add_address_btn);
        paymentBtn = findViewById(R.id.payment_btn);

        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();

        mAddressList = new ArrayList<>();
        mAddressAdapter = new AddressAdapter(getApplicationContext(), mAddressList);
        mAddressRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mAddressRecyclerView.setAdapter(mAddressAdapter);

        mStore.collection("User").document(mAuth.getCurrentUser().getUid())
                        .collection("Address").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for(DocumentSnapshot doc:task.getResult()){
                                Address address = doc.toObject(Address.class);
                                mAddressList.add(address);
                                mAddressAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
        mAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddressActivity.this, AddAddressActivity.class);
                startActivity(intent);
            }
        });
        paymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double amount = 0.0;
                if(obj instanceof Feature){
                    Feature f = (Feature) obj;
                    amount = f.getPrice();
                }
                if(obj instanceof BestSell){
                    BestSell f = (BestSell) obj;
                    amount = f.getPrice();
                }
                if(obj instanceof Items){
                    Items f = (Items) obj;
                    amount = f.getPrice();
                }
                Intent intent = new Intent(AddressActivity.this, PaymentActivity.class);
                intent.putExtra("amount", amount);
                startActivity(intent);
            }
        });
    }
}