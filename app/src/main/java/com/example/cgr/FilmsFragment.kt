package com.example.cgr

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cgr.films.Film
import com.example.cgr.films.FilmsAdapter
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
 * Use the [FilmsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FilmsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

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
        return inflater.inflate(R.layout.fragment_films, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val films = arrayListOf<Film>()
        val recyclerViewFilms = view.findViewById<RecyclerView>(R.id.recyclerViewFilms)
        recyclerViewFilms.layoutManager = LinearLayoutManager(requireContext())
        val filmsAdapter = FilmsAdapter(mContext, films)
        recyclerViewFilms.adapter = filmsAdapter

        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
        val mRequestUrl = "https://ugarit-online.000webhostapp.com/epsi/films/movies.json"
        val request =
            Request.Builder().url(mRequestUrl).get().cacheControl(CacheControl.FORCE_NETWORK)
                .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                val data = response.body?.string()
                if (data != null) {
                    val jsFilms = JSONObject(data)
                    val jsArrayFilms = jsFilms.getJSONArray("movies")
                    for (i in 0 until jsArrayFilms.length()) {
                        val js = jsArrayFilms.getJSONObject(i)
                        val film = Film(
                            js.optString("id", ""),
                            js.optString("title", ""),
                            js.optString("description", ""),
                            js.optString("runTime", ""),
                            js.optString("graphicUrl", ""),
                            js.optString("backdropUrl", "")
                        )
                        films.add(film)
                    }
                    requireActivity().runOnUiThread {
                        filmsAdapter.notifyDataSetChanged()
                    }
                }
            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FilmsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FilmsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}