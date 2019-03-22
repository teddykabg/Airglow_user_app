package me.tremor.Airglow_user.service;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import me.tremor.Airglow_user.Activities.PurchaseActivity;
import me.tremor.Airglow_user.R;
import me.tremor.Airglow_user.UI.RegisterFragment;
import me.tremor.Airglow_user.models.EntryTypes;

public class ModalityAdapter extends RecyclerView.Adapter {
    List<EntryTypes> entryList;
    PurchaseActivity mActivity;

    public ModalityAdapter(List<EntryTypes> entryList, PurchaseActivity mActivity) {
        this.entryList= entryList;
        this.mActivity = mActivity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.item_modality,parent,false);

        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((  ListViewHolder)holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return this.entryList.size() ;
    }

    class  ListViewHolder extends RecyclerView.ViewHolder{
        TextView mText;
        ViewGroup mContext;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mText = itemView.findViewById(R.id.m3text);
            this.mContext = mContext;


        }
        public void bindView(int position){
            mText.setText(entryList.get(position).getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mActivity.saveModality(entryList.get(position).getId());
                    mActivity.doTransition();
                }
            });
        }

    }
}
