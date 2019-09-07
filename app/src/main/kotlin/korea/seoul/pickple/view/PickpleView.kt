package korea.seoul.pickple.view

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import androidx.core.math.MathUtils
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import korea.seoul.pickple.R
import kotlinx.android.synthetic.main.item_pickpleview.view.*
import java.lang.ref.WeakReference
import kotlin.math.abs

class PickpleView @JvmOverloads constructor(context : Context, attrs : AttributeSet?) : FrameLayout(context,attrs) {

    private val TAG = PickpleView::class.java.simpleName

    /**
     * BaseViewPager
     */
    private var mBaseViewPager : ViewPager2 = ViewPager2(context)

    private val mListeners : MutableList<WeakReference<OnPickplePageChangedListener>> = mutableListOf()

    init {
        initViewPagers()

        this.addView(mBaseViewPager,FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT))
    }

    /**
     * [mBaseViewPager], [mImageViewPager] 초기화
     */
    private fun initViewPagers() {
        mBaseViewPager.also {viewPager2->
            viewPager2.adapter = PickpleBaseViewPagerAdapter()
            viewPager2.setShowSideItems(context.resources.getDimensionPixelOffset(R.dimen.pickpleview_page_margin),context.resources.getDimensionPixelOffset(R.dimen.pickpleview_pager_offset))
        }

    }


    fun setItems(items : List<PickpleItem>) {
        (mBaseViewPager.adapter as? PickpleBaseViewPagerAdapter)?.let { adapter->
            adapter.items = items
            adapter.notifyDataSetChanged()

            Handler(Looper.getMainLooper()).postDelayed({
                mBaseViewPager.beginFakeDrag()
                mBaseViewPager.fakeDragBy(-30f)
                mBaseViewPager.fakeDragBy(30f)
                mBaseViewPager.endFakeDrag()
            },200)

        }
    }

    //region Listener Routine
    /**
     * 리스너 추가
     *
     * @param listener 추가할 [OnPickplePageChangedListener] 객체
     */
    fun addOnPageChangedListener(listener: OnPickplePageChangedListener) {
        refreshCallbacks()
        mListeners.add(WeakReference(listener))
    }

    /**
     * 리스너를 제거
     *
     * @param listener 제거할 [OnPickplePageChangedListener] 객체
     */
    fun removeOnPageChangedListener(listener: OnPickplePageChangedListener) {
        refreshCallbacks()
        mListeners.removeAll {
            it.get() == listener
        }
    }

    /**
     * 참조가 끊어진 리스너 제거
     */
    private fun refreshCallbacks() {
        val deadList = mutableListOf<WeakReference<OnPickplePageChangedListener>>()

        mListeners.forEach {
            if(it.get() == null)
                deadList.add(it)
        }

        mListeners.removeAll(deadList)
    }

    fun notifyPageSelected(position : Int) {
        refreshCallbacks()
        mListeners.forEach {
            it.get()?.onPageSelected(position)
        }
    }


    //endregion

    //region Listener
    interface OnPickplePageChangedListener {
        fun onPageSelected(position : Int)
    }
    //endregion

    //region Entity
    data class PickpleItem(
        val title : String,
        val subTitle : String,
        val isBenefit : Boolean,
        @DrawableRes val image : Int
    )
    //endregion

    //region Adapter
    /**
     * [PickpleView]의 [mBaseViewPager]에 사용될 [RecyclerView.Adapter] 클래스
     */
    inner class PickpleBaseViewPagerAdapter : RecyclerView.Adapter<PickpleBaseViewPagerAdapter.PickpleViewBaseViewHolder>() {

        var items : List<PickpleItem> = listOf()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PickpleViewBaseViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return PickpleViewBaseViewHolder(inflater.inflate(R.layout.item_pickpleview,parent,false))
        }

        override fun getItemCount() = items.size

        override fun onBindViewHolder(holder: PickpleViewBaseViewHolder, position: Int) = holder.bind(items[position])

        inner class PickpleViewBaseViewHolder(private val mView : View) : RecyclerView.ViewHolder(mView) {

            private val titleText = mView.titleText
            private val subtitleText = mView.subtitleText
            private val benefitMark = mView.benefitMark
            private val image = mView.imageView




            fun bind(item : PickpleItem) {
                titleText.text = item.title
                subtitleText.text = item.subTitle
                benefitMark.visibility = if(item.isBenefit) View.VISIBLE else View.INVISIBLE
                image.setImageResource(item.image)
            }
        }
    }
    //endregion

    private fun ViewPager2.setShowSideItems(pageMarginPx : Int, offsetPx : Int) {

        clipToPadding = false
        clipChildren = false
        offscreenPageLimit = 3

        setPageTransformer { page, position ->

            val percentLeftTextGuideline = 0.25f * (position + 1)
            page.guideline1.setGuidelinePercent(percentLeftTextGuideline)

            val percentLeftGuideline = MathUtils.clamp(1 - abs(position),0.2f,1f) * 0.15f
            page.guideline2.setGuidelinePercent(percentLeftGuideline)

            val offset = position * -(2 * offsetPx + pageMarginPx)
            if (this.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
                if (ViewCompat.getLayoutDirection(this) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                    page.translationX = -offset
                } else {
                    page.translationX = offset
                }
            } else {
                page.translationY = offset
            }
        }
    }
}

