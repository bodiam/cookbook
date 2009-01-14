package net.javaisp.cookbook

class Ingredient {
    static belongsTo = Recipe

    Integer amount
    String unit

    Product product

    static constraints = {
        unit(nullable:true)
        amount(nullable:true)
    }

    String toString() {
        return "${amount} ${unit} ${product}"
    }
}