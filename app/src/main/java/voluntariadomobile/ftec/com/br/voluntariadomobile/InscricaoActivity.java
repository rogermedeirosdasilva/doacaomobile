package voluntariadomobile.ftec.com.br.voluntariadomobile;

import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.UUID;

import voluntariadomobile.ftec.com.br.voluntariadomobile.bol.ESituacaoCadastro;
import voluntariadomobile.ftec.com.br.voluntariadomobile.bol.Voluntario;
import voluntariadomobile.ftec.com.br.voluntariadomobile.dao.LoginDAO;
import voluntariadomobile.ftec.com.br.voluntariadomobile.dao.VoluntarioDAO;
import voluntariadomobile.ftec.com.br.voluntariadomobile.util.ScreenUtils;
import voluntariadomobile.ftec.com.br.voluntariadomobile.util.StringUtilsCore;

public class InscricaoActivity extends Activity {
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscricao);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        ctx = this;

        Bind();
    }

    private void Bind() {
        findViewById(R.id.btnAddVoluntario).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText fNome = (findViewById(R.id.nome_completo));
                EditText fCPF = (findViewById(R.id.nome_completo));
                EditText fEmail = (findViewById(R.id.email_cadastro));
                EditText fSenha = (findViewById(R.id.senha_cadastro));

                if (!ScreenUtils.ValidaCamposObrigatorios(fNome) || !ScreenUtils.ValidaCamposObrigatorios(fCPF) ||
                        !ScreenUtils.ValidaCamposObrigatorios(fEmail) || !ScreenUtils.ValidaCamposObrigatorios(fSenha)) {

                    return ;
                }

                Voluntario objeto = new Voluntario();
                objeto.setCodigo(UUID.randomUUID().toString());
                objeto.setNome(fNome.getText().toString());
                objeto.setDocumento(fCPF.getText().toString());
                objeto.setEmail(fEmail.getText().toString());
                objeto.setSituacao(ESituacaoCadastro.NAO_VERIFICADO.getValor());
                try {
                    String senha = StringUtilsCore.geraSHA1(fSenha.getText().toString() + LoginDAO.Chave);
                    objeto.setSenha(senha);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                VoluntarioDAO core = new VoluntarioDAO(getApplicationContext());
                try {
                    if (core.Salvar(objeto)) {
                        Toast.makeText(ctx, "Cadastro efetuado com sucesso.", Toast.LENGTH_LONG).show();

                        finish();
                    } else {
                        Toast.makeText(ctx, "Erro ao efetuar o cadastro.", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception ex) {
                    Toast.makeText(ctx, ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}