
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.amr.jokeobserver.JokeObserver;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.AsyncTaskEndpoints;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.ResponseAsync;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements ResponseAsync {
    private ProgressDialog dialog1;
    InterstitialAd mInterstitialAd;
    String jokeStr;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    AdRequest adReq1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

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
        mInterstitialAd.loadAd(adReq1);
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                Intent intent = JokeObserver.makeIntent(getActivity(), jokeStr);
                Toast.makeText(getActivity(), "joke" + jokeStr, Toast.LENGTH_SHORT).show();
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

    }


    private void catchJoke() {
        Log.e("CatchString", "Catch new Joke");
        AsyncTaskEndpoints asyncTask = new AsyncTaskEndpoints();
        asyncTask.delegate = this;
        asyncTask.execute();
    }

    @Override
    public void processFinish(String output) {
        jokeStr = output;

    }
      /*  AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);*/


}
