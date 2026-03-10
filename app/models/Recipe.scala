package models

import play.api.libs.json.{Json, OFormat}

case class Ingredient(
  name: String,
  amount: BigDecimal,
  unit: Option[String]
)

object Ingredient {
  implicit val ingredientFormat: OFormat[Ingredient] = Json.format[Ingredient]
}

case class Recipe(
  id: Long,
  name: String,
  ingredients: Seq[Ingredient],
  isVegan: Boolean
)

object Recipe {
  implicit val recipeFormat: OFormat[Recipe] = Json.format[Recipe]
}