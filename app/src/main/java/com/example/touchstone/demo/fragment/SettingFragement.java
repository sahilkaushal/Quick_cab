package com.example.touchstone.demo.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.touchstone.demo.Login;
import com.example.touchstone.demo.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SettingFragement.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SettingFragement#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingFragement extends Fragment {
    TextView emailEdit;
    private EditText Setting_oldpassword_edit_field,New_password_edit_field ,Confirm_password_edit_field;
    Button submiButton ;
    String oldPassword ;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_setting_fragement, container, false);


        emailEdit = (TextView) v.findViewById(R.id.Email_edit_field);
        Setting_oldpassword_edit_field = (EditText) v.findViewById(R.id.Setting_oldpassword_edit_field);
        New_password_edit_field = (EditText) v.findViewById(R.id.New_password_edit_field);
        Confirm_password_edit_field = (EditText) v.findViewById(R.id.Confirm_password_edit_field);
        submiButton = (Button) v.findViewById(R.id.button);


        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("signup", getActivity().MODE_PRIVATE);
        String email = sharedPreferences.getString("email", null);
        oldPassword = sharedPreferences.getString("password", null);
        emailEdit.setText(email);



        submiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String password = Setting_oldpassword_edit_field.getText().toString();

                if(password.equals(oldPassword)){

                    if(New_password_edit_field.getText().toString().equals(Confirm_password_edit_field.getText().toString())){

                        String newPassword = Confirm_password_edit_field.getText().toString();
                        //store the value in Sql lite
                        SharedPreferences.Editor sharedPreferences4 = getActivity().getSharedPreferences("signup", getActivity().MODE_PRIVATE).edit();
                        sharedPreferences4.putString("password", newPassword);
                        sharedPreferences4.commit();


                        Intent intent = new Intent(getActivity(), Login.class);
                        startActivity(intent);
                        getActivity().finish();


                    }else{

                        Toast.makeText(getActivity(), "Password not matched", Toast.LENGTH_SHORT).show();
                    }

                }else{

                    Toast.makeText(getActivity(), "Please enter correct old password", Toast.LENGTH_SHORT).show();
                }




            }
        });



        return v;


    }





}