package voluntariadomobile.ftec.com.br.voluntariadomobile;

import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.UUID;

import voluntariadomobile.ftec.com.br.voluntariadomobile.bol.ESituacaoCadastro;
import voluntariadomobile.ftec.com.br.voluntariadomobile.bol.Voluntario;
import voluntariadomobile.ftec.com.br.voluntariadomobile.dao.VoluntarioDAO;
import voluntariadomobile.ftec.com.br.voluntariadomobile.util.Globais;
import voluntariadomobile.ftec.com.br.voluntariadomobile.util.ScreenUtils;

public class EditActivity extends Activity {
    private EditText fNome;
    private EditText fCPF;
    private EditText fEmail;

    private Context ctx;

    private Voluntario objeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        ctx = this;

        Bind();
    }

    private void Bind() {
        fNome = (findViewById(R.id.nome_completo2));
        fCPF = (findViewById(R.id.cpf_cadastro2));
        fEmail = (findViewById(R.id.email_cadastro2));

        final VoluntarioDAO usuarioCore = new VoluntarioDAO(getApplicationContext());
        try {
            objeto = usuarioCore.Obtem(Globais.UsuarioLogado.getCodigo());

            fNome.setText(objeto.getNome());
            fCPF.setText(objeto.getDocumento());
            fEmail.setText(objeto.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }

        findViewById(R.id.btnAddVoluntario).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ScreenUtils.ValidaCamposObrigatorios(fNome) || !ScreenUtils.ValidaCamposObrigatorios(fCPF) ||
                        !ScreenUtils.ValidaCamposObrigatorios(fEmail)) {

                    return ;
                }

                objeto.setNome(fNome.getText().toString());
                objeto.setDocumento(fCPF.getText().toString());
                objeto.setEmail(fEmail.getText().toString());
                objeto.setSituacao(ESituacaoCadastro.NAO_VERIFICADO.getValor());

                try {
                    if (usuarioCore.Salvar(objeto)) {
                        Toast.makeText(ctx, "Cadastro alterado com sucesso.", Toast.LENGTH_LONG).show();

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
