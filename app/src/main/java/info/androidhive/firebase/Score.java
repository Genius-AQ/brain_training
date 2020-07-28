package info.androidhive.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
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

public class Score extends AppCompatActivity {
    TextView tv, first_player, second_player, third_player;
    private DatabaseReference mDatabase;
    private FirebaseUser fb_user = FirebaseAuth.getInstance().getCurrentUser();
    List<String> topPlayers = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        tv = (TextView) findViewById(R.id.scoreTextView);
        tv.setText("Your score: " + Integer.toString(MainActivity.score));

        first_player = (TextView) findViewById(R.id.top1);
        second_player = (TextView) findViewById(R.id.top2);
        third_player = (TextView) findViewById(R.id.top3);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        Toast.makeText(this, "Please wait...", Toast.LENGTH_LONG).show();
        mDatabase.child("Player").orderByChild("score").limitToLast(3).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Player player = child.getValue(Player.class);
//                            System.out.println("================================" + player.email + " " + player.score);
                    topPlayers.add(player.email + " " + player.score);
                    //System.out.println(topPlayers.get(0));
                }

                first_player.setText(topPlayers.get(2));
                second_player.setText(topPlayers.get(1));
                third_player.setText(topPlayers.get(0));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

    }
}
