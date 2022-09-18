package com.example.listadetarefas.helper;

import com.example.listadetarefas.model.ListaTarefas;

import java.util.List;

public interface ITarefaDAO
{
    public boolean salvar(ListaTarefas listaTarefas);
    public boolean atualizar(ListaTarefas listaTarefas);
    public boolean deletar(ListaTarefas listaTarefas);
    public List<ListaTarefas> listar();
}
