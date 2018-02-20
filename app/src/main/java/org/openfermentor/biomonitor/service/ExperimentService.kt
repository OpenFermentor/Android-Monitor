package org.openfermentor.biomonitor.service

import io.reactivex.Single
import org.openfermentor.biomonitor.service.response.ExperimentLogResponse
import org.openfermentor.biomonitor.service.response.ExperimentPageResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ExperimentService {
  companion object {
    const val QUERY_NAME_PAGE = "page"
    const val QUERY_NAME_TITLE = "title"
    const val QUERY_NAME_ID = "id"
  
    const val QUERY_ID = "{$QUERY_NAME_ID}"
  
    const val URL_BASE = "routines"
    const val URL_LOG = "$URL_BASE/$QUERY_ID/log_entries"
  }
  
  @GET(URL_BASE)
  fun getExperiments(@Query(QUERY_NAME_PAGE) page: Int): Single<ExperimentPageResponse>
  
  @GET(URL_BASE)
  fun searchExperiments(@Query(QUERY_NAME_TITLE) title: String): Single<ExperimentPageResponse>
  
  @GET(URL_LOG)
  fun getLog(@Path(QUERY_NAME_ID) experimentId: Int): Single<ExperimentLogResponse>
}
