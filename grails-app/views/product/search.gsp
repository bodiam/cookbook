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
  .recipeRow {
    border: 1px solid #ddd;
    width: 100%;
    height: 50px;
    margin: 5px 0 0 0;
    padding: 5px
  }

  .withBorder {
    border: 1px solid #ddd;
  }
  </style>

  <g:javascript library="jquery"/>

  <script type="text/javascript">
    function getValue(value) {
      return value == null ? "" : value;
    }

    $(document).ready(function() {
       var lastSearchKeyword = $("#name");

      $("#name").keyup(function() {
        var keyword = $("#name").val();

        if (lastSearchKeyword == keyword) {
          return false;
        }
        lastSearchKeyword = keyword;

        $.getJSON("findRecipesByName",
        {
          name: keyword
        },
        function(data) {
          $("#result").empty();

          $(data).each(function() {
            var recipe = this;

            var result = '<div class="recipeRow">';
            result += '<p><b>' + recipe.name + '</b></p>';
            result += '<img class="withBorder" src="${createLinkTo(dir: 'images/ingredients', file: 'aardbei.jpg')}" width="50" align="left"/>';
            result += '<i>Ingredienten:';

            $(recipe.ingredients).each(function () {
              var ingredient = this;

              result += getValue(ingredient.product.name) + ", ";
            });

            result += '</i><br /><br />';
            result += getValue(recipe.description);
            result += '</div';

            $("#result").append(result);
          });
        });

      });


      /*
      var selectedProducts = new Array();

      $(".product").click(function() {
        $("#jsonOutput").empty();

        //          $("#selectedProducts").append($(this).attr("id"));
        var productId = $(this).attr("id");

        $("#" + productId).toggleClass("selected");

        var index = $.inArray(productId, selectedProducts);
        //alert(index);
        if (index == -1) {
          selectedProducts.push(productId); // add item
        } else {
          selectedProducts.splice(index, 1); // remove item
        }

        $("#selectedProducts").text(selectedProducts.join("|"));

        $.getJSON("findRecipesByProduct",
        {
          products: selectedProducts
        },
        function(data) {
          $(data).each(function() {
            var recipe = this;

            $("#jsonOutput").append("<b>" + recipe.name + "</b><br />");

            $(recipe.ingredients).each(function() {
              $("#jsonOutput").append("<i> * " + getValue(this.amount) + " " + getValue(this.unit) + " " + getValue(this.product.name) + "</i><br />");
            });
          });

        }
        );
      });

      */
    });


  </script>
</head>
<body>
<div class="nav">
  <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}">Home</a></span>
  <span class="menuButton"><g:link class="create" action="create">New Product</g:link></span>
</div>
<div class="body">
  <br /><br />
  <form id="findRecipes">
    <table>
      <tr>
        <td><input type="text" name="name" id="name" value="${params.name}" style="width: 100%; font-size: 28px"/></td>
      </tr>
      <tr>
        <th>searching...</th>
      </tr>
    </table>

    <h1>Result</h1>

    <div id="result">
      <div class="recipeRow">
          <p><b>Kip met appelmoes</b></p>
               <img class="withBorder" src="${createLinkTo(dir: 'images/ingredients', file: 'aardbei.jpg')}" width="50" align="left"/>
               <i>Ingredienten: Aardbei, kip, appelmoes.</i><br />
               <br />Some description about the recipe...
      </div>
    </div>
  </form>
</div>

</body>
</html>
