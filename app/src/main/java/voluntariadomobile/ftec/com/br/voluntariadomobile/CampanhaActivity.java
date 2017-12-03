package voluntariadomobile.ftec.com.br.voluntariadomobile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.StrictMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

import voluntariadomobile.ftec.com.br.voluntariadomobile.adapters.DashboardAdapter;
import voluntariadomobile.ftec.com.br.voluntariadomobile.bol.CartaoCampanha;
import voluntariadomobile.ftec.com.br.voluntariadomobile.dao.DashboardDAO;

public class CampanhaActivity extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campanha);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mRecyclerView = findViewById(R.id.recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<CartaoCampanha> myDataset = new DashboardDAO(this).PegaTodos();

        // specify an adapter (see also next example)
        mAdapter = new DashboardAdapter(this, myDataset);
        mRecyclerView.setAdapter(mAdapter);
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
