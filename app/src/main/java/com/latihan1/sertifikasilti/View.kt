package com.latihan1.sertifikasilti

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.print.PrintAttributes
import android.print.PrintDocumentAdapter
import android.print.PrintManager
import android.util.Log
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.core.net.toUri
import androidx.core.text.parseAsHtml
import androidx.print.PrintHelper
import com.downloader.PRDownloader
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_menu_pilihan.*
import kotlinx.android.synthetic.main.activity_view.*
import kotlinx.android.synthetic.main.activity_view.downloadview

class View : AppCompatActivity() {
    var mydownloadid : Long = 0
    companion object{
        const val EXTRA_NO_SERTIFIKAT = "nosertifikat"
        const val EXTRA_NO_HP = "nohp"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        PRDownloader.initialize(applicationContext)
        setContentView(R.layout.activity_view)

        val no_sertifikat = intent.getStringExtra(EXTRA_NO_SERTIFIKAT)
        val no_hp = intent.getStringExtra(EXTRA_NO_HP)

        pdfView.webViewClient = WebViewClient()
        pdfView.settings.setSupportZoom(true)

        pdfView.settings.javaScriptEnabled = true

        val url = FileUtils().getPdfUrl() + no_hp +"/"+ no_sertifikat
        Log.d("view-url", url)
        pdfView.loadUrl(url)

        downloadview.setOnClickListener {
//            val intent = Intent(this, Download::class.java)
//            intent.putExtra("ViewType", "internet")
//            startActivity(intent)
//            val no_sertifikat = intent.getStringExtra(EXTRA_NO_SERTIFIKAT)
//            val no_hp = intent.getStringExtra(EXTRA_NO_HP)
//
//            val externalPath = Environment.getExternalStorageDirectory().path + "MyExternalStorageAppPath"
//            val url = "http://server4111.com/db_sertifikat/frontend_kel3/front/index.php/Pdfview/cetak/$no_hp/$no_sertifikat"
//            val fileName = url.substring(url.lastIndexOf('/') + 1)
//            var request = DownloadManager.Request(Uri.parse(url))
//                .setTitle("Sertifikat Lauwba Techno Indonesia") //judul dari notifikasi
//                .setDescription("Sedang Mendownload Sertifikat")
//                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
//                .setAllowedOverMetered(true)
//                .setAllowedOverRoaming(true)
////                .setDestinationUri(Uri.fromFile(fileName))
////                .setDestinationInExternalFilesDir(this, externalPath, "Sertifikat.pdf");
//                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,fileName)
//            var dm = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
//            mydownloadid = dm.enqueue(request)

            createWebPrintJob(this, pdfView)
        }

        var br = object : BroadcastReceiver(){
            override fun onReceive(p0: Context?, p1: Intent?) {
                var id = p1?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID,-1)
                if (id == mydownloadid){
                    Toast.makeText(applicationContext, "Sertifikat Berhasil Didownload", Toast.LENGTH_SHORT).show()
                }
            }

        }
        registerReceiver(br, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))

    }
    @Suppress("DEPRECATION")
    private fun createWebPrintJob(context: Context, webView: WebView) {

//        PrintHelper(context).apply {
//            scaleMode = PrintHelper.SCALE_MODE_FIT
                // Get a PrintManager instance
        val printManager =
            context.getSystemService(Context.PRINT_SERVICE) as PrintManager
        val jobName: String = webView.title + " Document"

        // Get a print adapter instance
        val printAdapter: PrintDocumentAdapter
        printAdapter =
            webView.createPrintDocumentAdapter(jobName)
        // Create a print job with name and adapter instance
        printManager.print(
            jobName, printAdapter,
            PrintAttributes.Builder().build()
        )
        val builder: PrintAttributes.Builder = PrintAttributes.Builder()
        builder.setMediaSize(PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE)

        // Save the job object for later status checking
//        mPrintJobs.add(printJob)
//    }
    }


}
