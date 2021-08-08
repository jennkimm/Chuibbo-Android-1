import GuidelineContentsFragment.Companion.newIntent
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.chuibbo_android.R
import kotlinx.android.synthetic.main.guideline_fragment.*
import kotlinx.android.synthetic.main.guideline_fragment_contents.*
import kotlinx.android.synthetic.main.main_activity.*

private const val NUM_PAGES = 4

class GuidelineFragment : Fragment() {

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private lateinit var viewPager: ViewPager2
    private lateinit var callback: OnBackPressedCallback

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.guideline_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager = pager!!
        val pagerAdapter = ScreenSlidePagerAdapter(this)
        viewPager.adapter = pagerAdapter
        indicator.setViewPager(viewPager)
        pagerAdapter.registerAdapterDataObserver(indicator.adapterDataObserver)

        activity?.toolbar!!.setTitle("촬영 가이드라인")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (viewPager.currentItem == 0) {
                    // If the user is currently looking at the first step, allow the system to handle the
                    // Back button. This calls finish() on this activity and pops the back stack.
                    activity?.supportFragmentManager!!.popBackStack()
                    // childFragmentManager.popBackStack()
                } else {
                    // Otherwise, select the previous step.
                    viewPager.currentItem = viewPager.currentItem - 1
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onDetach() {
        super.onDetach()
        val bbb: ImageButton? = activity?.toolbar!!.findViewById(R.id.btn_next)
        activity?.toolbar!!.removeView(bbb)
        callback.remove()
    }

    /**
     * A pager adapter that represents 4 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter(fa: Fragment) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment =
            newIntent(position)
    }
}
