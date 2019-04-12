package com.alexanderastanford.launchertest

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import android.widget.GridView
import com.alexanderastanford.launchertest.adapters.AppAdapter
import com.alexanderastanford.launchertest.models.AppModel



class Home : AppCompatActivity() {

    var gridApps: GridView ? = null
    var listApps: ArrayList<AppModel> = ArrayList<AppModel>()
    var appsAdapter: AppAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hideStatusBar()

        setContentView(R.layout.activity_home)

        gridApps = findViewById<GridView>(R.id.gv_content_home_apps) as GridView
        addApps()
        appsAdapter = AppAdapter(listApps, this, packageManager, this)
        gridApps?.adapter = appsAdapter

        window.decorView.setOnSystemUiVisibilityChangeListener { visibility ->
            if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
                hideStatusBar()
            }
        }
    }

    private fun addApps() {
        val pManager = packageManager.getInstalledApplications(0)
        for (index in pManager.indices) {
            val app = pManager[index]

            val appModel: AppModel = AppModel()
            appModel.appResource = app.packageName
            appModel.imageResource = app.loadIcon(packageManager)

            listApps.add(appModel)
        }
    }

    private fun hideStatusBar() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
    }
}
