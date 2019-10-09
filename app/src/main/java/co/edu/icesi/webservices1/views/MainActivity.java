package co.edu.icesi.webservices1.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import co.edu.icesi.webservices1.R;
import co.edu.icesi.webservices1.model.Comment;
import co.edu.icesi.webservices1.model.University;
import co.edu.icesi.webservices1.model.Vehicle;
import co.edu.icesi.webservices1.util.HTTPSWebUtilDomi;

public class MainActivity extends AppCompatActivity {

    private EditText messageText;

    private Button postButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageText = findViewById(R.id.message_text);
        postButton = findViewById(R.id.post_button);

        postButton.setOnClickListener(
                (View v) -> {
                    String message = messageText.getText().toString().trim();
                    String de = "Santiago";
                    String para = "Todos";

                    final Comment comment = new Comment();
                    comment.setComentario(message);
                    comment.setDe(de);
                    comment.setPara(para);

                    new Thread(
                            () -> {
                                try {
                                    HTTPSWebUtilDomi httpManager = new HTTPSWebUtilDomi();
                                    Gson gson = new Gson();
                                    String json = gson.toJson(comment, Comment.class);
                                    String jsonRequest = httpManager.POSTrequest("https://instalacion-bc3ad.firebaseio.com/comentarios.json", json);

                                    Log.e(">>>>", jsonRequest);
                                    
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                    ).start();
                }
        );

        // Single object
        new Thread(
                () -> {
                    try {
                        HTTPSWebUtilDomi httpManager = new HTTPSWebUtilDomi();
                        String json = httpManager.GETrequest("https://instalacion-bc3ad.firebaseio.com/icesi.json");
                        Gson gson = new Gson();
                        University icesi = gson.fromJson(json, University.class);

                        Log.e(">>>>", icesi.getNombre());
                        Log.e(">>>>", icesi.getUbicacion());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        ).start();

        // An array
        new Thread(
                () -> {
                    try {
                        HTTPSWebUtilDomi httpManager = new HTTPSWebUtilDomi();
                        String json = httpManager.GETrequest("https://instalacion-bc3ad.firebaseio.com/vehiculos.json");
                        Gson gson = new Gson();
                        Type type = new TypeToken<ArrayList<Vehicle>>(){}.getType(); // Smell code
                        ArrayList<Vehicle> vehicles = gson.fromJson(json, type);

                        Log.e(">>>>", Integer.toString(vehicles.size()));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        ).start();

        new Thread(
                () -> {
                    try {
                        HTTPSWebUtilDomi httpManager = new HTTPSWebUtilDomi();
                        String json = httpManager.GETrequest("https://instalacion-bc3ad.firebaseio.com/comentarios.json");
                        Gson gson = new Gson();
                        Type type = new TypeToken<HashMap<String, Comment>>(){}.getType();
                        HashMap<String, Comment> comments = gson.fromJson(json, type);

                        Log.e(">>>>", Integer.toString(comments.size()));
                        Log.e(">>>>", comments.get("-LqhEPx7uLJftNeepSRQ").toString());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        ).start();

    }
}
