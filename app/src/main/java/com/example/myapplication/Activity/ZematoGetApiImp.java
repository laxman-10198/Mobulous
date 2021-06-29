package com.example.myapplication.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication.Adapter.ZematoAdapter;
import com.example.myapplication.Interface.ZematoListener;
import com.example.myapplication.Model.Zemato.ZematoResponse;
import com.example.myapplication.R;
import com.example.myapplication.Retrofit.APIClient;
import com.example.myapplication.Retrofit.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ZematoGetApiImp extends AppCompatActivity implements ZematoListener {
    Toolbar toolbar;
    RecyclerView recyclerView;
    EditText editText;
    APIClient apiClient;
    static final String URL = "https://developers.zomato.com/";
    String searchString = "";
    TextView textView;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zemato_get_api_imp);
        toolbar = findViewById(R.id.toolbar_zemato);
        editText = toolbar.findViewById(R.id.search_word);
        mProgressDialog = new ProgressDialog(this);


        toolbar.findViewById(R.id.bookmarks).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), BookMarkActivity.class));
            }
        });
        textView = findViewById(R.id.error);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.rv_zemato);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        getZematoSearchApi();
        queryFiredAgain();

    }


    private void getZematoSearchApi() {
        mProgressDialog.setMessage("Loading");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        apiClient = new APIClient(URL);
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ZematoResponse> call = apiInterface.getAllSearch(searchString);
        call.enqueue(new Callback<ZematoResponse>() {
            @Override
            public void onResponse(Call<ZematoResponse> call, Response<ZematoResponse> response) {
                if (response.body().getRestaurants() != null && !response.body().getRestaurants().isEmpty()) {
                    try {
                        recyclerView.setAdapter(new ZematoAdapter(getApplicationContext(), response.body().getRestaurants(),
                                ZematoGetApiImp.this));
                        textView.setText("");
                        mProgressDialog.dismiss();

                    } catch (Exception e) {
                        textView.setText(e.getMessage());
                        mProgressDialog.dismiss();
                    }

                } else {
                    recyclerView.setAdapter(new ZematoAdapter(getApplicationContext(), response.body().getRestaurants(),
                            ZematoGetApiImp.this));
                    textView.setText("Not Data Found");
                    mProgressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ZematoResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                textView.setText(t.getMessage());
                mProgressDialog.dismiss();

            }
        });

    }

    private void queryFiredAgain() {
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    searchString = editText.getText().toString();
                    getZematoSearchApi();
                }
                return false;
            }
        });
    }


    @Override
    public void zematoSearchDataStatus(String string) {
        textView.setText(string);
    }
}

