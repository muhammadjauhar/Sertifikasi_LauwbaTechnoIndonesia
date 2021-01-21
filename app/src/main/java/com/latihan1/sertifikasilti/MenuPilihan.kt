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
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_menu_pilihan.*

class MenuPilihan : AppCompatActivity() {
    var mydownloadid : Long = 0

    companion object{
        const val EXTRA_NO_SERTIFIKAT = "extra_nosertifikat"
        const val EXTRA_NAMA = "extra_nama"
        const val EXTRA_NO_HP = "extra_notelp"
        const val EXTRA_EMAIL = "extra_email"
        const val EXTRA_JADWAL_PELAKSANAAN = "extra_jadwal_pelaksanaan"
        const val EXTRA_NAMA_PERUSAHAAN = "extra_nama_perusahaan"
        const val EXTRA_JABATAN = "extra_jabatan"
        const val EXTRA_KOTA_PELAKSANAAN = "extra_kota_pelaksanaan"
        const val EXTRA_INFO = "extra_info"
        const val EXTRA_MOTIVASI = "extra_motivasi"
        const val EXTRA_KET_TAMBAHAN = "extra_ket_tambahan"
        const val EXTRA_STATUS = "extra_status"
        const val EXTRA_AKSES = "extra_akses"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_pilihan)

        val no_sertifikat = intent.getStringExtra(EXTRA_NO_SERTIFIKAT)
        val nama = intent.getStringExtra(EXTRA_NAMA)
        val no_hp = intent.getStringExtra(EXTRA_NO_HP)
        val email = intent.getStringExtra(EXTRA_EMAIL)
        val jadwal_pelaksanaan = intent.getStringExtra(EXTRA_JADWAL_PELAKSANAAN)
        val nama_perusahaan = intent.getStringExtra(EXTRA_NAMA_PERUSAHAAN)
        val jabatan = intent.getStringExtra(EXTRA_JABATAN)
        val kota_pelaksanaan = intent.getStringExtra(EXTRA_KOTA_PELAKSANAAN)
        val info = intent.getStringExtra(EXTRA_INFO)
        val motivasi = intent.getStringExtra(EXTRA_MOTIVASI)
        val ket_tambahan = intent.getStringExtra(EXTRA_KET_TAMBAHAN)
//        val status = intent.getStringExtra(EXTRA_STATUS)
//        val akses = intent.getStringExtra(EXTRA_AKSES)

        txt_nosertifikat.text = no_sertifikat
        txt_nama.text = nama
        txt_hp.text = no_hp
        txt_email.text = email
        txt_jadwal.text = jadwal_pelaksanaan
        txt_perusahaan.text = nama_perusahaan
        txt_jabatan.text = jabatan
        txt_kota.text = kota_pelaksanaan
        txt_info.text = info
        txt_motivasi.text = motivasi
        txt_ket.text = ket_tambahan
//        txt_status.text = status
//        txt_akses.text = akses


//    fun checkPdfAction (intent: Intent) {
//         when (intent.getStringExtra("ViewType")) {
//             "internet" -> {
//               // perform action to show pdf from the internet
//              }
//         }
//    }

//        download.setOnClickListener {
////            val intent = Intent(this, Download::class.java)
////            intent.putExtra("ViewType", "internet")
////            startActivity(intent)
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
//        }

        var br = object : BroadcastReceiver(){
            override fun onReceive(p0: Context?, p1: Intent?) {
                var id = p1?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID,-1)
                if (id == mydownloadid){
                    Toast.makeText(applicationContext, "Sertifikat Berhasil Didownload", Toast.LENGTH_SHORT).show()
                }
            }

        }
        registerReceiver(br, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))

        view.setOnClickListener {
            val intent = Intent(this, View::class.java)
            intent.putExtra("nosertifikat", no_sertifikat )
            intent.putExtra("nohp", no_hp )
            startActivity(intent)
//            startActivity(Intent(this, View::class.java))
        }
    }
}
