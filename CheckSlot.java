package com.example.yenchang.myapplication.tabs_refragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yenchang.myapplication.R;

import org.json.JSONArray;
import org.json.JSONObject;

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

public class CheckSlot extends Fragment {

    private static final String ARG_EXAMPLE = "this_is_a_constant";
    private String example_data;

    EditText et1;
    TextView tv2;
    ListView listView;
    Button btnNav;
    String Data = "";
    String result;
    InputStream isr;

    public CheckSlot() {
    }

    public static CheckSlot newInstance(String example_argmument) {
        CheckSlot checkSlot= new CheckSlot();
        Bundle args = new Bundle();
        args.putString(ARG_EXAMPLE, example_argmument);
        checkSlot.setArguments(args);
        return checkSlot;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        example_data = getArguments().getString(ARG_EXAMPLE);
        Log.i("Fragment created with ", example_data);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.check_slot, container, false);

        //ListView listView = (ListView) rootView.findViewById(R.id.showList);
        tv2 = (TextView) rootView.findViewById(R.id.showParking);
        btnNav = (Button)rootView.findViewById(R.id.getParkingButton);

        btnNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CheckSlot.getData().execute("");
            }
        });
        return rootView;
    }

    class getData extends AsyncTask<String, Void, String> {

        String result = "";
        String Data = "";

        @Override
        protected String doInBackground(String... params) {
            try {
                String parking = "0";
                String login_url = "http://61.6.71.32:8080/checkSlot_final.php";
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(10000);
                httpURLConnection.setConnectTimeout(15000);

                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("code", "UTF-8") + "=" + URLEncoder.encode(parking, "UTF-8");
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

            try {
                JSONArray jArray = new JSONArray(result);
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json = jArray.getJSONObject(i);
                    Data = Data + "\n" + json.getString("username") + "                 " + json.getString("code");
                }
                return Data;
            } catch (Exception e) {
                // TODO: handle exception
                Log.e("log_tag", "Error Parsing Data " + e.toString());
                return "Error Parsing";
            }

            //return null;
            //return result;
        }

        @Override
        protected void onPostExecute(String result) {
            tv2.setText(result);
        }
    }

}
