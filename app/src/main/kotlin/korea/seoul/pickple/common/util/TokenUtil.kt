package korea.seoul.pickple.common.util

/**
 * Created by mj on 24, September, 2019
 */

class TokenUtil(private val spUtil: SPUtil) {
    fun saveToken(token : String) {
        spUtil.putString(SPUtil.SPKeys.TOKEN,token)
    }
    fun loadToken() : String? {
        // 신승민 커밋 전에 지워!
        // return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoxLCJlbWFpbCI6InBpY2twbGVAZ21haWwuY29tIiwibmlja25hbWUiOiJwaWNrcGxlIiwiaWF0IjoxNTY5NDM2NTUxLCJleHAiOjE1NzA2NDYxNTEsImlzcyI6InlhbmcifQ.Zu9cxyYgVlFkCBExW9m-NUJiNhs2f2zKc02MMM0pV9M"
        return spUtil.getString(SPUtil.SPKeys.TOKEN)
    }
}