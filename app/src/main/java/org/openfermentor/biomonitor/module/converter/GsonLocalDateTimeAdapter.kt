package org.openfermentor.biomonitor.module.converter

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import com.xmartlabs.bigbang.core.extensions.ifException
import com.xmartlabs.bigbang.core.extensions.stringToLocalDateTime
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import timber.log.Timber
import java.lang.reflect.Type

class GsonLocalDateTimeAdapter : JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
  @Synchronized
  override fun serialize(dateTime: LocalDateTime?, type: Type?,
                         jsonSerializationContext: JsonSerializationContext?) =
      dateTime
          ?.atZone(ZoneId.systemDefault())
          ?.withZoneSameInstant(ZoneId.of("UTC"))
          ?.let { DateTimeFormatter.ISO_DATE_TIME.format(it) }
          ?.ifException(::JsonPrimitive) { Timber.e(it, "Date cannot be serialized, date='%s'", dateTime) }
  
  @Synchronized
  override fun deserialize(jsonElement: JsonElement?, type: Type?,
                           jsonDeserializationContext: JsonDeserializationContext?) =
      jsonElement
          ?.let(JsonElement::toString)
          ?.takeIf(String::isNotEmpty)
          ?.let { stringToLocalDateTime(it, DateTimeFormatter.ISO_DATE_TIME) }
}
