
<%@ page import="net.javaisp.cookbook.Recipe" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create Recipe</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Recipe List</g:link></span>
        </div>
        <div class="body">
            <h1>Create Recipe</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${recipeInstance}">
            <div class="errors">
                <g:renderErrors bean="${recipeInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
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
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:recipeInstance,field:'name','errors')}">
                                    <input type="text" id="name" name="name" value="${fieldValue(bean:recipeInstance,field:'name')}"/>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
