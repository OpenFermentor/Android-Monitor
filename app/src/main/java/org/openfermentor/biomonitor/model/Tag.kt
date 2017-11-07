package org.openfermentor.biomonitor.model

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.ForeignKey
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import org.openfermentor.biomonitor.database.AppDataBase
import org.parceler.Parcel

@Parcel(Parcel.Serialization.BEAN)
@Table(database = AppDataBase::class)
class Tag {
  @PrimaryKey
  var id = 0
  @Column
  var value = ""
  @ForeignKey(stubbedRelationship = true)
  var experiment: Experiment? = null
}
