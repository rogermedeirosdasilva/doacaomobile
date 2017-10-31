package voluntariadomobile.ftec.com.br.voluntariadomobile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends Activity {
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ctx = this;

        Bind();
    }

    private void Bind() {
        findViewById(R.id.btnInscreva).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inscricao = new Intent(ctx, InscricaoActivity.class);
                inscricao.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(inscricao);
            }
        });
    }
}
