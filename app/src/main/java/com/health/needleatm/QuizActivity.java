package com.health.needleatm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.slider.Slider;
import com.health.needleatm.model.Question;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";

    ImageView quiz_backBtn;

    TextView quiz_quesNum;

    LinearLayout questionBody;
    MaterialButton prevBtn;
    MaterialButton nextBtn;
    MaterialButton resBtn;

    int current_ques_number = 1;

    ArrayList<Question> questions = new ArrayList<>();
    boolean[] isSubmitted = new boolean[18];

    View view_mc;
    View view_sc;
    View view_slider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        quiz_backBtn = findViewById(R.id.quiz_backBtn);

        quiz_quesNum = findViewById(R.id.quiz_questionNum);

        questionBody = (LinearLayout) findViewById(R.id.quiz_question);
        prevBtn = findViewById(R.id.quiz_prevBtn);
        nextBtn = findViewById(R.id.quiz_nextBtn);
        resBtn = findViewById(R.id.quiz_results);

        // Multiple Choice
        view_mc = getLayoutInflater().inflate(R.layout.view_multiplechoice, questionBody, false);

        // Single Choice
        view_sc = getLayoutInflater().inflate(R.layout.view_singlechoice, questionBody, false);

        // Slider
        view_slider = getLayoutInflater().inflate(R.layout.view_slider, questionBody, false);

        addQuestions();

        updateView();

        prevBtn.setOnClickListener(v -> {
            current_ques_number = current_ques_number <= 1 ? 1 : current_ques_number - 1;
            updateView();
        });

        nextBtn.setOnClickListener(v -> {
            current_ques_number = current_ques_number >= 18 ? 18 : current_ques_number + 1;
            updateView();
        });

        quiz_backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        resBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int points = calculateScore();
                Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                intent.putExtra("points", points);
                startActivity(intent);
                finish();
            }
        });
    }

    private int calculateScore() {

        int score = 0;
        //        Question q1 = new Question(1,  1, "1");
        //        Question q2 = new Question(2,  0, "1,4,6,10,11,12,14");
        //        Question q3 = new Question(3,  0, "1,2,3,4");
        //        Question q4 = new Question(4,  1, "3");
        //        Question q5 = new Question(5,  1, "1");
        //        Question q6 = new Question(6,  0, "1,2,3,4,5,6,7");
        //        Question q7 = new Question(7,  1, "2");
        //        Question q8 = new Question(8,  0, "1,2,3,4,5,6");
        //        Question q9 = new Question(9,  1, "1");
        //        Question q10 = new Question(10, 2, "17");
        //        Question q11 = new Question(11, 2, "53");
        //        Question q12 = new Question(12, 1, "1");
        //        Question q13 = new Question(13, 1, "1");
        //        Question q14 = new Question(14, 1, "4");
        //        Question q15 = new Question(15, 1, "1");
        //        Question q16 = new Question(16, 1, "1");
        //        Question q17 = new Question(17, 1, "3");
        //        Question q18 = new Question(18, 1, "1");

        for (Question ques : questions) {
            int quesNum = ques.getQuesNumber();
            if (quesNum == 7 || quesNum == 13 || quesNum == 18) continue;

            int quesType = ques.getQuesType();
            if (quesType == 0) {
                ArrayList<String> answers = new ArrayList<>(Arrays.asList(ques.getAnswer().split(",")));
                if (quesNum == 2) {
                    if (answers.contains("1") && answers.contains("4") && answers.contains("6") && answers.contains("10") && answers.contains("11") && answers.contains("12") && answers.contains("14"))
                        score++;
                    else if (answers.contains("2") || answers.contains("3") || answers.contains("5") || answers.contains("7") || answers.contains("8") || answers.contains("9") || answers.contains("13") || answers.contains("15"))
                        score--;
                } else if (quesNum == 3) {
                    if (answers.contains("1") && answers.contains("2") && answers.contains("3"))
                        score++;
                    else if (answers.contains("4")) score++;
                } else if (quesNum == 6) {
                    if (answers.contains("1") && answers.contains("2") && answers.contains("3") && answers.contains("4") && answers.contains("5") && answers.contains("6") && answers.contains("7"))
                        score++;
                } else if (quesNum == 8) {
                    if (answers.contains("1") && answers.contains("2") && answers.contains("3") && answers.contains("4") && answers.contains("5"))
                        score++;
                    else if (answers.contains("6")) score++;
                }

            } else if (quesType == 1) {
                int answer = Integer.parseInt(ques.getAnsOptions());
                int answered = Integer.parseInt(ques.getAnswer());
                if (answer == answered) score++;
            } else if (quesType == 2) {
                int val = Integer.parseInt(ques.getAnswer());
                if (quesNum == 10) {
                    if (val >= 14 && val <= 20) {
                        score++;
                    }
                } else if (quesNum == 11) {
                    if (val >= 50 && val <= 56) {
                        score++;
                    }
                }
            }

            Log.d(TAG, "calculateScore: Ques Num = "+quesNum+" Answers = "+ques.getAnswer()+" Score = "+score);
        }

        Log.d(TAG, "calculateScore: FINAL SCORE = "+score);

        return score;

    }

    private void updateView() {
        String quesNumText = "Question " + current_ques_number + "/18";
        quiz_quesNum.setText(quesNumText);

        int ques_type = questions.get(current_ques_number - 1).getQuesType();
        if (ques_type == 0) {
            multiplechoiceview();
        } else if (ques_type == 1) {
            singlechoiceview();
        } else if (ques_type == 2) {
            sliderview();
        }
    }

    private void multiplechoiceview() {
        questionBody.removeAllViews();
        questionBody.addView(view_mc);

        TextView mc_ques = findViewById(R.id.mc_ques);
        MaterialButton mc_submit = findViewById(R.id.mc_submitBtn);
        MaterialButton mc_explain = findViewById(R.id.mc_explain);

        Question q = questions.get(current_ques_number - 1);
        mc_ques.setText(q.getQuesEng());

        String[] options = q.getQuesOptions().split("~");
//        Toast.makeText(this, ""+options.length, Toast.LENGTH_SHORT).show();

        MaterialButton opt1 = findViewById(R.id.mc_opt1);
        MaterialButton opt2 = findViewById(R.id.mc_opt2);
        MaterialButton opt3 = findViewById(R.id.mc_opt3);
        MaterialButton opt4 = findViewById(R.id.mc_opt4);
        MaterialButton opt5 = findViewById(R.id.mc_opt5);
        MaterialButton opt6 = findViewById(R.id.mc_opt6);
        MaterialButton opt7 = findViewById(R.id.mc_opt7);
        MaterialButton opt8 = findViewById(R.id.mc_opt8);
        MaterialButton opt9 = findViewById(R.id.mc_opt9);
        MaterialButton opt10 = findViewById(R.id.mc_opt10);
        MaterialButton opt11 = findViewById(R.id.mc_opt11);
        MaterialButton opt12 = findViewById(R.id.mc_opt12);
        MaterialButton opt13 = findViewById(R.id.mc_opt13);
        MaterialButton opt14 = findViewById(R.id.mc_opt14);
        MaterialButton opt15 = findViewById(R.id.mc_opt15);

        ArrayList<MaterialButton> allBtns = new ArrayList<>();
        boolean[] btnSelected = new boolean[options.length];
        allBtns.add(opt1);
        allBtns.add(opt2);
        allBtns.add(opt3);
        allBtns.add(opt4);
        allBtns.add(opt5);
        allBtns.add(opt6);
        allBtns.add(opt7);
        allBtns.add(opt8);
        allBtns.add(opt9);
        allBtns.add(opt10);
        allBtns.add(opt11);
        allBtns.add(opt12);
        allBtns.add(opt13);
        allBtns.add(opt14);
        allBtns.add(opt15);

        if (!isSubmitted[current_ques_number - 1]) {
            mc_submit.setEnabled(true);
            mc_submit.setVisibility(View.VISIBLE);

            mc_explain.setEnabled(false);
            mc_explain.setVisibility(View.GONE);

            for (int i = 0; i < allBtns.size(); i++) {

                MaterialButton btn = allBtns.get(i);
                btn.setVisibility(View.VISIBLE);
                btn.setEnabled(true);
                btn.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.white));
                if (i >= options.length) {
//                    Log.d(TAG, "multiplechoiceview: " + i);
                    btn.setVisibility(View.GONE);
                    continue;
                }
                btn.setText(options[i]);
                int finalI = i;
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean selected = btnSelected[finalI];
                        if (!selected) {
                            btn.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.gold));
                        } else {
                            btn.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.white));
                        }
                        btnSelected[finalI] = !selected;
                    }
                });
            }
        } else {
            mc_submit.setEnabled(false);
            mc_submit.setVisibility(View.GONE);

            mc_explain.setEnabled(true);
            mc_explain.setVisibility(View.VISIBLE);

            Log.d(TAG, "singlechoiceview: CURR QUES NUM="+q.getQuesNumber()+"  CC="+current_ques_number+"   ANS="+q.getAnswer());

            ArrayList<String> answers = new ArrayList<>(Arrays.asList(q.getAnsOptions().split(",")));
            ArrayList<String> answered = new ArrayList<>(Arrays.asList(q.getAnswer().split(",")));
            for (int i = 0; i < allBtns.size(); i++) {
                MaterialButton btn = allBtns.get(i);
                btn.setVisibility(View.VISIBLE);
                btn.setEnabled(false);
                btn.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.white));

                if (i >= options.length) {
//                    Log.d(TAG, "multiplechoiceview: " + i);
                    btn.setVisibility(View.GONE);
                    continue;
                }
                btn.setText(options[i]);

                String opt_num = String.valueOf(i + 1);
                if (answers.contains(opt_num)) {
                    //Correct
                    btn.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.green));
                } else {
                    if (answered.contains("" + (i + 1))) {
                        //Wrong
                        btn.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.red));
                    }
                }
            }
        }

        mc_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSubmitted[current_ques_number - 1] = true;

                mc_submit.setEnabled(false);
                mc_submit.setVisibility(View.GONE);

                mc_explain.setEnabled(true);
                mc_explain.setVisibility(View.VISIBLE);

                ArrayList<String> answers = new ArrayList<>(Arrays.asList(q.getAnsOptions().split(",")));
                StringBuilder ans = new StringBuilder();
                for (int i = 0; i < options.length; i++) {
                    MaterialButton btn = allBtns.get(i);
                    btn.setEnabled(false);
                    String opt_num = String.valueOf(i + 1);
                    if (answers.contains(opt_num)) {
                        //Correct
                        btn.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.green));
                    } else {
                        if (btnSelected[i]) {
                            //Wrong
                            btn.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.red));
                        }
                    }
                    if (btnSelected[i]) ans.append((i + 1)).append(",");
                }
                if (ans.length() > 0) {
                    q.setAnswer(ans.substring(0, ans.length() - 1));
                }
                Log.d(TAG, "onClick: ANS=" + q.getAnswer());

                allAnswered();
                showExplanation(q);
            }
        });

        mc_explain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExplanation(q);
            }
        });

    }

    private void singlechoiceview() {
        questionBody.removeAllViews();
        questionBody.addView(view_sc);

        TextView sc_ques = findViewById(R.id.sc_ques);

        Question q = questions.get(current_ques_number - 1);
        sc_ques.setText(q.getQuesEng());

        MaterialButton sc_submit = findViewById(R.id.sc_submitBtn);
        MaterialButton sc_explain = findViewById(R.id.sc_explain);

        String[] options = q.getQuesOptions().split("~");
//        Toast.makeText(this, ""+options.length, Toast.LENGTH_SHORT).show();

        MaterialButton opt1 = findViewById(R.id.sc_opt1);
        MaterialButton opt2 = findViewById(R.id.sc_opt2);
        MaterialButton opt3 = findViewById(R.id.sc_opt3);
        MaterialButton opt4 = findViewById(R.id.sc_opt4);
        MaterialButton opt5 = findViewById(R.id.sc_opt5);
        MaterialButton opt6 = findViewById(R.id.sc_opt6);

        ArrayList<MaterialButton> allBtns = new ArrayList<>();

        allBtns.add(opt1);
        allBtns.add(opt2);
        allBtns.add(opt3);
        allBtns.add(opt4);
        allBtns.add(opt5);
        allBtns.add(opt6);

        if (!isSubmitted[current_ques_number - 1]) {
            sc_submit.setEnabled(true);
            sc_submit.setVisibility(View.VISIBLE);

            sc_explain.setEnabled(false);
            sc_explain.setVisibility(View.GONE);

            for (int i = 0; i < allBtns.size(); i++) {

                MaterialButton btn = allBtns.get(i);
                btn.setVisibility(View.VISIBLE);
                btn.setEnabled(true);
                btn.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.white));
                if (i >= options.length) {
                    btn.setVisibility(View.GONE);
                    continue;
                }
                btn.setText(options[i]);
                int finalI = i;
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (q.getAnswer().isEmpty()) {
                            btn.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.gold));
                        } else {
                            for (MaterialButton btn1 : allBtns) {
                                btn1.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.white));
                            }
                            btn.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.gold));
                        }
                        q.setAnswer((finalI + 1) + "");
                    }
                });
            }
        } else {
            sc_submit.setEnabled(false);
            sc_submit.setVisibility(View.GONE);

            if (current_ques_number != 7) {
                sc_explain.setEnabled(true);
                sc_explain.setVisibility(View.VISIBLE);
            }
            Log.d(TAG, "singlechoiceview: CURR QUES NUM="+q.getQuesNumber()+"  CC="+current_ques_number+"   ANS="+q.getAnswer());
            String answer = q.getAnsOptions();
            String answered = q.getAnswer();

            for (int i = 0; i < allBtns.size(); i++) {
                MaterialButton btn = allBtns.get(i);
                btn.setVisibility(View.VISIBLE);
                btn.setEnabled(false);
                btn.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.white));

                if (i >= options.length) {
//                    Log.d(TAG, "multiplechoiceview: " + i);
                    btn.setVisibility(View.GONE);
                    continue;
                }
                btn.setText(options[i]);

                if (answer.equals("" + (i + 1))) {
                    //Correct
                    btn.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.green));
                } else {
                    if (answered.equals("" + (i + 1))) {
                        //Wrong
                        btn.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.red));
                    }
                }
            }
        }

        sc_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSubmitted[current_ques_number - 1] = true;

                sc_submit.setEnabled(false);
                sc_submit.setVisibility(View.GONE);

                if (current_ques_number != 7) {
                    sc_explain.setEnabled(true);
                    sc_explain.setVisibility(View.VISIBLE);
                }

                String answer = q.getAnsOptions();
                String answered = q.getAnswer();

                for (int i = 0; i < options.length; i++) {
                    MaterialButton btn = allBtns.get(i);
                    btn.setEnabled(false);
                    String opt_num = String.valueOf(i + 1);
                    if (answer.equals("" + (i + 1))) {
                        //Correct
                        btn.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.green));
                    } else {
                        if (answered.equals("" + (i + 1))) {
                            //Wrong
                            btn.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.red));
                        }
                    }

                }

                allAnswered();
                if (current_ques_number != 7) {
                    showExplanation(q);
                }

            }
        });

        sc_explain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExplanation(q);
            }
        });
    }

    private void sliderview() {
        questionBody.removeAllViews();
        questionBody.addView(view_slider);

        TextView slider_ques = findViewById(R.id.slider_ques);
        Slider slider = findViewById(R.id.slider_slider);
        TextView slider_text = findViewById(R.id.slider_text);

        MaterialButton slider_submitBtn = findViewById(R.id.slider_submitBtn);
        MaterialButton slider_explain = findViewById(R.id.slider_explain);

        Question q = questions.get(current_ques_number - 1);
        slider_ques.setText(q.getQuesEng());

        if (isSubmitted[current_ques_number - 1]) {
            slider.setEnabled(false);
            slider_submitBtn.setEnabled(false);
            slider_submitBtn.setVisibility(View.GONE);

            slider_explain.setEnabled(true);
            slider_explain.setVisibility(View.VISIBLE);

            int val = Integer.parseInt(q.getAnswer());
            slider.setValue(val);
            slider_text.setText(val + "%");

            if (q.getQuesNumber() == 10) {
                if (val >= 14 && val <= 20) {
                    slider_text.setBackgroundResource(R.color.green);
                    if(val != 17){
                        slider_text.setText(val + "%\nClose Enough");
                    }
                } else {
                    slider_text.setBackgroundResource(R.color.red);
                    slider_text.setText(val + "%\nToo Far");
                }
            } else if (q.getQuesNumber() == 11) {
                if (val >= 50 && val <= 56) {
                    slider_text.setBackgroundResource(R.color.green);
                    if(val != 53){
                        slider_text.setText(val + "%\nClose Enough");
                    }
                } else {
                    slider_text.setBackgroundResource(R.color.red);
                    slider_text.setText(val + "%\nToo Far");
                }
            }

        } else {
            slider.setEnabled(true);
            slider_submitBtn.setEnabled(true);
            slider_submitBtn.setVisibility(View.VISIBLE);

            slider_explain.setEnabled(false);
            slider_explain.setVisibility(View.GONE);
            slider_text.setBackgroundResource(R.color.white);

            slider.setValue(0.0f);
            slider_text.setText("0%");

            slider.addOnChangeListener(new Slider.OnChangeListener() {
                @Override
                public void onValueChange(@NonNull @org.jetbrains.annotations.NotNull Slider slider, float value, boolean fromUser) {
                    int val = (int) value;
                    slider_text.setText(val + "%");
                }
            });

            slider_submitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isSubmitted[current_ques_number - 1] = true;
                    slider.setEnabled(false);
                    slider_submitBtn.setVisibility(View.GONE);

                    slider_explain.setEnabled(true);
                    slider_explain.setVisibility(View.VISIBLE);
                    int val = (int) slider.getValue();
                    q.setAnswer(val + "");

                    if (q.getQuesNumber() == 10) {
                        if (val >= 14 && val <= 20) {
                            slider_text.setBackgroundResource(R.color.green);
                            if(val != 17){
                                slider_text.setText(val + "%\nClose Enough");
                            }
                        } else {
                            slider_text.setBackgroundResource(R.color.red);
                            slider_text.setText(val + "%\nToo Far");
                        }
                    } else if (q.getQuesNumber() == 11) {
                        if (val >= 50 && val <= 56) {
                            slider_text.setBackgroundResource(R.color.green);
                            if(val != 53){
                                slider_text.setText(val + "%\nClose Enough");
                            }
                        } else {
                            slider_text.setBackgroundResource(R.color.red);
                            slider_text.setText(val + "%\nToo Far");
                        }
                    }

                    allAnswered();

                    showExplanation(questions.get(current_ques_number - 1));
                }
            });

            slider_explain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Log.d(TAG, "onClick: "+q.getQuesNumber()+" | "+current_ques_number);
                    showExplanation(questions.get(current_ques_number - 1));
                }
            });
        }
    }

    private void showExplanation(Question q) {
        FragmentManager fm = getSupportFragmentManager();
        ExplanationFragment surveyDialogFragment = new ExplanationFragment(q);
        surveyDialogFragment.show(fm, "ques_expl");
    }

    private void allAnswered() {
        for (boolean b : isSubmitted) {
            if (!b) return;
        }

        resBtn.setVisibility(View.VISIBLE);
    }

    private void addQuestions() {
        Question q1 = new Question(1, "What is HIV?", "", 1, "It is a virus that lowers immunity over years and make your body more susceptible to diseases and infections.~It is an airborne flu virus that can cause pneumonia.~It is a food and waterborne virus that affects your brain and degrades it over years to cause loss of memory.~It is a bacteria that causes diarrhoea.", "1");
        Question q2 = new Question(2, "How is it transmitted?  Choose the correct ones.", "", 0, "Blood~Saliva~Vomit~Pre-seminal fluid (pre-cum)~Nasal fluid~Sharing needles while injecting drugs~Tears from eyes~Sweat~Urine~Semen~Breast Milk~Vaginal fluids~Feces~Anal mucous~Spending time with someone who has AIDS/HIV", "1,4,6,10,11,12,14");
        Question q3 = new Question(3, "Which of these is a symptom of HIV infection ?", "", 0, "Swollen lymph nodes~Fever~Tiredness~ All of the above", "1,2,3,4");
        Question q4 = new Question(4, "What is AIDS – acquired immunodeficiency syndrome ?", "", 1, "Exposure to HIV~HIV antibodies are found in the blood~The CD4+ count is lower than 200 or opportunistic infections develop in an HIV-infected person~A person has HIV for 5 years", "3");
        Question q5 = new Question(5, "What does HIV-positive mean?", "", 1, "Either antibodies against HIV or the virus particles themselves are present in the blood~You have been tested for HIV~Your white cell count is high~You have been informed about HIV", "1");
        Question q6 = new Question(6, "What all can be called as substance abuse?", "", 0, "Using a substance longer than intended~Desire for or unsuccessful efforts to reduce or cease use of the substance~Large amount of time trying to obtain, use, or recover from a substance~The use of the substance results in a failure to fulfil life obligation~Use continues despite causing social disturbances (family, job, etc...)?~Tolerance? (Need for increasingly more amounts of substance to achieve the same effect)~Withdrawal? (Experiencing physical or mental symptoms on abruptly stopping the substance)", "1,2,3,4,5,6,7");
        Question q7 = new Question(7, "Have you ever involved yourself as an injection drug abuser ?", "", 1, "YES~NO", "2");
        Question q8 = new Question(8, "What are the infections associated with intravenous drug use ?", "", 0, "HIV~Viral hepatitis – Hepatitis C, Hepatitis D, Hepatitis B ~Skin infections~Infective Endocarditis (heart infection)~Pulmonary infections (chest infection)~All of the above", "1,2,3,4,5,6");
        Question q9 = new Question(9, "Is there increased risk of dying in IV drug abusers compared to someone who ages normally ?", "", 1, "YES~NO", "1");
        Question q10 = new Question(10, "What percent (%) total injection drug users in the world are affected with HIV ?", "", 2, "", "17");
        Question q11 = new Question(11, "What percent (%) of total injection drug users are affected with HCV(Hepatitis C Virus) ?", "", 2, "", "53");
        Question q12 = new Question(12, "If you think you've been exposed to HIV, when should you consult doctor to start Post-exposure prophylaxis ?", "", 1, "within 3 days~3-5 days~5-7 days~> 7 days", "1");
        Question q13 = new Question(13, "Have you heard of Syringe Service Programs (SSPs) or Needle exchange programs (NEPs) ?", "", 1, "YES~NO", "1");
        Question q14 = new Question(14, "How does injecting drugs increase the risk of HIV, Hepatitis C virus, Hepatitis B virus ? ", "", 1, "Lowers inhibitions & alters judgement resulting in poor decisions such as - sex without condom, multiple sexual partners, sharing syringe/needle/other equipment~Turning to other ways to get the drug, including trading sex for drugs or money, which increases HIV risk.~Drugs lower immunity which make it easy to contract the viruses~Both A and B", "4");
        Question q15 = new Question(15, "If you are an intravenous drug user, how often should you test yourself for HIV ?", "", 1, "At least once a year~Once in 2 years~Once in 5 years~Never", "1");
        Question q16 = new Question(16, "Have you heard of any Needle exchange programmes or visited any in your area ?", "", 1, "YES~NO", "1");
        Question q17 = new Question(17, "What is the best way to lower your risk of getting infected with HIV(Human Immunodeficiency Virus), HCV(Hepatitis C Virus), HBV(Hepatitis B Virus) if you are an injectable drug user ?", "", 1, "Use sterile needles~Take pre-exposure  prophylaxis For HIV~Stop injectable drug use~Do not share needles or other equipments", "3");
        Question q18 = new Question(18, "Do you think SSPs can help you  to stop using drugs?", "", 1, "YES~NO", "1");

        questions.add(q1);
        questions.add(q2);
        questions.add(q3);
        questions.add(q4);
        questions.add(q5);
        questions.add(q6);
        questions.add(q7);
        questions.add(q8);
        questions.add(q9);
        questions.add(q10);
        questions.add(q11);
        questions.add(q12);
        questions.add(q13);
        questions.add(q14);
        questions.add(q15);
        questions.add(q16);
        questions.add(q17);
        questions.add(q18);

    }
}