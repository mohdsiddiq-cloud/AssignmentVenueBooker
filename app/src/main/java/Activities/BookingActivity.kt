package Activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.venuebooker.Adapters.ItemAdapter4
import retrofit2.Response
import com.example.venuebooker.ApiModels.BookingRequest
import com.example.venuebooker.ApiModels.Slot
import com.example.venuebooker.ApiModels.bookingResponse
import com.example.venuebooker.Controller.API
import com.example.venuebooker.R
import com.example.venuebooker.databinding.ActivityBookingBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback

class BookingActivity : AppCompatActivity() {
    lateinit var binding: ActivityBookingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val id = intent.getIntExtra("id",0)
        val startTime = intent.getStringExtra("startTime")
        val endTime = intent.getStringExtra("endTime")

//        val slots = listOf(
//            startTime?.let {
//                if (endTime != null) {
//                    Slot(id, it,endTime)
//                }
//            }
//        )
        val slots = listOf(
            Slot(turf_id = id, start_time = startTime.toString(), end_time = endTime.toString())
        )

        binding.progressBar.visibility = View.GONE

        binding.submitBtn.setOnClickListener {
            val firstname = binding.firstName.text
            val lastName = binding.lastName.text
            val email = binding.email.text
            val code = binding.dialCode.text
            val number = binding.number.text

//            val bookingRequest = BookingRequest(
//               slots, firstname.toString(), lastName.toString(),email.toString(),code.toString(),number.toString()
//            )

            val bookingRequest = BookingRequest(
                slots = slots,
                user_first_name = firstname.toString(),
                user_last_name = lastName.toString(),
                user_email = email.toString(),
                user_dial_code = code.toString(),
                user_mobile_no = number.toString()
            )
            val gson = Gson()

            val bookingRequestJson = gson.toJson(bookingRequest)

            Log.d("BookingRequestJSON", bookingRequestJson)
//            Log.d("mydata",bookingRequest.toString())

            val api: Call<bookingResponse> =
                API.getInstance().bookSlot(bookingRequest)
            api.enqueue(object : Callback<bookingResponse> {


                override fun onResponse(
                    call: Call<bookingResponse>,
                    response: Response<bookingResponse>
                ) {
                    if(response.code()==200){
                        val obj: bookingResponse = response.body()!!
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this@BookingActivity,response.message() ,Toast.LENGTH_SHORT).show()
                        Log.d("here1",response.message())
                    }
                    else{
                        Toast.makeText(this@BookingActivity,"something went wrong" ,Toast.LENGTH_SHORT).show()
//                        Log.d("here2",response.error)
                    }

                }

                override fun onFailure(call: Call<bookingResponse>, t: Throwable) {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this@BookingActivity,t.message ,Toast.LENGTH_SHORT).show()
                    Log.d("herefail",t.message.toString());
                }
            })
        }


    }
}