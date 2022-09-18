package com.example.listadetarefas.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listadetarefas.R;
import com.example.listadetarefas.model.ListaTarefas;

import java.util.List;

public class AdapterListaTarefas extends RecyclerView.Adapter<AdapterListaTarefas.MyViewHolder>
{
    private List<ListaTarefas> listaTarefas;

    public AdapterListaTarefas(List<ListaTarefas> lista)
    {
        this.listaTarefas = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_tarefa_adapter, parent, false);
        return  new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        ListaTarefas listaTarefas1 =  listaTarefas.get(position);
        holder.tarefa.setText( listaTarefas1.getNomeTarefa() );

        Log.i("tarefaAdapter", listaTarefas1.getNomeTarefa() );
    }

    @Override
    public int getItemCount()
    {
        return this.listaTarefas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {   TextView tarefa;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tarefa = itemView.findViewById(R.id.textTarefa);
        }
    }
}
