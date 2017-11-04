package voluntariadomobile.ftec.com.br.voluntariadomobile.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import voluntariadomobile.ftec.com.br.voluntariadomobile.bol.Afinidade;
import voluntariadomobile.ftec.com.br.voluntariadomobile.bol.Voluntario;
import voluntariadomobile.ftec.com.br.voluntariadomobile.util.BaseDAO;

/**
 * Created by roger on 04/11/17.
 */

public class AfinidadeDAO extends BaseDAO {
    public AfinidadeDAO(Context ctx) {
        super(ctx);
    }

    public ArrayList<Afinidade> ObtemLista() {
        ArrayList<Afinidade> lista = new ArrayList<>();

        String sql = "select c.* from afinidade c" +
                " order by descricao;";

        this.open();
        Cursor cursor = mDb.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Afinidade obj = Preenche(cursor);
            lista.add(obj);

            cursor.moveToNext();
        }
        cursor.close();
        this.close();

        return lista;
    }

    private Afinidade Preenche(Cursor cursor) {
        Afinidade v = new Afinidade();
        v.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
        v.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
        v.setSelecionado(cursor.getString(cursor.getColumnIndex("selecionado")).equals("S"));

        return v;
    }

    public boolean Salvar(Afinidade obj) {
        ContentValues values = new ContentValues();
        this.open();
        mDb.beginTransactionNonExclusive();
        try
        {
            values.put("codigo", obj.getCodigo());
            values.put("descricao", obj.getDescricao());
            values.put("selecionado", obj.isSelecionado() ? "S" : "N");

            String sql = "select 1 from afinidade where codigo = ?";
            Cursor cursor = mDb.rawQuery(sql, new String[] { obj.getCodigo().toString() });
            cursor.moveToFirst();
            boolean existe = !cursor.isAfterLast();
            if (existe) {
                this.Atualiza(values, "codigo = '" + obj.getCodigo() + "'");
            } else {
                this.Insere(values);
            }

            mDb.setTransactionSuccessful();
        }
        finally
        {
            mDb.endTransaction();
        }
        this.close();

        return true;
    }

    private boolean Atualiza(ContentValues cv, String Where){
        return (mDb.update("afinidade", cv, Where, null) > 0);
    }

    private boolean Insere(ContentValues cv){
        return (mDb.insert("afinidade", "", cv) > 0);
    }
}
