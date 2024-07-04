package Activities

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.venuebooker.Adapters.ItemAdapter
import com.example.venuebooker.Adapters.ItemAdapter4
import com.example.venuebooker.ApiModels.AllTurfsResponse
import com.example.venuebooker.ApiModels.SlotsResponse
import com.example.venuebooker.Controller.API
import com.example.venuebooker.R
import com.example.venuebooker.databinding.ActivityMainBinding
import com.example.venuebooker.databinding.ActivitySlotsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class SlotsActivity : AppCompatActivity() {
    lateinit var binding: ActivitySlotsBinding
    lateinit var date:String
    var id:Int= 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivitySlotsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        id = intent.getIntExtra("id",0)
        binding.recyclerView.layoutManager=
            LinearLayoutManager(this@SlotsActivity)
        binding.progressBar.visibility = View.GONE
        binding.errorTv.visibility = View.VISIBLE


    }


    fun showDatePickerDialog(view: View) {
        binding.progressBar.visibility = View.VISIBLE
        binding.errorTv.visibility = View.GONE

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
                // Validate date here
                val selectedCalendar = Calendar.getInstance()
                selectedCalendar.set(selectedYear, selectedMonth, selectedDay)
                if (selectedCalendar >= Calendar.getInstance()) {
                    // Format the selected date as "yyyy-MM-dd"
                    val formattedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(selectedCalendar.time)
                    date = formattedDate

                    Log.d("myvalue",date)
                    val api: Call<SlotsResponse> =
                        API.getInstance().getSlots(id.toString(),date)
                    api.enqueue(object : Callback<SlotsResponse> {
                        override fun onResponse(
                            call: Call<SlotsResponse>,
                            response: Response<SlotsResponse>
                        ) {
                            val obj: SlotsResponse = response.body()!!
                            binding.recyclerView.adapter= ItemAdapter4(obj.response,this@SlotsActivity,id)
                            binding.progressBar.visibility = View.GONE
                        }

                        override fun onFailure(call: Call<SlotsResponse>, t: Throwable) {
                            Log.d("error",t.message.toString());
                            binding.errorTv.visibility = View.VISIBLE
                            binding.progressBar.visibility = View.GONE
                        }
                    })

                } else {
                    binding.progressBar.visibility = View.GONE
                    binding.errorTv.visibility = View.VISIBLE
                }
            },
            year,
            month,
            day
        )

        // Set minimum date to today
        datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000 // Disable one second ago

        datePickerDialog.show()

    }
}