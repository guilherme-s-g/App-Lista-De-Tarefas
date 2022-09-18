package com.example.listadetarefas.helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.listadetarefas.model.ListaTarefas;

import java.util.ArrayList;
import java.util.List;

public class TarefaDAO implements ITarefaDAO
{
    private SQLiteDatabase escreve, le;

    public TarefaDAO(Context context)
    {
        DbHelper db = new DbHelper( context );
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(ListaTarefas listaTarefas)
    {
        ContentValues cv = new ContentValues();
        cv.put("nome",listaTarefas.getNomeTarefa());
        try
        {
            escreve.insert(DbHelper.TABELA_TAREFAS, null, cv);
            Log.e("INFO"," Tarefa salva com sucesso!" );
        }
        catch (Exception e)
        {
            Log.e("INFO","Error ao salvar tarefa" + e.getMessage());
           return false;
        }
        return true;
    }

    @Override
    public boolean atualizar(ListaTarefas listaTarefas)
    {
        ContentValues cv = new ContentValues();
        cv.put("nome",listaTarefas.getNomeTarefa());

        try
        {
            String[] args = {String.valueOf(listaTarefas.getId())};
            escreve.update(DbHelper.TABELA_TAREFAS, cv, "id=?", args );
            Log.i("INFO", "Tarefa atualizada com sucesso!");
        }
        catch (Exception e)
        {
            Log.e("INFO","Error ao atualizar tarefa" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deletar(ListaTarefas listaTarefas)
    {

        try
        {
            String [] args = {String.valueOf(listaTarefas.getId())};
            escreve.delete(DbHelper.TABELA_TAREFAS,"id=?", args);
            Log.i("INFO", "Tarefa removida com sucesso!");
        }
        catch (Exception e)
        {
            Log.e("INFO","Error ao remover tarefa" + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public List<ListaTarefas> listar()
    {
        List<ListaTarefas> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM " + DbHelper.TABELA_TAREFAS + " ; ";
        Cursor c = le.rawQuery(sql,null);

        while (c.moveToNext())
        {
            ListaTarefas listaTarefas = new ListaTarefas();

            @SuppressLint("Range") Long id = c.getLong(c.getColumnIndex("id"));
            @SuppressLint("Range") String nomeTarefa = c.getString(c.getColumnIndex("nome"));

            listaTarefas.setId(id);
            listaTarefas.setNomeTarefa(nomeTarefa);

            tarefas.add(listaTarefas);
            Log.i("tarefaDao", listaTarefas.getNomeTarefa() );
        }

        return tarefas;
    }
}
