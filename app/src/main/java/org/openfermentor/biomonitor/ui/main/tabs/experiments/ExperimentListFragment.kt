package org.openfermentor.biomonitor.ui.main.tabs.experiments

import android.content.res.ColorStateList
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.arlib.floatingsearchview.FloatingSearchView
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs
import kotlinx.android.synthetic.main.fragment_experiment_list.*
import org.openfermentor.biomonitor.R
import org.openfermentor.biomonitor.helper.extensions.startFrom
import org.openfermentor.biomonitor.model.Experiment
import org.openfermentor.biomonitor.ui.Henson
import org.openfermentor.biomonitor.ui.common.base.BioMonitorFragment
import javax.inject.Inject

@FragmentWithArgs
class ExperimentListFragment : BioMonitorFragment<ExperimentListView, ExperimentListPresenter>(), ExperimentListView {
  @Inject
  override lateinit var presenter: ExperimentListPresenter
  
  override val layoutResId = R.layout.fragment_experiment_list
  
  private val adapter by lazy { ExperimentListAdapter() }
  
  override fun setup() {
    progressBar.indeterminateTintList = ColorStateList.valueOf(Color.WHITE)
    setProgressBarVisible(true)
    
    adapter.onClickListener = presenter::onExperimentClicked
    recyclerView.layoutManager = LinearLayoutManager(context)
    recyclerView.isNestedScrollingEnabled = false
    recyclerView.adapter = adapter
  }
  
  override fun experimentsLoaded(experiments: List<Experiment>) {
    setProgressBarVisible(false)
    setSearchViewVisibility(true)
    adapter.setItems(experiments)
    setupSearchView()
  }
  
  private fun setupSearchView() {
    searchView.setOnHomeActionClickListener { presenter.experimentSearchClosed() }
    searchView.setOnFocusChangeListener(object : FloatingSearchView.OnFocusChangeListener {
      override fun onFocus() = presenter.experimentSearchOpened()
      override fun onFocusCleared() = presenter.experimentSearchClosed()
    })
    searchView.setOnSearchListener(object : FloatingSearchView.OnSearchListener {
      override fun onSearchAction(currentQuery: String?) = presenter.search(currentQuery ?: "")
      override fun onSuggestionClicked(searchSuggestion: SearchSuggestion?) = Unit
    })
  }
  
  override fun clearSearchBar() = let { searchView.clearQuery() }
  
  override fun closeSearchBar() = let { searchView.clearSearchFocus() }
  
  override fun setProgressBarVisible(visible: Boolean) {
    progressBar.visibility = if (visible) View.VISIBLE else View.GONE
  }
  
  private fun setSearchViewVisibility(visible: Boolean) {
    searchView.visibility = if (visible) View.VISIBLE else View.GONE
    searchViewBackground.visibility = if (visible) View.VISIBLE else View.GONE
  }
  
  override fun showErrorMessage(error: String) {
    setProgressBarVisible(false)
    setSearchViewVisibility(false)
    
    message.visibility = View.VISIBLE
    message.text = error
  }
  
  override fun goToExperimentActivity(experiment: Experiment) = Henson.with(context)
      .gotoExperimentActivity()
      .experiment(experiment)
      .build()
      .startFrom(activity)
}
