import java.io.Serializable;

/*
//todo lo que quiera guardar en un archivo debe ser serializable, esta interfaz separa un objeto en los
//datos primitivos que tiene, los "des-serializa, y tambien los va agrupando a
//medida que se vayan leyendo datos"
 */


public class CuentaBancaria implements Serializable {
    private int id;
    private double saldo;

    public CuentaBancaria(int id, double saldo){
        this.id = id;
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "CuentaBancaria{" +
                "id=" + id +
                ", saldo=" + saldo +
                '}';
    }

    public String retiro(double retiro) throws ExcepcionCuenta{
        if(retiro <=0 || retiro > saldo){
            throw new ExcepcionCuenta("Saldo Insuficiente");
        }
        saldo -= retiro;
        return "Operacion realizada con exito";


    }
}
