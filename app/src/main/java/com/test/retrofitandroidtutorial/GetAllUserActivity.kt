package com.test.retrofitandroidtutorial

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.retrofitandroidtutorial.adapters.RvAdapter
import com.test.retrofitandroidtutorial.databinding.ActivityGetAllUserBinding
import com.test.retrofitandroidtutorial.models.UsersItem
import com.test.retrofitandroidtutorial.utils.RetrofitInstance
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class GetAllUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGetAllUserBinding

    private lateinit var rvAdapter: RvAdapter
    private lateinit var usersList: List<UsersItem>

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetAllUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        usersList = listOf()

        GlobalScope.launch(Dispatchers.IO) {
            val response = try {
                RetrofitInstance.api.getAllUsers()
            } catch (e: IOException) {
                Toast.makeText(applicationContext,"app error ${e.message}", Toast.LENGTH_LONG).show()
                return@launch
            } catch (e: HttpException) {
                Toast.makeText(applicationContext,"http error ${e.message}", Toast.LENGTH_LONG).show()
                return@launch
            }

            if (response.isSuccessful && response.body() != null){
                withContext(Dispatchers.Main){
                    usersList = response.body()!!
                    binding.rvMain.apply {
                        rvAdapter = RvAdapter(usersList)
                        adapter = rvAdapter
                        layoutManager = LinearLayoutManager(this@GetAllUserActivity)
                    }
                }
            }
        }
    }
}