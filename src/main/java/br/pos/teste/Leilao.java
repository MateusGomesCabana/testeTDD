/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pos.teste;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Mateus
 */
public class Leilao {

    private String descricao;
    private List<Lance> lances;

    public Leilao(String descricao) {
        this.descricao = descricao;
        this.lances = new ArrayList<Lance>();
    }
    /**
     * Função que retorna a quantidade de lances de um usuario
     * @param usuario
     * @return 
     */
    private int qtdDelanceDo(Usuario usuario) {
        int total = 0;
        for (Lance lance1 : lances) {
            if (lance1.getUsuario().equals(usuario)) {
                total++;
            }
        }
        return total;
    }
    /**
     * antes de add verifica se o ultimo lance foi dado pela mesma pessoa
     *
     * @param lance
     */
    public void propoe(Lance lance) {
        if (lances.isEmpty() || !lances.get(lances.size() - 1).getUsuario().equals(lance.getUsuario()) && qtdDelanceDo(lance.getUsuario()) < 5) {
            this.lances.add(lance);
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public List<Lance> getLances() {
        return Collections.unmodifiableList(lances);
    }

    private Lance ultimoLanceDado() {
        return lances.get(lances.size() - 1);
    }

}
