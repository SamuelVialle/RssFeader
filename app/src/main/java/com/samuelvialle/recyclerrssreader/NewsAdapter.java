package com.samuelvialle.recyclerrssreader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private Context context;
    private List<NewsModel> postList;
    private View.OnClickListener mOnItemClickListener;

    public NewsAdapter(Context context, List<NewsModel> postList) {
        this.context = context;
        this.postList = postList;
    }

    /**
     * Le ViewHolder est directement intégré dans l'adapter
     **/
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            imageView = view.findViewById(R.id.imageView);

            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    /** Les 3 classes abstraites du RecyclerView **/
    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull NewsAdapter.MyViewHolder holder, int position) {
        NewsModel post = postList.get(position);
        holder.title.setText(post.getTitle());
        Glide.with(context).load(post.getImageUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    /** Gestion du click sur un item **/
    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }
}

