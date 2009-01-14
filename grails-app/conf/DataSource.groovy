dataSource {
	pooled = true
//	driverClassName = "org.hsqldb.jdbcDriver"
	driverClassName = "com.mysql.jdbc.Driver"
	username = "root"
	password = "root"
}
hibernate {
    cache.use_second_level_cache=true
    cache.use_query_cache=true
    cache.provider_class='com.opensymphony.oscache.hibernate.OSCacheProvider'
}
// environment specific settings
environments {
	development {
		dataSource {
//			dbCreate = "create" // one of 'create', 'create-drop','update'
			dbCreate = "create-drop" // one of 'create', 'create-drop','update'
//			url = "jdbc:hsqldb:mem:devDB"
			url = "jdbc:mysql://localhost:3306/recipes"
//            loggingSql = true
		}
	}
	test {
		dataSource {
			dbCreate = "update"
			url = "jdbc:hsqldb:mem:testDb"
		}
	}
	production {
		dataSource {
			dbCreate = "update"
			url = "jdbc:hsqldb:file:prodDb;shutdown=true"
		}
	}
}