package com.example.demux;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.MyViewHolder> implements Filterable {

    private ArrayList<QuestionsItems> questionsItemsArrayList;
    private ArrayList<QuestionsItems> questionsItemsArrayListFull;

    public QuestionsAdapter(ArrayList<QuestionsItems> questionsItemsArrayList) {
        this.questionsItemsArrayList = questionsItemsArrayList;
        questionsItemsArrayListFull=new ArrayList<>(questionsItemsArrayList);
    }

    @NonNull
    @Override
    public QuestionsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_items,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionsAdapter.MyViewHolder holder, int position) {
        holder.QuestionTitle.setText(questionsItemsArrayList.get(position).getTitle());
        holder.QuestionDifficulty.setText(questionsItemsArrayList.get(position).getDifficulty());
        if(questionsItemsArrayList.get(position).getDifficulty()=="Easy"){
            holder.QuestionDifficulty.setBackgroundResource(R.drawable.difficulty_bg_easy);
        }else if(questionsItemsArrayList.get(position).getDifficulty()=="Medium"){
            holder.QuestionDifficulty.setBackgroundResource(R.drawable.difficulty_bg_medium);
        }else if(questionsItemsArrayList.get(position).getDifficulty()=="Hard"){
            holder.QuestionDifficulty.setBackgroundResource(R.drawable.difficulty_bg_hard);
        }
    }

    @Override
    public int getItemCount() {
        return questionsItemsArrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView QuestionTitle;
        TextView QuestionDifficulty;
        TextView QuestionTags;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            QuestionTitle=itemView.findViewById(R.id.item_title);
            QuestionDifficulty=itemView.findViewById(R.id.item_difficulty);
            QuestionTags=itemView.findViewById(R.id.item_tagDetails);
        }
    }

    @Override
    public Filter getFilter() {
        return questionFilter;
    }

    private Filter questionFilter= new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<QuestionsItems> filteredList= new ArrayList<>();

            if(constraint==null || constraint.length()==0){
                filteredList.addAll(questionsItemsArrayListFull);
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (QuestionsItems items : questionsItemsArrayListFull){
                    if(items.getTitle().toLowerCase().contains(filterPattern)){
                        filteredList.add(items);
                    }
                }
            }
            FilterResults results= new FilterResults();
            results.values=filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            questionsItemsArrayList.clear();
            questionsItemsArrayList.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };
}
