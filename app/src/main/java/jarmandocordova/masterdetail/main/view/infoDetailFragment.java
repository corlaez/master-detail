package jarmandocordova.masterdetail.main.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import jarmandocordova.masterdetail.R;
import jarmandocordova.masterdetail.main.data.Item;

/**
 * A fragment representing a single info detail screen.
 * This fragment is either contained in a {@link infoListActivity}
 * in two-pane mode (on tablets) or a {@link infoDetailActivity}
 * on handsets.
 */
public class infoDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    public static final String ARG_ITEM_CONTENT = "item_content";
    public static final String ARG_ITEM_DETAIL = "item_detail";

    /**
     * The dummy content this fragment is presenting.
     */
    private Item mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public infoDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(infoDetailFragment.ARG_ITEM_ID)) {
            String id = getArguments().getString(infoDetailFragment.ARG_ITEM_ID);
            String cont = getArguments().getString(infoDetailFragment.ARG_ITEM_CONTENT);
            String det = getArguments().getString(infoDetailFragment.ARG_ITEM_DETAIL);
            mItem = new Item(id, cont, det);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.info_detail, container, false);

        Activity activity = this.getActivity();
        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout)
                activity.findViewById(R.id.toolbar_layout);

        boolean tituloSeteado = false;
        if (appBarLayout != null) {
            appBarLayout.setTitle(mItem.id);
            tituloSeteado = true;
        }

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            String m = "";
            if (!tituloSeteado) {
                m = mItem.id + "\n";
            }
            if (mItem.content != null) {
                if (mItem.content.equals(mItem.details))
                    m += mItem.content;
                else {
                    m += mItem.content + "\n\n" + mItem.details;
                }
            }
            ((TextView) rootView.findViewById(R.id.info_detail))
                    .setText(m);
        }
        return rootView;
    }

    public String getItemText(){
        return mItem.toString();
    }
}
