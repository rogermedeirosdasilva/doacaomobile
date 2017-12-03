package voluntariadomobile.ftec.com.br.voluntariadomobile.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import voluntariadomobile.ftec.com.br.voluntariadomobile.bol.Voluntario;
import voluntariadomobile.ftec.com.br.voluntariadomobile.util.BaseDAO;

/**
 * Created by roger on 01/11/17.
 */

public class VoluntarioDAO extends BaseDAO {
    public VoluntarioDAO(Context ctx) {
        super(ctx);
    }

    public boolean Salvar(Voluntario obj) {
        ContentValues values = new ContentValues();
        this.open();
        mDb.beginTransactionNonExclusive();
        try
        {
            values.put("codigo", obj.getCodigo());
            values.put("nome", obj.getNome());
            values.put("documento", obj.getDocumento());
            values.put("email", obj.getEmail());
            values.put("senha", obj.getSenha());
            values.put("situacao", obj.getSituacao());

            String sql = "select 1 from voluntario where codigo = ?";
            Cursor cursor = mDb.rawQuery(sql, new String[] { obj.getCodigo() });
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

    public boolean Esvaziar() {
        this.open();
        mDb.beginTransactionNonExclusive();
        try
        {
            mDb.execSQL("delete from voluntario");
            mDb.setTransactionSuccessful();
        }
        finally {
            mDb.endTransaction();
        }
        this.close();

        return true;
    }

    private boolean Insere(ContentValues cv){
        return (mDb.insert("voluntario", "", cv) > 0);
    }

    private boolean Atualiza(ContentValues cv, String Where){
        return (mDb.update("voluntario", cv, Where, null) > 0);
    }

    public Voluntario Obtem(String codigo) {
        Voluntario v = null;

        String sql = "select c.* from voluntario c" +
                " where codigo = ?";

        this.open();
        Cursor cursor = mDb.rawQuery(sql, new String[] {
                codigo
        });
        cursor.moveToFirst();
        if (!cursor.isAfterLast()){
            v = Preenche(cursor);
        }
        cursor.close();
        this.close();

        return v;
    }

    private Voluntario Preenche(Cursor cursor) {
        Voluntario v = new Voluntario();
        v.setCodigo(cursor.getString(cursor.getColumnIndex("codigo")));
        v.setNome(cursor.getString(cursor.getColumnIndex("nome")));
        v.setEmail(cursor.getString(cursor.getColumnIndex("email")));
        v.setDocumento(cursor.getString(cursor.getColumnIndex("documento")));
        v.setSenha(cursor.getString(cursor.getColumnIndex("senha")));
        v.setSituacao(cursor.getInt(cursor.getColumnIndex("situacao")));

        return v;
    }

    public Voluntario ObtemPorEmail(String email) {
        Voluntario v = null;

        String sql = "select c.* from voluntario c" +
                " where email = ?";

        this.open();
        Cursor cursor = mDb.rawQuery(sql, new String[] {
                email
        });
        cursor.moveToFirst();
        if (!cursor.isAfterLast()){
            v = Preenche(cursor);
        }
        cursor.close();
        this.close();

        return v;
    }

    public Voluntario ObtemPrimeiroRegistro() {
        Voluntario v = null;

        String sql = "select c.* from voluntario c" +
                " limit 1";

        this.open();
        Cursor cursor = mDb.rawQuery(sql, null);
        cursor.moveToFirst();
        if (!cursor.isAfterLast()){
            v = Preenche(cursor);
        }
        cursor.close();
        this.close();

        return v;
    }
}