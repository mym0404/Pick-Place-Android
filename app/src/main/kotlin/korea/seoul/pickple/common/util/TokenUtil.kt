package korea.seoul.pickple.common.util

/**
 * Created by mj on 24, September, 2019
 */

class TokenUtil(private val spUtil: SPUtil) {
    fun saveToken(token : String) {
        spUtil.putString(SPUtil.SPKeys.TOKEN,token)
    }
    fun loadToken() : String? {
        return spUtil.getString(SPUtil.SPKeys.TOKEN)
    }
}