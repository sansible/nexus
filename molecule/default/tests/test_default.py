import os
import re

import testinfra.utils.ansible_runner

testinfra_hosts = testinfra.utils.ansible_runner.AnsibleRunner(
    os.environ['MOLECULE_INVENTORY_FILE']).get_hosts('all')


def test_config(host):
    nexus_config = host.file(
        '/home/nexus/sonatype-work/nexus3/etc/nexus.properties'
    )
    assert nexus_config.exists
    assert nexus_config.contains(r'^application\-host=0\.0\.0\.0$')
    assert nexus_config.contains(r'^application\-port=8081$')


def test_listening(host):
    assert host.socket('tcp://0.0.0.0:8081').is_listening


def test_process(host):
    process = host.process.get(user='nexus', comm='java')
    mem_settings = [
        '-Xms1200M', '-Xmx1200M', '-XX:MaxDirectMemorySize=2G',
    ]
    for setting in mem_settings:
        assert setting in process.args

    process_limits = host.file('/proc/%i/limits' % process.pid).content_string
    assert re.search(r'Max open files\s+65536\s+65536\s+', process_limits)


def test_users(host):
    assert host.user('nexus').group == 'nexus'
