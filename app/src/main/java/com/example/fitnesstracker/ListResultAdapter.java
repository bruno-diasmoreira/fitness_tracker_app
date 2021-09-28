package com.example.fitnesstracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnesstracker.model.Register;

import java.text.SimpleDateFormat;
import java.util.List;

public class ListResultAdapter extends RecyclerView.Adapter<ListResultAdapter.ViewHolder> {


    List<Register> registerList;
    Context context;

    public ListResultAdapter(Context context, List<Register> registerList) {
        this.registerList = registerList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.result_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Register register = registerList.get(position);

        holder.bind(register);

    }

    @Override
    public int getItemCount() {
        return registerList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(Register register) {

            TextView resultImcList = itemView.findViewById(R.id.resultImcList);
            TextView dateImcList = itemView.findViewById(R.id.dateImcList);





            resultImcList.setText(context.getString((register.getType().toString().equalsIgnoreCase("imc")?R.string.imc_response : R.string.tmb_response),register.getResult()));

            dateImcList.setText("Data: "+register.getCreated_date());


        }
    }


}
