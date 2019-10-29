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

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void varastollaEiOleNegatiivinenTilavuus() {
        Varasto negatiivinen = new Varasto(-5);
        assertEquals(0, negatiivinen.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void EiVoiPoistaaNegatiivista() {
        varasto.otaVarastosta(-5);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void EiVoiLisataNegatiivista() {
        varasto.lisaaVarastoon(-5);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void EiVoiTayttaaYliTilavuuden() {
        varasto.lisaaVarastoon(100);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void SaldoTyhjeneeJosPoistoLiianIso() {
        varasto.lisaaVarastoon(10);
        varasto.otaVarastosta(20);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }



    @Test
    public void alkuSaldoLiianPieniTilavuus(){
        Varasto saldoVarasto = new Varasto(-5, 10);
        assertEquals(saldoVarasto.getTilavuus(), 0, vertailuTarkkuus);
    }

    @Test
    public void alkuSaldoPienempiTilavuuskuinSaldo(){
        Varasto saldoVarasto = new Varasto(5, 10);
        assertEquals(saldoVarasto.getSaldo(), 5, vertailuTarkkuus);
    }

    @Test
    public void alkuSaldoPienempiKuinNolla(){
        Varasto saldoVarasto = new Varasto(5, -10);
        assertEquals(saldoVarasto.getSaldo(), 0, vertailuTarkkuus);
    }

    @Test
    public void alkuSaldoMahtuu(){
        Varasto saldoVarasto = new Varasto(10, 5);
        assertEquals(saldoVarasto.getSaldo(), 5, vertailuTarkkuus);
    }

    @Test
    public void merkkijonoEsityToimii(){
        varasto.lisaaVarastoon(5);
        assertEquals(varasto.toString(), "saldo = 5.0, vielä tilaa 5.0");
    }

}