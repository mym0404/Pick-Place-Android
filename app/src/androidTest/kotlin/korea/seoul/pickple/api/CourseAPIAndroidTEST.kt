package korea.seoul.pickple.api

import android.Manifest
import android.app.Application
import android.provider.MediaStore
import androidx.test.core.app.ApplicationProvider
import androidx.test.rule.GrantPermissionRule
import korea.seoul.pickple.common.util.MultiPartUtil
import korea.seoul.pickple.common.util.TokenUtil
import korea.seoul.pickple.common.util.debugE
import korea.seoul.pickple.data.api.CourseAPI
import korea.seoul.pickple.data.enumerator.SeoulDistrict
import org.junit.Rule
import org.junit.Test
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Created by mj on 26, September, 2019
 */

class CourseAPIAndroidTEST : KoinComponent {

    private val courseAPI : CourseAPI by inject()
    private val multiPartUtil : MultiPartUtil by inject()
    private val tokenUtil : TokenUtil by inject()

    @get:Rule
    var permissionRule = GrantPermissionRule.grant(Manifest.permission.WRITE_EXTERNAL_STORAGE)

    @Test
    fun createCouseTest() {
        tokenUtil.saveToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoxLCJlbWFpbCI6InBpY2twbGVAZ21haWwuY29tIiwibmlja25hbWUiOiJwaWNrcGxlIiwiaWF0IjoxNTY5NDM2NTUxLCJleHAiOjE1NzA2NDYxNTEsImlzcyI6InlhbmcifQ.Zu9cxyYgVlFkCBExW9m-NUJiNhs2f2zKc02MMM0pV9M")

        val cursor = ApplicationProvider.getApplicationContext<Application>()
            .contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,null,null
        ,null,null)!!

        cursor.moveToNext()

        val imageStr = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))

        val part = multiPartUtil.realPathToPart("course_thumbnail",imageStr)



            val response = courseAPI.createCourse(
                "이름",
                "설명",
                part,
                1,
                2,
                3,
                4,
                5,
                1f,
                2f,
                3f,
                4f,
                "tag1",
                "tag2",
                "tag3",
                "tag4",
                "tag5",
                "2019-01-01",
                SeoulDistrict.DOBONG.code,
                1,
                1,
                "2억광년"
            ).execute().body()!!

        debugE(response)


    }
}