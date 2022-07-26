package com.rochim.finalprojectschoters

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.rochim.finalprojectschoters.databinding.ActivityArticleBinding

class ArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleBinding
    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar
    private lateinit var frag: String
    private lateinit var hint: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        frag = intent.getStringExtra("frag").toString()
        hint = intent.getStringExtra("hint").toString()

        binding.fabBack.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("frag",frag)
            intent.putExtra("hint",hint)
            startActivity(intent)
        }

        progressBar = binding.loading

        val url: String = intent.getStringExtra("url").toString()
        webView = binding.wvNews
        webView.webViewClient = WebViewClient()
        webView.apply {
            loadUrl(url)
            settings.javaScriptEnabled = true
            settings.setSupportZoom(true)
            webViewClient = object : WebViewClient(){
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    progressBar.visibility = View.VISIBLE
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    progressBar.visibility = View.GONE
                }
            }
        }
    }
}