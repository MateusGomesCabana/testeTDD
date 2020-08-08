/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pos.teste;

/**
 * Classe utilizando design pattern builder
 * @author Mateus
 */
public class CriadorDeLeilao {
    private Leilao leilao;

   public CriadorDeLeilao() {
   }

   public CriadorDeLeilao para(String descricao) {
       this.leilao = new Leilao(descricao);
       return this;
   }

   public CriadorDeLeilao lance(Usuario usuario, double valor) {
       leilao.propoe(new Lance(usuario, valor));
       return this;
   }

   public Leilao constroi() {
       return leilao;
   }

}
