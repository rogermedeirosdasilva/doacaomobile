package voluntariadomobile.ftec.com.br.voluntariadomobile.util;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

public class DbAdapter{
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;
    public static String DB_NAME = "voluntario.db";
	private static final int DATABASE_VERSION = 3;
	private final Context mCtx;
	
	public DbAdapter(Context ctx) {
		this.mCtx = ctx;
	}

	public  SQLiteDatabase open() throws SQLException {
		mDbHelper = new DatabaseHelper(mCtx);
		mDb = mDbHelper.getWritableDatabase();
		return mDb;
	}
	
	public void close() {
		mDbHelper.close();
		mDb.close();
	}
	
	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DB_NAME, null, DATABASE_VERSION); 
		}

		@Override
		public void onOpen(SQLiteDatabase db) {
			super.onOpen(db);
			if (!db.isReadOnly()) {
				db.execSQL("PRAGMA foreign_keys=ON;");
			}
		}
	 	
		@Override
		public void onCreate(SQLiteDatabase db) {
			// Cliente
			String sql = "CREATE TABLE voluntario (" +
				" codigo varchar(36) NOT NULL," +
				" nome varchar(100) NOT NULL," +
				" documento varchar(15) NOT NULL," +
				" email varchar(100)," +
				" senha varchar(60)," +
				" situacao integer null," +
				" CONSTRAINT pk_voluntario PRIMARY KEY (codigo)" +
				");";

			sql += "CREATE TABLE afinidade (" +
					" codigo INTEGER NOT NULL," +
					" descricao VARCHAR(100) NOT NULL," +
                    " selecionado CHAR(1) NULL DEFAULT 'N'," +
					" CONSTRAINT pk_afinidade PRIMARY KEY (codigo)" +
					");";

			sql += "INSERT INTO afinidade values (1, 'Refugiados');";
			sql += "INSERT INTO afinidade values (2, 'Crianças vítimas de abuso');";
			sql += "INSERT INTO afinidade values (3, 'Pessoas em situação de rua');";
			sql += "INSERT INTO afinidade values (4, 'Mulheres vítimas de violência');";
			sql += "INSERT INTO afinidade values (5, 'Crianças desaparecidas');";
			sql += "INSERT INTO afinidade values (6, 'Animais abandonados');";
			sql += "INSERT INTO afinidade values (7, 'Crianças e adolescentes fora da escola');";
			sql += "INSERT INTO afinidade values (8, 'Idosos');";
			sql += "INSERT INTO afinidade values (9, 'Pessoas com deficiência');";
			sql += "INSERT INTO afinidade values (10, 'Direitos Humanos');";

			try
			{
				String[] comandos = sql.split(";");
				for (String sqlToRun : comandos) {
					try {
						db.execSQL(sqlToRun);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			} catch (Exception ex) {
				
			}			
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			String sql = "";

			if (oldVersion < 2) {
				sql += "CREATE TABLE afinidade (" +
						" codigo INTEGER NOT NULL," +
						" descricao VARCHAR(100) NOT NULL," +
						" CONSTRAINT pk_afinidade PRIMARY KEY (codigo)" +
						");";

				sql += "INSERT INTO afinidade values (1, 'Refugiados');";
				sql += "INSERT INTO afinidade values (2, 'Crianças vítimas de abuso');";
				sql += "INSERT INTO afinidade values (3, 'Pessoas em situação de rua');";
				sql += "INSERT INTO afinidade values (4, 'Mulheres vítimas de violência');";
				sql += "INSERT INTO afinidade values (5, 'Crianças desaparecidas');";
				sql += "INSERT INTO afinidade values (6, 'Animais abandonados');";
				sql += "INSERT INTO afinidade values (7, 'Crianças e adolescentes fora da escola');";
				sql += "INSERT INTO afinidade values (8, 'Idosos');";
				sql += "INSERT INTO afinidade values (9, 'Pessoas com deficiência');";
				sql += "INSERT INTO afinidade values (10, 'Direitos Humanos');";
			}

			if (oldVersion < 3) {
                sql += "ALTER TABLE afinidade ADD COLUMN selecionado CHAR(1) NOT NULL DEFAULT 'N';";
            }

			try
			{
				String[] comandos = sql.split(";");
				for (String sqlToRun : comandos) {
					try {
						db.execSQL(sqlToRun);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			} catch (Exception ex) {
				
			}			
		}
	}
}