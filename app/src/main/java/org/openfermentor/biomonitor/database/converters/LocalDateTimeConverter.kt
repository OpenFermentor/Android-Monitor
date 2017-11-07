package org.openfermentor.biomonitor.database.converters

import com.raizlabs.android.dbflow.converter.TypeConverter
import com.xmartlabs.bigbang.core.extensions.localDateTimeFromEpochMilli
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset

@com.raizlabs.android.dbflow.annotation.TypeConverter
class LocalDateTimeConverter : TypeConverter<Long, LocalDateTime>() {
  override fun getDBValue(localDate: LocalDateTime) = localDate.toInstant(ZoneOffset.UTC).toEpochMilli()
  
  override fun getModelValue(epoch: Long?) = localDateTimeFromEpochMilli(epoch ?: 0)
}
