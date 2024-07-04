package Activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.venuebooker.Adapters.ItemAdapter
import com.example.venuebooker.ApiModels.AllTurfsResponse
import com.example.venuebooker.Controller.API
import com.example.venuebooker.R
import com.example.venuebooker.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.recyclerView.layoutManager=
            LinearLayoutManager(this@MainActivity)
        binding.progressBar.visibility = View.VISIBLE
        binding.errorTv.visibility = View.GONE

        val api: Call<AllTurfsResponse> =
            API.getInstance().getTurfs()
        api.enqueue(object : Callback<AllTurfsResponse> {
            override fun onResponse(
                call: Call<AllTurfsResponse>,
                response: Response<AllTurfsResponse>
            ) {
                val obj: AllTurfsResponse = response.body()!!
                binding.recyclerView.adapter= ItemAdapter(obj.response,this@MainActivity)
                binding.progressBar.visibility = View.GONE
            }

            override fun onFailure(call: Call<AllTurfsResponse>, t: Throwable) {
                Log.d("error",t.message.toString());
                binding.errorTv.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
            }
        })

    }


}