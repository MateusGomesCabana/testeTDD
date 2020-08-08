/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.fib.teste;

import br.pos.teste.Avaliador;
import br.pos.teste.CriadorDeLeilao;
import br.pos.teste.Lance;
import br.pos.teste.Leilao;
import br.pos.teste.Usuario;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Mateus
 */
public class AvaliadorTest {

    private Avaliador leiloeiro;
    private Usuario joao;
    private Usuario jose;
    private Usuario maria;
    private Leilao leilao;

    @Before
    public void criaAvaliador() {
        this.leiloeiro = new Avaliador();
        this.joao = new Usuario("João");
        this.jose = new Usuario("José");
        this.maria = new Usuario("Maria");
        this.leilao = new Leilao("Playstation 3 Novo");
    }

    @Test
    public void deveRetornarOMaiorEMenorLance() {
        leilao.propoe(new Lance(this.joao, 300.0));
        leilao.propoe(new Lance(this.jose, 400.0));
        leilao.propoe(new Lance(this.maria, 250.0));

        Avaliador avaliador = new Avaliador();
        avaliador.avalia(leilao);

        // comparando a saida com o esperado
        assertEquals(400, avaliador.getMaiorLance(), 0.00001);
        assertEquals(250, avaliador.getMenorLance(), 0.00001);
    }

    /**
     * metodo que testa os 3 maiores lances
     */
    @Test
    public void deveEncontrarOsTresMaioresLancesSemDesignPattern() {
        leilao.propoe(new Lance(this.joao, 100.0));
        leilao.propoe(new Lance(this.maria, 200.0));
        leilao.propoe(new Lance(this.joao, 300.0));
        leilao.propoe(new Lance(this.maria, 400.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);
        List<Lance> maiores = leiloeiro.getTresMaiores();
        assertEquals(3, maiores.size());
        assertEquals(400, maiores.get(0).getValor(), 0.00001);
        assertEquals(300, maiores.get(1).getValor(), 0.00001);
        assertEquals(200, maiores.get(2).getValor(), 0.00001);
    }

    /**
     * testa se é possivel realizar dois lances seguidos
     */
    @Test
    public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
        Usuario steveJobs = new Usuario("Steve Jobs");
        leilao.propoe(new Lance(steveJobs, 2000));
        leilao.propoe(new Lance(steveJobs, 3000));
        assertEquals(1, leilao.getLances().size());
        assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
    }

    /**
     * teste que verifica se um usuario tem mais do que 5 lances
     */
    @Test
    public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() {
        Usuario steveJobs = new Usuario("Steve Jobs");
        Usuario billGates = new Usuario("Bill Gates");

        this.leilao.propoe(new Lance(steveJobs, 2000));
        this.leilao.propoe(new Lance(billGates, 3000));
        this.leilao.propoe(new Lance(steveJobs, 4000));
        this.leilao.propoe(new Lance(billGates, 5000));
        this.leilao.propoe(new Lance(steveJobs, 6000));
        this.leilao.propoe(new Lance(billGates, 7000));
        this.leilao.propoe(new Lance(steveJobs, 8000));
        this.leilao.propoe(new Lance(billGates, 9000));
        this.leilao.propoe(new Lance(steveJobs, 10000));
        this.leilao.propoe(new Lance(billGates, 11000));

        // deve ser ignorado
        this.leilao.propoe(new Lance(steveJobs, 12000));

        assertEquals(10, this.leilao.getLances().size());
        int ultimo = this.leilao.getLances().size() - 1;
        Lance ultimoLance = this.leilao.getLances().get(ultimo);
        assertEquals(11000.0, ultimoLance.getValor(), 0.00001);
    }

    /**
     * teste que verifica se ele passa todos os lances em ordem crescente
     */
    @Test
    public void deveEntenderLancesEmOrdemCrescenteComOutrosValores() {
        this.leilao.propoe(new Lance(this.joao, 1000.0));
        this.leilao.propoe(new Lance(this.jose, 2000.0));
        this.leilao.propoe(new Lance(this.maria, 3000.0));

        this.leiloeiro.avalia(this.leilao);

        assertEquals(3000, leiloeiro.getMaiorLance(), 0.00001);
        assertEquals(1000, leiloeiro.getMenorLance(), 0.00001);
    }

    /**
     * teste que verifica se ele passa todos os lances em ordem decrescente
     */
    @Test
    public void deveEntenderLancesEmOrdemDecresenteComOutrosValores() {
        this.leilao.propoe(new Lance(this.maria, 3000.0));
        this.leilao.propoe(new Lance(this.jose, 2000.0));
        this.leilao.propoe(new Lance(this.joao, 1000.0));

        this.leiloeiro.avalia(this.leilao);

        assertEquals(3000, leiloeiro.getMaiorLance(), 0.00001);
        assertEquals(1000, leiloeiro.getMenorLance(), 0.00001);
    }

    /**
     * deve entender apenas um lance
     */
    @Test
    public void deveEntenderApenaUmLance() {
        this.leilao.propoe(new Lance(this.joao, 1000.0));
        this.leiloeiro.avalia(this.leilao);

        assertEquals(1000, leiloeiro.getMaiorLance(), 0.00001);
        assertEquals(1000, leiloeiro.getMenorLance(), 0.00001);
    }
    /**
     * método que testa os 3 maiores lances utilizando design pattern builder
     */
    @Test
    public void deveEncontrarOsTresMaioresLances() {
        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
                .lance(this.joao, 100.0)
                .lance(this.maria, 200.0)
                .lance(this.joao, 300.0)
                .lance(this.maria, 400.0)
                .constroi();

        this.leiloeiro.avalia(leilao);
        List<Lance> maiores = this.leiloeiro.getTresMaiores();

        assertEquals(3, maiores.size());
        assertEquals(400, maiores.get(0).getValor(), 0.00001);
        assertEquals(300, maiores.get(1).getValor(), 0.00001);
        assertEquals(200, maiores.get(2).getValor(), 0.00001);
    }

}
