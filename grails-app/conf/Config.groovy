import org.apache.log4j.DailyRollingFileAppender
import org.apache.log4j.Level
import org.apache.log4j.PatternLayout

// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination

// The ACCEPT header will not be used for content negotiation for user agents containing the following strings (defaults to the 4 major rendering engines)
grails.mime.disable.accept.header.userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
grails.mime.types = [ // the first one is the default format
    all:           '*/*', // 'all' maps to '*' or the first available format in withFormat
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    hal:           ['application/hal+json','application/hal+xml'],
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// Legacy setting for codec used to encode data with ${}
grails.views.default.codec = "html"

// The default scope for controllers. May be prototype, session or singleton.
// If unspecified, controllers are prototype scoped.
grails.controllers.defaultScope = 'singleton'

// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside ${}
                scriptlet = 'html' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        // filteringCodecForContentType.'text/html' = 'html'
    }
}


grails.converters.encoding = "UTF-8"
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

// configure passing transaction's read-only attribute to Hibernate session, queries and criterias
// set "singleSession = false" OSIV mode in hibernate configuration after enabling
grails.hibernate.pass.readonly = false
// configure passing read-only to OSIV session by default, requires "singleSession = false" OSIV mode
grails.hibernate.osiv.readonly = false

environments {
    development {
        grails.logging.jul.usebridge = true
        grails.serverURL = "https://localhost:8443/asd-ticket"
        grails.plugin.springsecurity.portMapper.httpPort = "8080"
        grails.plugin.springsecurity.portMapper.httpsPort = "8443"
        // Configuración de Emails
        grails.fromMailAddress = "hospitalgdl@gmail.com"
        grails {
            mail {
                host = "smtp.gmail.com"
                port = 465
                username = "hospitalgdl@gmail.com"
                password = "qwerty12345!"
                props = ["mail.smtp.auth":"true",
                        "mail.smtp.socketFactory.port":"465",
                        "mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
                        "mail.smtp.socketFactory.fallback":"false"]
            }
        }

    }
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
    }
}

// log4j configuration
log.info("Loading logging config...")
def appname = appName
log4j = {
    // Example of changing the log pattern for the default console
    // appender:
    //
    root{
        info 'stdout'
    }

    def appNombre = appname
    def logDirectory = "logs"
    def logLayoutPattern = new PatternLayout("%d{yyyy-MM-dd/HH:mm:ss.SSS} [%t] %x %-5p %c{2} - %m%n")

    appenders {
        console name: 'stdout', layout: pattern(conversionPattern: '%d{yyyy-MMM-dd HH:mm:ss,SSS} %c{2} %m%n')
        console name: 'stdout', layout: pattern(conversionPattern: '%c{2} %m%n')

        appender new DailyRollingFileAppender(name: "customAppender",
                threshold: Level.toLevel("INFO"),
                file: "/${userHome}/grails-configs/${logDirectory}/${appNombre}_logs.log",
                datePattern: "'.'yyyy-MM-dd",   //Rollover at midnight each day.
                layout: logLayoutPattern
        )
    }

    appenders {
        name: 'stacktrace'
        layout: pattern(conversionPattern: '%d{yyyy-MMM-dd HH:mm:ss,SSS} %c{2} %m%n')

    }

    error 'org.codehaus.groovy.grails.web.servlet',  //  controllers
            'org.codehaus.groovy.grails.web.pages', //  GSP
            'org.codehaus.groovy.grails.web.sitemesh', //  layouts
            'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
            'org.codehaus.groovy.grails.web.mapping', // URL mapping
            'org.codehaus.groovy.grails.commons', // core / classloading
            'org.codehaus.groovy.grails.plugins', // plugins
            'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
            'org.springframework',
            'org.hibernate',
            'net.sf.ehcache.hibernate'

    info customAppender:"com.vincoorbis.aeromexico.clubpremier.bo.emails.ImplSendGrid"
}


// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.asd.seven.adventticket.security.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.asd.seven.adventticket.security.UserRole'
grails.plugin.springsecurity.authority.className = 'com.asd.seven.adventticket.security.Role'
grails.plugin.springsecurity.logout.postOnly = false
grails.plugin.springsecurity.auth.forceHttps = false
grails.plugin.springsecurity.securityConfigType = 'InterceptUrlMap'
grails.plugin.springsecurity.interceptUrlMap = [
	'/':                              ['permitAll'],
	'/index':                         ['permitAll'],
	'/index.gsp':                     ['permitAll'],
	'/assets/**':                     ['permitAll'],
	'/**/js/**':                      ['permitAll'],
	'/**/css/**':                     ['permitAll'],
	'/**/images/**':                  ['permitAll'],
	'/**/favicon.ico':                ['permitAll'],
    '/login/**':                      ['permitAll'],
    '/logout/**':                     ['permitAll'],
    '/registro/**':                   ['permitAll'],
    '/enrollment/**':                 ['permitAll'],
    '/perfil/**':                     ['ROLE_ASSISTANT'],
    '/profile/**':                    ['ROLE_ASSISTANT'],
    '/configuracion/**':              ['ROLE_ASSISTANT']
]

grails.plugin.springsecurity.roleHierarchy = '''
   ROLE_SUPERADMIN > ROLE_ORGANIZATION
   ROLE_ORGANIZATION > ROLE_ASSISTANT
'''
