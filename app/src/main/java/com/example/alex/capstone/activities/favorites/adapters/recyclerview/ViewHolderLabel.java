package com.example.alex.capstone.activities.favorites.adapters.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.alex.capstone.R;
import com.example.alex.capstone.model.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewHolderLabel extends RecyclerView.ViewHolder {

    @BindView(R.id.rv_favorite_label)
    TextView mLabelTv;

    public ViewHolderLabel(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public TextView getmLabelTv() {
        return mLabelTv;
    }

    public void setmLabelTv(TextView mLabelTv) {
        this.mLabelTv = mLabelTv;
    }
}
