import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.amr.jokeobserver.JokeObserver;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.AsyncTaskEndpoints;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.ResponseAsync;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements ResponseAsync {
    InterstitialAd mInterstitialAd;
    String jokeStr;
    AdRequest adRequest1;
    private ProgressDialog dialog1;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        Button display = (Button) view.findViewById(R.id.display);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catchJoke();
            }
        });
        return view;
    }
/*
        AdView mAdView = (AdView) view.findViewById(R.id.adView);
        AdRequest adReq = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adReq);

        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        adReq1 = new AdRequest.Builder()
                .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .build();
       /* mInterstitialAd.loadAd(adRequest1);
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                Intent intent = JokeObserver.makeIntent(getActivity(), joke);
                Toast.makeText(getActivity(), "joke"+joke, Toast.LENGTH_SHORT).show();
                // startActivity(intent);
            }
        });

        Button display = (Button) view.findViewById(R.id.display);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInterstitialAd.loadAd(adReq1);
                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        Intent intent = JokeObserver.makeIntent(getActivity(), jokeStr);
                        Toast.makeText(getActivity(), "joke" + jokeStr, Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                });

                mInterstitialAd.show();
                catchJoke();

            }
        });

        return view;

    }*/

    private void catchJoke() {
        dialog1 = new ProgressDialog(getContext());
        dialog1.setMessage(getResources().getString(R.string.progress_text));
        dialog1.show();
        AsyncTaskEndpoints asyncTask = new AsyncTaskEndpoints();
        asyncTask.delegate = this;
        asyncTask.execute();
    }

    @Override
    public void processFinish(String output) {
        jokeStr = output;
        dialog1.dismiss();
        Intent intent = JokeObserver.makeIntent(getActivity(), jokeStr);
        startActivity(intent);

    }
}