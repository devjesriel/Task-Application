package com.example.lenovo.task_application.presenters.tasklist.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.task_application.R;
import com.example.lenovo.task_application.data.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 29/7/2017.
 */

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskListViewholder> {

    private List<Task> taskList = new ArrayList<>();
    private OnCustomItemClickListener onCustomItemClickListener;

    public TaskListAdapter(List<Task> taskList, OnCustomItemClickListener listener) {
        this.taskList = taskList;
        this.onCustomItemClickListener = listener;
    }

    @Override
    public TaskListViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskListViewholder(itemView);
    }

    @Override
    public void onBindViewHolder(TaskListViewholder holder, final int position) {

        if (taskList.get(position) != null) {
            holder.name.setText(taskList.get(position).getName());
            holder.desc.setText(taskList.get(position).getDescription());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onCustomItemClickListener.onItemClicked(taskList.get(position));
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class TaskListViewholder extends RecyclerView.ViewHolder {

        private TextView name, desc;

        TaskListViewholder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.view_task_name);
            desc = itemView.findViewById(R.id.view_task_desc);
        }
    }

    public interface OnCustomItemClickListener {
        void onItemClicked(Task task);
    }
}
