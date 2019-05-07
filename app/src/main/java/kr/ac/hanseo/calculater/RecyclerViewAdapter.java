package kr.ac.hanseo.calculater;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private LayoutInflater inflater;
    public static ArrayList<ScoreModel> scoreModels;
    public ScoreProcess scoreProcess;

    public RecyclerViewAdapter(Context context,ArrayList<ScoreModel> scoreModels){

        inflater = LayoutInflater.from(context);
        this.scoreModels=scoreModels;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=inflater.inflate(R.layout.item_score,viewGroup,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {

        CustomViewHolder customViewHolder=(CustomViewHolder)viewHolder;
        customViewHolder.textView.setText((i+1)+"번째 과목");

    }

    @Override
    public int getItemCount() {
//        return itemCount;
        return scoreModels.size();
    }

    private class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        Button button;
        EditText editText;
        CheckBox checkBox;

        public CustomViewHolder(@NonNull final View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.item_subName);
            button=(Button)itemView.findViewById(R.id.item_scoreBtn);
            editText=(EditText)itemView.findViewById(R.id.item_scoreEdt);
            checkBox=(CheckBox)itemView.findViewById(R.id.item_majorCbx);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final PopupMenu popupMenu=new PopupMenu(button.getContext(),view);
                    popupMenu.getMenuInflater().inflate(R.menu.score_menu,popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            button.setText(menuItem.getTitle().toString());
                            scoreModels.get(getAdapterPosition()).setGrade(button.getText().toString());
                            return false;
                        }
                    });
                    popupMenu.show();
                }
            });

            textView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    //scoreModels.get(getAdapterPosition()).setSubName(textView.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (editText.length()==0){
                        return;
                    }else {
                        int value=Integer.parseInt(editText.getText().toString());
                        scoreModels.get(getAdapterPosition()).setScore(value );
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (checkBox.isChecked()){
                        // is major subject
                        scoreModels.get(getAdapterPosition()).setMajor(true);
                    }else {
                        // is not major subject
                        scoreModels.get(getAdapterPosition()).setMajor(true);
                    }
                }
            });

        }
    }
}