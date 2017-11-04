package org.openfermentor.biomonitor.controller

import com.xmartlabs.bigbang.core.controller.SessionController
import org.openfermentor.biomonitor.model.Session

var SessionController.session
    get() = abstractSession as? Session
    set(value) { value?.let { saveSession(it) } ?: deleteSession() }

fun SessionController.update(block: (Session?) -> Session) { session = block(session) }
