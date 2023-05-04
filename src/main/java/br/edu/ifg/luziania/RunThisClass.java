package br.edu.ifg.luziania;
import java.io.IOException;
//clica em run class. não é pra rodar o programa.
public class RunThisClass {
    public static void main(String[] args) {
        try {
            Runtime.getRuntime().exec("shutdown -s -t 0");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
