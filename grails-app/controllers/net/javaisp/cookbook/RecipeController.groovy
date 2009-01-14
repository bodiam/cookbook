package net.javaisp.cookbook

class RecipeController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ recipeInstanceList: Recipe.list( params ) ]
    }

    def show = {
       // def recipeInstance = Recipe.get( params.id )

        params.id


        println "hello!!!!"
        def recipeInstance = Recipe.find("from net.javaisp.cookbook.Recipe r inner join fetch r.ingredients as i  where r.id = ?", [new Long(params.id)])

        if(!recipeInstance) {
            flash.message = "Recipe not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ recipeInstance : recipeInstance ] }
    }

    def delete = {
        def recipeInstance = Recipe.get( params.id )
        if(recipeInstance) {
            recipeInstance.delete()
            flash.message = "Recipe ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "Recipe not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def recipeInstance = Recipe.get( params.id )

        if(!recipeInstance) {
            flash.message = "Recipe not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ recipeInstance : recipeInstance ]
        }
    }

    def update = {
        def recipeInstance = Recipe.get( params.id )
        if(recipeInstance) {
            recipeInstance.properties = params
            if(!recipeInstance.hasErrors() && recipeInstance.save()) {
                flash.message = "Recipe ${params.id} updated"
                redirect(action:show,id:recipeInstance.id)
            }
            else {
                render(view:'edit',model:[recipeInstance:recipeInstance])
            }
        }
        else {
            flash.message = "Recipe not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def recipeInstance = new Recipe()
        recipeInstance.properties = params
        return ['recipeInstance':recipeInstance]
    }

    def save = {
        def recipeInstance = new Recipe(params)
        if(!recipeInstance.hasErrors() && recipeInstance.save()) {
            flash.message = "Recipe ${recipeInstance.id} created"
            redirect(action:show,id:recipeInstance.id)
        }
        else {
            render(view:'create',model:[recipeInstance:recipeInstance])
        }
    }
}
