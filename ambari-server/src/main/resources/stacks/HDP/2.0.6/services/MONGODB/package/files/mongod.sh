#!/usr/bin/env bash

# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

#
# If this scripted is run out of /usr/bin or some other system bin directory
# it should be linked to and not copied. Things like java jar files are found
# relative to the canonical path of this script.
#

MONGOD="/etc/init.d/mongod"
MONGOD_PID_FILE="/var/run/mongodb/mongod.pid"

case $1 in
start)
    echo  "Starting mongod ... "
    STAT=`$MONGOD start`
    echo $STAT
    ;;
stop)
    echo "Stopping mongod ... "
    STAT=`$MONGOD stop`
    echo $STAT
    ;;
restart)
    echo "Restarting mongod ... "
    STAT=`$MONGOD restart`
    echo $STAT
    ;;
status)
    STAT=`$MONGOD status`
    echo $STAT
    ;;
*)
    echo "Usage: $MONGOD {start|stop|restart|status}" >&2
esac
