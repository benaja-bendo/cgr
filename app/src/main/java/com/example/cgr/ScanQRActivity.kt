package com.example.cgr

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.util.forEach
import androidx.core.util.isNotEmpty
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector

class ScanQRActivity : AppCompatActivity() {

    companion object {
        const val QR_CODE_KEY = "qr_code_key"
        private const val CAMERA_PERMISSION_REQUEST_CODE = 100
    }

    private lateinit var scanSurfaceView: SurfaceView
    private lateinit var barcodeDetector: BarcodeDetector
    private lateinit var cameraSource: CameraSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_qractivity)

        scanSurfaceView = findViewById(R.id.scan_surface_view)
        initBarcodeDetector()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (cameraPermissionGranted(requestCode, grantResults)) {
            finish()
            overridePendingTransition(0, 0)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }else {
            Toast.makeText(this, "Camera permission is required to scan QR code", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun cameraPermissionGranted(requestCode: Int, grantResults: IntArray): Boolean {
        return requestCode == CAMERA_PERMISSION_REQUEST_CODE
                && grantResults.isNotEmpty()
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
    }

    private fun initBarcodeDetector() {
        barcodeDetector = BarcodeDetector.Builder(this)
            .setBarcodeFormats(Barcode.ALL_FORMATS)
            .build()
        initCameraSource()
        initScanSurfaceView()

        barcodeDetector.setProcessor(object : com.google.android.gms.vision.Detector.Processor<Barcode> {
            override fun release() {
            }

            override fun receiveDetections(detections: Detector.Detections<Barcode>) {
                val barcodes = detections.detectedItems
//                if (barcodes.size() != 0) {
//                    val intent = intent
//                    intent.putExtra(QR_CODE_KEY, barcodes.valueAt(0)?.displayValue)
//                    setResult(RESULT_OK, intent)
//                    finish()
//                }
                if (barcodes.isNotEmpty()){
                    barcodes.forEach { _, barcode ->
                        if (barcode.displayValue.isNotEmpty()){
                            onQrCodeDetected(barcode.displayValue)
                        }
                    }
                }
            }
        })
    }

    private fun onQrCodeDetected(displayValue: String) {
        val intent = Intent()
        intent.putExtra(QR_CODE_KEY, displayValue)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    private fun initScanSurfaceView() {
        scanSurfaceView.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                try {
                    if (ActivityCompat.checkSelfPermission(
                            this@ScanQRActivity,
                            Manifest.permission.CAMERA
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        ActivityCompat.requestPermissions(
                            this@ScanQRActivity,
                            arrayOf(Manifest.permission.CAMERA),
                            CAMERA_PERMISSION_REQUEST_CODE
                        )

                        return
                    }
                    cameraSource.start(holder)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                cameraSource.release()
            }
        })
    }

    private fun initCameraSource() {
        cameraSource = CameraSource.Builder(this, barcodeDetector)
            .setRequestedPreviewSize(1920, 1080)
//            .setRequestedPreviewSize(640, 480)
            .setAutoFocusEnabled(true)
            .build()
    }
}