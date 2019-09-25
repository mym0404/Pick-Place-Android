package korea.seoul.pickple.common.util

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment
import org.koin.core.KoinComponent

class GalleryUtil : KoinComponent {
    private val REQ_CHOOSE_PICTURE = 0

    interface GalleryUtilListener {
        fun onReadyImageUri(uri: Uri) {}
        fun onFailLoad() {}
    }

    fun choosePhotoFromGallery(fragment: Fragment) {
        val intent = Intent(Intent.ACTION_PICK).apply {
            type = "image/*"
        }

        fragment.startActivityForResult(Intent.createChooser(intent, "Select Image"), REQ_CHOOSE_PICTURE)
    }

    fun choosePhotoFromGallery(activity: Activity) {
        val intent = Intent(Intent.ACTION_PICK).apply {
            type = "image/*"
        }

        activity.startActivityForResult(Intent.createChooser(intent, "Select Image"), REQ_CHOOSE_PICTURE)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?, listener: GalleryUtilListener) {
        val selectedMediaUri = data?.data

        when (requestCode) {

            REQ_CHOOSE_PICTURE -> {

                if (resultCode == RESULT_OK) {

                    if (selectedMediaUri == null) {
                        listener.onFailLoad()
                        return
                    }


                    listener.onReadyImageUri(selectedMediaUri)


                } else {
                    listener.onFailLoad()
                }
            }


        }
    }


}