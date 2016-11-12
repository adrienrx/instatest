package net.sparkeek.instatest.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.sparkeek.instatest.R;
import net.sparkeek.instatest.models.recent.Datum;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by adrienrx on 30/09/2016.
 */

public class InstAdapter extends RecyclerView.Adapter<InstAdapter.ViewHolder> {

    private ArrayList<Datum> mDataset;
    private Context mContext;
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_profil_img) ImageView mTimelineImg;
        @BindView(R.id.item_profil_desc) TextView mTvDesc;
        public ViewHolder(View v, Context ctx) {
            super(v);
            mContext = ctx;
            ButterKnife.bind(this ,v);
        }
    }

    public InstAdapter(ArrayList<Datum> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public InstAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profil, parent, false);
        ViewHolder vh = new ViewHolder(v, parent.getContext());
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Datum dataToFill = mDataset.get(position);
        Picasso.with(mContext).load(dataToFill.getImages().getStandardResolution().getUrl()).into(holder.mTimelineImg);
        holder.mTvDesc.setText(dataToFill.getCaption().getText());

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
