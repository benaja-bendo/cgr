import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cgr.films.Film
import com.example.cgr.films.FilmsAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.cgr.R
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class PanierFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var filmsAdapter: FilmsAdapter
    private val filmsList = mutableListOf<Film>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_panier, container, false)
        val arrayList = ArrayList(filmsList)
        recyclerView = view.findViewById(R.id.recyclerViewPanier)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        filmsAdapter = FilmsAdapter(requireContext(), arrayList)
        recyclerView.adapter = filmsAdapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadFilmsData()
    }

    private fun loadFilmsData() {
        val json = loadJSONFromAsset("movies.json")
        if (json != null) {
            val gson = Gson()
            val filmType = object : TypeToken<List<Film>>() {}.type
            val films: List<Film> = gson.fromJson(json, filmType)
            filmsList.addAll(films)
            filmsAdapter.notifyItemChanged(0)
        } else {
            Toast.makeText(requireContext(), "Failed to load films data", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadJSONFromAsset(filename: String): String? {
        return try {
            val inputStream = requireContext().assets.open(filename)
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            val stringBuilder = StringBuilder()
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
            stringBuilder.toString()
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}
