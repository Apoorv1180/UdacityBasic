package com.example.android.mylogininformation.UI.Activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.android.mylogininformation.Adapter.ApkAdapter;
import com.example.android.mylogininformation.R;

import java.util.List;

public class ViewInstalledAppsActivity extends AppCompatActivity {

    PackageManager packageManager;
    ListView apkList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_installed_apps);

        packageManager = getPackageManager();
        List<PackageInfo> packageList = packageManager
                .getInstalledPackages(PackageManager.GET_PERMISSIONS);

        apkList = findViewById(R.id.applist);
        apkList.setAdapter(new ApkAdapter(this, packageList, packageManager));

        //For further functionality to view details of each app their revision etc
        //the on click can be implemented
        //And using the list view and another xml can be shown into ApkInfo

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position,
//                            long row) {
//        PackageInfo packageInfo = (PackageInfo) parent
//                .getItemAtPosition(position);
//        AppData appData = (AppData) getApplicationContext();
//        appData.setPackageInfo(packageInfo);
//
//        Intent appInfo = new Intent(getApplicationContext(), ApkInfo.class);
//        startActivity(appInfo);
//    }


    }
}

