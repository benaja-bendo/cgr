package com.example.cgr

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cgr.salles.Salle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import okhttp3.CacheControl
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SallesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SallesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    val salles = arrayListOf<Salle>()
    val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
    val mRequestUrl = "https://ugarit-online.000webhostapp.com/epsi/films/salles.json"
    val request = Request.Builder().url(mRequestUrl).get().cacheControl(CacheControl.FORCE_NETWORK).build()
    lateinit var googleMap: GoogleMap



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_salles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync { googleMap ->
            okHttpClient.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response) {
                    val data = response.body?.string()
                    if (data != null) {
                        val jsonSalles = JSONObject(data)
                        val items = jsonSalles.getJSONArray("salles")

                        for (i in 0 until items.length()) {
                            val salle = items.getJSONObject(i)
                            val id = salle.optString("id", "")
                            val name = salle.optString("name", "Unknown")
                            val address1 = salle.optString("address1", "Unknown")
                            val address2 = salle.optString("address2", "Unknown")
                            val city = salle.optString("city", "Unknown")
                            val lat = salle.optDouble("latitude", 0.0)
                            val lng = salle.optDouble("longitude", 0.0)
                            val parkingInfo = salle.optString("parkingInfo", "Unknown")
                            val description = salle.optString("description", "Unknown")
                            val publicTransport = salle.optString("publicTransport", "Unknown")


                            val salleObj = Salle(id, name, address1, address2, city, lat, lng, parkingInfo, description, publicTransport)
                            salles.add(salleObj)

                            val originalMarkerBitmap = BitmapFactory.decodeResource(resources, R.drawable.cinema_marker)

                            val width = 50
                            val height = 50

                            val scaledMarkerBitmap = Bitmap.createScaledBitmap(originalMarkerBitmap, width, height, false)

                            val markerOptions = MarkerOptions()
                                .position(LatLng(lat, lng))
                                .title(name)
                                .icon(BitmapDescriptorFactory.fromBitmap(scaledMarkerBitmap))



                            activity?.runOnUiThread {
                                val initialLocation = LatLng(48.8566, 2.3522)

                                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(initialLocation,
                                    5F
                                ))
                                googleMap.addMarker(markerOptions)

                                googleMap.setOnMarkerClickListener { clickedMarker ->
                                    val clickedSalle = salles.find { it.name == clickedMarker.title }
                                    if (clickedSalle != null) {
                                        val intent = Intent(activity, SalleDetailActivity::class.java).apply {
                                            putExtra("nameSalle", clickedSalle.name)
                                            putExtra("address1", clickedSalle.address1)
                                            putExtra("address2", clickedSalle.address2)
                                            putExtra("descriptionSalle", clickedSalle.description)
                                            putExtra("parkingInfo", clickedSalle.parkingInfo)
                                            putExtra("publicTransport", clickedSalle.publicTransport)
                                        }
                                        startActivity(intent)
                                        true
                                    } else {
                                        false
                                    }
                                }
                            }
                        }
                    }
                }
            })
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SallesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SallesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}