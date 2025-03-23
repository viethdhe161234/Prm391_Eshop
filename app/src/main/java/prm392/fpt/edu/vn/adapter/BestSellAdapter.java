package prm392.fpt.edu.vn.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import prm392.fpt.edu.vn.R;
import prm392.fpt.edu.vn.activities.DetailActivity;
import prm392.fpt.edu.vn.domain.BestSell;

public class BestSellAdapter extends RecyclerView.Adapter<BestSellAdapter.ViewHolder> {
    Context context;
    List<BestSell> mBestSellList;
    public BestSellAdapter(Context context, List<BestSell> mBestSellList) {
        this.context = context;
        this.mBestSellList = mBestSellList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_bestsell_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mBsCost.setText(mBestSellList.get(position).getPrice() + "$");
        holder.mBsName.setText(mBestSellList.get(position).getName());
        Glide.with(context).load(mBestSellList.get(position).getImg_url()).into(holder.mBsImage);

        holder.mBsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("detail", mBestSellList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBestSellList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView mBsImage;
        TextView mBsCost;
        TextView mBsName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mBsImage = itemView.findViewById(R.id.bs_img);
            mBsCost = itemView.findViewById(R.id.bs_cost);
            mBsName = itemView.findViewById(R.id.bs_name);

        }
    }
}
