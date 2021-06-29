package com.example.doctor_patient_app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.doctor_patient_app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CreditsFragment extends Fragment {
    private TextView creditsText;
    private Button parseButton;

    private RequestQueue mQueue;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.credits_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        creditsText = view.findViewById(R.id.credits_name);
        parseButton = view.findViewById(R.id.parse_button);
        mQueue = Volley.newRequestQueue(getContext());
        parseButton.setOnClickListener(v -> {
            jsonParse();
        });
    }

    private void jsonParse(){
        String url = "https://mocki.io/v1/ecb79a00-1466-4210-a493-5bdaeeff6db8";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("creators");

                            for (int index = 0; index < jsonArray.length(); index++){
                                JSONObject creator = jsonArray.getJSONObject(index);

                                String name = creator.getString("name");
                                String email = creator.getString("email");

                                creditsText.append(name + "\n" + email + "\n\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Something went wrong!",Toast.LENGTH_SHORT).show();
            }
        });
        mQueue.add(request);
    }


}
