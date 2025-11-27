package Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class filtrarListaPorId {

    public static <T> List<T> filtrar(List<T> lista, Predicate<T> condicion) {
        List<T> resultado = new ArrayList<>();
        for (T elemento : lista) {
            if (condicion.test(elemento)) {
                resultado.add(elemento);
            }
        }
        return resultado;
    }
}

