# nexus

Master: [![Build Status](https://travis-ci.org/sansible/nexus.svg?branch=master)](https://travis-ci.org/sansible/nexus)
Develop: [![Build Status](https://travis-ci.org/sansible/nexus.svg?branch=develop)](https://travis-ci.org/sansible/nexus)

* [Installation and Dependencies](#installation-and-dependencies)
* [Tags](#tags)
* [Examples](#examples)

This role install and configures Sonatype Nexus, supports version 3 at the moment.


## Installation and Dependencies

To install run `ansible-galaxy install sansible.nexus` or add this to your
`roles.yml`.

```YAML
- name: sansible.nexus
  version: v1.0.0
```

and run `ansible-galaxy install -p ./roles -r roles.yml`


## Tags

This role uses tags: **build** and **configure**

* `build` - Installs Sonatype Nexus
* `configure` - Configures Sonatype Nexus


## Examples

Simply include role in your playbook

```YAML
- name: Install and Configure nexus
  hosts: somehost

  roles:
    - role: sansible.nexus
      sansible_nexus_configuration_admin_password: securepassword
```

Groovy scripts are included in files/groovy for configuring a Nexus
server from code, these scripts can be enabled and configured via
role vars:

```YAML
- name: Install and Configure nexus
  hosts: somehost

  roles:
    - role: sansible.nexus
      sansible_nexus_configuration_admin_password: securepassword
      sansible_nexus_configuration_realms:
        - name: NuGetApiKey
          enabled: true
      sansible_nexus_configuration_roles:
        - id: some-role
          name: some-role
          description: some-role
          privileges:
            - nx-search-read
      sansible_nexus_configuration_s3_blobstores:
        - bucket: some-bucket
          expiration: 3
          name: some-bucket
          region: eu-west-1
```
