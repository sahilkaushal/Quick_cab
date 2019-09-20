package com.example.touchstone.demo.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.touchstone.demo.Home;
import com.example.touchstone.demo.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragement.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragement#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragement extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView emailEdit, frist_name, last_name;
    String enteredEmail, fristnameedit, lastnameedit;
    private String emailedit, frist, last;
    Button logout;
    private ImageView profielImageView ;


    // TODO: Rename and change types and number of parameters
    public static ProfileFragement newInstance(String param1, String param2) {
        ProfileFragement fragment = new ProfileFragement();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile_fragement, container, false);

        emailEdit = (TextView) v.findViewById(R.id.Get_email);
        frist_name = (TextView) v.findViewById(R.id.Get_frist_name);
        last_name = (TextView) v.findViewById(R.id.Get_last_name);
        profielImageView = (ImageView) v.findViewById(R.id.profile_page);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("signup", getActivity().MODE_PRIVATE);
        String email = sharedPreferences.getString("email", null);
        String fristname = sharedPreferences.getString("fristname", null);
        String lastname = sharedPreferences.getString("lastname", null);


        SharedPreferences sharedPreferences1 =  getActivity().getSharedPreferences("image",getActivity().MODE_PRIVATE);
        String image = sharedPreferences1.getString("output",null);
        String image1 = sharedPreferences1.getString("output",null);

        SharedPreferences sharedPreferences2 = getActivity().getSharedPreferences("profile_image",getActivity().MODE_PRIVATE);
        String image2 = sharedPreferences2.getString("image",null);
        Bitmap bitmap = StringToBitMap(image2);
        profielImageView.setImageBitmap(bitmap);



        emailEdit.setText(email);
        frist_name.setText(fristname);
        last_name.setText(lastname);
logout=(Button) v.findViewById(R.id.log_out);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                      SharedPreferences.Editor sharedPreferences2 = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE).edit();
                                      sharedPreferences2.putBoolean("value",false);
                                      sharedPreferences2.commit();
                Intent intent = new Intent(getActivity(), Home.class);
                startActivity(intent);
                getActivity().finish();


            }
        });




        // Inflate the layout for this fragment
        return v;
    }

    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }





}
