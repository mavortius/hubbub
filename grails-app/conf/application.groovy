

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.grailsinaction.hubbub.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.grailsinaction.hubbub.UserRole'
grails.plugin.springsecurity.authority.className = 'com.grailsinaction.hubbub.Role'
grails.plugin.springsecurity.userLookup.usernamePropertyName = 'loginId'
grails.plugin.springsecurity.userLookup.passwordPropertyName = 'passwordHash'
grails.plugin.springsecurity.rejectIfNoRule = false
grails.plugin.springsecurity.logout.postOnly = false
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
		[pattern:'/'               ,access: ['permitAll']],
		[pattern:'/error'          ,access: ['permitAll']],
		[pattern:'/index'          ,access: ['permitAll']],
		[pattern:'/index.gsp'      ,access: ['permitAll']],
		[pattern:'/shutdown'       ,access: ['permitAll']],
		[pattern:'/assets/**'      ,access: ['permitAll']],
		[pattern:'/**/js/**'       ,access: ['permitAll']],
		[pattern:'/**/css/**'      ,access: ['permitAll']],
		[pattern:'/**/images/**'   ,access: ['permitAll']],
		[pattern:'/**/favicon.ico' ,access: ['permitAll']]
]
grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/api/**',		 filters: 'JOINED_FILTERS,-anonymousAuthenticationFilter,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-rememberMeAuthenticationFilter'],
	[pattern: '/**',             filters: 'JOINED_FILTERS,-restTokenValidationFilter,-restExceptionTranslationFilter']
]

