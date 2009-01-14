package net.javaisp.cookbook

class Recipe {
    String name
    String description
    String tools

    Instruction preparation
    Instruction execution

    Country countryOfOrigin

    static hasMany = [
            ingredients: Ingredient
    ]

    static embedded = ['preparation', 'execution']

    static constraints = {
        description(nullable:true)
        tools(nullable:true)
        preparation(nullable:true)
        countryOfOrigin(nullable:true)
    }

    static fetchMode = [ingredients:"eager"]

    String toString() {
      "${id}"
    }
}