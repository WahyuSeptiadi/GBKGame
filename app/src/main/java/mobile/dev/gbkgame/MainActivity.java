package mobile.dev.gbkgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements FragmentGame.FragmentGameListener{

    private FragmentGame fragmentGame;
    private FragmentHistory fragmentHistory;
    private TextView notifHuman, codeart;
    private Button btnHistory, btnBalik;
    FrameLayout c1, c2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentGame = new FragmentGame();
        fragmentHistory = new FragmentHistory();
        notifHuman = findViewById(R.id.notifHuman);
        btnHistory = findViewById(R.id.btnHistory);
        btnBalik = findViewById(R.id.btnBalik);
        c1 = findViewById(R.id.contianer1);
        c2 = findViewById(R.id.contianer2);
        codeart = findViewById(R.id.codeart);

        //event click btnHistory -> fragment history
        btnHistory.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contianer1, fragmentGame)
                        .replace(R.id.contianer2, fragmentHistory)
                        .commit();
                c1.setVisibility(View.GONE);
                c2.setVisibility(View.VISIBLE);
                btnBalik.setVisibility(View.VISIBLE);
                codeart.setVisibility(View.INVISIBLE);
                btnHistory.setVisibility(View.INVISIBLE);
                notifHuman.setText("   Rock Paper Stone");
            }
        });

        btnBalik.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contianer1, fragmentGame)
                        .replace(R.id.contianer2, fragmentHistory)
                        .commit();
                c2.setVisibility(View.INVISIBLE);
                c1.setVisibility(View.VISIBLE);
                btnBalik.setVisibility(View.INVISIBLE);
                codeart.setVisibility(View.VISIBLE);
                btnHistory.setVisibility(View.VISIBLE);
                notifHuman.setText("   Rock Paper Stone");
            }
        });
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contianer1, fragmentGame)
                .replace(R.id.contianer2, fragmentHistory)
                .commit();
        c2.setVisibility(View.INVISIBLE);
        btnBalik.setVisibility(View.INVISIBLE);
        codeart.setVisibility(View.VISIBLE);
        btnHistory.setVisibility(View.VISIBLE);
    }

    @Override
    public void onInputFragmentGameSent(CharSequence input1, CharSequence input2) {
        fragmentHistory.updateEditext(input1, input2);
    }
}

















//    @Override
//    public void onInputFragmentHistorySent(CharSequence input1, CharSequence input2) {
//        fragmentHistory.updateEditext(input1, input2);
//    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        //DETERMINE WHO STARTED THIS ACTIVITY
////        String sender = this.getIntent().getExtras().getString("SENDER_KEY");
//
//        //IF ITS THE FRAGMENT THEN RECEIVE DATA
////        if(sender != null)
////        {
//            this.receiveData();
////        }
//    }

//    private void openFragment()
//    {
//        //PASS OVER THE BUNDLE TO OUR FRAGMENT
//        FragmentGame myFragment = new FragmentGame();
//        //THEN NOW SHOW OUR FRAGMENT
//        getSupportFragmentManager().beginTransaction().replace(R.id.contianer1, myFragment).commit();
//    }

//    private void receiveData()
//    {
//        //RECEIVE DATA VIA INTENT
//        Intent i = getIntent();
//        String status = i.getStringExtra("STATUS_KEY");
//
//        //SET DATA TO TEXTVIEWS
//        notifHuman.setText(status);
//    }





//    //buatLisview
//    private void setButtons() {
//        scCpu = findViewById(R.id.scCpu);
//        scHuman = findViewById(R.id.scHuman);
//    }
//
//    //tambah changeItem untuk ngubah textView ketika di click
//    public void changeItem(int position, String text) {
//        mExampleList.get(position).changeText1(text); // tambah methodnya di exampleitem
//        mAdapter.notifyItemChanged(position);
//    }
//
//    public void createExampleList() {
//        mExampleList = new ArrayList<>();
//        mExampleList.add(new historyItem("1", "2"));
//        mExampleList.add(new historyItem("3", "0"));
//        mExampleList.add(new historyItem("1", "2"));
//        mExampleList.add(new historyItem("1", "2"));
//        mExampleList.add(new historyItem("3", "0"));
//    }
//
//    public void buildRecyclerView() {
//        mRecyclerView = findViewById(R.id.recyclerView);
////        mRecyclerView.setHasFixedSize(true);
////        mLayoutManager = new LinearLayoutManager(this);
//        mAdapter = new AdapterList(mExampleList);
//
////        mRecyclerView.setLayoutManager(mLayoutManager);
////        mRecyclerView.setAdapter(mAdapter);
//
//        //tambah perubahan ketika item diclick
////        mAdapter.setOnItemClickListener(new AdapterList.OnItemClickListener() {
////            @Override
////            public void onItemClick(int position) {
////                changeItem(position, "Clicked");
////            }
////        });
//    }