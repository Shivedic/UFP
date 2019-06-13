package com.example.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apps.realestate.R;
import com.example.item.Enquiry;
import com.example.item.Review;
import com.github.ornolfr.ratingview.RatingView;

import java.util.ArrayList;

public class EnquiriesAdapter extends RecyclerView.Adapter<EnquiriesAdapter.ItemRowHolder2> {

    private ArrayList<Enquiry> dataList;
    private Context mContext;

    public EnquiriesAdapter(Context context, ArrayList<Enquiry> dataList) {
        this.dataList = dataList;
        this.mContext = context;
    }

    @Override
    public ItemRowHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_enquiries, parent, false);
        return new EnquiriesAdapter.ItemRowHolder2(v);
    }

    @Override
    public void onBindViewHolder(EnquiriesAdapter.ItemRowHolder2 holder, final int position) {
        holder.userName.setText(dataList.get(position).getUserName());
        holder.enqDesc.setText(dataList.get(position).getDesc());
        holder.enqResp.setText("@RESPONSE : " + dataList.get(position).getResponse());
        holder.enqPlace.setText(dataList.get(position).getPlace());
    }

    @Override
    public int getItemCount() {
        return (null != dataList ? dataList.size() : 0);
    }

    public class ItemRowHolder2 extends RecyclerView.ViewHolder {
        public TextView userName;
        public TextView enqDesc;
        public TextView enqResp;
        public TextView enqPlace;

        private ItemRowHolder2(View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.username_enq);
            enqDesc = itemView.findViewById(R.id.enq_desc);
            enqResp = itemView.findViewById(R.id.enq_response);
            enqPlace = itemView.findViewById(R.id.enq_place);
        }
    }
}

