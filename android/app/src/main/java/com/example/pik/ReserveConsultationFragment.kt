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

/**
 * A simple [Fragment] subclass.
 * Use the [ReserveConsultationFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ReserveConsultationFragment : Fragment(),
    FreeConsultationsRecyclerAdapter.ActionListener, SwipeRefreshLayout.OnRefreshListener {

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
        MyAsyncTask(context!!, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null)
    }

    fun updateView(result: FreeConsultationsRecyclerAdapter) {
        try {
            view!!.recyclerView.apply {
                setHasFixedSize(true)
                adapter = result
                layoutManager = LinearLayoutManager(context)
            }
        } catch (e: NullPointerException) {

        }
        swipe_container.isRefreshing = false
    }

    override fun reserve(id: String) {
        (context as ActionListener).reserve(id)
    }

    interface ActionListener {
        fun reserve(id: String)
    }

    private class MyAsyncTask internal constructor(context: Context, actionListener: ReserveConsultationFragment) :
        AsyncTask<Void, Void, FreeConsultationsRecyclerAdapter>() {

        private val context: WeakReference<Context> = WeakReference(context)
        private val actionListener: WeakReference<ReserveConsultationFragment> = WeakReference(actionListener)

        override fun doInBackground(vararg params: Void): FreeConsultationsRecyclerAdapter {
            val consultations = try {
                val repository = Repository(context.get()!!)
                repository.getFreeConsultations().get()
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
         * @return A new instance of fragment reserve_consultation.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = ReserveConsultationFragment()
    }
}
