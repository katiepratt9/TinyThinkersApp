package com.example.tinythinkers;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class ReferenceAdapter extends RecyclerView.Adapter<ReferenceAdapter.ViewHolder> {
    private Context context;
    private List<String> links;
    public ReferenceAdapter(Context context, List<String> links) {
        this.context = context;
        this.links = links;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.reference_item, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textLink.setText(links.get(position));
    }
    @Override
    public int getItemCount() {
        return links.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textLink;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textLink = itemView.findViewById(R.id.textLink);
        }
    }
}