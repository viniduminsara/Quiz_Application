package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.example.quizapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.startBtn.setOnClickListener(view -> {
            binding.trueBtn.setVisibility(View.VISIBLE);
            binding.falseBtn.setVisibility(View.VISIBLE);
            binding.startBtn.setVisibility(View.INVISIBLE);
        });
    }
}