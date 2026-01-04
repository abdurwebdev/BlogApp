package com.example.madfinal.adapters;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madfinal.R;
import com.example.madfinal.models.BlogPost;

import java.util.List;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.BlogViewHolder> {

    private Context context;
    private List<BlogPost> blogList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(BlogPost blog);
        void onEditClick(BlogPost blog);
        void onDeleteClick(BlogPost blog);
    }

    public BlogAdapter(Context context, List<BlogPost> blogList, OnItemClickListener listener) {
        this.context = context;
        this.blogList = blogList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BlogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_blog_post, parent, false);
        return new BlogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogViewHolder holder, int position) {
        BlogPost blog = blogList.get(position);
        holder.title.setText(blog.getTitle());
        holder.body.setText(blog.getBody());

        holder.itemView.setOnClickListener(v -> listener.onItemClick(blog));

        // Popup Menu (3-dots icon)
        holder.optionsMenu.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(context, holder.optionsMenu);
            popup.inflate(R.menu.blog_item_menu);
            popup.setOnMenuItemClickListener(item -> {
                int id = item.getItemId();
                if (id == R.id.action_edit) {
                    listener.onEditClick(blog);
                    return true;
                } else if (id == R.id.action_delete) {
                    listener.onDeleteClick(blog);
                    return true;
                }
                return false;
            });
            popup.show();
        });
        
        // Context Menu (Long Click) handled in ViewHolder
    }

    @Override
    public int getItemCount() {
        return blogList.size();
    }

    public void updateList(List<BlogPost> newList) {
        blogList = newList;
        notifyDataSetChanged();
    }

    public class BlogViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView title, body;
        ImageView optionsMenu;

        public BlogViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.blog_title);
            body = itemView.findViewById(R.id.blog_body);
            optionsMenu = itemView.findViewById(R.id.options_menu);
            
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select Action");
            MenuItem edit = menu.add(Menu.NONE, 1, 1, "Edit");
            MenuItem delete = menu.add(Menu.NONE, 2, 2, "Delete");

            edit.setOnMenuItemClickListener(item -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onEditClick(blogList.get(position));
                }
                return true;
            });

            delete.setOnMenuItemClickListener(item -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onDeleteClick(blogList.get(position));
                }
                return true;
            });
        }
    }
}