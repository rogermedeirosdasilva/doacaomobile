package voluntariadomobile.ftec.com.br.voluntariadomobile;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class CampanhaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campanha);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.mnu_afinidades:
                Intent afinidade = new Intent(getApplicationContext(), AfinidadesActivity.class);
                afinidade.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(afinidade);

                return true;
            case R.id.mnu_dados:
                Intent dados = new Intent(getApplicationContext(), EditActivity.class);
                dados.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(dados);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
