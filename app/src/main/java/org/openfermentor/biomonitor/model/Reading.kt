package org.openfermentor.biomonitor.model

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import org.openfermentor.biomonitor.database.AppDataBase
import org.openfermentor.biomonitor.database.converters.LocalDateTimeConverter
import org.parceler.Parcel
import org.threeten.bp.LocalDateTime

@Parcel(Parcel.Serialization.BEAN)
@Table(database = AppDataBase::class)
class Reading {
  @PrimaryKey
  var id = 0
  @Column
  var routineId = 0
  @Column
  var temp = 0.0
  @Column
  var ph = 0.0
  @Column(typeConverter = LocalDateTimeConverter::class)
  var insertedAt = LocalDateTime.now()
}
