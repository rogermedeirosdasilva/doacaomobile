package voluntariadomobile.ftec.com.br.voluntariadomobile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

import voluntariadomobile.ftec.com.br.voluntariadomobile.bol.Voluntario;
import voluntariadomobile.ftec.com.br.voluntariadomobile.dao.LoginDAO;
import voluntariadomobile.ftec.com.br.voluntariadomobile.dao.VoluntarioDAO;
import voluntariadomobile.ftec.com.br.voluntariadomobile.util.Globais;
import voluntariadomobile.ftec.com.br.voluntariadomobile.util.ScreenUtils;

public class LoginActivity extends Activity {
    private Context ctx;
    private EditText fUsuario;
    private EditText fSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ctx = this;

        VerificaInstalacao();

        Bind();
    }

    private void VerificaInstalacao() {
        // Diretório IMNH
        String Caminho = Environment.getExternalStorageDirectory() + "/ftec/";
        File file = new File(Caminho);
        file.mkdirs();
    }

    private void Bind() {
        fUsuario = findViewById(R.id.email);
        fSenha = findViewById(R.id.senha);

        Voluntario remember = new VoluntarioDAO(this).ObtemPrimeiroRegistro();
        if (remember != null) {
            fUsuario.setText(remember.getEmail());
            fSenha.setText("123456");
            fUsuario.requestFocus();
        }

        findViewById(R.id.btnInscreva).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inscricao = new Intent(ctx, InscricaoActivity.class);
                inscricao.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(inscricao);
            }
        });

        findViewById(R.id.btnEntrar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ScreenUtils.ValidaCamposObrigatorios(fUsuario) || !ScreenUtils.ValidaCamposObrigatorios(fSenha)) {
                    return ;
                }

                VoluntarioDAO usuarioCore = new VoluntarioDAO(getApplicationContext());
                try {
                    Voluntario usuario = usuarioCore.ObtemPorEmail(fUsuario.getText().toString());

                    LoginDAO loginCore = new LoginDAO(getApplicationContext());

                    String resposta = loginCore.Autenticado(usuario, fSenha.getText().toString());
                    if (!resposta.equals("OK")) {
                        Toast.makeText(ctx, resposta, Toast.LENGTH_LONG).show();
                        return;
                    }

                    Globais.UsuarioLogado = usuario;

                    Intent campanha = new Intent(getApplicationContext(), CampanhaActivity.class);
                    campanha.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(campanha);

                } catch (Exception e) {
                    Toast.makeText(ctx, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
