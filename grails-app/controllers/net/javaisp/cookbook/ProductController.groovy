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
      if(params.products) {
        def productIds = params.products.collect { it.toLong() }

        // Find all recipes which have the selected products
        def recipes = Recipe.executeQuery("select distinct(r) from net.javaisp.cookbook.Recipe as r " +
                                          "inner join r.ingredients as i " +
                                          "inner join i.product as p " +
                                          "where p.id in (:products)" +
                                           "group by r.id having count(*) = :length", [products:productIds, length:productIds.size() as Long])

        render recipes as JSON
      }
    }

    def findRecipesByName = {
      if(params.name) {
        def recipes = Recipe.findAllByNameLike("%${params.name}%")

        log.debug "findRecipesByName returned ${recipes.size()} recipes: ${recipes}"

        render recipes as JSON
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
