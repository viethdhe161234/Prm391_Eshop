package prm392.fpt.edu.vn.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

import prm392.fpt.edu.vn.R;
import prm392.fpt.edu.vn.adapter.ItemsRecyclerAdapter;
import prm392.fpt.edu.vn.domain.Items;

public class ItemsActivity extends AppCompatActivity {

    private FirebaseFirestore mStore;
    List<Items> mItemsList;
    private RecyclerView itemRecyclerView;
    private ItemsRecyclerAdapter itemsRecyclerAdapter;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_items);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        String type = getIntent().getStringExtra("type");

        mStore=FirebaseFirestore.getInstance();
        mItemsList=new ArrayList<>();
        mToolbar = findViewById(R.id.item_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Items");

        itemRecyclerView=findViewById(R.id.items_recycler);
        itemRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        itemsRecyclerAdapter=new ItemsRecyclerAdapter(this,mItemsList);
        itemRecyclerView.setAdapter((itemsRecyclerAdapter));

        if(type == null || type.isEmpty()){
            mStore.collection("All").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for(DocumentSnapshot doc:task.getResult().getDocuments()){
                            Log.i("TAG", "onComplete: "+doc.toString());
                            Items items=doc.toObject(Items.class);
                            mItemsList.add(items);
                            itemsRecyclerAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }
        if(type!=null && type.equalsIgnoreCase("man")){
            mStore.collection("All").whereEqualTo("type","man").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for(DocumentSnapshot doc:task.getResult().getDocuments()){
                            Log.i("TAG", "onComplete: "+doc.toString());
                            Items items=doc.toObject(Items.class);
                            mItemsList.add(items);
                            itemsRecyclerAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }

        if(type!=null && type.equalsIgnoreCase("woman")){
            mStore.collection("All").whereEqualTo("type","woman").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for(DocumentSnapshot doc:task.getResult().getDocuments()){
                            Log.i("TAG", "onComplete: "+doc.toString());
                            Items items=doc.toObject(Items.class);
                            mItemsList.add(items);
                            itemsRecyclerAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }

        if(type!=null && type.equalsIgnoreCase("kid")){
            mStore.collection("All").whereEqualTo("type","kid").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for(DocumentSnapshot doc:task.getResult().getDocuments()){
                            Log.i("TAG", "onComplete: "+doc.toString());
                            Items items=doc.toObject(Items.class);
                            mItemsList.add(items);
                            itemsRecyclerAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }
    }
}