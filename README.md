# Recipe Management API 🍳

## About this Project
Hi! I built this project over the weekend as a hands-on way to warm up my local development environment and refresh my Scala and Play Framework knowledge ahead of my technical interview. 

This API allows users to create recipes, list them, filter by dietary restrictions (like Vegan), and dynamically scale ingredient amounts using a multiplier (a common use case for chefs planning different batch sizes).

## Tech Stack
* **Language:** Scala
* **Framework:** Play Framework
* **Build Tool:** sbt
* **Data Format:** JSON (`play-json`)

## Features & Endpoints

### 1. Get All Recipes
* **GET** `/recipes`
* Returns a list of all available recipes.

### 2. Get Recipe by ID
* **GET** `/recipes/:id`
* Returns a specific recipe by its ID.

### 3. Create a Recipe
* **POST** `/recipes`
* Accepts a JSON payload to create a new recipe.

### 4. Scale a Recipe (Yield Adjustment)
* **GET** `/recipes/:id/scale?multiplier={value}`
* **Domain Logic:** Dynamically multiplies the `amount` of every ingredient in a specific recipe by the given multiplier. Perfect for scaling a recipe from 2 servings to 20 servings.

### 5. Filter Vegan Recipes
* **GET** `/recipes/vegan`
* Returns only the recipes marked as `isVegan = true`.

## How to Run Locally

1. Make sure you have `sbt` and Java installed.
2. Clone this repository.
3. Open your terminal and navigate to the project folder.
4. Run the following command:
   ```bash
   sbt run
