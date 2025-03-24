package prm392.fpt.edu.vn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import prm392.fpt.edu.vn.R;
import prm392.fpt.edu.vn.domain.Address;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {
    Context applicationContext;
    List<Address> mAddressList;
    private RadioButton mSelectedRadioButton;
    public AddressAdapter(Context applicationContext, List<Address> mAddressList) {
        this.applicationContext = applicationContext;
        this.mAddressList = mAddressList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(applicationContext).inflate(R.layout.single_address_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mAddress.setText(mAddressList.get(position).getAddress());
        holder.mRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Address address:mAddressList){
                    address.setSelected(false);
                }
                mAddressList.get(position).setSelected(true);
                if (mSelectedRadioButton != null){
                    mSelectedRadioButton.setChecked(false);
                }
                mSelectedRadioButton = (RadioButton)v;
                mSelectedRadioButton.setChecked(true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAddressList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mAddress;
        private RadioButton mRadio;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mAddress = itemView.findViewById(R.id.address_add);
            mRadio = itemView.findViewById(R.id.select_address);
        }
    }
}
