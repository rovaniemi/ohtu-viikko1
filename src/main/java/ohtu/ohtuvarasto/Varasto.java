package ohtu.ohtuvarasto;

public class Varasto {

    private double tilavuus;
    private double saldo;

    public Varasto(double tilavuus) {
        if (tilavuus > 0.0) {
            this.tilavuus = tilavuus;
        } else {
            this.tilavuus = 0.0; 
        }
        saldo = 0;
    }

    public Varasto(double tilavuus, double alkuSaldo) {
        if (tilavuus < 0.0) {
            tilavuus = 0.0;
        } else {
            this.tilavuus = tilavuus;
        }
        this.saldo = asetaAlkuSaldo(tilavuus, alkuSaldo);
    }


    public double getSaldo() {
        return saldo;
    }

    public double getTilavuus() {
        return tilavuus;
    }

    public double paljonkoMahtuu() {
        return tilavuus - saldo; 
    }


    public void lisaaVarastoon(double maara) {
        if (maara < 0) {
            return; 
        }
        if (maara <= paljonkoMahtuu()) {
            saldo = saldo + maara; 
        } else {
            saldo = tilavuus;
        }
    }

    public double otaVarastosta(double maara) {
        if (maara < 0) {
            return 0.0;
        }
        if (maara > saldo) {
            double kaikkiMitaVoidaan = saldo;
            saldo = 0.0;
            return kaikkiMitaVoidaan;
        }
        saldo = saldo - maara;
        return maara;
    }

    public String toString() {
        return ("saldo = " + saldo + ", vielä tilaa " + paljonkoMahtuu());
    }

    private double asetaAlkuSaldo(double tilavuus, double alkuSaldo) {
        double saldo = alkuSaldo;
        if (saldo < 0.0) {
            saldo= 0.0;
        } else if (alkuSaldo <= tilavuus) {
            saldo = alkuSaldo;
        } else {
            saldo = tilavuus;
        }
        return saldo;
    }
}