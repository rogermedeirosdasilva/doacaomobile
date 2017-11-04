package voluntariadomobile.ftec.com.br.voluntariadomobile;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ListView;

import java.util.ArrayList;

import voluntariadomobile.ftec.com.br.voluntariadomobile.adapters.AfinidadeAdapter;
import voluntariadomobile.ftec.com.br.voluntariadomobile.bol.Afinidade;
import voluntariadomobile.ftec.com.br.voluntariadomobile.dao.AfinidadeDAO;

public class AfinidadesActivity extends Activity {
    private ListView lst;
    private ArrayList<Afinidade> afinidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afinidades);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        Bind();
    }

    private void Bind() {
        lst = findViewById(R.id.lstAfinidades);

        afinidades = new AfinidadeDAO(this).ObtemLista();
        AfinidadeAdapter adapter = new AfinidadeAdapter(this, R.layout.adapter_afinidade, afinidades);

        lst.setAdapter(adapter);
    }
}
