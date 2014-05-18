#!/bin/bash
for BaseName in *; do
  if [ -f "${BaseName}/run.sh" ]; then
    cd ${BaseName}
    sh "./run.sh"
    cd ../
  fi
done
