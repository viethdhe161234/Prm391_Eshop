package prm392.fpt.edu.vn.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import prm392.fpt.edu.vn.R;

public class AddAddressActivity extends AppCompatActivity {

    private EditText mName;
    private EditText mCity;
    private EditText mAddress;
    private EditText mCode;
    private EditText mNumber;
    private Button mAddAddressBtn;
    private FirebaseFirestore mStore;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_address);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mName = findViewById(R.id.ad_name);
        mCity = findViewById(R.id.ad_city);
        mAddress = findViewById(R.id.ad_address);
        mCode = findViewById(R.id.ad_code);
        mNumber = findViewById(R.id.ad_phone);

        mStore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        mAddAddressBtn = findViewById(R.id.ad_add_address);
        mAddAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mName.getText().toString();
                String city = mCity.getText().toString();
                String address = mAddress.getText().toString();
                String code = mCode.getText().toString();
                String number = mNumber.getText().toString();
                String final_address = "";

                if (!name.isEmpty())
                    final_address += name + ", ";
                if (!city.isEmpty())
                    final_address += city + ", ";
                if (!address.isEmpty())
                    final_address += address + ", ";
                if (!code.isEmpty())
                    final_address += code + ", ";
                if (!number.isEmpty())
                    final_address += number + ", ";

                Map<String,String> mMap = new HashMap<>();
                mMap.put("address", final_address);

                mStore.collection("User").document(mAuth.getCurrentUser().getUid())
                        .collection("Address").add(mMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(AddAddressActivity.this, "Address Added", Toast.LENGTH_LONG).show();
                                    finish();
                                }
                            }
                        });
            }
        });
    }
}