package info.androidhive.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Puz1 extends AppCompatActivity {
//    ImageView image;
    ImageButton btnNext;
    EditText edit_text;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puz1);

        MainActivity.score = 0;

        btnNext = (ImageButton) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                edit_text = (EditText) findViewById(R.id.resultPuz1);
                result = edit_text.getText().toString();

                if (result.equals("710"))
                {
//                    Toast.makeText(Puz1.this, "Right!!!", Toast.LENGTH_LONG).show();
                    MainActivity.score += 1;
                }

                Intent intent = new Intent(Puz1.this, Puz2.class);
                startActivity(intent);
                finish();
            }
        });
    }


}
