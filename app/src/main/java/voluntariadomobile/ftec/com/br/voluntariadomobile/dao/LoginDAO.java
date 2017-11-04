package voluntariadomobile.ftec.com.br.voluntariadomobile.dao;

import android.content.Context;

import voluntariadomobile.ftec.com.br.voluntariadomobile.bol.Voluntario;
import voluntariadomobile.ftec.com.br.voluntariadomobile.util.BaseDAO;
import voluntariadomobile.ftec.com.br.voluntariadomobile.util.StringUtilsCore;

/**
 * Created by roger on 02/11/17.
 */

public class LoginDAO extends BaseDAO {
    public static String Chave = "VOL*2017@";

    public LoginDAO(Context ctx) {
        super(ctx);
    }

    public String Autenticado(Voluntario usuario, String senha) throws Exception {
        if (usuario == null) {
            return "Usuário não encontrado.";
        }

        if (!usuario.getSenha().equals( StringUtilsCore.geraSHA1(senha + Chave))) {
            return "Senha incorreta.";
        }

        return "OK";
    }
}