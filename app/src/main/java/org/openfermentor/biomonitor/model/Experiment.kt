package org.openfermentor.biomonitor.model

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.OneToMany
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.kotlinextensions.from
import com.raizlabs.android.dbflow.kotlinextensions.oneToMany
import com.raizlabs.android.dbflow.kotlinextensions.select
import com.raizlabs.android.dbflow.kotlinextensions.where
import org.openfermentor.biomonitor.database.AppDataBase
import org.openfermentor.biomonitor.database.converters.LocalDateTimeConverter
import org.parceler.Parcel
import org.threeten.bp.LocalDateTime

@Parcel(Parcel.Serialization.BEAN)
@Table(database = AppDataBase::class, useBooleanGetterSetters = false)
class Experiment {
  @PrimaryKey
  var id = 0
  @Column
  var uuid = ""
  @Column
  var estimatedTimeSeconds = 0.0
  @Column
  var targetTemp = 0.0
  @Column
  var tempTolerance = 0.0
  @Column
  var targetPh = 0.0
  @Column
  var phTolerance = 0.0
  @Column
  var balancePh = false
  @Column
  var triggerFor: Long? = null
  @Column
  var triggerAfter: Long? = null
  @Column
  var loopDelay = 0
  @Column
  var title = ""
  @Column
  var extraNotes: String? = ""
  @Column
  var strain = ""
  @Column
  var medium = ""
  @Column(typeConverter = LocalDateTimeConverter::class)
  var insertedAt = LocalDateTime.now()
  @get:OneToMany(methods = arrayOf(OneToMany.Method.ALL))
  var tags by oneToMany { select from Tag::class where (Tag_Table.experiment_id.eq(id)) }
  @get:OneToMany(methods = arrayOf(OneToMany.Method.ALL))
  var tempRanges by oneToMany { select from TemperatureRange::class where (TemperatureRange_Table.experiment_id.eq(id)) }
}
