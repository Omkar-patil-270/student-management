#!/bin/sh
# ----------------------------------------------------------------------------
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#    https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
# ----------------------------------------------------------------------------

# ----------------------------------------------------------------------------
# Maven Start Up Batch script
# ----------------------------------------------------------------------------

die() {
    echo "$*" >&2
    exit 1
}

[ -z "$JAVA_HOME" ] && die "JAVA_HOME is not set."

WRAPPER_PROP=".mvn/wrapper/maven-wrapper.properties"
[ -f "$WRAPPER_PROP" ] || die "$WRAPPER_PROP not found."

DIST_URL=$(grep 'distributionUrl=' "$WRAPPER_PROP" | cut -d= -f2-)
[ -z "$DIST_URL" ] && die "distributionUrl not specified."

DIR_NAME=$(basename "$DIST_URL" .zip)
MVN_DIR="$HOME/.m2/wrapper/dists/$DIR_NAME"
MVN_BIN="$MVN_DIR/apache-maven-3.9.6/bin/mvn"

if [ ! -x "$MVN_BIN" ]; then
    mkdir -p "$MVN_DIR"
    echo "Downloading Maven from $DIST_URL..."
    curl -fsSL "$DIST_URL" -o "$MVN_DIR/maven.zip" || wget -qO "$MVN_DIR/maven.zip" "$DIST_URL"
    unzip -qo "$MVN_DIR/maven.zip" -d "$MVN_DIR"
    rm -f "$MVN_DIR/maven.zip"
    chmod +x "$MVN_DIR"/apache-maven-*/bin/mvn
    MVN_BIN=$(find "$MVN_DIR" -name mvn -type f -perm +111 | head -n 1)
fi

exec "$MVN_BIN" "$@"
