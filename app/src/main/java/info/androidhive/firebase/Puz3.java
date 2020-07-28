package info.androidhive.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Puz3 extends AppCompatActivity {

    EditText edit_text;
    ImageButton btnNext;
    String result;
    private DatabaseReference mDatabase;
    private FirebaseUser fb_user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puz3);

        btnNext = (ImageButton) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                edit_text = (EditText) findViewById(R.id.resultPuz3);
                result = edit_text.getText().toString();

                if (result.equals("144"))
                {
//                    Toast.makeText(Puz1.this, "Right!!!", Toast.LENGTH_LONG).show();
                    MainActivity.score += 1;
                }

                mDatabase = FirebaseDatabase.getInstance().getReference();
                Player player = new Player(fb_user.getEmail(), MainActivity.score);
                mDatabase.child("Player").push().setValue(player);

                Intent intent = new Intent(Puz3.this, Score.class);
                startActivity(intent);
                finish();

            }
        });
    }
}
