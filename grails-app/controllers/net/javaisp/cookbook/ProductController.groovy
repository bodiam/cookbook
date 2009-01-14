package net.javaisp.cookbook

import grails.converters.deep.JSON

class ProductController {

    def sessionFactory

    def index = { redirect(action: list, params: params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete: 'POST', save: 'POST', update: 'POST']

    def list = {
        if (!params.max) params.max = 10
        [productInstanceList: Product.list(params)]
    }

    def show = {
        def productInstance = Product.get(params.id)

        if (!productInstance) {
            flash.message = "Product not found with id ${params.id}"
            redirect(action: list)
        }
        else { return [productInstance: productInstance] }
    }

    def search = {
        return [productInstanceList: Product.list()]
    }


    def delete = {
        def productInstance = Product.get(params.id)
        if (productInstance) {
            productInstance.delete()
            flash.message = "Product ${params.id} deleted"
            redirect(action: list)
        }
        else {
            flash.message = "Product not found with id ${params.id}"
            redirect(action: list)
        }
    }


    def ajax = {
        def currentTime = new Date()

        render "The time is ${currentTime}"
    }

    def ajaxPost = {
        println "Received parameters ${params}"
        println " message    : ${params.message}"
        println " param.size : ${params.size()}"

        String xml = """
      <recipes>
        <recipe>
          <name>Aardappelstamppot</name>
          <ingredients>
            <ingredient>2 aardappels</ingredient>
            <ingredient>3 uitjes</ingredient>
          </ingredients>
        </recipe>
        <recipe>
           <name>Kippesoep</name>
           <ingredients>
             <ingredient>4 liter water</ingredient>
             <ingredient>een halve kip</ingredient>
           </ingredients>
        </recipe>
      </recipes>
    """

        render xml
    }

    def findRecipesByProduct = {
      println "7"

      println params.products

      if(params.products) {
        def productIds = params.products.collect { it.toLong() }

        // Find all recipes which have the selected products
       
        def recipes = Recipe.executeQuery("select distinct(r) from net.javaisp.cookbook.Recipe as r " +
                                          "inner join r.ingredients as i " +
                                          "inner join i.product as p " +
                                          "where p.id in (:products)" +
                                           "group by r.id having count(*) = :length", [products:productIds, length:productIds.size() as Long])
        /*
        select r.id, count(*) from recipe r
join recipe_ingredient ri on r.id = ri.recipe_ingredients_id
join ingredient i on i.id = ri.ingredient_id
join product p on p.id = i.product_id
where p.id in (1, 2,3,4,5)
group by r.id having count(*) = 5
         */


        // Loop door alle producten er verwijder de producten die niet alle producten hebben



/*
def sessionFactory = ctx.sessionFactory
def session = sessionFactory.getCurrentSession()

Criteria criteria = session.createCriteria(Recipe.class)
criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
List recipes = criteria.createCriteria("ingredients").createCriteria("product").add(Property.forName("id").in( [1L, 2L, 3L] )).list()
*/

        //select tag.text, count(snippet.id) from Tag as tag inner join tag.snippets as snippet group by tag.text


        /*
        def session = sessionFactory.getCurrentSession()
        Query q = session.createQuery("select distinct(r) from net.javaisp.cookbook.Recipe as r inner join r.ingredients as i ")
        def recipes =  q.list()
        */

/*
        def recipes = Recipe.executeQuery("select r from net.javaisp.cookbook.Recipe as r " +
                                          "inner join r.ingredients as i " +
                                          "inner join i.product as p " +
                                          "where p.id in (:products)", [products:productIds])
*/


        /*
        def recipes = Recipe.findAll(
                "from net.javaisp.cookbook.Recipe as r ")
             //   "inner join r.ingredients as i ")
               // "inner join i.product as p " +
               // "where p.id in (:products)", [products:productIds])
*/



        /*
        def c = Recipe.createCriteria()
        c.resultTransformer = CriteriaSpecification.DISTINCT_ROOT_ENTITY
        c.fetchMode "recipe", FM.SELECT

        def recipes = c.list() {
            ingredients {
               product {
                 'in'('id', productIds)
               }
            }
        }*/


        /** ===== TODO ===========
         *

 Maarten Winkels
10:00 AM
je zou SELECT kunnen proberen als fetch mode
10:00 AM

dan wordt het recept alleen gequeried in de eerste select en komt de rest van de data in een volgende select (denk ik)
Erik Pragt
10:00 AM
(ff standup... BRB)
10:00 AM

ff proberen zo
Maarten Winkels
10:00 AM
maar je kan ook een betere query formuleren
10:00 AM

met een subquery (want dat is wat je eigenlijk wil)
10:01 AM

select recipe inner join ingredients inner join product where recipe.is in (select recipe.id inner join ingredients inner join product where product.id in (...))
         *
         *
         */

                

        println "recipes 2 : " + recipes

        recipes.each { recipe ->
          println "> " + recipe.getClass()
        }

        def json = recipes as JSON

        println "findRecipesByProduct result : " + json

        render json
      }
    }


    def ajaxJSON = {
        def recipes = Recipe.list()

        println "recipes : " + recipes

        def output = recipes as JSON

        println "ajaxJSON result : " + output

        render output
    }


    def edit = {
        def productInstance = Product.get(params.id)

        if (!productInstance) {
            flash.message = "Product not found with id ${params.id}"
            redirect(action: list)
        }
        else {
            return [productInstance: productInstance]
        }
    }

    def update = {
        def productInstance = Product.get(params.id)
        if (productInstance) {
            productInstance.properties = params
            if (!productInstance.hasErrors() && productInstance.save()) {
                flash.message = "Product ${params.id} updated"
                redirect(action: show, id: productInstance.id)
            }
            else {
                render(view: 'edit', model: [productInstance: productInstance])
            }
        }
        else {
            flash.message = "Product not found with id ${params.id}"
            redirect(action: edit, id: params.id)
        }
    }

    def create = {
        def productInstance = new Product()
        productInstance.properties = params
        return ['productInstance': productInstance]
    }

    def save = {
        def productInstance = new Product(params)
        if (!productInstance.hasErrors() && productInstance.save()) {
            flash.message = "Product ${productInstance.id} created"
            redirect(action: show, id: productInstance.id)
        }
        else {
            render(view: 'create', model: [productInstance: productInstance])
        }
    }
}
