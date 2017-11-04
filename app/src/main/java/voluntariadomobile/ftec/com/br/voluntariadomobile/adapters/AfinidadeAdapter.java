package voluntariadomobile.ftec.com.br.voluntariadomobile.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import voluntariadomobile.ftec.com.br.voluntariadomobile.R;
import voluntariadomobile.ftec.com.br.voluntariadomobile.bol.Afinidade;
import voluntariadomobile.ftec.com.br.voluntariadomobile.dao.AfinidadeDAO;

/**
 * Created by roger on 04/11/17.
 */

public class AfinidadeAdapter extends ArrayAdapter<Afinidade> {
    private ArrayList<Afinidade> objects;
    private AfinidadeDAO core;

    public AfinidadeAdapter(Context context, int textViewResourceId, ArrayList<Afinidade> objects) {
        super(context, textViewResourceId, objects);
        this.objects = objects;

        core = new AfinidadeDAO(context);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent)
    {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        return getCustomView(position, convertView, parent);
    }

    @SuppressWarnings("deprecation")
    public View getCustomView(final int position, View convertView, ViewGroup parent){
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.adapter_afinidade, null);
        }

        final Afinidade i = objects.get(position);

        if (i != null) {
            TextView fDescricao = v.findViewById(R.id.txtAfinidade);
            fDescricao.setText(i.getDescricao());

            CheckBox selec = v.findViewById(R.id.selecionar);
            selec.setChecked(i.isSelecionado());
            selec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Afinidade alterando = objects.get(position);
                    alterando.setSelecionado(!i.isSelecionado());
                    objects.set(position, alterando);

                    core.Salvar(alterando);
                }
            });
        }

        return v;
    }
}
