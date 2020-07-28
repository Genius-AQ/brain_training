package info.androidhive.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Puz2 extends AppCompatActivity {

    ImageButton btnNext;
    EditText edit_text;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puz2);

        btnNext = (ImageButton) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                edit_text = (EditText) findViewById(R.id.resultPuz2);
                result = edit_text.getText().toString();

                if (result.equals("44"))
                {
//                    Toast.makeText(Puz1.this, "Right!!!", Toast.LENGTH_LONG).show();
                    MainActivity.score += 1;
                }

                Intent intent = new Intent(Puz2.this, Puz3.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
