package com.example.alex.capstone.activities.favorites.adapters.recyclerview;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.alex.capstone.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewHolderFavoriteEntry extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.rv_favorite_entry)
    TextView mFavoriteEntryTv;
    @BindView(R.id.rv_favorite_entry_container)
    ConstraintLayout constraintLayout;
    private String _id;
    private final EntryOnclickListener listener;

    public ViewHolderFavoriteEntry(View itemView, final EntryOnclickListener listener) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        this.listener = listener;
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onListItemClick(_id);
            }
        });
    }

    @Override
    public void onClick(View v) {
        listener.onListItemClick(get_id());
    }

    public TextView getmFavoriteEntryTv() {
        return mFavoriteEntryTv;
    }

    public void setmFavoriteEntryTv(TextView mFavoriteEntryTv) {
        this.mFavoriteEntryTv = mFavoriteEntryTv;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
