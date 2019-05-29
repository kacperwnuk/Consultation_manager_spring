package com.example.pik


import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pik.REST.Repository
import com.example.pik.REST.entity.Consultation
import kotlinx.android.synthetic.main.fragment_reserve_consultation.*
import kotlinx.android.synthetic.main.fragment_reserve_consultation.view.*
import java.lang.ref.WeakReference


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val DATE = "date"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ReserveConsultationFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ReserveConsultationFragment : Fragment(),
    FreeConsultationsRecyclerAdapter.ActionListener, SwipeRefreshLayout.OnRefreshListener {

    // TODO: Rename and change types of parameters
    private var date: Long? = null
    private var param2: String? = null

    private lateinit var recyclerAdapter: FreeConsultationsRecyclerAdapter

    private lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            date = it.getLong(DATE)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_reserve_consultation, container, false)
        view.swipe_container.setOnRefreshListener(this)
        update()
        return view
    }

    override fun onRefresh() {
        update()
    }

    fun update() {
        MyAsyncTask(context!!, this, date!!).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null)
    }

    fun updateView(result: FreeConsultationsRecyclerAdapter) {
        view!!.recyclerView.apply {
            setHasFixedSize(true)
            adapter = result
            layoutManager = LinearLayoutManager(context)
        }
        swipe_container.isRefreshing = false
    }

    override fun reserve(id: String) {
        (context as ActionListener).reserve(id)
    }

    interface ActionListener {
        fun reserve(id: String)
    }

    private class MyAsyncTask internal constructor(context: Context, actionListener: ReserveConsultationFragment, val date: Long) :
        AsyncTask<Void, Void, FreeConsultationsRecyclerAdapter>() {

        private val context: WeakReference<Context> = WeakReference(context)
        private val actionListener: WeakReference<ReserveConsultationFragment> = WeakReference(actionListener)

        override fun doInBackground(vararg params: Void): FreeConsultationsRecyclerAdapter {
            val consultations = try {
                val repository = Repository(context.get()!!)
                repository.getFreeConsultations(date).get()
            } catch (e: Exception) {
                listOf<Consultation>()
            }
            return FreeConsultationsRecyclerAdapter(
                consultations.sortedWith(
                    compareBy({ it.date },
                        { it.consultationStartTime })
                ), actionListener.get()!!
            )
        }

        override fun onPostExecute(result: FreeConsultationsRecyclerAdapter) {
            actionListener.get()!!.updateView(result)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param date Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment reserve_consultation.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(date: Long, param2: String) =
            ReserveConsultationFragment().apply {
                arguments = Bundle().apply {
                    putLong(DATE, date)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
