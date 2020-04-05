package com.sample.picasso.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sample.picasso.Model.Widget;
import com.sample.picasso.R;
import com.sample.picasso.ViewHolder.WidgetViewHolder;

import java.util.List;

public class WidgetAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<Widget> widgetList;

    public WidgetAdapter(Context context, List<Widget> widgetList) {
        this.context = context;
        this.widgetList = widgetList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WidgetViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_widget,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof WidgetViewHolder)
        {
            WidgetViewHolder viewHolder = (WidgetViewHolder) holder;
            viewHolder.widgetTextView.setText(widgetList.get(position).getWidgetName());
        }
    }

    @Override
    public int getItemCount() {
        return widgetList.size();
    }
}
