package com.example.alex.capstone.activities.favorites.adapters.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alex.capstone.R;
import com.example.alex.capstone.activities.favorites.FavoritesActivity;
import com.example.alex.capstone.data.FavoriteEntry;

import java.util.ArrayList;
import java.util.List;

public class FavoritesRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int LABEL_ITEM = 0;
    private static final int FAVORITE_ITEM = 1;
    private final Context context;

    private List<Object> mDataSet;

    public FavoritesRecyclerViewAdapter(Context c ) {
        context=c;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder ;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;

        switch (viewType){
            case LABEL_ITEM:
                view = inflater.inflate(R.layout.rv_favorite_label_entry_layout,parent,false);
                viewHolder= new ViewHolderLabel(view);
                break;

            case FAVORITE_ITEM:
                view = inflater.inflate(R.layout.rv_favorite_entry_layout,parent,false);
                viewHolder = new ViewHolderFavoriteEntry(view,(FavoritesActivity) context);
                break;

            default:
                viewHolder= null;
        }

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        switch (holder.getItemViewType()){
            case LABEL_ITEM:
                ViewHolderLabel viewHolderLabel = (ViewHolderLabel) holder;
                if (mDataSet.get(position)instanceof Label){
                    viewHolderLabel.mLabelTv.setText(((Label) mDataSet.get(position)).getLabel());
                }
                break;

            case FAVORITE_ITEM:
                ViewHolderFavoriteEntry viewHolderFavoriteEntry = (ViewHolderFavoriteEntry) holder;
                if (mDataSet.get(position) instanceof FavoriteEntry){
                    FavoriteEntry entry =(FavoriteEntry) mDataSet.get(position);
                    viewHolderFavoriteEntry.getmFavoriteEntryTv().setText((entry.getName()));
                    viewHolderFavoriteEntry.set_id(String.valueOf(entry.getFavId()));
                    holder.itemView.setTag(entry.getFavId());
                }
                break;

            default:
                Log.e(FavoritesRecyclerViewAdapter.class.getSimpleName(),context.getString(R.string.unsupported_viewholder)+holder.getItemViewType());
        }
    }

    @Override
    public int getItemViewType(int position) {
        Object o = mDataSet.get(position);
        if(o instanceof Label){
            return LABEL_ITEM;
        }
        else {
            return FAVORITE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        if (mDataSet==null || mDataSet.isEmpty())return 0;
        return mDataSet.size();
    }

    public void setmDataSet(List<FavoriteEntry> favoriteEntries){
        if (favoriteEntries!=null && !favoriteEntries.isEmpty()){
            mDataSet=new ArrayList<>();
            Label label = new Label();
            int i = 0;

            for(FavoriteEntry entry : favoriteEntries){
                if (i==0) {
                    label.setLabel(entry.getCategory());
                    mDataSet.add(label);
                }
                else if (!label.getLabel().equals(favoriteEntries.get(i).getCategory())){
                    label.setLabel(favoriteEntries.get(i).getCategory());
                    mDataSet.add(label);
                }
                mDataSet.add(entry);
                i++;

            }
            notifyDataSetChanged();
        }
    }
}
