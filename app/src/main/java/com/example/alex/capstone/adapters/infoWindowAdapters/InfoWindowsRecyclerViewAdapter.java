package com.example.alex.capstone.adapters.infoWindowAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.alex.capstone.R;

import com.example.alex.capstone.model.Schedule;
import com.example.alex.capstone.model.StopArea;

import java.util.List;

public class InfoWindowsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Schedule> mDataSet;
    private Context context;
    public InfoWindowsRecyclerViewAdapter(Context context) {
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.bus_stop_info_rv_layout, parent, false);
        BusStopInfoViewHolder busStopInfoViewHolder = new BusStopInfoViewHolder(view);
        return busStopInfoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BusStopInfoViewHolder busStopInfoViewHolder= (BusStopInfoViewHolder) holder;
        busStopInfoViewHolder.mlineNameTv.setText(mDataSet.get(position).getLine().getShortName());
        busStopInfoViewHolder.mDirectionTv.setText(mDataSet.get(position).getDestination().getName());
        busStopInfoViewHolder.mNextPassageTv.setText(mDataSet.get(position).getJourneys().get(0).getWaitingTime());

    }

    @Override
    public int getItemCount() {
        if(mDataSet==null && mDataSet.isEmpty()) return 0;
        return mDataSet.size();
    }

    public void setmDataSet(StopArea stopArea){
        mDataSet=stopArea.getSchedules();
        notifyDataSetChanged();
    }


    private class BusStopInfoViewHolder extends RecyclerView.ViewHolder{
        private TextView mlineNameTv;
        private TextView mDirectionTv;
        private TextView mNextPassageTv;

        public BusStopInfoViewHolder(View itemView) {
            super(itemView);
            mlineNameTv = itemView.findViewById(R.id.line_name_tv_bus_stop_info);
            mDirectionTv = itemView.findViewById(R.id.direction_tv_bus_stop_info);
            mNextPassageTv = itemView.findViewById(R.id.next_passage_tv_bus_stop_info);
        }

        public TextView getMlineNameTv() {
            return mlineNameTv;
        }

        public void setMlineNameTv(TextView mlineNameTv) {
            this.mlineNameTv = mlineNameTv;
        }

        public TextView getmDirectionTv() {
            return mDirectionTv;
        }

        public void setmDirectionTv(TextView mDirectionTv) {
            this.mDirectionTv = mDirectionTv;
        }

        public TextView getmNextPassageTv() {
            return mNextPassageTv;
        }

        public void setmNextPassageTv(TextView mNextPassageTv) {
            this.mNextPassageTv = mNextPassageTv;
        }
    }
}
