package zio.redis.options

trait Geo {

  sealed case class LongLat(longitude: Double, latitude: Double)

  sealed case class GeoView(member: String, dist: Option[Double], hash: Option[Long], longLat: Option[LongLat])

  sealed trait RadiusUnit { self =>
    private[redis] final def stringify: String =
      self match {
        case RadiusUnit.Meters     => "m"
        case RadiusUnit.Kilometers => "km"
        case RadiusUnit.Feet       => "ft"
        case RadiusUnit.Miles      => "mi"
      }
  }

  object RadiusUnit {
    case object Meters     extends RadiusUnit
    case object Kilometers extends RadiusUnit
    case object Feet       extends RadiusUnit
    case object Miles      extends RadiusUnit
  }

  sealed case class StoreDist(key: String)

  case object WithCoord {
    private[redis] def stringify: String = "WITHCOORD"
  }

  type WithCoord = WithCoord.type

  case object WithDist {
    private[redis] def stringify: String = "WITHDIST"
  }

  type WithDist = WithDist.type

  case object WithHash {
    private[redis] def stringify: String = "WITHHASH"
  }

  type WithHash = WithHash.type

}
