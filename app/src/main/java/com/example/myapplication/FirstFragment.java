package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private EditText txt_nombre;
    private EditText txt_informacion;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Asigancion de valores
        txt_nombre = (EditText)binding.txtNombre;
        txt_informacion = (EditText)binding.txtInformacion;

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        binding.btmGuardar.setOnClickListener((View v)->{
            Guardar();
        });

        binding.btmBuscar.setOnClickListener((View V)->{
            Buscar();
        });

    }

    public void Guardar(){
        SharedPreferences pref = getActivity().getSharedPreferences("datos",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(txt_nombre.getText().toString(),txt_informacion.getText().toString());
        editor.commit();
        getActivity().finish();
    }

    public void Buscar(){
        SharedPreferences pref = getActivity().getSharedPreferences("datos",Context.MODE_PRIVATE);
        String nombre = pref.getString(txt_nombre.getText().toString(),"");
        if(nombre.length() == 0){
            Toast.makeText(getContext(),"No se encontraron datos.",Toast.LENGTH_SHORT).show();
        } else{
            txt_informacion.setText(nombre);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}