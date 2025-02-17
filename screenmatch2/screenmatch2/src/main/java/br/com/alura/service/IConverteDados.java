package br.com.alura.service;

public interface IConverteDados {
    public <T>T obterDados(String json, Class<T> classe);
}
