package example

import io.circe.derivation.annotations.JsonCodec

@JsonCodec
// FIXME: https://github.com/scalameta/scalameta/issues/1789
case class MacroAnnotation/*example.MacroAnnotation#*/(
    name/*example.MacroAnnotation#name.*/: String
) {
  def method/*example.MacroAnnotation#method().*/ = 42
}

object MacroAnnotations/*example.MacroAnnotations.*/ {
  import scala.meta._
  // IntelliJ has never managed to goto definition for the inner classes from Trees.scala
  // due to the macro annotations.
  val x/*example.MacroAnnotations.x.*/: Defn.Class = Defn.Class(null, null, null, null, null)
  val y/*example.MacroAnnotations.y.*/: Mod.Final = Mod.Final()
}
