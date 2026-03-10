package controllers

import models.Recipe
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Request}
import services.RecipeService

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class RecipeController @Inject()(
     cc: ControllerComponents,
     recipeService: RecipeService
  )(implicit ec: ExecutionContext) extends AbstractController(cc) {


  def createRecipe: Action[Recipe] = Action(parse.json[Recipe]) { (request: Request[Recipe]) =>
    val recipe = request.body
    recipeService.saveRecipe(recipe)
    Created(Json.toJson(recipe))
  }


  def getAllRecipes: Action[AnyContent] = Action.async {
    recipeService.getAllRecipes.map {
      recipes => Ok(Json.toJson(recipes))
    }
  }

  def getRecipe(recipeId: Long): Action[AnyContent] = Action.async {
    recipeService.getRecipeById(recipeId).map {
      case Some(recipe) => Ok(Json.toJson(recipe))
      case None => NotFound("The recipe wasn't found")
    }
  }

  def scaleRecipe(id: Long): Action[AnyContent] = Action.async { request =>
    request.getQueryString("multiplier") match {
      case Some(value) =>
        recipeService.scaleRecipe(id, BigDecimal(value)).map {
          case Some(recipe) => Ok(Json.toJson(recipe))
          case None => NotFound("It wasn't possible for scale the recipe")
        }
      case None =>
        Future.successful(BadRequest("Invalid multiplier"))
    }
  }

  def filterVeganRecipes: Action[AnyContent] = Action.async {
    recipeService.filterVeganRecipes.map {
      recipes => Ok(Json.toJson(recipes))
    }
  }
}
