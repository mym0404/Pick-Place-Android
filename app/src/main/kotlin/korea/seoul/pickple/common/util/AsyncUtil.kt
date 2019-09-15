package korea.seoul.pickple.common.util

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Call 객체에 enqueue하는 작업을 단순화 시킨 것
 *
 * @param successCallback Callback 객체의 onResponse시, 성공이면 데이터를 꺼내서 사용자 지정 콜백으로 넘김
 * @param failCallback Callback 객체의 onResponse시, 실퍄면 데이터를 꺼내서 사용자 지정 콜백으로 넘김
 * @param errorCallback Callback 객체의 onFailure 발생시, 에러 메시지를 사용자 지정 콜백으로 넘김
 * @author greedy0110
 * */
fun<T> Call<T>.callback(
    successCallback: ((T) -> Unit)? = null,
    failCallback: (() -> Unit)? = null,
    errorCallback: ((Throwable) -> Unit)? = null
) {
    enqueue(
        object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                Log.e("pick-place", "network error : ${t.message}")
                errorCallback?.invoke(t)
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    successCallback?.invoke(response.body()!!)
                }
                else {
                    failCallback?.invoke()
                }
            }
        }
    )
}