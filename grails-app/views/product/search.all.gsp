<%@ page import="net.javaisp.cookbook.Product" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <title>Product List</title>

  <style type="text/css">
    .selected {
      border: 3px solid red;

    }


  </style>

  <g:javascript library="jquery"/>

  <script type="text/javascript">
    $(document).ready(function() {
        var selectedProducts = new Array();

        //i: $(".classOfFruit").click(function(){   .... .... .post with $(this).attr("id")... ....   });
        $(".product").click(function() {
          $("#jsonOutput").empty();

//          $("#selectedProducts").append($(this).attr("id"));
          var productId = $(this).attr("id");

          $("#"+productId).toggleClass("selected");

          var index = $.inArray(productId, selectedProducts);
          //alert(index);
          if(index == -1) {
            selectedProducts.push(productId); // add item
          } else {
            selectedProducts.splice(index, 1); // remove item
          }

          /** Debug */
          $("#selectedProducts").text(selectedProducts.join("|"));

          $.getJSON("findRecipesByProduct",
                {
                  products: selectedProducts
                },
                function(data) {
                  $(data).each( function() {
                     var recipe = this;

                     $("#jsonOutput").append("<b>" + recipe.name + "</b><br />");

                     $(recipe.ingredients).each(function() {
                         $("#jsonOutput").append("<i> * " + getValue(this.amount) + " " + getValue(this.unit) + " " + getValue(this.product.name) + "</i><br />");
                     });
                  });

                }
          );

        });


        /*
      $("a").click(function() {
        alert("You clicked a link");
      });*/

      $("#timeButton").click(function() {
        $("#time h3").load("ajax");
      });

      $("#postForm").submit(function() {
        /** Some code */
        $.post("ajaxPost", {
          message: [$("#msg").val(), "hello", "bye" ] }, function(xml) {
          addRecipe(xml);
        });

        return false;
      });


        $("#jsonForm").submit( function() {
           $.getJSON("ajaxJSON", function(data) {
               alert(data.length);

               $(data).each( function() {
                  var recipe = this;

                  $("#jsonOutput").append("<b>" + recipe.name + "</b><br />");

                  $(recipe.ingredients).each(function() {
                      $("#jsonOutput").append("<i> * " + getValue(this.amount) + " " + getValue(this.unit) + " " + getValue(this.product.name) + "</i><br />");
                  });
               });
           });

            return false;
        });

        function getValue(value) {
            return value == null ? "" : value;
        }


    });

    function addRecipe(xml) {
        $(xml).find('recipe').each( function() {
           var name = $(this).find('name').text();

            $("#output").append("<b>"+name+"</b><br />");

            $(this).find('ingredient').each( function() {
               $("#output").append("<i> - " + $(this).text() +"</i><br />");
            });

        });
    }



  </script>
</head>
<body>
<div class="nav">
  <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}">Home</a></span>
  <span class="menuButton"><g:link class="create" action="create">New Product</g:link></span>
</div>
<div class="body">
  <h1>Product List</h1>

  <ul>
    <li><a href="#">Apple</a></li>
    <li><a href="#">Pear</a></li>
    <li><a href="#">Banana</a></li>
  </ul>

  <p><br/><br/>
    Recipes found: 0
  </p>

  <hr>

  <h1>Ajax test</h1>

  <input type="button" value="Tell me the time!" id="timeButton"/>
  <div id="time"><h3></h3></div>


  <hr/>

  <h1>Another Ajax test, this time with Post</h1>

  <div id="wrapper">
    <p id="messageWindow"><span id="loading">Loading...</span></p>
    <form id="postForm">
      <input type="text" name="message" id="msg"/>
      <input type="submit" value="Post me" id="postButton">
    </form>
  </div>

  <form action="ajaxPost">

    <input type="hidden" name="hiddenProduct" value="1" id="hiddenProduct1" />
    <input type="hidden" name="hiddenProduct" value="2" id="hiddenProduct2" />
    <input type="hidden" name="hiddenProduct" value="3" id="hiddenProduct3" />
    <input type="hidden" name="hiddenProduct" value="4" id="hiddenProduct4" />

    <input type="submit" value="post hidden values">

  </form>

  <div id="output"></div>


  <h1>JSON Stuff</h1>

  <div id="jsonWrapper">
    <form id="jsonForm">
      <input type="submit" value="Post me">
    </form>
  </div>
    <div id="jsonOutput"></div>


${net.javaisp.cookbook.Product.list()}


  <ul>
  <g:each in="${net.javaisp.cookbook.Product.list()}" status="i" var="product">
    <li>${i}. <a href="#" id="${fieldValue(bean:product, field:'id')}" class="product" />${fieldValue(bean:product, field:'name')}</li>
  </g:each>
  </ul>

  <table>
      <tr>
          <td><img src="${createLinkTo(dir:'images/ingredients',file:'aardbei.jpg')}" id='1' class="product" width="75" /></td>
          <td><img src="${createLinkTo(dir:'images/ingredients',file:'andijvie.jpg')}" id='2' class="product" width="75" /></td>
          <td><img src="${createLinkTo(dir:'images/ingredients',file:'banaan.jpg')}" id='3' class="product" width="75" /></td>
          <td><img src="${createLinkTo(dir:'images/ingredients',file:'bloemkool.jpg')}" id='4' class="product" width="75" /></td>
          <td><img src="${createLinkTo(dir:'images/ingredients',file:'boerenkool.jpg')}" id='5' class="product" width="75" /></td>
      </tr>
      <tr>
          <td><img src="${createLinkTo(dir:'images/ingredients',file:'kaas.jpg')}" id='6' class="product" width="75" /></td>
          <td><img src="${createLinkTo(dir:'images/ingredients',file:'komkommer.jpg')}" id='7' class="product" width="75" /></td>
          <td><img src="${createLinkTo(dir:'images/ingredients',file:'paprika.jpg')}" id='8' class="product" width="75" /></td>
          <td><img src="${createLinkTo(dir:'images/ingredients',file:'peultjes.jpg')}" id='9' class="product" width="75" /></td>
          <td><img src="${createLinkTo(dir:'images/ingredients',file:'tomaat.jpg')}" id='10' class="product" width="75" /></td>
      </tr>
      <tr>
          <td><img src="${createLinkTo(dir:'images/ingredients',file:'wortel.jpg')}" id='11' class="product" width="75" /></td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
      </tr>
  </table>

  <h3>Selected products</h3>
  <div id="selectedProducts"></div>

</div>
</body>
</html>
