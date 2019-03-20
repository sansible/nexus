import groovy.json.JsonSlurper

parsed_args = new JsonSlurper().parseText(args)

if (parsed_args.enabled) {
    security.setAnonymousAccess(true)
    log.info('Anonymous access enabled')
} else {
    security.setAnonymousAccess(false)
    log.info('Anonymous access disabled')
}
