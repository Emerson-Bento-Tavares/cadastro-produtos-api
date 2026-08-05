package com.seuprojeto.cadastroprodutos.Strategy;

import org.springframework.stereotype.Component;

@Component
public class Desconto10Strategy implements DescontoStrategy {

    @Override
    public double calcularDesconto(double preco) {
        return preco - (preco * 0.10);
    }
}
