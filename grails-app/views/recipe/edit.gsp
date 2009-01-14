
<%@ page import="net.javaisp.cookbook.Recipe" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit Recipe</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Recipe List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New Recipe</g:link></span>
        </div>
        <div class="body">
            <h1>Edit Recipe</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${recipeInstance}">
            <div class="errors">
                <g:renderErrors bean="${recipeInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${recipeInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description">Description:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:recipeInstance,field:'description','errors')}">
                                    <input type="text" id="description" name="description" value="${fieldValue(bean:recipeInstance,field:'description')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tools">Tools:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:recipeInstance,field:'tools','errors')}">
                                    <input type="text" id="tools" name="tools" value="${fieldValue(bean:recipeInstance,field:'tools')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="preparation">Preparation:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:recipeInstance,field:'preparation','errors')}">
                                    
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="countryOfOrigin">Country Of Origin:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:recipeInstance,field:'countryOfOrigin','errors')}">
                                    <g:select optionKey="id" from="${net.javaisp.cookbook.Country.list()}" name="countryOfOrigin.id" value="${recipeInstance?.countryOfOrigin?.id}" noSelection="['null':'']"></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="execution">Execution:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:recipeInstance,field:'execution','errors')}">
                                    
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="ingredients">Ingredients:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:recipeInstance,field:'ingredients','errors')}">
                                    
<ul>
<g:each var="i" in="${recipeInstance?.ingredients?}">
    <li><g:link controller="ingredient" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="ingredient" params="['recipe.id':recipeInstance?.id]" action="create">Add Ingredient</g:link>

                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:recipeInstance,field:'name','errors')}">
                                    <input type="text" id="name" name="name" value="${fieldValue(bean:recipeInstance,field:'name')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tags">Tags:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:recipeInstance,field:'tags','errors')}">
                                    
<ul>
<g:each var="t" in="${recipeInstance?.tags?}">
    <li><g:link controller="tag" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="tag" params="['recipe.id':recipeInstance?.id]" action="create">Add Tag</g:link>

                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
