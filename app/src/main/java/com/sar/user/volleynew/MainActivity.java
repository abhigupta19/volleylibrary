package com.sar.user.volleynew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
private static final String url="https://api.myjson.com/bins/1cl2ce";
TextView textView;
    RequestQueue mquee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.button);
         textView=findViewById(R.id.textView);
       mquee =Volley.newRequestQueue(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volley();
            }
        });


    }
    private void volley() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arrayRequest = response.getJSONArray("radha");
                    for (int i = 0; i < arrayRequest.length(); i++) {
                        JSONObject jsonObject = arrayRequest.getJSONObject(i);
                        int a = jsonObject.getInt("id");
                        String b = jsonObject.getString("name");
                        String c = jsonObject.getString("paradigm");
                        textView.append("id=" + Integer.toString(a) + "  name =" + b + " para=" + c + "\n\n");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        mquee.add(request);
    }
}
