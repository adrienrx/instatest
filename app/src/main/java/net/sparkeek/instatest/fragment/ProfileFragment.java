package net.sparkeek.instatest.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.sparkeek.instatest.Utils.ToolBox;
import net.sparkeek.instatest.adapter.InstAdapter;
import net.sparkeek.instatest.interfaces.IFragmentToActivity;
import net.sparkeek.instatest.models.recent.Datum;
import net.sparkeek.instatest.models.recent.InstaUserRecent;
import net.sparkeek.instatest.models.self.InstaUserSelf;
import net.sparkeek.instatest.rest.InstaSelfApiEndpointInterface;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import net.sparkeek.instatest.R;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Unbinder unBinder;
    private View rootview;
    private IFragmentToActivity mCallback;
    private Subscription mSubscription;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Datum> mTimelineData;

    @BindView(R.id.btn_test)
    Button mBtnTest;
    @BindView(R.id.tv_profil_total_publication)
    TextView mTvTotalMedia;
    @BindView(R.id.tv_profil_total_followers)
    TextView mTvTotalFollowers;
    @BindView(R.id.tv_profil_total_follows)
    TextView mTvTotalFollows;
    @BindView(R.id.imgview_profil)
    ImageView mImgviewProfil;
    @BindView(R.id.timeline_profil)
    RecyclerView mRecyclerTimeline;

    public ProfileFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_profile, container, false);
        unBinder = ButterKnife.bind(this, rootview);
        mRecyclerTimeline.setHasFixedSize(true);

        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.instagram.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .build();

        InstaSelfApiEndpointInterface apiService = retrofit.create(InstaSelfApiEndpointInterface.class);
        Observable<InstaUserSelf> call = apiService.getUser(ToolBox.getUserToken(getContext()));

        mSubscription = call
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<InstaUserSelf>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            HttpException response = (HttpException) e;
                            int code = response.code();
                        }
                    }

                    @Override
                    public void onNext(InstaUserSelf instaUserSelf) {
                        mTvTotalFollowers.setText("Followers : \n" + instaUserSelf.getData().getCounts().getFollowedBy());
                        mTvTotalFollows.setText("Following : \n" + instaUserSelf.getData().getCounts().getFollows());
                        mTvTotalMedia.setText("Publish : \n" + instaUserSelf.getData().getCounts().getMedia());
                        Picasso.with(getContext()).load(instaUserSelf.getData().getProfilePicture()).into(mImgviewProfil);
                    }
                });

        Observable<InstaUserRecent> call_recent = apiService.getRecentPost(ToolBox.getUserToken(getContext()));
        mSubscription = call_recent
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<InstaUserRecent>() {
                    @Override
                    public void onCompleted() {
                        mLayoutManager = new LinearLayoutManager(getContext());
                        mRecyclerTimeline.setLayoutManager(mLayoutManager);
                        mAdapter = new InstAdapter(mTimelineData);
                        mRecyclerTimeline.setAdapter(mAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(InstaUserRecent instaUserRecent) {
                        mTimelineData = (ArrayList<Datum>) instaUserRecent.getData();
                    }
                });

        return rootview;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (IFragmentToActivity) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement IFragmentToActivity");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mSubscription.unsubscribe();
        mCallback = null;
        unBinder.unbind();
    }


    //Onclick
    @OnClick(R.id.btn_test)
    public void onBtnTestClick(View view) {
        ToolBox.getSharedPref(getContext()).edit().remove("insta_key").commit();
        getActivity().finish();
    }


}
