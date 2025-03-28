package prm392.fpt.edu.vn.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import prm392.fpt.edu.vn.R;
import prm392.fpt.edu.vn.domain.BestSell;
import prm392.fpt.edu.vn.domain.Feature;
import prm392.fpt.edu.vn.domain.Items;

public class DetailActivity extends AppCompatActivity {
    private ImageView mImage;
    private TextView mItemName;
    private TextView mPrice;
    private Button mItemRating;
    private TextView mItemRatDesc;
    private TextView mItemDesc;
    private Button mAddToCArt;
    private Button mBuyBtn;
    FirebaseFirestore mStore;
    FirebaseAuth mAuth;

    Feature feature = null;
    BestSell bestSell = null;
    Items items = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mStore =FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        Object obj = getIntent().getSerializableExtra("detail");
        if (obj instanceof Feature)
            feature = (Feature) obj;
        else if (obj instanceof BestSell)
            bestSell = (BestSell) obj;
        else
            items = (Items) obj;
        //Toast.makeText(this, "" + feature.getName(), Toast.LENGTH_LONG).show();


        mImage = findViewById(R.id.item_img);
        mItemName = findViewById(R.id.item_name);
        mPrice = findViewById(R.id.item_price);
        mItemRating = findViewById(R.id.item_rating);
        mItemRatDesc = findViewById(R.id.item_rat_des);
        mItemDesc = findViewById(R.id.item_des);
        mAddToCArt = findViewById(R.id.item_add_cart);
        mBuyBtn = findViewById(R.id.item_buy_now);

        if (feature != null) {
            Glide.with(getApplicationContext()).load(feature.getImg_url()).into(mImage);
            mItemName.setText(feature.getName());
            mPrice.setText(feature.getPrice() + "$");
            mItemRating.setText(feature.getRating() + "");
            if (feature.getRating() > 3) {
                mItemRatDesc.setText("Very Good");
            } else
                mItemRatDesc.setText("Good");
            mItemDesc.setText(feature.getDescription());
        }

        if (bestSell != null) {
            Glide.with(getApplicationContext()).load(bestSell.getImg_url()).into(mImage);
            mItemName.setText(bestSell.getName());
            mPrice.setText(bestSell.getPrice() + "$");
            mItemRating.setText(bestSell.getRating() + "");
            if (bestSell.getRating() > 3) {
                mItemRatDesc.setText("Very Good");
            } else
                mItemRatDesc.setText("Good");
            mItemDesc.setText(bestSell.getDescription());
        }

        if (items != null) {
            Glide.with(getApplicationContext()).load(items.getImg_url()).into(mImage);
            mItemName.setText(items.getName());
            mPrice.setText(items.getPrice() + "$");
            mItemRating.setText(items.getRating() + "");
            if (items.getRating() > 3) {
                mItemRatDesc.setText("Very Good");
            } else
                mItemRatDesc.setText("Good");
            mItemDesc.setText(items.getDescription());
        }


        mAddToCArt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mBuyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, AddressActivity.class);
                if(feature != null)
                    intent.putExtra("item", feature);
                if(bestSell != null)
                    intent.putExtra("item", bestSell);
                if (items!= null)
                    intent.putExtra("item", items);
                startActivity(intent);
            }
        });
    }
}