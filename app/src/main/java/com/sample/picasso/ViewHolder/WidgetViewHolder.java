package com.sample.picasso.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sample.picasso.R;

public class WidgetViewHolder extends RecyclerView.ViewHolder {

    public TextView widgetTextView;
    public WidgetViewHolder(@NonNull View itemView) {
        super(itemView);

        widgetTextView = itemView.findViewById(R.id.widgetTextView);
    }
}
