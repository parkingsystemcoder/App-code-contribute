package com.example.yenchang.myapplication.tabs_refragments;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yenchang.myapplication.R;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import static com.example.yenchang.myapplication.LoginActivity.username;

public class GetCode extends Fragment {
    private static final String ARG_EXAMPLE = "this_is_a_constant";
    private String example_data;

    EditText et1;
    TextView tv;
    Button btnNav;
    String Data;
    String result;
    InputStream isr;

    public GetCode() {
    }

    public static GetCode newInstance(String example_argmument) {
        GetCode getCode= new GetCode();
        Bundle args = new Bundle();
        args.putString(ARG_EXAMPLE, example_argmument);
        getCode.setArguments(args);
        return getCode;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        example_data = getArguments().getString(ARG_EXAMPLE);
        Log.i("Fragment created with ", example_data);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.get_code, container, false);

        et1 = (EditText) rootView.findViewById(R.id.editText);
        tv = (TextView) rootView.findViewById(R.id.editText);

        btnNav = (Button)rootView.findViewById(R.id.payButton);

        btnNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getData().execute("");
            }
        });
        return rootView;
    }

    class getData extends AsyncTask<String, Void, String> {

        //String username = LoginActivity.UserLoginTask.;
        //String result = "";

        @Override
        protected String doInBackground(String... params) {
            try {
                //String name = "yenchang";
                //String login_url = "http://dbyytesting123.000webhostapp.com/getCode.php";
                String login_url = "http://61.6.71.32:8080/getCode_final.php";
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(10000);
                httpURLConnection.setConnectTimeout(15000);

                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                //return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Data = "alallaa1";
            try {
                JSONArray arr = new JSONArray(result);
                Data = "LALALA1";
                //JSONObject jObj = arr.getJSONObject(0);
                Data = "LALALA2";
                //Data = jObj.getString("username");
                Data = "LALALA3";
            } catch (Exception e) {
                // TODO: handle exception
                Data = "1234";
                Log.e("log_tag", "Error Parsing Data " + e.toString());
            }

            //return null;
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            tv.setText(result);
        }
    }
}