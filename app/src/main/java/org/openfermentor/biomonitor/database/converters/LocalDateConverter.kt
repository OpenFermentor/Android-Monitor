package org.openfermentor.biomonitor.database.converters

import com.raizlabs.android.dbflow.converter.TypeConverter
import com.xmartlabs.bigbang.core.extensions.epochSeconds
import com.xmartlabs.bigbang.core.extensions.localDatefromEpochMilli
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneOffset

@com.raizlabs.android.dbflow.annotation.TypeConverter
class LocalDateConverter : TypeConverter<Long, LocalDate>() {
  override fun getDBValue(localDate: LocalDate) = localDate.epochSeconds(ZoneOffset.UTC)
  
  override fun getModelValue(value: Long?) = localDatefromEpochMilli(value ?: 0)
}
