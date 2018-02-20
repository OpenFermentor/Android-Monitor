package org.openfermentor.biomonitor.service.response

class PageInfo {
  var page = ""
  var maxPage = ""
  
  val hasMorePagesToLoad
    get() = page != maxPage
}
