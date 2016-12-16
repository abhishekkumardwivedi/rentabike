package com.crossover.jitensha.rentabike;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.crossover.jitensha.rentabike.helper.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by abhishek on 15/12/16.
 */
public class PaymentActivity extends Activity implements View.OnClickListener {
    private static final String TAG = PaymentActivity.class.getSimpleName();
    private ProgressDialog pDialog;

    private EditText tvCardNumber;
    private EditText tvName;
    private EditText tvExpDate;
    private EditText tvCvc;
    private Button btnCancel;
    private Button btnSubmit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        tvCardNumber = (EditText) findViewById(R.id.text_number);
        tvName = (EditText) findViewById(R.id.text_name);
        tvExpDate = (EditText) findViewById(R.id.text_expiry);
        tvCvc = (EditText) findViewById(R.id.text_cvc);
        btnCancel = (Button) findViewById(R.id.button_cancel);
        btnSubmit = (Button) findViewById(R.id.button_submit);

        btnCancel.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
    }

    private void submitPayment(final String number, final String name,
                               final String expiration, final String code) {

        pDialog.setMessage("Processing payment ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConstants.URL_RENT, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Payment Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    String message = jObj.getString("message");
                    if (message != null) {

                        Toast.makeText(getApplicationContext(), message,
                                Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "payment failed!!", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Payment Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        "payment error!!", Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("number", number);
                params.put("name", name);
                params.put("expiration", expiration);
                params.put("code", code);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() {

                // Session manager
                SessionManager session = new SessionManager(getApplicationContext());
                String token = session.getAccessToken();

                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", token);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(strReq);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_cancel:
                Log.d(TAG, "payment cancelled!!");
                finish();
                break;
            case R.id.button_submit:
                String number = tvCardNumber.getText().toString();
                String name = tvName.getText().toString();
                String expiration = tvExpDate.getText().toString();
                String code = tvCvc.getText().toString();
                submitPayment(number, name, expiration, code);
                break;
        }
    }
}
