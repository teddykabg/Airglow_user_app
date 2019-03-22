package me.tremor.Airglow_user.UI;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bottlerocketstudios.vault.SharedPreferenceVault;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import me.tremor.Airglow_user.R;
import me.tremor.Airglow_user.models.Position;
import me.tremor.Airglow_user.models.ShortEvent;
import me.tremor.Airglow_user.service.RetrofitClient;
import me.tremor.Airglow_user.vault.VaultLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EventDetailsFragment extends Fragment implements OnMapReadyCallback,MediaPlayer.OnBufferingUpdateListener,MediaPlayer.OnCompletionListener {
    int eventId;
    ShortEvent event;
    SharedPreferenceVault mShared;
    TextView mTitle;
    TextView mDisco;
    TextView mDescription;
    Button mButton;
    GoogleMap mMap;
    private SeekBar mSeekbar;
    TextView mTimer;
    ImageButton mPlayPause;
    private MediaPlayer mPlayer;
    private int mediaFileLength;
    private int realTimeLength;
    final Handler mHandler = new Handler();


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.event_details_fragment, container, false);
        eventId= getActivity().getIntent().getExtras().getInt("id");
        event=new ShortEvent();
        mShared = VaultLocator.getAutomaticallyKeyedVault();
        mTitle = view.findViewById(R.id.title_details);
        mDisco = view.findViewById(R.id.disco_details);
        mDescription = view.findViewById(R.id.event_description);
        mButton = view.findViewById(R.id.purchase1_button);
        mSeekbar= view.findViewById(R.id.seekBar);
        mSeekbar.setMax(99);
        mTimer = view.findViewById(R.id.text_timer);
        mPlayPause = view.findViewById(R.id.imageButton);

        mPlayer = MediaPlayer.create(getActivity(),R.raw.ok);
        //mPlayer = new MediaPlayer();
        mPlayer.setOnBufferingUpdateListener(this);
        mPlayer.setOnCompletionListener(this);

        mSeekbar.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(mPlayer.isPlaying()){
                    SeekBar seekBar = (SeekBar)view;
                    int playPosition = (mediaFileLength/100)*seekBar.getProgress();
                    mPlayer.seekTo(playPosition);
                }
                return false;
            }
        });

        mPlayPause.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ProgressDialog mDialog = new ProgressDialog(view.getContext());
                @SuppressLint("StaticFieldLeak") AsyncTask<String,String,String> mp3Player= new AsyncTask<String, String, String>() {
                    @Override
                    protected void onPreExecute() {
                        mDialog.setMessage("Please wait");
                        mDialog.show();
                    }

                    @Override
                    protected String doInBackground(String... strings) {
                       try {
                           mPlayer.prepare();
                           mPlayer.setDataSource(strings[0]);

                       }catch (Exception e){

                       }
                       return " ";
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        mediaFileLength = mPlayer.getDuration();
                        realTimeLength =mediaFileLength;
                        if(!mPlayer.isPlaying()){
                            mPlayer.start();
                            mPlayPause.setImageResource(R.drawable.ic_pause);
                        }
                        else{
                            mPlayer.pause();
                            mPlayPause.setImageResource(R.drawable.ic_play);
                        }
                        //updateSeekBar();
                        mDialog.dismiss();

                    }
                };
                mp3Player.execute("https://p.scdn.co/mp3-preview/3eb16018c2a700240e9dfb8817b6f2d041f15eb1?cid=774b29d4f13844c495f206cafdad9c86");

           mediaFileLength = mPlayer.getDuration();
                realTimeLength =mediaFileLength;
               if(!mPlayer.isPlaying()){
                    mPlayer.start();
                    mPlayPause.setImageResource(R.drawable.ic_pause);
                }else{
                    mPlayer.pause();
                    mPlayPause.setImageResource(R.drawable.ic_play);
                }updateSeekBar();

            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        SetUi mSet = new SetUi(mTitle,mDisco,mDescription,eventId);
        mSet.execute();

          mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new ModalityFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_1, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
               /* Fragment fragment = new RegisterFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/

        return view;
    }


       private void updateSeekBar() {
            mSeekbar.setProgress((int)(((float)mPlayer.getCurrentPosition()/mediaFileLength)*100));
            if(mPlayer.isPlaying()){
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        updateSeekBar();
                        realTimeLength-=1000;
                        mTimer.setText(String.format("%d:%d", TimeUnit.MILLISECONDS.toMinutes(realTimeLength),
                                TimeUnit.MILLISECONDS.toSeconds(realTimeLength)-TimeUnit.MILLISECONDS.toSeconds(TimeUnit.MILLISECONDS.toMinutes(realTimeLength))));
                    }

                };
                mHandler.postDelayed(updater,1000);
            }
        }

        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            SetPos mPos = new SetPos(mMap,eventId);
            mPos.execute();

        }

        @Override
        public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
            mSeekbar.setSecondaryProgress(i);
        }

        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            mPlayPause.setImageResource(R.drawable.ic_play);
        }
    }
    class SetPos extends AsyncTask{
        GoogleMap mMap;
        int eventId;
        SharedPreferenceVault mShared =VaultLocator.getAutomaticallyKeyedVault();

        public SetPos(GoogleMap mMap,int eventId) {
            this.mMap = mMap;
            this.eventId=eventId;
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            RetrofitClient.getInsance().getApi().getEventInfoss(mShared.getString("token",null),Integer.toString(eventId))
                    .enqueue(new Callback<ShortEvent>() {
                        @Override
                        public void onResponse(Call<ShortEvent> call, Response<ShortEvent> response) {
                            Position mPos;
                            if(response.body() != null) {
                                mPos = response.body().getDisco().getPosition();
                                LatLng pos = new LatLng(mPos.getLat(), mPos.getLon());
                                mMap.addMarker(new MarkerOptions().position(pos).title("here"));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos,12));
                            }

                        }

                        @Override
                        public void onFailure(Call<ShortEvent> call, Throwable t) {
                            Log.d("NO","Hola"+t.getMessage());
                        }
                    });
            return null;
        }
    }
    class SetUi extends AsyncTask {
        TextView mTitle;
        TextView mDisco;
        TextView mDescription;
        int eventId;
        SharedPreferenceVault mShared = VaultLocator.getAutomaticallyKeyedVault();


        public SetUi(TextView mTitle, TextView mDisco, TextView mDescription, int eventId) {
            this.mTitle = mTitle;
            this.mDisco = mDisco;
            this.mDescription = mDescription;
            this.eventId = eventId;

        }


        @Override
        protected Object doInBackground(Object[] objects) {
            RetrofitClient.getInsance().getApi().getEventInfoss(mShared.getString("token", null), Integer.toString(eventId))
                    .enqueue(new Callback<ShortEvent>() {
                        @Override
                        public void onResponse(Call<ShortEvent> call, Response<ShortEvent> response) {
                            if (response.body() != null) {
                                mTitle.setText(response.body().getTitle());
                                mDisco.setText(response.body().getDisco().getName());
                                mDescription.setText(response.body().getDescription());
                            }
                        }

                        @Override
                        public void onFailure(Call<ShortEvent> call, Throwable t) {
                            Log.d("NO", "Hola" + t.getMessage());
                        }
                    });
            return null;
        }
    }


