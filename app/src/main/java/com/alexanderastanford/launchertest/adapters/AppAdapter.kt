package com.alexanderastanford.launchertest.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.alexanderastanford.launchertest.R
import com.alexanderastanford.launchertest.models.AppModel
import android.content.pm.PackageManager
import android.util.Log
import java.lang.Exception


data class AppAdapter(var appList:List<AppModel>, var activity: Activity, var packageManager: PackageManager, val context: Context) : BaseAdapter(){

    override fun getItem(position: Int): Any {
        return appList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return appList.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = View.inflate(activity, R.layout.layout_adapter_app,null)


        val appModel: AppModel = getItem(position) as AppModel

        val appImageView = view.findViewById<ImageView>(R.id.iv_layout_adapter_app_app_image) as ImageView
        appImageView.setImageDrawable(appModel.imageResource)
        appImageView.setOnClickListener {
            try {
                val intent: Intent = packageManager.getLaunchIntentForPackage(appModel.appResource)
                if (intent != null) {
                    context.startActivity(intent)
                }
            } catch (e: Exception) {
                Log.d("something broke", "woops")
            }
        }

        return view
    }

}