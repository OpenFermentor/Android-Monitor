package org.openfermentor.biomonitor.service.response

import com.google.gson.annotations.SerializedName
import org.openfermentor.biomonitor.model.Experiment

class ExperimentPageResponse {
  @SerializedName("data")
  var experiments = listOf<Experiment>()
  @SerializedName("paginate")
  var pageInfo: PageInfo? = null
}
