/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import Enums.Denominacion;
/**
 *
 * @author USER
 */
public class Moneda {
    private Denominacion denominacion;
    private  int valor;

    public Moneda() {
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Moneda(Denominacion denominacion) {
        this.denominacion = denominacion;
    }

    public Denominacion getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(Denominacion denominacion) {
        this.denominacion = denominacion;
        if(denominacion == Denominacion.QUIENTOS)
        {
            setValor(500);
        }else if(denominacion == Denominacion.MIL)
        {
            setValor(1000);
        }
    }
    @Override
    public String toString(){
      return this.denominacion.toString();
    }
}
