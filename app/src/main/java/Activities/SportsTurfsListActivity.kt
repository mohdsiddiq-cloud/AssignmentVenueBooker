package Activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.venuebooker.Adapters.ItemAdapter2
import com.example.venuebooker.Adapters.ItemAdapter3
import com.example.venuebooker.ApiModels.AllTurfsResponse
import com.example.venuebooker.Controller.API
import com.example.venuebooker.R
import com.example.venuebooker.databinding.ActivitySportsTurfsListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SportsTurfsListActivity : AppCompatActivity() {
    lateinit var binding: ActivitySportsTurfsListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivitySportsTurfsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val id1 = intent.getIntExtra("id1",0)
        val id2 = intent.getIntExtra("id2",0)
        Log.d("welcome","welcome in this activity")

        binding.progressBar.visibility = View.VISIBLE
        binding.recyclerView.layoutManager = LinearLayoutManager(this@SportsTurfsListActivity)
        binding.errorTv.visibility = View.GONE

        val api: Call<AllTurfsResponse> =
            API.getInstance().getTurfs()
        api.enqueue(object : Callback<AllTurfsResponse> {
            override fun onResponse(
                call: Call<AllTurfsResponse>,
                response: Response<AllTurfsResponse>
            ) {
                val obj: AllTurfsResponse = response.body()!!
                Log.d("value",obj.response.get(id1).categories.get(id2).turfs.get(0).name)
                binding.recyclerView.adapter= ItemAdapter3(obj.response[id1].categories[id2].turfs,this@SportsTurfsListActivity)
                binding.progressBar.visibility = View.GONE

            }

            override fun onFailure(call: Call<AllTurfsResponse>, t: Throwable) {

                binding.errorTv.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE

            }
        })

    }
}