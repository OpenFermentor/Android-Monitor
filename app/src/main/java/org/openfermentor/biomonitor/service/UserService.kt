package org.openfermentor.biomonitor.service

import io.reactivex.Single
import org.openfermentor.biomonitor.service.request.LoginRequest
import org.openfermentor.biomonitor.service.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
  companion object {
    const val URL_BASE = "sessions"
    const val URL_LOGIN = "$URL_BASE/login"
  }
  
  @POST(URL_LOGIN)
  fun login(@Body user: LoginRequest): Single<LoginResponse>
}
