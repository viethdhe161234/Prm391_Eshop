package prm392.fpt.edu.vn.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

import prm392.fpt.edu.vn.R;
import prm392.fpt.edu.vn.adapter.ItemsRecyclerAdapter;
import prm392.fpt.edu.vn.domain.Items;
import prm392.fpt.edu.vn.fragment.HomeFragment;

public class HomeActivity extends AppCompatActivity {

    Fragment homeFragment;
    private Toolbar mToolbar;
    private FirebaseAuth mAuth;
    private FirebaseFirestore mStore;
    private EditText mSearchtext;
    private List<Items> mItemsList;
    private RecyclerView mItemRecyclerView;
    private ItemsRecyclerAdapter itemsRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        homeFragment = new HomeFragment();
        loadFragment(homeFragment);
        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();

        mToolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(mToolbar);
        //mSearchtext = findViewById(R.id.search_text);

        mItemsList = new ArrayList<>();
        mItemRecyclerView = findViewById(R.id.search_recycler);
        mItemRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        itemsRecyclerAdapter=new ItemsRecyclerAdapter(this,mItemsList);
        mItemRecyclerView.setAdapter(itemsRecyclerAdapter);
//        mSearchtext.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                //searchItem(s.toString());
//            }
//        });
    }

    private void searchItem(String text) {
        if(!text.isEmpty()){
            mStore.collection("All").whereGreaterThanOrEqualTo("name",text).get()
                    .addOnCompleteListener(task -> {
                        if(task.isSuccessful() && task.getResult()!=null){
                            mItemsList.clear();
                            for(DocumentSnapshot doc:task.getResult().getDocuments()){
                                Items items=doc.toObject(Items.class);
                                if(!mItemsList.contains(items)){
                                    mItemsList.add(items);

                                }
                            }
                            itemsRecyclerAdapter=new ItemsRecyclerAdapter(getApplicationContext(),mItemsList);
                            mItemRecyclerView.setAdapter(itemsRecyclerAdapter);
                            itemsRecyclerAdapter.notifyDataSetChanged();

                        }
                    });
        }else{
            mItemsList.clear();
            itemsRecyclerAdapter=new ItemsRecyclerAdapter(getApplicationContext(),new ArrayList<>());
            mItemRecyclerView.setAdapter(itemsRecyclerAdapter);
            itemsRecyclerAdapter.notifyDataSetChanged();
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.logout_btn){
            mAuth.signOut();
            Intent intent=new Intent(HomeActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        return true;
    }

}