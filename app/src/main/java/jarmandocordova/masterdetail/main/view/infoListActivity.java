package jarmandocordova.masterdetail.main.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import jarmandocordova.masterdetail.R;
import jarmandocordova.masterdetail.global.data.belatrixsf.Data;
import jarmandocordova.masterdetail.global.views.SimpleSectionedRVAdapter;
import jarmandocordova.masterdetail.main.data.Item;
import jarmandocordova.masterdetail.main.data.MainGateway;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * An activity representing a list of infos. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link infoDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class infoListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private MainGateway mainGateway = new MainGateway();
    private SimpleItemRecyclerViewAdapter mAdapter;

    @Override
    protected void onResume() {
        super.onResume();
        final View recyclerView = findViewById(R.id.info_list);
        assert recyclerView != null;

        mainGateway.getData()
                .map(new Func1<Data, Pair<List<Item>, Integer>>() {
                    @Override
                    public Pair<List<Item>, Integer> call(Data data) {
                        List<Item> list = new ArrayList<>();
                        //Info
                        list.add(new Item("Dream:", data.getDream(), data.getDream()));
                        list.add(new Item("My Why:", data.getMyWhy(), data.getMyWhy()));
                        list.add(new Item("Period:", data.getPeriod(), data.getPeriod()));
                        list.add(new Item("Roadmap:", data.getRoadmap(), data.getRoadmap()));
                        //Metrics

                        int i = 1;
                        for (Data.Metric m : data.getMetric()) {
                            list.add(new Item("M"+i++, m.getType(), m.toString()));
                        }

                        i = 1;
                        //Volume
                        for (Data.Volume v : data.getVolume()) {
                            list.add(new Item("V"+i++, v.getType(), v.toString()));
                        }

                        return new Pair<>(list, data.getMetric().size());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Pair<List<Item>, Integer>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("myError", e.getMessage(), e);
                    }

                    @Override
                    public void onNext(final Pair<List<Item>, Integer> pair) {
                        Log.d("mySuccess", pair.first.toString());
                        Log.d("mySuccess", pair.second.toString());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                boolean first4NoData = true;
                                for (int i = 0; i < 4; i++ ){
                                    Item it = pair.first.get(i);
                                    if(!it.noContentNorDetail()){
                                        first4NoData = false;
                                        break;
                                    }
                                }
                                if(pair.first.size() < 5 && first4NoData){
                                    Toast.makeText(infoListActivity.this, "Couldn't connect to server, nothing on cache. =(", Toast.LENGTH_SHORT).show();
                                }
                                setupRecyclerView((RecyclerView) recyclerView, pair.first, pair.second);
                            }
                        });
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Delete Cache?", Snackbar.LENGTH_LONG)
                        .setAction("Delete", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mainGateway.deleteCache();
                                Toast.makeText(infoListActivity.this, "Cache deleted", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });


        if (findViewById(R.id.info_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView, List<Item> items, int metricsCount) {
        // recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(DummyContent.ITEMS));

        mAdapter =
                new SimpleItemRecyclerViewAdapter(items);

        List<SimpleSectionedRVAdapter.Section> sections = new ArrayList<>();

        //Sections
        sections.add(new SimpleSectionedRVAdapter.Section(0, "Information"));
        sections.add(new SimpleSectionedRVAdapter.Section(4, "Metrics"));
        sections.add(new SimpleSectionedRVAdapter.Section(4 + metricsCount, "Volumes"));

        //Add your adapter to the sectionAdapter
        SimpleSectionedRVAdapter.Section[] dummy = new SimpleSectionedRVAdapter.Section[sections.size()];
        SimpleSectionedRVAdapter mSectionedAdapter = new
                SimpleSectionedRVAdapter(this, R.layout.section, R.id.section_text, mAdapter);
        mSectionedAdapter.setSections(sections.toArray(dummy));

        //Apply this adapter to the RecyclerView
        recyclerView.setAdapter(mSectionedAdapter);
    }


    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private List<Item> mValues;

        public SimpleItemRecyclerViewAdapter(List<Item> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.info_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            if (position >= mValues.size())
                return;
            holder.mItem = mValues.get(position);
            holder.mIdView.setText(mValues.get(position).id);
            holder.mContentView.setText(mValues.get(position).content);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(infoDetailFragment.ARG_ITEM_ID, holder.mItem.id);
                        arguments.putString(infoDetailFragment.ARG_ITEM_CONTENT, holder.mItem.content);
                        arguments.putString(infoDetailFragment.ARG_ITEM_DETAIL, holder.mItem.details);
                        infoDetailFragment fragment = new infoDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.info_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, infoDetailActivity.class);
                        intent.putExtra(infoDetailFragment.ARG_ITEM_ID, holder.mItem.id);
                        intent.putExtra(infoDetailFragment.ARG_ITEM_CONTENT, holder.mItem.content);
                        intent.putExtra(infoDetailFragment.ARG_ITEM_DETAIL, holder.mItem.details);

                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public Item mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}
