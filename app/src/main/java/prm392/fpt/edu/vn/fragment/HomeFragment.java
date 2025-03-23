package prm392.fpt.edu.vn.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import prm392.fpt.edu.vn.R;
import prm392.fpt.edu.vn.adapter.CategoryAdapter;
import prm392.fpt.edu.vn.adapter.FeatureAdapter;
import prm392.fpt.edu.vn.domain.Category;
import prm392.fpt.edu.vn.domain.Feature;

public class HomeFragment extends Fragment {
    private FirebaseFirestore mStore;
    // Category ---
    private List<Category> mCategoryList;
    private CategoryAdapter mCategoryAdapter;
    private RecyclerView mCatRecyclerView;

    // Feature ---
    private List<Feature> mFeatureList;
    private FeatureAdapter mFeatureAdapter;
    private RecyclerView mFeatureRecyclerView;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mStore = FirebaseFirestore.getInstance();
        mCatRecyclerView = view.findViewById(R.id.category_recycler);
        mFeatureRecyclerView = view.findViewById(R.id.feature_recycler);

        // Category ---
        mCategoryList = new ArrayList<>();
        mCategoryAdapter = new CategoryAdapter(getContext(), mCategoryList);

        mCatRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));
        mCatRecyclerView.setAdapter(mCategoryAdapter);
        // Feature ---
        mFeatureList = new ArrayList<>();
        mFeatureAdapter = new FeatureAdapter(getContext(), mFeatureList);

        mFeatureRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));
        mFeatureRecyclerView.setAdapter(mFeatureAdapter);



        mStore.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Category category = document.toObject(Category.class);
                                mCategoryList.add(category);
                                mCategoryAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Log.w("Tag", "Error getting documents.", task.getException());
                        }
                    }
                });

        mStore.collection("Feature")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Feature feature = document.toObject(Feature.class);
                                mFeatureList.add(feature);
                                mFeatureAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Log.w("Tag", "Error getting documents.", task.getException());
                        }
                    }
                });
        return view;
    }
}