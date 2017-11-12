package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
        varasto.lisaaVarastoon(10);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisaaTilaa() {
        varasto.lisaaVarastoon(8);
        varasto.otaVarastosta(2);

        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void varastonTilavuusPaljonkoMahtuuJaSaldoOnAlussaOikeita() {
        assertEquals(varasto.getTilavuus(), 10,0);
        assertEquals(varasto.getSaldo(), 0,0);
        assertEquals(varasto.paljonkoMahtuu(), 10,0);
    }

    @Test
    public void varastolleEiVoiAntaaNegatiivistaTilavuutta(){
        Varasto v = new Varasto(-120);
        assertEquals(v.getTilavuus(),0,0);
    }


    @Test
    public void alkuSaldoEiVoiOllaNegatiivinenEikaSuurempiKuinTilavuus(){
        for (int i = 0; i < 1000; i++) {
            Varasto v = new Varasto(i,1001);
            assertEquals(v.getSaldo(),i,0);
        }
        Varasto v = new Varasto(10,-10);
        assertEquals(v.getSaldo(),0,0);
        v = new Varasto(20,5);
        assertEquals(v.getSaldo(),5,5);
    }

    @Test
    public void toStringToimiiOikein(){
        Varasto v = new Varasto(-120);
        assertEquals(v.toString(), "saldo = 0.0, vielä tilaa " + 0.0);
        v = new Varasto(10);
        v.lisaaVarastoon(2);
        assertEquals(v.toString(), "saldo = 2.0, vielä tilaa " + 8.0);
    }

    @Test
    public void eiVoiLisataNegatiivista(){
        varasto.lisaaVarastoon(-1);
        assertEquals(varasto.getSaldo(),0,0);
    }

    @Test
    public void eiVoiOttaaNegatiivista(){
        assertEquals(varasto.otaVarastosta(-1),0,0);
    }

    @Test
    public void voiLisataVainVarastonTilavuudenVerran(){
        for (int i = 0; i < 0; i++) {
            varasto.lisaaVarastoon(i + 10);
            assertEquals(varasto.getSaldo(), 10, 0);
        }
    }

    @Test
    public void varastostaVoiOttaaVainSenMitaSiellaOn(){
        varasto.lisaaVarastoon(8);

        assertEquals(varasto.otaVarastosta(10), 8, 0);
    }



}