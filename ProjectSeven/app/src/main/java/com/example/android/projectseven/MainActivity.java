package com.example.android.projectseven;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {

    public static final String LOG_TAG = MainActivity.class.getName();
    private static final int LOADER_ID = 1;
    private static String searchKeyword = "";
    private boolean FIRST_ACTIVITY = true;
    private static final String BOOK_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    BookAdapter mAdapter;
    TextView mEmptyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView listView = (ListView) findViewById(R.id.list);
        Button searchButton = (Button) findViewById(R.id.search_button);
        mEmptyTextView = (TextView) findViewById(R.id.empty_view);
        listView.setEmptyView(mEmptyTextView);
        mAdapter = new BookAdapter(this, new ArrayList<Book>());
        listView.setAdapter(mAdapter);

        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
        //Check connection status and set the text of empty view accordingly
        if (hasConnectivity()) {
            Log.i(LOG_TAG, "CHECKING CONNECTIVITY");
            mEmptyTextView.setText(R.string.searchProcedure);
        } else {
            mEmptyTextView.setText(R.string.no_internet);
        }

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //link the search edit text to get the search keyword
                EditText mSearchKeyword = (EditText) findViewById(R.id.searchKeyword);
                searchKeyword = mSearchKeyword.getText().toString().trim();
                ListView mListView = (ListView) findViewById(R.id.list);
                if (hasConnectivity()) {
                    Log.i(LOG_TAG, "RESTARTING LOADER");
                    mListView.setVisibility(View.VISIBLE);
                    getSupportLoaderManager().restartLoader(LOADER_ID, null, MainActivity.this);
                } else {
                    mListView.setVisibility(View.GONE);
                    Log.i(LOG_TAG, "NO INTERNET");
                    mEmptyTextView.setText(R.string.no_internet);
                    mEmptyTextView.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        Log.i(LOG_TAG, "INSIDE ONcreateLoader");
        Button searchButton = (Button) findViewById(R.id.search_button);
        return new BookLoader(this, BOOK_REQUEST_URL + searchKeyword);
    }


    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {
        final ListView listView = (ListView) findViewById(R.id.list);
        Log.i(LOG_TAG, "INSIDE ONfinishedLoader");
        if (FIRST_ACTIVITY == true) {
            Log.i(LOG_TAG, "THIS LOOP");
            mEmptyTextView.setText(R.string.searchProcedure);
            FIRST_ACTIVITY = false;
        } else {
            mEmptyTextView.setText(R.string.no_results_found);
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
        }

        mAdapter.clear();
        //if data exists
        if (data != null && !data.isEmpty()) {
            Log.i(LOG_TAG, "Populating data");
            mAdapter.addAll(data);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = (Book) listView.getItemAtPosition(position);
                String url = book.getBookInfoLink();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportLoaderManager().restartLoader(LOADER_ID, null, MainActivity.this);
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        Log.i(LOG_TAG, "data reset");
        mAdapter.clear();
    }

    //Method to check connectivity
    private boolean hasConnectivity() {
        ConnectivityManager connMng = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connMng.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}
