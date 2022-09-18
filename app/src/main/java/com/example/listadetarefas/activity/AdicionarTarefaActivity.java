package com.example.listadetarefas.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.listadetarefas.R;
import com.example.listadetarefas.helper.TarefaDAO;
import com.example.listadetarefas.model.ListaTarefas;
import com.google.android.material.textfield.TextInputEditText;

public class AdicionarTarefaActivity extends AppCompatActivity
{
    private TextInputEditText editTarefa;
    private  ListaTarefas tarefaAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);
        editTarefa = findViewById(R.id.textTarefa);

        //Recupera tarefa, caso seja edição
        tarefaAtual = (ListaTarefas) getIntent().getSerializableExtra("tarefaSelecionada");

        //Configurar tarefa na caixa de texto
        if(tarefaAtual !=null)
        {
            editTarefa.setText(tarefaAtual.getNomeTarefa());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.itemSalvar:
                //Executa ação para o item salvar
                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());

                if (tarefaAtual != null)
                {     //Edição de tarefa
                    String nomeTarefa = editTarefa.getText().toString();

                    if (!nomeTarefa.isEmpty())
                    {
                        ListaTarefas listaTarefas = new ListaTarefas();
                        listaTarefas.setNomeTarefa(nomeTarefa);
                        listaTarefas.setId(tarefaAtual.getId());

                        //Atualizar no bando de dados
                        if (tarefaDAO.atualizar(listaTarefas))
                        {
                            finish();
                            Toast.makeText(this, "Sucesso ao atualizar tarefa!", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(this, "Erro ao atualizar tarefa!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    String nomeTarefa = editTarefa.getText().toString();
                    if (!nomeTarefa.isEmpty())
                    {
                        //Salvar uma tarefa
                        ListaTarefas listaTarefas = new ListaTarefas();
                        listaTarefas.setNomeTarefa(nomeTarefa);

                        if (tarefaDAO.salvar(listaTarefas))
                        {
                            finish();
                            Toast.makeText(this, "Sucesso ao salvar tarefa!", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(this, "Erro ao salvar tarefa!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}