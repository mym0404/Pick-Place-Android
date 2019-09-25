package korea.seoul.pickple.common.util

import android.net.Uri
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 * Created by mj on 26, September, 2019
 */

class MultiPartUtil(private val fileUriUtil: FileUtil) {

    fun stringToPart(text: String) = RequestBody.create(MultipartBody.FORM, text)

    fun uriToPart(name: String, uri: Uri): MultipartBody.Part {

        val file = File(fileUriUtil.getPathFromUri(uri) ?: "")


        return MultipartBody.Part.createFormData(
            name,
            file.name,
            RequestBody.create(fileUriUtil.getTypeOfFile(uri.path!!), file)
        )
    }

    fun realPathToPart(name : String, path : String) : MultipartBody.Part {
        val file = File(path)
        debugE(file.exists())

        return MultipartBody.Part.createFormData(
            name,
            file.name,
            RequestBody.create(fileUriUtil.getTypeOfFile(path), file)
        )
    }
}