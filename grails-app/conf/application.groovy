

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.grailsinaction.hubbub.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.grailsinaction.hubbub.UserRole'
grails.plugin.springsecurity.authority.className = 'com.grailsinaction.hubbub.Role'
grails.plugin.springsecurity.userLookup.usernamePropertyName = 'loginId'
grails.plugin.springsecurity.userLookup.passwordPropertyName = 'passwordHash'
grails.plugin.springsecurity.auth.loginFormUrl = '/login/form'
grails.plugin.springsecurity.failureHandler.defaultFailureUrl = '/login/form'
grails.plugin.springsecurity.successHandler.defaultTargetUrl = '/timeline'
grails.plugin.springsecurity.rejectIfNoRule = false
grails.plugin.springsecurity.logout.postOnly = false
grails.plugin.springsecurity.securityConfigType = 'InterceptUrlMap'
grails.plugin.springsecurity.interceptUrlMap = [
		[pattern:'/'               ,access: ['permitAll']],
		[pattern:'/post/global'	   ,access: ['permitAll']],
		[pattern:'/user/register*' ,access: ['permitAll']],
		[pattern:'/login/**'	   ,access: ['permitAll']],
		[pattern:'/logout/**'	   ,access: ['permitAll']],
		[pattern:'/error'          ,access: ['permitAll']],
		[pattern:'/index'          ,access: ['permitAll']],
		[pattern:'/index.gsp'      ,access: ['permitAll']],
		[pattern:'/shutdown'       ,access: ['permitAll']],
		[pattern:'/assets/**'      ,access: ['permitAll']],
		[pattern:'/**/js/**'       ,access: ['permitAll']],
		[pattern:'/**/css/**'      ,access: ['permitAll']],
		[pattern:'/**/images/**'   ,access: ['permitAll']],
		[pattern:'/**/favicon.ico' ,access: ['permitAll']],
		[pattern:'/user/profile/**',access: ['isAuthenticated()']],
		[pattern:'/user/**' 	   ,access: ['hasRole("ROLE_ADMIN")']],
		[pattern:'/role/**' 	   ,access: ['hasRole("ROLE_ADMIN")']],
		[pattern:'/**'			   ,access: ['isAuthenticated()']]
]
grails.plugin.springsecurity.useBasicAuth = true
grails.plugin.springsecurity.basic.realmName = "Hubbub"
grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/api/**',		 filters: 'JOINED_FILTERS'],
	[pattern: '/**',             filters: 'JOINED_FILTERS, -basicAuthenticationFilter, -basicExceptionTranslationFilter']
]

