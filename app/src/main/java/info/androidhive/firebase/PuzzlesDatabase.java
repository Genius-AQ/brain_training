package info.androidhive.firebase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static android.support.v7.widget.StaggeredGridLayoutManager.TAG;

/**
 * Created by GeniusAQ on 23/01/2017.
 */

public class PuzzlesDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "puzzle_db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Puzzles";
    private static String DATABASE_PATH = "";

    private static final String TABLE_ID = "rowid";
    private static final String KEY_ID = "id";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_RESULT = "result";

    private SQLiteDatabase myDataBase;
    private final Context myContext;

    public PuzzlesDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        if(android.os.Build.VERSION.SDK_INT >= 17)
        {
            DATABASE_PATH = context.getApplicationInfo().dataDir + "/databases/";
        }
        else
        {
            DATABASE_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
//        DATABASE_PATH = "/data/data/" + context.getPackageName() + "/databases/";

        this.myContext = context;
    }

    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if(dbExist){
            //do nothing - database already exist
            Log.i("DBCreate", "DB already created");
//            myContext.deleteDatabase(DATABASE_NAME);
//
//            try {
//
//                copyDataBase();
//                Log.e("DBCreate", "DB created");
//
//            } catch (IOException e) {
//
//                throw new Error("Error copying database");
//            }
        }
        else{

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();

            try {

                copyDataBase();
                Log.i("DBCreate", "DB created");

            } catch (IOException e) {

                throw new Error("Error copying database");
            }
        }
    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;

        try{
            String myPath = DATABASE_PATH + DATABASE_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        } catch(SQLiteException e){

            //database does't exist yet.
        }

        if(checkDB != null){

            checkDB.close();
        }

        return (checkDB != null) ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DATABASE_NAME);

        // Path to the just created empty db
        String outFileName = DATABASE_PATH + DATABASE_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase() throws SQLException {

        //Open the database
        String myPath = DATABASE_PATH + DATABASE_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {

        if(myDataBase != null)
            myDataBase.close();

        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        String script = String.format("CREATE TABLE %s (INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT)", TABLE_NAME, KEY_ID, KEY_CONTENT, KEY_RESULT);
//        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        String drop_students_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
//        db.execSQL(drop_students_table);
//
//        onCreate(db);
    }

    public void createDemo()  {
        int count = 0;
        if(count == 0 ) {
            Puzzle p1 = new Puzzle("Firstly see Android ListView",
                    "See Android ListView Example in o7planning.org");
            Puzzle p2 = new Puzzle("Learning Android SQLite",
                    "See Android SQLite Example in o7planning.org");
            this.addNote(p1);
            this.addNote(p2);
        }
    }

    public void addNote(Puzzle puz) {
        Log.i(TAG, "Adding Puzzle ... " + puz.getId());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_CONTENT, puz.getContent());
        values.put(KEY_RESULT, puz.getResult());


        // Chèn một dòng dữ liệu vào bảng.
        db.insert(TABLE_NAME, null, values);

        // Đóng kết nối database.
        db.close();
    }

    public Puzzle getPuzzle(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[] { TABLE_ID, KEY_CONTENT, KEY_RESULT }, TABLE_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if ((cursor != null) && (cursor.moveToFirst())) {
            //cursor.moveToFirst();
            Log.i("SUCCESSSSSSSSSSSSSSS","= )))))");
        }
        else
            Log.i("FAILEDDDDDDDDDDDDDDD",": (((((");

        Puzzle puz = new Puzzle(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        Log.i("Info", " " + String.valueOf(puz.getId()) + puz.getContent());
// return shop
        return puz;
    }
}
