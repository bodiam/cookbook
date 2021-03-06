
<%@ page import="net.javaisp.cookbook.Recipe" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Recipe List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New Recipe</g:link></span>
        </div>
        <div class="body">
            <h1>Recipe List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="name" title="Name" />
                        
                   	        <g:sortableColumn property="tools" title="Tools" />
                        
                   	        <th>Preparation</th>
                   	    
                   	        <th>Country Of Origin</th>
                   	    
                   	        <th>Execution</th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${recipeInstanceList}" status="i" var="recipeInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${recipeInstance.id}">${fieldValue(bean:recipeInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:recipeInstance, field:'name')}</td>
                        
                            <td>${fieldValue(bean:recipeInstance, field:'tools')}</td>
                        
                            <td>${fieldValue(bean:recipeInstance, field:'preparation')}</td>
                        
                            <td>${fieldValue(bean:recipeInstance, field:'countryOfOrigin')}</td>
                        
                            <td>${fieldValue(bean:recipeInstance, field:'execution')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${Recipe.count()}" />
            </div>
        </div>
    </body>
</html>
