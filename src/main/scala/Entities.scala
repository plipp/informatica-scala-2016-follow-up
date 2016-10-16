object Entities {

  sealed abstract class Entity (val abbreviation: Char, val energyBoost: Int)

  // the player itself
  case object Bot extends Entity('M',0)

  case object Fluppet extends Entity ('B',200) // a nice beast (mobile and edible, computer-controlled), blue
  case object Snorg extends Entity ('b',-150) // an evil beast (mobile and predatory, computer-controlled), red
  case object Zugar extends Entity ('P',100) // an edible plant (immobile and edible), green
  case object Toxifera extends Entity ('p',-100) // a poisonous plant (immobile and harmful), yellow
  case object Wall extends Entity ('W',-10) // an immobile obstacle
  case object Hidden extends Entity ('?', 0) // an hidden obstacle

  val entities = Seq(Bot,Fluppet,Snorg,Zugar,Toxifera,Wall)
  val abbreviationToEntity = entities.map(entity => entity.abbreviation -> entity).toMap
}