import net.javaisp.cookbook.*

class BootStrap {

    def init = {servletContext ->
        initializeRecipes()

        println "Number of recipes loaded: " + Recipe.count()

        Recipe.list().each { println "* ${it.name}" }
    }

    private def initializeRecipes() {
        createRecipeLamsboutUitDeOven()
        createKrokanteKipstukjesMetKnoflookMayonaise()
        createGewokteKerrieMassalaVisMetCitroen()
    }



    private def createRecipeLamsboutUitDeOven() {
        def recipe = new Recipe(
                name: 'Lamsbout uit de oven',
                description: 'Een lekker mals boutje uit de over',
                tools: 'wat keukenpapier, een braadslee, wat aluminiumfolie en een zeef.',
                preparation: new Instruction(
                        text: """Lekker met verse spaghetti en snijbonen.
Verwarmen de oven voor op 225 graden Celsius. Neem de lamsbout een half uur voor het bereiden uit de koelkast en dep het vlees droog met keukenpapier.
Snijd de knoflookteentjes eerst in plakjes en vervolgens in dunne pijltjes. Met een mes steeds een kleine inkeping in het vlees maken en er een stukje knoflook in steken.
Leg het vlees in de braadslee van de oven en bedek met de in dunne plakjes gesneden koude boter. Zet de braadslee in de oven. Verlaag de temperatuur na vijftien minuten tot 175 graden Celsius en nog vijftien ? twintig minuten verder laten braden, zodat het lamsvlees roze blijft. Bedruip het vlees iedere tien minuten met het braadvet.
Neem het vlees uit de braadslee, bestrooi met peper en zout en laat de lamsbout gewikkeld in aluminiumfolie tien minuten rusten.
Verwijder het vet uit de braadslee en schenk de wijn in de braadslee. Maak met een vork alle aanbaksels los. Kook de wijn tot de helft in,voeg de lamsfond toe en laat het geheel nog even inkoken. Zeef de saus, breng op smaak met peper en zout. Snijd de lamsbout in dunne plakken en serveer de saus bij het vlees.
                        """,
                        timeInMinutes: 30
                ),
                execution: new Instruction(
                        text: """Lekker met verse spaghetti en snijbonen.
Verwarmen de oven voor op 225 graden Celsius. Neem de lamsbout een half uur voor het bereiden uit de koelkast en dep het vlees droog met keukenpapier.
Snijd de knoflookteentjes eerst in plakjes en vervolgens in dunne pijltjes. Met een mes steeds een kleine inkeping in het vlees maken en er een stukje knoflook in steken.
Leg het vlees in de braadslee van de oven en bedek met de in dunne plakjes gesneden koude boter. Zet de braadslee in de oven. Verlaag de temperatuur na vijftien minuten tot 175 graden Celsius en nog vijftien ? twintig minuten verder laten braden, zodat het lamsvlees roze blijft. Bedruip het vlees iedere tien minuten met het braadvet.
Neem het vlees uit de braadslee, bestrooi met peper en zout en laat de lamsbout gewikkeld in aluminiumfolie tien minuten rusten.
Verwijder het vet uit de braadslee en schenk de wijn in de braadslee. Maak met een vork alle aanbaksels los. Kook de wijn tot de helft in,voeg de lamsfond toe en laat het geheel nog even inkoken. Zeef de saus, breng op smaak met peper en zout. Snijd de lamsbout in dunne plakken en serveer de saus bij het vlees.
                        """,
                        timeInMinutes: 30
                )
        )

        def ingredients = [
                new Ingredient(amount: 550, unit: 'gram', product: findProduct('Lamsbout')),
                new Ingredient(amount: 200, unit: 'centiliter', product: findProduct('Rode wijn')),
                new Ingredient(amount: 100, unit: 'centiliter', product: findProduct('Lamsfond')),
                new Ingredient(product: findProduct('Peper')),
                new Ingredient(product: findProduct('Zout'))
        ]

        ingredients.each {ingredient ->
            recipe.addToIngredients(ingredient)
        }

        recipe.save()

        if (recipe.hasErrors()) {
            println recipe.errors
        }
    }

    def destroy = {
    }

    private def createKrokanteKipstukjesMetKnoflookMayonaise() {
        println "hello! "
        def recipe = new Recipe(
                name: 'Krokante kipstukjes met knoflook mayonaise',
                execution: new Instruction(
                        text: """De kip in blokjes snijden en bruin bakken in de olie. De kip uit de pan scheppen en het bakvet verwijderen. Kip weer in de pan doen en sherry erbij schenken. Knoflook er boven uitpersen en met komijn, paprikapoeder en tijm er doorscheppen. Afgedekt op een zacht vuur in circa 5 minuten gaar stoven. Op smaak brengen met peper en zout. Kipstukjes op kamertemperatuur serveren met de knoflookmayonaise. """,
                )
        )

        def ingredients = [
                new Ingredient(amount: 500, unit: 'gram', product: findProduct('Kipfilet')),
                new Ingredient(amount: 1, unit: 'eetlepel', product: findProduct('Olie')),
                new Ingredient(amount: 3, unit: 'theelepel', product: findProduct('Komijn')),
                new Ingredient(amount: 1.5, unit: 'theelepel', product: findProduct('Gedroogde tijm')),
                new Ingredient(amount: 100, unit: 'centiliter', product: findProduct('Lamsfond')),
                new Ingredient(product: findProduct('Peper')),
                new Ingredient(product: findProduct('Zout'))
        ]

        ingredients.each {ingredient ->
            recipe.addToIngredients(ingredient)
        }

        recipe.save()

        if (recipe.hasErrors()) {
            println recipe.errors
        }
    }

    private def createGewokteKerrieMassalaVisMetCitroen() {
      def recipe = new Recipe(
              name:'Gewokte Kerrie masala vis met citroen en koriander',
              execution: new Instruction(
                      text: """Snijd de vis in stukken van ca. 3 cm.
                        Was en rasp de citroenschil op een fijne rasp. Pers de citroen uit en besprenkel de vis met 2 tl citroensap.
                        Kook de rijst volgens de aanwijzing op de verpakking. Kook de snijbonen de laatste 5 minuten met de rijst mee.
                        Verhit een wok, voeg een scheutje olie toe en wok de stukjes visfilet 3 minuten. Roer de saus door de vis en laat 3 minuten pruttelen.
                        Meng ondertussen de rijst en de snijbonen met de geraspte citroenschil, koriander, ketjap en citroensap naar smaak.
                        Schep de rijst op 3-4 borden en schep de kerrie masala vis ernaast. Leg de kroepoek erbij.
                            """
              )
      )

      def ingredients = [
               new Ingredient(amount: 400, unit: 'gram', product: findProduct('Stevige visfilet (bijv. pangasiusfilet)')),
               new Ingredient(amount: 1, unit: 'stuk', product: findProduct('Citroen')),
               new Ingredient(amount: 400, unit: 'gram', product:findProduct('Witte rijst')),
               new Ingredient(amount: 600, unit: 'gram', product:findProduct('Gesneden snijbonen')),
               new Ingredient(product: findProduct('Conimex Wok Olie')),
               new Ingredient(amount: 1, unit: 'zak', product:findProduct('Conimex Roerbaksaus Kerrie Masala')),
               new Ingredient(amount: 2, unit: 'eetlepel', product:findProduct('fijngehakte koriander')),
               new Ingredient(amount: 0.5, unit: 'zak', product:findProduct('Conimex Cassave Kroepoek')),
               new Ingredient(amount: 1, unit: 'eetlepel', product:findProduct('Conimex Ketjap Manis'))
      ]

      ingredients.each {ingredient ->
            recipe.addToIngredients(ingredient)
      }

      recipe.save()

      if (recipe.hasErrors()) {
            println recipe.errors
      }
    }




    Product findProduct(String name) {
        if(!Product.findByName(name)) {
            new Product(name:name).save()
        }

        return Product.findByName(name)
    }
}