package org.openfermentor.biomonitor.model

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.ForeignKey
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import org.openfermentor.biomonitor.database.AppDataBase
import org.parceler.Parcel

@Parcel(Parcel.Serialization.BEAN)
@Table(database = AppDataBase::class)
class TemperatureRange {
  @PrimaryKey(autoincrement = true)
  var id = 0
  @Column
  var temp = 0.0
  @Column
  var fromSecond = 0.0
  @ForeignKey(stubbedRelationship = false)
  var experiment: Experiment? = null
}
