import groovy.json.JsonSlurper

parsed_args = new JsonSlurper().parseText(args)

core.removeHTTPProxy()
if (parsed_args.with_http_proxy) {
    core.httpProxy(parsed_args.http_proxy_host, parsed_args.http_proxy_port as int)
}

core.removeHTTPSProxy()
if (parsed_args.with_https_proxy) {
    core.httpsProxy(parsed_args.https_proxy_host, parsed_args.https_proxy_port as int)
}

if (parsed_args.with_http_proxy || parsed_args.with_https_proxy) {
    core.nonProxyHosts()
    core.nonProxyHosts(parsed_args.no_proxy)
}
