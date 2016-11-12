package net.sparkeek.instatest.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.meetic.marypopup.MaryPopup;

import net.sparkeek.instatest.Utils.ToolBox;
import net.sparkeek.instatest.interfaces.IFragmentToActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import net.sparkeek.instatest.R;
import tyrantgit.explosionfield.ExplosionField;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match


    String msg_callback = "Bad Credentials";

    private Unbinder unBinder;
    private ExplosionField mExplosionField;
    private IFragmentToActivity mCallback;
    private MaryPopup mMarypopup;
    private View rootView;
    private SharedPreferences mSharedPref;
    @BindView(R.id.btn_connexion)
    Button mBtnLogin;
    WebView mLoginWebview;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mExplosionField = ExplosionField.attach2Window(getActivity());
        mSharedPref = ToolBox.getSharedPref(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        unBinder = ButterKnife.bind(this, rootView);
        return rootView;
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
        mCallback = null;
        unBinder.unbind();
    }

    //OnClick Listener
    @OnClick(R.id.btn_connexion)
    public void onLogin(View view) {

        mLoginWebview = new WebView(getContext());
        mMarypopup = MaryPopup.with(getActivity())
                .cancellable(true)
                .blackOverlayColor(Color.parseColor("#DD444444"))
                .backgroundColor(Color.parseColor("#EFF4F5"))
                .content(mLoginWebview)
                .center(false)
                .draggable(false)
                .scaleDownDragging(false)
                .from(rootView);
        mMarypopup.show();

        if (mMarypopup.isOpened()) {
            mLoginWebview.setBackgroundColor(Color.TRANSPARENT);
            mLoginWebview.getSettings().setJavaScriptEnabled(true);
            mLoginWebview.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    if (url.startsWith("http://www.sparkeek.net")) {
                        String urls[] = url.split("=");
                        msg_callback = "Login Successful";
                        mSharedPref.edit().putString("insta_key", urls[1]).commit();
                        mMarypopup.close(true);
                        mCallback.setFragment(2);
                        return true;
                    }
                    return false;
                }
            });
            mLoginWebview.loadUrl(getString(R.string.url_instagram));
        }
    }



}
