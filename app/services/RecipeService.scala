package services

import models.Recipe
import repositories.RecipeRepository

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class RecipeService @Inject()(recipeRepository: RecipeRepository)(implicit ec: ExecutionContext) {

  //The ExecutionContext is required when transforming Futures.
  // In the service layer I'm mapping over the Future returned by the repository, so I need an implicit ExecutionContext.

  def createRecipe(recipe: Recipe): Future[Recipe] =
    Future.successful(recipeRepository.save(recipe))

  def getAllRecipes: Future[Seq[Recipe]] =
    recipeRepository.getAll

  def getRecipeById(id: Long): Future[Option[Recipe]] =
    recipeRepository.getById(id)

  def saveRecipe(recipe: Recipe): Recipe =
    recipeRepository.save(recipe)

  private def scale(recipe: Recipe, multiplier: BigDecimal): Recipe = {
    recipe.copy(
      ingredients = recipe.ingredients.map {
        ingredient => ingredient.copy(amount = ingredient.amount *(multiplier))
      }
    )
  }

  def scaleRecipe(id: Long, multiplier: BigDecimal): Future[Option[Recipe]] =
    recipeRepository
      .getById(id)
      .map(_.map(scale(_, multiplier)))

  def filterVeganRecipes: Future[Seq[Recipe]] = recipeRepository.getVeganRecipes
}
