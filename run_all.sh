#!/bin/bash
regex="#[[:space:]]+VERSION[[:space:]]([0-9.]+)"
for BaseName in *; do
  if [ -f "${BaseName}/run.sh" ]; then
    cd ${BaseName}
    sh "./run.sh"
    cd ../
  fi
done
