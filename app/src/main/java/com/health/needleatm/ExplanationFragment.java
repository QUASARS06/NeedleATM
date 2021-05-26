package com.health.needleatm;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.health.needleatm.model.Question;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class ExplanationFragment extends DialogFragment {

    private static final String TAG = "ExplanationFragment";

    Question q;

    private ImageView mClose;

    String q1 = "<!DOCTYPE html> <html> <head> <title></title> </head> <body style='padding-left: 12px, padding-right: 12px'> <p>Option 1 is correct. HIV is the Human Immunodeficiency Virus. It is a virus that gets into a person’s cells. HIV affects the immune system, specifically the T-Cells or CD4 cells which fight infection. Simply put, the virus destroys the T-cells so that the immune system of a person with untreated HIV infection is not able to fight off diseases and infections.</p> <p>Source: <a href='https://freehivtest.org.in/en/hiv-basics/'>Open Source Link</a></p> </body> </html>";
    String q2 = "<!DOCTYPE html> <html> <head> <title></title> </head> <body style='padding-left: 12px, padding-right: 12px'> <p>HIV is transmitted through the following bodily fluids:</p> <ul> <li>Blood</li> <li>Pre-seminal fluid (pre-cum)</li> <li>Semen</li> <li>Breast Milk</li> <li>Vaginal fluids</li> <li>Anal mucous</li> </ul> <p>When you have sex with someone who is HIV-positive (infected with HIV) the virus can enter your system through small tears in your vagina, anus, penis or – rarely – your mouth. Open sores caused by sexually-transmitted diseases (STDs) such as herpes and syphilis can make it easier for HIV to enter your system. If you are an injection drug-user, HIV can be transmitted when your blood comes into contact with another person’s blood through sharing needles. HIV can pass from mother to child while a woman is pregnant or through breast milk. In rare cases, healthcare workers have come into contact with body fluids and become infected. Effective screening has made HIV infection via blood transfusion or organ donation extremely rare.</p> <p>HIV is NOT transmitted through the following bodily:</p> <ul> <li>Saliva</li> <li>Vomit</li> <li>Feces</li> <li>Nasal fluid</li> <li>Tears</li> <li>Sweat</li> <li>Urine</li> </ul> <p>Source: <a href='https://freehivtest.org.in/en/hiv-basics/'>Open Source Link</a></p> </body> </html>";
    String q3 = "<!DOCTYPE html> <html> <head> <title></title> </head> <body style='padding-left: 12px, padding-right: 12px'> <p>These are symptoms of HIV, but a blood test is the only way to know for sure if you have HIV infection. Symptoms are not always reliable. They can be mistaken for other illnesses. Severe symptoms like rapid weight loss or chronic infections usually don't appear for years, if at all. Even if you don’t have any symptoms, you can still infect other people. This is one important reason why early testing is so important.</p> <p>Source: <a href='https://www.urmc.rochester.edu/encyclopedia/content.aspx?ContentTypeID=40&ContentID=HIVAIDSQuiz&CustomAnswers_HIVAIDSQuiz=q1a3_c,q2a4_c,q3a3_c,q4a1_c,q5a2_c,q6a3_c,q8a4_c,q9a4_c'>Open Source Link</a></p> </body> </html>";
    String q4 = "<!DOCTYPE html> <html> <head> <title></title> </head> <body style='padding-left: 12px, padding-right: 12px'> <p>The CD4+ count is lower than 200 or opportunistic infections develop in an HIV-infected person. An HIV-infected person has AIDS when he or she has fewer than 200 CD4 cells. These cells are an important part of the body’s immune system and help to fight off infection. The definition of AIDS also includes developing one or more of 26 health conditions. These include opportunistic infections like recurrent pneumonia and Kaposi sarcoma, a cancerlike disease affecting the skin, even if that person doesn't meet the CD4+ criteria. Many of these conditions don’t affect healthy people. But someone with AIDS has poorer defenses against infection because the immune system is weakened. </p> <p>Source: <a href='https://www.urmc.rochester.edu/encyclopedia/content.aspx?ContentTypeID=40&ContentID=HIVAIDSQuiz&CustomAnswers_HIVAIDSQuiz=q1a3_c,q2a4_c,q3a3_c,q4a1_c,q5a2_c,q6a3_c,q8a4_c,q9a4_c'>Open Source Link</a></p> </body> </html>";
    String q5 = "<!DOCTYPE html> <html> <head> <title></title> </head> <body style='padding-left: 12px, padding-right: 12px'> <p>The correct answer is A. Either antibodies against HIV or the virus particles themselves are present in the blood. When you have HIV in your body, your immune system makes antibodies (a kind of protein) to fight the infection. These antibodies can be measured by a blood test. It usually takes 2 to 8 weeks after infection before HIV antibodies can be detected. For some people, it may take longer, but most people will develop antibodies within the first 3 months after infection. Once the antibodies are detected, a person is considered HIV-positive. A person can also be diagnosed with HIV infection when a blood test detects the actual virus particles. </p> <p>Source: <a href='https://www.urmc.rochester.edu/encyclopedia/content.aspx?ContentTypeID=40&ContentID=HIVAIDSQuiz&CustomAnswers_HIVAIDSQuiz=q1a3_c,q2a4_c,q3a3_c,q4a1_c,q5a2_c,q6a3_c,q8a4_c,q9a4_c'>Open Source Link</a></p> </body> </html>";
    String q6 = "<!DOCTYPE html> <html> <head> <title></title> </head> <body style='padding-left: 12px, padding-right: 12px'> <p>Explanation: “the recurrent use of alcohol and/or drugs causes clinically significant impairment, including health problems, disability, and failure to meet major responsibilities at work, school, or home.” </p> <p>Source: DSM- 5 <a href='https://www.ruralhealthinfo.org/toolkits/substance-abuse/1/definition'>Open Source Link</a></p> </body> </html>";
    String q7 = "";
    String q8 = "<!DOCTYPE html> <html> <head> <title></title> </head> <body style='padding-left: 12px, padding-right: 12px'> <p>Explanation: Intravenous drug users (IDUs) are vulnerable to a wide range of potentially life-threatening infections, including rare infections such as wound botulism and cutaneous anthrax.</p> <p>All active and previous IDUs should be offered blood-borne viruses like HIV, Hepatitis B, Hepatitis C virus screening when in contact with healthcare services.</p> <p>In IDUs with skin and soft-tissue infections, thorough assessment of the site and severity is key Careful assessment for endocarditis, including echocardiography, is required in IDUs presenting with bloodstream infections, septic pulmonary emboli and infected deep venous thromboses.</p> <p>IDUs are at increased risk of pulmonary infection, and septic pulmonary emboli are commonly associated with right-sided endocarditis, infected deep venous thromboses and bloodstream infection</p> <p>Source: <a href='https://www.ncbi.nlm.nih.gov/pmc/articles/PMC4953807/'>Open Source Link</a></p> </body> </html>";
    String q9 = "<!DOCTYPE html> <html> <head> <title></title> </head> <body style='padding-left: 12px, padding-right: 12px'> <p>Mortality in injecting drug users (IDUs) is up to 22 times higher than for the age-adjusted population.</p> <p>Source: <a href='https://www.ncbi.nlm.nih.gov/pmc/articles/PMC4953807/'>Open Source Link</a></p> </body> </html>";
    String q10 = "<!DOCTYPE html> <html> <head> <title></title> </head> <body style='padding-left: 12px, padding-right: 12px'> <p>Correct value is 17%</p> <p>Which means that in a group of 6 injection drug abusers 1 might be HIV positive? This may thus increase your chances of  getting exposed to HIV if you are sharing needles in a  group.</p> </body> </html>";
    String q11 = "<!DOCTYPE html> <html> <head> <title></title> </head> <body style='padding-left: 12px, padding-right: 12px'> <p>Correct value is 53%</p> <p>Which means that in a group of 6 injection drug users 3 might be HCV positive. Sharing of needles in a group can thus increase your chance of getting exposed to HCV.</p> </body> </html>";
    String q12 = "<!DOCTYPE html> <html> <head> <title></title> </head> <body style='padding-left: 12px, padding-right: 12px'> <p>Source: <a href='https://www.hiv.gov/hiv-basics/hiv-prevention/reducing-risk-from-alcohol-and-drug-use/substance-use-and-hiv-risk'>Open Source Link</a></p> </body> </html>";
    String q13 = "<!DOCTYPE html> <html> <head> <title></title> </head> <body style='padding-left: 12px, padding-right: 12px'> <p>Explanation - SSPs are places where injection drug users can get new needles and works, along with other services such as help with stopping substance abuse; testing and, if needed, linkage to treatment for HIV, hepatitis B, and hepatitis C; and education on what to do for an overdose. SSPs have been demonstrated to be an effective component of a comprehensive approach to prevent HIV and viral hepatitis among people who inject drugs, while not increasing illegal drug use. </p> <p>Although the services they provide may vary, SSPs are community-based programs that provide access to sterile needles and syringes, facilitate safe disposal of used syringes, and provide and link to other important services and programs such as</p> <ul> <li>Referral to substance use disorder treatment programs.</li> <li>Screening, care, and treatment for viral hepatitis and HIV.</li> <li>Education about overdose prevention and safer injection practices.</li> <li>Vaccinations, including those for hepatitis A and hepatitis B.</li> <li>Screening for sexually transmitted diseases.</li> <li>Abscess and wound care.</li> <li>Naloxone distribution and education.</li> <li>Referral to social, mental health, and other medical services.</li> </ul> <p>Source: <a href='https://www.hiv.gov/hiv-basics/hiv-prevention/reducing-risk-from-alcohol-and-drug-use/substance-use-and-hiv-risk'>Open Source Link</a></p> </body> </html>";
    String q14 = "<!DOCTYPE html> <html> <head> <title></title> </head> <body style='padding-left: 12px, padding-right: 12px'> <p>Opioids, a class of drugs that reduce pain, include both prescription drugs and heroin. They are associated with HIV risk behaviors such as needle sharing when infected and risky sexual behaviors, and have been linked to outbreaks of HIV and viral hepatitis. People who are addicted to opioids are also at risk of turning to other ways to get the drug, including trading sex for drugs or money, which increases increases HIV risk and also hepatitis risk.</p> <p>Source: <a href='https://www.hiv.gov/hiv-basics/hiv-prevention/reducing-risk-from-alcohol-and-drug-use/substance-use-and-hiv-risk'>Open Source Link</a></p> </body> </html>";
    String q15 = "<!DOCTYPE html> <html> <head> <title></title> </head> <body style='padding-left: 12px, padding-right: 12px'> <p>Source: <a href='https://www.hiv.gov/hiv-basics/hiv-prevention/reducing-risk-from-alcohol-and-drug-use/substance-use-and-hiv-risk'>Open Source Link</a></p> </body> </html>";
    String q16 = "<!DOCTYPE html> <html> <head> <title></title> </head> <body style='padding-left: 12px, padding-right: 12px'> <p>Source: <a href='https://www.cdc.gov/ssp/syringe-services-programs-faq.html'>Open Source Link</a></p> </body> </html>";
    String q17 = "<!DOCTYPE html> <html> <head> <title></title> </head> <body style='padding-left: 12px, padding-right: 12px'> <p>The best way to lower your chances of getting HIV is to stop injecting drugs. You may need help to stop or cut down using drugs, but there are many resources available to help you</p> <p>If you keep injecting drugs, here are some ways to lower your risk for getting HIV and other infections:</p> <ul> <li>Use only new, sterile needles and works each time you inject. Many communities have needle exchange programs where you can get new needles and works, and some pharmacies may sell needles without a prescription.</li> <li>Never share needles or works.</li> <li>Clean used needles with bleach only when you can’t get new ones. Bleaching a needle may reduce the risk of HIV but doesn’t eliminate it.</li> <li>Use sterile water to fix drugs.</li> <li>Clean your skin with a new alcohol swab before you inject.</li> <li>Be careful not to get someone else’s blood on your hands or your needle or works.</li> <li>Dispose of needles safely after one use. Use a sharps container, or keep used needles away from other people.</li> <li>Get tested for HIV at least once a year</li> <li>Ask your doctor about taking daily medicine to prevent HIV called pre-exposure prophylaxis (PrEP).</li> <li>If you think you’ve been exposed to HIV within the last 3 days, ask a health care provider about post-exposure prophylaxis (PEP) right away. PEP can prevent HIV, but it must be started within 72 hours.</li> <li>Don’t have sex if you’re high. If you do have sex, make sure to protect yourself and your partner by using a condom the right way every time or by using other effective methods.</li> </ul> <img src='https://hivgov-prod-v3.s3.amazonaws.com/s3fs-public/three-steps-syringe.jpg?null' style='margin-top: 16px, display: inline;height: auto;max-width: 100%;'/> <p>Source: <a href='https://www.hiv.gov/hiv-basics/hiv-prevention/reducing-risk-from-alcohol-and-drug-use/substance-use-and-hiv-risk'>Open Source Link</a></p> </body> </html>";
    String q18 = "<!DOCTYPE html> <html> <head> <title></title> </head> <body style='padding-left: 12px, padding-right: 12px'> <p>Yes. When people who inject drugs use an SSP, they are more likely to enter treatment for substance use disorder and stop injecting than those who don’t use an SSP.1,2,3,4 New users of SSPs are five times as likely to enter drug treatment as those who don’t use the programs. People who inject drugs and who have used an SSP regularly are nearly three times as likely to report a reduction in injection frequency as those who have never used an SSP.</p> <p>Source: <a href='https://www.cdc.gov/ssp/syringe-services-programs-faq.html'>Open Source Link</a></p> </body> </html>";

    ArrayList<String> quesExpl = new ArrayList<>();

    WebView webView;

    public ExplanationFragment() {
        // Required empty public constructor
    }

    public ExplanationFragment(Question q) {
        this.q = q;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_explanation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mClose = view.findViewById(R.id.expl_close);
        webView = view.findViewById(R.id.expl_body);

        mClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getDialog() != null) {
                    getDialog().dismiss();
                }
            }
        });

        quesExpl.add(q1);
        quesExpl.add(q2);
        quesExpl.add(q3);
        quesExpl.add(q4);
        quesExpl.add(q5);
        quesExpl.add(q6);
        quesExpl.add(q7);
        quesExpl.add(q8);
        quesExpl.add(q9);
        quesExpl.add(q10);
        quesExpl.add(q11);
        quesExpl.add(q12);
        quesExpl.add(q13);
        quesExpl.add(q14);
        quesExpl.add(q15);
        quesExpl.add(q16);
        quesExpl.add(q17);
        quesExpl.add(q18);

        setExplanation();
    }

    private void setExplanation() {
        int quesNum = q.getQuesNumber();
        String expl = quesExpl.get(quesNum-1);

        //Log.d(TAG, "setExplanation: "+quesNum+" "+expl);

        String encodedHtml = Base64.encodeToString(expl.getBytes(),
                Base64.NO_PADDING);

        webView.loadData(encodedHtml, "text/html", "base64");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getDialog() != null) {
            Window window = getDialog().getWindow();
            if (window != null) {
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(window.getAttributes());
                //This makes the dialog take up the full width
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.MATCH_PARENT;
//                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                window.setAttributes(lp);
            }
        }
    }
}