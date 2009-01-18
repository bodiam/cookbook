package net.javaisp.cookbook

class Recipe {
    String name
    String description
    String tools

    Instruction preparation
    Instruction execution

    Country countryOfOrigin

    List ingredients

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

    String toString() {
      "${id}"
    }
}