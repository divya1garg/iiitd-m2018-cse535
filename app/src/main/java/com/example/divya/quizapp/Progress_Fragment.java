package com.example.divya.quizapp;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Progress_Fragment extends Fragment {

    ProgressBar pb;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view= inflater.inflate(R.layout.fragment_progress_, container, false);
        pb=(ProgressBar)view.findViewById(R.id.progressBar1);
        new PostServer(getActivity().getApplicationContext()).execute();
        return view;
    }
    public class PostServer extends AsyncTask<String, Integer, String> {

        Context context;
        int totalSize=0;
        File file;

        PostServer(Context context)
        {
            this.context=context;}

        @Override
        protected void onPreExecute() {
            File exportDir = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "csv");
            file = new File(exportDir, "csvname.csv");
            totalSize = (int)file.length();
            pb.setMax(100);
            pb.setVisibility(getView().VISIBLE);
            super.onPreExecute();

        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            super.onProgressUpdate(values);
            pb.setProgress(values[0]);
        }

        @Override
        protected String doInBackground(String... args) {
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection connection = null;
            String fileName = file.getName();

            try {
                connection = (HttpURLConnection) new URL("http://192.168.51.232/abc.php").openConnection();
                connection.setRequestMethod("POST");
                String boundary = "---------------------------boundary";
                String tail = "\r\n--" + boundary + "--\r\n";
                connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
                connection.setDoOutput(true);

                String metadataPart = "--" + boundary + "\r\n"
                        + "Content-Disposition: form-data; name=\"metadata\"\r\n\r\n"
                        + "" + "\r\n";
                String fileHeader1 = "--" + boundary + "\r\n"
                        + "Content-Disposition: form-data; name=\"fileToUpload\"; filename=\""
                        + fileName + "\"\r\n"
                        + "Content-Type: application/octet-stream\r\n"
                        + "Content-Transfer-Encoding: binary\r\n";
                long fileLength = file.length() + tail.length();
                String fileHeader2 = "Content-length: " + fileLength + "\r\n";
                String fileHeader = fileHeader1 + fileHeader2 + "\r\n";
                String stringData = metadataPart + fileHeader;

                long requestLength = stringData.length() + fileLength;
                connection.setRequestProperty("Content-length", "" + requestLength);
                connection.setFixedLengthStreamingMode((int) requestLength);
                connection.connect();

                DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                out.writeBytes(stringData);
                out.flush();
                int progress = 0;
                int bytesRead = 0;
                byte buf[] = new byte[1024];
                BufferedInputStream bufInput = new BufferedInputStream(new FileInputStream(file));
                while ((bytesRead = bufInput.read(buf)) != -1) {
                    out.write(buf, 0, bytesRead);
                    out.flush();
                    progress += bytesRead;
                    publishProgress((int)((progress*100)/totalSize));
                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException ie)
                    {
                        ie.printStackTrace();
                    }
                }
                out.writeBytes(tail);
                out.flush();
                out.close();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                StringBuilder builder = new StringBuilder();
                while((line = reader.readLine()) != null) {
                    builder.append(line);
                }

            } catch (Exception e) {
                // Exception
            } finally {
                if (connection != null) connection.disconnect();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Toast.makeText(getActivity(),"Upload_complete", Toast.LENGTH_SHORT).show();


        }
    }

}
