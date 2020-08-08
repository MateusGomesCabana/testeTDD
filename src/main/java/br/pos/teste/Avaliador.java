/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pos.teste;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Mateus
 */
public class Avaliador {

    private double maiorDeTodos = Double.NEGATIVE_INFINITY;
    private double menorDeTodos = Double.POSITIVE_INFINITY;
    private List<Lance> maiores;
    
    /**
     * seta os maiores e menores valores
     * @param leilao 
     */
    public void avalia(Leilao leilao) {
        for (Lance lance : leilao.getLances()) {
            if (lance.getValor() > maiorDeTodos) {
                maiorDeTodos = lance.getValor();
            } 
            if (lance.getValor() < menorDeTodos) {
                menorDeTodos = lance.getValor();
            }
        }
        if(leilao.getLances().size() >= 3){
          pegaOsMaioresNo(leilao);
        }
    }
    /**
     * Pega os 3 maiores valores
     * @param leilao 
     */
    private void pegaOsMaioresNo(Leilao leilao) {
        maiores = new ArrayList<Lance>(leilao.getLances());
        Collections.sort(maiores, new Comparator<Lance>() {
            public int compare(Lance o1, Lance o2) {
                if (o1.getValor() < o2.getValor()) {
                    return 1;
                }
                if (o1.getValor() > o2.getValor()) {
                    return -1;
                }
                return 0;
            }
        });
        maiores = maiores.subList(0, 3);
    }
    /**
     * getter dos 3 maiores valores
     * @return 
     */
    public List<Lance> getTresMaiores() {
        return this.maiores;
    }
    /**
     * getter do maior valor
     * @return 
     */
    public double getMaiorLance() {
        return maiorDeTodos;
    }
    /**
     * getter do menor valor
     * @return 
     */
    public double getMenorLance() {
        return menorDeTodos;
    }

}
