package voluntariadomobile.ftec.com.br.voluntariadomobile.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public abstract class BaseDAO {
	protected static SQLiteDatabase mDb;
	protected static Context context;
	private static DbAdapter dbAdapter;
	private static int contador;
	private static String keyLock;
	private static boolean locked = false;

	public void lock(String keyLock) throws Exception {
		if(!locked){
			BaseDAO.keyLock = keyLock;
			locked = true;
		}
	}

	public void unlock(String keyLock){
		if(keyLock.equals(BaseDAO.keyLock)){
			BaseDAO.locked = false;
			BaseDAO.keyLock = "";
		}
	}

	public BaseDAO(Context ctx) {
		context = ctx;
	}

	private synchronized static int numeroConexoes(int i) {
		contador = contador + i;
		return contador;
	}

	public void verificarLock() throws Exception {
		if(locked)
			throw new Exception("Database bloqueado.");
	}

	public synchronized void open() {
		if (mDb == null || (mDb != null && !mDb.isOpen())) {
			dbAdapter = new DbAdapter(context);
			mDb = dbAdapter.open();
		}
		numeroConexoes(+1);
	}

	public synchronized void close() {
		if (mDb != null && mDb.isOpen() && (numeroConexoes(0) >= 1)) {
			dbAdapter.close();
			mDb.close();
		}
		numeroConexoes(-1);
	}

	protected boolean existeTabela(String tabela) {
		Cursor cursor = mDb.rawQuery(
				"select DISTINCT tbl_name from sqlite_master where tbl_name = '"
						+ tabela + "'", null);
		if (cursor != null) {
			if (cursor.getCount() > 0) {
				return true;
			}
		}
		return false;
	}

	public String[] toArray(String ... strs) {
		return strs;
	}
}