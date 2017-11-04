package voluntariadomobile.ftec.com.br.voluntariadomobile.util;

import android.widget.EditText;

/**
 * Created by roger on 02/11/17.
 */

public class ScreenUtils {
    public static boolean ValidaCamposObrigatorios(EditText campo) {
        if (campo.getText().toString().trim().equals("")) {
            campo.setError("Campo obrigatório");

            return false;
        }

        return true;
    }
}
