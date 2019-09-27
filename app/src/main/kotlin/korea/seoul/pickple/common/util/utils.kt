package korea.seoul.pickple.common.util

import android.app.Activity
import android.content.Context
import android.inputmethodservice.InputMethodService
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * 스트링 리스트를 # 으로 엮어서 하나의 스트링을 반환하는 단순한 함수
 * @param count 몇 개의 tag를 보여줄 지 정하는 것, 기본값은 전체를 다 보여줌
 * @return "#tag1  #tag2  " 와 같은 스트링
 * @author greedy0110
 * */
fun List<String>.toTagList(count: Int = this.size): String {
    val str = StringBuffer()
    val targetTag = take(count)
    for (tag in targetTag){
        str.append("#$tag  ")
    }
    return str.toString()
}

fun hideKeyboardFrom(context: Context, view: View) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.let { it.hideSoftInputFromWindow(view.windowToken, 0) }
}