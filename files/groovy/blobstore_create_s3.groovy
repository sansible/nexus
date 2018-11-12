import groovy.json.JsonSlurper

parsed_args = new JsonSlurper().parseText(args)

config = new HashMap <Object,Object> ()
        config.put("bucket", parsed_args.bucket);
        config.put("region", parsed_args.region);
        config.put("expiration", parsed_args.expiration);

existingBlobStore = blobStore.getBlobStoreManager().get(parsed_args.name)
if (existingBlobStore == null) {
    blobStore.createS3BlobStore(parsed_args.name, config)
}
