package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import com.example.quizapplication.databinding.ActivityMainBinding;
import com.example.quizapplication.model.Question;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int currentQuestionIndex = 0;

    private Question[] questions = new Question[]{
            new Question(R.string.question_independence, true),
            new Question(R.string.question_constitution, true),
            new Question(R.string.question_amendments, false),
            new Question(R.string.question_rights, true),
            new Question(R.string.question_religion, false),
            new Question(R.string.question_government, true),
            new Question(R.string.question_government_laws, false),
            new Question(R.string.question_government_parliament, true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.startBtn.setOnClickListener(view -> {
            binding.trueBtn.setVisibility(View.VISIBLE);
            binding.falseBtn.setVisibility(View.VISIBLE);
            binding.prevBtn.setVisibility(View.VISIBLE);
            binding.nextBtn.setVisibility(View.VISIBLE);

            binding.startBtn.setVisibility(View.INVISIBLE);

            binding.textView.setText(questions[currentQuestionIndex].getAnswerResId());
        });

        binding.nextBtn.setOnClickListener(view -> {
                currentQuestionIndex = (currentQuestionIndex + 1) % questions.length;
                updateQuestion();
                binding.answerText.setVisibility(View.INVISIBLE);
        });

        binding.prevBtn.setOnClickListener(view -> {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex = (currentQuestionIndex - 1) % questions.length;
                updateQuestion();
                binding.answerText.setVisibility(View.INVISIBLE);
            }
        });

        binding.trueBtn.setOnClickListener(view -> checkAnswer(true));
        binding.falseBtn.setOnClickListener(view -> checkAnswer(false));

    }

    private void updateQuestion() {
        binding.textView.setText(questions[currentQuestionIndex].getAnswerResId());
    }

    private void checkAnswer(boolean userAnswer){
        boolean answerIsCorrect = questions[currentQuestionIndex].isAnswerTrue();
        binding.answerText.setVisibility(View.VISIBLE);

        if (answerIsCorrect == userAnswer){
            binding.answerText.setTextColor(ContextCompat.getColor(this, R.color.green));
            binding.answerText.setText(R.string.correct_answer);
//            Snackbar.make(binding.imageView, R.string.correct_answer, Snackbar.LENGTH_SHORT).show();
        }else {
            binding.answerText.setTextColor(ContextCompat.getColor(this, R.color.red));
            binding.answerText.setText(R.string.wrong_answer);
//            Snackbar.make(binding.imageView, R.string.wrong_answer, Snackbar.LENGTH_SHORT).show();
        }
    }

}