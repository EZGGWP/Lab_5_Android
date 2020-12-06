package com.itmo.lab5belov

import android.content.DialogInterface
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class MainActivity : AppCompatActivity() {
    lateinit var swipeRefresh: SwipeRefreshLayout;
    lateinit var recycler: RecyclerView;
    lateinit var loading: ProgressBar;
    lateinit var adapter: Adapter;
    lateinit var loader: Loader;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        swipeRefresh = findViewById(R.id.swiperefresh);
        recycler = findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        adapter = Adapter();
        recycler.adapter = adapter;
        loading = findViewById(R.id.loading);
    }

    override fun onResume() {
        super.onResume()

        repeat (1) {
            loading.isVisible = true
            loader = Loader(this, adapter, loading);
        }

        swipeRefresh.setOnRefreshListener {
            adapter.removeAllPics();
            repeat (1) {
                swipeRefresh.isRefreshing = false;
                adapter.addPics(loader.dataSet.photos);
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater;
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.searchItem -> {
                val dialogView = layoutInflater.inflate(R.layout.dialog, null)
                val dialogBuilder = AlertDialog.Builder(this, R.style.Theme_SampleDialog);
                val searchET = dialogView.findViewById<EditText>(R.id.search_edittext);
                dialogBuilder.setView(dialogView);
                dialogBuilder.setTitle("New search");
                dialogBuilder.setCancelable(true);
                dialogBuilder.setPositiveButton("Search") { _: DialogInterface, _: Int ->
                    loader.loadPics(searchET.text.toString());
                };
                val dialog = dialogBuilder.create();
                val dialogSize = WindowManager.LayoutParams();
                dialogSize.copyFrom(dialog.window?.attributes);
                dialogSize.width = WindowManager.LayoutParams.MATCH_PARENT;
                dialogSize.height = WindowManager.LayoutParams.MATCH_PARENT;
                dialog.window?.attributes = dialogSize;
                dialog.show();

                true;
            }
            else ->
                super.onOptionsItemSelected(item)
        }
    }
}