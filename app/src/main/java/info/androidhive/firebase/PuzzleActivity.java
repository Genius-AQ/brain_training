package info.androidhive.firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class PuzzleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        TextView puzContent = new TextView(this);

        Random r = new Random();
        int i = r.nextInt(3);
        PuzzlesDatabase pd = new PuzzlesDatabase(this);
        Puzzle puz = pd.getPuzzle(i);

        puzContent.setText((puz.getContent()!=null)?puz.getContent():"hello");
        //Log.i("Content", puz.getContent());

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_puzzle);
        layout.addView(puzContent);

    }
}
