package net.javaisp.cookbook

class Product {
    String name
    String description
    //String image

    static constraints = {
        name(nullable:false)
        description(nullable:true)
    }

    String toString() {
        "${name}"
    }
}