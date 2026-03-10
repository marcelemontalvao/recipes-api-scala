package repositories

import models.{Ingredient, Recipe}

import javax.inject.Singleton
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class RecipeRepository {

  //"For simplicity I'm using an in-memory repository. In a real application this would be backed by a database or a persistent store."
  private var recipes: Map[Long, Recipe] = Map(
    1L -> Recipe(
      1L,
      "Pancakes",
      Seq(
        Ingredient("Flour", BigDecimal(200), Some("g")),
        Ingredient("Milk", BigDecimal(300), Some("ml")),
        Ingredient("Egg", BigDecimal(2), Some("unit")),
        Ingredient("Salt", BigDecimal(5), None)
      ),
      isVegan = true
    ),
    2L -> Recipe(
      2L,
      "Pancakes 2",
      Seq(
        Ingredient("Flour", BigDecimal(200), Some("g")),
        Ingredient("Milk", BigDecimal(300), Some("ml")),
        Ingredient("Egg", BigDecimal(2), Some("unit")),
        Ingredient("Salt", BigDecimal(5), None)
      ),
      isVegan = false
    ),
    3L -> Recipe(
      3L,
      "Pancakes 3",
      Seq(
        Ingredient("Flour", BigDecimal(200), Some("g")),
        Ingredient("Milk", BigDecimal(300), Some("ml")),
        Ingredient("Egg", BigDecimal(2), Some("unit")),
        Ingredient("Salt", BigDecimal(5), None)
      ),
      isVegan = false
    )
  )

  def save(recipe: Recipe): Recipe = {
    recipes = recipes + (recipe.id -> recipe)
    recipe
  }

  def getAll: Future[Seq[Recipe]] =
    Future.successful(recipes.values.toSeq)

  def getById(id: Long): Future[Option[Recipe]] =
    Future.successful(recipes.get(id))

  def getVeganRecipes: Future[Seq[Recipe]] = {
    val recipes = getAll
    recipes.map(_.filter(_.isVegan))
  }
}