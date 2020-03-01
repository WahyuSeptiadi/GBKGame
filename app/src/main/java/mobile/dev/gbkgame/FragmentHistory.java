package mobile.dev.gbkgame;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

public class FragmentHistory extends Fragment {
    private TextView scH1, scH2, scH3, scH4, scH5;
    private TextView scC1, scC2, scC3, scC4, scC5;

    private Button btnreset, btnsave;
    private String scoreDef;
    private String c1,c2,c3,c4,c5;

    private String h1,h2,h3,h4,h5;

    private final String SCORE_KEY = "score";
    private final String C1_KEY = "C1";
    private final String C2_KEY = "C2";
    private final String C3_KEY = "C3";
    private final String C4_KEY = "C4";
    private final String C5_KEY = "C5";

    private final String H1_KEY = "H1";
    private final String H2_KEY = "H2";
    private final String H3_KEY = "H3";
    private final String H4_KEY = "H4";
    private final String H5_KEY = "H5";

    private SharedPreferences mPreferences;

    // Name of shared preferences file
    private static final String mSharedPrefFile = "com.example.android.gbkgame";

    private TextView codeart;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_history, container, false);

        scH1 = v.findViewById(R.id.scoreHuman1);
        scC1 = v.findViewById(R.id.scoreCpu1);
        scH2 = v.findViewById(R.id.scoreHuman2);
        scC2 = v.findViewById(R.id.scoreCpu2);
        scH3 = v.findViewById(R.id.scoreHuman3);
        scC3 = v.findViewById(R.id.scoreCpu3);
        scH4 = v.findViewById(R.id.scoreHuman4);
        scC4 = v.findViewById(R.id.scoreCpu4);
        scH5 = v.findViewById(R.id.scoreHuman5);
        scC5 = v.findViewById(R.id.scoreCpu5);
        btnreset = v.findViewById(R.id.reset);
        btnsave = v.findViewById(R.id.save);
        codeart = v.findViewById(R.id.codeart);

        upDataHistory();
        loadData();

        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset(v);
            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                loadData();
            }
        });
        return v;
    }

    public void updateEditext(CharSequence input1, CharSequence input2){
        if(scC1.getText().toString() == "0" && scH1.getText().toString() == "0") {
            scC1.setText(input1);
            scH1.setText(input2);
        }else if(scC2.getText().toString() == "0" && scH2.getText().toString() == "0"){
            scC2.setText(input1);
            scH2.setText(input2);
        }else if(scC3.getText().toString() == "0" && scH3.getText().toString() == "0"){
            scC3.setText(input1);
            scH3.setText(input2);
        }else if(scC4.getText().toString() == "0" && scH4.getText().toString() == "0"){
            scC4.setText(input1);
            scH4.setText(input2);
        }else if(scC5.getText().toString() == "0" && scH5.getText().toString() == "0"){
            scC5.setText(input1);
            scH5.setText(input2);
        }else{
            scC5.setText(input1);
            scH5.setText(input2);
        }
    }
    public void loadData() {
        mPreferences = getActivity().getSharedPreferences(mSharedPrefFile, MODE_PRIVATE);
        SharedPreferences.Editor editor = mPreferences.edit();
        c1 = mPreferences.getString(C1_KEY, "");
        c2 = mPreferences.getString(C2_KEY, "");
        c3 = mPreferences.getString(C3_KEY, "");
        c4 = mPreferences.getString(C4_KEY, "");
        c5 = mPreferences.getString(C5_KEY, "");

        h1 = mPreferences.getString(H1_KEY, "");
        h2 = mPreferences.getString(H2_KEY, "");
        h3 = mPreferences.getString(H3_KEY, "");
        h4 = mPreferences.getString(H4_KEY, "");
        h5 = mPreferences.getString(H5_KEY, "");

        editor.apply();
        editor.commit();
    }
    public void saveData() {
        mPreferences = getActivity().getSharedPreferences(mSharedPrefFile, MODE_PRIVATE);
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putString(C1_KEY, scC1.getText().toString());
        preferencesEditor.putString(C2_KEY, scC2.getText().toString());
        preferencesEditor.putString(C3_KEY, scC3.getText().toString());
        preferencesEditor.putString(C4_KEY, scC4.getText().toString());
        preferencesEditor.putString(C5_KEY, scC5.getText().toString());
        preferencesEditor.putString(H1_KEY, scH1.getText().toString());
        preferencesEditor.putString(H2_KEY, scH2.getText().toString());
        preferencesEditor.putString(H3_KEY, scH3.getText().toString());
        preferencesEditor.putString(H4_KEY, scH4.getText().toString());
        preferencesEditor.putString(H5_KEY, scH5.getText().toString());

        preferencesEditor.apply();
        showSnackBar("Data saved");
    }
    public void upDataHistory(){
        if(scH1.getText().toString() == "0" && scC1.getText().toString() == "0"){
            try {
                Bundle bundle = this.getArguments();

                if(bundle != null){
                    int scAkhirHuman = bundle.getInt("ScoreAkhirHuman");
                    int scAkhirCpu = bundle.getInt("ScoreAkhirCpu");
                    scC1.setText(scAkhirCpu);
                    scH1.setText(scAkhirHuman);
                }

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }else if(scH2.getText().toString() == "0" && scC2.getText().toString() == "0"){
            try {
                Bundle bundle = this.getArguments();

                if(bundle != null){
                    int scAkhirHuman = bundle.getInt("ScoreAkhirHuman");
                    int scAkhirCpu = bundle.getInt("ScoreAkhirCpu");
                    scC2.setText(scAkhirCpu);
                    scH2.setText(scAkhirHuman);
                }

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        else if(scH3.getText().toString() == "0" && scC3.getText().toString() == "0"){
            try {
                Bundle bundle = this.getArguments();

                if(bundle != null){
                    int scAkhirHuman = bundle.getInt("ScoreAkhirHuman");
                    int scAkhirCpu = bundle.getInt("ScoreAkhirCpu");
                    scC3.setText(scAkhirCpu);
                    scH3.setText(scAkhirHuman);
                }

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }else if(scH4.getText().toString() == "0" && scC4.getText().toString() == "0"){
            try {
                Bundle bundle = this.getArguments();

                if(bundle != null){
                    int scAkhirHuman = bundle.getInt("ScoreAkhirHuman");
                    int scAkhirCpu = bundle.getInt("ScoreAkhirCpu");
                    scC4.setText(scAkhirCpu);
                    scH4.setText(scAkhirHuman);
                }

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }else if(scH5.getText().toString() == "0" && scC5.getText().toString() == "0"){
            try {
                Bundle bundle = this.getArguments();

                if(bundle != null){
                    int scAkhirHuman = bundle.getInt("ScoreAkhirHuman");
                    int scAkhirCpu = bundle.getInt("ScoreAkhirCpu");
                    scC5.setText(scAkhirCpu);
                    scH5.setText(scAkhirHuman);
                }

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }else {
            try {
                Bundle bundle = this.getArguments();

                if(bundle != null){
                    int scAkhirHuman = bundle.getInt("ScoreAkhirHuman");
                    int scAkhirCpu = bundle.getInt("ScoreAkhirCpu");
                    scC5.setText(scAkhirCpu);
                    scH5.setText(scAkhirHuman);
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public void showSnackBar(String massage){
        Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content), massage, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }

    public void reset(View view) {
        // Reset list
        mPreferences = getActivity().getSharedPreferences(mSharedPrefFile, MODE_PRIVATE);
        scC1.setText("0");
        scC2.setText("0");
        scC3.setText("0");
        scC4.setText("0");
        scC5.setText("0");

        scH1.setText("0");
        scH2.setText("0");
        scH3.setText("0");
        scH4.setText("0");
        scH5.setText("0");

        // Clear preferences
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.clear();
        preferencesEditor.apply();
        showSnackBar("History has beed reset");
    }
}






















//        mPreferences = getActivity().getSharedPreferences(mSharedPrefFile, MODE_PRIVATE);

//restore
//        c1 = mPreferences.getInt(C1_KEY, 0);
//        c2 = mPreferences.getInt(C2_KEY, 0);
//        c3 = mPreferences.getInt(C3_KEY, 0);
//        c4 = mPreferences.getInt(C4_KEY, 0);
//        c5 = mPreferences.getInt(C5_KEY, 0);
//        scC1.setText(String.valueOf(c1));
//        scC2.setText(String.valueOf(c2));
//        scC3.setText(String.valueOf(c3));
//        scC4.setText(String.valueOf(c4));
//        scC5.setText(String.valueOf(c5));
//
//        h1 = mPreferences.getInt(H1_KEY, 0);
//        h2 = mPreferences.getInt(H2_KEY, 0);
//        h3 = mPreferences.getInt(H3_KEY, 0);
//        h4 = mPreferences.getInt(H4_KEY, 0);
//        h5 = mPreferences.getInt(H5_KEY, 0);
//        scH1.setText(String.valueOf(h1));
//        scH2.setText(String.valueOf(h2));
//        scH3.setText(String.valueOf(h3));
//        scH4.setText(String.valueOf(h4));
//        scH5.setText(String.valueOf(h5));

//        editor.putString(C1_KEY, scC1.getText().toString());
//        editor.putString(C2_KEY, scC2.getText().toString());
//        editor.putString(C3_KEY, scC3.getText().toString());
//        editor.putString(C4_KEY, scC4.getText().toString());
//        editor.putString(C5_KEY, scC5.getText().toString());
//
//        editor.putString(H1_KEY, scH1.getText().toString());
//        editor.putString(H2_KEY, scH2.getText().toString());
//        editor.putString(H3_KEY, scH3.getText().toString());
//        editor.putString(H4_KEY, scH4.getText().toString());
//        editor.putString(H5_KEY, scH5.getText().toString());

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        mPreferences = getActivity().getSharedPreferences(mSharedPrefFile, MODE_PRIVATE);
//        if (context instanceof FragmentGame.FragmentGameListener) {
//            Listener = (FragmentHistoryListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        Listener = null;
//    }

//    @Override
//    public void onPause() {
//        super.onPause();
//        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
//        preferencesEditor.putInt(C1_KEY, c1);
//        preferencesEditor.putInt(H1_KEY, h1);
//        preferencesEditor.putInt(C2_KEY, c2);
//        preferencesEditor.putInt(H2_KEY, h2);
//        preferencesEditor.putInt(C3_KEY, c3);
//        preferencesEditor.putInt(H3_KEY, h3);
//        preferencesEditor.putInt(C4_KEY, c4);
//        preferencesEditor.putInt(H4_KEY, h4);
//        preferencesEditor.putInt(C5_KEY, c5);
//        preferencesEditor.putInt(H5_KEY, h5);
//        preferencesEditor.apply();
//        preferencesEditor.commit();
//    }