package voluntariadomobile.ftec.com.br.voluntariadomobile;

import android.os.Bundle;
import android.app.Activity;

public class InscricaoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscricao);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
