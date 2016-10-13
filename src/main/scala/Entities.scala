object Entities {

  sealed abstract class Entity (val abbreviation: Char, val energyBoost: Int)

  // the player itself
  case object Bot extends Entity('M',0)

  case object Fluppet extends Entity ('B',200) // a nice beast (mobile and edible, computer-controlled)
  case object Snorg extends Entity ('b',-150) // an evil beast (mobile and predatory, computer-controlled)
  case object Zugar extends Entity ('P',100) // an edible plant (immobile and edible)
  case object Toxifera extends Entity ('p',-100) // a poisonous plant (immobile and harmful)
  case object Wall extends Entity ('W',-10) // an immobile obstacle

  val entities = Seq(Bot,Fluppet,Snorg,Zugar,Toxifera,Wall)
  val abbreviationToEntity = entities.map(entity => entity.abbreviation -> entity).toMap
}