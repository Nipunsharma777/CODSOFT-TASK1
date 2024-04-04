package com.world.to_dolistapp;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<TaskModel> taskList;
    private AdapterCallback callback;
    private AdapterCallback2 callback2;

    public TaskAdapter(List<TaskModel> taskList) {
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new ViewHolder(view);
    }

    public interface AdapterCallback2 {
        void onCheckBoxClicked2(boolean isChecked2);
    }

    public void setCallback2(AdapterCallback2 callback2) {
        this.callback2 = callback2;
    }

    public interface AdapterCallback {
        void onCheckBoxClicked(boolean isChecked);
    }

    public void setCallback(AdapterCallback callback) {
        this.callback = callback;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TaskModel task = taskList.get(position);
        holder.taskNameTextView.setText(task.getTaskName());
        holder.dueDateTextView.setText(task.getDueDate());
        holder.priorityTextView.setText(task.getPriority());

        holder.checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (isChecked) {

                    holder.taskNameTextView.setText("  Task Completed");
                    holder.dueDateTextView.setText("Well Done");
                    holder.priorityTextView.setAlpha(0);

                    if (callback != null) {
                        callback.onCheckBoxClicked(isChecked);
                    }

                }

            }
        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (callback2 != null) {
                    callback2.onCheckBoxClicked2(true);
                }

                int adapterPosition = holder.getAdapterPosition();

                taskList.remove(adapterPosition);
                notifyItemRemoved(adapterPosition);

            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView taskNameTextView;
        TextView dueDateTextView;
        TextView priorityTextView;
        CheckBox checkBox3;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskNameTextView = itemView.findViewById(R.id.taskNameTextView);
            dueDateTextView = itemView.findViewById(R.id.dueDateTextView);
            priorityTextView = itemView.findViewById(R.id.priorityTextView);
            checkBox3 = itemView.findViewById(R.id.checkBox3);
            imageView = itemView.findViewById(R.id.imageView);
        }

    }

}