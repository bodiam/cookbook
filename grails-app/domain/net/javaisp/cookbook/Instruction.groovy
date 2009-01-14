package net.javaisp.cookbook

class Instruction {
    static belongsTo = Recipe
    
    String text
    Integer timeInMinutes

    static constraints = {
        timeInMinutes(nullable:true)
        text(nullable:true, size:0..4000)
//        text(size:1..4000)                     // TODO: Hack until Grails 1.0.5 has been released... ?
    }

    String toString() {
      return text
    }
}